package br.project.eleicao.service;
import br.project.eleicao.domain.Cargo;
import java.util.List;

public interface CargoService {
    void salvar(Cargo cargo, long eleicaoId);
    List<Cargo> recuperarPorEleicaoId(long eleicaoId);
    Cargo recuperarPorId(long id);
    Cargo recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId);
    void atualizar(Cargo cargo, long eleicaoId);
    void excluir(long eleicaoId, long cargoId);
}
