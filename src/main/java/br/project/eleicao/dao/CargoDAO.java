package br.project.eleicao.dao;

import br.project.eleicao.domain.Cargo;

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
public interface CargoDAO {

    void salvar(Cargo cargo);

    List<Cargo> recuperarPorEleicaoId(long eleicaoId);

    Cargo recuperarPorID(long id);

    Cargo recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId);

    void atualizar(Cargo cargo);

    void excluir(long cargoId);

}
