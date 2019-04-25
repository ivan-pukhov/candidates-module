package com.innowise.rm.calendar.app.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
abstract class StoredEntity implements Serializable {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

}
