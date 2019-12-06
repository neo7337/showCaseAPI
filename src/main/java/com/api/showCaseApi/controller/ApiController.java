package com.api.showCaseApi.controller;

import java.util.HashMap;

import com.api.showCaseApi.component.ShutdownApp;
import com.api.showCaseApi.methods.MethodService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController extends MethodService{

    String finalResult = "", result = "";
    String[] data;
    ShutdownApp shutdownApp = new ShutdownApp();

    @RequestMapping("/systemExit")
    public void systemExit(){
        shutdownApp.initiateShutdown(0);
    }

    @RequestMapping(value = {"${url.mapping}","${url.mapping.two}" })
    public String feedBack() {
        System.out.println("inside feedback function");
        return "feedBack";
    }
    
    @RequestMapping("/view")
    public String viewResult() {
        System.out.println("inside viewResult function");
        return "view";
    }
    

    @RequestMapping(value = "/api/v1/saveData", method = RequestMethod.GET)
    @ResponseBody
    public String saveData(@RequestParam("feedback") String dataInput){
        //fetches the keyword from the user input via a python script
        String input = dataInput.trim();
        String fetchResult = fetchKeywords(input);
        if(fetchResult.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
            System.out.println("TextRank executed successfully");
            //result comes in the form of a saperator and store that data in an array for further parsing
            data = (fetchResult.split("@#!")[0]).split(",");
            System.out.println("keywords Extracted : " + data.toString());
        } else {
            finalResult = "404";
        }
        String saveResult = createMap(data); //parameter will be a hashmap
        if(saveResult.split("@#!")[1].equalsIgnoreCase("Success")){
            finalResult = "200";
            System.out.println("Data saved successfully");
        } else {
            finalResult = "404";
        }
        return finalResult;
    }

    @RequestMapping(value = "/api/v1/fetchData", produces = "application/json")
    @ResponseBody
    public HashMap<String, Integer> fetchJSONData(){
        System.out.println("inside fetch");
        HashMap<String, Integer> response = new HashMap<String, Integer>();
        try{
            response = fetchMap();
        } catch (Exception e){
            result = "Error";
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value ="/api/v1/reset")
    public void reset() {
        emptyMap();
    }

    @RequestMapping(value="/api/v1/saveData")
    public String save() {
        String status = saveData();
        return status;
    }
}