package org.example.dao;

import org.example.dao.implement.DaoUsuarioImple;
import org.example.database.ConexaoMysql;
import org.example.database.ConexaoSQLServer;
import org.example.database.DatabaseExeption;
import org.example.entidade.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public interface DaoUsuario {

    Usuario validarUsuarioMysql(String login, String senha);

    Usuario validarUsuarioSql(String login, String senha) throws DatabaseExeption, SQLException;
}
