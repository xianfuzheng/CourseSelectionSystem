package intf;

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
	 * Move record set to next page
	 * Must check the range
	 */
	public void nextPage();
	
	/**
	 * Return whether there is any more page
	 * @return 
	 */
	public boolean isNextPageAvailable();

	/**
	 * Cleanup function, should be invoked by framework
	 */
	public void destroy();

}