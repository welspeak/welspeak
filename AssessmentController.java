package com.assessment.sasing.controller;

import com.assessment.sasing.model.Assessment;
import com.assessment.sasing.model.Question;
import com.assessment.sasing.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentRepository assessmentRepository;

 
    @GetMapping
    public List<Assessment> getAll() {
        return assessmentRepository.findAll();
    }

    // GET a single assessment by ID
    @GetMapping("/{id}")
    public Assessment getOne(@PathVariable Long id) {
        return assessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found"));
    }

    // CREATE a new assessment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Assessment create(@RequestBody Assessment assessment) {
        if (assessment.getQuestions() != null) {
            for (Question q : assessment.getQuestions()) {
                q.setAssessment(assessment); // link each question to the parent assessment
            }
        }
        return assessmentRepository.save(assessment);
    }

    // DELETE an assessment by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!assessmentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found");
        }
        assessmentRepository.deleteById(id);
    }
}
