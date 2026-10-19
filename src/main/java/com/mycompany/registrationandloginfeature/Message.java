/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;



/**
 *
 * @author sandi
 */
public class Message {
    //Attributes
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText; 
    private String messageHash; 
    private String flag; // "Sent", "Stored", "Disregard"
    
    // Keeps a running total of sent messages across the whole app run
    private static int totalMessagesSent = 0;

    // Stores all sent messages for JSON writing
    private static final StringBuilder jsonBuffer = new StringBuilder();

//constructor
public Message(String messageID, int messageNumber, String recipient, String messageText){
    this.messageID = messageID;
    this.messageNumber = messageNumber;
    this.recipient = recipient;
    this.messageText = messageText;
    this.messageHash = createMessageHash();
    this.flag = "Pending";
}
public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
}

public String checkRecipientCell() {
        String regex = "^\\+27[0-9]{9}$";
        if (recipient != null && recipient.matches(regex)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}
public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord  = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
}
public String sentMessage(int choice) {
    if (choice == 1) {
        this.flag = "Sent";
        totalMessagesSent++;
        return "Message successfully sent";
    } else if (choice == 2) {
        this.flag = "Stored";
        return "Message successfully stored";
    } else if (choice == 3) {
        this.flag = "Disregard";
        return "Press 0 to delete the message";
    } else {
        return "Invalid option.";
    }
}
public String printMessages() {
        return "Message ID: " + messageID + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: " + recipient + "\n"
             + "Message: " + messageText;
    }
public int returnTotalMessages() {
        return totalMessagesSent;
}
    //storeMessage() — writes the message to JSON
    //Manual JSON build (no external library needed).
    //Reference: https://www.geeksforgeeks.org/parse-json-java/
  
    public void storeMessage() {
        if (this.flag.equals("Stored")) {
            jsonBuffer.append("  {\n")
                .append("    \"messageID\": \"").append(messageID).append("\",\n")
                .append("    \"messageNumber\": ").append(messageNumber).append(",\n")
                .append("    \"recipient\": \"").append(recipient).append("\",\n")
                .append("    \"messageText\": \"")
                .append(messageText.replace("\"", "\\\"")).append("\",\n")
                .append("    \"messageHash\": \"").append(messageHash).append("\",\n")
                .append("    \"flag\": \"").append(flag).append("\"\n")
                .append("  },\n");
        }
    }
      public static void writeJsonFile() {
        String content = "[\n" + jsonBuffer.toString();
        // Remove trailing comma + newline if present
        if (content.endsWith(",\n")) {
            content = content.substring(0, content.length() - 2) + "\n";
        }
        content += "]\n";

        try (FileWriter fw = new FileWriter("messages.json")) {
            fw.write(content);
            System.out.println("Messages successfully stored in messages.json");
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }
public String validateMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }
        int excess = messageText.length() - 250;
        return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
    }
//getters
public String getMessageID()    { return messageID; }
    public int getMessageNumber()   { return messageNumber; }
    public String getRecipient()    { return recipient; }
    public String getMessageText()  { return messageText; }
    public String getMessageHash()  { return messageHash; }
    public String getFlag()         { return flag; }
    
    public static String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) id.append(rand.nextInt(10));
        return id.toString();
    }
    public static void sendMessages(int numMessages, Scanner input) {
        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Message " + i + " of " + numMessages + " ---");

            // ---- Recipient (loop until valid) ----
            String recipient;
            while (true) {
                System.out.print("Enter recipient cell number (+27...): ");
                recipient = input.nextLine().trim();
                Message temp = new Message("0000000000", i, recipient, "x");
                String check = temp.checkRecipientCell();
                System.out.println(check);
                if (check.startsWith("Cell phone number successfully")) break;
            }
            
 // ---- Message text (loop until <= 250 chars) ----
            String text;
            while (true) {
                System.out.print("Enter your message (max 250 chars): ");
                text = input.nextLine();
                if (text.length() > 250) {
                    System.out.println("Please enter a message of less than 250 characters.");
                } else {
                    System.out.println("Message sent");
                    break;
                }
            }
            // ---- Build message ----
            String id = generateMessageID();
            Message msg = new Message(id, i, recipient, text);

            // ---- Send / Store / Disregard ----
            System.out.println("\nChoose an option:");
            System.out.println("1) Send Message");
            System.out.println("2) Store Message to send later");
            System.out.println("3) Disregard Message");
            System.out.print("Enter choice: ");

            int choice;
            try { choice = Integer.parseInt(input.nextLine().trim()); }
            catch (NumberFormatException e) { choice = 0; }

            System.out.println(msg.sentMessage(choice));
            if (msg.getFlag().equals("Sent")) {
                System.out.println("\n===== Message Details =====");
                System.out.println(msg.printMessages());
                System.out.println("===========================\n");
            } else if (msg.getFlag().equals("Stored")) {
                msg.storeMessage();
            }
        }
        System.out.println("Total messages sent: " + totalMessagesSent);
        writeJsonFile();
    }
}
        