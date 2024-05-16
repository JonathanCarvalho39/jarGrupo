package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.example.entidade.Maquina;
import org.example.entidade.Usuario;
import org.example.entidade.componente.Registro;
import org.example.utils.Utilitarios;
import org.example.utils.console.FucionalidadeConsole;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Looca looca = new Looca();

        Registro registro = new Registro();
        Utilitarios utilitarios = new Utilitarios();
        FucionalidadeConsole fucionalidadeConsole = new FucionalidadeConsole();
        Usuario usuario = new Usuario();

        Maquina maquina = new Maquina(
                null,
                looca.getProcessador().getId(),
                null,
                null,
                registro.converterGB(looca.getGrupoDeDiscos().getTamanhoTotal()),
                looca.getSistema().getSistemaOperacional(),
                looca.getSistema().getArquitetura()
        );

        do {
            fucionalidadeConsole.limparConsole();
            utilitarios.exibirLogo();
            if (!usuario.validarUsuario()) {
                utilitarios.senhaIncorreta();
                Thread.sleep(2000);
            } else {
                fucionalidadeConsole.limparConsole();
                utilitarios.exibirBemVindo();
                Thread.sleep(2000);
                break;
            }
        } while (true);
        maquina.monitoramento(maquina);
    }
}
