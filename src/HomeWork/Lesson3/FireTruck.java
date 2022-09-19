package HomeWork.Lesson3;

class FireTruck extends Car implements Sprayable {
  private int amountOfWater;
  private boolean isHaveLongStaircase = false;

  private FireTruck(FireTruckBuilder fireTruckBuilder) {
    this.amountOfWater = fireTruckBuilder.amountOfWater;
    this.isHaveLongStaircase = fireTruckBuilder.isHaveLongStaircase;
  }

  public static class FireTruckBuilder {
    private int amountOfWater;
    private boolean isHaveLongStaircase = false;

    public FireTruckBuilder() {
    }

    public FireTruckBuilder setAmountOfWater(int amountOfWater) {
      this.amountOfWater = amountOfWater;
      return this;
    }

    public FireTruckBuilder setIsHaveLongStaircase(boolean isHaveLongStaircase) {
      this.isHaveLongStaircase = isHaveLongStaircase;
      return this;
    }

    public FireTruck build() {
      return new FireTruck(this);
    }
  }


  public void putOutFire(FireTruck fireTruck) {
    while (amountOfWater > 100) {
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

  @Override
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

  @Override
  public void move() {
    System.out.println("Двигаться по асфальту");
    System.out.println("Двигаться по бездорожью");
  }
}
