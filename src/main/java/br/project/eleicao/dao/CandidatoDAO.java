package br.project.eleicao.dao;

import br.project.eleicao.domain.Candidato;
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
public interface CandidatoDAO {

    void salvar(Candidato candidato);

    List<Candidato> recuperarPorEleicaoId(long eleicaoId);

    List<Candidato> recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId);

    Candidato recuperarPorId(long id);

    Candidato recuperarPorEleicaoIdECandidatoId(long eleicaoId, long candidatoId);

    Candidato recuperarPorEleicaoIdECargoIdECandidatoId(long eleicaoId, long cargoId, long candidatoId);

    void atualizar(Candidato candidato);

    void excluir(long candidatoId);
}
