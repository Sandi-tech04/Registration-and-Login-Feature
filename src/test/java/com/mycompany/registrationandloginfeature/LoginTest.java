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
public class LoginTest {
    Login obj = new Login("Kyle", "Brown", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
    
    @Test
    public void testRegisteruserNameCorrectlyFormatted(){
        String userName = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber= "+27838968976";
        
        obj.RegisterUser(userName, password, cellPhoneNumber);
        obj.firstName = "Kyle";
        obj.lastName= "Brown";
        
        String expected = "Welcome " + "Kyle " + "Brown" + ", it is great to see you.";
         String results = obj.returnLoginStatus();
         assertEquals(expected, results);
    }
    
    @Test          
    public void testRegisteruserNameIncorrectlyFormatted() {
        String userName = "kyle!!!!!!!";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String results = obj.RegisterUser(userName, password, cellPhoneNumber);
        assertEquals(expected, results); 
    }
    
    @Test          
    public void testRegisterUserpasswordMeetsComplexity() {
        String userName = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
       
        
        String expected =  "Password successfully captured";
        String results = obj.RegisterUser(userName, password, cellPhoneNumber);
        
          String actual = results.split("\n")[1];
        assertEquals(expected, actual); 
        
    } 
    
    @Test          
    public void testRegisterUserpasswordDoesNotMeetComplexity() {
        String userName = "kyl_1";
        String password = "password";
        String cellPhoneNumber = "+27838968976";
        
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String results = obj.RegisterUser(userName, password, cellPhoneNumber);
        assertEquals(expected, results); 
    }
    
    @Test          
    public void testRegisterUsercellPhoneNumberCorrectlyFormatted() {
        String userName = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        
        
        String expected = "Cell phone number successfully captured";
        String results = obj.RegisterUser(userName, password, cellPhoneNumber);
        
         String actual = results.split("\n")[2];
        assertEquals(expected, actual); 
    }
    
    @Test          
    public void testRegisterUsercellPhoneNumberIncorrectlyFormatted() {
        String userName = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "08966553";
        
        String expected = "Cell phone number incorrectly formatted or does not contain international code.";
        String results = obj.RegisterUser(userName, password, cellPhoneNumber);
        assertEquals(expected, results); 
    }

    @Test          
    public void testLoginSuccessful() {
         String userName = "kyl_1";
        String password = "Ch&&sec@ke99!";
    boolean results = obj.loginUser(userName, password);
        assertTrue(results); 
    }
    
    @Test          
    public void testLoginFailed() {
        String userName = "kyle!!!!!!!";
        String password = "password";
        
    boolean results = obj.loginUser(userName, password);
        assertFalse(results); 
    }
     
    @Test
    public void testuserNameCorrectlyFormatted() {
        String userName = "kyl_1";
        boolean results = obj.checkuserName(userName);
        assertTrue(results);
    }
    
      @Test          
    public void testUserNameIncorrectlyFormatted() {
        String userName = "kyle!!!!!!!";
        boolean results = obj.checkuserName(userName);
        assertFalse(results);
}
    
    @Test          
    public void testpasswordMeetsComplexityRequirements() {
        String password = "Ch&&sec@ke99!";
        boolean results = obj.checkpassword(password);
        assertTrue(results);    
    }
    
    @Test          
    public void testpasswordDoesNotMeetsComplexityRequirements() {
        String password = "password";
        boolean results = obj.checkpassword(password);
        assertFalse(results);   
    }
    
    @Test          
    public void testcellPhoneNumberCorrectlyFormatted() {
        String cellPhoneNumber = "+27838968976";
    boolean results = obj.checkcellPhoneNumber(cellPhoneNumber);
        assertTrue(results); 
    
    }
    
    @Test          
    public void testcellPhoneNumberIncorrectlyFormatted() {
        String cellPhoneNumber = "08966553";
    boolean results = obj.checkcellPhoneNumber(cellPhoneNumber);
        assertFalse(results); 
    }
    
    
}