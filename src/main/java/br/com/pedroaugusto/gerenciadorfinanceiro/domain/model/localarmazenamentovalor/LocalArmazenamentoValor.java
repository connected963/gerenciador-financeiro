package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "local_armazenamento_valor")
public class LocalArmazenamentoValor {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double valor;

    @Column
    private LocalDateTime data;

    @JoinColumn
    @ManyToOne
    private LocalArmazenamento localArmazenamento;

    private LocalArmazenamentoValor() {

    }

    LocalArmazenamentoValor(final Double valor, final LocalArmazenamento localArmazenamento) {
        this.valor = valor;
        this.data = LocalDateTime.now();
        this.localArmazenamento = localArmazenamento;
    }

    LocalArmazenamentoValor(final Long id, final Double valor, final LocalDateTime data,
                            final LocalArmazenamento localArmazenamento) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.localArmazenamento = localArmazenamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalArmazenamentoValor that = (LocalArmazenamentoValor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(data, that.data) &&
                Objects.equals(localArmazenamento, that.localArmazenamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, data, localArmazenamento);
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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
                .append("data", data)
                .append("localArmazenamento", localArmazenamento)
                .toString();
    }
}
