/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.VotacaoDAO;
import br.project.eleicao.domain.Votacao;
import java.awt.print.Pageable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
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
    public int recuperarPorId(long eleicaoId) {
        return em.createQuery("select count(v) from Votacao v where v.eleicao.id = :eleicaoId", Votacao.class)
                .setParameter("eleicaoId", eleicaoId)
                .getResultList().size();
    }

    @Override
    public Votacao recuperarPorEleicaoIdEleitorIdCargoId(long eleicaoId, long eleitorId, long cargoId) {
        return em.createQuery("select v from Votacao v where v.eleicao.id = :eleicaoId and v.eleitor.id = :eleitorId and v.cargo.id = :cargoId", Votacao.class)
                .setParameter("eleicaoId", eleicaoId)
                .setParameter("eleitorId", eleitorId)
                .setParameter("cargoId", cargoId)
                .getSingleResult();
    }

    
    @Override
    public List<Votacao> recuperarPorEleicaoIdEleitorIdCargoIdTeste(long eleicaoId, long eleitorId) {
        return em.createQuery("select v  from Votacao v, Cargo c where v.eleicao.id = :eleicaoId and v.eleitor.id = :eleitorId and v.cargo.id = c.id", Votacao.class)
                .setParameter("eleicaoId", eleicaoId)
                .setParameter("eleitorId", eleitorId)
                .getResultList();
    }
}
