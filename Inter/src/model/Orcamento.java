package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Orcamento {

    // Atributos
    private int idOrcamento;
    private String descricao;
    private int idCliente; // referência ao Cliente
    private int idUsuario; // referência ao Usuario
    private int idProduto; // referência ao Produto
    private LocalDate dataCancel;
    private LocalDate dataOrcamento;
    private boolean status;

    // Getters & Setters
    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public LocalDate getDataCancel() {
        return dataCancel;
    }

    public void setDataCancel(LocalDate dataCancel) {
        this.dataCancel = dataCancel;
    }

    public LocalDate getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(LocalDate dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Contrutores
    public Orcamento(int idOrcamento, String descricao, int idCliente, int idUsuario, int idProduto,
            LocalDate dataCancel, LocalDate dataOrcamento, boolean status) {
        this.idOrcamento = idOrcamento;
        this.descricao = descricao;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.dataCancel = dataCancel;
        this.dataOrcamento = dataOrcamento;
        this.status = status;
    }

    public Orcamento() {
        this.idOrcamento = 0;
        this.descricao = new String();
        this.idCliente = 0;
        this.idUsuario = 0;
        this.idProduto = 0;
        this.dataCancel = null;
        this.dataOrcamento = null;
        this.status = false;
    }

    public void lancarOrcamento() {
        System.out.println("--- LANÇANDO NOVO ORÇAMENTO ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("ID Cliente: " + this.idCliente);
        System.out.println("ID Usuário: " + this.idUsuario);
        System.out.println("ID Produto: " + this.idProduto);
        System.out.println("Descrição: " + this.descricao);
        System.out.println("Status: " + (this.status ? "Aprovado" : "Pendente"));

        if(this.dataOrcamento != null) {
            System.out.println("Data do Orçamento: " + this.dataOrcamento.format(formatter));
        }
        
        System.out.println("\nOrçamento lançado com sucesso! (Simulação)");
        System.out.println("------------------------------------");
    }

}
