package com.innowise.rm.calendar.app.controller;

import com.innowise.rm.calendar.app.controller.dto.InterviewDTO;
import com.innowise.rm.calendar.app.controller.mapper.InterviewMapper;
import com.innowise.rm.calendar.app.service.api.InterviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private InterviewService interviewService;
    private InterviewMapper interviewMapper;

    @Autowired
    public InterviewController(final InterviewService interviewService,
                               final InterviewMapper interviewMapper) {
        this.interviewService = interviewService;
        this.interviewMapper = interviewMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds interview for given identifier", produces = "application/json")
    public ResponseEntity<InterviewDTO> getInterview(@PathVariable final Long id) {
        return ResponseEntity.ok(interviewMapper.toDTO(interviewService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + id))));
    }

    @PutMapping
    @ApiOperation(value = "Save Interview", produces = "application/json")
    public ResponseEntity<InterviewDTO> saveInterview(
            @ApiParam(value = "Json body with the Interview object", required = true)
            @RequestBody final InterviewDTO interviewDTO) {
        return ResponseEntity.ok(interviewMapper.toDTO(
                interviewService.save(interviewMapper.fromDTO(interviewDTO))));
    }

    @PutMapping("/reset")
    @ApiOperation("Reset Interview")
    public void resetInterview(
            @ApiParam(value = "Json body with the Interview object", required = true)
            @RequestBody final InterviewDTO interviewDTO) {
        interviewService.reset(interviewMapper.fromDTO(interviewDTO));
    }

    @PutMapping("/update")
    @ApiOperation("Update Interview")
    public ResponseEntity<InterviewDTO> updateInterview(
            @ApiParam(value = "Json body with the Interview object", required = true)
            @RequestBody final InterviewDTO interviewDTO) {
        return ResponseEntity.ok(interviewMapper.toDTO(
                interviewService.update(interviewMapper.fromDTO(interviewDTO))));
    }

    @GetMapping("/candidate/{candidateId}")
    @ApiOperation(value = "Finds list of interviews for given candidate identifier", produces = "application/json")
    public ResponseEntity<List<InterviewDTO>> getInterviewsByCandidateId(@PathVariable final Long candidateId) {
        return ResponseEntity.ok(interviewMapper.toListDTO(interviewService.getInterviewsByCandidateId(candidateId)));
    }
}
