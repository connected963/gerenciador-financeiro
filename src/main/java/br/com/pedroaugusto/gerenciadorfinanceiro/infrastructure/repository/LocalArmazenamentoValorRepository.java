package br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalArmazenamentoValorRepository extends JpaRepository<LocalArmazenamentoValor, Long> {

    @Query("select localArmazenamento from LocalArmazenamentoValor localArmazenamento " +
            "where localArmazenamento.localArmazenamento.id = :armazenamentoId")
    List<LocalArmazenamentoValor> buscarTodosPorLocalArmazenamentoId(Long armazenamentoId);

}
