package com.Java9Features;

import java.util.Arrays;
import java.util.List;

public class HelloWorld {
	
	
	
	
	public static void myMethod(List<String>...l)
	{
		Object[] obj=l;
		obj[0]=Arrays.asList(1,2);
	}
}
