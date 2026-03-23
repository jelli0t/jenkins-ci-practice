package com.yellesdev.jenkins_ci_practice.partners.rest.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record PartnerCreateDTO(
        @NotBlank String no,
        @NotBlank String name
) implements Serializable { }
