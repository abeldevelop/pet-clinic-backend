package com.abeldevelop.petclinic.services.customers.generated.resource;

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
public class PetResponseResource {

    private Integer id;
    private String name;
    private LocalDate birthDate;
    private Integer petTypeId;
    private Integer ownerId;
    
}
