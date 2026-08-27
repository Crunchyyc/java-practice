# 定義 Java 編譯器和運行命令
JAVAC = javac
JAVA = java

# 定義所有源檔案
SOURCES = CarbonFootprint.java CarbonFootprintTest.java Car.java Building.java Bicycle.java
MAIN_CLASS = CarbonFootprint409110035.CarbonFootprintTest

# 預設目標：編譯並運行程式
all: compile run

# 編譯目標：編譯所有 Java 源檔案，生成包結構
compile:
	$(JAVAC) -d . $(SOURCES)

# 運行目標：運行主類，指定類路徑
run:
	$(JAVA) -cp . $(MAIN_CLASS)

# 清理目標：刪除生成的類檔案
clean:
	rm -rf CarbonFootprint409110035/*.class