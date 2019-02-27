package br.project.eleicao.domain;

import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

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
 * @NotNull campo não pode ser nullo
 * @DateTimeFormat recebe valor formatado
 *
 * @Temporal anotação para campos persistentes ou propriedades do tipo
 * java.util.Date e java.util.Calendar.
 *
 * @Entity e @Table(name = “eleicao”) Informa que a classe representa uma
 * entidade e que no banco de dados ela deve ser mapeada a uma tabela de nome
 * eleicao.
 */
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

    /**
     * Metodos getters e setters
     */
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
}
