/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;

import java.util.Scanner;

/**
 *
 * @author sandi
 */
public class Main {

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
        
     System.out.println("================================");
     System.out.println("Registration");  
     System.out.println("================================");
     
     System.out.println("Enter your first name");
     String firstName = input.nextLine();
     
      System.out.println("Enter your last name");
     String lastName = input.nextLine();
    
     System.out.println("Enter your username");
     String userName = input.nextLine();
     
     System.out.println("Enter your password");
     String password = input.nextLine();
             
     System.out.println("Enter your South African cellphone number");
     String cellPhoneNumber = input.nextLine();
     
     //an instance
     Login Login = new Login (firstName, lastName, userName, password, cellPhoneNumber);
     
     String registrationMessage = Login.RegisterUser(userName, password, cellPhoneNumber);
     
        System.out.println(registrationMessage);
        
     System.out.println("================================");
     System.out.println("Login");  
     System.out.println("================================");
     
     System.out.println("Enter your username");
     String LoginuserName = input.nextLine();
     
     System.out.println("Enter your password");
     String LoginPassword = input.nextLine();
     
     if(!Login.loginUser(LoginuserName, LoginPassword)){
         System.out.println("Username or Password incorrect, please try again");
         input.close();
         return;
     }
        System.out.println(Login.returnLoginStatus());
        
                // QUICKCHAT (Part 2)
        System.out.println("\nWelcome to QuickChat.");

        System.out.print("How many messages do you wish to enter? ");
        int numMessages;
        try { numMessages = Integer.parseInt(input.nextLine().trim()); }
        catch (NumberFormatException e) {
            System.out.println("Invalid number. Exiting.");
            input.close();
            return;
        }

        // WHILE LOOP — runs until user picks Quit (3)
        boolean running = true;
        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            int option;
try {
    option = Integer.parseInt(input.nextLine().trim());
} catch (NumberFormatException e) {
    System.out.println("Please enter a number 1-3.");
    continue;
}

if (option == 1) {
    Message.sendMessages(numMessages, input);
} else if (option == 2) {
    System.out.println("Coming Soon.");
} else if (option == 3) {
    System.out.println("Goodbye!");
    running = false;
} else {
    System.out.println("Invalid option. Please choose 1, 2 or 3.");
}
        }

        input.close();
    }

    }
