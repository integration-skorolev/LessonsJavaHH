package HomeWork.Lesson3;

/**
 * Добавить в классовую модель из домашнего задания 3 темы дженерик классы и дженерик интерфейсы.
 * @param <T>
 */

public class PrintMove<T extends Car> extends Car {

  T t;

  public PrintMove(T t) {
    this.t = t;
  }

  @Override
  public void move() {
  }

  public void print() {
    System.out.println("У этого класса - " + t.getClass().getName() + " - выглядит так: \r");
    t.move();
  }
}
