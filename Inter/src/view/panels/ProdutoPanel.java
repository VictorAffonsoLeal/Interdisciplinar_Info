package view.panels;

import model.Produto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdutoPanel extends JPanel {

    // Declare os componentes
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JTextField txtQtd;
    private JButton btnSalvar;

    public ProdutoPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialize os componentes
        txtDescricao = (JTextField) createStyledTextField();
        txtValor = (JTextField) createStyledTextField();
        txtQtd = (JTextField) createStyledTextField();
        btnSalvar = createSaveButton("Salvar Produto");

        // --- Montagem do Layout ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2; add(new JLabel("Descrição do Produto/Serviço"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.8; gbc.gridwidth = 3; add(txtDescricao, gbc); gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.2; add(new JLabel("Valor Unitário (R$)"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.3; add(txtValor, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0.2; add(new JLabel("Quantidade em Estoque"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 0.3; add(txtQtd, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        add(btnSalvar, gbc);

        // Adicione o ActionListener ao botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação de campos
                if (txtDescricao.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Cria um novo objeto Produto
                Produto novoProduto = new Produto();
                novoProduto.setDescricao(txtDescricao.getText());
                
                // Trata os campos numéricos, que podem gerar erros de formato
                try {
                    // Substitui vírgula por ponto para aceitar ambos formatos
                    String valorTexto = txtValor.getText().replace(",", ".");
                    novoProduto.setValor(Float.parseFloat(valorTexto));
                    
                    novoProduto.setQtd(Integer.parseInt(txtQtd.getText()));
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor ou Quantidade inválidos. Por favor, insira apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Chama o método de cadastro
                novoProduto.cadastrarProduto();

                // Mostra mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Produto '" + novoProduto.getDescricao() + "' cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // --- MÉTODOS DE AJUDA PARA ESTILIZAÇÃO ---
    private JComponent createStyledTextField() {
        final Color borderColor = new Color(244, 114, 182);
        Border line = new LineBorder(borderColor, 2);
        Border padding = new EmptyBorder(10, 10, 10, 10);
        
        JTextField textField = new JTextField();
        textField.setFont(new Font("Inter", Font.PLAIN, 14));
        textField.setBorder(new CompoundBorder(line, padding));
        return textField;
    }

    private JButton createSaveButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(250, 204, 21));
        button.setForeground(new Color(30, 41, 59));
        button.setFont(new Font("Inter", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 24, 12, 24));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
