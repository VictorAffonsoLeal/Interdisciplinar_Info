package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

    // Atributos
    private int idPedido;
    private String descricao;
    private int idCliente; // referência ao Cliente
    private int idUsuario; // referência ao Usuario
    private int idProduto; // referência ao Produto
    private LocalDate dataCancel;
    private LocalDate dataPedido;

    // Getters & Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    // Construtores
    public Pedido(int idPedido, String descricao, int idCliente, int idUsuario, int idProduto,
            LocalDate dataCancel, LocalDate dataPedido) {
        this.idPedido = idPedido;
        this.descricao = descricao;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.dataCancel = dataCancel;
        this.dataPedido = dataPedido;
    }

    public Pedido() {
        this.idPedido = 0;
        this.descricao = new String();
        this.idCliente = 0;
        this.idUsuario = 0;
        this.idProduto = 0;
        this.dataCancel = null;
        this.dataPedido = null;
    }

    public void lancarPedido() {
        System.out.println("--- LANÇANDO NOVO PEDIDO ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("ID Cliente: " + this.idCliente);
        System.out.println("ID Usuário: " + this.idUsuario);
        System.out.println("ID Produto: " + this.idProduto);
        System.out.println("Descrição: " + this.descricao);

        if (this.dataPedido != null) {
            System.out.println("Data do Pedido: " + this.dataPedido.format(formatter));
        }
        if (this.dataCancel != null) {
            System.out.println("Data de Cancelamento: " + this.dataCancel.format(formatter));
        } else {
            System.out.println("Data de Cancelamento: (Não cancelado)");
        }

        System.out.println("\nPedido lançado com sucesso! (Simulação)");
        System.out.println("------------------------------------");
    }
}
