import interfaces.Appointment;

import java.util.Comparator;

public class ComparatorDuration implements Comparator<Appointment> {

	/**
	 * compares two Appointments by its durations
	 * 
	 * @param Appointment this Appointment
	 * @param Appointment this Appointment
	 * 
	 * @return Integer <=> 0
	 */
	@Override
	public int compare(Appointment thisAppointment, Appointment thatAppointment) {
		return Integer.valueOf(thisAppointment.duration()).compareTo(Integer.valueOf(thatAppointment.duration()));
	}

}
