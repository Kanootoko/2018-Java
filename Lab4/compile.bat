mkdir bin 2> nul
javac -cp ./src;./libs/sqlite-jdbc-3.7.2.jar -d ./bin -encoding UTF-8 ./src/Main.java || pause && exit 0