package com.abeldevelop.petclinic.services.visits.generated.resource;

import java.time.LocalDate;

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
public class VisitResponseResource {

    private Integer id;
    private LocalDate date;
    private String description;
    private Integer petId;
    
}
