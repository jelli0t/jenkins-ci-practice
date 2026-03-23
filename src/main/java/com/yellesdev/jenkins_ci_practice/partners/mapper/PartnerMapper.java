package com.yellesdev.jenkins_ci_practice.partners.mapper;

import com.yellesdev.jenkins_ci_practice.partners.repository.entity.Partner;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerCreateDTO;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartnerMapper {

    @Mapping(target = "id", ignore = true)
    Partner map(PartnerCreateDTO createDTO);

    PartnerDTO map(Partner partner);
}
