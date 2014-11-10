package com.demo.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;

import com.demo.bean.Course;
import com.demo.intf.CourseSearching;

@Stateful
@Name("courseSearch")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class CourseSearchingAction implements CourseSearching {

	@PersistenceContext
	private EntityManager em;

	// search string
	private String searchString;

	// page size
	private int pageSize = 5;

	// Default page number
	private int page = 1;

	private int totalPage = 1;

	private boolean nextPageAvailable;
	private boolean prePageAvailable;

	// user start to search
	private boolean isStartFind = false;

	private List<Course> queryCourses;

	@DataModel
	private List<Course> courses;

	@Logger
	private Log log;

	public void find() {
		isStartFind = true;

		page = 1;
		queryCourses();
		getPageResult();
	}

	public void nextPage() {
		page++;
		getPageResult();
	}

	public void prePage() {
		page--;
		getPageResult();
	}

	/**
	 * Must check the page range
	 */
	private void getPageResult() {

		log.debug("page={0} totalPage={1}", page, totalPage);

		if (page <= 1) {
			page = 1;
		}

		if (page > totalPage) {
			page = totalPage;
		}

		prePageAvailable = page > 1;
		nextPageAvailable = totalPage > page;

		int totalCount = queryCourses.size();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;
		if (end >= totalCount) {
			end = totalCount - 1;
		}

		courses = new ArrayList<Course>(queryCourses.subList(start, end));
	}

	/**
	 * Query according course name ,teacher and time
	 * 
	 * Calculate nextPageAvailable
	 */
	private void queryCourses() {
		queryCourses = em
				.createQuery(
						"select h from Course h where h.name like #{pattern} or h.teacher like #{pattern} or h.courseTime like #{pattern}")
				.getResultList();

		int totalCount = queryCourses.size();

		totalPage = totalCount / pageSize;

		if (totalCount - totalPage * pageSize > 0) {
			totalPage++;
		}

	}

	public boolean isStartFind() {
		return isStartFind;
	}

	public boolean isNextPageAvailable() {
		return nextPageAvailable;
	}

	public boolean isPrePageAvailable() {
		return prePageAvailable;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Escape the search string Prevent sql-injection-attack
	 * 
	 */
	@Factory(value = "pattern", scope = ScopeType.EVENT)
	public String getSearchPattern() {
		return searchString == null ? "%" : '%' + searchString.trim().replace(
				'*', '%') + '%';
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getPage() {
		return page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	@Remove
	public void destroy() {
	}

}
