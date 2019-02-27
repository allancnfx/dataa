package br.project.eleicao.service.impl;

import br.project.eleicao.dao.CandidatoDAO;
import br.project.eleicao.domain.Candidato;
import br.project.eleicao.service.CandidatoService;
import br.project.eleicao.service.CargoService;
import br.project.eleicao.service.EleicaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoDAO candidatoDAO;

    @Autowired
    private EleicaoService eleicaoService;

    @Autowired
    private CargoService cargoService;

    @Override
    public void salvar(Candidato candidato, long eleicaoId) {
        candidato.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        candidatoDAO.salvar(candidato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidato> recuperarPorEleicaoId(long eleicaoId) {
        return candidatoDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidato> recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId) {
        return candidatoDAO.recuperarPorEleicaoIdECargoId(eleicaoId, cargoId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Candidato recuperarPorEleicaoIdECandidatoId(long eleicaoId, long candidatoId) {
         return candidatoDAO.recuperarPorEleicaoIdECandidatoId(eleicaoId, candidatoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Candidato recuperarPorEleicaoIdECargoIdECandidatoId(long eleicaoId, long cargoId, long candidatoId) {
        return candidatoDAO.recuperarPorEleicaoIdECargoIdECandidatoId(eleicaoId, cargoId, candidatoId);
    }

    @Override
    public void atualizar(Candidato candidato, long eleicaoId) {
        candidato.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        candidatoDAO.atualizar(candidato);
    }

    @Override
    public void excluir(long eleicaoId, long candidatoId) {
        candidatoDAO.excluir(recuperarPorEleicaoIdECandidatoId(eleicaoId, candidatoId).getId());
    }

    

}
