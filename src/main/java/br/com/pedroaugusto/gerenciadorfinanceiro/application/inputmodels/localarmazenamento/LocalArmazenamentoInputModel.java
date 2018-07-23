package br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamento;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class LocalArmazenamentoInputModel {

    private Long id;

    @NotBlank(message = "localarmazenamento.nome.blank")
    @Length(max = 200, message = "localarmazenamento.nome.max")
    private String nome;

    private LocalArmazenamentoInputModel() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .toString();
    }
}
