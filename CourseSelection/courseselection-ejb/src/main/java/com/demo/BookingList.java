package com.demo;

import javax.ejb.Local;

@Local
public interface BookingList
{
   public void getBookings();
   public StudentBooking getBooking();
   public void cancel();
   public void destroy();
}