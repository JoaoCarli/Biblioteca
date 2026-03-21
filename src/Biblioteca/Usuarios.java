package Biblioteca;

import java.util.List;

public class Usuarios {
    private int id;
    private String nome;
    private String email;
    private Emprestimo emprestimo;

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

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Usuarios(int id, String nome, String email, Emprestimo emprestimo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
        return "Usuarios [id=" + id + ", nome=" + nome + ", email=" + email + ", emprestimo=" + emprestimo + "]";
    }

}
