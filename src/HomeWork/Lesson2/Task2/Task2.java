package HomeWork.Lesson2.Task2;

/**
 * 2. Дано натуральное число N. Напишите метод выводящий все цифры числа по одной, в обычном порядке, разделяя их пробелами или новыми строками.
 * При решении этой задачи нельзя использовать строки, списки, массивы, циклы. Разрешена только рекурсия и целочисленная арифметика.
 */

public class Task2 {

  public static void main(String[] args) {
    printNumber(1678);
  }

  private static void printNumber(int number) {
    if (number >= 10) printNumber(number / 10);
    System.out.print(number % 10 + " ");
  }
}
