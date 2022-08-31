package HomeWork.Lesson3;

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
