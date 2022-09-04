package HomeWork.Lesson1.Task4;

import java.util.Arrays;

/**
 * 4. Напишите программу, которая формирует два массива (не обязательно одинакового размера) целых чисел.
 * Программа возвращает массив значений, которые присутствуют в первом массиве, но не присутствуют во втором.
 * *Программа возвращает двумерный массив значений. В первом кортеже массив значений, которые присутствуют в первом массиве,
 * но не присутствуют во втором. Во втором кортеже массив значений, которые присутствуют во втором массиве, но не присутствуют в первом.
 */

public class Task4 {

  public static void main(String[] args) {

    int[] a = new int[50];
    int[] b = new int[30];
    int[][] c = new int[2][50];

    fillArray(a);
    fillArray(b);

    printArray(a);
    printArray(b);

    fillUniqueArrayA(a, b, c);
    fillUniqueArrayB(a, b, c);

    printMultiArray(c);
  }

  private static void printMultiArray(int[][] c) {
    for (int i = 0; i < c.length; i++) {
      System.out.println("Кортеж № " + i + " = " + Arrays.toString(c[i]));
    }
  }

  public static void fillArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * 100);
    }
  }

  public static void printArray(int[] arr) {
    System.out.println("Массив = " + Arrays.toString(arr));
  }

  public static void fillUniqueArrayA(int[] a, int[] b, int[][] c) {
    int v = 0;
    for (int i = 0; i < a.length; i++) {
      int z = a[i];
      boolean isPresent = false;

      for (int j = 0; j < b.length; j++) {
        if (z == b[j]) {
          isPresent = true;
        }
      }

      if (!isPresent) {
        c[0][v] = z;
        v++;
      }

    }
    c[0] = Arrays.copyOf(c[0], v);
  }

  public static void fillUniqueArrayB(int[] a, int[] b, int[][] c) {
    int w = 0;
    for (int i = 0; i < b.length; i++) {
      int z = b[i];
      boolean isPresent = false;

      for (int j = 0; j < a.length; j++) {
        if (z == a[j]) {
          isPresent = true;
        }
      }

      if (!isPresent) {
        c[1][w] = z;
        w++;
      }

    }
    c[1] = Arrays.copyOf(c[1], w);
  }
}
