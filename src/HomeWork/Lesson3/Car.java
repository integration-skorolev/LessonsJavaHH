package HomeWork.Lesson3;

/**
 * Реализуйте классовую модель автомобиля или любой другой близкой вам предметной области. Модель должна включать:
 * +++ 1. Иерархия классов (родитель и не менее 2 потомков) .
 * +++ 2. Не менее двух интерфейсов, каждый из которых реализован как минимум в одном из классов.
 * +++ 3. Как минимум один абстрактный класс
 * +++ 4. Каждый класс должен содержать не менее 2 полей
 * +++ 5. Должна быть реализация хотя бы одного параметризованного конструктора
 * +++ 6. Реализация инкапсуляции включая разграничение с модификаторами доступа
 * +++ 7. Наличие статических полей и методов
 * +++ 8. Наличие методов в каждом классе
 * +++ 9. Методы должны реализовать какую либо усложненную логику (ветвления, циклы)
 */

public abstract class Car {

  protected int weight;
  protected int capacityFuel;

  public void move() {
    System.out.println("Движение по асфальту");
  }

  public static void checkStatusCar() {
    System.out.println("Проверка автомобиля перед выездом");
  }
}

class FireTruck extends Car implements Sprayable {
  private static int amountOfWater;
  private boolean isHaveLongStaircase = false;

  static {
    amountOfWater = 1000;
  }

  public FireTruck(int amountOfWater, boolean isHaveLongStaircase) {
    this.amountOfWater = amountOfWater;
    this.isHaveLongStaircase = isHaveLongStaircase;
  }

  public FireTruck(boolean isHaveLongStaircase) {
    this.isHaveLongStaircase = isHaveLongStaircase;
  }

  public void putOutFire(FireTruck fireTruck) {
    while (fireTruck.amountOfWater > 100) {
      sprayLiquid();
    }
    if (isHaveLongStaircase) {
      savePeople();
    }
    refuelWater();
  }

  public int getAmountOfWater() {
    return amountOfWater;
  }

  public void sprayLiquid() {
    System.out.println("Распылять воду с пеной");
    amountOfWater = -100;
  }

  private void refuelWater() {
    System.out.println("Перезаправка воды");
  }

  private void savePeople() {
    System.out.println("Спасать людей по выдвижной лестнице");
  }
}

class ZombiCar extends Car implements Shootable, Armoredable, Sprayable {

  private int distance;
  private boolean isSavePeoples;

  public ZombiCar(int distance, boolean isSavePeoples, int capacityFuelTank, int weight) {
    this.distance = distance;
    this.isSavePeoples = isSavePeoples;
    this.capacityFuel = capacityFuelTank;
    this.weight = weight;
  }

  public void discoverNewCity() {
    if (isSavePeoples && distance > 100 && capacityFuel < 100 && weight > 3000) {
      System.out.println("capacityFuelTank должно быть больше 150 ");
    } else {
      System.out.println("Отправляемся в новый город");
    }
  }

  @Override
  public void shoot() {
    System.out.println("Стрелять из пушки");
  }

  @Override
  public void defend() {
    System.out.println("Находиться под броней");
  }

  @Override
  public void sprayLiquid() {
    System.out.println("Распылять напалм");
  }
}

class Amphibian extends Car implements Swimable {
  private int swimmingDistance = 100;
  private int quantityPropeller;

  @Override
  public void swim() {
    System.out.println("Плыть по воде");
  }

  public Amphibian(int quantityPropeller, int capacityFuelTank) {
    if (quantityPropeller < 3 || super.capacityFuel < 100) {
      this.swimmingDistance = swimmingDistance / 2;
    }
  }
}

interface Shootable {
  void shoot();
}

interface Swimable {
  void swim();
}

interface Armoredable {
  void defend();
}

interface Sprayable {
  void sprayLiquid();
}
