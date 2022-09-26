package HomeWork.Lesson3;

class Amphibian extends Car implements Swimable {
  private int swimmingDistance = 100;
  private int quantityPropeller;

  private Amphibian(AmphibianBuilder builder) {
    this.quantityPropeller = builder.quantityPropeller;
    super.capacityFuel = builder.capacityFuel;
  }

  public Amphibian setSwimmingDistance(int swimmingDistance) {
    this.swimmingDistance = swimmingDistance;
    return this;
  }

  public Amphibian setQuantityPropeller(int quantityPropeller) {
    this.quantityPropeller = quantityPropeller;
    return this;
  }

  public static class AmphibianBuilder {
    private int swimmingDistance = 100;
    private int quantityPropeller;
    private int capacityFuel;

    public AmphibianBuilder() {
    }

    public AmphibianBuilder setSwimmingDistance(int swimmingDistance) {
      this.swimmingDistance = swimmingDistance;
      return this;
    }

    public AmphibianBuilder setQuantityPropeller(int quantityPropeller) {
      this.quantityPropeller = quantityPropeller;
      return this;
    }

    public AmphibianBuilder setCapacityFuel(int capacityFuel) {
      this.capacityFuel = capacityFuel;
      return this;
    }

    public Amphibian build() {
      if (quantityPropeller < 3 || capacityFuel < 100) {
        return new Amphibian(this).setSwimmingDistance(swimmingDistance / 2);
      }
      return new Amphibian(this);
    }
  }

  @Override
  public void swim() {
    System.out.println("Плыть по воде");
  }

  @Override
  public void move() {
    System.out.println("Двигаться по асфальту");
    System.out.println("Двигаться по воде");
  }
}

