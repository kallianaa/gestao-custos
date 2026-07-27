package br.com.javadevweek.gestao_custos.repository;

import br.com.javadevweek.gestao_custos.enitity.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DespesaRepository extends JpaRepository <Despesas, UUID>{

}
