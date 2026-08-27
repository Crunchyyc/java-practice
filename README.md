# Java Practice Projects

## Overview

This repository contains five Java practice projects based on exercises from *Java How to Program* by Deitel. The projects cover computer simulation, game development, arbitrary-precision arithmetic, interface-based design, polymorphism, and JavaFX animation.

Each project is stored in its own directory and includes its source code, exercise description, README documentation, and, where applicable, a Makefile.

## Project List

| Project | Directory | Main topic | Entry point |
|---|---|---|---|
| 1 | `1_computer_simulator` | Simpletron and SML machine-language simulation | `computer_simulator` |
| 2 | `2_TicTacToe` | 3×3 game, validation, and basic AI | `TicTacToe` |
| 3 | `3_hugeInteger` | 40-digit arithmetic using an integer array | `HugeInteger` |
| 4 | `4_carbonFootprint_interface_polymorphism` | Interfaces and polymorphism | `CarbonFootprintTest` |
| 5 | `5_bouncing_balls` | JavaFX animation and mouse events | `BouncingBalls` |

## Repository Structure

```text
java_practice/
├── README.md
├── 1_computer_simulator/
│   ├── computer_simulator.java
│   ├── question_computer_simulator.txt
│   └── README.md
├── 2_TicTacToe/
│   ├── Makefile
│   ├── question_TicTacToe.txt
│   ├── README.md
│   └── TicTacToe.java
├── 3_hugeInteger/
│   ├── HugeInteger.java
│   ├── Makefile
│   ├── question_hugelnteger.txt
│   └── README.md
├── 4_carbonFootprint_interface_polymorphism/
│   ├── Bicycle.java
│   ├── Building.java
│   ├── Car.java
│   ├── CarbonFootprint.java
│   ├── CarbonFootprintTest.java
│   ├── Makefile
│   ├── question_CarbonFootprint_Interface_Polymorphism.txt
│   └── README.md
└── 5_bouncing_balls/
	├── Makefile
	├── README.md
	├── src/BouncingBalls.java
	└── .vscode/
```

## Learning Objectives

The projects collectively practice:

- Classes, objects, fields, constructors, and methods
- Encapsulation and object-oriented design
- Arrays and two-dimensional arrays
- Strings and character processing
- Loops, conditionals, and state management
- Input handling and validation
- Arithmetic algorithms and comparisons
- Interfaces and polymorphism
- Inheritance through JavaFX classes
- Event handling and graphical animation
- Error detection and debugging output

---

## Project 1: Simpletron Computer Simulator

### Description

`1_computer_simulator` implements a Simpletron, a hypothetical computer that executes programs written in Simpletron Machine Language (SML).

The simulator contains 100 memory locations, an accumulator, an instruction counter, an instruction register, an operation code, and an operand. It follows the fetch-decode-execute cycle to load and execute SML instructions.

### Supported Instructions

| Code | Instruction | Function |
|---|---|---|
| 10 | `READ` | Read an integer into memory |
| 11 | `WRITE` | Display a value from memory |
| 20 | `LOAD` | Load a memory value into the accumulator |
| 21 | `STORE` | Store the accumulator in memory |
| 30 | `ADD` | Add a memory value to the accumulator |
| 31 | `SUBTRACT` | Subtract a memory value from the accumulator |
| 40 | `BRANCH` | Unconditionally change the instruction counter |
| 41 | `BRANCHNEG` | Branch when the accumulator is negative |
| 42 | `BRANCHZERO` | Branch when the accumulator is zero |
| 43 | `HALT` | End program execution |

Program and data values must be between `-9999` and `+9999`. Enter `-99999` to finish loading instructions. The simulator displays a complete register and memory dump after normal or fatal termination.

### Run

```bash
cd 1_computer_simulator
javac computer_simulator.java
java computer_simulator
```

The program is interactive. Enter one SML instruction or data value at each memory location, then enter `-99999` to begin execution.

---

## Project 2: Tic-Tac-Toe

### Description

`2_TicTacToe` implements a 3×3 command-line Tic-Tac-Toe game. It supports both human-versus-human and human-versus-computer modes.

The board uses a two-dimensional `CellState[][]` array with the values `X`, `O`, and `EMPTY`. Players enter row and column coordinates from `0` to `2`.

### Features

- Player 1 first or Player 2 first selection
- Human-versus-human mode
- Human-versus-computer mode
- Move validation for range and occupied cells
- Row, column, and diagonal win detection
- Draw detection when the board is full
- Computer strategy that wins when possible, blocks the opponent, chooses the center, and otherwise selects a random empty cell

### Run

```bash
cd 2_TicTacToe
javac TicTacToe.java
java TicTacToe
```

The Makefile can also compile and run the program:

```bash
make TicTacToe
```

The program asks whether to play against the computer and whether Player 1 wants to go first. Human moves are entered as a row and column, for example `1 2`.

---

## Project 3: Huge Integer Class

### Description

`3_hugeInteger` implements a `HugeInteger` class for numbers with up to 40 digits. Each decimal digit is stored separately in a fixed-size integer array rather than in a Java primitive numeric type.

The least significant digit is stored at index `39`. The class uses a `length` field to record the number of digits currently represented.

### Features

- String parsing with `parse()`
- String conversion with `toString()`
- Addition with carry handling
- Subtraction with borrow handling
- Multiplication
- Integer division
- Remainder calculation
- Equality and ordering predicates
- Zero checking with `isZero()`
- Built-in demonstration and interactive testing

The implementation does not represent negative results. If subtraction would produce a negative value, it returns zero. Division and remainder use repeated subtraction, and division by zero returns zero.

### Run

```bash
cd 3_hugeInteger
javac HugeInteger.java
java HugeInteger
```

Or use the Makefile:

```bash
make
```

Interactive input must contain only digits and be between 1 and 40 characters long.

---

## Project 4: Carbon Footprint Interface and Polymorphism

### Description

`4_carbonFootprint_interface_polymorphism` demonstrates how different classes can implement one shared interface while keeping their own calculation logic.

The `CarbonFootprint` interface defines `getCarbonFootprint()`. `Building`, `Car`, and `Bicycle` implement this interface and are stored together in an `ArrayList<CarbonFootprint>`.

### Implementations

| Class | Calculation |
|---|---|
| `Building` | Electricity usage × `0.5` + building area × `10.0` |
| `Car` | Fuel consumption × `2.3` + car weight × `0.5` |
| `Bicycle` | Annual distance × `0.0205` |

`CarbonFootprintTest` creates one object of each type, uses the interface to calculate each result polymorphically, and prints the object's description and carbon footprint in kilograms of CO2 per year.

### Run

```bash
cd 4_carbonFootprint_interface_polymorphism
javac -d . CarbonFootprint.java CarbonFootprintTest.java Car.java Building.java Bicycle.java
java -cp . CarbonFootprint409110035.CarbonFootprintTest
```

Or use the Makefile:

```bash
make
```

The source files use the package `CarbonFootprint409110035`, so the package name is required when running the main class.

---

## Project 5: Bouncing Balls

### Description

`5_bouncing_balls` is a JavaFX animation in which the user clicks inside an `800 × 600` window to create colored balls. Each ball moves independently with a random speed and direction.

The application uses a JavaFX `Timeline` with a 20-millisecond `KeyFrame`. When a ball reaches a window boundary, its horizontal or vertical velocity is reversed.

### Features

- Mouse-click ball creation
- Random RGB color for each ball
- Random speed from `2` to `10`
- Random movement direction
- Maximum of 30 balls
- Boundary collision detection
- Continuous animation using JavaFX `Timeline`

The program focuses on wall collisions. Ball-to-ball collisions, gravity, acceleration, and friction are not implemented.

### Requirements

- JDK
- JavaFX SDK 24.0.1
- A graphical desktop environment

The Makefile expects the JavaFX libraries at:

```text
C:/Program Files/Java/javafx-sdk-24.0.1/lib
```

### Run

From the `5_bouncing_balls` directory:

```bash
make BouncingBalls
```

To compile and run manually:

```bash
javac --module-path "C:/Program Files/Java/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.graphics -d bin src/BouncingBalls.java
java --module-path "C:/Program Files/Java/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.graphics -cp bin BouncingBalls
```

---

## General Requirements

Projects 1 through 4 use the Java standard library and do not require third-party dependencies. Project 5 additionally requires the JavaFX SDK.

The exact JDK version is not specified by the projects. A modern JDK compatible with the installed JavaFX SDK is recommended. The commands above should be run from the corresponding project directory.

## Documentation

Each project contains its own README with detailed technical information, examples, edge cases, main components, and implementation notes. The `question_*.txt` files contain the related exercise descriptions.

---

# Java 練習專案

## 專案簡介

本資料夾包含五個 Java 練習專案，內容主要根據 Deitel 的 *Java How to Program* 相關習題製作。主題包括電腦模擬、遊戲設計、大數運算、介面與多型，以及 JavaFX 動畫。

每個專案都放在獨立資料夾中，並依專案需求包含原始碼、題目說明、README，以及部分專案使用的 Makefile。

## 專案列表

| 專案 | 資料夾 | 主要主題 | 進入點 |
|---|---|---|---|
| 1 | `1_computer_simulator` | Simpletron 與 SML 機器語言模擬 | `computer_simulator` |
| 2 | `2_TicTacToe` | 3×3 遊戲、輸入驗證與基本 AI | `TicTacToe` |
| 3 | `3_hugeInteger` | 使用整數陣列處理 40 位數大數 | `HugeInteger` |
| 4 | `4_carbonFootprint_interface_polymorphism` | 介面與多型 | `CarbonFootprintTest` |
| 5 | `5_bouncing_balls` | JavaFX 動畫與滑鼠事件 | `BouncingBalls` |

## 資料夾結構

```text
java_practice/
├── README.md
├── 1_computer_simulator/
├── 2_TicTacToe/
├── 3_hugeInteger/
├── 4_carbonFootprint_interface_polymorphism/
└── 5_bouncing_balls/
```

每個專案的詳細檔案與說明，請參閱各資料夾中的 README。

## 學習目標

這五個專案合併練習以下內容：

- 類別、物件、欄位、建構子與方法
- 封裝與物件導向設計
- 一維陣列與二維陣列
- 字串與字元處理
- 迴圈、條件判斷與狀態管理
- 使用者輸入與輸入驗證
- 算術演算法與比較運算
- 介面與多型
- JavaFX 類別的繼承
- 事件處理與圖形動畫
- 錯誤偵測與除錯輸出

---

## 專案 1：Simpletron 電腦模擬器

### 專案內容

`1_computer_simulator` 使用 Java 實作 Simpletron。Simpletron 是一台可以執行 SML（Simpletron Machine Language）程式的假想電腦。

模擬器包含 100 個記憶體位置、一個累加器、指令計數器、指令暫存器、運算碼與運算元，並按照 fetch-decode-execute 指令週期載入和執行 SML 指令。

### 支援的指令

| 運算碼 | 指令 | 功能 |
|---|---|---|
| 10 | `READ` | 將整數讀入記憶體 |
| 11 | `WRITE` | 顯示記憶體中的值 |
| 20 | `LOAD` | 將記憶體值載入累加器 |
| 21 | `STORE` | 將累加器存入記憶體 |
| 30 | `ADD` | 將記憶體值加入累加器 |
| 31 | `SUBTRACT` | 從累加器減去記憶體值 |
| 40 | `BRANCH` | 無條件修改指令計數器 |
| 41 | `BRANCHNEG` | 累加器為負數時跳躍 |
| 42 | `BRANCHZERO` | 累加器為零時跳躍 |
| 43 | `HALT` | 結束程式執行 |

程式與資料的輸入範圍為 `-9999` 至 `+9999`。輸入 `-99999` 可結束程式載入。正常結束或發生致命錯誤時，模擬器都會顯示完整的暫存器與記憶體內容。

### 執行方式

```bash
cd 1_computer_simulator
javac computer_simulator.java
java computer_simulator
```

程式是互動式的，請逐一輸入 SML 指令或資料，最後輸入 `-99999` 開始執行。

---

## 專案 2：Tic-Tac-Toe

### 專案內容

`2_TicTacToe` 使用命令列實作 3×3 Tic-Tac-Toe，支援雙人對戰與人機對戰。

棋盤使用包含 `X`、`O` 與 `EMPTY` 的 `CellState[][]` 二維陣列。玩家使用 `0` 到 `2` 的列、欄座標輸入落子位置。

### 功能

- 選擇 Player 1 或 Player 2 先手
- 雙人對戰模式
- 人機對戰模式
- 檢查座標範圍與格子是否已被使用
- 橫列、直欄與對角線勝負判定
- 棋盤填滿時的平局判定
- 電腦可以優先獲勝、阻擋對手、選擇中央，否則從空格中隨機落子

### 執行方式

```bash
cd 2_TicTacToe
javac TicTacToe.java
java TicTacToe
```

也可以使用 Makefile：

```bash
make TicTacToe
```

程式會先詢問是否與電腦對戰，以及 Player 1 是否先手。玩家輸入列與欄即可落子，例如 `1 2`。

---

## 專案 3：Huge Integer 大數類別

### 專案內容

`3_hugeInteger` 實作可以處理最多 40 位數的 `HugeInteger` 類別。每一個十進位數字都分別儲存在固定大小的整數陣列中，而不是使用 Java 的 primitive 數值型別。

最低有效位儲存在索引 `39`，並使用 `length` 欄位記錄目前數字的位數。

### 功能

- 使用 `parse()` 解析字串
- 使用 `toString()` 轉回字串
- 處理進位的大數加法
- 處理借位的大數減法
- 乘法
- 整數除法
- 餘數計算
- 相等與大小比較方法
- 使用 `isZero()` 判斷零值
- 內建展示與互動式測試

目前不表示負數結果；如果減法結果為負數，會回傳零。除法與餘數使用重複減法，除以零時回傳零。

### 執行方式

```bash
cd 3_hugeInteger
javac HugeInteger.java
java HugeInteger
```

或使用 Makefile：

```bash
make
```

互動式輸入只能包含數字，長度必須介於 1 至 40 個字元。

---

## 專案 4：Carbon Footprint 介面與多型

### 專案內容

`4_carbonFootprint_interface_polymorphism` 展示不同類別如何實作同一個介面，同時保留各自的碳足跡計算邏輯。

`CarbonFootprint` 介面定義 `getCarbonFootprint()`。`Building`、`Car` 與 `Bicycle` 都實作此介面，並一起儲存在 `ArrayList<CarbonFootprint>` 中。

### 實作類別

| 類別 | 計算方式 |
|---|---|
| `Building` | 用電量 × `0.5` + 建築面積 × `10.0` |
| `Car` | 燃油消耗量 × `2.3` + 車重 × `0.5` |
| `Bicycle` | 年度騎乘距離 × `0.0205` |

`CarbonFootprintTest` 會建立三種物件，透過介面以多型方式計算結果，並輸出物件描述與每年公斤 CO2 的碳足跡。

### 執行方式

```bash
cd 4_carbonFootprint_interface_polymorphism
javac -d . CarbonFootprint.java CarbonFootprintTest.java Car.java Building.java Bicycle.java
java -cp . CarbonFootprint409110035.CarbonFootprintTest
```

或使用 Makefile：

```bash
make
```

原始碼使用 `CarbonFootprint409110035` package，因此執行主類別時必須包含完整 package 名稱。

---

## 專案 5：Bouncing Balls 彈跳小球

### 專案內容

`5_bouncing_balls` 是一個 JavaFX 動畫。使用者可以在 `800 × 600` 的視窗中點擊，建立彩色小球。每顆小球都會以隨機速度與方向獨立移動。

程式使用每 20 毫秒執行一次的 JavaFX `Timeline` 與 `KeyFrame`。當小球碰到視窗邊界時，會反轉水平或垂直速度。

### 功能

- 滑鼠點擊建立小球
- 每顆小球使用隨機 RGB 顏色
- 隨機速度範圍為 `2` 到 `10`
- 隨機移動方向
- 最多建立 30 顆小球
- 邊界碰撞判定
- 使用 JavaFX `Timeline` 持續播放動畫

本程式只處理小球與牆壁的碰撞，尚未實作小球彼此碰撞、重力、加速度或摩擦力。

### 環境需求

- JDK
- JavaFX SDK 24.0.1
- 圖形桌面環境

Makefile 預設使用以下 JavaFX 路徑：

```text
C:/Program Files/Java/javafx-sdk-24.0.1/lib
```

### 執行方式

在 `5_bouncing_balls` 資料夾中執行：

```bash
make BouncingBalls
```

也可以手動編譯與執行：

```bash
javac --module-path "C:/Program Files/Java/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.graphics -d bin src/BouncingBalls.java
java --module-path "C:/Program Files/Java/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.graphics -cp bin BouncingBalls
```

---

## 共通環境需求

專案 1 至 4 只使用 Java 標準函式庫，不需要第三方套件。專案 5 另外需要 JavaFX SDK。

各專案沒有指定唯一的 JDK 版本，建議使用與已安裝 JavaFX SDK 相容的現代 JDK。以上指令應在對應的專案資料夾中執行。

## 文件說明

每個專案都包含自己的 README，提供更詳細的技術內容、範例、邊界情況、主要元件與實作備註。`question_*.txt` 檔案則包含相關練習題目說明。
