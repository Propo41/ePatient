package model;

import javafx.beans.property.SimpleStringProperty;

public class Disease {
    private final SimpleStringProperty diseaseName;
    private String diseaseType;
    private String description;

    public Disease(String disease, String diseaseType, String description) {
        this.diseaseName = new SimpleStringProperty(disease);
        this.diseaseType = diseaseType;
        this.description = description;
    }

    public String getDiseaseName() {
        return diseaseName.getValue();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return diseaseName.getValue();
    }
}
