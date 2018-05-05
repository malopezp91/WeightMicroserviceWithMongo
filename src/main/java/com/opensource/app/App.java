package com.opensource.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.opensource.app.processor.RequestProcessorImpl;

//Guice Configuration Modules
class WeightModule  extends AbstractModule{
    
    protected void configure(){
        //Binding

    }
}



public class App 
{
    public static void main( String[] args )
    {
        //Initialize Guice!

        Injector injector = Guice.createInjector(new WeightModule());
        
        injector.getInstance(RequestProcessorImpl.class).processArgsFromConsole(args);
    }
}
