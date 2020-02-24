package com.Logging.logging;

/**
 * Hello world!
 *
 */
//public class App 
//{
//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//    }
//}


import HouseConstruction.*;
import Interest_Calculator.*;

import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class App 
{
	static Scanner scanner = new Scanner(System.in);
	static Map<String,Integer> ConstructionDetails;
	static PrintStream output =  new PrintStream(new FileOutputStream(FileDescriptor.out));
    static Double principal ;
    static Double rateofInterest;
    static Double timeperiod;
    private static final Logger LOGGER = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {	
    	Character opt;
        try {
        	
    	do {
    		
    		 LOGGER.info("	Select Task	\n"
		        		+ "** Interest Calculator **\n"
		        		+ "1. Simple Interest\n"
		        		+ "2. Compound Interest\n\n"
		        		+ "** Estimating House Construction Cost **\n"
		        		+ "3. Select a Construction Plan : \n");
    		 
    		Character option;
    		option = scanner.next().charAt(0);
    		
    		switch(option) {
    		     
    		case '1':  getInterestDetails();
    		      SimpleInterest simpleinterest = new SimpleInterest(principal,rateofInterest,timeperiod);
    		      LOGGER.info("Simple Interest is "+ simpleinterest.getSimpleInterest()+"\n");
    		      break;
    		case '2': getInterestDetails();
    		      CompoundInterest compoundinterest = new CompoundInterest(principal,rateofInterest,timeperiod);
    		      LOGGER.info("Compound Interest is "+ compoundinterest.getCompoundInterest() +"\n");
    		      break;
    		case '3': getmoreDetails();  
    		        break;
    		default:  LOGGER.info("Select correct option\n");;
    		}
    		
    		LOGGER.info("Do you want to enter more Y or N\n");
    		opt= scanner.next().charAt(0);
    		
    	}while(opt!='N');
    	
    	LOGGER.info("Terminated Successfully\n");
    	
        }catch(Exception e){}
    	
    }
    public static void getInterestDetails() {
    	
    	LOGGER.info("Enter values principal,RateOfInterest, Timeperiod\n");
    	
    	principal = scanner.nextDouble();
    	rateofInterest = scanner.nextDouble();
    	timeperiod = scanner.nextDouble();
    	
    	
    }
    public static void getmoreDetails(){
    	
    	Estimation e = new Estimation();
    	
    	ConstructionDetails = e.getConstructionDetails();
    	
    	Integer SerialNumber=1;
    	
    	
    	Set<Map.Entry<String,Integer>> Plans = e.getConstructionDetails().entrySet();
    	
    	ArrayList<String> options = new ArrayList<String>();
    	LOGGER.info("List of Plans Available are\n");
    	
    	for(Map.Entry<String,Integer> details: Plans) {
   
    		options.add(details.getKey());
    		LOGGER.info(SerialNumber+" "+ details.getKey()+" "+details.getValue()+ "per Square Meter\n");
    	    SerialNumber++;
    		
    	}
    	
    	Integer key = scanner.nextInt();
    	
        e.SelectedPlan(options.get(key-1));
        
        LOGGER.info("Enter the Area(LENGTH x BREADTH)");
        
       Integer length = scanner.nextInt();
       Integer breadth = scanner.nextInt();
    	

       e.SetDimensions(length, breadth);
       
      
    	LOGGER.info("The Total Cost for the Construction is "+ e.getCost_Estimation()+"\n");
    	
    	
    	
    }
    
    
}
