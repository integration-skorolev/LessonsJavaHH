package HomeWork.Lesson3;

public class Fabric {


  private static Fabric instance;

  public static Fabric getInstance() {
    if (instance == null) {
      instance = new Fabric();
    }
    return instance;
  }

  private Fabric() {
  }

  public Car createCar(String name) {
    return switch (name) {
      case "ZombiCar" -> new ZombiCar.ZombiCarBuilder().build();
      case "FireTruck" -> new FireTruck.FireTruckBuilder().build();
      case "Amphibian" -> new Amphibian.AmphibianBuilder().build();
      default -> null;
    };
  }
}

