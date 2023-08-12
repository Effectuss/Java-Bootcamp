#1. Delete target directory if exist
rm -rf target

#2. Create a target directory
mkdir target

#3. Compile
javac -d ./target src/java/edu/school21/printer/*/*.java

#4 Execute the program
# First argument it's a white pixel, second it's a black pixel and third it's a full path to image
java -classpath target edu.school21.printer.app.Program . 0 /opt/goinfre/englishk/Java-Bootcamp/Day04/ex00/ImagesToChar/it.bmp