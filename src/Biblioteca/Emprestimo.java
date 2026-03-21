package Biblioteca;

import java.util.List;

public class Emprestimo {
    private String data_inicio;
    private String data_dev;
    private float multa;
    private int dias_multa;
    private List<Livro> livros;

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_dev() {
        return data_dev;
    }

    public void setData_dev(String data_dev) {
        this.data_dev = data_dev;
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        this.multa = multa;
    }

    public int getDias_multa() {
        return dias_multa;
    }

    public void setDias_multa(int dias_multa) {
        this.dias_multa = dias_multa;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Emprestimo(String data_inicio, String data_dev, float multa, int dias_multa, List<Livro> livros) {
        this.data_inicio = data_inicio;
        this.data_dev = data_dev;
        this.multa = multa;
        this.dias_multa = dias_multa;
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Emprestimo [data_inicio=" + data_inicio + ", data_dev=" + data_dev + ", multa=" + multa
                + ", dias_multa=" + dias_multa + ", livros=" + livros + "]";
    }

}
