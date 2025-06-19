package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {

    // Atributos
    private int idCliente;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String telefone;
    private String email;
    private LocalDate dataCadastro;

    // Getters & Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Construtores
    public Cliente(int idCliente, String nome, LocalDate dataNascimento, String endereco, String telefone, String email,
            LocalDate dataCadastro) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public Cliente() {
        this.idCliente = 0;
        this.nome = new String();
        this.dataNascimento = null;
        this.endereco = new String();
        this.telefone = new String();
        this.email = new String();
        this.dataCadastro = null;
    }

    public void cadastrarCliente() {
        System.out.println("--- CADASTRANDO NOVO CLIENTE ---");
        System.out.println("ID: " + this.idCliente);
        System.out.println("Nome: " + this.nome);
        System.out.println("Email: " + this.email);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Endereço: " + this.endereco);

        // Formata as datas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (this.dataNascimento != null) {
            System.out.println("Data de Nascimento: " + this.dataNascimento.format(formatter));
        }
        if (this.dataCadastro != null) {
            System.out.println("Data de Cadastro: " + this.dataCadastro.format(formatter));
        }
        
        System.out.println("\nCliente cadastrado com sucesso!!");
        System.out.println("------------------------------------");
    }
}
