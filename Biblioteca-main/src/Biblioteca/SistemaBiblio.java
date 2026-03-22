package Biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaBiblio {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro(
                "Dom Casmurro",
                "Machado de Assis",
                "978-8572322645",
                LocalDate.of(1899, 12, 15),
                "Romance",
                true,
                new ArrayList<>()
        );

        Livro livro2 = new Livro(
                "A Divina Comédia",
                "Dante Alighieri",
                "790-8312104290",
                LocalDate.of(1304, 6, 24),
                "Epopeia",
                false,
                new ArrayList<>()
        );

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        Usuarios user1 = new Usuarios(
                0,
                "Carlos",
                "carlos@gmail.com"
        );

        biblioteca.cadastrarusuario(user1);

        biblioteca.realizarEmprestimo(user1, "978-8572322645");
        biblioteca.realizarEmprestimo(user1, "790-8312104290");

        Livro buscado = biblioteca.buscarLivroPorISBN("978-8572322645");
        if (buscado != null) {
            System.out.println("Livro encontrado: " + buscado.getTitulo());
        } else {
            System.out.println("Livro não encontrado!");
        }
    }
}