/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Allan
 */
@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4, max = 150)
    @Column(nullable = false, length = 150)
    private String nomeCandidato;

    @OneToOne
    @JoinColumn(name = "id_cargo_fk")
    private Cargo cargoId;

    @OneToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Votacao> votacao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Image> image;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nomeCandidato
     */
    public String getNomeCandidato() {
        return nomeCandidato;
    }

    /**
     * @param nomeCandidato the nomeCandidato to set
     */
    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    /**
     * @return the cargoId
     */
    public Cargo getCargoId() {
        return cargoId;
    }

    /**
     * @param cargoId the cargoId to set
     */
    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
    }

    /**
     * @return the eleicao
     */
    public Eleicao getEleicao() {
        return eleicao;
    }

    /**
     * @param eleicao the eleicao to set
     */
    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
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

    /**
     * @return the image
     */
    public List<Image> getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(List<Image> image) {
        this.image = image;
    }

}
