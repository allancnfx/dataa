package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.EleitorDAO;
import br.project.eleicao.domain.Eleitor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    @Override
    public Eleitor recuperarPorId(long id) {
        return em.find(Eleitor.class, id);
    }
}
