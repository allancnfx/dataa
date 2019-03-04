package br.project.eleicao.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Allan
 *
 * Classe de dominio
 *
 * @Id informa que o atributo sera um indificador unico
 *
 * @@GeneratedValue(strategy = GenerationType.IDENTITY) informa que o atributo
 * no banco de dados sera um auto incremento
 *
 * @NotBlank anotação de validação
 *
 * @Size(min = ?, max = ?) define o tamanho minimo e maximo do campo
 *
 * @Column(nullable = false, length = ?) coluna não nulla e tamanho definido
 * caracteres
 *
 * @OneToOne relacionamento do tipo 1:1
 *
 * @OneToMany Com o parâmetro mappedBy informamos o nome do atributo na outra
 * ponta CascadeType.ALL operações executadas sobre uma entidade serão
 * propagadas sobre a entidade relacionada.
 *
 * @Entity e @Table(name = “cargo”) Informa que a classe representa uma entidade
 * e que no banco de dados ela deve ser mapeada a uma tabela de nome cargo.
 */
@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4, max = 60)
    @Column(nullable = false, length = 60)
    private String nomeCargo;

    @OneToMany(mappedBy = "cargoId", cascade = CascadeType.ALL)
    private List<Candidato> candidato;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Votacao> votacao;

    @ManyToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;

    /**
     * Metodos getters e setters
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public Collection<Candidato> getCandidato() {
        return candidato;
    }

    public void setCandidato(List<Candidato> candidato) {
        this.candidato = candidato;
    }

    /**
     * @return the votacao
     */
    public List<Votacao> getVotacao() {
        return votacao;
    }

    /**
     * @param votacao the votacao to set
     */
    public void setVotacao(List<Votacao> votacao) {
        this.votacao = votacao;
    }

}
