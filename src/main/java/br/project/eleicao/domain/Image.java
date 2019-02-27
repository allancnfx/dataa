/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Allan
 */
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Image() {
    }

    public Image(String name) {
        this.name = name;
    }
    
    @OneToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;
    
    
    @OneToOne
    @JoinColumn(name = "id_candidato_fk")
    private Candidato candidato;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
