# Advanced Programming Course - Assignment Lab 6
## Student Details
- **Student ID**: 2001391
- **Student Name**: Ahmed Atwa
- **Section Number**: 3
- **Department**: Junior CSE

## Project Description
This project is a simple implementation of an XML Sorter using **Java**. It takes an ARXML file path as input and sorts it based on an inner tag named "SHORT-NAME". The output is a new XML file with the same name as the input file but with a suffix "_mod" before the extension.

It implements custom Exceptions for the following cases:
- The input file has an incorrect extension. (Not .arxml)
- The input file is empty.

## How to run the project
1. Clone the repository to your local machine.
2. Run the following command in the terminal to compile the project:
``` 
javac App.java
```

3. Run the following command in the terminal to run the project:
```
java App <input_file_path>
```

## Testing
An batch file is provided to test the project. It contains 3 test cases:
1. A correct input file.
2. An empty input file.
3. An input file with an incorrect extension.

To run the batch file, run the following command in the terminal:
```
test.bat
```

