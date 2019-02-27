package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.EleicaoDAO;
import br.project.eleicao.domain.Eleicao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EleicaoDaoImpl implements EleicaoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Eleicao eleicao) {
        em.persist(eleicao);
    }

    @Override
    public List<Eleicao> recuperar() {
        return em.createQuery("select p from Eleicao p", Eleicao.class).getResultList();
    }

    @Override
    public Eleicao recuperarPorID(long id) {
        return em.find(Eleicao.class, id);
    }

    @Override
    public void atualizar(Eleicao eleicao) {
        em.merge(eleicao);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Eleicao.class, id));
    }
}
