package interfaces;

import java.util.Date;
import java.util.List;

public interface Appointment {
	/**
	 * topic for the appointment
	 * @return String topic
	 */
	public String topic();
	/**
	 * duration for the appointment in minutes
	 * @return String duration
	 */
	public int duration();
	/**
	 * exact date for the appointment, FULL DateFormat
	 * @return Date date
	 */
	public Date date();
	/**
	 * simple toString-method; appointment with its attributes in one String, separated by ";"
	 * @return String
	 */
	public String toString();
	/**
	 * Appointment as List<String>
	 * 
	 * @return a List of Strings, containing topic, date, duration as Strings
	 */
	public List<String> toStringList();
}
