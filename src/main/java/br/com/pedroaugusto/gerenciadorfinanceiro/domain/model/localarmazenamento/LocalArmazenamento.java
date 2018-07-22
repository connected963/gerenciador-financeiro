package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "local_armazenamento")
public class LocalArmazenamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @ElementCollection
    @CollectionTable(name = "local_armazenamento_valor")
    private List<LocalArmazenamentoValor> valores;

    private LocalArmazenamento() {

    }

    LocalArmazenamento(String nome) {
        this.nome = nome;
    }

    LocalArmazenamento(final Long id, final String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalArmazenamento that = (LocalArmazenamento) o;
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

    public List<LocalArmazenamentoValor> getValores() {
        return List.copyOf(valores);
    }

    public void setValores(List<LocalArmazenamentoValor> valores) {
        this.valores = valores;
    }

    public void adicionaValor(final LocalArmazenamentoValor localArmazenamentoValor) {
        valores.add(localArmazenamentoValor);
    }

    public void removeValor(final Long localArmazenamentoValorId) {
//        valores.remove(localArmazenamentoValor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .toString();
    }
}
