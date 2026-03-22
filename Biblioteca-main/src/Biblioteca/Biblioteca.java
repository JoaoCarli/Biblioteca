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
        for (Livro l : acervo) {
            if (l.getNum_isbn().equals(num_isbn)) {
                if (l.getStatus()) {
                    Emprestimo emprestimo = new Emprestimo(usuario, l);
                    usuario.adicionarEmprestimo(emprestimo);
                    l.setStatus(false);

                    Notificacao nota = new Notificacao();
                    nota.setSituacao("Empréstimo");
                    nota.setInfo("Você pegou o livro: " + l.getTitulo());
                    usuario.enviarNotificacao(nota);

                    return;
                } else {
                    usuario.reservarLivro(l);
                    System.out.println("Livro nao esta disponivel. A reserva foi realizada!");

                    Notificacao nota = new Notificacao();
                    nota.setSituacao("Reserva");
                    nota.setInfo("O livro " + l.getTitulo() + " foi reservado para você.");
                    usuario.enviarNotificacao(nota);
                    return;
                }
            }
        }
        System.out.println("Livro nao encontrado no acervo!");
    }

    public void devolverLivro(Usuarios usuario, Livro livro) {
        Emprestimo emprestimoEncontrado = null;

        for (Emprestimo e : usuario.getEmprestimos()) {
            if (e.getLivro().getNum_isbn().equals(livro.getNum_isbn)) {
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

                Notificacao notaMulta = new Notificacao();
                notaMulta.setSituacao("Multa por Atraso");
                notaMulta.setDias_atraso((int) diasAtraso);
                notaMulta.setInfo("Livro devolvido com atraso. Multa de R$" + valorMulta);
                usuario.enviarNotificacao(notaMulta);
            }

            usuario.getEmprestimos().remove(emprestimoEncontrado);
            livro.setStatus(true);
            System.out.println("Livro " + livro.getTitulo() + " devolvido com sucesso!");

            if (!livro.getFilaReservas().isEmpty()) {
                Ususarios proximoDaFila = livro.getFilaReservas().get(0);

                Notificacao avisoReserva = new Notificacao();
                avisoReserva.setSituacao("Disponibilidade");
                avisoReserva.setInfo(
                        "O livro " + livro.getTitulo() + " já está disponível! Você tem 3 dias para retirá-lo.");

                proximoDaFila.enviarNotificacao(avisoReserva);
            }
        } else {
            System.out.println("Este usuário não possui o empréstimo deste livro.");
        }
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
