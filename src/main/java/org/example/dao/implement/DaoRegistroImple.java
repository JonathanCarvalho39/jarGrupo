package org.example.dao.implement;

import com.github.britooo.looca.api.core.Looca;
import org.example.dao.DaoRegistro;
import org.example.database.ConexaoMysql;
import org.example.database.ConexaoSQLServer;
import org.example.entidade.Componente;
import org.example.entidade.Maquina;
import org.example.entidade.componente.Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoRegistroImple implements DaoRegistro {

    private Connection connMysl = null;
    private Connection connSql = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;


    Looca looca = new Looca();
    Registro registro = new Registro();

    public void inserirRegistroTempoReal(Componente componente) {

        Double usoComponente = 0.0;

        if (componente.getTipo().equals("Processador")) {
            usoComponente = looca.getProcessador().getUso();
        } else if (componente.getTipo().equals("Memoria")) {
            usoComponente = registro.converterGB(looca.getMemoria().getEmUso());
        }

        try {
            connMysl = ConexaoMysql.getConection();
            connSql = ConexaoSQLServer.getConection();

            st = connMysl.prepareStatement("""
                            INSERT INTO registro (uso_componente, data_hora, fk_componente) VALUES (?,?,?);
                    """);

            st.setDouble(1, usoComponente);
            st.setString(2, "now()");
            st.setInt(3, 1);

            st.executeUpdate();

            st = connSql.prepareStatement("""
                            INSERT INTO registro (uso_componente, data_hora, fk_componente) VALUES (?,?,?);
                    """);

            st.setDouble(1, usoComponente);
            st.setString(2, "now()");
            st.setInt(3, 1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
