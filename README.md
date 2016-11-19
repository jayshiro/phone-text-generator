# Phone Text Generator

This is a simple program that prints out the possible conversions of a phone number into text given a dictionary of words. The program can accept a specific dictionary file path as the second paramter if you use the _-d_ as the first parameter when running the jar file; the third parameter, if supplied, will be the phone numbers file containing sample phone numbers on each line. If you choose to not use _-d_ and not supply a specific dictionary file, the first parameter will always be the phone numbers file. When the phone numbers sample file is not given, the program will accept phone numbers as command line inputs. The program will only accept **.txt** files. If the dictionary file is not given, the default dictionary.txt will be used. Both files will only contain one value per line. The program has been coded using [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and as such will require this specific version to be installed in your machine to run. To package and test the project, you will also need to have [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) installed.

To test the project, go into the project folder and execute the following command.
```sh
mvn test
```

To package the project.
```sh
mvn package
```

To run the program, use the following command in the project folder. This example shows how to use the _-d_ parameter along with supplying the dictionary and phone number files in the current path.
```sh
java -jar target/phone-text-generator-1.0-SNAPSHOT.jar -d path/to/dictionary.txt path/to/phoneNumbers.txt
```

To run the program, while using the default dictionary.txt, only supply the path to the phone numbers file.
```sh
java -jar target/phone-text-generator-1.0-SNAPSHOT.jar path/to/phoneNumbers.txt
```

To run the program and expecting it to accept command-line phone number inputs, please see the following example.
```sh
java -jar target/phone-text-generator-1.0-SNAPSHOT.jar -d path/to/dictionary.txt
java -jar target/phone-text-generator-1.0-SNAPSHOT.jar
```

##### Sample Phone Number Input and Output
###### Input
>2255.63  

###### Output
>CALL-ME

The number encoding on the phone the program will use is:

|DIGIT|CHARACTERS|
|---|---|
|2   |A B C   |
|3   |D E F   |
|4   |G H I   |
|5   |J K L   |
|6   |M N O   |
|7   |P Q R S   |
|8   |T U V   |
|9   |W X Y Z   |
