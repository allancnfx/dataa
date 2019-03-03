package br.project.eleicao.service.impl;

import br.project.eleicao.dao.CandidatoDAO;
import br.project.eleicao.domain.Candidato;
import br.project.eleicao.service.CandidatoService;
import br.project.eleicao.service.EleicaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Allan
 *
 * @Service para indicar que é uma classe de serviço.
 *
 * @Transactional indica ao Spring que ele deve gerenciar as transações dos
 * métodos dessa classe.
 *
 * @Autowired informa que a classe passa a depender da DAO e essa dependência
 * será resolvida pelo Spring, via injeção de dependência.
 *
 * Os métodos. o código desta classe faz um “mapeamento”, chamando os
 * respectivos métodos do DAO para salvar, recuperar, atualizar e excluir dados.
 *
 * @Transactional(readOnly = true) indica ao Spring que as transações abertas
 * nesses métodos são apenas para leitura.
 */
@Service
@Transactional
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoDAO candidatoDAO;

    @Autowired
    private EleicaoService eleicaoService;

    @Override
    public void salvar(Candidato candidato, long eleicaoId) {
        candidato.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        candidatoDAO.salvar(candidato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidato> recuperarPorEleicaoId(long eleicaoId) {
        return candidatoDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidato> recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId) {
        return candidatoDAO.recuperarPorEleicaoIdECargoId(eleicaoId, cargoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Candidato recuperarPorEleicaoIdECandidatoId(long eleicaoId, long candidatoId) {
        return candidatoDAO.recuperarPorEleicaoIdECandidatoId(eleicaoId, candidatoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Candidato recuperarPorEleicaoIdECargoIdECandidatoId(long eleicaoId, long cargoId, long candidatoId) {
        return candidatoDAO.recuperarPorEleicaoIdECargoIdECandidatoId(eleicaoId, cargoId, candidatoId);
    }

    @Override
    public void atualizar(Candidato candidato, long eleicaoId) {
        candidato.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        candidatoDAO.atualizar(candidato);
    }

    @Override
    public void excluir(long eleicaoId, long candidatoId) {
        candidatoDAO.excluir(recuperarPorEleicaoIdECandidatoId(eleicaoId, candidatoId).getId());
    }

    @Override
    public Candidato recuperarPorId(long id) {
        return candidatoDAO.recuperarPorId(id);
    }

}
