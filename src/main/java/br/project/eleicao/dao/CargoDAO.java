package br.project.eleicao.dao;

import br.project.eleicao.domain.Cargo;

import java.util.List;

public interface CargoDAO {

    void salvar(Cargo cargo);
    List<Cargo> recuperarPorEleicaoId(long eleicaoId);
    Cargo recuperarPorID(long id);
    Cargo recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId);
    void atualizar(Cargo cargo);
    void excluir(long cargoId);

}
