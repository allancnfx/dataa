package br.project.eleicao.dao;

import br.project.eleicao.domain.Eleicao;

import java.util.List;

/**
 *
 * @author Allan
 *
 * Classe que provê uma interface que abstrai o acesso a dados; lê e grava a
 * partir da origem de dados (banco de dados) e encapsula o acesso aos dados.
 *
 * Ex: recuperarPorEleicaoId(long eleicaoId); -> recupera todos candidatos
 * relacionados à eleição cadastrado em uma lista.
 *
 */
public interface EleicaoDAO {

    void salvar(Eleicao eleicao);

    List<Eleicao> recuperar();

    Eleicao recuperarPorID(long id);

    void atualizar(Eleicao eleicao);

    void excluir(long id);
}
