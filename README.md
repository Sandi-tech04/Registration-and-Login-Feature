# Registration, Login & QuickChat Feature – Part 1 & Part 2

## Project Description

This project is a Java-based Registration, Login, and QuickChat messaging system developed for Part 1 and Part 2 of the programming assignment.

The system first allows a user to register by entering their first name, last name, username, password, and South African cellphone number. The program validates the information entered by the user according to the required formatting rules. 

Once logged in, the user is taken to the **QuickChat** application. QuickChat allows the user to send, store, or disregard messages. It includes automatic message ID generation, message hashing, character limits, and stores "stored" messages to a local JSON file.

JUnit 5 tests are included to test the registration, login, and messaging functions, and these tests are automated using GitHub Actions CI/CD.

## Features

The program includes the following features:

**Part 1: Registration & Login**
- User registration (First name, last name, username, password, cellphone number)
- Username validation (must contain `_` and be ≤ 5 characters)
- Password complexity validation (≥ 8 chars, capital letter, number, special character)
- South African cellphone number validation (`+27...`)
- User login with success/failure status messages

**Part 2: QuickChat Application**
- "Welcome to QuickChat" menu with a while-loop that runs until the user quits
- Ability for the user to define how many messages they want to enter
- Automatic 10-digit Message ID generation
- Automatic Message Hash generation (e.g., `00:0:HITONIGHT`)
- 250-character limit validation for messages
- Options to Send, Store, or Disregard a message
- Automatic saving of "Stored" messages to a `messages.json` file
- Automated CI/CD pipeline using GitHub Actions

## Validation Requirements

### Username
The username must:
* Contain an underscore (`_`)
* Be no more than 5 characters long
* *Example:* `kyl_1`

### Password
The password must:
* Contain at least 8 characters
* Contain at least one uppercase letter
* Contain at least one number
* Contain at least one special character
* *Example:* `Ch&&sec@ke99!`

### Cellphone Number
The cellphone number must:
* Use the South African international code `+27`
* Contain the required number of digits
* *Example:* `+27838968976`

### Message Hash
The message hash is auto-generated and contains:
* The first two digits of the Message ID
* A colon (`:`)
* The message number
* A colon (`:`)
* The first word and last word of the message (ALL CAPS, no spaces)
* *Example:* `00:0:HITONIGHT`

## Project Structure

The project contains the following main classes:

### Main.java
Controls the flow of the program. It handles the registration and login process, then displays the QuickChat menu and captures the user's menu choices (Send, Coming Soon, Quit).

### Login.java
Stores the user's information and contains the methods responsible for username validation, password validation, cellphone number validation, user registration, and login status.

### Message.java
Handles all QuickChat message logic. It generates Message IDs, creates Message Hashes, checks message lengths, and manages the Send/Store/Disregard logic. It also handles writing the stored messages to a JSON file.

### LoginTest.java & MessageTest.java
These classes use JUnit 5 to test the functionality of the `Login` and `Message` classes. They test both success and failure scenarios for validation, login, and message limits.

## Technologies Used

* Java
* NetBeans
* Maven
* JUnit 5
* GitHub & GitHub Actions (CI/CD)

## How to Run the Program

1. Open the project in NetBeans.
2. Build the project using Maven.
3. Run the `Main` class.
4. Enter the requested registration information, then log in.
5. Once logged in, enter the number of messages you wish to send.
6. Use the menu to interact with the QuickChat application.

## How to Run the Tests

**Locally in NetBeans:**
1. Open the project in NetBeans.
2. Locate the `LoginTest` or `MessageTest` class in the Test Packages folder.
3. Right-click the test class and select **Test File**.
4. JUnit will execute the tests and show the results.

**Automated Testing (GitHub Actions):**
Every time code is pushed to the `master` branch, GitHub Actions automatically runs all JUnit tests. You can view the test results by clicking the **Actions** tab on the GitHub repository.

## Testing

JUnit 5 is used to verify that the registration, login, and messaging functionality produces the expected results.

`assertEquals` is used to compare expected and actual messages, while `assertTrue` and `assertFalse` are used when testing Boolean results.

## Author

**Sandisile**