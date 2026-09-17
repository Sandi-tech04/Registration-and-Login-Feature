# Registration and Login Feature – Part 1

## Project Description

This project is a Java-based Registration and Login System developed for Part 1 of the programming assignment.

The system allows a user to register by entering their first name, last name, username, password, and South African cellphone number. 

The program validates the information entered by the user according to the required formatting rules.

The system also allows the user to enter their username and password to test whether the login details are correct.

JUnit 5 tests are included to test the different registration, validation, and login functions.

## Features

The program includes the following features:

-User registration
-First name and last name input
-Username validation
-Password complexity validation
-South African cellphone number validation
-User login
-Successful and unsuccessful login testing
-JUnit 5 unit tests

## Validation Requirements

### Username

The username must:

* Contain an underscore (`_`)
* Be no more than 5 characters long

Example of a valid username:

`kyl_1`

### Password

The password must:

* Contain at least 8 characters
* Contain at least one uppercase letter
* Contain at least one number
* Contain at least one special character

Example of a valid password:

`Ch&&sec@ke99!`

### Cellphone Number

The cellphone number must:

* Use the South African international code `+27`
* Contain the required number of digits

Example of a valid cellphone number:

`+27838968976`

## Project Structure

The project contains the following main classes:

### Main.java

The `Main` class controls the flow of the program. It collects the user's registration details, creates a `Login` object, calls the registration method, and collects the user's login details.

### Login.java

The `Login` class stores the user's information and contains the methods responsible for:

* Username validation
* Password validation
* Cellphone number validation
* User registration
* User login
* Returning the login status

### LoginTest.java

The `LoginTest` class uses JUnit 5 to test the functionality of the `Login` class.

The tests include:

* Correctly formatted usernames
* Incorrectly formatted usernames
* Passwords that meet the complexity requirements
* Passwords that do not meet the complexity requirements
* Correctly formatted cellphone numbers
* Incorrectly formatted cellphone numbers
* Successful login
* Failed login
* Individual validation methods

## Technologies Used

* Java
* NetBeans
* Maven
* JUnit 5
* GitHub

## How to Run the Program

1. Open the project in NetBeans.
2. Build the project using Maven.
3. Run the `Main` class.
4. Enter the requested registration information.
5. The program will validate the information entered.
6. Enter the username and password when prompted to test the login functionality.

## How to Run the Tests

1. Open the project in NetBeans.
2. Locate the `LoginTest` class.
3. Right-click the test class.
4. Select **Test File**.
5. JUnit will execute the tests.
6. The test results will indicate which tests passed or failed.

## Testing

JUnit 5 is used to verify that the registration and login functionality produces the expected results.

`assertEquals` is used to compare expected and actual messages, while `assertTrue` and `assertFalse` are used when testing Boolean results.

## Author

**Sandisile**
