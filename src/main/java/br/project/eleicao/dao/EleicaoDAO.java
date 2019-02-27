package br.project.eleicao.dao;

import br.project.eleicao.domain.Eleicao;

import java.util.List;

public interface EleicaoDAO {

    void salvar(Eleicao eleicao);
    List<Eleicao> recuperar();
    Eleicao recuperarPorID(long id);
    void atualizar(Eleicao eleicao);
    void excluir(long id);
}
