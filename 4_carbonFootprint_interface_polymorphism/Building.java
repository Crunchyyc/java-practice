package CarbonFootprint409110035;

// 定義 Building 類，表示建築物，無繼承關係
public class Building implements CarbonFootprint {
    // 定義建築物屬性：年度用電量（千瓦時）、建築名稱、建築面積（平方米）
    private double annualElectricityUsage;
    private String buildingName;
    private double buildingArea;

    // 定義間接排放係數：每千瓦時電量 0.5 公斤 CO2，每平方米每年 10 公斤 CO2（製造建材）
    private static final double ELECTRICITY_FOOTPRINT = 0.5;
    private static final double AREA_FOOTPRINT = 10.0;

    // 建構子，初始化建築物屬性
    public Building(String name, double electricityUsage, double area) {
        this.buildingName = name;
        this.annualElectricityUsage = electricityUsage;
        this.buildingArea = area;
    }

    // 實現介面的 getCarbonFootprint 方法，計算直接（用電）+ 間接（製造）碳足跡
    @Override
    public double getCarbonFootprint() {
        double directFootprint = annualElectricityUsage * ELECTRICITY_FOOTPRINT; // 用電排放
        double indirectFootprint = buildingArea * AREA_FOOTPRINT; // 製造建材排放
        return directFootprint + indirectFootprint; // 總碳足跡（公斤 CO2/年）
    }

    // 提供一個方法返回建築物的描述資訊
    public String getDescription() {
        return "Building: " + buildingName + " (Electricity Usage: " + annualElectricityUsage + 
               " kWh/year, Area: " + buildingArea + " m²)";
    }
}