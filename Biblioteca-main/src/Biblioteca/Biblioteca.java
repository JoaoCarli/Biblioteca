package Biblioteca;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Biblioteca {
    private List<Livro> acervo;
    private List<Usuarios> usuarios;

    public Biblioteca() {
        acervo = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        for (Livro l : acervo) {
            if (l.getNum_isbn().equals(livro.getNum_isbn())) {
                System.out.println("Livro ja existe no acervo!");
            }
        }
        acervo.add(livro);
        System.out.println("Livro " + livro.getTitulo() + " foi adicionado ao acervo!");
    }

    public void removerLivro(Livro livro) {
        for (int i = 0; i < acervo.size(); i++) {
            if (acervo.get(i).getNum_isbn().equals(livro.getNum_isbn())) {
                System.out.println("O livro " + livro.getTitulo() + " foi removido do acervo!");
                return;
            }
        }
        System.out.println("Livro nao encontrado no acervo!");
    }

    public Livro buscarLivroPorISBN(String num_isbn) {
        for (Livro l : acervo) {
            if (l.getNum_isbn().equals(num_isbn)) {
                return l;
            }
        }
        return null;
    }

    public void realizarEmprestimo(Usuarios usuario, String num_isbn) {
        Livro livroEncontrado = buscarLivroPorISBN(num_isbn);
        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        for (Emprestimo e : usuario.getEmprestimos()) {
            if (LocalDate.now().isAfter(e.getData_dev())) {
                System.out.println("Empréstimo NEGADO: Você tem livros atrasados.");
                return;
            }
        }

        if (livroEncontrado.getStatus) {
            if (!livroEncontrado.getFilaReservas().isEmpty()) {
                Usuarios primeiroDaFila = livroEncontrado.getFilaReservas.get(0);

                if (!primeiroDaFila.equals(usuario)) {
                    System.out.println("Empréstimo NEGADO: Este livro está reservado para outra pessoa.");
                    return;
                } else {
                    livroEncontrado.getFilaReservas().remove(0);
                    usuario.getLivros_reservados().remove(livroEncontrado);
                }
            }

            Emprestimo emprestimo = new Emprestimo(usuario, livroEncontrado);
            usuario.adicionarEmprestimo(emprestimo);
            livroEncontrado.setStatus(false);
            livroEncontrado.setDataLimiteRetirada(null);

            Notificacao nota = new Notificacao(usuario.getId(), "Empréstimo", 0,
                    "Você pegou o livro: " + livroEncontrado.getTitulo(), usuario, emprestimo);
            usuario.enviarNotificacao(nota);

            System.out.println("Empréstimo de '" + livroEncontrado.getTitulo() + "' realizado!");
        } else {
            usuario.reservarLivro(livroEncontrado);
            System.out.println("Livro não disponível. Reserva realizada para " + usuario.getNome());

            Notificacao nota = new Notificacao(usuario.getId(), "Reserva", 0,
                    "O livro " + livroEncontrado.getTitulo() + " foi reservado para você.", usuario, null);
            usuario.enviarNotificacao(nota);
        }
    }

    public void devolverLivro(Usuarios usuario, Livro livro) {
        Emprestimo emprestimoEncontrado = null;

        for (Emprestimo e : usuario.getEmprestimos()) {
            if (e.getLivro().getNum_isbn().equals(livro.getNum_isbn())) {
                emprestimoEncontrado = e;
                break;
            }
        }
        if (emprestimoEncontrado != null) {
            LocalDate hoje = LocalDate.now();
            LocalDate prevista = emprestimoEncontrado.getData_dev();

            if (hoje.isAfter(prevista)) {
                long diasAtraso = ChronoUnit.DAYS.between(prevista, hoje);
                double valorMulta = diasAtraso * 2.5;

                Notificacao notaMulta = new Notificacao(
                        usuario.getId(),
                        "Multa por Atraso",
                        (int) diasAtraso,
                        "Livro devolvido com atraso. Multa de R$" + valorMulta, usuario, emprestimoEncontrado);
                usuario.enviarNotificacao(notaMulta);
            }

            usuario.getEmprestimos().remove(emprestimoEncontrado);

            if (!livro.getFilaReservas().isEmpty()) {
                livro.setStatus(false);
                livro.setDataLimiteRetirada(LocalDate.now().plusDays(3));
                Usuarios proximoDaFila = livro.getFilaReservas().get(0);

                Notificacao avisoReserva = new Notificacao(
                        proximoDaFila.getId(),
                        "Disponibilidade",
                        0,
                        "O livro " + livro.getTitulo() + " já está disponível! Você tem 3 dias para retirá-lo.",
                        proximoDaFila,
                        null);
                proximoDaFila.enviarNotificacao(avisoReserva);
                System.out.println("Livro devolvido e reservado para " + proximoDaFila.getNome());
            } else {
                livro.setStatus(true);
            }

            System.out.println("Livro " + livro.getTitulo() + " devolvido com sucesso!");

        } else {
            System.out.println("Este usuário não possui o empréstimo deste livro.");
        }
    }

    public void verificaReservaExistente() {
        LocalDate hoje = LocalDate.now();

        for (Livro livro : acervo) {
            if (!livro.getStatus() && !livro.getFilaReservas().isEmpty() && livro.getDataLimiteRetirada != null) {
                if (hoje.isAfter(livro.getDataLimiteRetirada)) {
                    Usuarios perdeuPrazo = livro.getFilaReservas().remove(0);
                    perdeuPrazo.getLivros_reservados.remove(livro);

                    System.out.println("O usuário " + perdeuPrazo.getNome() + " perdeu o prazo de retirada do livro "
                            + livro.getTitulo());

                    if (!livro.getFilaReservas().isEmpty()) {
                        livro.setDataLimiteRetirada(hoje.plusDays(3));
                    } else {
                        livro.setStatus(true);
                        livro.setDataLimiteRetirada(null);
                    }
                }
            }
        }
    }

    public void verificaAtrasosGerais() {
        LocalDate hoje = LocalDate.now();
        int totalAvisos = 0;

        System.out.println("=== INICIANDO VARREDURA DE ATRASOS ===");

        for (Usuario u : usuarios) {
            for (Emprestimo e : u.getEmprestimos()) {
                if (hoje.isAfter(e.getData_dev())) {
                    long dias = ChronoUnit.DAYS.between(e.getData_dev(), hoje);

                    Notificacao lembrete = new Notificacao(
                            u.getId(),
                            "Lembrete de Atraso",
                            (int) dias,
                            "O livro '" + e.getLivro().getTitulo() + "' está atrasado. Por favor, devolva-o.",
                            u,
                            e);

                    u.enviarNotificacao(lembrete);
                    totalAvisos++;
                }
            }
        }

        if (totalAvisos == 0) {
            System.out.println("Nenhum atraso encontrado hoje.");
        } else {
            System.out.println("Varredura finalizada. " + totalAvisos + " utilizadores notificados.");
        }
        System.out.println("=======================================");

    }

    public void cadastrarusuario(Usuarios usuario) {
        for (Usuarios u : usuarios) {
            if (u.getId() == usuario.getId()) {
                System.out.println("Usuario ja cadastrado!");
                return;
            }
        }
        usuarios.add(u);
        System.out.println("Usuario cadastrado na biblioteca!");
    }
}
