package br.com.javadevweek.gestao_custos.repository;

import br.com.javadevweek.gestao_custos.enitity.Despesas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DespesaRepository extends JpaRepository <Despesas, UUID>{

    List<Despesas> findByEmail(String email);

    List<Despesas> findByEmailAndData(String email, LocalDate data);

    Page<Despesas> findByEmail(String email, Pageable pageable);

}
