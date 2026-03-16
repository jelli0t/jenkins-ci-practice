package com.yellesdev.jenkins_ci_practice.commissioners.rest.api.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AddressDTO(
        String street,
        String city,
        String zipcode,
        String country
) implements Serializable { }
