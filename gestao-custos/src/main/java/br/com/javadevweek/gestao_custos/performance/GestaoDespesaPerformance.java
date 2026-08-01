package br.com.javadevweek.gestao_custos.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javadevweek.gestao_custos.enitity.Despesas;
import br.com.javadevweek.gestao_custos.repository.DespesaRepository;


@RequestMapping("/gestao/performance")
@RestController
@EnableCaching
public class GestaoDespesaPerformance {

    @Autowired
    DespesaRepository repository;

    @GetMapping("/sem-paginacao")
    public ResponseEntity<List<Despesas>> listarSemPaginacao() {

        long inicio = System.currentTimeMillis();
        var despesa = repository.findAll();

        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (fim - inicio) + "ms");

        return ResponseEntity.ok(despesa);
    }

    @GetMapping("/com-paginacao/{email}") // localhost:8080/com-paginacao?page=0&size=10
    public ResponseEntity<Page<Despesas>> listarComPaginacao(@PathVariable String email, Pageable pageable) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var despesa = repository.findByEmail(email, pageable);
        stopWatch.stop();

        System.out.println("Tempo de execução: " + stopWatch.getTotalTimeMillis() + "ms");
        return ResponseEntity.ok(despesa);
    }

    @Cacheable(value = "gastosPorEmailCache", key = "#email + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-'")
    @GetMapping("/cache/{email}")
    public ResponseEntity<Page<Despesas>> cacheComPaginacao(@PathVariable String email, Pageable pageable) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var despesa = repository.findByEmail(email, pageable);
        stopWatch.stop();

        System.out.println("Tempo de execução: " + stopWatch.getTotalTimeMillis() + "ms");
        return ResponseEntity.ok(despesa);
    }

}
