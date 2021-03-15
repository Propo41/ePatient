package database.interfaces;

import model.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IAppointment {
    /**
     * @return all the appointments for the given date
     */
    ArrayList<Appointment> getAppointmentInfo(LocalDate date);
    /**
     * @return all the appointments for the given date where doctorID = $doctorId
     */
    ArrayList<Appointment> getAppointmentInfo(String doctorId, LocalDate date);

    ArrayList<Appointment> getAppointmentInfoByKeyword(String keyword, LocalDate date);
}
