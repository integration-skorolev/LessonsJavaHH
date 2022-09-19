package HomeWork.Lesson3;

class ZombiCar extends Car implements Shootable, Armoredable, Sprayable {

  private int distance;
  private boolean isSavePeoples;

  private ZombiCar(ZombiCarBuilder zombiCarBuilder) {
    this.distance = zombiCarBuilder.distance;
    this.isSavePeoples = zombiCarBuilder.isSavePeoples;
    this.capacityFuel = zombiCarBuilder.capacityFuel;
    this.weight = zombiCarBuilder.weight;
  }

  public static class ZombiCarBuilder {
    private int distance;
    private boolean isSavePeoples;

    private int weight;
    private int capacityFuel;

    public ZombiCarBuilder() {
    }

    public ZombiCarBuilder setDistance(int distance) {
      this.distance = distance;
      return this;
    }

    public ZombiCarBuilder setIsSavePeoples(boolean isSavePeoples) {
      this.isSavePeoples = isSavePeoples;
      return this;
    }

    public ZombiCarBuilder setWeight(int weight) {
      this.weight = weight;
      return this;
    }

    public ZombiCarBuilder setCapacityFuel(int capacityFuel) {
      this.capacityFuel = capacityFuel;
      return this;
    }

    public ZombiCar build() {
      return new ZombiCar(this);
    }
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
