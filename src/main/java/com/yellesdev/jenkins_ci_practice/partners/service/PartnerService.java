package com.yellesdev.jenkins_ci_practice.partners.service;

import com.yellesdev.jenkins_ci_practice.partners.mapper.PartnerMapper;
import com.yellesdev.jenkins_ci_practice.partners.repository.PartnerRepository;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerCreateDTO;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerDTO;
import org.springframework.stereotype.Service;

@Service
public record PartnerService(
        PartnerRepository repository,
        PartnerMapper mapper
) {

    public PartnerDTO add(PartnerCreateDTO createDTO) {
        var mapped = mapper.map(createDTO);
        var saved = repository.save(mapped);
        return mapper.map(saved);
    }
}
