package org.example.entidade.componente;

public class CaracteristicaComponente {
    private String nome;
    private String valorString;
    private Double valorNumerico;


    public CaracteristicaComponente(String nome, String valorString, Double valorNumerico) {
        this.nome = nome;
        this.valorString = valorString;
        this.valorNumerico = valorNumerico;
    }

    public CaracteristicaComponente() {
    }

    public Double converterGB(Long numero) {
        Double numeroConvertido = Math.round(numero / (1024.0 * 1024.0 * 1024.0) * 100.0) / 100.0;
        return numeroConvertido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public String getValorString() {
        return valorString;
    }

    public Double getValorNumerico(Double aDouble) {
        return valorNumerico;
    }

    public void setValorNumerico(Double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    @Override
    public String toString() {
        return """
                Caracteriscas{
                    nome='%s',
                    valorString='%s',
                    valorNumerico=%.2f
                }
                """.formatted(nome, valorString, valorNumerico);
    }
}
