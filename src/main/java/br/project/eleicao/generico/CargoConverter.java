/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.generico;

import br.project.eleicao.dao.CargoDAO;
import br.project.eleicao.domain.Cargo;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Allan
 */
public class CargoConverter extends PropertyEditorSupport {
    private final CargoDAO cargoDAO;

    public CargoConverter(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        Long cargoId = new Long(id);
        Cargo cargo = this.cargoDAO.recuperarPorID(cargoId);
        this.setValue(cargo);
    }
}
