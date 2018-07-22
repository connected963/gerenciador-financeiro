package br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamento;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class LocalArmazenamentoInputModel {

    private final Long id;

    @NotBlank(message = "localarmazenamento.nome.blank")
    @Max(value = 200, message = "localarmazenamento.nome.max")
    private final String nome;

    LocalArmazenamentoInputModel(final Long id, final String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalArmazenamentoInputModel that = (LocalArmazenamentoInputModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .toString();
    }
}
