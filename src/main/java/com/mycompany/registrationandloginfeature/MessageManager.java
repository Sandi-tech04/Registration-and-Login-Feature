package com.mycompany.registrationandloginfeature;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author sandi
 */
public class MessageManager {
    // ---- Parallel arrays (NO ArrayList) ----
    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;
    private String[] recipients;   // parallel to storedMessages (for report / search)
    private String[] senders;      // parallel to storedMessages
    private int sentCount = 0;
    private int disregardCount = 0;
    private int storedCount = 0;

    // Constructor - size set by caller
    public MessageManager(int maxMessages) {
        sentMessages        = new String[maxMessages];
        disregardedMessages = new String[maxMessages];
        storedMessages      = new String[maxMessages];
        messageHashes       = new String[maxMessages * 2]; // hashes for all messages
        messageIDs          = new String[maxMessages * 2];
        recipients          = new String[maxMessages];
        senders             = new String[maxMessages];
    }

    // ---------- POPULATE ARRAYS ----------
    public void addSentMessage(String id, String hash, String recipient, String sender, String text) {
        sentMessages[sentCount] = text;
        messageIDs[sentCount]   = id;
        messageHashes[sentCount] = hash;
        recipients[sentCount]   = recipient;
        senders[sentCount]      = sender;
        sentCount++;
    }

    public void addDisregardedMessage(String text) {
        disregardedMessages[disregardCount++] = text;
    }

    // Adds to stored arrays AND keeps parallel hash/id/recipient/sender
    public void addStoredMessage(String id, String hash, String recipient, String sender, String text) {
        storedMessages[storedCount] = text;
        messageIDs[storedCount]     = id;
        messageHashes[storedCount]  = hash;
        recipients[storedCount]     = recipient;
        senders[storedCount]        = sender;
        storedCount++;
    }

    // ---------- READ JSON INTO STORED ARRAY ----------
    /*
     * Reads messages.json (written in Part 2) into the storedMessages array.
     * Reference: https://www.geeksforgeeks.org/parse-json-java/
     */
    public void readJsonFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) content.append(line).append("\n");
            parseJsonIntoArrays(content.toString());
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
        }
    }

    private void parseJsonIntoArrays(String json) {
        // Simple manual parse (no external lib) - looks for "key": "value" pairs
        String[] blocks = json.split("\\{");
        for (String block : blocks) {
            if (!block.contains("\"messageID\"")) continue;
            String id    = extract(block, "messageID");
            String rec   = extract(block, "recipient");
            String text  = extract(block, "messageText");
            String hash  = extract(block, "messageHash");
            String flag  = extract(block, "flag");
            if (flag != null && flag.equalsIgnoreCase("Stored")) {
                addStoredMessage(id, hash, rec, "Me", text);
            }
        }
    }

    private String extract(String block, String key) {
        String pattern = "\"" + key + "\": \"";
        int start = block.indexOf(pattern);
        if (start == -1) return null;
        start += pattern.length();
        int end = block.indexOf('"', start);
        return end == -1 ? null : block.substring(start, end);
    }

    // ---------- FEATURE a: Display senders & recipients ----------
    public String displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder("Sender | Recipient\n");
        for (int i = 0; i < storedCount; i++) {
            sb.append(senders[i]).append(" | ").append(recipients[i]).append("\n");
        }
        return sb.toString();
    }

    // ---------- FEATURE b: Longest stored message ----------
    public String displayLongestMessage() {
        if (storedCount == 0) return "No stored messages.";
        String longest = storedMessages[0];
        for (int i = 1; i < storedCount; i++) {
            if (storedMessages[i].length() > longest.length()) longest = storedMessages[i];
        }
        return longest;
    }

    // ---------- FEATURE c: Search by message ID ----------
    public String searchByMessageID(String id) {
        for (int i = 0; i < storedCount; i++) {
            if (messageIDs[i].equals(id)) {
                return "Recipient: " + recipients[i] + "\nMessage: " + storedMessages[i];
            }
        }
        return "Message ID not found.";
    }

    // ---------- FEATURE d: Search all messages by recipient ----------
    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storedCount; i++) {
            if (recipients[i].equals(recipient)) {
                sb.append(storedMessages[i]).append("\n");
            }
        }
        return sb.length() == 0 ? "No messages found for that recipient." : sb.toString().trim();
    }

    // ---------- FEATURE e: Delete by message hash ----------
    public String deleteByHash(String hash) {
        for (int i = 0; i < storedCount; i++) {
            if (messageHashes[i].equalsIgnoreCase(hash)) {
                String deleted = storedMessages[i];
                // Shift arrays left
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                    messageIDs[j]     = messageIDs[j + 1];
                    messageHashes[j]  = messageHashes[j + 1];
                    recipients[j]     = recipients[j + 1];
                    senders[j]        = senders[j + 1];
                }
                storedCount--;
                return "Message: \"" + deleted + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // ---------- FEATURE f: Full report ----------
    public String displayReport() {
        StringBuilder sb = new StringBuilder("===== STORED MESSAGES REPORT =====\n");
        sb.append("Message Hash | Recipient | Message\n");
        sb.append("-----------------------------------\n");
        for (int i = 0; i < storedCount; i++) {
            sb.append(messageHashes[i]).append(" | ")
              .append(recipients[i]).append(" | ")
              .append(storedMessages[i]).append("\n");
        }
        return sb.toString();
    }

    // ---------- Getters for tests ----------
    public String[] getSentMessages()        { return sentMessages; }
    public String[] getDisregardedMessages() { return disregardedMessages; }
    public String[] getStoredMessages()      { return storedMessages; }
    public String[] getMessageHashes()       { return messageHashes; }
    public String[] getMessageIDs()          { return messageIDs; }
    public int getSentCount()                { return sentCount; }
    public int getStoredCount()              { return storedCount; }
    public int getDisregardCount()           { return disregardCount; }
}