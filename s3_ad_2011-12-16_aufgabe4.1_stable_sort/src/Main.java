import interfaces.Appointment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utility.Helper.*;
public class Main {
	private final static String DELIMITER = " || ";
	
	public static void main(String[] args){

		BufferedReader fileReader = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Integer, Comparator<Appointment>> comparatorMap = new HashMap<Integer, Comparator<Appointment>>();
		
		comparatorMap.put(1, new ComparatorTopic());
		comparatorMap.put(2, new ComparatorDate());
		comparatorMap.put(3, new ComparatorDuration());
		
		boolean fileFound = false;
		String filePathInput = null;
		CsvParser parsedData = null;
		
		while(!fileFound){
			pl("Please enter filepath e.g. samples/test1.1.csv: ");

			try {
				filePathInput = fileReader.readLine();
				if(filePathInput.equals("exit")){
					 exitConsole();
				}
				parsedData = CsvParser.parse(filePathInput, DELIMITER);
				fileFound = true;
		    } catch (IOException ioe){
		    	pl("IO error trying to read your order!");
		    	System.exit(1);
		    } catch (IllegalArgumentException e){
		    	pl("File not Found");
		    }
		}
	
		while(true){
			p("Choose the sorting order or quit with \"exit\"!");
			Integer elemCounter = 1;
			for(String headerElem : parsedData.dataHeader()){
				p(" "+elemCounter+"="+headerElem);
				elemCounter++;
			}
			p(": ");
			
			BufferedReader orderReader = new BufferedReader(new InputStreamReader(System.in));
			String orderInput = null;
			try {
				orderInput = orderReader.readLine();
				if(orderInput.equals("exit")){
					 exitConsole();
				}
		    } catch (IOException ioe){
		    	pl("IO error trying to read your order!");
		    	System.exit(1);
		    }
			
			List<Integer> sortOrder = new ArrayList<Integer>();
			sortOrder = buildOrder(orderInput);
		
			if(sortOrder.isEmpty() || !validOrderNumber(sortOrder, parsedData.dataHeader().size())){
				pl("Your sorting order is not correct!");
			} else{
				pl("Here we go!");
				
				for(Integer sortElem : sortOrder){
					Collections.sort(parsedData.dataContent(), comparatorMap.get(sortElem));
				}
				
				pl(parsedData.dataContent());
				pl("");
				CsvWriter.write(parsedData.dataAll(), "samples/SortedData"+System.currentTimeMillis()+".csv", DELIMITER);
			}
		}
	}
	
	/**
	 * turns the input-order into integer-list for the methods
	 * @param String o:		containing integer-input from user, using "," to split
	 * @return List<Integer>
	 */
	private static List<Integer> buildOrder(String o){
		List<Integer> result = new ArrayList<Integer>();
		String[] elements = o.split(",");
		try { 
			for(String elem : elements){
				result.add(Integer.parseInt(elem));
			}
	    } 
	    catch (NumberFormatException ex){ 
	      result.clear();
	    }
		Collections.reverse(result);
		return result;
	}
	
	/**
	 * tests, whether number of orders is valid
	 * @param List<Integer> numbers:	list of orders
	 * @param Integer limit:			limit of allowed orders, addicted to the number of an appointments attributes (3)
	 * @return boolean; whether orders are valid or not
	 */
	private static boolean validOrderNumber(List<Integer> numbers, Integer limit){
		for(Integer number : numbers){
			if(number > limit){
				return false;
			}
		}
		return true;
	}
	
	private static void exitConsole(){
		pl("BuyBuy");
		System.exit(0);
	}
}