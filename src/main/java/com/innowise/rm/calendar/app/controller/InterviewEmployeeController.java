package com.innowise.rm.calendar.app.controller;

import com.innowise.rm.calendar.app.controller.dto.InterviewEmployeeDTO;
import com.innowise.rm.calendar.app.controller.mapper.InterviewEmployeeMapper;
import com.innowise.rm.calendar.app.service.api.InterviewEmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/feedback")
public class InterviewEmployeeController {

    private InterviewEmployeeService interviewEmployeeService;
    private InterviewEmployeeMapper interviewEmployeeMapper;

    @Autowired
    public InterviewEmployeeController(final InterviewEmployeeService interviewEmployeeService,
                                       final InterviewEmployeeMapper interviewEmployeeMapper) {
        this.interviewEmployeeService = interviewEmployeeService;
        this.interviewEmployeeMapper = interviewEmployeeMapper;
    }

    @PutMapping
    @ApiOperation(value = "Save InterviewEmployee", produces = "application/json")
    public ResponseEntity<InterviewEmployeeDTO> save(
            @ApiParam(value = "Json body with the InterviewEmployee object", required = true)
            @RequestBody final InterviewEmployeeDTO interviewEmployeeDTO) {
        return ResponseEntity.ok(interviewEmployeeMapper.toDTO(
                interviewEmployeeService.save(interviewEmployeeMapper.fromDTO(interviewEmployeeDTO))));
    }

    @PutMapping("/reset")
    @ApiOperation("Reset InterviewEmployee")
    public void reset(
            @ApiParam(value = "Json body with the InterviewEmployee object", required = true)
            @RequestBody final InterviewEmployeeDTO interviewEmployeeDTO) {
        interviewEmployeeService.reset(interviewEmployeeMapper.fromDTO(interviewEmployeeDTO));
    }

    @PutMapping("/update")
    @ApiOperation("Update InterviewEmployee")
    public ResponseEntity<InterviewEmployeeDTO> update(
            @ApiParam(value = "Json body with the InterviewEmployee object", required = true)
            @RequestBody final InterviewEmployeeDTO interviewEmployeeDTO) {
        return ResponseEntity.ok(interviewEmployeeMapper.toDTO(
                interviewEmployeeService.update(interviewEmployeeMapper.fromDTO(interviewEmployeeDTO))));
    }

    @GetMapping("/interview/{interviewId}")
    @ApiOperation(value = "Finds list of IntreviewEmployees for given interview identifier", produces = "application/json")
    public ResponseEntity<List<InterviewEmployeeDTO>> getAllByInterviewId(@PathVariable final Long interviewId) {
        return ResponseEntity.ok(interviewEmployeeMapper.toListDTO(interviewEmployeeService.getAllByInterviewId(interviewId)));
    }

    @GetMapping("/employee/{employeeId}")
    @ApiOperation(value = "Finds list of IntreviewEmployees for given employee identifier", produces = "application/json")
    public ResponseEntity<List<InterviewEmployeeDTO>> getAllByEmployeeId(@PathVariable final Long employeeId) {
        return ResponseEntity.ok(interviewEmployeeMapper.toListDTO(interviewEmployeeService.getAllByEmployeeId(employeeId)));
    }
}
