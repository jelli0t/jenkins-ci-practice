package com.yellesdev.jenkins_ci_practice.partners.rest.api.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record PartnerDTO(
        Long id,
        String no,
        String name
) implements Serializable { }
