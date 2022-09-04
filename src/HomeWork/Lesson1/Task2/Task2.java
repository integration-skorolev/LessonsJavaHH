package HomeWork.Lesson1.Task2;

/**
 * 2. Напишите программу, которая выводит на экран все трехзначные числа, для которых соблюдаются два условия:
 * • само число заканчивается на 0;
 * • сумма нечетных делителей этого числа тоже заканчивается на 0.
 */

public class Task2 {

  public static void main(String[] args) {

    for (int i = 100; i < 1000; i++) {

      int sumDel = 0;

      for (int j = 2; j <= i / 2; j++) {

        if (j % 2 == 0) {
          continue;
        }

        if (i % j != 0) {
          sumDel += j;
        }
      }

      if (sumDel % 10 == 0 && i % 10 == 0) {
        System.out.println(i);
      }
    }
  }
}

