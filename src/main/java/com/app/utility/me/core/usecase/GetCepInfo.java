package com.app.utility.me.core.usecase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.function.Function;

@FunctionalInterface
public interface GetCepInfo extends Function<String, GetCepInfo.CepInfo> {

    @JsonIgnoreProperties(ignoreUnknown = true)
    record CepInfo(String cep, String logradouro, String estado, String localidade, String uf) {
    }

}
