package Biblioteca;

public class Notificacao {
    private int user_id;
    private String situacao;
    private int dias_atraso;
    private String info;
    private Usuarios usuario;
    private Emprestimo emprestimo;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getDias_atraso() {
        return dias_atraso;
    }

    public void setDias_atraso(int dias_atraso) {
        this.dias_atraso = dias_atraso;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Notificacao(int user_id, String situacao, int dias_atraso, String info, Usuarios usuario,
            Emprestimo emprestimo) {
        this.user_id = user_id;
        this.situacao = situacao;
        this.dias_atraso = dias_atraso;
        this.info = info;
        this.usuario = usuario;
        this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
        return "Notificacao [user_id=" + user_id + ", situacao=" + situacao + ", dias_atraso=" + dias_atraso + ", info="
                + info + ", usuario=" + usuario + ", emprestimo=" + emprestimo + "]";
    }

}
