package Biblioteca;

public class SistemaBiblio {
        private Livro livro1, livro2;
        private Usuarios user1;
        private Emprestimo emp1;
        private Notificacao noti1, noti2;

        public SistemaBiblio() {
                this.livro1 = new Livro(
                                "Dom Casmurro",
                                "Machado de Assis",
                                "978-8572322645",
                                "15/12/1899",
                                "Romance",
                                "Disponível");
                this.livro2 = new Livro(
                                "A Divina Comédia",
                                "Dante Alighieri",
                                "790-8312104290",
                                "24/06/1304",
                                "Epopeia",
                                "Emprestado");

                this.user1 = new Usuarios(
                                0,
                                "Carlos",
                                "carlos@gmail.com",
                                emp1);

                this.emp1 = new Emprestimo(
                                "20/03/2026",
                                "27/03/2026",
                                0,
                                0,
                                user1);

                this.noti1 = new Notificacao(
                                0,
                                "disponibilidade de livros",
                                0,
                                "Um livro que voce tem interesse está disponível",
                                user1,
                                null);
        }
}
