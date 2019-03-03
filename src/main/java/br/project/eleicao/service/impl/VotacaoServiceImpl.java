/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.service.impl;

import br.project.eleicao.dao.VotacaoDAO;
import br.project.eleicao.domain.Votacao;
import br.project.eleicao.service.EleicaoService;
import br.project.eleicao.service.EleitorService;
import br.project.eleicao.service.VotacaoService;
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
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    private VotacaoDAO votacaoDAO;

    @Autowired
    private EleicaoService eleicaoService;

    @Autowired
    private EleitorService eleitorService;



    @Override
    public void salvar(Votacao votacao, long eleicaoId, long eleitorId, String protocolo) {

        votacao.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        votacao.setEleitor(eleitorService.recuperarPorId(eleitorId));
        votacao.setProtocolo(protocolo);
        votacaoDAO.salvar(votacao);
    }

    @Override
    public List<Votacao> recuperarPorEleicaoId(long eleicaoId) {
        return votacaoDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    public int recuperarPorId(long eleicaoId) {
        return votacaoDAO.recuperarPorId(eleicaoId);
    }


}
