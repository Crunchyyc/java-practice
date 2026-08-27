# Simpletron Simulator

## What I Implemented

* Implemented a Simpletron simulator in Java based on Exercise 7.37 from *Java How to Program* (Deitel).

* Implemented program loading with location-by-location prompts and a sentinel-controlled input loop.

* Implemented the complete fetch–decode–execute instruction cycle using `instructionRegister`, `operationCode`, and `operand`.

* Implemented accumulator-based arithmetic operations, including `ADD` and `SUBTRACT`.

* Implemented conditional and unconditional branching with `BRANCH`, `BRANCHNEG`, and `BRANCHZERO`.

* Implemented memory-based input/output with `READ` and `WRITE`.

* Implemented `LOAD` and `STORE` operations for transferring values between memory and the accumulator.

* Added input validation during program loading to reject values outside the valid range of `-9999` to `+9999`.

* Added arithmetic overflow detection during `ADD`, `SUBTRACT`, and `READ` operations.

* Added invalid operation-code detection that terminates execution when an unrecognized opcode is encountered.

* Implemented a complete register and memory dump for debugging after both normal (`HALT`) and abnormal (fatal error) termination.

## Overview

This project is a Java implementation of the Simpletron, a hypothetical computer that executes programs written in SML (Simpletron Machine Language). It corresponds to Exercise 7.37 from *Java How to Program* by Deitel.

The Simpletron uses a 100-word memory and a single accumulator register. Each SML instruction is represented as a signed four-digit integer, with the first two digits representing the operation code and the last two digits representing the operand.

The simulator follows the standard fetch–decode–execute cycle. It loads an SML program into memory, fetches each instruction, decodes the operation code and operand, and then executes the corresponding operation.

The implementation also includes input validation, arithmetic overflow detection, invalid opcode detection, and a complete memory/register dump to make program execution easier to inspect and debug.

---

## Technical Details

### Machine Representation

The Simpletron consists of:

* **100 words of memory**, addressed from `00` to `99`
* **One accumulator register**, used for calculations and intermediate values
* **An instruction counter**, which stores the location of the next instruction
* **An instruction register**, which stores the current instruction
* **An operation code**, which identifies the operation to execute
* **An operand**, which identifies the memory location used by the operation

Each SML instruction or data value is a signed four-digit integer in the range:

```text
-9999 to +9999
```

For an instruction, the first two digits represent the **operation code**, while the last two digits represent the **operand**.

### Fetch–Decode–Execute Cycle

The simulator processes instructions using the following sequence:

1. Fetch the instruction from the memory location indicated by the instruction counter.
2. Store the instruction in the instruction register.
3. Extract the operation code from the instruction.
4. Extract the operand from the instruction.
5. Execute the operation.
6. Continue with the next instruction unless a branch changes the instruction counter or `HALT` terminates execution.

### Supported Operation Codes

| Code | Name       | Description                                                           |
| ---- | ---------- | --------------------------------------------------------------------- |
| 10   | READ       | Read a word from the keyboard into a specified memory location        |
| 11   | WRITE      | Write a word from a specified memory location to the screen           |
| 20   | LOAD       | Load a word from a specified memory location into the accumulator     |
| 21   | STORE      | Store the accumulator value into a specified memory location          |
| 30   | ADD        | Add a word from a specified memory location to the accumulator        |
| 31   | SUBTRACT   | Subtract a word from a specified memory location from the accumulator |
| 40   | BRANCH     | Unconditionally branch to a specified memory location                 |
| 41   | BRANCHNEG  | Branch if the accumulator is negative                                 |
| 42   | BRANCHZERO | Branch if the accumulator is zero                                     |
| 43   | HALT       | Terminate program execution                                           |

### Program Loading

The simulator loads one instruction or data word at a time.

The current memory location is displayed before each input, allowing the user to enter the corresponding SML instruction or data value.

The sentinel value:

```text
-99999
```

terminates the program-loading phase and starts program execution.

---

## Environment Requirements

* Java Development Kit (JDK) 8 or later
* Java compiler (`javac`)
* Java runtime (`java`)
* A terminal or command-line environment

No external dependencies are required.

---

## Build & Run

### Compile with Java

The source file is:

```text
computer_simulator.java
```

Compile:

```bash
javac computer_simulator.java
```

Run:

```bash
java computer_simulator
```

---

## Usage

When the simulator starts, it prompts the user to enter one instruction or data word at a time:

```text
*** Welcome to Simpletron! ***

*** Please enter your program one instruction
*** (or data word) at a time. I will display
*** the location number and a question mark (?).
*** You then type the word for that location.
*** Type -99999 to stop entering your program.

00 ? +1009
01 ? +1010
02 ? +2009
03 ? +3110
04 ? +4107
05 ? +1109
06 ? +4300
07 ? +1110
08 ? +4300
09 ? +0000
10 ? +0000
11 ? -99999

*** Program loading completed ***

*** Program execution begins ***
```

Enter `-99999` to finish loading the program. The simulator then automatically begins execution.

During execution, the simulator processes each SML instruction according to its operation code.

---

## Example

### Sample Program: Read Two Numbers and Display the Larger

The following SML program reads two numbers, compares them, and displays the larger value.

| Location | Instruction | Description           |
| -------- | ----------- | --------------------- |
| 00       | `+1009`     | Read A                |
| 01       | `+1010`     | Read B                |
| 02       | `+2009`     | Load A                |
| 03       | `+3110`     | Subtract B            |
| 04       | `+4107`     | Branch negative to 07 |
| 05       | `+1109`     | Write A               |
| 06       | `+4300`     | Halt                  |
| 07       | `+1110`     | Write B               |
| 08       | `+4300`     | Halt                  |
| 09       | `+0000`     | Variable A            |
| 10       | `+0000`     | Variable B            |

The program performs the following operations:

1. Read the first number into memory location `09`.
2. Read the second number into memory location `10`.
3. Load the first number into the accumulator.
4. Subtract the second number.
5. If the result is negative, branch to location `07` and output B.
6. Otherwise, output A.
7. Halt execution.

---

## Error Handling and Edge Cases

The simulator handles several important error conditions.

### Program Loading

During the loading phase:

* Values outside the valid range of `-9999` to `+9999` are rejected.
* The user is prompted to enter a valid value again.

### Program Execution

During execution, the following conditions terminate the program with a fatal error:

* **Accumulator overflow**
* **Invalid operation code**

When a fatal error occurs, the simulator displays an error message and prints the complete register and memory dump before terminating.

### Memory Dump

The simulator prints the contents of all registers and memory locations after:

* Normal termination through `HALT`
* Abnormal termination caused by a fatal error

Example:

```text
REGISTERS:

Accumulator          +0000
instructionCounter   00
instructionRegister  +0000
operationCode        00
operand              00

MEMORY:

       0     1     2     3     4     5     6     7     8     9
 0 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000
10 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000
...
```

---

## Main Components

| Component                  | Responsibility                                               |
| -------------------------- | ------------------------------------------------------------ |
| `computer_simulator`       | Main simulator class and program execution                   |
| Memory                     | Stores SML instructions and data                             |
| `accumulator`              | Stores calculation results and intermediate values           |
| `instructionCounter`       | Tracks the next instruction to execute                       |
| `instructionRegister`      | Stores the current instruction                               |
| `operationCode`            | Identifies the operation to execute                          |
| `operand`                  | Identifies the memory location used by the operation         |
| Program loading            | Reads SML instructions and data into memory                  |
| Fetch–decode–execute cycle | Fetches, decodes, and executes SML instructions              |
| Error handling             | Detects invalid input, overflow, and invalid operation codes |
| Memory dump                | Displays register and memory contents for debugging          |

---

## Related Information

* **Exercise:** 7.37 — Computer Simulator
* **Related Exercise:** 7.36 — SML Programs
* **Book:** *Java How to Program* — Deitel
* **Language:** Java
* **Programming concepts:** Classes, arrays, loops, conditionals, methods, input handling, machine-language simulation, registers, memory management, instruction decoding, and error handling

Exercise 7.36 introduces SML programs for tasks such as calculating the sum of positive numbers, calculating the average of seven numbers, and finding the largest number. Exercise 7.37 extends this by requiring a Simpletron simulator capable of loading and executing SML programs.

---

## Implementation Notes

This implementation focuses on demonstrating how a simple computer architecture can be modeled using Java.

The simulator explicitly represents concepts such as memory, registers, an accumulator, an instruction counter, operation codes, and operands rather than relying on Java's normal control flow alone.

The additional validation and error-handling logic extends the basic simulator by detecting invalid input, arithmetic overflow, and unsupported operation codes. The register and memory dump provides a way to inspect the machine state after execution and is useful for debugging SML programs.

---

# Simpletron 模擬器

## 實作內容

* 使用 Java 實作 Simpletron 模擬器，對應 Deitel《Java How to Program》的 Exercise 7.37。

* 實作逐一輸入記憶體位置的程式載入流程，並使用哨兵值（sentinel）控制輸入結束。

* 實作完整的 fetch–decode–execute 指令週期，使用 `instructionRegister`、`operationCode` 與 `operand` 處理指令。

* 實作以累加器為核心的算術運算，包括 `ADD` 與 `SUBTRACT`。

* 實作條件與無條件分支，包括 `BRANCH`、`BRANCHNEG` 與 `BRANCHZERO`。

* 實作以記憶體為基礎的輸入輸出操作，包括 `READ` 與 `WRITE`。

* 實作 `LOAD` 與 `STORE`，處理記憶體與累加器之間的資料傳遞。

* 加入程式載入階段的輸入驗證，拒絕超出 `-9999` 至 `+9999` 範圍的數值。

* 加入 `ADD`、`SUBTRACT` 與 `READ` 過程中的算術溢位偵測。

* 加入無效運算碼偵測，遇到未支援的 operation code 時會終止程式執行。

* 實作完整的暫存器與記憶體傾印（memory dump），在正常結束（`HALT`）以及異常終止（fatal error）時皆輸出，方便檢查程式執行狀態。

## 專案簡介

本專案是使用 Java 實作的 Simpletron 模擬器，對應 Deitel《Java How to Program》的 Exercise 7.37。

Simpletron 是一台假想的電腦，只能執行以 SML（Simpletron Machine Language）撰寫的程式。

Simpletron 使用 100 個字組的記憶體以及一個累加器。每個 SML 指令都以帶符號的四位數整數表示，其中前兩碼代表運算碼（operation code），後兩碼代表運算元（operand）。

模擬器透過 fetch–decode–execute 指令週期執行程式：從記憶體取得指令、解析運算碼與運算元，再執行對應的操作。

除了基本的指令執行功能之外，本專案也加入輸入驗證、算術溢位偵測、無效運算碼偵測，以及完整的暫存器與記憶體傾印功能，方便檢查與除錯 SML 程式。

---

## 技術細節

### 電腦架構與資料表示

Simpletron 包含：

* **100 個字組（word）的記憶體**，位置編號為 `00` 至 `99`
* **一個累加器（accumulator）**，用於運算與儲存中間結果
* **指令計數器（instruction counter）**，記錄下一個要執行的指令位置
* **指令暫存器（instruction register）**，儲存目前正在執行的指令
* **運算碼（operation code）**，表示要執行的操作
* **運算元（operand）**，表示操作所使用的記憶體位置

每個 SML 指令或資料值都是帶符號的四位數整數，合法範圍為：

```text
-9999 ～ +9999
```

對於 SML 指令而言，前兩碼代表**運算碼**，後兩碼代表**運算元**。

### Fetch–Decode–Execute 指令週期

模擬器按照以下流程處理每一個指令：

1. 從指令計數器指定的記憶體位置取得指令。
2. 將指令存入指令暫存器。
3. 從指令中解析運算碼。
4. 從指令中解析運算元。
5. 執行對應的操作。
6. 除非分支指令改變指令計數器，否則繼續執行下一個指令；遇到 `HALT` 則終止執行。

### 支援的運算碼

| 運算碼 | 名稱         | 說明                |
| --- | ---------- | ----------------- |
| 10  | READ       | 從鍵盤讀取數值並存入指定記憶體位置 |
| 11  | WRITE      | 將指定記憶體位置的值輸出到螢幕   |
| 20  | LOAD       | 將指定記憶體位置的值載入累加器   |
| 21  | STORE      | 將累加器的值存入指定記憶體位置   |
| 30  | ADD        | 將指定記憶體位置的值加入累加器   |
| 31  | SUBTRACT   | 將指定記憶體位置的值從累加器中減去 |
| 40  | BRANCH     | 無條件跳躍到指定記憶體位置     |
| 41  | BRANCHNEG  | 如果累加器為負數，跳躍到指定位置  |
| 42  | BRANCHZERO | 如果累加器為零，跳躍到指定位置   |
| 43  | HALT       | 終止程式執行            |

### 程式載入

模擬器會一次要求使用者輸入一個指令或資料。

每次輸入前會顯示目前的記憶體位置，讓使用者輸入對應的 SML 指令或資料。

輸入以下哨兵值：

```text
-99999
```

即可結束程式載入階段，並自動開始執行程式。

---

## 環境需求

* Java Development Kit（JDK）8 或以上
* Java 編譯器（`javac`）
* Java Runtime（`java`）
* Terminal / Command Line

不需要額外安裝第三方套件。

---

## Build & Run

### 使用 Java 編譯

程式檔案名稱為：

```text
computer_simulator.java
```

編譯：

```bash
javac computer_simulator.java
```

執行：

```bash
java computer_simulator
```

---

## Usage

啟動模擬器後，程式會逐一詢問每個記憶體位置要輸入的指令或資料：

```text
*** Welcome to Simpletron! ***

*** Please enter your program one instruction
*** (or data word) at a time. I will display
*** the location number and a question mark (?).
*** You then type the word for that location.
*** Type -99999 to stop entering your program.

00 ? +1009
01 ? +1010
02 ? +2009
03 ? +3110
04 ? +4107
05 ? +1109
06 ? +4300
07 ? +1110
08 ? +4300
09 ? +0000
10 ? +0000
11 ? -99999

*** Program loading completed ***

*** Program execution begins ***
```

輸入 `-99999` 後，程式載入結束，模擬器會自動開始執行 SML 程式。

執行期間，模擬器會根據每個指令的運算碼執行對應操作。

---

## 範例

### 範例程式：讀取兩個數字並顯示較大的值

以下 SML 程式會讀取兩個數字，比較兩者後輸出較大的數字。

| 位置 | 指令      | 說明                    |
| -- | ------- | --------------------- |
| 00 | `+1009` | Read A                |
| 01 | `+1010` | Read B                |
| 02 | `+2009` | Load A                |
| 03 | `+3110` | Subtract B            |
| 04 | `+4107` | Branch negative to 07 |
| 05 | `+1109` | Write A               |
| 06 | `+4300` | Halt                  |
| 07 | `+1110` | Write B               |
| 08 | `+4300` | Halt                  |
| 09 | `+0000` | Variable A            |
| 10 | `+0000` | Variable B            |

程式執行流程如下：

1. 將第一個數字讀入記憶體位置 `09`。
2. 將第二個數字讀入記憶體位置 `10`。
3. 將第一個數字載入累加器。
4. 從累加器中減去第二個數字。
5. 如果結果為負數，跳躍到位置 `07` 並輸出 B。
6. 否則輸出 A。
7. 終止程式。

---

## 錯誤處理與邊界情況

模擬器會處理以下重要的錯誤情況。

### 程式載入階段

載入程式時：

* 超出 `-9999` ～ `+9999` 範圍的數值會被拒絕。
* 程式會要求使用者重新輸入合法數值。

### 程式執行階段

執行程式時，以下情況會造成致命錯誤並終止：

* **累加器溢位（Accumulator Overflow）**
* **無效運算碼（Invalid Operation Code）**

發生致命錯誤時，模擬器會顯示錯誤訊息，並在結束前輸出完整的暫存器與記憶體傾印。程式目前明確偵測累加器溢位與無效運算碼；輸入非整數或使用超出記憶體範圍的運算元，則可能造成未處理的例外。

### 記憶體傾印

模擬器會在以下情況輸出所有暫存器與記憶體內容：

* 透過 `HALT` 正常結束
* 因致命錯誤而異常終止

範例：

```text
REGISTERS:

Accumulator          +0000
instructionCounter   00
instructionRegister  +0000
operationCode        00
operand              00

MEMORY:

       0     1     2     3     4     5     6     7     8     9
 0 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000
10 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000 +0000
...
```

---

## 主要元件

| 元件                    | 功能               |
| --------------------- | ---------------- |
| `computer_simulator`  | 主要模擬器類別與程式執行     |
| Memory                | 儲存 SML 指令與資料     |
| `accumulator`         | 儲存運算結果與中間值       |
| `instructionCounter`  | 記錄下一個要執行的指令位置    |
| `instructionRegister` | 儲存目前正在執行的指令      |
| `operationCode`       | 判斷目前要執行的操作       |
| `operand`             | 指定操作所使用的記憶體位置    |
| 程式載入                  | 將 SML 指令與資料讀入記憶體 |
| Fetch–decode–execute  | 取得、解析並執行 SML 指令  |
| 錯誤處理                  | 偵測無效輸入、溢位與無效運算碼  |
| Memory dump           | 顯示暫存器與記憶體內容以協助除錯 |

---

## 相關資訊

* **Exercise：** 7.37 — Computer Simulator
* **相關習題：** 7.36 — SML Programs
* **教材：** *Java How to Program* — Deitel
* **程式語言：** Java
* **程式設計概念：** Class、Array、Loop、Conditional、Method、Input Handling、Machine Language Simulation、Register、Memory Management、Instruction Decoding、Error Handling

Exercise 7.36 主要要求撰寫 SML 程式，例如計算正數總和、計算七個數字的平均值，以及找出最大值。Exercise 7.37 則進一步要求建立可以載入並執行這些 SML 程式的 Simpletron 模擬器。

---

## 實作說明

本專案主要展示如何使用 Java 模擬一個簡單的電腦架構。

模擬器以程式中的變數與控制流程明確表示記憶體、暫存器、累加器、指令計數器、運算碼與運算元等概念，而不是單純依賴 Java 本身的控制流程來執行程式。

在基本的 Simpletron 功能之外，本專案也加入輸入驗證、算術溢位與無效運算碼偵測，並提供暫存器與記憶體傾印功能，讓使用者可以在程式執行結束後檢查 Simpletron 的狀態。
