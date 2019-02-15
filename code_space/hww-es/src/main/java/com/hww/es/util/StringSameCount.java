package com.hww.es.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author 付轩
 *	单行文字数量统计
 */
public class StringSameCount {

	private HashMap<String,Integer> map;  
	private int counter;  
	public StringSameCount() {  
		map = new HashMap<String,Integer>();  
	}  
	public void hashInsert(String string) {  
		if (map.containsKey(string)) {   //判断指定的Key是否存在  
			counter = (Integer)map.get(string);  //根据key取得value  
			map.put(string, ++counter);  
		} else {  
			map.put(string, 1);  
		}  
	}  
	    public HashMap<String,Integer> getHashMap(){  
	        return map;  
	    }  
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 StringSameCount ssc = new StringSameCount();  
	        ssc.hashInsert("ab");  
	        ssc.hashInsert("ac");  
	        ssc.hashInsert("acd");  
	        ssc.hashInsert("acd");  
	        ssc.hashInsert("asc");  
	        ssc.hashInsert("afc");  
	        ssc.hashInsert("avc");  
	        ssc.hashInsert("ac");  
	        HashMap map = ssc.getHashMap();  
	        Iterator it = map.keySet().iterator();  
	        String temp;  
	        while (it.hasNext()) {  
	            temp = (String)it.next();  
	            System.out.println(temp+"出现了"+map.get(temp)+"次");  
	        }  
	        System.out.println(map);
	        
	}

}
