package CarbonFootprint409110035;

// 定義 Bicycle 類，表示自行車，無繼承關係
public class Bicycle implements CarbonFootprint {
    // 定義自行車屬性：年度騎行距離（公里）、自行車類型
    private double annualDistance;
    private String bikeType;

    // 定義間接排放係數：每公里 0.0205 公斤 CO2（製造 + 食物消耗）
    private static final double DISTANCE_FOOTPRINT = 0.0205;

    // 建構子，初始化自行車屬性
    public Bicycle(String type, double distance) {
        this.bikeType = type;
        this.annualDistance = distance;
    }

    // 實現介面的 getCarbonFootprint 方法，計算間接碳足跡（製造 + 食物）
    @Override
    public double getCarbonFootprint() {
        return annualDistance * DISTANCE_FOOTPRINT; // 總碳足跡（公斤 CO2/年）
    }

    // 提供一個方法返回自行車的描述資訊
    public String getDescription() {
        return "Bicycle: " + bikeType + " (Distance per year: " + annualDistance + " km)";
    }
}