package com.demo;

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

@Stateful
@Name("courseSearch")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class CourseSearchingAction implements CourseSearching
{
    @PersistenceContext
    private EntityManager em;
    
    private String searchString;
    private int pageSize = 10;
    private int page;
    private boolean nextPageAvailable;
   
    @DataModel
    private List<Course> courses;
   
    public void find() 
    {
        page = 0;
        queryCourses();
    }

    public void nextPage() 
    {
        page++;
        queryCourses();
    }
    
    private void queryCourses() {
        List<Course> results = em.createQuery("select h from Course h ")
                                .setMaxResults(pageSize+1)
                                .setFirstResult(page * pageSize)
                                .getResultList();
        
        nextPageAvailable = results.size() > pageSize;

		
        if (nextPageAvailable) 
        {
            courses = new ArrayList<Course>(results.subList(0,pageSize));
        } else {
            courses = results;
        }
    }

    public boolean isNextPageAvailable()
    {
        return nextPageAvailable;
    }
   
   public int getPageSize() {
      return pageSize;
   }
   
   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }
   
   @Factory(value="pattern", scope=ScopeType.EVENT)
   public String getSearchPattern()
   {
      return searchString==null ? 
            "%" : '%' + searchString.trim().replace('*', '%') + '%';
   }
   
   public String getSearchString()
   {
      return searchString;
   }
   
   public void setSearchString(String searchString)
   {
      this.searchString = searchString;
   }
   
   @Remove
   public void destroy() {}
}
