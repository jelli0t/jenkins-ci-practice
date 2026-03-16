package com.yellesdev.jenkins_ci_practice.commissioners.rest.api;


import com.yellesdev.jenkins_ci_practice.commissioners.rest.api.dto.CommissionerDTO;
import com.yellesdev.jenkins_ci_practice.common.dto.MultipleDTO;

public interface CommissionersApi {


    CommissionerDTO getById(Long id);

    MultipleDTO<CommissionerDTO> getAll();
}
