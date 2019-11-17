/* package com.api.showCaseApi.keywordExtract.algorithm;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class TFIDF {

    public static List<String> getKeywords(String str)
    {
    	List<String> fileList = new ArrayList<String>();
    	fileList = ReadDir.readDirFileNames(dirPath);
    	
    	// calculate TF-IDF value for each word of each file under the dirPath
    	Map<String, HashMap<String, Float>> dirTFIDF = new HashMap<String, HashMap<String, Float>>(); 
    	dirTFIDF = TFIDF.getDirTFIDF(dirPath);
    	
        Map<String,Float> singlePassageTFIDF= new HashMap<String,Float>();
        singlePassageTFIDF = dirTFIDF.get(file);
        
        //sort the keywords in terms of TF-IDF value in descending order
        List<Map.Entry<String,Float>> entryList=new ArrayList<Map.Entry<String,Float>>(singlePassageTFIDF.entrySet());
        

        Collections.sort(entryList,new Comparator<Map.Entry<String,Float>>()
        {
            @Override
            public int compare(Map.Entry<String,Float> c1,Map.Entry<String,Float> c2)
            {
                return c2.getValue().compareTo(c1.getValue()); 	        		
            }
        }
        );
                    
        // get keywords 
        List<String> systemKeywordList=new ArrayList<String>();
        for(int k=0;k<keywordsNumber;k++)
        {
            try
            {
            systemKeywordList.add(entryList.get(k).getKey());
            }
            catch(IndexOutOfBoundsException e)
            {
                continue;
            }
        }
            
        return systemKeywordList;
    }

} */