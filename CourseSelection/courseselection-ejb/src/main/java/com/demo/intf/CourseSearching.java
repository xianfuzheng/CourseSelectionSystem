package com.demo.intf;

import javax.ejb.Local;

/**
 * Interface for searching courses given conditions inputed by user
 * 
 * @author Frank ZHENG
 *
 */
@Local
public interface CourseSearching {
	
	/**
	 * Return pageSize of this search
	 * @return pageSize
	 */
	public int getPageSize();

	/**
	 * Set page size of this search
	 * @param pageSize
	 */
	public void setPageSize(int pageSize);

	/**
	 * Return user-input search string,previous input by user
	 * @return
	 */
	public String getSearchString();

	/**
	 * Set search string for this conversation
	 * @param searchString
	 */
	public void setSearchString(String searchString);

	/**
	 * 
	 * @return
	 */
	public String getSearchPattern();
	
	public void find();
	
	/**
	 * Return whether user start to search
	 * @return
	 */
	public boolean isStartFind();

	/**
	 * Move record set to next page
	 * Must check the range
	 */
	public void nextPage();
	
	/**
	 * Move record set to pre page
	 * Must check the range
	 */
	public void prePage();
	
	
	/**
	 * Return whether there is any previous page
	 * @return 
	 */
	public boolean isPrePageAvailable();
	
	/**
	 * Return whether there is any more page
	 * @return 
	 */
	public boolean isNextPageAvailable();
	
	/**
	 * Return current page no
	 * @return 
	 */
	public int getPage();
	
	/**
	 * Return total page number
	 * @return
	 */
	public int getTotalPage();
	
	/**
	 * Cleanup function, should be invoked by framework
	 */
	public void destroy();

}