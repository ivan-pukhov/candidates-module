package com.innowise.rm.calendar.app.repository;

import com.innowise.rm.calendar.app.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository  extends JpaRepository<Interview, Long> {

}
