package br.project.eleicao.domain;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
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
 * @Entity e @Table(name = “eleitor”) Informa que a classe representa uma
 * entidade e que no banco de dados ela deve ser mapeada a uma tabela de nome
 * eleitor.
 */
@Entity
@Table(name = "eleitor")
public class Eleitor implements Serializable {

    /**
     * @Id define que atributo representa um indentifcador unico e
     * @GeneratedValue para indidcar que este atributo seja um auto incremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4, max = 60)
    @Column(nullable = false, length = 60)
    private String nomeEleitor;

    @NotBlank
    @Size(min = 14, max = 14)
    @Column(nullable = false, length = 14)
    private String cpfEleitor;

    @OneToMany(mappedBy = "eleitor", cascade = CascadeType.ALL)
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

    public String getNomeEleitor() {
        return nomeEleitor;
    }

    public void setNomeEleitor(String nomeEleitor) {
        this.nomeEleitor = nomeEleitor;
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

    public String getCpfEleitor() {
        return cpfEleitor;
    }

    public void setCpfEleitor(String cpfEleitor) {
        this.cpfEleitor = cpfEleitor;
    }

}
