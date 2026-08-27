import java.util.Scanner;

public class HugeInteger {
    // 宣告一個 40 元素的整數陣列來儲存數字
    private int[] digits = new int[40];
    // 記錄數字的長度
    private int length = 0;

    // 初始化陣列為 0
    public HugeInteger() {
        for (int i = 0; i < 40; i++) {
            digits[i] = 0;
        }
    }

    // parse ：將輸入的字串轉換為 HugeInteger
    public void parse(String number) {
        // 清除舊數據
        for (int i = 0; i < 40; i++) {
            digits[i] = 0;
        }
        // 取得字串長度
        length = number.length();
        // 檢查輸入是否超過 40 位
        if (length > 40) {
            length = 40;
        }
        // 從字串右邊，將每個字元轉為數字存入陣列
        for (int i = 0; i < length; i++) {
            // 將字元轉為對應的整數
            digits[39 - i] = number.charAt(length - 1 - i) - '0';
        }
    }

    // toString：將 HugeInteger 轉為字串
    public String toString() {
        // 如果是 0，直接返回 "0"
        if (isZero()) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        // 是否遇到非零數字
        boolean started = false;
        // 從陣列左邊檢查
        for (int i = 0; i < 40; i++) {
            if (digits[i] != 0) {
                started = true;
            }
            if (started) {
                result.append(digits[i]);
            }
        }
        return result.toString();
    }

    // add ：將兩個 HugeInteger 相加
    public HugeInteger add(HugeInteger other) {
        HugeInteger result = new HugeInteger();
        int carry = 0; // 進位
        // 從低位相加
        for (int i = 39; i >= 0; i--) {
            int sum = digits[i] + other.digits[i] + carry;
            result.digits[i] = sum % 10; // 儲存個位數
            carry = sum / 10; // 計算進位
        }
        // 更新長度
        result.length = Math.max(this.length, other.length);
        if (carry > 0 && result.length < 40) {
            result.digits[39 - result.length] = carry;
            result.length++;
        }
        return result;
    }

    // subtract ：將兩個 HugeInteger 相減（this - other）
    public HugeInteger subtract(HugeInteger other) {
        HugeInteger result = new HugeInteger();
        int borrow = 0; // 借位
        // 檢查是否可以相減（this 必須大於等於 other）
        if (isLessThan(other)) {
            return result; // 如果 this < other，返回 0
        }
        // 從低位相減
        for (int i = 39; i >= 0; i--) {
            int diff = digits[i] - other.digits[i] - borrow;
            if (diff < 0) {
                diff += 10; // 借位
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.digits[i] = diff;
        }
        // 更新長度
        result.length = this.length;
        // 移除前0
        while (result.length > 1 && result.digits[40 - result.length] == 0) {
            result.length--;
        }
        return result;
    }

    // multiply ：將兩個 HugeInteger 相乘
    public HugeInteger multiply(HugeInteger other) {
        HugeInteger result = new HugeInteger();
        // 如果任一數字為 0，結果為 0
        if (this.isZero() || other.isZero()) {
            return result;
        }
        // 乘法邏輯，逐位相乘累加
        for (int i = 39; i >= 40 - this.length; i--) {
            int carry = 0;
            HugeInteger temp = new HugeInteger();
            int tempIndex = i;
            // 對 other 的每一位進行乘法
            for (int j = 39; j >= 40 - other.length; j--) {
                int product = this.digits[i] * other.digits[j] + carry;
                temp.digits[tempIndex] = product % 10;
                carry = product / 10;
                tempIndex--;
            }
            if (carry > 0 && tempIndex >= 0) {
                temp.digits[tempIndex] = carry;
            }
            // 將臨時結果加到最終結果
            result = result.add(temp);
        }
        // 更新長度
        result.length = this.length + other.length;
        if (result.length > 40) {
            result.length = 40;
        }
        // 移除前 0
        while (result.length > 1 && result.digits[40 - result.length] == 0) {
            result.length--;
        }
        return result;
    }

    // divide ：將兩個 HugeInteger 相除（this ÷ other），返回商
    public HugeInteger divide(HugeInteger other) {
        HugeInteger result = new HugeInteger();
        // 如果除數為 0，返回 0
        if (other.isZero()) {
            return result;
        }
        // 如果被除數小於除數，商為 0
        if (this.isLessThan(other)) {
            return result;
        }
        HugeInteger dividend = new HugeInteger();
        dividend.length = this.length;
        System.arraycopy(this.digits, 0, dividend.digits, 0, 40); // 複製被除數
        HugeInteger quotient = new HugeInteger();
        // 除法
        while (dividend.isGreaterThanOrEqualTo(other)) {
            dividend = dividend.subtract(other);
            // 創建一個表示 1 的 HugeInteger 物件
            HugeInteger one = new HugeInteger();
            one.parse("1");
            quotient = quotient.add(one); // 將 quotient 加上 1
        }
        result = quotient;
        // 更新長度
        result.length = quotient.length;
        return result;
    }

    // remainder ：計算 this ÷ other 的餘數
    public HugeInteger remainder(HugeInteger other) {
        // 如果除數為 0，返回 0
        if (other.isZero()) {
            return new HugeInteger();
        }
        // 如果被除數小於除數，餘數為被除數本身
        if (this.isLessThan(other)) {
            HugeInteger result = new HugeInteger();
            result.length = this.length;
            System.arraycopy(this.digits, 0, result.digits, 0, 40);
            return result;
        }
        HugeInteger dividend = new HugeInteger();
        dividend.length = this.length;
        System.arraycopy(this.digits, 0, dividend.digits, 0, 40); // 複製被除數
        // 模擬除法，直到無法再減
        while (dividend.isGreaterThanOrEqualTo(other)) {
            dividend = dividend.subtract(other);
        }
        return dividend;
    }

    // isEqualTo ：檢查兩個 HugeInteger 是否相等
    public boolean isEqualTo(HugeInteger other) {
        for (int i = 0; i < 40; i++) {
            if (digits[i] != other.digits[i]) {
                return false;
            }
        }
        return true;
    }

    // isNotEqualTo ：檢查兩個 HugeInteger 是否不相等
    public boolean isNotEqualTo(HugeInteger other) {
        return !isEqualTo(other);
    }

    // isGreaterThan ：檢查 this 是否大於 other
    public boolean isGreaterThan(HugeInteger other) {
        // 先比較長度
        if (this.length > other.length) {
            return true;
        }
        if (this.length < other.length) {
            return false;
        }
        // 長度相同，逐位比較
        for (int i = 40 - length; i < 40; i++) {
            if (digits[i] > other.digits[i]) {
                return true;
            } else if (digits[i] < other.digits[i]) {
                return false;
            }
        }
        return false; // 相等
    }

    // isLessThan ：檢查 this 是否小於 other
    public boolean isLessThan(HugeInteger other) {
        return !isGreaterThan(other) && !isEqualTo(other);
    }

    // isGreaterThanOrEqualTo ：檢查 this 是否大於等於 other
    public boolean isGreaterThanOrEqualTo(HugeInteger other) {
        return isGreaterThan(other) || isEqualTo(other);
    }

    // isLessThanOrEqualTo ：檢查 this 是否小於等於 other
    public boolean isLessThanOrEqualTo(HugeInteger other) {
        return !isGreaterThan(other);
    }

    // isZero ：檢查 HugeInteger 是否為 0
    public boolean isZero() {
        for (int i = 0; i < 40; i++) {
            if (digits[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // 因為不知道要寫展示好的還是提供測試所以都寫了。展示測試並新增使用者輸入測試
    public static void main(String[] args) {
        // === 展示部分：使用數據測試所有功能 ===
        System.out.println("=== Demonstrating HugeInteger functionality ===");
        // 創建兩個 HugeInteger 物件
        HugeInteger numberA = new HugeInteger();
        HugeInteger numberB = new HugeInteger();

        // 測試
        numberA.parse("409110035");
        numberB.parse("123456789");

        // 測試 toString
        System.out.println("numberA = " + numberA.toString()); //  409110035
        System.out.println("numberB = " + numberB.toString()); //  123456789

        // 測試加法
        HugeInteger sum = numberA.add(numberB);
        System.out.println("numberA + numberB = " + sum.toString()); //  532566824

        // 測試減法
        HugeInteger diff = numberA.subtract(numberB);
        System.out.println("numberA - numberB = " + diff.toString()); //  285653246

        // 測試乘法
        HugeInteger product = numberA.multiply(numberB);
        System.out.println("numberA * numberB = " + product.toString()); //  505325973390119415

        // 測試除法
        HugeInteger quotient = numberA.divide(numberB);
        System.out.println("numberA / numberB = " + quotient.toString()); //  3

        // 測試餘數
        HugeInteger remainder = numberA.remainder(numberB);
        System.out.println("numberA % numberB = " + remainder.toString()); //  37026668

        // 測試比較
        System.out.printf("numberA == numberB: %b%n", numberA.isEqualTo(numberB)); // false
        System.out.printf("numberA != numberB: %b%n", numberA.isNotEqualTo(numberB)); // true
        System.out.printf("numberA > numberB: %b%n", numberA.isGreaterThan(numberB)); // true
        System.out.printf("numberA < numberB: %b%n", numberA.isLessThan(numberB)); // false
        System.out.printf("numberA >= numberB: %b%n", numberA.isGreaterThanOrEqualTo(numberB)); // true
        System.out.printf("numberA <= numberB: %b%n", numberA.isLessThanOrEqualTo(numberB)); // false

        // 測試 isZero
        HugeInteger zero = new HugeInteger();
        System.out.printf("zero isZero: %b%n", zero.isZero()); // true
        System.out.printf("numberA isZero: %b%n", numberA.isZero());
        System.out.printf("numberB isZero: %b%n", numberB.isZero());


        // === 使用者輸入部分：讓使用者輸入兩個數進行測試 ===
        System.out.println("\n=== User Input Testing ===");
        Scanner scanner = new Scanner(System.in);

        // 創建兩個新的 HugeInteger 物件
        HugeInteger userNumberA = new HugeInteger();
        HugeInteger userNumberB = new HugeInteger();

        // 提示使用者輸入第一個數字
        System.out.println("Please enter the first large number (up to 40 digits)：");
        String input1 = scanner.nextLine().trim();
        // 驗證合法（僅包含數字且不超過 40 位）
        if (!input1.matches("\\d{1,40}")) {
            System.out.println("不可以這樣！Please enter a number between 1 and 40 digits.");
            scanner.close();
            return;
        }
        userNumberA.parse(input1);

        // 提示使用者輸入第二個數字
        System.out.println("Please enter the second large number (up to 40 digits)：");
        String input2 = scanner.nextLine().trim();
        // 驗證合法
        if (!input2.matches("\\d{1,40}")) {
            System.out.println("不可以這樣！Please enter a number between 1 and 40 digits.");
            scanner.close();
            return;
        }
        userNumberB.parse(input2);

        // 顯示使用者輸入的數字
        System.out.println("You entered numberA = " + userNumberA.toString());
        System.out.println("You entered numberB = " + userNumberB.toString());

        // 測試加法
        HugeInteger userSum = userNumberA.add(userNumberB);
        System.out.println("numberA + numberB = " + userSum.toString());

        // 測試減法
        HugeInteger userDiff = userNumberA.subtract(userNumberB);
        System.out.println("numberA - numberB = " + userDiff.toString());

        // 測試乘法
        HugeInteger userProduct = userNumberA.multiply(userNumberB);
        System.out.println("numberA * numberB = " + userProduct.toString());

        // 測試除法
        HugeInteger userQuotient = userNumberA.divide(userNumberB);
        System.out.println("numberA / numberB = " + userQuotient.toString());

        // 測試餘數
        HugeInteger userRemainder = userNumberA.remainder(userNumberB);
        System.out.println("numberA % numberB = " + userRemainder.toString());

        // 測試比較
        System.out.printf("numberA == numberB: %b%n", userNumberA.isEqualTo(userNumberB));
        System.out.printf("numberA != numberB: %b%n", userNumberA.isNotEqualTo(userNumberB));
        System.out.printf("numberA > numberB: %b%n", userNumberA.isGreaterThan(userNumberB));
        System.out.printf("numberA < numberB: %b%n", userNumberA.isLessThan(userNumberB));
        System.out.printf("numberA >= numberB: %b%n", userNumberA.isGreaterThanOrEqualTo(userNumberB));
        System.out.printf("numberA <= numberB: %b%n", userNumberA.isLessThanOrEqualTo(userNumberB));

        // 測試 isZero
        System.out.printf("numberA isZero: %b%n", userNumberA.isZero());
        System.out.printf("numberB isZero: %b%n", userNumberB.isZero());

        // 關閉 Scanner
        scanner.close();
    }
}

