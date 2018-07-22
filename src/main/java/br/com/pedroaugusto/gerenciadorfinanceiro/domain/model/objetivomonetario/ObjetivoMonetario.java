package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.objetivomonetario;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "objetivo_monetario")
public class ObjetivoMonetario {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double valor;

    @ManyToOne
    @JoinColumn
    private LocalArmazenamento localArmazenamento;

    private ObjetivoMonetario() {

    }

    ObjetivoMonetario(final Double valor, final LocalArmazenamento localArmazenamento) {
        this.id = id;
        this.valor = valor;
        this.localArmazenamento = localArmazenamento;
    }

    ObjetivoMonetario(final Long id, final Double valor, final LocalArmazenamento localArmazenamento) {
        this.id = id;
        this.valor = valor;
        this.localArmazenamento = localArmazenamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetivoMonetario that = (ObjetivoMonetario) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(localArmazenamento, that.localArmazenamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, localArmazenamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalArmazenamento getLocalArmazenamento() {
        return localArmazenamento;
    }

    public void setLocalArmazenamento(LocalArmazenamento localArmazenamento) {
        this.localArmazenamento = localArmazenamento;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("valor", valor)
                .append("localArmazenamento", localArmazenamento)
                .toString();
    }
}
