package br.project.eleicao.domain;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "eleitor")
public class Eleitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4, max = 60)
    @Column(nullable = false, length = 60)
    private String nomeEleitor;
    
    @NotBlank
    @Size(min = 4, max = 60)
    @Column(nullable = false, length = 60)
    private String cpfEleitor;
    
    
    @OneToMany(mappedBy = "eleitor", cascade = CascadeType.ALL)
    private List<Votacao> votacao;

    @ManyToOne
    @JoinColumn(name = "id_eleicao_fk")
    private Eleicao eleicao;

   

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
