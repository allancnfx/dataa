package br.project.eleicao.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

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
 * @Entity e @Table(name = “candidato”) Informa que a classe representa uma
 * entidade e que no banco de dados ela deve ser mapeada a uma tabela de nome
 * candidato.
 */
@Entity
@Table(name = "candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4, max = 150)
    @Column(nullable = false, length = 150)
    private String nomeCandidato;

    @OneToOne
    @JoinColumn(name = "id_cargo_fk", nullable = false)
    private Cargo cargoId;

    @OneToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Votacao> votacao;

    /**
     * Metodos getters e setters
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public List<Votacao> getVotacao() {
        return votacao;
    }

    public void setVotacao(List<Votacao> votacao) {
        this.votacao = votacao;
    }

}
