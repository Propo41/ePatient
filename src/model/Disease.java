package model;

import javafx.beans.property.SimpleStringProperty;

public class Disease {
    private final SimpleStringProperty diseaseName;
    private String diseaseType;
    private String comments;

    public Disease(String disease, String diseaseType, String comments) {
        this.diseaseName = new SimpleStringProperty(disease);
        this.diseaseType = diseaseType;
        this.comments = comments;
    }

    public String getDiseaseName() {
        return diseaseName.get();
    }

    public SimpleStringProperty diseaseNameProperty() {
        return diseaseName;
    }

    public void setDiseaseName(String disease) {
        this.diseaseName.set(disease);
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return diseaseName.getValue();
    }
}
