# Simpletron Simulator

A Java implementation of a simple computer simulator (Simpletron) that reads and executes programs written in SML (Simpletron Machine Language). This project implements Exercise **7.37 (Computer Simulator)** from *Java How to Program* (Deitel).

## Overview

The Simpletron is a hypothetical computer with:

- **100 words of memory**, addressed by location numbers 00–99
- **One accumulator register**, used for computation and storing intermediate results
- The ability to understand only SML, its native machine language

Every SML instruction (or data value) is a signed four-digit integer (range -9999 to +9999). The first two digits of an instruction are the **operation code**, and the last two digits are the **operand** — the memory location the operation acts on.

## Supported Operation Codes

| Code | Name | Description |
|------|------|-------------|
| 10 | READ | Read a word from the keyboard into a specific memory location |
| 11 | WRITE | Write a word from a specific memory location to the screen |
| 20 | LOAD | Load a word from a specific memory location into the accumulator |
| 21 | STORE | Store a word from the accumulator into a specific memory location |
| 30 | ADD | Add a word from a specific memory location to the accumulator |
| 31 | SUBTRACT | Subtract a word from a specific memory location from the accumulator |
| 40 | BRANCH | Unconditionally branch to a specific location |
| 41 | BRANCHNEG | Branch to a specific location if the accumulator is negative |
| 42 | BRANCHZERO | Branch to a specific location if the accumulator is zero |
| 43 | HALT | Terminate program execution |

## Requirements

- Java JDK 8 or later

## Build & Run

```bash
javac Simpletron.java
java Simpletron
```

## Usage

When the simulator starts, it prompts for one instruction or data word at a time:

```
*** Welcome to Simpletron! ***
*** Please enter your program one instruction ***
*** (or data word) at a time. I will display ***
*** the location number and a question mark (?). ***
*** You then type the word for that location. ***
*** Type -99999 to stop entering your program. ***
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

Type `-99999` to finish entering the program; execution begins automatically.

## Sample Program: Read Two Numbers and Display the Larger

| Location | Instruction | Description |
|----------|-------------|-------------|
| 00 | +1009 | Read A |
| 01 | +1010 | Read B |
| 02 | +2009 | Load A |
| 03 | +3110 | Subtract B |
| 04 | +4107 | Branch negative to 07 |
| 05 | +1109 | Write A |
| 06 | +4300 | Halt |
| 07 | +1110 | Write B |
| 08 | +4300 | Halt |
| 09 | +0000 | Variable A |
| 10 | +0000 | Variable B |

## Error Handling

- **Loading phase**: values outside the -9999 to +9999 range prompt the user to re-enter.
- **Execution phase**: the following conditions trigger an error message and a full memory dump before terminating:
  - Accumulator overflow
  - Invalid operation code

## Sample Memory Dump

On normal termination (HALT) or a fatal error, all registers and memory contents are printed:

```
REGISTERS:
Accumulator          +0000
instructionCounter   00
instructionRegister  +0000
operationCode        00
operand              00

MEMORY:
       0     1     2     3     4     5     6     7     8     9
 0 +0000+0000+0000+0000+0000+0000+0000+0000+0000+0000
10 +0000+0000+0000+0000+0000+0000+0000+0000+0000+0000
...
```

## Project Structure

```
.
├── Simpletron.java   # Simulator main program
└── README.md
```

## Related Exercises

This project corresponds to Chapter 7 exercises from Deitel's *Java How to Program*:

- **7.36**: Write three SML assembly programs (sum of positive numbers, average of seven numbers, find the largest number)
- **7.37**: This project — build a Simpletron simulator to run the SML programs above

## License

MIT

---

# Simpletron 模擬器

一個以 Java 實作的簡易電腦模擬器（Simpletron），可讀取並執行 SML（Simpletron Machine Language）機器語言程式。此專案為 *Java How to Program*（Deitel）習題 **7.37（Computer Simulator）** 的實作。

## 專案簡介

Simpletron 是一台假想的電腦，擁有：

- **100 個字組（word）的記憶體**，位置編號 00～99
- **一個累加器（accumulator）**，用於運算與儲存中間結果
- 僅能理解 SML 這種機器語言

每個 SML 指令（或資料）都是一個帶符號的四位數整數（範圍 -9999 ～ +9999）。指令的前兩碼為**運算碼（operation code）**，後兩碼為**運算元（operand）**，也就是該指令所操作的記憶體位置。

## 支援的運算碼（Operation Codes）

| 運算碼 | 名稱 | 說明 |
|--------|------|------|
| 10 | READ | 從鍵盤讀取一個數字，存入指定記憶體位置 |
| 11 | WRITE | 將指定記憶體位置的值輸出到螢幕 |
| 20 | LOAD | 將指定記憶體位置的值載入累加器 |
| 21 | STORE | 將累加器的值存入指定記憶體位置 |
| 30 | ADD | 將指定記憶體位置的值加到累加器 |
| 31 | SUBTRACT | 將累加器減去指定記憶體位置的值 |
| 40 | BRANCH | 無條件跳躍到指定位置 |
| 41 | BRANCHNEG | 若累加器為負數，跳躍到指定位置 |
| 42 | BRANCHZERO | 若累加器為零，跳躍到指定位置 |
| 43 | HALT | 終止程式執行 |

## 環境需求

- Java JDK 8 或以上版本

## 如何編譯與執行

```bash
javac Simpletron.java
java Simpletron
```

## 使用方式

程式啟動後會逐一詢問每個記憶體位置要輸入的指令或資料：

```
*** Welcome to Simpletron! ***
*** Please enter your program one instruction ***
*** (or data word) at a time. I will display ***
*** the location number and a question mark (?). ***
*** You then type the word for that location. ***
*** Type -99999 to stop entering your program. ***
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

輸入 `-99999` 表示程式輸入結束，模擬器會自動開始執行。

## 範例程式：讀取兩數並顯示較大值

| 位置 | 指令 | 說明 |
|------|------|------|
| 00 | +1009 | Read A |
| 01 | +1010 | Read B |
| 02 | +2009 | Load A |
| 03 | +3110 | Subtract B |
| 04 | +4107 | Branch negative to 07 |
| 05 | +1109 | Write A |
| 06 | +4300 | Halt |
| 07 | +1110 | Write B |
| 08 | +4300 | Halt |
| 09 | +0000 | Variable A |
| 10 | +0000 | Variable B |

## 錯誤處理

- **載入階段**：輸入值若超出 -9999 ～ +9999 範圍，會要求重新輸入。
- **執行階段**：發生以下情況會顯示錯誤訊息並印出完整記憶體傾印（dump）後終止程式：
  - 累加器溢位（accumulator overflow）
  - 無效的運算碼（invalid operation code）

## 記憶體傾印（Dump）範例

程式正常結束（HALT）或發生嚴重錯誤時，會輸出所有暫存器與記憶體內容：

```
REGISTERS:
Accumulator          +0000
instructionCounter   00
instructionRegister  +0000
operationCode        00
operand              00

MEMORY:
       0     1     2     3     4     5     6     7     8     9
 0 +0000+0000+0000+0000+0000+0000+0000+0000+0000+0000
10 +0000+0000+0000+0000+0000+0000+0000+0000+0000+0000
...
```

## 專案結構

```
.
├── Simpletron.java   # 模擬器主程式
└── README.md
```

## 延伸閱讀 / 相關習題

本專案對應 Deitel《Java How to Program》第 7 章習題：

- **7.36**：撰寫三支 SML 組合語言程式（讀取正整數總和、計算平均、找最大值）
- **7.37**：本專案 — 建立 Simpletron 模擬器，以執行上述 SML 程式

## License

MIT
