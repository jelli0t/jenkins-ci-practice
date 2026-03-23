package com.yellesdev.jenkins_ci_practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yellesdev.jenkins_ci_practice.commissioners.rest.api.CommissionersController;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.PartnerController;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerCreateDTO;
import com.yellesdev.jenkins_ci_practice.partners.rest.api.dto.PartnerDTO;
import com.yellesdev.jenkins_ci_practice.partners.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {
        CommissionersController.class,
        PartnerController.class
})
class PartnersControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PartnerService partnerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void Should_returnOK_When_CallCommissionersByIdTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/commissioners/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, 'Test Partner's Name Inc.'",
            "'1012900', null"
            },
            nullValues = {"null", "NULL"}
    )
    void Should_returnBadRequest_When_NonValidPartnerCreating(
            String no, String name
    ) throws Exception {
        var createDTO = new PartnerCreateDTO(no, name);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/partners")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(createDTO))
        ).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1010091', 'Test Partner's Name Inc.'",
            "'1012902', 'Another Test d.o.o.'"
    })
    void Should_ReturnCreated_When_ValidPartnerCreationTest(
            String no, String name
    ) throws Exception {
        var createDTO = new PartnerCreateDTO(no, name);

        when(partnerService.add(eq(createDTO)))
                .thenReturn(new PartnerDTO(1L, no, name));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/partners")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(createDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.no").value(no))
                .andExpect(jsonPath("$.name").value(name));
    }
}
