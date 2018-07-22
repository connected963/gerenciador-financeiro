package br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.objetivomonetario.ObjetivoMonetario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoMonetarioRepository extends JpaRepository<ObjetivoMonetario, Long> {
}
