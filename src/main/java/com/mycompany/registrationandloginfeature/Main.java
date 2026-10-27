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

        MessageManager mgr = new MessageManager(numMessages * 2);
        // WHILE LOOP — runs until user picks Quit (3)
        boolean running = true;
        while (running) {
    System.out.println("\n--- QuickChat Menu ---");
    System.out.println("1) Send Messages");
    System.out.println("2) Show recently sent messages");
    System.out.println("3) Stored Messages");     // NEW
    System.out.println("4) Quit");                // Quit is now 4
    System.out.print("Choose an option: ");

            int option;
try {
    option = Integer.parseInt(input.nextLine().trim());
} catch (NumberFormatException e) {
    System.out.println("Please enter a number 1-4.");
    continue;
}

           switch (option) {
        case 1:
            Message.sendMessages(numMessages, input, mgr);   // pass mgr
            break;
        case 2:
            System.out.println("Coming Soon.");
            break;
        case 3:
            // Read the JSON file into the stored array first
            mgr.readJsonFile("messages.json");

            boolean subMenu = true;
            while (subMenu) {
                System.out.println("\n--- Stored Messages ---");
                System.out.println("a) Display sender & recipient");
                System.out.println("b) Display longest message");
                System.out.println("c) Search by Message ID");
                System.out.println("d) Search by Recipient");
                System.out.println("e) Delete by Message Hash");
                System.out.println("f) Display full report");
                System.out.println("g) Back");
                System.out.print("Choose: ");
                String sub = input.nextLine().trim().toLowerCase();

                switch (sub) {
                    case "a": System.out.println(mgr.displaySendersAndRecipients()); break;
                    case "b": System.out.println(mgr.displayLongestMessage());       break;
                    case "c":
                        System.out.print("Enter Message ID: ");
                        System.out.println(mgr.searchByMessageID(input.nextLine().trim()));
                        break;
                    case "d":
                        System.out.print("Enter recipient (+27...): ");
                        System.out.println(mgr.searchByRecipient(input.nextLine().trim()));
                        break;
                    case "e":
                        System.out.print("Enter Message Hash: ");
                        System.out.println(mgr.deleteByHash(input.nextLine().trim()));
                        break;
                    case "f": System.out.println(mgr.displayReport()); break;
                    case "g": subMenu = false; break;
                    default:  System.out.println("Invalid option.");
                }
            }
            break;
        case 4:
            System.out.println("Goodbye!");
            running = false;
            break;
        default:
            System.out.println("Invalid option. Please choose 1-4.");
           }
        }
    }
}
    