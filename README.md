# Registration, Login & QuickChat Feature – Parts 1, 2 & 3

## Project Description

This project is a Java-based Registration, Login, and QuickChat messaging system developed for Parts 1, 2, and 3 of the programming assignment (PoE).

The system first allows a user to register by entering their first name, last name, username, password, and South African cellphone number. The program validates the information entered by the user according to the required formatting rules. 

Once logged in, the user is taken to the **QuickChat** application. QuickChat allows the user to send, store, or disregard messages. It includes automatic message ID generation, message hashing, character limits, and stores "stored" messages to a local JSON file. 

In Part 3, a comprehensive **Stored Messages** menu was added. This feature reads the JSON file back into standard Java arrays (no ArrayLists), allowing users to search, delete, and generate reports based on the stored data. 

JUnit 5 tests are included to test the registration, login, messaging, and array management functions, and these tests are automated using GitHub Actions CI/CD.

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

**Part 3: Stored Messages & Arrays**
- Manual parsing of the `messages.json` file into standard Java arrays (using `BufferedReader` and `String` manipulation, no external libraries)
- **Stored Messages Menu** with the following options:
  - Display the sender and recipient of all stored messages
  - Display the longest stored message
  - Search for a message ID to find the corresponding recipient and message
  - Search for all messages sent/stored to a particular recipient
  - Delete a message using the Message Hash
  - Display a full report (Message Hash, Recipient, Message)
- Strict use of **Parallel Arrays** instead of `ArrayList` to manage Sent, Stored, and Disregarded messages.

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
Controls the flow of the program. It handles the registration and login process, then displays the QuickChat menu and captures the user's menu choices (Send, Coming Soon, Stored Messages, Quit).

### Login.java
Stores the user's information and contains the methods responsible for username validation, password validation, cellphone number validation, user registration, and login status.

### Message.java
Handles all QuickChat message logic. It generates Message IDs, creates Message Hashes, checks message lengths, and manages the Send/Store/Disregard logic. It also handles writing the stored messages to a JSON file.

### MessageManager.java *(New in Part 3)*
Handles the parallel arrays for Sent, Stored, and Disregarded messages. It contains the logic to read the JSON file, search for specific messages, delete messages, and generate the final reporting metrics.

### Test Classes
*   **LoginTest.java**: Tests user validation and login.
*   **MessageTest.java**: Tests message length, hashing, and send/store/discard logic.
*   **MessageManagerTest.java**: Tests array population, searching, deleting, and reporting using the specific test data provided in the brief.

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
6. Use the main menu (1-4) to Send messages, view Coming Soon, access the Stored Messages menu, or Quit.

## How to Run the Tests

**Locally in NetBeans:**
1. Open the project in NetBeans.
2. Locate the `LoginTest`, `MessageTest`, or `MessageManagerTest` classes in the Test Packages folder.
3. Right-click the test class and select **Test File**.
4. JUnit will execute the tests and show the results.

**Automated Testing (GitHub Actions):**
Every time code is pushed to the `master` branch, GitHub Actions automatically runs all JUnit tests via the Maven Surefire plugin. You can view the test results by clicking the **Actions** tab on the GitHub repository.

## Testing

JUnit 5 is used to verify that the registration, login, messaging, and array management functionality produces the expected results.

`assertEquals` is used to compare expected and actual messages and array contents, while `assertTrue` and `assertFalse` are used when testing Boolean results and verifying that deleted items are no longer in the arrays.

## Author

**Sandisile**
