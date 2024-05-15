package org.example.dao;

import org.example.entidade.Componente;
import org.example.entidade.Maquina;

import java.util.List;

public interface DaoComponente {

    Integer cadastrarComponenteMysql(Componente componente, Integer idMaquina);

    List<Componente> buscarComponenteMysql(Maquina maquina);
}
