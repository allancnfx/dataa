package br.project.eleicao.service.impl;

import br.project.eleicao.dao.EleitorDAO;
import br.project.eleicao.domain.Eleitor;
import br.project.eleicao.service.EleicaoService;
import br.project.eleicao.service.EleitorService;
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
public class EleitorServiceImpl implements EleitorService {

    @Autowired
    private EleitorDAO eleitorDAO;

    @Autowired
    private EleicaoService eleicaoService;

    @Override
    public void salvar(Eleitor eleitor, long eleicaoId) {
        eleitor.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        eleitorDAO.salvar(eleitor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Eleitor> recuperarPorEleicaoId(long eleicaoId) {
        return eleitorDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Eleitor> recuperarPorEleitorId(long eleitorId) {
        return eleitorDAO.recuperarPorEleitorId(eleitorId);

    }

}
