package com.assessment.sasing.model;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    private Submission submission;

    public Answer() {}

    public Answer(String response, Question question, Submission submission) {
        this.response = response;
        this.question = question;
        this.submission = submission;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public Submission getSubmission() { return submission; }
    public void setSubmission(Submission submission) { this.submission = submission; }
}
