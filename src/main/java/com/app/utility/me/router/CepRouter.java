package com.app.utility.me.router;

import com.app.utility.me.core.usecase.GetCepInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CepRouter {

    private final GetCepInfo getCepInfo;

    @GetMapping("/cep")
    public ResponseEntity<GetCepInfo.CepInfo> getCepInfo(@RequestParam("cep") String cep) {

            return ResponseEntity.ok(getCepInfo.apply(cep));
    }

}
