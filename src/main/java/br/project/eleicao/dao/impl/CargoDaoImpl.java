package br.project.eleicao.dao.impl;

import br.project.eleicao.dao.CargoDAO;
import br.project.eleicao.domain.Cargo;
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
public class CargoDaoImpl implements CargoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Cargo cargo) {
        em.persist(cargo);
    }

    @Override
    public Cargo recuperarPorID(long id) {
        return em.find(Cargo.class, id);
    }

    @Override
    public List<Cargo> recuperarPorEleicaoId(long eleicaoId) {
        return em.createQuery("select c from Cargo c where c.eleicao.id = :eleicaoId", Cargo.class)
                .setParameter("eleicaoId", eleicaoId)
                .getResultList();
    }

    @Override
    public Cargo recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId) {
        return em.createQuery("select c from Cargo c where c.eleicao.id = :eleicaoId and c.id = :cargoId", Cargo.class)
                .setParameter("eleicaoId", eleicaoId)
                .setParameter("cargoId", cargoId)
                .getSingleResult();
    }

    @Override
    public void atualizar(Cargo cargo) {
        em.merge(cargo);
    }

    @Override
    public void excluir(long cargoId) {
        em.remove(em.getReference(Cargo.class, cargoId));
    }

}
