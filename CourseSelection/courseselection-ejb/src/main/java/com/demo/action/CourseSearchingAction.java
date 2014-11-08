package com.demo.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

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
	private int pageSize = 10;

	// Default page number
	private int page = 0;

	private boolean nextPageAvailable;

	@DataModel
	private List<Course> courses;

	public void find() {
		page = 0;
		queryCourses();
	}

	public void nextPage() {
		page++;
		queryCourses();
	}

	/**
	 * Query according course name ,teacher and time
	 * 
	 * Calculate nextPageAvailable
	 */
	private void queryCourses() {
		List<Course> results = em
				.createQuery(
						"select h from Course h where h.name like #{pattern} or h.teacher like #{pattern} or h.courseTime like #{pattern}")
				.setMaxResults(pageSize + 1).setFirstResult(page * pageSize)
				.getResultList();

		nextPageAvailable = results.size() > pageSize;

		if (nextPageAvailable) {
			courses = new ArrayList<Course>(results.subList(0, pageSize));
		} else {
			courses = results;
		}
	}

	public boolean isNextPageAvailable() {
		return nextPageAvailable;
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

	@Remove
	public void destroy() {
	}
}
