package com.yellesdev.jenkins_ci_practice.partners.rest.api;

import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerCreateDTO;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(
        name = "Partners API",
        description = "Endpoints used to manage Partners"
)
@RequestMapping("/v1/partners")
public interface PartnersApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    PartnerDTO create(@Valid @RequestBody PartnerCreateDTO createDTO);
}
