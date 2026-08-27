package CarbonFootprint409110035;

import java.util.ArrayList;

// 主類，用於測試碳足跡計算和多態行為
public class CarbonFootprintTest {
    public static void main(String[] args) {
        // 創建一個 ArrayList 來儲存 CarbonFootprint 物件
        ArrayList<CarbonFootprint> carbonObjects = new ArrayList<>();

        // 創建不同類型的物件並加入 ArrayList
        carbonObjects.add(new Building("Office Tower", 100000, 5000)); // 辦公大樓，10萬千瓦時，5000 m²
        carbonObjects.add(new Car("Sedan", 20000, 12, 1500)); // 轎車，2萬公里，12 km/L，1500 kg
        carbonObjects.add(new Bicycle("Mountain Bike", 400)); // 山地自行車，400 公里/年

        // 遍歷 ArrayList，通過多態調用 getCarbonFootprint 方法
        for (CarbonFootprint obj : carbonObjects) {
            // 獲取物件的描述（需要類型轉換，因為介面無 getDescription 方法）
            String description;
            if (obj instanceof Building) {
                description = ((Building) obj).getDescription();
            } else if (obj instanceof Car) {
                description = ((Car) obj).getDescription();
            } else {
                description = ((Bicycle) obj).getDescription();
            }

            // 輸出物件描述和碳足跡
            System.out.println(description);
            System.out.printf("Carbon Footprint: %.2f kg CO2/year%n", obj.getCarbonFootprint());
            System.out.println(); // 空行分隔
        }
    }
}