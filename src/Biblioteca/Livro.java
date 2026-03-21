package Biblioteca;

public class Livro {
    private String titulo;
    private String autor;
    private String num_isbn;
    private String data_publicacao;
    private String categoria;
    private String status;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(String data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Livro(String titulo, String autor, String num_isbn, String data_publicacao, String categoria,
            String status) {
        this.titulo = titulo;
        this.autor = autor;
        this.num_isbn = num_isbn;
        this.data_publicacao = data_publicacao;
        this.categoria = categoria;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livro [titulo=" + titulo + ", autor=" + autor + ", num_isbn=" + num_isbn + ", data_publicacao="
                + data_publicacao + ", categoria=" + categoria + ", status=" + status + "]";
    }
}
