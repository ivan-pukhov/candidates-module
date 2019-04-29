package com.innowise.rm.calendar.app.controller;

import com.innowise.rm.calendar.app.controller.dto.DepartmentDTO;
import com.innowise.rm.calendar.app.controller.mapper.DepartmentMapper;
import com.innowise.rm.calendar.app.service.api.DepartmentService;
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

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(final DepartmentService departmentService,
                                final DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds department for given identifier", produces = "application/json")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable final Long id) {
        return ResponseEntity.ok(departmentMapper.toDTO(departmentService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + id))));
    }

    @PutMapping
    @ApiOperation(value = "Save Department", produces = "application/json")
    public ResponseEntity<DepartmentDTO> save(
            @ApiParam(value = "Json body with the Department object", required = true)
            @RequestBody final DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentMapper.toDTO(
                departmentService.save(departmentMapper.fromDTO(departmentDTO))));
    }

    @PutMapping("/reset")
    @ApiOperation("Reset Department")
    public void reset(
            @ApiParam(value = "Json body with the Department object", required = true)
            @RequestBody final DepartmentDTO departmentDTO) {
        departmentService.reset(departmentMapper.fromDTO(departmentDTO));
    }

    @PutMapping("/update")
    @ApiOperation("Update Department")
    public ResponseEntity<DepartmentDTO> update(
            @ApiParam(value = "Json body with the Department object", required = true)
            @RequestBody final DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentMapper.toDTO(
                departmentService.update(departmentMapper.fromDTO(departmentDTO))));
    }

}
