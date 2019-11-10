package com.api.showCaseApi.controller;

import com.api.showCaseApi.methods.MethodService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController{

    MethodService apis = new MethodService();
    String finalResult = "", result = "", data="";
    
    @GetMapping("/saveData")
    public String saveData(){
        //fetches the keyword from the user input via a python script
        String fetchResult = apis.fetchKeywords("this is a very good and interesting event.");
        if(fetchResult.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
            //result comes in the form of a saperator and store that data in an array for further parsing
            data = fetchResult.split("@#!")[0];
        } else {
            finalResult = "404";
        }
        //saves the keywords in mongo db
        String saveResult = apis.saveToMongo(data);
        if(saveResult.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
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