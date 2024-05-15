package org.example.dao;

import org.example.entidade.Maquina;

import java.sql.SQLException;

public interface DaoMaquina {

    void cadastrarMaquinaMysql(Integer id_cadastro, Maquina maquina) throws SQLException;

    Integer buscarSetorMaquina(Integer idMaquina);

    Boolean validarMaquinaMysql(String idProcessador);

}