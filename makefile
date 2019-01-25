 
#308414556
#nitai halle

compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*/*.java src/*.java
run:
	java -cp biuoop-1.4.jar:bin:resources Ass6Game 
jar:
	jar cfm space-invaders.jar MANIFEST.MF -C bin . -C resources .
bin:
	mkdir bin
