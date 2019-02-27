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
