
package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Usuario {
    // Atributos
    private int idUsuario;
    private String nome;
    private String login;
    private String senha;
    private LocalDate dataNascimento;
    // Getters & Setters
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    // Contrutores
    public Usuario(int idUsuario, String nome, String login, String senha, LocalDate dataNascimento) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }
    public Usuario() {
        this.idUsuario = 0;
        this.nome = new String();
        this.login = new String();
        this.senha = new String();
        this.dataNascimento = null;
    }

    public void cadastrarUsuario() {
        System.out.println("--- CADASTRANDO NOVO USUÁRIO ---");
        System.out.println("Nome: " + this.nome);
        System.out.println("Login: " + this.login);
        // A senha não deve ser impressa em logs em um ambiente real por segurança
        System.out.println("Senha: [PROTEGIDO]"); 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (this.dataNascimento != null) {
            System.out.println("Data de Nascimento: " + this.dataNascimento.format(formatter));
        }
        
        System.out.println("\nUsuário cadastrado com sucesso! (Simulação)");
        System.out.println("------------------------------------");
    }
}
