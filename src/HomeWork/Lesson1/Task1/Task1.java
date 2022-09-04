package HomeWork.Lesson1.Task1;

import java.util.Scanner;

/**
 * 1. Напишите программу, которая принимает с клавиатуры целое трехзначное положительное число и изменяет его следующим образом:
 * • для чисел, больших 500, - переставляет местами числа единиц и сотен (вместо 672 - 276);
 * • в остальных числах переставляются местами числа десятков и единиц (вместо 363 - 336).
 * Программа должна вывести на экран новое значение переменной.
 */

public class Task1 {

  public static void main(String[] args) {

    System.out.println("Введите число от 100 до 999\r");
    Scanner scanner = new Scanner(System.in);
    int a = validateDigit(scanner);
    System.out.println(changeDigits(a));
  }

  public static int changeDigits(int a) {

    int a1 = a / 100;
    int a2 = a / 10 % 10;
    int a3 = a % 10;

    if (a > 500) {
      return a3 * 100 + a2 * 10 + a1;
    } else {
      return a1 * 100 + a3 * 10 + a2;
    }
  }

  public static int validateDigit(Scanner scanner) {
    final String i = scanner.nextLine();
    try {
      int z = Integer.parseInt(i);
      if (z < 1 || z > 999) {
        System.out.print("Введите число от 1 до 999");
      }
      return z;
    } catch (NumberFormatException ex) {
      System.out.println("Не число, Введите число от 1 до 999");
    }
    return 0;
  }
}
