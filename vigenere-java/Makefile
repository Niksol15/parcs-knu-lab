all: run

SRCS := src/Bluck.java src/Vigenere.java
JARS := $(SRCS:src/%.java=out/%.jar)

clean:
	rm ${JARS}

out/Bluck.jar: out/parcs.jar src/Bluck.java src/DataToEncrypt.java
	@javac -cp out/parcs.jar src/Bluck.java src/DataToEncrypt.java
	@jar cf out/Bluck.jar -C src Bluck.class -C src DataToEncrypt.class
	@rm -f src/Bluck.class src/DataToEncrypt.class

out/Vigenere.jar: out/parcs.jar src/Vigenere.java src/DataToEncrypt.java
	@javac -cp out/parcs.jar src/Vigenere.java src/DataToEncrypt.java
	@jar cf out/Vigenere.jar -C src Vigenere.class -C src DataToEncrypt.class
	@rm -f src/Vigenere.class src/DataToEncrypt.class

Vigenere: out/Vigenere.jar
	@java -jar out/Vigenere.jar

build: ${JARS}

run: build
	@cd out && @java -cp 'parcs.jar:Bluck.jar' Bluck