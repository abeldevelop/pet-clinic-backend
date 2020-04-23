package com.abeldevelop.petclinic.services.visits.generated.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "visits")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_identification_document", nullable = false)
    private String customerIdentificationDocument;

    @Column(name = "pet_id", nullable = false)
    private Integer petId;
    
    @Column(name = "vet_id")
    private Integer vetId;
    
    @Column(name = "visit_date", nullable = false)
    private LocalDate date;

    @Column(name = "visit_time", nullable = false)
    private LocalTime time;
    
    @Size(max = 8192)
    @Column(name = "description")
    private String description;

}
