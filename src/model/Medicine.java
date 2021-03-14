package model;

import javafx.beans.property.SimpleStringProperty;

public class Medicine {
    private final SimpleStringProperty medicineName;
    private String medicineDuration;
    private String comment;

    public Medicine(String medicineName, String medicineDuration, String comment) {
        this.medicineName = new SimpleStringProperty(medicineName);
        this.medicineDuration = medicineDuration;
        this.comment = comment;
    }

    public String getMedicineName() {
        return medicineName.getValue();
    }

    public SimpleStringProperty medicineNameProperty() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName.set(medicineName);
    }

    public String getMedicineDuration() {
        return medicineDuration;
    }

    public void setMedicineDuration(String medicineDuration) {
        this.medicineDuration = medicineDuration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return medicineName.getValue();
    }
}
