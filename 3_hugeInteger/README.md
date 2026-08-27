# Huge Integer Class

## What I Implemented

- Implemented a `HugeInteger` class that stores integers with up to 40 digits.
- Stored each decimal digit in a fixed-size 40-element integer array.
- Implemented `parse()` to convert a numeric string into the internal digit array.
- Implemented `toString()` to convert the stored digits back into a number string.
- Implemented digit-by-digit addition with `add()` and carry handling.
- Implemented digit-by-digit subtraction with `subtract()` and borrow handling.
- Implemented multiplication with `multiply()`.
- Implemented integer division with `divide()`.
- Implemented remainder calculation with `remainder()`.
- Implemented comparison methods for equal, unequal, greater-than, and less-than checks.
- Implemented `isZero()` to determine whether a number is zero.
- Added a built-in demonstration using two predefined numbers.
- Added interactive testing for two user-entered numbers.
- Added input validation for numbers containing 1 to 40 digits.

## Overview

This project is a Java implementation of the Huge Integer Class exercise. It represents integers that may be too large for Java's built-in numeric types by storing each decimal digit separately in an integer array.

The program performs arithmetic manually, digit by digit, instead of using `int`, `long`, or another built-in large-number type. It also provides comparison predicates and a zero check.

The project demonstrates arrays, strings, character processing, arithmetic algorithms, loops, methods, encapsulation, input validation, and object-oriented programming.

---

## Technical Details

### Data Representation

Each `HugeInteger` object contains:

```java
private int[] digits = new int[40];
private int length = 0;
```

Each array element stores one decimal digit from `0` to `9`. The least significant digit is stored at index `39`, so the number is aligned to the right side of the array.

The `length` field records the number of digits currently represented. A newly constructed object contains zero in every array position and represents zero.

### Parsing

The `parse()` method receives a numeric `String`, clears the previous digits, and stores the characters from right to left.

For example:

```text
"409110035"
```

is converted by subtracting `'0'` from each character. The digits are placed into the 40-element array so that the final digit is at index `39`.

If the input contains more than 40 characters, the current implementation limits the stored length to 40 and keeps the final 40 characters processed by the method.

### String Conversion

The `toString()` method scans the array from left to right, skips leading zeros, and joins the remaining digits into a string.

If every digit is zero, it returns:

```text
0
```

This prevents a zero value from being displayed as a string of 40 zeroes.

---

## Arithmetic Operations

### Addition

`add()` starts at the least significant digit and adds the corresponding digits from both operands together with the carry from the previous position.

The result digit is stored with `% 10`, and the carry is passed to the next position. The result is returned as a new `HugeInteger`; the original operands are not changed.

### Subtraction

`subtract()` calculates:

```text
this - other
```

The method subtracts one digit at a time from right to left. When a digit is too small, it borrows from the next position.

If `this` is smaller than `other`, the current implementation returns a zero `HugeInteger` instead of representing a negative number.

### Multiplication

`multiply()` multiplies the digits of both operands, creates intermediate results, and adds them to produce the final result.

If either operand is zero, the method immediately returns zero. Results are limited by the fixed 40-element representation.

### Division

`divide()` calculates the integer quotient by repeatedly subtracting the divisor from a copy of the dividend. Each successful subtraction increases the quotient by one.

If the divisor is zero, or if the dividend is smaller than the divisor, the method returns zero.

This repeated-subtraction implementation is easy to understand but can be slow for very large values or quotients.

### Remainder

`remainder()` repeatedly subtracts the divisor from a copy of the dividend until the remaining value is smaller than the divisor.

The remaining value is returned as the remainder. If the divisor is zero, the method returns zero.

---

## Comparison Operations

The class provides the following predicate methods:

| Method | Description |
|---|---|
| `isEqualTo()` | Returns `true` when both numbers have the same digits |
| `isNotEqualTo()` | Returns `true` when the numbers are different |
| `isGreaterThan()` | Returns `true` when this number is larger |
| `isLessThan()` | Returns `true` when this number is smaller |
| `isGreaterThanOrEqualTo()` | Returns `true` when this number is larger or equal |
| `isLessThanOrEqualTo()` | Returns `true` when this number is smaller or equal |

Numbers with different lengths are compared by length first. Numbers with the same length are compared from the most significant digit to the least significant digit.

### Zero Check

`isZero()` checks every element in the `digits` array. It returns `true` only when all stored digits are zero.

---

## Environment Requirements

- Java Development Kit (JDK) 8 or later
- Java compiler (`javac`)
- Java runtime (`java`)
- A terminal or command-line environment
- `make` is optional when using the provided Makefile

The project uses the Java standard library and does not require external dependencies.

---

## Build & Run

### Using Java Commands

The source file is:

```text
HugeInteger.java
```

Compile:

```bash
javac HugeInteger.java
```

Run:

```bash
java HugeInteger
```

### Using the Makefile

The Makefile defines `HugeInteger` as the default target. From the project directory, run:

```bash
make
```

This is equivalent to:

```bash
javac HugeInteger.java
java HugeInteger
```

---

## Usage

When the program starts, it first runs a built-in demonstration using:

```text
numberA = 409110035
numberB = 123456789
```

It displays the results of arithmetic, comparison, and zero-checking operations.

After the demonstration, the program asks the user to enter two numbers:

```text
Please enter the first large number (up to 40 digits)：
Please enter the second large number (up to 40 digits)：
```

The program then displays the entered values and applies the same arithmetic and comparison methods to them.

Input is read with `Scanner.nextLine()` and trimmed before validation.

---

## Example

The built-in demonstration produces output similar to:

```text
=== Demonstrating HugeInteger functionality ===
numberA = 409110035
numberB = 123456789

numberA + numberB = 532566824
numberA - numberB = 285653246
numberA * numberB = 505325973390119415
numberA / numberB = 3
numberA % numberB = 37026668

numberA == numberB: false
numberA != numberB: true
numberA > numberB: true
numberA < numberB: false
numberA >= numberB: true
numberA <= numberB: false

zero isZero: true
numberA isZero: false
numberB isZero: false
```

For interactive testing, entering:

```text
409110035
123456789
```

produces the same arithmetic and comparison results for the user-entered values.

---

## Error Handling and Edge Cases

### Input Validation

Interactive input must:

- Contain only decimal digits.
- Contain between 1 and 40 characters.
- Not contain signs, spaces inside the number, decimal points, or other characters.

The program validates input with:

```java
input.matches("\\d{1,40}")
```

Invalid input prints an error message and ends the interactive testing section.

### Numbers Longer Than 40 Digits

The internal representation has a fixed capacity of 40 digits. The current `parse()` implementation limits the stored length to 40 instead of reporting an exception for longer input.

### Subtraction Result

The class does not represent negative values. When `this < other`, `subtract()` returns zero.

### Division by Zero

When the divisor is zero, both `divide()` and `remainder()` return a zero `HugeInteger` instead of throwing an exception.

### Overflow and Performance

Arithmetic results are stored in a fixed-size 40-digit array. Values that need more than 40 digits cannot be represented completely. Division and remainder use repeated subtraction, so these operations may take a long time for large values.

---

## Main Components

| Component | Responsibility |
|---|---|
| `HugeInteger` | Represents and operates on large integers |
| `digits` | Stores individual decimal digits in a 40-element array |
| `length` | Records the current number of digits |
| `parse()` | Converts a numeric string into the internal representation |
| `toString()` | Converts the stored digits back into a string |
| `add()` | Performs digit-by-digit addition |
| `subtract()` | Performs digit-by-digit subtraction |
| `multiply()` | Performs multiplication using digit operations |
| `divide()` | Calculates the integer quotient using repeated subtraction |
| `remainder()` | Calculates the remainder using repeated subtraction |
| `isEqualTo()` | Checks equality |
| `isNotEqualTo()` | Checks inequality |
| `isGreaterThan()` | Checks whether this number is larger |
| `isLessThan()` | Checks whether this number is smaller |
| `isGreaterThanOrEqualTo()` | Checks whether this number is larger or equal |
| `isLessThanOrEqualTo()` | Checks whether this number is smaller or equal |
| `isZero()` | Checks whether the number is zero |
| `main()` | Runs the demonstration and interactive testing |
| `Makefile` | Compiles and runs the program |

---

## Related Information

- **Exercise:** 8.16 — Huge Integer Class
- **Book:** *Java How to Program* — Deitel
- **Language:** Java
- **Data structure:** 40-element integer array
- **Programming concepts:** Classes, arrays, strings, character processing, methods, loops, arithmetic algorithms, comparisons, predicate methods, input validation, and encapsulation
- **External dependencies:** None

### Implementation Notes

The required exercise functionality includes `parse()`, `toString()`, `add()`, `subtract()`, the comparison predicate methods, and `isZero()`. This project also includes `multiply()`, `divide()`, and `remainder()` as additional functionality.

The implementation emphasizes how large-number operations can be built manually by storing each digit separately rather than relying on Java's built-in integer types.

---

# Huge Integer Class 大數類別

## 實作內容

- 使用 40 個元素的整數陣列，儲存最多 40 位數的整數。
- 實作 `parse()`，將數字字串轉換成內部的數字陣列。
- 實作 `toString()`，將儲存的數字轉回字串。
- 實作 `add()`，使用逐位相加與進位處理完成加法。
- 實作 `subtract()`，使用逐位相減與借位處理完成減法。
- 實作 `multiply()`，完成大數乘法。
- 實作 `divide()`，使用重複減法計算整數商。
- 實作 `remainder()`，使用重複減法計算餘數。
- 實作相等、不相等、大於、小於、大於等於與小於等於等比較方法。
- 實作 `isZero()`，判斷數值是否為零。
- 加入預先定義的功能展示，測試算術、比較與零值判斷。
- 加入互動式輸入，讓使用者輸入兩個大數進行測試。
- 加入輸入驗證，限制輸入只能是 1 至 40 位的數字。

## 專案簡介

本專案使用 Java 實作 `HugeInteger` 類別，用來表示與處理可能超過 Java 內建數值型別範圍的大數。

程式不使用 `int`、`long` 或其他內建大數型別，而是將每一個十進位數字分別儲存在整數陣列中，再透過逐位運算完成算術與比較。

這個專案展示陣列、字串、字元處理、算術演算法、迴圈、方法、封裝、輸入驗證與物件導向程式設計。

---

## 技術細節

### 資料表示

每個 `HugeInteger` 物件包含：

```java
private int[] digits = new int[40];
private int length = 0;
```

陣列中的每個元素代表一個 `0` 到 `9` 的十進位數字。最低有效位儲存在索引 `39`，因此數字會靠陣列右側對齊。

`length` 記錄目前數字的位數。新建立的物件會將所有陣列元素初始化為零，代表數字零。

### 字串解析

`parse()` 接收數字字串，先清除原本的內容，再由右至左將每個字元轉換成數字並存入陣列。

如果輸入超過 40 個字元，目前的實作會將儲存長度限制為 40。

### 字串轉換

`toString()` 從陣列左側開始掃描，略過前導零，並將剩餘數字組合成字串。如果所有數字都是零，就回傳：

```text
0
```

---

## 算術運算

### 加法

`add()` 從最低有效位開始，逐位加上兩個運算元與前一位的進位，並將結果存入新的 `HugeInteger` 物件。

### 減法

`subtract()` 計算：

```text
this - other
```

程式從右至左逐位相減，需要時向下一位借位。如果 `this` 小於 `other`，目前實作會回傳零，而不表示負數。

### 乘法

`multiply()` 將兩個數字的各個位數相乘，建立中間結果，再將中間結果累加成最後答案。如果任一運算元為零，會直接回傳零。

結果受到固定 40 位陣列的限制。

### 除法

`divide()` 使用重複減法計算整數商。程式持續從被除數複本中減去除數，每成功減一次，商就加一。

如果除數為零，或被除數小於除數，則回傳零。對很大的商而言，重複減法可能需要較長時間。

### 餘數

`remainder()` 持續從被除數複本中減去除數，直到剩餘值小於除數，最後剩下的數值就是餘數。

如果除數為零，則回傳零。

---

## 比較運算

| 方法 | 說明 |
|---|---|
| `isEqualTo()` | 判斷兩個數字是否相等 |
| `isNotEqualTo()` | 判斷兩個數字是否不相等 |
| `isGreaterThan()` | 判斷目前數字是否較大 |
| `isLessThan()` | 判斷目前數字是否較小 |
| `isGreaterThanOrEqualTo()` | 判斷目前數字是否較大或相等 |
| `isLessThanOrEqualTo()` | 判斷目前數字是否較小或相等 |

兩個數字位數不同時，會先比較位數；位數相同時，則從最高有效位開始逐位比較。

### 零值判斷

`isZero()` 會檢查 `digits` 陣列中的每個元素。只有所有元素都是零時，才會回傳 `true`。

---

## 環境需求

- Java Development Kit（JDK）8 或以上
- Java 編譯器（`javac`）
- Java Runtime（`java`）
- Terminal / Command Line
- 如果使用 Makefile，則需要 `make`

本專案只使用 Java 標準函式庫，不需要額外安裝第三方套件。

---

## Build & Run

### 使用 Java 指令

程式檔案為：

```text
HugeInteger.java
```

編譯：

```bash
javac HugeInteger.java
```

執行：

```bash
java HugeInteger
```

### 使用 Makefile

在專案資料夾執行：

```bash
make
```

Makefile 的預設 target 為 `HugeInteger`，等同於執行：

```bash
javac HugeInteger.java
java HugeInteger
```

---

## 使用方式

程式啟動後，首先會使用以下兩個預設數字執行功能展示：

```text
numberA = 409110035
numberB = 123456789
```

展示內容包括算術運算、比較運算與零值判斷。

展示結束後，程式會要求使用者輸入兩個數字：

```text
Please enter the first large number (up to 40 digits)：
Please enter the second large number (up to 40 digits)：
```

接著會顯示輸入的數字，並執行相同的算術與比較操作。

---

## 範例

內建展示會產生類似以下的結果：

```text
=== Demonstrating HugeInteger functionality ===
numberA = 409110035
numberB = 123456789

numberA + numberB = 532566824
numberA - numberB = 285653246
numberA * numberB = 505325973390119415
numberA / numberB = 3
numberA % numberB = 37026668

numberA == numberB: false
numberA != numberB: true
numberA > numberB: true
numberA < numberB: false
numberA >= numberB: true
numberA <= numberB: false

zero isZero: true
numberA isZero: false
numberB isZero: false
```

如果互動式測試輸入：

```text
409110035
123456789
```

就會得到相同的算術與比較結果。

---

## 錯誤處理與邊界情況

### 輸入驗證

互動式輸入必須符合以下條件：

- 只能包含十進位數字。
- 長度介於 1 至 40 個字元。
- 不可包含正負號、數字中間的空格、小數點或其他字元。

程式使用以下正規表示式驗證：

```java
input.matches("\\d{1,40}")
```

如果輸入格式錯誤，程式會顯示錯誤訊息並結束互動式測試。

### 超過 40 位

內部資料結構固定只能儲存 40 位數。`parse()` 目前會將儲存長度限制為 40，而不是針對超過長度的輸入拋出例外。

### 減法結果

本類別不表示負數。當 `this < other` 時，`subtract()` 會回傳零。

### 除以零

當除數為零時，`divide()` 與 `remainder()` 都會回傳值為零的 `HugeInteger`，不會拋出例外。

### 溢位與效能

算術結果儲存在固定大小的 40 位陣列中，因此超過 40 位的結果無法完整表示。除法與餘數使用重複減法，處理很大的數值時可能需要較長時間。

---

## 主要元件

| 元件 | 功能 |
|---|---|
| `HugeInteger` | 表示與處理大數 |
| `digits` | 儲存個別十進位數字的 40 元素陣列 |
| `length` | 記錄目前數字的位數 |
| `parse()` | 將數字字串轉換成內部表示 |
| `toString()` | 將內部數字轉換回字串 |
| `add()` | 執行逐位加法 |
| `subtract()` | 執行逐位減法 |
| `multiply()` | 使用逐位運算進行乘法 |
| `divide()` | 使用重複減法計算整數商 |
| `remainder()` | 使用重複減法計算餘數 |
| `isEqualTo()` | 判斷是否相等 |
| `isNotEqualTo()` | 判斷是否不相等 |
| `isGreaterThan()` | 判斷是否較大 |
| `isLessThan()` | 判斷是否較小 |
| `isGreaterThanOrEqualTo()` | 判斷是否較大或相等 |
| `isLessThanOrEqualTo()` | 判斷是否較小或相等 |
| `isZero()` | 判斷是否為零 |
| `main()` | 執行功能展示與互動式測試 |
| `Makefile` | 編譯與執行程式 |

---

## 相關資訊

- **Exercise：** 8.16 — Huge Integer Class
- **教材：** *Java How to Program* — Deitel
- **程式語言：** Java
- **資料結構：** 40 元素整數陣列
- **程式設計概念：** Class、Array、String、Character Processing、Method、Loop、Arithmetic Algorithm、Comparison、Predicate Method、Input Validation、Encapsulation
- **外部依賴：** 無

### 實作備註

原題要求實作 `parse()`、`toString()`、`add()`、`subtract()`、各種比較 predicate methods，以及 `isZero()`。本專案另外加入 `multiply()`、`divide()` 與 `remainder()` 作為延伸功能。

本實作的重點，是展示如何不依賴 Java 內建整數型別，將每個數字分開儲存，再透過逐位運算完成大數處理。
