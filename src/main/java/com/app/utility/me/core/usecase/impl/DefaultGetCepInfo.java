package com.app.utility.me.core.usecase.impl;


import com.app.utility.me.core.usecase.GetCepInfo;
import com.app.utility.me.repository.ApiClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultGetCepInfo implements GetCepInfo {

    public static final String URL = "https://viacep.com.br/ws/%s/json/";
    private final ApiClientRepository apiClientRepository;



    @Override
    public CepInfo apply(String s) {
        return apiClientRepository.sendGet(String.format(URL, s), CepInfo.class);
    }
}
