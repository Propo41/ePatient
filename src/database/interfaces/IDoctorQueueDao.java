package database.interfaces;

import javafx.util.Pair;

public interface IDoctorQueueDao {

    /**
     * checks if there are any values stored at DoctorQueue Table
     * where doctorId = $doctorId and returns it
     * @return key -> patientID
     *         value -> appointmentId
     */
    Pair<String, String> fetchQueue(String doctorId);

    /**
     * first checks if there's any existing row with the given doctorId
     * if yes, then update it, else push a new entry
     * @param doctorId
     * @param patientId
     * @param appointmentId
     */
    void insertIntoQueue(String doctorId, String patientId, String appointmentId);


}
