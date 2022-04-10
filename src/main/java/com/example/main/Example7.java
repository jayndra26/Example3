package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

public class Example7 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Volkswagen");

        Supplier<Vehicle> volkswagenSupplier =() -> vehicle;

        Supplier<Vehicle> audiSupplier = () ->{
            Vehicle vehicle1 = new Vehicle();
            vehicle1.setName("Audi");
            return vehicle1;
        };
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        System.out.println("randomNumber = "+randomNumber);
        if(randomNumber%2==0){
            context.registerBean("vehicle",Vehicle.class,volkswagenSupplier);
        }
        else{
            context.registerBean("vehicle1",Vehicle.class,audiSupplier);
        }
        Vehicle volksVehicle = null;
        Vehicle audiVehicle = null;

        try {
            volksVehicle =context.getBean("vehicle",Vehicle.class);
        }
        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error while creating volks");
        }
        try { 
            audiVehicle =context.getBean("vehicle1",Vehicle.class);
        }
        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error while creating audi");
        }
        if(volksVehicle != null){
            System.out.println(volksVehicle.getName());
        }
        else{
            System.out.println(audiVehicle.getName());
        }


    }

}
