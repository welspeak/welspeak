package com.assessment.sasing.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Integer totalScore = 0;
    private Boolean graded = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public Submission() {}

    public Submission(Long studentId, Assessment assessment) {
        this.studentId = studentId;
        this.assessment = assessment;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }

    public Boolean getGraded() { return graded; }
    public void setGraded(Boolean graded) { this.graded = graded; }

    public Assessment getAssessment() { return assessment; }
    public void setAssessment(Assessment assessment) { this.assessment = assessment; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) {
        this.answers.clear();
        if (answers != null) answers.forEach(this::addAnswer);
    }

    public void addAnswer(Answer answer) {
        answer.setSubmission(this);
        this.answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        answer.setSubmission(null);
        this.answers.remove(answer);
    }
}
