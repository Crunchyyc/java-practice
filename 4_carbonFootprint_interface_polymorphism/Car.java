package CarbonFootprint409110035;

// 定義 Car 類，表示汽車，無繼承關係
public class Car implements CarbonFootprint {
    // 定義汽車屬性：年度行駛里程（公里）、燃油效率（公里/公升）、車型、車重（公斤）
    private double annualMileage;
    private double fuelEfficiency;
    private String carModel;
    private double carWeight;

    // 定義間接排放係數：每公升汽油 2.3 公斤 CO2，每公斤車重每年 0.5 公斤 CO2（製造）
    private static final double FUEL_FOOTPRINT = 2.3;
    private static final double WEIGHT_FOOTPRINT = 0.5;

    // 建構子，初始化汽車屬性
    public Car(String model, double mileage, double efficiency, double weight) {
        this.carModel = model;
        this.annualMileage = mileage;
        this.fuelEfficiency = efficiency;
        this.carWeight = weight;
    }

    // 實現介面的 getCarbonFootprint 方法，計算直接（燃油）+ 間接（製造）碳足跡
    @Override
    public double getCarbonFootprint() {
        double fuelConsumption = annualMileage / fuelEfficiency; // 計算年度油耗
        double directFootprint = fuelConsumption * FUEL_FOOTPRINT; // 燃油排放
        double indirectFootprint = carWeight * WEIGHT_FOOTPRINT; // 製造排放
        return directFootprint + indirectFootprint; // 總碳足跡（公斤 CO2/年）
    }

    // 提供一個方法返回汽車的描述資訊
    public String getDescription() {
        return "Car: " + carModel + " (Mileage: " + annualMileage + " km/year, Efficiency: " + 
               fuelEfficiency + " km/L, Weight: " + carWeight + " kg)";
    }
}