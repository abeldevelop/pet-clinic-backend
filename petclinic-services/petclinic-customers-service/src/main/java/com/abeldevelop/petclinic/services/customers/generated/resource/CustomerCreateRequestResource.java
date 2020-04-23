package com.abeldevelop.petclinic.services.customers.generated.resource;

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
public class CustomerCreateRequestResource {

	private String identificationDocument;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    
}
