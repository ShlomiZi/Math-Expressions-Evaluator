# Shlomi

compile: bin
	javac -cp src -d bin src/*.java

run:
	java -cp bin main/ExpressionsTest	
bin:
	mkdir bin