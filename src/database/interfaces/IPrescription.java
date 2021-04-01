package database.interfaces;

import javafx.collections.ObservableList;
import main.ui.test.Test;
import model.*;

import java.util.ArrayList;

public interface IPrescription {
    /**
     * uses keyword to find prescription history of patient.
     * @param keyword doctor name or Id
     * @param patientId patient's id
     * @return a list of objects that include <String> and <Prescription>.
    <String> is used for header whereas <Prescription>
     */
    ArrayList<Object> getPrescriptionHistory(String patientId, String keyword);
    String createNewPrescription(Prescription prescription);

    Prescription getPrescriptionDetails(String prescriptionId);
    ObservableList<Disease> getDiseasesInfo(String prescriptionId);
    ObservableList<MedicalTest> getMedicalTestsInfo(String prescriptionId);
    ObservableList<Medicine> getMedicineInfo(String prescriptionId);
    ArrayList<Prescription> getMedicalTest(String name);
    ArrayList<MedicalTest> getTestForAddReport(String id);
}
