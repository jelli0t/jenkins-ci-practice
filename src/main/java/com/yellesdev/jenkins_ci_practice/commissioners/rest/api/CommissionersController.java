package com.yellesdev.jenkins_ci_practice.commissioners.rest.api;

import com.yellesdev.jenkins_ci_practice.commissioners.rest.api.dto.AddressDTO;
import com.yellesdev.jenkins_ci_practice.commissioners.rest.api.dto.CommissionerDTO;
import com.yellesdev.jenkins_ci_practice.common.dto.MultipleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/commissioners")
public class CommissionersController implements CommissionersApi {

    @Override
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public CommissionerDTO getById(@PathVariable Long id) {
        return CommissionerDTO.builder()
                .id(id)
                .no("101")
                .name("Jenkins Test INC")
                .address(AddressDTO.builder()
                        .street("2341 Wilson Avenue")
                        .city("Brooklyn")
                        .zipcode("11201")
                        .country("United States")
                        .build()
                )
                .build();
    }

    @Override
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public MultipleDTO<CommissionerDTO> getAll() {
        var commissioners = CommissionerDTO.builder()
                .id(1L)
                .no("101")
                .name("Jenkins Test INC")
                .address(AddressDTO.builder()
                        .street("2341 Wilson Avenue")
                        .city("Brooklyn")
                        .zipcode("11201")
                        .country("United States")
                        .build()
                )
                .build();

        return MultipleDTO.<CommissionerDTO>builder()
                .data(List.of(commissioners))
                .page(1)
                .size(1)
                .total(1)
                .build();
    }
}
