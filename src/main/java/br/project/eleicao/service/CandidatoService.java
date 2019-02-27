package br.project.eleicao.service;

import br.project.eleicao.domain.Candidato;
import java.util.List;

public interface CandidatoService {

    void salvar(Candidato candidato, long eleicaoId);

    List<Candidato> recuperarPorEleicaoId(long eleicaoId);

    Candidato recuperarPorEleicaoIdECandidatoId(long eleicaoId, long candidatoId);

    List<Candidato> recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId);

    
    Candidato recuperarPorEleicaoIdECargoIdECandidatoId(long eleicaoId, long cargoId, long candidatoId);

    void atualizar(Candidato candidato, long eleicaoId);

    void excluir(long eleicaoId, long candidatoId);
}
