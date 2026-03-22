package Biblioteca;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Livro {
    private String titulo;
    private String autor;
    private String num_isbn;
    private LocalDate data_publicacao;
    private String categoria;
    private boolean status;
    private List<Usuarios> filaReservas;
    private LocalDate dataLimiteRetirada;

    public LocalDate getDataLimiteRetirada() {
        return dataLimiteRetirada;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Usuarios> getFilaReservas() {
        return filaReservas;
    }

    public void setFilaReservas(List<Usuarios> filaReservas) {
        this.filaReservas = filaReservas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDataLimiteRetirada(LocalDate dataLimiteRetirada) {
        this.dataLimiteRetirada = dataLimiteRetirada;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNum_isbn() {
        return num_isbn;
    }

    public void setNum_isbn(String num_isbn) {
        this.num_isbn = num_isbn;
    }

    public LocalDate getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(LocalDate data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Livro(String titulo, String autor, String num_isbn, LocalDate data_publicacao, String categoria,
            boolean status, List<Usuarios> filaReservas) {
        this.titulo = titulo;
        this.autor = autor;
        this.num_isbn = num_isbn;
        this.data_publicacao = data_publicacao;
        this.categoria = categoria;
        this.status = status;
        this.filaReservas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Livro [titulo=" + titulo + ", autor=" + autor + ", num_isbn=" + num_isbn + ", data_publicacao="
                + data_publicacao + ", categoria=" + categoria + ", status=" + status + "]";
    }

    public void adicionarReserva(Usuarios usuario) {
        if (!filaReservas.contains(usuario)) {
            filaReservas.add(usuario);
        }
    }
}
