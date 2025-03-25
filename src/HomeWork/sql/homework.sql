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
    specialization integer[] not null,
    area_id integer not null,
    compensation_from integer default 0,
    compensation_to integer default 0,
    created timestamp not null,
    constraint respond_areas_id_fk
        foreign key (area_id) references public.areas (id)
);

create table public.resume
(
    id serial primary key,
    title varchar(120),
    applicant_id integer not null,
    specialization integer[] not null,
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
insert into specialization(title)
values ('Дворник'), ('Художник'), ('Строитель'), ('Менеджер'), ('Продавец'), ('Охранник'), ('Военный') ,('Писатель'), ('Журналист'), ('Певец');

insert into areas(title)
values ('Москва'), ('Санкт-Петербург'), ('Екатеринбург'), ('Самара'), ('Вологда'), ('Новосибирск'), ('Ростов-на-Дону') ,('Тюмень'), ('Красноярск'), ('Омск');

CREATE OR REPLACE FUNCTION random_array(min_val int, max_val int, array_length int)
    RETURNS int[] AS $$
DECLARE
result int[];
BEGIN
SELECT array_agg(num)
INTO result
FROM (
         SELECT (floor(random() * (max_val - min_val + 1))::int + min_val) AS num
         FROM generate_series(1, array_length)
     ) AS random_numbers;
RETURN result;
END;
$$ LANGUAGE plpgsql;

with
    test_data_for_vacancy (id, title, employer_id, specialization, area_id, compensation_from , compensation_to, created) as
        (select
             generate_series(1, 10000),
             CONCAT('Вакансия от компании - ', md5(random()::text)),
             random(1, 10),
             random_array(1,10, 3),
             random(1, 10),
             random(1000, 10000),
             random(20000, 100000),
             timestamp '2022-01-01' + INTERVAL '1 day' * round(random() * 730)
    )

INSERT INTO vacancy (id, title, employer_id, specialization, area_id, compensation_from , compensation_to, created)
SELECT *
FROM test_data_for_vacancy;

with
    test_data_for_resume (id, title, applicant_id, specialization, area_id, compensation, created) as
        (select
             generate_series(1, 100000),
             CONCAT('Резюме от соискателя - ', md5(random()::text)),
             random(1, 10),
             random_array(1,10, 3),
             random(1, 10),
             random(1000, 10000),
             timestamp '2022-01-01' + INTERVAL '1 day' * round(random() * 730)
    )

INSERT INTO public.resume (id, title, applicant_id, specialization, area_id, compensation, created)
SELECT *
FROM test_data_for_resume;

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
select DATE_TRUNC('month', vacancy.created) AS max_month_with_vacancy,
       count(*) as count_vacancies
from public.vacancy
group by max_month_with_vacancy
order by count_vacancies desc
    limit 1;

select DATE_TRUNC('month', resume.created) AS max_month_with_resume,
       count(*) as count_resumes
from public.resume
group by max_month_with_resume
order by count_resumes desc
    limit 1;

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
CREATE INDEX vacancy_specialization_idx_array_gin ON vacancy using gin(specialization); -- специализации
CREATE INDEX employer_id_idx ON vacancy (employer_id); -- конкретному работодателю
CREATE INDEX vacancy_created_idx ON vacancy (created); -- дате создания

CREATE INDEX resume_title_idx ON resume (title); -- тайтлу вакансии
CREATE INDEX resume_specialization_idx_array_gin ON resume using gin (specialization); -- специализации
CREATE INDEX applicant_id_idx ON resume (applicant_id); -- конкретному соискателю
CREATE INDEX resume_created_idx ON resume (created); -- дате создания

CREATE INDEX vacancy_id_idx ON respond (vacancy_id); -- вакансии
CREATE INDEX respond_created_idx ON respond (created); -- дате создания