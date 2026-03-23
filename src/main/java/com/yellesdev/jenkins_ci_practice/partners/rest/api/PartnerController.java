package com.yellesdev.jenkins_ci_practice.partners.rest.api;

import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerCreateDTO;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerDTO;
import com.yellesdev.jenkins_ci_practice.partners.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartnerController implements PartnersApi {

    private final PartnerService service;

    @Override
    public PartnerDTO create(PartnerCreateDTO createDTO) {
        return service.add(createDTO);
    }
}
