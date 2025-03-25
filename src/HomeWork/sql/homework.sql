-- ЗАДАНИЕ 1
create database testhhdatabase;

create table public.specialization
(
    id serial primary key,
    title varchar(120) not null
);

create table public.areas
(
    id serial primary key,
    title varchar(120) not null
);

create table public.vacancy
(
    id serial primary key,
    title varchar(120),
    employer_id integer not null,
    area_id integer not null,
    compensation_from integer default 0,
    compensation_to integer default 0,
    created timestamp not null,
    constraint respond_areas_id_fk
        foreign key (area_id) references public.areas (id)
);

-- Промежуточная таблица для связи вакансий и специализаций
CREATE TABLE public.vacancy_specialization
(
    vacancy_id        INTEGER NOT NULL,
    specialization_id INTEGER NOT NULL,
    PRIMARY KEY (vacancy_id, specialization_id),
    FOREIGN KEY (vacancy_id) REFERENCES public.vacancy (id) ON DELETE CASCADE,
    FOREIGN KEY (specialization_id) REFERENCES public.specialization (id) ON DELETE CASCADE
);

create table public.resume
(
    id serial primary key,
    title varchar(120),
    applicant_id integer not null,
    specialization integer not null,
    area_id integer not null,
    compensation integer default 0,
    created timestamp not null,
    constraint respond_areas_id_fk
        foreign key (area_id) references public.areas (id)
);

create table public.respond
(
    id serial primary key,
    resume_id integer not null,
    vacancy_id integer not null,
    created timestamp not null,
    constraint respond_resume_id_fk
        foreign key (resume_id) references public.resume (id),
    constraint respond_vacancy_id_fk
        foreign key (vacancy_id) references public.vacancy (id)
);

------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------

-- ЗАДАНИЕ 2

-- добавляем данные в specialization
insert into specialization(title)
values ('Дворник'), ('Художник'), ('Строитель'), ('Менеджер'), ('Продавец'), ('Охранник'), ('Военный') ,('Писатель'), ('Журналист'), ('Певец');

-- добавляем данные в areas
insert into areas(title)
values ('Москва'), ('Санкт-Петербург'), ('Екатеринбург'), ('Самара'), ('Вологда'), ('Новосибирск'), ('Ростов-на-Дону') ,('Тюмень'), ('Красноярск'), ('Омск');

-- добавляем данные в vacancy
with
    test_data_for_vacancy (id, title, employer_id, area_id, compensation_from , compensation_to, created) as
        (select
             generate_series(1, 10000),
             CONCAT('Вакансия от компании - ', md5(random()::text)),
             random(1, 10),
             random(1, 10),
             random(1000, 10000),
             random(20000, 100000),
             timestamp '2022-01-01' + INTERVAL '1 day' * round(random() * 730)
    )

INSERT INTO vacancy (id, title, employer_id, area_id, compensation_from , compensation_to, created)
SELECT *
FROM test_data_for_vacancy;

-- добавляем данные в промежуточную таблицу vacancy_specialization
DO $$
    DECLARE
v_id INT;
        spec_id INT;
        specialization_count INT;
BEGIN
        -- Для каждой вакансии
FOR v_id IN (select id from public.vacancy) LOOP
                -- Определяем случайное количество специализаций для этой вакансии (от 1 до 3)
                specialization_count := FLOOR(random() * 3 + 1)::INT;

                -- Для каждой специализации, которую мы хотим связать с этой вакансией
FOR i IN 1..specialization_count LOOP
                        -- Выбираем случайный ID специализации (предположим, от 1 до 10)
                        spec_id := (select id from public.specialization order by random() limit 1);

                        -- Вставляем связь в таблицу vacancy_specialization, игнорируя дубли
INSERT INTO public.vacancy_specialization (vacancy_id, specialization_id)
VALUES (v_id, spec_id)
    ON CONFLICT (vacancy_id, specialization_id) DO NOTHING;
END LOOP;
END LOOP;
END $$;

-- добавляем данные в resume
with
    test_data_for_resume (id, title, applicant_id, specialization, area_id, compensation, created) as
        (select
             generate_series(1, 100000),
             CONCAT('Резюме от соискателя - ', md5(random()::text)),
             random(1, 10),
             random(1,10),
             random(1, 10),
             random(1000, 10000),
             timestamp '2022-01-01' + INTERVAL '1 day' * round(random() * 730)
    )

INSERT INTO public.resume (id, title, applicant_id, specialization, area_id, compensation, created)
SELECT *
FROM test_data_for_resume;

-- добавляем данные в respond
with
    test_data_for_respond (id, resume_id, vacancy_id, created) as
        (select
             generate_series(1, 10000),
             random(1, 10000),
             random(1, 10000),
             timestamp '2022-01-01' + INTERVAL '1 day' * round(random() * 730)
    )

INSERT INTO public.respond (id, resume_id, vacancy_id, created)
SELECT *
FROM test_data_for_respond;

------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------

-- ЗАДАНИЕ 3
select areas.title as areas_title,
       round(avg(vacancy.compensation_from), 2) as avg_com_from,
       round(avg(vacancy.compensation_to), 2) as avg_com_to,
       round(avg(compensation_from + vacancy.compensation_to), 2) as avg_com_from_plus_to
from vacancy join areas on vacancy.area_id = areas.id
group by areas.title
order by avg_com_from_plus_to desc;

------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------

-- ЗАДАНИЕ 4
-- самый злачный месяц по созданным вакансиям
select to_char(vacancy.created, 'Month') || (extract(year from vacancy.created)) AS max_month_with_vacancy,
       count(*) AS count_vacancies
from public.vacancy
group by max_month_with_vacancy
order by count_vacancies desc
    limit 1;

-- все месяца по созданным вакансиям
select to_char(vacancy.created, 'month') AS month_name,
       count(*) AS count_vacancies
from public.vacancy
group by month_name
order by count_vacancies desc;

-- самый злачный месяц по созданным резюме
select to_char(resume.created, 'Month') || (extract(year from resume.created)) AS max_month_with_resume,
       count(*) as count_resumes
from public.resume
group by max_month_with_resume
order by count_resumes desc
    limit 1;

-- все месяца по созданным резюме
select to_char(resume.created, 'month') AS month_name,
       count(*) AS count_resumes
from public.resume
group by month_name
order by count_resumes desc;

------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------

-- ЗАДАНИЕ 5
select vacancy_id, title as vacancy_title, count(respond.*) as responds_count
from public.respond
         join vacancy on respond.vacancy_id = vacancy.id
where respond.created >= vacancy.created + interval '7 day'
group by vacancy_id, title
having count(respond.*) > 5
order by responds_count desc;

------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------

-- ЗАДАНИЕ 6
-- Предполагаю, что часто ищут по
CREATE INDEX vacancy_title_idx ON vacancy (title); -- тайтлу вакансии
CREATE INDEX vacancy_employer_id_idx ON vacancy (employer_id); -- конкретному работодателю
CREATE INDEX vacancy_created_idx ON vacancy (created); -- дате создания

CREATE INDEX resume_title_idx ON resume (title); -- тайтлу вакансии
CREATE INDEX resume_specialization_idx ON resume (specialization); -- специализации
CREATE INDEX resume_applicant_id_idx ON resume (applicant_id); -- конкретному соискателю
CREATE INDEX resume_created_idx ON resume (created); -- дате создания

CREATE INDEX respond_vacancy_id_idx ON respond (vacancy_id); -- вакансии
CREATE INDEX respond_created_idx ON respond (created); -- дате создания

CREATE INDEX vacancy_specialization_specialization_id_idx ON vacancy_specialization (specialization_id); -- конкретно по специализации
