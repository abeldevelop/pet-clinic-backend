package com.abeldevelop.petclinic.services.visits.generated.resource;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class VisitRequestResource {

    private Integer vetId;
    private LocalDate date;
    private LocalTime time;
    private String description;
    
}
