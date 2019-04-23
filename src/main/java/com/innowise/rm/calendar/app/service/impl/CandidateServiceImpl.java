package com.innowise.rm.calendar.app.service.impl;

import com.innowise.rm.calendar.app.domain.Attachment;
import com.innowise.rm.calendar.app.domain.Candidate;
import com.innowise.rm.calendar.app.domain.Interview;
import com.innowise.rm.calendar.app.repository.AttachmentRepository;
import com.innowise.rm.calendar.app.repository.CandidateRepository;
import com.innowise.rm.calendar.app.repository.InterviewRepository;
import com.innowise.rm.calendar.app.service.api.CandidateService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;

    private AttachmentRepository attachmentRepository;

    private InterviewRepository interviewRepository;

    @Autowired
    public CandidateServiceImpl(final CandidateRepository candidateRepository,
                                final AttachmentRepository attachmentRepository,
                                final InterviewRepository interviewRepository) {
        this.candidateRepository = candidateRepository;
        this.attachmentRepository = attachmentRepository;
        this.interviewRepository = interviewRepository;
    }


    @Override
    public Candidate save(@NonNull final Candidate candidate) {

        Candidate result = candidate;
        if (candidateRepository.existsById(candidate.getId())) {
            result = candidateRepository.getOne(candidate.getId());
        }
        result.setFirstName(candidate.getFirstName());
        result.setLastName(candidate.getLastName());
        result.setMail(candidate.getMail());
        result.setTelephone(candidate.getTelephone());
        result.setSkype(candidate.getSkype());
        result.setDescription(candidate.getDescription());
        updateAttachments(result, attachmentRepository.findByCandidateId(result.getId()));
        updateInterviews(result, interviewRepository.findByCandidateId(result.getId()));
        return candidateRepository.save(result);
    }

    @Override
    public void reset(@NonNull final Candidate candidate) {

        candidateRepository.delete(candidate);
    }

    @Override
    public Optional<Candidate> findById(@NonNull final Long id) {

        Candidate candidate = candidateRepository.findById(id).orElse(null);
        return Optional.ofNullable(candidate);
    }

    private void updateAttachments(@NonNull final Candidate candidate, @NonNull List<Attachment> attachments) {

        for(Attachment attachment : attachments) {
            attachment.setCandidate(candidate);
        }
        attachmentRepository.saveAll(attachments);
    }

    private void updateInterviews(@NonNull final Candidate candidate, @NonNull List<Interview> interviews) {

        for(Interview interview : interviews) {
            interview.setCandidate(candidate);
        }
        interviewRepository.saveAll(interviews);
    }
}
