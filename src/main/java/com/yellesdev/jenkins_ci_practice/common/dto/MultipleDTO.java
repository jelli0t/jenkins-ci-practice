package com.yellesdev.jenkins_ci_practice.common.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Collection;

@Builder
public record MultipleDTO<T extends Serializable> (
        Collection<T> data,
        Integer size,
        Integer total,
        Integer page
) implements Serializable { }
