package com.folder.folderstructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folder.folderstructure.dto.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RunAsyncDemo {
    public void saveEmployees(File jsonFile){
        ObjectMapper mapper= new ObjectMapper();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                   List<Employee> employees= mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });
                   //write logic to save list of employee to database
                    System.out.println("Thread: "+ Thread.currentThread().getName());
                    employees.stream().forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
