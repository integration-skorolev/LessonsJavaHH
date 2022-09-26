package HomeWork.Lesson3;

class Amphibian extends Car implements Swimable, Equable<Car> {
  private int swimmingDistance = 100;
  private int quantityPropeller;


  @Override
  public void swim() {
    System.out.println("Плыть по воде");
  }

  public Amphibian(int quantityPropeller, int capacityFuelTank, int weight) {
    if (quantityPropeller < 3 || super.capacityFuel < 100) {
      this.swimmingDistance = swimmingDistance / 2;
    }
    this.weight = weight;
  }

  @Override
  public void move() {
    System.out.println("Двигаться по асфальту");
    System.out.println("Двигаться по воде");
  }

  @Override
  public boolean isEqual(Car car) {
    return this.weight == car.weight;
  }
}

