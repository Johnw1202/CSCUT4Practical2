import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import java.lang.Number;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FormatNamesm {

    public static void main(String[] args) throws FileNotFoundException {
    	
    	String inputFileName = args[args.length - 2].toString(); // the input file name
    	String outputFileName = args[args.length -1].toString(); // the output file name
    	
    	
    	
    	File input = new File(inputFileName); // the input file
    	Scanner sc = new Scanner(input);   // scanner for input file
    	PrintWriter out = new PrintWriter(outputFileName);
    	
    	
    	while (sc.hasNextLine())
    	{
    		String line = sc.nextLine();
    		int i = 0; //index of first digit
    		
    		while (!Character.isDigit(line.charAt(i))) //finds index of first digit
    		{
    			i++;
    		}
    		
    		String name = line.substring(0, i);
    		String number = line.substring(i);
    		name = name.trim(); //trims white space
    		
    		String dob = "";
    		
    		
    		char[] nameArray = name.toCharArray(); //breaks up name into array of characters
    		boolean isSpace = true;
    		
    		for (int j = 0; j < nameArray.length; j++)  //traverses name array
    		{
    			if (Character.isLetter(nameArray[j])) //checks if element is character
    			{
    				if (isSpace) // checks if space occurs before character
    				{
    					nameArray[j] = Character.toUpperCase(nameArray[j]); //changes character to uppercase
    					isSpace = false;
    				}
    				
    				
    			}
    			else
    			{
    				isSpace = true;
    			}
    			
    			name = String.valueOf(nameArray); //recombines characters to string
    			
    			String[] names = name.split("\\s+"); //splits name into array of strings
    			
    			for (int k = 0; k < names.length; k++)
    			{
    				if (names[k].length() == 1) // looks for initials
    				{
    					names[k] = " " + names[k] + ". ";  //adds . to initials and spaces that were lost to split delimiter
    				}
    				else {names[k] = names[k] + " ";} //adds spaces that were lost to split delimiter
    			}
    			
    			StringBuffer sb = new StringBuffer();   
    			for (int l = 0; l < names.length; l++)
    			{
    				sb.append(names[l]);   // recombines array into string
    			}
    			
    			name = sb.toString().trim();
    			
    			Integer dobNumber = Integer.parseInt(number); // changes number to int
    			SimpleDateFormat originalFormat = new SimpleDateFormat("ddMMyyyy"); // date format
    			SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy"); // format for output
    			Date dobDate;
    			try {
					dobDate = originalFormat.parse(dobNumber.toString()); //parse number to date
					dob = newFormat.format(dobDate); //change date to output format 
				} catch (ParseException e) {
					System.out.println("Could not parse date");
					e.printStackTrace();
				}
    		}
    		
    		
    		
    		if (!(args[0].equals("-u"))) // checks for -u in arguments
    		{
    			out.printf("%-20s%10s%n", name, dob);
    		}
    		else 
    		{
    			out.printf("%-20S%10s%n", name, dob);
    		}
    		
    	}
    	
    	sc.close();
    	out.close();
    	

    } // main

} // FilesInOut
