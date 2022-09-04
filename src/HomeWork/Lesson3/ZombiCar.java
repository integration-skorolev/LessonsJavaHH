package HomeWork.Lesson3;

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

  @Override
  public void move() {
    System.out.println("Двигаться по асфальту");
    System.out.println("Двигаться по бездорожью");
  }
}
