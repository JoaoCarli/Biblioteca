package Biblioteca;

import java.util.List;
import java.util.ArrayList;

public class Usuarios {
    private int id;
    private String nome;
    private String email;
    private List<Livro> livros_reservados;
    private List<Emprestimo> emprestimos;

    public int getId() {
        return id;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
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

    public List<Livro> getLivros_reservados() {
        return livros_reservados;
    }

    public void setLivros_reservados(List<Livro> livros_reservados) {
        this.livros_reservados = livros_reservados;
    }

    public Usuarios(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.livros_reservados = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Usuarios [id=" + id + ", nome=" + nome + ", email=" + email + ", livros_reservados=" + livros_reservados
                + ", emprestimos=" + emprestimos + "]";
    }

    public void reservarLivro(Livro livro) {
        if (!livro.getStatus() && !this.livros_reservados.contains(livro)) {
            this.livros_reservados.add(livro);
            livro.adicionarReserva(this);
        } else {
            System.out.println("O livro está disponivel para emprestimo!");
        }
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getNum_isbn().equals(emprestimo.getLivro().getNum_isbn())) {
                System.out.println("O livro ja foi adicionado a lista de emprestados!");
                return;
            }
        }
        emprestimos.add(emprestimo);
    }

    public void enviarNotificacao(Notificacao notificacao) {
        System.out.println("------------------------------------------");
        System.out.println("ENVIANDO E-MAIL PARA: " + this.email);
        System.out.println("ASSUNTO: Notificação de " + notificacao.getSituacao());
        System.out.println("MENSAGEM: Olá " + this.nome + ", " + notificacao.getInfo());

        if (notificacao.getDias_atraso() > 0) {
            System.out.println("ALERTA: Você está com " + notificacao.getDias_atraso() + " dias de atraso.");
        }

        System.out.println("E-mail enviado com sucesso!");
        System.out.println("------------------------------------------");
    }
}
