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
    public void salvar(Votacao votacao, long eleicaoId, long eleitorId) {
        votacao.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        votacaoDAO.salvar(votacao);
    }

    @Override
    public List<Votacao> recuperarPorEleicaoId(long eleicaoId) {
        return votacaoDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    public int recuperar(long eleicaoId) {
        return votacaoDAO.recuperar(eleicaoId);
    }
}
