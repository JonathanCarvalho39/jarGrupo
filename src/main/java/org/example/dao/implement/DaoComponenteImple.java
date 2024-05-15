package org.example.dao.implement;

import org.example.database.ConexaoMysql;
import org.example.database.ConexaoSQLServer;
import org.example.entidade.Maquina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoComponenteImple implements org.example.dao.DaoComponente {

    public void cadastrarComponenteMysql(Maquina maquina) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConexaoMysql.getConection();

            st = conn.prepareStatement("""
                            INSERT INTO componente (tipo_componente, tamanho_total_gb, tamanho_disponivel_gb, modelo, frequencia, fabricante, fk_maquina) VALUES (?,?,?,?,?,?,?);
                    """, st.RETURN_GENERATED_KEYS);

            for (int i = 0; i < maquina.listarComponentes().size(); i++) {
                st.setString(1, maquina.listarComponentes().get(i).getTipo() == null ? "" : maquina.listarComponentes().get(i).getTipo());
                st.setDouble(2, maquina.listarComponentes().get(i).getTamanho_total_gb() == null ? 0.0 : maquina.listarComponentes().get(i).getTamanho_total_gb());
                st.setDouble(3, maquina.listarComponentes().get(i).getTamanho_livre_gb() == null ? 0.0 : maquina.listarComponentes().get(i).getTamanho_livre_gb());
                st.setString(4, maquina.listarComponentes().get(i).getModelo() == null ? "" : maquina.listarComponentes().get(i).getModelo());
                st.setLong(5, maquina.listarComponentes().get(i).getFrequencia() == null ? 0 : maquina.listarComponentes().get(i).getFrequencia());
                st.setString(6, maquina.listarComponentes().get(i).getFabricante() == null ? "" : maquina.listarComponentes().get(i).getFabricante());
                st.setInt(7, maquina.getId());
                st.executeUpdate();

                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idInserido = generatedKeys.getInt(1);
                        System.out.println("ID inserido: " + idInserido);
                    } else {
                        throw new SQLException("Falha ao obter o ID inserido.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar componente: " + e.getMessage());
        }
    }

    public void cadastrarComponenteSqlServer(Maquina maquina) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConexaoSQLServer.getConection();

            st = conn.prepareStatement("""
                            INSERT INTO componente (tipo_componente, tamanho_total_gb, tamanho_disponivel_gb, modelo, frequencia, fabricante, fk_maquina) VALUES (?,?,?,?,?,?,?);
                    """, st.RETURN_GENERATED_KEYS);

            for (int i = 0; i < maquina.listarComponentes().size(); i++) {
                st.setString(1, maquina.listarComponentes().get(i).getTipo() == null ? "" : maquina.listarComponentes().get(i).getTipo());
                st.setDouble(2, maquina.listarComponentes().get(i).getTamanho_total_gb() == null ? 0.0 : maquina.listarComponentes().get(i).getTamanho_total_gb());
                st.setDouble(3, maquina.listarComponentes().get(i).getTamanho_livre_gb() == null ? 0.0 : maquina.listarComponentes().get(i).getTamanho_livre_gb());
                st.setString(4, maquina.listarComponentes().get(i).getModelo() == null ? "" : maquina.listarComponentes().get(i).getModelo());
                st.setLong(5, maquina.listarComponentes().get(i).getFrequencia() == null ? 0 : maquina.listarComponentes().get(i).getFrequencia());
                st.setString(6, maquina.listarComponentes().get(i).getFabricante() == null ? "" : maquina.listarComponentes().get(i).getFabricante());
                st.setInt(7, maquina.getId());
                st.executeUpdate();

                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idInserido = generatedKeys.getInt(1);
                        System.out.println("ID inserido: " + idInserido);
                    } else {
                        throw new SQLException("Falha ao obter o ID inserido.");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar componente: " + e.getMessage());
        }
    }

}
