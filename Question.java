package com.assessment.sasing.model;

import jakarta.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String type; // SHORT, MCQ, MULTIPLE_CHOICE
    private int points;

    @Column(columnDefinition = "TEXT")
    private String choices; // JSON string for MCQs

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    public Question() {}

    public Question(String text, String type, int points, String choices) {
        this.text = text;
        this.type = type;
        this.points = points;
        this.choices = choices;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getChoices() { return choices; }
    public void setChoices(String choices) { this.choices = choices; }

    public Assessment getAssessment() { return assessment; }
    public void setAssessment(Assessment assessment) { this.assessment = assessment; }
}
