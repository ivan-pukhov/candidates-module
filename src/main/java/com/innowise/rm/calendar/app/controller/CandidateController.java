package com.innowise.rm.calendar.app.controller;

import com.innowise.rm.calendar.app.controller.dto.CandidateDTO;
import com.innowise.rm.calendar.app.controller.mapper.CandidateMapper;
import com.innowise.rm.calendar.app.service.api.CandidateService;
import com.innowise.rm.calendar.app.service.impl.CandidateServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private CandidateService candidateService;
    private CandidateMapper candidateMapper;
    private CandidateServiceImpl candidateServiceImpl;

    @Autowired
    public CandidateController(final CandidateService candidateService, final CandidateMapper candidateMapper,
                               final CandidateServiceImpl candidateServiceImpl){
        this.candidateService = candidateService;
        this.candidateMapper = candidateMapper;
        this.candidateServiceImpl = candidateServiceImpl;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds candidate for given identifier")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable final Long id) {
        return ResponseEntity.ok(candidateMapper.toDTO(candidateService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + id))));
    }

    @PutMapping
    @ApiOperation(value = "Save Candidate", produces = "application/json")
    public ResponseEntity<CandidateDTO> save(
            @ApiParam(value = "Json body with the Candidate object", required = true)
            @RequestBody final CandidateDTO candidateDTO) {
        return ResponseEntity.ok(candidateMapper.toDTO(
                candidateService.save(candidateMapper.fromDTO(candidateDTO))));
    }

    @PutMapping("/reset")
    @ApiOperation(value = "Reset candidate", produces = "application/json")
    public void reset(
            @ApiParam(value = "Json body with the Candidate object", required = true)
            @RequestBody final CandidateDTO candidateDTO) {
        candidateService.reset(candidateMapper.fromDTO(candidateDTO));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update candidate", produces = "application/json")
    public ResponseEntity<CandidateDTO> update(
            @ApiParam(value = "Json body with the Candidate object", required = true)
            @RequestBody final CandidateDTO candidateDTO) {
        return ResponseEntity.ok(candidateMapper.toDTO(
                candidateService.update(candidateMapper.fromDTO(candidateDTO))));
    }

    @PutMapping("/fill")
    @ApiOperation("Fill database")
    public void fill() {
        candidateServiceImpl.fillDatabase();
    }
}
