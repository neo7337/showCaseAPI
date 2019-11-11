package com.api.showCaseApi.controller;

import com.api.showCaseApi.methods.MethodService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController extends MethodService{

    String finalResult = "", result = "";
    String[] data;
    
    @GetMapping("/saveData")
    public String saveData(){
        //fetches the keyword from the user input via a python script
        String fetchResult = fetchKeywords("this is a very good and interesting event.");
        if(fetchResult.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
            System.out.println("Py Script executed successfully");
            //result comes in the form of a saperator and store that data in an array for further parsing
            data = (fetchResult.split("@#!")[0]).split(",");
        } else {
            finalResult = "404";
        }
        int getResult = getMongoData();
        if(getResult == 200){
            //the data is fetched form mongo database into a HashMap
            System.out.println("Data fetched successfully");
        }
        //saves the keywords in mongo db 
        String saveResult = saveToMongo(data); //parameter will be a hashmap
        if(saveResult.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
            System.out.println("Data saved successfully");
        } else {
            finalResult = "404";
        }
        return finalResult;
    }

    @GetMapping("/fetchData")
    public String fetchJSONData(){
        try{
            //JSONObject jobj = new JSONObject();
            //jobj.put("success","200");
            result = "success";
        } catch (Exception e){
            result = "Error";
            e.printStackTrace();
        }
        return result;
    }
}