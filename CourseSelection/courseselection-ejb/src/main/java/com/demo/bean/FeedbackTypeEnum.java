package com.demo.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public enum FeedbackTypeEnum {
	SYSTEM_BUG("System Bug"),SUGGESTION("Suggestion"),RECOMMENDATAION("Recommendation System");
	
	private FeedbackTypeEnum(String type){
		
	}
	
	//Store enum type names 
	static Map<String,Object> typeEnumMap = new LinkedHashMap<String,Object>();
	static{
		Object[] enumValues = FeedbackTypeEnum.class.getEnumConstants();
		if (enumValues != null) {
			for (Object enumValue : enumValues) {
				typeEnumMap.put(enumValue.toString(), enumValue);
			}
		}
	}
	
	/**
	 * Provide the list of names for this enum class
	 * @return
	 */
	public static Map<String,Object> getTypeMap(){
		return typeEnumMap;
	}
}
