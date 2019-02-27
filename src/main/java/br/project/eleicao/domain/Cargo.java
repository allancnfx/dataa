package br.project.eleicao.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Size;

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

   
    @ManyToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;

   

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

    /**
     * @return the candidato
     */
    public Collection<Candidato> getCandidato() {
        return candidato;
    }

    /**
     * @param candidato the candidato to set
     */
    public void setCandidato(List<Candidato> candidato) {
        this.candidato = candidato;
    }

}
