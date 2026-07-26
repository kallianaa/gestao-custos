package br.com.javadevweek.gestao_custos.controller;

import br.com.javadevweek.gestao_custos.enitity.Despesas;
import br.com.javadevweek.gestao_custos.useCases.CadastroDespesaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gestao")
public class GestaoDespesaController {

    //cadastro de despesas
    //criar tabela no banco de dados
    //criar entidade

    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;

    @PostMapping("/create")
    public void create(@RequestBody Despesas despesas){

        cadastroDespesaUseCase.execute(despesas);
    }
}
