package model;

public class HealthCondition {
    private String foodSuggestion;
    private String exerciseRoutine;
    private String healthCondition;
    private String comments;

    public HealthCondition(String foodSuggestion, String exerciseRoutine, String healthCondition, String comments) {
        this.foodSuggestion = foodSuggestion;
        this.exerciseRoutine = exerciseRoutine;
        this.healthCondition = healthCondition;
        this.comments = comments;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFoodSuggestion() {
        return foodSuggestion;
    }

    public void setFoodSuggestion(String foodSuggestion) {
        this.foodSuggestion = foodSuggestion;
    }

    public String getExerciseRoutine() {
        return exerciseRoutine;
    }

    public void setExerciseRoutine(String exerciseRoutine) {
        this.exerciseRoutine = exerciseRoutine;
    }
}
