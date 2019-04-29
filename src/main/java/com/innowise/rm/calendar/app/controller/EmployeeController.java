package com.innowise.rm.calendar.app.controller;

import com.innowise.rm.calendar.app.controller.dto.EmployeeDTO;
import com.innowise.rm.calendar.app.controller.mapper.EmployeeMapper;
import com.innowise.rm.calendar.app.service.api.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(final EmployeeService employeeService,
                              final EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds employee for given identifier", produces = "application/json")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable final Long id) {
        return ResponseEntity.ok(employeeMapper.toDTO(employeeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + id))));
    }

    @GetMapping("/department/{departmentId}")
    @ApiOperation(value = "Finds list of employees for given department identifier", produces = "application/json")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartmentId(@PathVariable final Long departmentId) {
        return ResponseEntity.ok(employeeMapper.toListDTO(employeeService.getEmployeesByDepartmentId(departmentId)));
    }

    @PutMapping
    @ApiOperation(value = "Save Employee", produces = "application/json")
    public ResponseEntity<EmployeeDTO> save(
            @ApiParam(value = "Json body with the Employee object", required = true)
            @RequestBody final EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeMapper.toDTO(
                employeeService.save(employeeMapper.fromDTO(employeeDTO))));
    }

    @PutMapping("/reset")
    @ApiOperation("Reset Employee")
    public void reset(
            @ApiParam(value = "Json body with the Employee object", required = true)
            @RequestBody final EmployeeDTO employeeDTO) {
        employeeService.reset(employeeMapper.fromDTO(employeeDTO));
    }

    @PutMapping("/update")
    @ApiOperation("Update Employee")
    public ResponseEntity<EmployeeDTO> update(
            @ApiParam(value = "Json body with the Employee object", required = true)
            @RequestBody final EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeMapper.toDTO(
                employeeService.update(employeeMapper.fromDTO(employeeDTO))));
    }

}
