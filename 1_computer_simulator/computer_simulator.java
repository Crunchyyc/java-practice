import java.util.Scanner;

public class computer_simulator {
    // 定義常量
    public static final Scanner input = new Scanner(System.in);
    public static final int READ = 10;
    public static final int WRITE = 11;
    public static final int LOAD = 20;
    public static final int STORE = 21;
    public static final int ADD = 30;
    public static final int SUBTRACT = 31;
    public static final int BRANCH = 40;
    public static final int BRANCHNEG = 41;
    public static final int BRANCHZERO = 42;
    public static final int HALT = 43;
    public static final int SENTINEL = -99999;
    public static final int MAX_MEMORY = 100;

    // 模擬器變數
    private int[] memory = new int[MAX_MEMORY];
    private int accumulator = 0;
    private int operationCode = 0;
    private int operand = 0;
    private int instructionCounter = 0;
    private int instructionRegister = 0;

    public static void main(String[] args) {
        computer_simulator simpletron = new computer_simulator();
        simpletron.Simpletron_Start();
        simpletron.Enter_Instructions();
        simpletron.Run_Instructions();
    }

    public void Simpletron_Start() {
        System.out.println("*** Welcome to Simpletron! ***");
        System.out.println("*** Please enter your program one instruction ***");
        System.out.println("*** (or data word) at a time. I will display ***");
        System.out.println("*** the location number and a question mark (?) ***");
        System.out.println("*** You then type the word for that location. ***");
        System.out.println("*** Type -99999 to stop entering your program. ***");
    }

    public void Enter_Instructions() {
        int instruction = 0;
        instructionCounter = 0;
        do {
            System.out.printf("%02d ? ", instructionCounter);
            instruction = input.nextInt();
            if (instruction >= -9999 && instruction <= 9999) {
                memory[instructionCounter] = instruction;
                instructionCounter++;
            } else if (instruction != SENTINEL) {
                System.out.println("*** Error: Value must be between -9999 and +9999 ***");
            }
        } while (instruction != SENTINEL && instructionCounter < MAX_MEMORY);
        System.out.println("\n*** Program loading completed ***");
        System.out.println("*** Program execution begins ***");
    }

    public void Run_Instructions() {
        instructionCounter = 0;
        while (true) {
            instructionRegister = memory[instructionCounter];
            instructionCounter++;
            operationCode = instructionRegister / 100;
            operand = instructionRegister % 100;

            switch (operationCode) {
                case READ:
                    System.out.print("Enter an integer: ");
                    int value = input.nextInt();
                    if (value < -9999 || value > 9999) {
                        fatalError("Register overflow during READ");
                        return;
                    }
                    memory[operand] = value;
                    break;

                case WRITE:
                    System.out.println(memory[operand]);
                    break;

                case LOAD:
                    accumulator = memory[operand];
                    break;

                case STORE:
                    memory[operand] = accumulator;
                    break;

                case ADD:
                    long resultAdd = (long) accumulator + memory[operand];
                    if (resultAdd < -9999 || resultAdd > 9999) {
                        fatalError("Accumulator overflow during ADD");
                        return;
                    }
                    accumulator = (int) resultAdd;
                    break;

                case SUBTRACT:
                    long resultSub = (long) accumulator - memory[operand];
                    if (resultSub < -9999 || resultSub > 9999) {
                        fatalError("Accumulator overflow during SUBTRACT");
                        return;
                    }
                    accumulator = (int) resultSub;
                    break;

                case BRANCH:
                    instructionCounter = operand;
                    break;

                case BRANCHNEG:
                    if (accumulator < 0) instructionCounter = operand;
                    break;

                case BRANCHZERO:
                    if (accumulator == 0) instructionCounter = operand;
                    break;

                case HALT:
                    System.out.println("*** Simpletron execution terminated ***");
                    Display_Dump();
                    return;

                default:
                    fatalError("Invalid operation code: " + operationCode);
                    return;
            }
        }
    }

    public void Display_Dump() {
        System.out.println("\nREGISTERS:");
        System.out.printf("Accumulator          %+05d%n", accumulator);
        System.out.printf("instructionCounter   %02d%n", instructionCounter);
        System.out.printf("instructionRegister  %+05d%n", instructionRegister);
        System.out.printf("operationCode        %02d%n", operationCode);
        System.out.printf("operand              %02d%n", operand);

        System.out.println("\nMEMORY:");
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%6d ", i);
        }
        System.out.println();
        for (int i = 0; i < MAX_MEMORY; i += 10) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%+06d ", memory[i + j]);
            }
            System.out.println();
        }
    }

    private void fatalError(String message) {
        System.out.println("\n*** " + message + " ***");
        System.out.println("*** Simpletron execution abnormally terminated ***");
        Display_Dump();
    }
}