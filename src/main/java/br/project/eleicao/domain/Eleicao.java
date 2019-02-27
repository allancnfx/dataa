package br.project.eleicao.domain;

import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eleicao")
public class Eleicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String nomeEleicao;

    @NotNull(message = "Insira a data de inicio das eleições")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;

    @NotNull(message = "Insira a data de encerramento das eleições")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinal;

    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.ALL)
    private List<Cargo> cargo;

    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.ALL)
    private List<Candidato> candidato;

    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.ALL)
    private List<Eleitor> eleitor;
    
    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.ALL)
    private List<Image> image;



    public String getNomeEleicao() {
        return nomeEleicao;
    }

    public void setNomeEleicao(String nomeEleicao) {
        this.nomeEleicao = nomeEleicao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    public List<Candidato> getCandidato() {
        return candidato;
    }

    public void setCandidato(List<Candidato> candidato) {
        this.candidato = candidato;
    }

    public List<Eleitor> getEleitor() {
        return eleitor;
    }

    public void setEleitor(List<Eleitor> eleitor) {
        this.eleitor = eleitor;
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
