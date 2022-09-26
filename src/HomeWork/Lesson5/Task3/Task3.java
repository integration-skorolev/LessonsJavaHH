package HomeWork.Lesson5.Task3;

/**
 * писать функциональный интерфейс с методом, который принимает три дробных числа:
 * a, b, c и возвращает тоже дробное число.
 * Написать реализацию такого интерфейса в виде лямбда-выражения, которое возвращает дискриминант.
 * Кто забыл, D = b^2 — 4ac.
 */

public class Task3 {
  public static void main(String[] args) {
    DiscriminantСalculationable checkableDiscriminant = (a, b, c) -> Math.pow(b, 2) - 4 * a * c;
    System.out.println(checkableDiscriminant.calculateDiscriminant(5, 7, 13));
  }
}
