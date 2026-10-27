/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author sandi
 */
public class MessageManagerTest {
    
    private MessageManager mgr;

    @BeforeEach
    public void setUp() {
        mgr = new MessageManager(10);

        // ---- Populate with Test Data (Messages 1-5) ----
        // Message 1
        mgr.addSentMessage("1111111111", "11:1:DIDCAKE", "+27834557896", "Me",
                "Did you get the cake?");
        // Message 2 (Stored)
        mgr.addStoredMessage("2222222222", "22:2:WHERETIME",
                "+27838884567", "Me",
                "Where are you? You are late! I have asked you to be on time.");
        // Message 3 (Disregarded)
        mgr.addDisregardedMessage("Yohoooo, I am at your gate.");
        // Message 4 (Sent)
        mgr.addSentMessage("0838884567", "08:4:ITDINNER",
                "+27834557896", "Me", "It is dinner time!");
        // Message 5 (Stored)
        mgr.addStoredMessage("5555555555", "55:5:OKLEAVING",
                "+27838884567", "Me", "Ok, I am leaving without you.");
    }

    // ---- Sent Messages array correctly populated ----
    @Test
    public void testSentMessagesArrayPopulated() {
        String[] sent = mgr.getSentMessages();
        assertEquals("Did you get the cake?", sent[0]);
        assertEquals("It is dinner time!", sent[1]);
    }

    // ---- Longest message ----
    @Test
    public void testDisplayLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, mgr.displayLongestMessage());
    }

    // ---- Search by message ID ----
    @Test
    public void testSearchByMessageID() {
        String result = mgr.searchByMessageID("0838884567");
        assertTrue(result.contains("It is dinner time!"));
    }

    // ---- Search all messages by recipient ----
    @Test
    public void testSearchByRecipient() {
        String result = mgr.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    // ---- Delete by hash ----
    @Test
    public void testDeleteByHash() {
        String result = mgr.deleteByHash("22:2:WHERETIME");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time"));
        assertTrue(result.contains("successfully deleted"));
        // Verify it's actually removed
        assertEquals("No messages found for that recipient.",
                mgr.searchByRecipient("+27838884567").contains("Where are") ?
                "still there" : "No messages found for that recipient.");
    }

    // ---- Display report ----
    @Test
    public void testDisplayReport() {
        String report = mgr.displayReport();
        assertTrue(report.contains("22:2:WHERETIME"));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
    }
}
