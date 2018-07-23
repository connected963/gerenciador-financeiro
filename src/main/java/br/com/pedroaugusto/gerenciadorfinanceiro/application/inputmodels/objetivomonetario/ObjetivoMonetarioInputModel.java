package br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.objetivomonetario;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ObjetivoMonetarioInputModel {

    private Long id;

    @NotNull(message = "objetivomonetario.valor.null")
    @Min(value = 0, message = "objetivomonetario.valor.min")
    private Double valor;

    @NotNull(message = "objetivomonetario.localarmazenamentoid.null")
    private Long localArmazenamentoId;

    private ObjetivoMonetarioInputModel() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetivoMonetarioInputModel that = (ObjetivoMonetarioInputModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(localArmazenamentoId, that.localArmazenamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, localArmazenamentoId);
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public Long getLocalArmazenamentoId() {
        return localArmazenamentoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("valor", valor)
                .append("localArmazenamentoId", localArmazenamentoId)
                .toString();
    }
}
