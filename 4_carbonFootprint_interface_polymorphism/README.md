# Carbon Footprint

## What I Implemented

- Defined the `CarbonFootprint` interface with a shared `getCarbonFootprint()` method.
- Implemented the interface in `Building`, `Car`, and `Bicycle` classes.
- Used polymorphism to store different objects in an `ArrayList<CarbonFootprint>`.
- Implemented a separate carbon-footprint formula for each type of object.
- Added description methods to display the data stored by each object.
- Created `CarbonFootprintTest` to construct example objects and print their results.
- Used `instanceof` and type casting to retrieve each object's description.
- Displayed every carbon footprint in kilograms of CO2 per year.

## Overview

This project is a Java implementation of the CarbonFootprint interface and polymorphism exercise. It models three different objects that produce carbon emissions: a building, a car, and a bicycle.

Although the three classes use different data and calculation formulas, they all implement the same `CarbonFootprint` interface. This allows the test program to store and process them through one common interface type.

The project demonstrates interfaces, implementation, polymorphism, encapsulation, collections, loops, type checking, and formula-based calculations.

---

## Technical Details

### Interface Definition

The `CarbonFootprint` interface defines the common behavior required by every carbon-emitting object:

```java
package CarbonFootprint409110035;

public interface CarbonFootprint {
    double getCarbonFootprint();
}
```

Each implementing class provides its own version of `getCarbonFootprint()`.

### Building Calculation

A `Building` stores its name, annual electricity usage, and building area.

The calculation uses these factors:

- Electricity: `0.5 kg CO2` per kWh
- Building area: `10.0 kg CO2` per square meter per year

Formula:

```text
carbon footprint = annual electricity usage × 0.5
                 + building area × 10.0
```

For the test object `Office Tower`, the calculation is:

```text
100000 × 0.5 + 5000 × 10.0 = 100000.00 kg CO2/year
```

### Car Calculation

A `Car` stores its model, annual mileage, fuel efficiency, and weight.

The calculation uses these factors:

- Fuel: `2.3 kg CO2` per liter
- Vehicle manufacturing: `0.5 kg CO2` per kilogram of vehicle weight per year

Formula:

```text
fuel consumption = annual mileage ÷ fuel efficiency
carbon footprint = fuel consumption × 2.3
                 + car weight × 0.5
```

For the test object `Sedan`, the result is approximately:

```text
(20000 ÷ 12) × 2.3 + 1500 × 0.5 = 4583.33 kg CO2/year
```

### Bicycle Calculation

A `Bicycle` stores its type and annual riding distance.

The project uses a factor of `0.0205 kg CO2` per kilometer:

```text
carbon footprint = annual distance × 0.0205
```

For the test object `Mountain Bike`, the result is:

```text
400 × 0.0205 = 8.20 kg CO2/year
```

### Polymorphism

The test program stores all three object types in one collection:

```java
ArrayList<CarbonFootprint> carbonObjects = new ArrayList<>();

carbonObjects.add(new Building("Office Tower", 100000, 5000));
carbonObjects.add(new Car("Sedan", 20000, 12, 1500));
carbonObjects.add(new Bicycle("Mountain Bike", 400));
```

The objects can then be processed through the interface:

```java
for (CarbonFootprint obj : carbonObjects) {
    System.out.println(obj.getCarbonFootprint());
}
```

The method call is polymorphic: Java invokes the implementation belonging to the actual object, whether it is a `Building`, `Car`, or `Bicycle`.

### Description Output

The `CarbonFootprint` interface only defines `getCarbonFootprint()`, so it does not define a common description method. `CarbonFootprintTest` uses `instanceof` and type casting to call the correct `getDescription()` method for each class.

---

## Environment Requirements

- Java Development Kit (JDK)
- Java compiler (`javac`)
- Java runtime (`java`)
- Terminal or an IDE such as VS Code, IntelliJ IDEA, or Eclipse
- `make` is optional when using the provided Makefile

The project uses Java standard libraries only. No external dependencies are required.

---

## Build & Run

### Using the Makefile

The source files declare the package `CarbonFootprint409110035`. From the project directory, run:

```bash
make
```

The Makefile compiles all source files into the package directory and runs:

```text
CarbonFootprint409110035.CarbonFootprintTest
```

To compile only:

```bash
make compile
```

To run after compiling:

```bash
make run
```

To remove the generated class files:

```bash
make clean
```

### Using Java Commands

Compile all source files:

```bash
javac -d . CarbonFootprint.java CarbonFootprintTest.java Car.java Building.java Bicycle.java
```

Run the main class by specifying its package name:

```bash
java -cp . CarbonFootprint409110035.CarbonFootprintTest
```

The commands must be run from the directory containing the five `.java` files.

---

## Usage

After launching the program, `CarbonFootprintTest` performs the following steps:

1. Creates an `ArrayList<CarbonFootprint>`.
2. Adds one `Building`, one `Car`, and one `Bicycle` object.
3. Loops through the collection using the interface type.
4. Displays the description of each object.
5. Calls `getCarbonFootprint()` polymorphically.
6. Prints the result with two decimal places.

The program does not request user input. The sample values are defined in `CarbonFootprintTest.main()`.

---

## Example

The test program creates these objects:

```java
new Building("Office Tower", 100000, 5000)
new Car("Sedan", 20000, 12, 1500)
new Bicycle("Mountain Bike", 400)
```

Example output:

```text
Building: Office Tower (Electricity Usage: 100000.0 kWh/year, Area: 5000.0 m²)
Carbon Footprint: 100000.00 kg CO2/year

Car: Sedan (Mileage: 20000.0 km/year, Efficiency: 12.0 km/L, Weight: 1500.0 kg)
Carbon Footprint: 4583.33 kg CO2/year

Bicycle: Mountain Bike (Distance per year: 400.0 km)
Carbon Footprint: 8.20 kg CO2/year
```

---

## Error Handling and Edge Cases

The current implementation is a fixed-data demonstration and does not include explicit input validation. Important considerations include:

- `Car.getCarbonFootprint()` requires a non-zero fuel efficiency to avoid division by zero.
- Negative electricity usage, area, mileage, weight, or distance values are not rejected by the current classes.
- Empty descriptions or names are not validated.
- The test program uses `instanceof` because `getDescription()` is not part of the interface.
- The program calculates each object's result independently and does not calculate a combined total.

Future improvements could add constructor validation, a shared description method to the interface, and a total carbon-footprint calculation.

---

### Main Components

| Component | Responsibility |
|---|---|
| `CarbonFootprint` | Defines the shared `getCarbonFootprint()` method |
| `Building` | Calculates emissions from electricity usage and building area |
| `Car` | Calculates emissions from fuel consumption and vehicle weight |
| `Bicycle` | Calculates emissions from annual riding distance |
| `CarbonFootprintTest` | Creates objects, demonstrates polymorphism, and prints results |
| `Makefile` | Compiles and runs the packaged Java program |

---

## Related Information

- **Exercise:** 10.19 — CarbonFootprint Interface: Polymorphism
- **Book:** *Java How to Program* — Deitel
- **Language:** Java
- **Package:** `CarbonFootprint409110035`
- **Programming concepts:** Classes, interfaces, inheritance of behavior, polymorphism, encapsulation, collections, loops, methods, `instanceof`, and type casting
- **External dependencies:** None

### Implementation Notes

The main purpose of this project is to demonstrate that different classes can share one interface while keeping their own calculation logic. `CarbonFootprintTest` can process all three object types through `CarbonFootprint`, which is the central example of polymorphism in this project.

---

# 碳足跡計算

## 實作內容

- 定義 `CarbonFootprint` 介面，提供共通的 `getCarbonFootprint()` 方法。
- 建立 `Building`、`Car` 與 `Bicycle` 三個實作類別。
- 使用多型，將不同類型的物件儲存在同一個 `ArrayList<CarbonFootprint>` 中。
- 為每一種物件實作不同的碳足跡計算公式。
- 加入描述方法，顯示每個物件所儲存的資料。
- 建立 `CarbonFootprintTest` 測試類別，建立範例物件並輸出結果。
- 使用 `instanceof` 與型別轉換取得各物件的描述文字。
- 將所有碳足跡結果以每年公斤 CO2 顯示。

## 專案簡介

本專案是使用 Java 實作 CarbonFootprint 介面與多型的練習。程式建立三種不同的碳排放來源：建築物、汽車與自行車。

雖然三個類別使用的資料與計算公式不同，但都實作相同的 `CarbonFootprint` 介面。因此，測試程式可以使用同一種介面型別儲存並處理它們。

這個專案展示介面、實作、多型、封裝、集合、迴圈、型別判斷，以及依照公式進行計算等物件導向概念。

---

## 技術細節

### 介面定義

`CarbonFootprint` 介面規定所有碳排放物件都必須提供以下方法：

```java
package CarbonFootprint409110035;

public interface CarbonFootprint {
    double getCarbonFootprint();
}
```

每個實作類別都會提供自己的 `getCarbonFootprint()` 計算方式。

### Building 計算方式

`Building` 儲存建築物名稱、年度用電量與建築面積。

使用的排放係數如下：

- 用電量：每 kWh 為 `0.5 kg CO2`
- 建築面積：每平方公尺每年為 `10.0 kg CO2`

計算公式：

```text
碳足跡 = 年度用電量 × 0.5 + 建築面積 × 10.0
```

測試資料 `Office Tower` 的結果為：

```text
100000 × 0.5 + 5000 × 10.0 = 100000.00 kg CO2/year
```

### Car 計算方式

`Car` 儲存車型、年度行駛里程、燃油效率與車重。

使用的排放係數如下：

- 燃油：每公升為 `2.3 kg CO2`
- 車輛製造：每公斤車重每年為 `0.5 kg CO2`

計算公式：

```text
燃油消耗量 = 年度行駛里程 ÷ 燃油效率
碳足跡 = 燃油消耗量 × 2.3 + 車重 × 0.5
```

測試資料 `Sedan` 的結果約為：

```text
(20000 ÷ 12) × 2.3 + 1500 × 0.5 = 4583.33 kg CO2/year
```

### Bicycle 計算方式

`Bicycle` 儲存自行車類型與年度騎乘距離。

專案使用每公里 `0.0205 kg CO2` 的排放係數：

```text
碳足跡 = 年度騎乘距離 × 0.0205
```

測試資料 `Mountain Bike` 的結果為：

```text
400 × 0.0205 = 8.20 kg CO2/year
```

### 多型

測試程式將三種不同類型的物件放入同一個集合：

```java
ArrayList<CarbonFootprint> carbonObjects = new ArrayList<>();

carbonObjects.add(new Building("Office Tower", 100000, 5000));
carbonObjects.add(new Car("Sedan", 20000, 12, 1500));
carbonObjects.add(new Bicycle("Mountain Bike", 400));
```

之後可以透過介面統一處理：

```java
for (CarbonFootprint obj : carbonObjects) {
    System.out.println(obj.getCarbonFootprint());
}
```

這個方法呼叫具有多型特性：Java 會根據物件實際的類別，呼叫 `Building`、`Car` 或 `Bicycle` 中相對應的實作方法。

### 描述文字輸出

`CarbonFootprint` 介面只定義 `getCarbonFootprint()`，沒有定義共通的描述方法。因此 `CarbonFootprintTest` 使用 `instanceof` 與型別轉換，分別呼叫各類別的 `getDescription()`。

---

## 環境需求

- Java Development Kit（JDK）
- Java 編譯器（`javac`）
- Java Runtime（`java`）
- Terminal 或 VS Code、IntelliJ IDEA、Eclipse 等 IDE
- 如果使用 Makefile，則需要 `make`

本專案只使用 Java 標準函式庫，不需要額外安裝第三方套件。

---

## Build & Run

### 使用 Makefile

由於所有原始碼都宣告了 `CarbonFootprint409110035` package，請在專案資料夾執行：

```bash
make
```

Makefile 會編譯所有原始碼，並執行以下主類別：

```text
CarbonFootprint409110035.CarbonFootprintTest
```

只進行編譯：

```bash
make compile
```

編譯後執行：

```bash
make run
```

清除產生的 class 檔案：

```bash
make clean
```

### 使用 Java 指令

編譯所有原始碼：

```bash
javac -d . CarbonFootprint.java CarbonFootprintTest.java Car.java Building.java Bicycle.java
```

執行主類別時，必須指定完整 package 名稱：

```bash
java -cp . CarbonFootprint409110035.CarbonFootprintTest
```

以上指令必須在包含五個 `.java` 檔案的資料夾中執行。

---

## 使用方式

執行程式後，`CarbonFootprintTest` 會依序完成以下工作：

1. 建立 `ArrayList<CarbonFootprint>`。
2. 加入一個 `Building`、一個 `Car` 與一個 `Bicycle` 物件。
3. 使用介面型別遍歷集合。
4. 顯示每個物件的描述。
5. 以多型方式呼叫 `getCarbonFootprint()`。
6. 將結果格式化為小數點後兩位。

本程式不會要求使用者輸入，測試資料直接寫在 `CarbonFootprintTest.main()` 中。

---

## 範例

測試程式建立以下物件：

```java
new Building("Office Tower", 100000, 5000)
new Car("Sedan", 20000, 12, 1500)
new Bicycle("Mountain Bike", 400)
```

範例輸出：

```text
Building: Office Tower (Electricity Usage: 100000.0 kWh/year, Area: 5000.0 m²)
Carbon Footprint: 100000.00 kg CO2/year

Car: Sedan (Mileage: 20000.0 km/year, Efficiency: 12.0 km/L, Weight: 1500.0 kg)
Carbon Footprint: 4583.33 kg CO2/year

Bicycle: Mountain Bike (Distance per year: 400.0 km)
Carbon Footprint: 8.20 kg CO2/year
```

---

## 錯誤處理與邊界情況

目前的實作是使用固定資料的示範程式，沒有加入完整的輸入驗證。需要注意以下情況：

- `Car.getCarbonFootprint()` 的燃油效率不能為零，否則可能發生除以零的問題。
- 目前不會拒絕負數的用電量、面積、里程、車重或騎乘距離。
- 目前不會檢查名稱或描述是否為空。
- 因為介面沒有定義 `getDescription()`，測試程式需要使用 `instanceof`。
- 程式會分別計算各物件結果，沒有計算全部物件的總碳足跡。

未來可以加入建構子的參數驗證、在介面中加入共通描述方法，以及計算所有物件的總碳足跡。

---

### 主要元件

| 元件 | 功能 |
|---|---|
| `CarbonFootprint` | 定義共通的 `getCarbonFootprint()` 方法 |
| `Building` | 計算用電量與建築面積造成的碳足跡 |
| `Car` | 計算燃油消耗與車重造成的碳足跡 |
| `Bicycle` | 計算年度騎乘距離造成的碳足跡 |
| `CarbonFootprintTest` | 建立物件、展示多型並輸出結果 |
| `Makefile` | 編譯並執行 package 化的 Java 程式 |

---

## 相關資訊

- **Exercise：** 10.19 — CarbonFootprint Interface: Polymorphism
- **教材：** *Java How to Program* — Deitel
- **程式語言：** Java
- **Package：** `CarbonFootprint409110035`
- **程式設計概念：** Class、Interface、Polymorphism、Collection、Loop、Method、Encapsulation、`instanceof`、Type Casting
- **外部依賴：** 無

### 實作備註

本專案的重點是展示不同類別如何共享同一個介面，同時保留各自的計算邏輯。`CarbonFootprintTest` 可以透過 `CarbonFootprint` 統一處理三種不同物件，這就是本專案中的多型應用。
