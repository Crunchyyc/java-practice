# Tic-Tac-Toe

## What I Implemented

- Implemented a 3×3 Tic-Tac-Toe game using a private two-dimensional `CellState[][]` board.
- Defined an `enum` (`X`, `O`, `EMPTY`) to represent the state of every board cell.
- Initialized the entire board to `EMPTY` through the `TicTacToe` constructor.
- Implemented two-player human gameplay with configurable first-player selection.
- Added a computer-player mode, allowing the human player to choose whether to play as X or O.
- Implemented move validation to reject out-of-range coordinates and moves targeting occupied cells.
- Implemented win detection across rows, columns, and both diagonals.
- Implemented draw detection when the board is full without a winner.
- Implemented a rule-based computer opponent that:
  1. Takes a winning move when available.
  2. Blocks the opponent's immediate winning move.
  3. Prioritizes the center square.
  4. Selects randomly from the remaining empty cells.
- Used temporary board-state simulation in `checkWinningMove()` to evaluate potential winning and blocking moves.
- Organized the game into focused methods such as `playerMove()`, `computerMove()`, `findBestMove()`, `isWinner()`, and `isDraw()`.

## Overview

This project is a Java implementation of Tic-Tac-Toe based on Exercise 8.17 from *Java How to Program*. The program supports both human-vs-human and human-vs-computer gameplay.

The game uses a 3×3 board represented by a two-dimensional enum array. Players place X and O alternately, and the program checks the game state after every move to determine whether a player has won or whether the game has ended in a draw.

In computer mode, the AI uses a lightweight rule-based strategy rather than exhaustive game-tree search. It first attempts to win, then blocks the opponent, chooses the center when available, and otherwise makes a random legal move.

---

## Technical Details

### Game Representation

Each board position is represented by the following enum:

```java
enum CellState {
    X, O, EMPTY
}
```

The board is stored as:

```java
private CellState[][] gameBoard;
```

The constructor creates a 3×3 board and initializes every position to `EMPTY`.

### Game Modes

The program supports:

- **Human vs. Human**
  - Two human players take turns.
  - The user can choose whether Player 1 plays first.
- **Human vs. Computer**
  - The user can choose to play first as X or second as O.
  - The computer automatically controls the other symbol.

### Win Detection

The program checks all possible winning patterns:

- 3 horizontal rows
- 3 vertical columns
- 2 diagonals

A position is considered a winning line only when all three cells contain the same non-`EMPTY` symbol.

### Draw Detection

The game is considered a draw when every board position is occupied and no winning condition has been detected.

### Computer Decision Logic

The computer's move selection is implemented in `findBestMove()`.

The priority is:

1. Win immediately if possible.
2. Block the opponent if they can win on the next move.
3. Take the center square.
4. Choose randomly from the remaining empty cells.

### Input Validation

Player moves are validated before being placed on the board.

A move is accepted only when:

- The row is between `0` and `2`.
- The column is between `0` and `2`.
- The selected cell is `EMPTY`.

Invalid moves display an error message and prompt the player to try again.

### Environment Requirements

- Java Development Kit (JDK)
- Java compiler (`javac`)
- Java runtime (`java`)
- A terminal or command-line environment
- `make` is optional if using the provided Makefile

The program uses standard Java libraries:

```java
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
```

No external dependencies are required.

---

## Build & Run

### Compile with Java

The public class is `TicTacToe`, so the source file must be named:

```text
TicTacToe.java
```

Compile:

```bash
javac TicTacToe.java
```

Run:

```bash
java TicTacToe
```

### Using the Makefile

The provided Makefile defines a `TicTacToe` target that compiles and then launches the program:

```bash
make TicTacToe
```

---

## Usage

After launching the program, the user is asked whether to play against the computer:

```text
Play against computer? (y/n):
```

The program then asks whether Player 1 wants to go first:

```text
Does Player 1(it's you) want to go first (X)? (y/n):
```

For a human move, enter the row and column using values from `0` to `2`:

```text
Player X, enter row (0-2) and column (0-2):
```

For example:

```text
1 2
```

places the player's symbol in row `1`, column `2`.

The board is displayed after each turn.

---

## Example

A possible starting board:

```text
-------------
|   |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------
```

After entering:

```text
0 0
```

the board becomes:

```text
-------------
| X |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------
```

In computer mode, the program may respond with:

```text
Computer (O) is thinking...
Computer places O at row 1, column 1
```

The game continues until a player wins or the board becomes full.

---

## Error Handling and Edge Cases

The program handles several common invalid game states:

- Row values outside `0–2`
- Column values outside `0–2`
- Attempts to place a symbol in an occupied cell
- Winning moves detected before draw detection
- Computer moves restricted to empty cells

### Current Input Limitation

The current implementation assumes that row and column input can be parsed as integers using `Scanner.nextInt()`.

For example, entering:

```text
a b
```

would cause an input parsing exception rather than returning to the move prompt.

A future improvement would be to add explicit handling for non-integer input, such as `InputMismatchException`, to make the command-line interface more robust.

---

### Main Components

| Component | Responsibility |
|---|---|
| `CellState` | Represents X, O, or an empty board position |
| `TicTacToe` | Main game class and game-state management |
| `startGame()` | Initializes the game mode and controls the main game loop |
| `printBoard()` | Displays the current board |
| `playerMove()` | Validates and processes human moves |
| `computerMove()` | Processes the computer's move |
| `findBestMove()` | Selects a computer move using heuristic priorities |
| `checkWinningMove()` | Simulates a move to identify winning/blocking opportunities |
| `isWinner()` | Checks rows, columns, and diagonals |
| `isDraw()` | Checks whether the board is full |

---

## Related Information

- **Exercise:** 8.17 — Tic-Tac-Toe
- **Language:** Java
- **Programming concepts:** Classes, enums, arrays, loops, conditionals, methods, input handling, state management, and basic game AI
- **Difficulty extension:** The original exercise also suggests extending the project to a 4×4×4 three-dimensional Tic-Tac-Toe game. That extension is not implemented in this version.

### Implementation Notes

The computer opponent is intentionally lightweight. It uses immediate tactical checks and randomness. This keeps the implementation approachable while still demonstrating decision-making and state simulation.

---

# Tic-Tac-Toe 棋盤

## 實作內容

- 使用私有的 `CellState[][]` 二維陣列實作 3×3 Tic-Tac-Toe 棋盤。
- 使用 `enum`（`X`、`O`、`EMPTY`）表示每個棋盤格子的狀態。
- 在 `TicTacToe` 建構子中將所有棋盤格初始化為 `EMPTY`。
- 實作雙人模式，並允許選擇哪一位玩家先手。
- 加入人機對戰模式，讓玩家可以選擇使用 X 先手或 O 後手。
- 實作輸入驗證，拒絕超出棋盤範圍或已經被佔用的格子。
- 實作橫列、直欄以及兩條對角線的勝負判定。
- 實作平局判定，在棋盤填滿且沒有玩家獲勝時結束遊戲。
- 實作具備基本決策邏輯的電腦玩家：
  1. 如果可以直接獲勝，優先獲勝。
  2. 如果對手下一步可以獲勝，優先阻擋。
  3. 中央位置為空時優先選擇中央。
  4. 其他情況從剩餘空格中隨機選擇。
- 在 `checkWinningMove()` 中透過暫時模擬落子來判斷可以獲勝或需要阻擋的位置。
- 將遊戲功能拆分成 `playerMove()`、`computerMove()`、`findBestMove()`、`isWinner()`、`isDraw()` 等方法，使各項功能更加清楚。

## 專案簡介

本專案是使用 Java 實作的 Tic-Tac-Toe 遊戲，對應 *Java How to Program* 的 Exercise 8.17。程式支援雙人對戰以及人機對戰。

遊戲使用 3×3 的二維 enum 陣列表示棋盤。玩家輪流放置 X 與 O，每次落子後都會檢查遊戲狀態，判斷是否有玩家獲勝或遊戲是否平局。

在人機模式中，電腦並不是使用完整的遊戲樹搜尋，而是採用簡單的規則式策略：優先獲勝、阻擋對手、選擇中央，最後才從剩餘空格中隨機選擇。

---

## 技術細節

### 棋盤與資料表示

每個棋盤格使用以下 enum 表示：

```java
enum CellState {
    X, O, EMPTY
}
```

棋盤本身儲存於：

```java
private CellState[][] gameBoard;
```

建構子建立 3×3 棋盤，並將所有位置初始化為 `EMPTY`。

### 遊戲模式

程式支援：

- **雙人模式**
  - 兩位玩家輪流下棋。
  - 可以選擇 Player 1 是否先手。
- **人機模式**
  - 玩家可以選擇先手使用 X，或後手使用 O。
  - 電腦自動控制另一個符號。

### 勝負判定

程式會檢查所有可能的獲勝方式：

- 3 條橫列
- 3 條直欄
- 2 條對角線

只有當三個位置都是相同且不是 `EMPTY` 的符號時，才會判定為獲勝。

### 平局判定

當所有棋盤位置都已經被填滿，而且前一步沒有判定出勝利時，遊戲會判定為平局。

### 電腦決策邏輯

電腦的移動由 `findBestMove()` 負責。

優先順序為：

1. 如果可以直接獲勝，就獲勝。
2. 如果對手下一步可以獲勝，就阻擋。
3. 如果中央是空的，就選擇中央。
4. 從剩餘空格中隨機選擇。

### 輸入驗證

玩家的落子會在放入棋盤前進行驗證。

只有符合以下條件的移動才會被接受：

- row 必須介於 `0` 和 `2`。
- column 必須介於 `0` 和 `2`。
- 指定的位置必須是 `EMPTY`。

如果輸入無效，程式會顯示錯誤訊息並要求玩家重新輸入。

### 環境需求

- Java Development Kit（JDK）
- Java 編譯器（`javac`）
- Java Runtime（`java`）
- Terminal / Command Line
- 如果要使用提供的 Makefile，則需要 `make`

程式使用的 Java 標準函式庫：

```java
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
```

不需要額外安裝第三方套件。

---

## Build & Run

### 使用 Java 編譯

因為 public class 名稱為 `TicTacToe`，所以程式檔案名稱必須是：

```text
TicTacToe.java
```

編譯：

```bash
javac TicTacToe.java
```

執行：

```bash
java TicTacToe
```

### 使用 Makefile

目前提供的 Makefile 定義了 `TicTacToe` target，會先編譯，再直接啟動程式：

```bash
make TicTacToe
```

---

## Usage

啟動程式後，首先會詢問是否要與電腦對戰：

```text
Play against computer? (y/n):
```

接著會詢問 Player 1 是否要先手：

```text
Does Player 1(it's you) want to go first (X)? (y/n):
```

玩家輸入 row 與 column 時，必須使用 `0` 到 `2`：

```text
Player X, enter row (0-2) and column (0-2):
```

例如輸入：

```text
1 2
```

代表在第 `1` 列、第 `2` 欄放置棋子。

程式會在每一回合顯示目前棋盤。

---

## 範例

遊戲開始時可能顯示：

```text
-------------
|   |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------
```

輸入：

```text
0 0
```

之後棋盤會變成：

```text
-------------
| X |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------
```

在人機模式下，電腦可能接著顯示：

```text
Computer (O) is thinking...
Computer places O at row 1, column 1
```

遊戲會持續進行，直到玩家獲勝或棋盤填滿。

---

## 錯誤處理與邊界情況

目前程式處理了以下常見的無效遊戲狀態：

- row 超出 `0–2`
- column 超出 `0–2`
- 嘗試在已經被佔用的位置落子
- 在判定平局之前先確認是否已經有人獲勝
- 電腦只會選擇空的棋盤位置

### 目前輸入處理的限制

目前的實作假設玩家輸入的 row 與 column 都可以透過 `Scanner.nextInt()` 解析為整數。

例如輸入：

```text
a b
```

會產生輸入解析例外，而不是回到重新輸入的流程。

未來可以加入 `InputMismatchException` 等例外處理，使命令列介面更加穩健。

---

### 主要元件

| 元件 | 功能 |
|---|---|
| `CellState` | 表示 X、O 或空白棋盤位置 |
| `TicTacToe` | 主要遊戲類別與遊戲狀態管理 |
| `startGame()` | 初始化遊戲模式並控制主要遊戲迴圈 |
| `printBoard()` | 顯示目前棋盤 |
| `playerMove()` | 驗證並處理玩家落子 |
| `computerMove()` | 處理電腦落子 |
| `findBestMove()` | 使用啟發式優先順序選擇電腦移動 |
| `checkWinningMove()` | 模擬落子以尋找獲勝或阻擋位置 |
| `isWinner()` | 檢查橫列、直欄與對角線 |
| `isDraw()` | 檢查棋盤是否已填滿 |

---

## 相關資訊

- **Exercise：** 8.17 — Tic-Tac-Toe
- **程式語言：** Java
- **程式設計概念：** Class、Enum、Array、Loop、Conditional、Method、Input Handling、State Management、Basic Game AI
- **延伸挑戰：** 原題目也提出 4×4×4 三維 Tic-Tac-Toe 的延伸挑戰。本版本尚未實作此功能。

### 實作說明

目前的電腦玩家採用輕量級策略，透過立即獲勝、阻擋對手、中央優先與隨機選擇來決定行動。

這樣的設計在保持程式容易理解的同時，也展示了基本的決策邏輯與狀態模擬能力。

---