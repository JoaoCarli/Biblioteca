package Biblioteca;

import java.time.LocalDate;

public class Emprestimo {
    private Usuarios usuario;
    private Livro livro;
    private LocalDate data_inicio;
    private LocalDate data_dev;
    private float multa;
    private int dias_multa;

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_dev() {
        return data_dev;
    }

    public void setData_dev(LocalDate data_dev) {
        this.data_dev = data_dev;
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        this.multa = multa;
    }

    public Emprestimo(Usuarios usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.data_inicio = LocalDate.now();
        this.data_dev = data_inicio.plusDays(7); // defini 7 dias após emprestimo, para devolução
        this.multa = 0;
    }

    @Override
    public String toString() {
        return "Emprestimo [usuario=" + usuario + ", livro=" + livro + ", data_inicio=" + data_inicio + ", data_dev="
                + data_dev + ", multa=" + multa + ", dias_multa=" + dias_multa + "]";
    }

}
