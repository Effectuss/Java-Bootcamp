#1. Delete target and lib directories
    rm -rf target && rm -rf lib

#2. Create directories
    mkdir target
    mkdir lib

#3. Download libraries
    curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
    curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -o lib/JCDP-4.0.2.jar

#4 Unzip jar lib
    pushd target
    jar xf ../lib/jcommander-1.82.jar
    jar xf ../lib/JCDP-4.0.2.jar
    rm -rf META-INF && popd

#5 Copy resources
    cp -r src/resources target/.

#6 Compile files
    javac -classpath lib/jcommander-1.82.jar:lib/JCDP-4.0.2.jar -d target src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/ConsoleImagePrinter.java src/java/edu/school21/printer/logic/InputArgs.java

#7 Create jar
    jar cfmv target/images-to-chars-printer.jar src/manifest.txt -C target .

#8 Run the program
    java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
