package com.demo.intf;

import java.util.Map;

public interface Feedback {

	/**
	 * feedback type
	 * @return
	 */
	public String getType();
	
	/**
	 * Setter for feedback type
	 * @param type
	 */
	public void setType(String type);
	
	/**
	 * getter function for content
	 * @return
	 */
	public String getContent();

	/**
	 * setter function for content
	 * @param content
	 */
	public void setContent(String content);

	/**
	 * User click submit
	 * Should reset all the field
	 */
	public void sendFeedBack();
	
	
	/**
	 * Generate the dropdown box
	 */
	public Map<String,Object> getFeedBackTypes();
	
	/**
	 * destroy function
	 */
	public void destroy();
	
}
