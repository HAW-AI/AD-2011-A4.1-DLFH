import interfaces.Appointment;

import java.util.Comparator;
import java.util.Locale;

public class ComparatorTopic implements Comparator<Appointment> {
	
	/**
	 * compares two Appointments by its topics
	 * 
	 * @param Appointment this Appointment
	 * @param Appointment this Appointment
	 * 
	 * @return Integer <=> 0
	 */
	@Override
	public int compare(Appointment t1, Appointment t2) {
		return t1.topic().toUpperCase(Locale.ENGLISH).compareTo(t2.topic().toUpperCase(Locale.ENGLISH));
	}

}
