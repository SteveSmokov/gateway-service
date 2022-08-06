package com.example.gatewayservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name="created")
    private Date created = new Date();
    @LastModifiedDate
    @Column(name="updated")
    private  Date updated = new Date();
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;
}
