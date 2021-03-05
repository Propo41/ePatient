package main.ui.database;

import main.ui.model.Patient;

import java.util.ArrayList;

public interface IPatientDao {
    ArrayList<Patient> getPatientList();
}
