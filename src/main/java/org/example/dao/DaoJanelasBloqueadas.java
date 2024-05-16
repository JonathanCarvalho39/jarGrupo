package org.example.dao;

import org.example.entidade.JanelasBloqueadas;

import java.util.List;

public interface DaoJanelasBloqueadas {
    List<String> buscarJanelasBloqueadasMysql(Integer setor);
}
