package com.api.showCaseApi.controller;

import com.api.showCaseApi.methods.FetchKeywords;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController{

    FetchKeywords apis = new FetchKeywords();
    String finalResult = "", result = "";
    
    @GetMapping("/saveData")
    public String saveData(){
        //fetches the keyword from the user input via a python script
        String result = apis.fetchKeywords("this is a very good and interesting event.");
        if(result.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
        } else {
            finalResult = "404";
        }
        //saves the keywords in mongo db
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