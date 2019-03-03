package br.project.eleicao.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
 * @Column(nullable = false, length = ?) coluna não nulla e tamanho definido
 * caracteres
 *
 * @OneToOne relacionamento do tipo 1:1
 *
 * @OneToMany Com o parâmetro mappedBy informamos o nome do atributo na outra
 * ponta CascadeType.ALL operações executadas sobre uma entidade serão
 * propagadas sobre a entidade relacionada.
 *
 * @Entity e @Table(name = “votacao”) Informa que a classe representa uma
 * entidade e que no banco de dados ela deve ser mapeada a uma tabela de nome
 * votacao.
 */
@Entity
@Table(name = "votacao")
public class Votacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;

    @OneToOne
    @JoinColumn(name = "id_eleitor_fk")
    private Eleitor eleitor;

    @ManyToOne
    @JoinColumn(name = "id_candidato_fk")
    private Candidato candidato;
    
    
    @NotBlank
    @Column(nullable = false)
    private String protocolo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public Eleitor getEleitor() {
        return eleitor;
    }

    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }


    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    /**
     * @return the candidato
     */
    public Candidato getCandidato() {
        return candidato;
    }

    /**
     * @param candidato the candidato to set
     */
    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

}
