/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.VotacaoDAO;
import br.project.eleicao.domain.Cargo;
import br.project.eleicao.domain.Eleicao;
import br.project.eleicao.domain.Votacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class VotacaoDaoImpl implements VotacaoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Votacao votacao) {
        em.persist(votacao);
    }

    @Override
    public List<Votacao> recuperarPorEleicaoId(long eleicaoId) {
        return em.createQuery("select v from Votacao v where v.eleicao.id = :eleicaoId", Votacao.class)
                .setParameter("eleicaoId", eleicaoId)
                .getResultList();
    }

    @Override
    public int recuperar(long eleicaoId) {
        return em.createQuery("select count(v) from Votacao v where v.eleicao.id = :eleicaoId", Votacao.class)
                .setParameter("eleicaoId", eleicaoId)
                .getResultList().size();
    }



}
