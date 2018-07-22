package br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamentovalor;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class LocalArmazenamentoValorInputModel {

    private final Long id;

    @NotNull(message = "localarmazenamentovalor.valor.null")
    @Min(value = 0, message = "localarmazenamentovalor.valor.min")
    private final Double valor;

    @JsonFormat(pattern = "dd/MM/YYYY HH:mm")
    @NotNull(message = "localarmazenamentovalor.data.null")
    private final LocalDateTime data;

    @NotNull(message = "localarmazenamentovalor.localArmazenamentoId.null")
    private final Long localArmazenamentoId;

    public LocalArmazenamentoValorInputModel(final Long id, final Double valor,
                                             final LocalDateTime data,
                                             final Long localArmazenamentoId) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.localArmazenamentoId = localArmazenamentoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalArmazenamentoValorInputModel that = (LocalArmazenamentoValorInputModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(data, that.data) &&
                Objects.equals(localArmazenamentoId, that.localArmazenamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, data, localArmazenamentoId);
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Long getLocalArmazenamentoId() {
        return localArmazenamentoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("valor", valor)
                .append("data", data)
                .append("localArmazenamentoId", localArmazenamentoId)
                .toString();
    }
}
