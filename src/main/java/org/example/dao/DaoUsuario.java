package org.example.dao;


import org.example.database.DatabaseExeption;
import org.example.entidade.Usuario;
import java.sql.SQLException;

public interface DaoUsuario {

    Usuario validarUsuarioMysql(String login, String senha);

    Usuario validarUsuarioSql(String login, String senha) throws DatabaseExeption, SQLException;
}
