/* package com.api.showCaseApi.keywordExtract.algorithm;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class TextRankWithTFIDF {

    private static int keywordsNumber = 5;
    private static int keywordCandidateNum = 10;
    
    public static void setKeywordsNumber(int number){
        keywordsNumber = number;
        keywordCandidateNum = 2*number;
    }

    public static Map<String, List<String>> textRankMultiplyIDF(String str){
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        String content = str;
        Map<String, Float> idfForDir = TDIDF.idfForDir(content);
        Map<String, Float> trKeywords = TextRank.getWordScore(content);
        Iterator<Map.Entry<String, Float>> it = trKeywords.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Float> temp = it.next();
            String key = temp.getKey();
            trKeywords.put(key, temp.getValue()*idfForDir.get(key));
        }

        //sort the words in terms of their score in descending order
        List<Map.Entry<String, Float>> entryList = new ArrayList<Map.Entry<String,Float>>(trKeywords.entrySet());
        Collections.sort(entryList,
                new Comparator<Map.Entry<String, Float>>()
            {
                public int compare(Map.Entry<String, Float> c1, Map.Entry<String, Float> c2)
                {
                    return c2.getValue().compareTo(c1.getValue());
                }
                
            }
        );


    }
} */