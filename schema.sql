CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    name VARCHAR(255)
);

CREATE TABLE assessment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    deadline VARCHAR(50)
);

CREATE TABLE question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(1000),
    type VARCHAR(50),
    points INT,
    choices TEXT,
    assessment_id BIGINT,
    CONSTRAINT fk_question_assessment FOREIGN KEY (assessment_id) REFERENCES assessment(id) ON DELETE CASCADE
);

CREATE TABLE submission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    total_score INT,
    graded BOOLEAN,
    assessment_id BIGINT,
    CONSTRAINT fk_submission_assessment FOREIGN KEY (assessment_id) REFERENCES assessment(id)
);

CREATE TABLE answer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    response TEXT,
    question_id BIGINT,
    submission_id BIGINT,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES question(id),
    CONSTRAINT fk_answer_submission FOREIGN KEY (submission_id) REFERENCES submission(id)
);
