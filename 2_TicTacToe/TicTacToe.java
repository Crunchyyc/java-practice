import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// 表示棋盤格子的狀態
enum CellState {
    X, O, EMPTY
}

public class TicTacToe {
    // 3x3二維陣列作為棋盤
    private CellState[][] gameBoard;
    // 當前玩家（1 代表 X，2 代表 O）
    private int currentPlayer;
    // Scanner 用於接收玩家輸入
    private Scanner input;
    // Random 用於電腦玩家的隨機移動
    private Random random;
    // 是否與電腦對戰
    private boolean vsComputer;
    // 電腦玩家的符號（X 或 O）
    private CellState computerSymbol;

    // 初始化棋盤和遊戲狀態
    public TicTacToe() {
        gameBoard = new CellState[3][3]; // 創建 3x3 棋盤
        input = new Scanner(System.in);
        random = new Random();
        currentPlayer = 1; // 預設玩家1（X）先開始
        vsComputer = false; // 預設為雙人模式

        // 初始化棋盤所有格子為 EMPTY
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gameBoard[row][col] = CellState.EMPTY;
            }
        }
    }

    // 開始遊戲
    public void startGame() {
        System.out.println("This is 409110035's Tic-Tac-Toe!");
        
        // 詢問是否與電腦對戰
        System.out.println("Play against computer? (y/n): ");
        vsComputer = input.next().toLowerCase().charAt(0) == 'y';

        // 詢問玩家1是否先手（X），適用於雙人或人機模式
        System.out.println("Does Player 1(it's you) want to go first (X)? (y/n): ");
        boolean playerFirst = input.next().toLowerCase().charAt(0) == 'y';

        if (vsComputer) {
            // 人機模式：根據玩家是否先手，設定電腦符號和起始玩家
            computerSymbol = playerFirst ? CellState.O : CellState.X;
            currentPlayer = 1; // X 總是先手（若玩家後手，則電腦為 X）
            System.out.println("Player is " + (playerFirst ? "X (first)" : "O (second)") + 
                               ", Computer is " + computerSymbol + (playerFirst ? " (second)" : " (first)"));
        } else {
            // 雙人模式：根據選擇設定起始玩家
            currentPlayer = playerFirst ? 1 : 2;
            System.out.println("Player 1 is " + (playerFirst ? "X (first)" : "O (second)") + 
                               ", Player 2 is " + (playerFirst ? "O (second)" : "X (first)"));
        }

        // 遊戲迴圈，直到遊戲結束
        while (true) {
            printBoard(); // 顯示當前棋盤
            if (vsComputer && currentPlayer == (computerSymbol == CellState.X ? 1 : 2)) {
                computerMove(); // 電腦移動
            } else {
                playerMove(); // 玩家移動
            }

            // 檢查是否有玩家獲勝
            if (isWinner()) {
                printBoard();
                String winner = (currentPlayer == 1 ? "X" : "O");
                if (vsComputer && computerSymbol.toString().equals(winner)) {
                    System.out.println("Computer wins!");
                } else {
                    System.out.println("Player " + (vsComputer ? (playerFirst ? "X" : "O") : winner) + " wins!");
                }
                break;
            }

            // 檢查是否平局
            if (isDraw()) {
                printBoard();
                System.out.println("The game is a draw!");
                break;
            }

            // 切換玩家
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
        input.close(); // 關閉輸入
    }

    // 顯示棋盤
    private void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                // 根據格子狀態顯示 X, O 或空格
                String symbol = gameBoard[row][col] == CellState.EMPTY ? " " : gameBoard[row][col].toString();
                System.out.print(symbol + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 處理玩家的移動
    private void playerMove() {
        int row, col;
        while (true) {
            System.out.println("Player " + (currentPlayer == 1 ? "X" : "O") + ", enter row (0-2) and column (0-2): ");
            row = input.nextInt();
            col = input.nextInt();

            // 檢查輸入是否合法
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && gameBoard[row][col] == CellState.EMPTY) {
                // 放置 X 或 O
                gameBoard[row][col] = (currentPlayer == 1) ? CellState.X : CellState.O;
                break;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    // 處理電腦的移動
    private void computerMove() {
        System.out.println("Computer (" + computerSymbol + ") is thinking...");
        int[] move = findBestMove();
        int row = move[0], col = move[1];
        gameBoard[row][col] = computerSymbol;
        System.out.println("Computer places " + computerSymbol + " at row " + row + ", column " + col);
    }

    // 尋找電腦的最佳移動
    private int[] findBestMove() {
        // 1：檢查是否能贏
        int[] winMove = checkWinningMove(computerSymbol);
        if (winMove != null) return winMove;

        // 2：檢查是否需要阻擋對方贏
        int[] blockMove = checkWinningMove(computerSymbol == CellState.X ? CellState.O : CellState.X);
        if (blockMove != null) return blockMove;

        // 3：優先選擇中心
        if (gameBoard[1][1] == CellState.EMPTY) return new int[]{1, 1};

        // 4：隨機選擇空位
        ArrayList<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == CellState.EMPTY) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }

    // 檢查是否有贏棋或阻擋對方的移動
    private int[] checkWinningMove(CellState symbol) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == CellState.EMPTY) {
                    // 模擬放置符號
                    gameBoard[row][col] = symbol;
                    boolean canWin = isWinner();
                    gameBoard[row][col] = CellState.EMPTY; // 恢復棋盤
                    if (canWin) return new int[]{row, col};
                }
            }
        }
        return null;
    }

    // 檢查是否有玩家獲勝
    private boolean isWinner() {
        // 檢查row
        for (int row = 0; row < 3; row++) {
            if (gameBoard[row][0] != CellState.EMPTY &&
                gameBoard[row][0] == gameBoard[row][1] &&
                gameBoard[row][1] == gameBoard[row][2]) {
                return true;
            }
        }

        // 檢查column
        for (int col = 0; col < 3; col++) {
            if (gameBoard[0][col] != CellState.EMPTY &&
                gameBoard[0][col] == gameBoard[1][col] &&
                gameBoard[1][col] == gameBoard[2][col]) {
                return true;
            }
        }

        // 檢查對角線
        if (gameBoard[0][0] != CellState.EMPTY &&
            gameBoard[0][0] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[2][2]) {
            return true;
        }
        if (gameBoard[0][2] != CellState.EMPTY &&
            gameBoard[0][2] == gameBoard[1][1] &&
            gameBoard[1][1] == gameBoard[2][0]) {
            return true;
        }

        return false;
    }

    // 檢查是否平局（棋盤已滿且無人獲勝）
    private boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == CellState.EMPTY) {
                    return false; // 還有空位，不是平局
                }
            }
        }
        return true; // 棋盤已滿，平局
    }

    // 主函數，啟動遊戲
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }
}
