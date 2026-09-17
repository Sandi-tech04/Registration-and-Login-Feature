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
     String firstName= input.nextLine();
     
      System.out.println("Enter your last name");
     String lastName= input.nextLine();
    
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
     
     Login.loginUser(LoginuserName, LoginPassword);
     
     
    }
  
    }