package com.demo;

import javax.ejb.Local;

@Local
public interface CourseBooking
{
   public void selectCourse(Course selectedCourse);
 
   public void destroy();
   
}