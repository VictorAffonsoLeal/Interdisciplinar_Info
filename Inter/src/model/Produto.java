package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Produto {

    // Atributos
    private int idItem;
    private String descricao;
    private float valor;
    private int qtd;

    // Getters & Setters
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    // Construtores
    public Produto(int idItem, String descricao, float valor, int qtd) {
        this.idItem = idItem;
        this.descricao = descricao;
        this.valor = valor;
        this.qtd = qtd;
    }

    public Produto() {
        this.idItem = 0;
        this.descricao = new String();
        this.valor = 0;
        this.qtd = 0;
    }

    public void cadastrarProduto() {
        System.out.println("--- CADASTRANDO NOVO PRODUTO ---");
        System.out.println("Descrição: " + this.descricao);

        // Formata o valor como moeda local (R$)
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("Valor Unitário: " + formatter.format(this.valor));

        System.out.println("Quantidade: " + this.qtd);

        System.out.println("\nProduto cadastrado com sucesso! (Simulação)");
        System.out.println("------------------------------------");
    }
}
