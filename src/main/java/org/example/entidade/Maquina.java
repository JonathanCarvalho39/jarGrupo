package org.example.entidade;

import com.github.britooo.looca.api.core.Looca;
import org.example.dao.DaoComponente;
import org.example.dao.DaoMaquina;
import org.example.dao.implement.DaoComponenteImple;
import org.example.dao.implement.DaoMaquinaImple;
import org.example.entidade.componente.Registro;
import org.example.utils.Utilitarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maquina {
    private Integer id;
    private String idPorcessador;
    private String nome;
    private String modelo;
    private Double memorialTotal;
    private String sistemaOperacional;
    private Integer arquitetura;
    private List<Componente> componentes;

    final Looca locca = new Looca();
    final Utilitarios utilitarios = new Utilitarios();
    final Scanner sc = new Scanner(System.in);
    Registro registro = new Registro();
    Looca looca = new Looca();

    public Double getMemorialTotal() {
        return memorialTotal;
    }

    public void setMemorialTotal(Double memorialTotal) {
        this.memorialTotal = memorialTotal;
    }

    public Maquina(Integer id, String idPorcessador, String nome, String modelo, Double memorialTotal, String sistemaOperacional, Integer arquitetura) {
        this.id = id;
        this.idPorcessador = idPorcessador;
        this.nome = nome;
        this.modelo = modelo;
        this.memorialTotal = memorialTotal;
        this.sistemaOperacional = sistemaOperacional;
        this.arquitetura = arquitetura;
        componentes = new ArrayList<>();
    }

    public Maquina() {
        componentes = new ArrayList<>();
    }

    public void monitoramento(Maquina maquina) throws SQLException {
        DaoMaquina daoMaquina = new DaoMaquinaImple();
        DaoComponente daoComponente = new DaoComponenteImple();

        if (daoMaquina.validarMaquinaMysql(locca.getProcessador().getId())) {
            System.out.println("Monitorando componente");
        } else {
            utilitarios.centralizaTelaHorizontal(8);
            utilitarios.mensagemCadastroMaquina();
            utilitarios.centralizaTelaHorizontal(8);
            System.out.print("Insira o código aqui: ");
            Integer idCadastro = sc.nextInt();
            if (daoMaquina.buscarSetorMaquina(idCadastro) == null) {
                utilitarios.codigoIncorreto();
            } else {
                maquina.setId(idCadastro);
                Componente componenteRam = new Componente(
                        "Memória Ram",
                        registro.converterGB(looca.getMemoria().getTotal()),
                        null,
                        null,
                        null,
                        null);
                maquina.addComponente(componenteRam);

                Componente componenteCpu = new Componente(
                        "CPU",
                        null,
                        null,
                        looca.getProcessador().getFabricante(),
                        null,
                        looca.getProcessador().getFrequencia());

                maquina.addComponente(componenteCpu);

                for (int i = 0; i < looca.getGrupoDeDiscos().getDiscos().size(); i++) {
                    Componente componenteDisco = new Componente(
                            "Disco " + (i + 1),
                            registro.converterGB(looca.getGrupoDeDiscos().getVolumes().get(i).getTotal()),
                            registro.converterGB(looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel()),
                            null,
                            looca.getGrupoDeDiscos().getDiscos().get(i).getModelo(),
                            null
                    );
                    maquina.addComponente(componenteDisco);
                }
                daoComponente.cadastrarComponenteMysql(maquina);
                daoMaquina.cadastrarMaquinaMysql(idCadastro, maquina);

            }
        }
    }

    public List<Componente> listarComponentes() {
        return componentes;
    }

    public void addComponente(Componente componente) {
        componentes.add(componente);
    }

    public void removeComponente(Componente componente) {
        componentes.remove(componente);
    }

    public void listarCaracteristicas() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdPorcessador() {
        return idPorcessador;
    }

    public void setIdPorcessador(String idPorcessador) {
        this.idPorcessador = idPorcessador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    @Override
    public String toString() {
        return
                """
                        Maquina : {
                        id : %d
                        idPorcessador : %s
                        nome : %s
                        modelo : %s
                        sistemaOperacional : %s
                        memoryTotal : %.2f
                        arquitetura : %d
                        componentes : %s
                        }
                                                """.formatted(id, idPorcessador, nome, modelo, sistemaOperacional, memorialTotal, arquitetura, componentes);
    }
}
// Path: src/main/java/org/example/entidade/Componente.java