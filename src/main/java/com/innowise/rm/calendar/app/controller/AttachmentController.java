package com.innowise.rm.calendar.app.controller;

import com.innowise.rm.calendar.app.controller.dto.AttachmentDTO;
import com.innowise.rm.calendar.app.controller.mapper.AttachmentMapper;
import com.innowise.rm.calendar.app.service.api.AttachmentService;
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
@RequestMapping("/attachment")
public class AttachmentController {

    private AttachmentService attachmentService;

    private AttachmentMapper attachmentMapper;

    @Autowired
    public AttachmentController(final AttachmentService attachmentService,
                                final AttachmentMapper attachmentMapper) {
        this.attachmentService = attachmentService;
        this.attachmentMapper = attachmentMapper;
    }

    @GetMapping("/candidate/{candidateId}")
    @ApiOperation(value = "Finds list of attachments for given candidate identifier")
    public ResponseEntity<List<AttachmentDTO>> getAttachmentsByCandidateId(@PathVariable final Long candidateId) {
        return ResponseEntity.ok(attachmentMapper.toListDTO(attachmentService.getAttachmentsByCandidateId(candidateId)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds attachment for given identifier")
    public ResponseEntity<AttachmentDTO> getAttachmentById(@PathVariable final Long id) {
        return ResponseEntity.ok(attachmentMapper.toDTO(attachmentService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id " + id))));
    }

    @PutMapping
    @ApiOperation(value = "Save Attachment", produces = "application/json")
    public ResponseEntity<AttachmentDTO> save(
            @ApiParam(value = "Json body with the Attachment object", required = true)
            @RequestBody final AttachmentDTO attachmentDTO) {
        return ResponseEntity.ok(attachmentMapper.toDTO(
                attachmentService.save(attachmentMapper.fromDTO(attachmentDTO))));
    }

    @PutMapping("/reset")
    @ApiOperation(value = "Reset Attachment", produces = "application/json")
    public void reset(
            @ApiParam(value = "Json body with the Attachment object", required = true)
            @RequestBody final AttachmentDTO attachmentDTO) {
        attachmentService.reset(attachmentMapper.fromDTO(attachmentDTO));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Attachment", produces = "application/json")
    public ResponseEntity<AttachmentDTO> update(
            @ApiParam(value = "Json body with the Attachment object", required = true)
            @RequestBody final AttachmentDTO attachmentDTO) {
        return ResponseEntity.ok(attachmentMapper.toDTO(
                attachmentService.update(attachmentMapper.fromDTO(attachmentDTO))));
    }
}
