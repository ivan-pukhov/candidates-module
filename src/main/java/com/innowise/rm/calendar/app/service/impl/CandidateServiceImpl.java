package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Attachment;
import com.innowise.rm.calendar.app.domain.Candidate;
import com.innowise.rm.calendar.app.domain.Interview;
import com.innowise.rm.calendar.app.repository.AttachmentRepository;
import com.innowise.rm.calendar.app.repository.CandidateRepository;
import com.innowise.rm.calendar.app.repository.DepartmentRepository;
import com.innowise.rm.calendar.app.repository.EmployeeRepository;
import com.innowise.rm.calendar.app.repository.InterviewEmployeeRepository;
import com.innowise.rm.calendar.app.repository.InterviewRepository;
import com.innowise.rm.calendar.app.service.api.CandidateService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;

    private AttachmentRepository attachmentRepository;

    private InterviewRepository interviewRepository;

    private DepartmentRepository departmentRepository;

    private EmployeeRepository employeeRepository;

    private InterviewEmployeeRepository interviewEmployeeRepository;

    @Autowired
    public CandidateServiceImpl(final CandidateRepository candidateRepository,
                                final AttachmentRepository attachmentRepository,
                                final InterviewRepository interviewRepository,
                                final DepartmentRepository departmentRepository,
                                final EmployeeRepository employeeRepository,
                                final InterviewEmployeeRepository interviewEmployeeRepository) {
        this.candidateRepository = candidateRepository;
        this.attachmentRepository = attachmentRepository;
        this.interviewRepository = interviewRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.interviewEmployeeRepository = interviewEmployeeRepository;
    }


    @Override
    public Candidate save(final Candidate candidate) {
        candidate.setFirstName(candidate.getFirstName());
        candidate.setLastName(candidate.getLastName());
        candidate.setMail(candidate.getMail());
        candidate.setTelephone(candidate.getTelephone());
        candidate.setSkype(candidate.getSkype());
        candidate.setDescription(candidate.getDescription());
        candidate.setCreated(LocalDateTime.now());
        return candidateRepository.save(candidate);
    }

    @Override
    public void reset(@NonNull final Candidate candidate) {
        candidateRepository.delete(candidate);
    }

    @Override
    public Candidate update(final Candidate candidate) {
        candidate.setFirstName(candidate.getFirstName());
        candidate.setLastName(candidate.getLastName());
        candidate.setMail(candidate.getMail());
        candidate.setTelephone(candidate.getTelephone());
        candidate.setSkype(candidate.getSkype());
        candidate.setDescription(candidate.getDescription());
        candidate.setUpdated(LocalDateTime.now());
        updateAttachments(candidate, attachmentRepository.findAllByCandidateId(candidate.getId()));
        updateInterviews(candidate, interviewRepository.findAllByCandidateId(candidate.getId()));
        return candidateRepository.save(candidate);
    }

    @Override
    public Optional<Candidate> getById(final Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        return Optional.ofNullable(candidate);
    }

    public void fillDatabase() {
        Candidate candidate1 = Candidate.builder().firstName("Aleksei").lastName("Petrou").mail("abc@mail.ru").skype("1111").description("aaa").telephone("3752912345550").build();
        Candidate candidate2 = Candidate.builder().firstName("Petr").lastName("Alekseeu").mail("abb@mail.ru").skype("1234").description("aba").telephone("3752912345555").build();
        Candidate candidate3 = Candidate.builder().firstName("Petr").lastName("Ivanou").mail("acb@mail.ru").skype("1233").description("ccc").telephone("375291236655").build();
        candidate1.setCreated(LocalDateTime.now());
        candidate2.setCreated(LocalDateTime.now());
        candidate3.setCreated(LocalDateTime.now());
        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);
        candidateRepository.save(candidate3);
/*
        Attachment attachment1 = Attachment.builder().fileName("abc").path("home/aaa/abc").candidate(candidate2).build();
        Attachment attachment2 = Attachment.builder().fileName("abbbb").path("home/aaa/abbbb").candidate(candidate1).build();
        attachment1.setCreated(LocalDateTime.now());
        attachment2.setCreated(LocalDateTime.now());
        attachmentRepository.save(attachment1);
        attachmentRepository.save(attachment2);

        Department department1 = Department.builder().departmentName("Java").build();
        Department department2 = Department.builder().departmentName(".NET").build();
        Department department3 = Department.builder().departmentName("JS").build();
        department1.setCreated(LocalDateTime.now());
        department2.setCreated(LocalDateTime.now());
        department3.setCreated(LocalDateTime.now());
        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);

        Employee employee1 = Employee.builder().firstName("Aleksandr").lastName("Petrou").department(department1).build();
        Employee employee2 = Employee.builder().firstName("Andrei").lastName("Petrou").department(department2).build();
        Employee employee3 = Employee.builder().firstName("Andrei").lastName("Pavlov").department(department2).build();
        employee1.setCreated(LocalDateTime.now());
        employee2.setCreated(LocalDateTime.now());
        employee3.setCreated(LocalDateTime.now());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        Interview interview1 = Interview.builder().candidate(candidate2).interviewDate(LocalDateTime.of(2019,4,15,15,0,0)).status("aaa").build();
        Interview interview2 = Interview.builder().candidate(candidate2).interviewDate(LocalDateTime.of(2019,6,20,15,0,0)).status("aaa").build();
        Interview interview3 = Interview.builder().candidate(candidate3).interviewDate(LocalDateTime.of(2019,4,25,15,0,0)).status("aaa").build();
        interview1.setCreated(LocalDateTime.now());
        interview2.setCreated(LocalDateTime.now());
        interview3.setCreated(LocalDateTime.now());
        interviewRepository.save(interview1);
        interviewRepository.save(interview2);

        InterviewEmployee interviewEmployee1 = InterviewEmployee.builder().interview(interview1).employee(employee2).feedback("ccc").build();
        InterviewEmployee interviewEmployee2 = InterviewEmployee.builder().interview(interview2).employee(employee2).feedback("bbb").build();
        InterviewEmployee interviewEmployee3 = InterviewEmployee.builder().interview(interview3).employee(employee1).feedback("aaa").build();
        interviewEmployee1.setCreated(LocalDateTime.now());
        interviewEmployee2.setCreated(LocalDateTime.now());
        interviewEmployee3.setCreated(LocalDateTime.now());
        interviewEmployeeRepository.save(interviewEmployee1);
        interviewEmployeeRepository.save(interviewEmployee2);
        interviewEmployeeRepository.save(interviewEmployee3);*/
    }

    private void updateAttachments(final Candidate candidate, final List<Attachment> attachments) {
        for(Attachment attachment : attachments) {
            attachment.setCandidate(candidate);
            attachment.setUpdated(LocalDateTime.now());
        }
        attachmentRepository.saveAll(attachments);
    }

    private void updateInterviews(final Candidate candidate, final List<Interview> interviews) {
        for(Interview interview : interviews) {
            interview.setCandidate(candidate);
            interview.setUpdated(LocalDateTime.now());
        }
        interviewRepository.saveAll(interviews);
    }
}
