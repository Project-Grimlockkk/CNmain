package com.example.rr;

public class Feedback {
    private String role;
    private String description;
    private String selectedOption;

    public Feedback() {
        // Default constructor required for Firebase
    }

    public Feedback(String role, String description, String selectedOption) {
        this.role = role;
        this.description = description;
        this.selectedOption = selectedOption;
    }

    // Getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}

