package com.app.utility.me.router;

import com.app.utility.me.core.usecase.GetCepInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CepRouter {

    private final GetCepInfo getCepInfo;

    @GetMapping("/cep")
    public ResponseEntity<GetCepInfo.CepInfo> getCepInfo(
            @Valid
            @NonNull
            @NotEmpty
            @Size(min = 8, max = 8)
            @RequestParam("cep")
            String cep) {

            return ResponseEntity.ok(getCepInfo.apply(cep));
    }

}
