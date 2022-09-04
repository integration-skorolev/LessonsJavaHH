package HomeWork.Lesson2.Task1;

/**
 * 1. Напишите метод, который получает следующие параметры: координаты центра круга, радиус круга и координаты некой точки.
 * Метод должен проверить, находится ли данная точка внутри круга.
 */

public class Task1 {

  public static void main(String[] args) {
    System.out.println(isPresent(3, -3, 4, 0, -5));
  }

  private static boolean isPresent(int xc, int yc, int r, int x, int y) {
    if (Math.pow((x - xc), 2) + Math.pow((y - yc), 2) <= Math.pow(r, 2)) {
      return true;
    }
    return false;
  }
}
