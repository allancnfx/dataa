package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.CandidatoDAO;
import br.project.eleicao.domain.Candidato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Allan
 *
 * @Repository informa ao framework que esta classe se trata de um repositório,
 * componente responsável pelo acesso a dados em um banco de dados.
 *
 * @PersistenceContext. define para o container do Spring a responsabilidade de
 * gerenciar a dependência de um entity manager.
 *
 * método salvar(). Esse método recebe um objeto e, utilizando o objeto `em`,
 * chamamos o método persist().
 *
 * método recuperarPorEleicaoId(). execulta select para retornar todos os dados
 * cadastrados relacionado à eleição;
 *
 * método atualizar(). Chamamos o merge() do entity manager (em) para atualizar
 * os dados;
 *
 * Código do método remover(). Apenas o chamamos, passando como parâmetro uma
 * referência da do objeto que deve ser excluído.
 */
@Repository
public class CandidatoDaoImpl implements CandidatoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Candidato candidato) {
        em.persist(candidato);
    }

    @Override
    public List<Candidato> recuperarPorEleicaoId(long eleicaoId) {
        return em.createQuery("select c from Candidato c where c.eleicao.id = :eleicaoId", Candidato.class)
                .setParameter("eleicaoId", eleicaoId)
                .getResultList();
    }

    @Override
    public Candidato recuperarPorEleicaoIdECandidatoId(long eleicaoId, long candidatoId) {
        return em.createQuery("select c from Candidato c where c.eleicao.id = :eleicaoId and c.id = :candidatoId", Candidato.class)
                .setParameter("eleicaoId", eleicaoId)
                .setParameter("candidatoId", candidatoId)
                .getSingleResult();
    }

    @Override
    public List<Candidato> recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId) {
        return em.createQuery("select c from Candidato c where c.eleicao.id = :eleicaoId and c.cargo.id = :cargoId", Candidato.class)
                .setParameter("eleicaoId", eleicaoId)
                .setParameter("cargoId", cargoId)
                .getResultList();
    }

    @Override
    public Candidato recuperarPorEleicaoIdECargoIdECandidatoId(long eleicaoId, long cargoId, long candidatoId) {
        return em.createQuery("select c from Candidato c where c.eleicao.id = :eleicaoId and c.cargo.id = :cargoId and c.id = candidatoId", Candidato.class)
                .setParameter("eleicaoId", eleicaoId)
                .setParameter("cargoId", cargoId)
                .setParameter("candidatoId", candidatoId)
                .getSingleResult();
    }

    @Override
    public void atualizar(Candidato candidato) {
        em.merge(candidato);
    }

    @Override
    public void excluir(long candidatoId) {
        em.remove(em.getReference(Candidato.class, candidatoId));
    }

}
