package com.yellesdev.jenkins_ci_practice.commissioners.rest.api.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record CommissionerDTO(
        Long id,
        String no,
        String name,
        AddressDTO address
) implements Serializable { }
