/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.dao;

import br.project.eleicao.domain.Candidato;
import java.util.List;

/**
 *
 * @author Allan
 */
public interface CandidatoDAO {

    void salvar(Candidato candidato);

    List<Candidato> recuperarPorEleicaoId(long eleicaoId);

    List<Candidato> recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId);

    Candidato recuperarPorEleicaoIdECandidatoId(long eleicaoId, long candidatoId);

    Candidato recuperarPorEleicaoIdECargoIdECandidatoId(long eleicaoId, long cargoId, long candidatoId);

    void atualizar(Candidato candidato);

    void excluir(long candidatoId);
}
