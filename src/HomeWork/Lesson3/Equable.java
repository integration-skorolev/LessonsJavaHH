package HomeWork.Lesson3;

/**
 * Добавить в классовую модель из домашнего задания 3 темы дженерик классы и дженерик интерфейсы.
 * @param <V>
 */

public interface Equable<V extends Car> {
  boolean isEqual(V v);
}
