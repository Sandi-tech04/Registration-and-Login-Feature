/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

/**
 *
 * @author sandi
 */
public class Login {
   //Attributes
    public String firstName;
    public String lastName;
    public String userName;
    public String password; 
    public String cellPhoneNumber; 
    
   //Constructor
     public Login(String firstName, String lastName, String userName, String password, String cellPhoneNumber){  
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.cellPhoneNumber = cellPhoneNumber;
     }
     
    //methods to check UserName, Password and CellPhoneNumber
    public boolean checkuserName(String userName){
        return userName.contains("_") && userName.length() <= 5;
    }
    
    public boolean checkpassword(String password){
        return password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@#$%&^].*") && password.length() >= 8;

    }
    // Cell phone regex adapted from South African phone number format specifications
 public boolean checkcellPhoneNumber(String cellPhoneNumber){
     String regex = "^\\+27[0-9]{9}$";
        return cellPhoneNumber.matches(regex);
 }
public String RegisterUser(String userName, String password, String cellPhoneNumber){
    if(!checkuserName(userName)){
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";  
    }
    
    if(!checkpassword(password)){
       return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."; 
    } 
    if(!checkcellPhoneNumber(cellPhoneNumber)){
         return "Cell phone number incorrectly formatted or does not contain international code.";
    }
    return "Username successfully captured\nPassword successfully captured\nCell phone number successfully captured";
   
    
   
}

public boolean loginUser(String userName, String password){
    return this.userName != null && this.password!= null && this.userName.equals(userName)&& this.password.equals(password);
}
public String returnLoginStatus(){
if(loginUser(userName, password)){
    return "Welcome " + firstName  + " "+ lastName + ", it is great to see you.";
}
        return "Username or Password incorrect, please try again";
}

}