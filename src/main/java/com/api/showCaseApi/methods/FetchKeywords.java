package com.api.showCaseApi.methods;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.api.showCaseApi.services.APIService;

public class FetchKeywords implements APIService {
    public String fetchKeywords(String data){

        try{
            System.out.println("data " + data);
            Process p = Runtime.getRuntime().exec("python KeyFetch.py " + data);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer buf = new StringBuffer("");
            String line = null;
            System.out.println(in.readLine());
            while((line=in.readLine())!=null){
                System.out.println(line);
                buf.append(line);
            }
            System.out.println(buf.length());
        } catch (Exception e) {

        }



        return "interesting@#!success";
    }
}