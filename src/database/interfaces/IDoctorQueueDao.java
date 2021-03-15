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

}
