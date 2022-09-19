package HomeWork.Lesson7;

import java.util.*;

/**
 * Реализовать в объектной модели дз №6 как минимум 2 паттерна (например, фабрику и билдер)
 * Здесь билдер
 */

public class Student {

  private String name;
  private String group;
  private int course;
  private HashMap<String, Integer> graids;

  private Student(Builder builder) {
    this.name = builder.name;
    this.group = builder.group;
    this.course = builder.course;
    this.graids = builder.graids;
  }

  public String getName() {
    return name;
  }

  public int getCourse() {
    return course;
  }

  public String getGroup() {
    return group;
  }

  public Map<String, Integer> getGraids() {
    return graids;
  }

  public static class Builder {
    private String name;
    private String group;
    private int course;
    private HashMap<String, Integer> graids;

    public Builder() {
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setGroup(String group) {
      this.group = group;
      return this;
    }

    public Builder setCourse(int course) {
      this.course = course;
      return this;
    }

    public Builder setGraids(HashMap<String, Integer> graids) {
      this.graids = graids;
      return this;
    }

    public Student build() {
      return new Student(this);
    }
  }


  public static void main(String[] args) {
    Student student1 = new Student.Builder()
        .setName("Serg")
        .setGroup("A1")
        .setCourse(1)
        .setGraids(new HashMap<>(Map.of("English", 2, "History", 3, "Music", 2, "Geography", 2)))
        .build();

    Student student2 = new Student.Builder()
        .setName("Mark")
        .setGroup("A1")
        .setCourse(1)
        .setGraids(new HashMap<>(Map.of("English", 3, "History", 3, "Music", 3, "Geography", 3)))
        .build();

    Student student3 = new Student.Builder()
        .setName("Alex")
        .setGroup("A1")
        .setCourse(1)
        .setGraids(new HashMap<>(Map.of("English", 5, "History", 3, "Music", 5, "Geography", 3)))
        .build();

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
