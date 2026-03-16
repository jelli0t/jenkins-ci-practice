package com.yellesdev.jenkins_ci_practice;

import com.yellesdev.jenkins_ci_practice.commissioners.rest.api.CommissionersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommissionersController.class)
class JenkinsCiPracticeUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void Should_returnOK_When_CallCommissionersByIdTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/commissioners/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());
    }
}
