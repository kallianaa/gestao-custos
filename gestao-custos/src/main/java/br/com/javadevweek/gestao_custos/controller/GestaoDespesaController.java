package br.com.javadevweek.gestao_custos.controller;

import br.com.javadevweek.gestao_custos.custom_messages.ErrorMessage;
import br.com.javadevweek.gestao_custos.enitity.Despesas;
import br.com.javadevweek.gestao_custos.useCases.BuscarDespesaUseCase;
import br.com.javadevweek.gestao_custos.useCases.CadastroDespesaUseCase;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestao")
public class GestaoDespesaController {

    // cadastro de despesas
    // criar tabela no banco de dados
    // criar entidade

    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;

    @Autowired
    BuscarDespesaUseCase buscarDespesaUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Despesas despesas) {

        try {
            var result = cadastroDespesaUseCase.execute(despesas);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            var errorMessage = new ErrorMessage(e.getMessage(), "INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMessage);
        }
    }

    // /gestao/find/INSERIR EMAIL AQUI?data=2026-07
    @GetMapping("/{email}")
    public List<Despesas> findyByEmailAndDate(@PathVariable String email,
            @RequestParam(required = false) LocalDate data) {
        return buscarDespesaUseCase.execute(email, data);
    }
}
