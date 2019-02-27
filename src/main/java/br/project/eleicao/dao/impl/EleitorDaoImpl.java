package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.EleitorDAO;
import br.project.eleicao.domain.Eleitor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EleitorDaoImpl implements EleitorDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Eleitor eleitor) {
        em.persist(eleitor);
    }

    @Override
    public List<Eleitor> recuperarPorEleicaoId(long eleicaoId) {
        return em.createQuery("select e from Eleitor e where e.eleicao.id = :eleicaoId", Eleitor.class)
                .setParameter("eleicaoId", eleicaoId)
                .getResultList();
    }

    @Override
    public List<Eleitor> recuperarPorEleitorId(long eleitorId) {
        return em.createQuery("select e from Eleitor e where e.eleitor.id = :eleitorId", Eleitor.class)
                .setParameter("eleitor", eleitorId)
                .getResultList();
    }
}
