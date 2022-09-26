package HomeWork.Lesson5.Task2;

/**
 * Написать функциональный интерфейс с методом, который принимает число и возвращает булево значение.
 * Написать реализацию такого интерфейса в виде лямбда-выражения,
 * которое возвращает true если переданное число делится без остатка на 13.
 */

public class Task2 {
  public static void main(String[] args) {
    WithoutRemainderDivideable checkable = (x) -> x % 13 == 0;
    System.out.println(checkable.check(26));
  }
}
