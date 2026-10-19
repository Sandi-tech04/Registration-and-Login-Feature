/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sandi
 */
public class MessageTest {

    // Test for Message length SUCCESS
    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message("0012345678", 1, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", m.validateMessageLength());
    }

    // Test for Message length FAILURE 
    @Test
    public void testMessageLengthFailure() {
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) longMsg.append("a");
        Message m = new Message("0012345678", 1, "+27718693002", longMsg.toString());
        assertTrue(m.validateMessageLength()
                .startsWith("Message exceeds 250 characters by 10"));
    }

    // Test for Recipient correctly formatted
    @Test
    public void testRecipientCorrect() {
        Message m = new Message("0012345678", 1, "+27718693002", "Hi");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    // Test for Recipient incorrectly formatted
    @Test
    public void testRecipientIncorrect() {
        Message m = new Message("0012345678", 1, "08575975889", "Hi");
        assertTrue(m.checkRecipientCell()
                .startsWith("Cell phone number is incorrectly formatted"));
    }

    // Test for Message hash from Test Case 1 (00:0:HITONIGHT)
    @Test
    public void testMessageHashCorrect() {
        Message m = new Message("0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    // Test for checkMessageID within limit
    @Test
    public void testCheckMessageID() {
        Message m = new Message("0012345678", 1, "+27718693002", "Hi");
        assertTrue(m.checkMessageID());
    }

    // Test for sentMessage returns correct string
    @Test
    public void testSentMessage() {
        Message m = new Message("0012345678", 1, "+27718693002", "Hi");
        assertEquals("Message successfully sent", m.sentMessage(1));
        assertEquals("Sent", m.getFlag());
    }

    // Test for storeMessage flag
    @Test
    public void testStoreMessage() {
        Message m = new Message("0012345678", 1, "+27718693002", "Hi");
        assertEquals("Message successfully stored", m.sentMessage(2));
        assertEquals("Stored", m.getFlag());
    }
}