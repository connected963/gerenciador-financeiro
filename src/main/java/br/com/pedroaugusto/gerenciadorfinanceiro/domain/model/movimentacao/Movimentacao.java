package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.common.enums.TipoMovimentacao;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "movimentacao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @Column
    private Double valor;

    @JoinColumn
    @ManyToOne
    private LocalArmazenamento localArmazenamento;

    @Column
    private Boolean repetirMensalmente;

    Movimentacao() {

    }

    Movimentacao(final TipoMovimentacao tipo,
                 final Double valor,
                 final LocalArmazenamento localArmazenamento,
                 final Boolean repetirMensalmente) {
        this(null, tipo, valor, localArmazenamento, repetirMensalmente);
    }

    Movimentacao(final Long id, final TipoMovimentacao tipo,
                 final Double valor, final LocalArmazenamento localArmazenamento,
                 final Boolean repetirMensalmente) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.localArmazenamento = localArmazenamento;
        this.repetirMensalmente = repetirMensalmente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimentacao that = (Movimentacao) o;
        return Objects.equals(id, that.id) &&
                tipo == that.tipo &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(localArmazenamento, that.localArmazenamento) &&
                Objects.equals(repetirMensalmente, that.repetirMensalmente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, valor, localArmazenamento, repetirMensalmente);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
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

    public Boolean getRepetirMensalmente() {
        return repetirMensalmente;
    }

    public void setRepetirMensalmente(Boolean repetirMensalmente) {
        this.repetirMensalmente = repetirMensalmente;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("tipo", tipo)
                .append("valor", valor)
                .append("localArmazenamento", localArmazenamento)
                .append("repetirMensalmente", repetirMensalmente)
                .toString();
    }
}
