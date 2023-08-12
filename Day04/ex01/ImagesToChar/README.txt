#1. Delete target directory if exist
    rm -rf target

#2. Create a target directory
    mkdir target

#3. Compile files to the target directory
    javac -d target src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/ConsoleImagePrinter.java

#4 Copy resources to target
    cp -r src/resources target/.

#5 Create jar:
    jar cvfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

#6 Run program
    java -jar target/images-to-chars-printer.jar . 0
