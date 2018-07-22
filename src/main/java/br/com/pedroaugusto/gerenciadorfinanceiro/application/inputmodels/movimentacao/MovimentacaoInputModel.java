package br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.common.enums.TipoMovimentacao;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MovimentacaoInputModel {

    private final Long id;

    @NotNull(message = "movimentacao.tipo.null")
    private final TipoMovimentacao tipo;

    @NotNull(message = "movimentacao.localarmazenamento.null")
    private final Long localArmazenamentoId;

    @NotNull(message = "movimentacao.repetirmensalmente.null")
    private final Boolean repetirMensalmente;

    public MovimentacaoInputModel(final Long id, final TipoMovimentacao tipo,
                                  final Long localArmazenamentoId,
                                  final Boolean repetirMensalmente) {
        this.id = id;
        this.tipo = tipo;
        this.localArmazenamentoId = localArmazenamentoId;
        this.repetirMensalmente = repetirMensalmente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentacaoInputModel that = (MovimentacaoInputModel) o;
        return Objects.equals(id, that.id) &&
                tipo == that.tipo &&
                Objects.equals(localArmazenamentoId, that.localArmazenamentoId) &&
                Objects.equals(repetirMensalmente, that.repetirMensalmente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, localArmazenamentoId, repetirMensalmente);
    }

    public Long getId() {
        return id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
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
                .append("localArmazenamentoId", localArmazenamentoId)
                .append("repetirMensalmente", repetirMensalmente)
                .toString();
    }
}
