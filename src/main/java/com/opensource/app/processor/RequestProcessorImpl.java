package com.opensource.app.processor;

public class RequestProcessorImpl implements RequestProcessor{

    public void processArgsFromConsole(String[] args){
        //Based on input from user, we define which method we should call
        String action = args[1];

        switch(action){
            case "Hello":
                System.out.println("Hello!");
                break;
                default:
                System.out.println("Default");
        }

    }

}