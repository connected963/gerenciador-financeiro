package br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.common.enums.TipoMovimentacao;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MovimentacaoInputModel {

    private Long id;

    @NotNull(message = "movimentacao.tipo.null")
    private TipoMovimentacao tipo;

    @NotNull(message = "movimentacao.valor.null")
    private Double valor;

    private Long localArmazenamentoId;

    @NotNull(message = "movimentacao.repetirmensalmente.null")
    private Boolean repetirMensalmente;

    private MovimentacaoInputModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentacaoInputModel that = (MovimentacaoInputModel) o;
        return Objects.equals(id, that.id) &&
                tipo == that.tipo &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(localArmazenamentoId, that.localArmazenamentoId) &&
                Objects.equals(repetirMensalmente, that.repetirMensalmente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, valor, localArmazenamentoId, repetirMensalmente);
    }

    public Long getId() {
        return id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getLocalArmazenamentoId() {
        return localArmazenamentoId;
    }

    public Boolean getRepetirMensalmente() {
        return repetirMensalmente;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("tipo", tipo)
                .append("valor", valor)
                .append("localArmazenamentoId", localArmazenamentoId)
                .append("repetirMensalmente", repetirMensalmente)
                .toString();
    }
}
