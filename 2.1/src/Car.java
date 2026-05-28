public class Car {
    private String model;
    private double fuelLevel;
    private double mileage;

    public Car(String model, double initialFuel) {
        this.model = model;
        this.fuelLevel = initialFuel;
        this.mileage = 0;
    }

    public boolean drive(double distance) {
        double fuelNeeded = distance / 10; // расход 10 км/литр
        if (fuelLevel >= fuelNeeded) {
            fuelLevel -= fuelNeeded;
            mileage += distance;
            return true;
        }
        return false;
    }

    public void refuel(double amount) {
        if (amount > 0) {
            fuelLevel += amount;
        }
    }

    public double getFuelLevel() { return fuelLevel; }
    public double getMileage() { return mileage; }
}