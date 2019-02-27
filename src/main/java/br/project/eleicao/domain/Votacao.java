package br.project.eleicao.domain;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @OneToOne
    @JoinColumn(name = "id_candidato_fk")
    private Candidato candidato;



    @NotBlank
    @Size(min = 20, max = 20)
    @Column(nullable = false, length = 20, unique = true)
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
     * @return the eleitor
     */
    public Eleitor getEleitor() {
        return eleitor;
    }

    /**
     * @param eleitor the eleitor to set
     */
    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
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
