import interfaces.Appointment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AppointmentImpl implements Appointment{

	private final String topic;
	private final Date date;
	private final int duration;
	
	DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.UK);
	
//constructors
	
	private AppointmentImpl(String topic, Date date, int duration){
		this.topic = topic;
		this.date = date;
		this.duration = duration;
	}
	/**
	 * creates a new Appointment
	 * 
	 * @param String topic:		an appointments topic
	 * @param Date date:		an appointments date in Full-DataFormat
	 * @param Integer duration:	an appointments duration in minutes
	 * 
	 * @return new AppointmentImpl-Object
	 */
	public static AppointmentImpl create(String topic, Date date, int duration){
		return new AppointmentImpl(topic, date, duration);
	}
	
//getter
	
	/**
	 * topic for the appointment
	 * @return String topic
	 */
	public String topic(){
		return this.topic;
	}
	/**
	 * exact date for the appointment, FULL DateFormat
	 * @return Date date
	 */
	public Date date(){
		return this.date;
	}
	/**
	 * duration for the appointment in minutes
	 * @return String duration
	 */
	public int duration(){
		return this.duration;
	}
	
//toString
	
	/**
	 * Appointment as List<String>
	 * 
	 * @return a List of Strings, containing topic, date, duration as Strings
	 */
	public List<String> toStringList(){
		List<String> stringList = new ArrayList<String>();
		stringList.add(topic());
		stringList.add(formatter.format(date()));
		stringList.add(duration()+"");
		return stringList;
	}
	
	/**
	 * simple toString-method; appointment with its attributes in one String, separated by ";"
	 * @return String
	 */
	@Override
	public String toString(){
		return (topic()+"; "+date().toString()+"; "+duration());
	}
}
