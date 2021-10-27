package com.RMSSpringBoot;

import java.util.HashMap;

public class Problem
{
	private int count;
	private HashMap<String, Integer> counts = new HashMap<String, Integer>();

	/**
	 * Adds the name.
	 * @param name Name.
	 */
	public void addName(String name)
	{
		Integer nameCount = counts.get(name);

		if (nameCount == null)
		{
			nameCount = 1;
			counts.put(name, nameCount);
			count++;
			
			
		}
		else 
		{
		
			nameCount++;
			counts.put(name, nameCount);
			count++;
			
		}
		
		
	}

	/**
	 * Returns proportion of parameter name in all calls to addName.
	 * @param name Name.
	 * @return Double in interval [0, 1]. Returns 0 if AddName has not been called.
	 */
	public double nameProportion(String name)
	{
		return counts.get(name) / (double) count;
	}

	
}