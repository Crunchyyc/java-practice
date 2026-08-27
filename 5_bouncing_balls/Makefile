# 定義 JavaFX SDK 路徑
JAVAFX_PATH = "C:/Program Files/Java/javafx-sdk-24.0.1/lib"

# 編譯和運行目標
all: BouncingBalls

BouncingBalls: src/BouncingBalls.java
	# 編譯程式，輸出到 bin 資料夾
	javac --module-path $(JAVAFX_PATH) --add-modules javafx.controls,javafx.graphics -d bin src/BouncingBalls.java
	# 運行程式，從 bin 資料夾執行
	java --module-path $(JAVAFX_PATH) --add-modules javafx.controls,javafx.graphics -cp bin BouncingBalls

clean:
	rm -rf bin/*