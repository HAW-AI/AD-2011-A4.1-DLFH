import interfaces.Appointment;

import java.util.Comparator;

public class ComparatorDate implements Comparator<Appointment>{

	/**
	 * compares two Appointments by its dates
	 * 
	 * @param Appointment this Appointment
	 * @param Appointment this Appointment
	 * 
	 * @return Integer <=> 0
	 */
	@Override
	public int compare(Appointment thisAppointment, Appointment thatAppointment) {
		return thisAppointment.date().compareTo(thatAppointment.date());
	}

}
