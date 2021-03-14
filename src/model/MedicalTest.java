package model;

import javafx.beans.property.SimpleStringProperty;

public class MedicalTest {
    private final SimpleStringProperty testName;
    private String testDescription;
    private String testDate;


    public MedicalTest(String testName, String testDescription) {
        this.testName = new SimpleStringProperty(testName);
        this.testDescription = testDescription;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestName() {
        return testName.getValue();
    }

    public SimpleStringProperty testNameProperty() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName.set(testName);
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    @Override
    public String toString() {
        return testName.getValue();
    }
}
