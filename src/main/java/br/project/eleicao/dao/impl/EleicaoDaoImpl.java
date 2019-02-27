package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.EleicaoDAO;
import br.project.eleicao.domain.Eleicao;
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
