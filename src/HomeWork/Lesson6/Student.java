package HomeWork.Lesson6;

import java.util.*;

/**
 * 1. Создать класс Student, содержащий следующие характеристики – имя, группа, курс, оценки по предметам.
 * 2. Создать коллекцию, содержащую объекты класса Student.
 * 3. Написать метод, который удаляет студентов со средним баллом <3.
 * 4. Если средний балл>=3, студент переводится на следующий курс.
 * 5. Напишите метод printStudents(List<Student> students, int course), который получает список студентов и номер курса.
 * 6. А также печатает на консоль имена тех студентов из списка, которые обучаются на данном курсе.
 */

public class Student {

  private String name;
  private String group;
  private int course;
  private HashMap<String, Integer> graids;


  public Student(String name, String group, int course, HashMap<String, Integer> graids) {
    this.name = name;
    this.group = group;
    this.course = course;
    this.graids = graids;
  }

  public String getName() {
    return name;
  }

  public int getCourse() {
    return course;
  }

  public Map<String, Integer> getGraids() {
    return graids;
  }

  public static void main(String[] args) {
    Student student1 = new Student("Serg", "A1", 1, new HashMap<>(
        Map.of("English", 2, "History", 3, "Music", 2, "Geography", 2))
    );

    Student student2 = new Student("Mark", "A1", 1, new HashMap<>(
        Map.of("English", 3, "History", 3, "Music", 3, "Geography", 3))
    );

    Student student3 = new Student("Alex", "A1", 1, new HashMap<>(
        Map.of("English", 5, "History", 3, "Music", 5, "Geography", 3))
    );

    List<Student> studentsBefore = new ArrayList<>();

    studentsBefore.add(student1);
    studentsBefore.add(student2);
    studentsBefore.add(student3);
    printStudents(studentsBefore, 1);

    List<Student> studentsAfter = checkStudent(studentsBefore);

    printStudents(studentsAfter, 1);
    printStudents(studentsAfter, 2);
  }

  public static void printStudents(List<Student> students, int course) {
    ListIterator<Student> iterator = students.listIterator();
    while (iterator.hasNext()) {
      Student student = iterator.next();
      if (course == student.getCourse()) {
        System.out.println("Студент + " + course + " курса - " + student.getName());
      }
    }
  }

  public static List<Student> checkStudent(List<Student> students) {
    List<Student> studentsAfter = new ArrayList<>(students);

    students.forEach(x -> {
          double avg = x.getGraids().values().stream().mapToInt(v -> v).average().orElse(0);

          if (avg < 3) {
            studentsAfter.remove(x);
          } else {
            x.course++;
            System.out.println("Переведен на следующий курс");
          }
        }
    );
    return studentsAfter;
  }
}