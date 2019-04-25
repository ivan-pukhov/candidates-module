package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.InterviewEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewEmployeeRepository extends JpaRepository<InterviewEmployee, Long> {


    List<InterviewEmployee> findAllFeedbackByInterviewId(Long interviewId);

    List<InterviewEmployee> findAllByEmployeeId(Long employeeId);

    List<InterviewEmployee> findAllByInterviewId(Long interviewId);
}
