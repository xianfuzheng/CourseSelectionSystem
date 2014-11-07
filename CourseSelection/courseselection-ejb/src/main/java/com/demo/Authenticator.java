package com.demo;

import javax.ejb.Local;

@Local
public interface Authenticator
{
   boolean authenticate();
}
