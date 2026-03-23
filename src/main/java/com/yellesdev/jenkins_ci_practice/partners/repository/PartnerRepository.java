package com.yellesdev.jenkins_ci_practice.partners.repository;

import com.yellesdev.jenkins_ci_practice.partners.repository.entity.Partner;
import org.springframework.data.repository.CrudRepository;

public interface PartnerRepository extends CrudRepository<Partner, Long> {

}
