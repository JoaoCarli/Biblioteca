package Biblioteca;

import java.util.List;

public class Usuarios {
    private int id;
    private String nome;
    private String email;
    private List<String> livros_reservados;
    private List<Livro> livro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getLivros_reservados() {
        return livros_reservados;
    }

    public void setLivros_reservados(List<String> livros_reservados) {
        this.livros_reservados = livros_reservados;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    public Usuarios(int id, String nome, String email, List<String> livros_reservados, List<Livro> livro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.livros_reservados = livros_reservados;
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Usuarios [id=" + id + ", nome=" + nome + ", email=" + email + ", livros_reservados=" + livros_reservados
                + ", livro=" + livro + "]";
    }

}
