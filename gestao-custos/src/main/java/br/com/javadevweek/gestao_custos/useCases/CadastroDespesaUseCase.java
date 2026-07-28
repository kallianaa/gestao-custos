package br.com.javadevweek.gestao_custos.useCases;

import br.com.javadevweek.gestao_custos.enitity.Despesas;
import br.com.javadevweek.gestao_custos.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CadastroDespesaUseCaseService {
    
}
public class CadastroDespesaUseCase {
    //SOLID
    //Single Responsability Principle

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesas execute(Despesas despesas){

        if(despesas.getCategoria() == null || despesas.getData()== null || despesas.getDescricao() == null ||
                despesas.getEmail() == null){
            throw new IllegalArgumentException("Campos obrigatórios não preenchidos");

        }
        despesas = despesaRepository.save(despesas);
        return despesas;

    }
}
