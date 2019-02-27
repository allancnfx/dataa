/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.service.impl;

import br.project.eleicao.dao.EleicaoDAO;
import br.project.eleicao.domain.Eleicao;
import br.project.eleicao.service.EleicaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EleicaoServiceImpl implements EleicaoService {

    @Autowired
    private EleicaoDAO eleicaoDAO;

    @Override
    public void salvar(Eleicao eleicao) {
        eleicaoDAO.salvar(eleicao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Eleicao> recuperar() {
        return eleicaoDAO.recuperar();
    }

    @Override
    @Transactional(readOnly = true)
    public Eleicao recuperarPorId(long id) {
        return eleicaoDAO.recuperarPorID(id);
    }

    @Override
    public void atualizar(Eleicao eleicao) {
        eleicaoDAO.atualizar(eleicao);
    }

    @Override
    public void excluir(long id) {
        eleicaoDAO.excluir(id);
    }
}
