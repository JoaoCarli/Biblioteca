package Biblioteca;

import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
    private List<Livro> acervo;
    private List<Usuarios> usuarios;

    public Biblioteca() {
        acervo = new ArrayList<>();
        usuarios = new ArrayList<>();
   }

   public void adicionarLivro(Livro livro){
        for(Livro l : acervo){
            if(l.getNum_isbn().equals(livro.getNum_isbn())){
                System.out.println("Livro ja existe no acervo!");
            }
            else{
                acervo.add(livro);
                System.out.println("Livro " + livro.getTitulo() + " foi adicionado ao acervo!");
            }
        }
   }

   public void removerLivro(Livro livro){
        for(int i = 0; i < acervo.size(); i++){
            if(acervo.get(i).getNum_isbn().equals(livro.getNum_isbn())){
                System.out.println("O livro " + livro.getTitulo() + " foi removido do acervo!");
                return;
            }
        }
        System.out.println("Livro nao encontrado no acervo!");
   }

   public Livro buscarLivroPorISBN(String num_isbn){
        for(Livro l : acervo){
            if(l.getNum_isbn().equals(num_isbn)){
                return l;
            }
        }
        return null;
   }

   public void realizarEmprestimo(Usuarios usuario, String num_isbn){
        for(Livro l : acervo){
            if(l.getNum_isbn().equals(num_isbn)){
                if(l.getStatus()){
                    Emprestimo emprestimo = new Emprestimo(usuario, l);
                    usuario.adicionarEmprestimo(emprestimo);
                    l.setStatus(false);
                    return;
                }
                else{
                    usuario.reservarLivro(l);
                    System.out.println("Livro nao esta disponivel. A reserva foi realizada!");
                }
            }
        }
        System.out.println("Livro nao encontrado no acervo!");
   }

   public void cadastrarusuario(Usuarios usuario){
        for(Usuarios u : usuarios){
            if(u.getId() == usuario.getId()){
                System.out.println("Usuario ja cadastrado!");
                return;
            }
            else{
                usuarios.add(u);
                System.out.println("Usuario cadastrado na biblioteca!");
            }
        }
   }
}
