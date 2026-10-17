/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;



/**
 *
 * @author sandi
 */
public class Message {
    //Attributes
    public String messageID;
    public String messageNumber;
    public String recipient;
    public String messageText; 
    public String messageHash; 
    public String flag;
    
    // Keeps a running total of sent messages across the whole app run
    public static int totalMessagesSent = 0;

    // Stores all sent messages for JSON writing
    public static final StringBuilder jsonBuffer = new StringBuilder();

//constructor
public Message(String messageID, String messageNumber, String recipient, String messageText, String messageHash, String flag){
    this.messageID = messageID;
    this.messageNumber = messageNumber;
    this.recipient = recipient;
    this.messageText = messageText;
    this.messageHash = messageHash;
    this.flag = flag;
}

}