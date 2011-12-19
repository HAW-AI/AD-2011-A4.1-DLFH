import interfaces.Appointment;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import static utility.Helper.*;

public class CsvParser {

	private final Pattern ALLOWED_CHARS = Pattern.compile("([^a-zA-Z0-9])");
	private final String DELIMITER;
	private List<String> header;
	private List<Appointment> content;
	
//constructors
	
	/**
	 * creates new CsvParser to parse a file by its name; the text-file "filename" will be parsed and turned to a list of appointments; the appointments will be split into topic, date, duration by the given delimiter
	 * 
	 * @param String filename:	path to a file, containing a specific text-document
	 * @param String delimiter:	a char, separating an appointments topic, date, duration in a line of the text-file
	 * 
	 * @return new CsvParser by filename and delimiter
	 */
	public static CsvParser parse(String filename, String delimiter){
		return new CsvParser(filename, delimiter);
	}
	
	private CsvParser(String filename, String delimiter) {
		DELIMITER = escapeSpecialChars(delimiter);
		parse(filename);
	}
	
//parsing
	
	private void parse(String filename) {
		if (this.content == null) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(new File(filename)));
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException(filename + " not found");
				//catches exception if there is no file to be read
			}
			List<Appointment> buffer = new ArrayList<Appointment>();
			try {
				String line; 
				int lineCounter = 0;
				int lineElementNumber = 0;
				while ((line = reader.readLine()) != null) { //work as long as there are lines left
					if(lineCounter == 0){ //separates the head not to be turned into an appointment
						this.header = Arrays.asList(line.split(DELIMITER)); //split the line into attributes, using the delimiter
						lineElementNumber = this.header.size();
					}else {
						String[] lineElements = line.split(DELIMITER);
						if(lineElements.length != lineElementNumber){ //select all lines with wrong number of attributes
							pl("Aborted: All lines of the file have to be of the same element number!");
						} else{
							Appointment lineTermin = null; //one line in the file stands for one appointment plus attributes

							DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.UK); //f.e. Thu Dec 15 20:30:00 CEST 2011
							formatter.setLenient(false); //avoid not allowed date f.e. Feb 30
							try {
								//tries to create a new appointment with its attributes
								lineTermin = AppointmentImpl.create(lineElements[0], 
										formatter.parse(lineElements[1]), 
										Integer.parseInt(lineElements[2]));
							} catch (ParseException e) {
								//catches Exception, when date-Input is invalid
								//e.printStackTrace();
								pl("Invalid Input in Line "+lineCounter+"; Check date: "+lineElements[1]);
							} catch (NumberFormatException e){
								//catches Exception, when duration-Input is no Integer
								//e.printStackTrace();
								pl("Invalid Input in Line "+lineCounter+"; Check Dauer: "+lineElements[2]);
							}
							if(lineTermin != null){
								buffer.add(lineTermin);
							}
						}
					}
					lineCounter++;
				}
			} catch (IOException e) {
				// to bad, no lines to read
				pl("No valid lines available");
			}
			this.content = buffer;
		}
	}
	
	//help-functions
	
	// taken from http://stackoverflow.com/questions/7449852/escape-special-symbols-from-regexpr (12.12.2010) and adapted
	
	/**
	 * escapes all chars different from the pattern, so that there won't by parsing them
	 * 
	 * @param String str:	given String from which the chars will be escaped
	 * 
	 * @return String, where special chars were escaped
	 */
	public String escapeSpecialChars(String str) {
	    return ALLOWED_CHARS.matcher(str).replaceAll("\\\\$1");
	}

//getter
	
	/**
	 * a datas head / caption for the following appointments
	 * 
	 * @return List<String> containing the headline of a document
	 */
	public List<String> dataHeader(){
		return header;
	}
	
	/**
	 * all appointments of the document / the documents content except the head
	 * 
	 * @return List<Appointment>
	 */
	public List<Appointment> dataContent(){
		return content;
	}
	
	/**
	 * the whole data as a List of Strings
	 * 
	 * @return List<List<String>> a list of appointments an their topic, date, duration plus the head
	 */
	public List<List<String>> dataAll(){
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(header);
		for(Appointment line : content){
			data.add(line.toStringList());
		}
		return data;
	}
	
}