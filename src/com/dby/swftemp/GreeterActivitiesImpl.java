package com.dby.swftemp;

public class GreeterActivitiesImpl implements GreeterActivities{
	  @Override
	   public String getName() {
	      return "World";
	   }

	   @Override
	   public String getGreeting(String name) {
	      return "Hello " + name + "!";
	   }

	   @Override
	   public void say(String what) {
		   System.out.println("this is version 2.0");
	      System.out.println(what);
	   }
}
