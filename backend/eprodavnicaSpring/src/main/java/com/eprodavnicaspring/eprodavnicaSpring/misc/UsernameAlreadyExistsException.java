package com.eprodavnicaspring.eprodavnicaSpring.misc;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(String username){
        super("User with username:"+username+" already exists,please choose another username.");
    }
}
