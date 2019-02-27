package br.project.eleicao.service.impl;

import br.project.eleicao.dao.CargoDAO;
import br.project.eleicao.domain.Cargo;
import br.project.eleicao.service.CargoService;
import br.project.eleicao.service.EleicaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDAO cargoDAO;

    @Autowired
    private EleicaoService eleicaoService;

    @Override
    public void salvar(Cargo cargo, long eleicaoId) {
        cargo.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        cargoDAO.salvar(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo recuperarPorId(long id) {
        return cargoDAO.recuperarPorID(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> recuperarPorEleicaoId(long eleicaoId) {
        return cargoDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo recuperarPorEleicaoIdECargoId(long eleicaoId, long cargoId) {
        return cargoDAO.recuperarPorEleicaoIdECargoId(eleicaoId, cargoId);
    }

    @Override
    public void atualizar(Cargo cargo, long eleicaoId) {
        cargo.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        cargoDAO.atualizar(cargo);
    }

    @Override
    public void excluir(long eleicaoId, long cargoId) {
        cargoDAO.excluir(recuperarPorEleicaoIdECargoId(eleicaoId, cargoId).getId());
    }

}
