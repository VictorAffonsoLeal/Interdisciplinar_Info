package view.panels;

import model.Orcamento;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OrcamentoPanel extends JPanel {

    // Declare os componentes da UI
    private JTextField txtIdCliente;
    private JTextField txtIdUsuario;
    private JTextField txtIdProduto;
    private JTextArea txtDescricao;
    private JTextField txtDataOrcamento;
    private JCheckBox chkStatus;
    private JButton btnSalvar;

    public OrcamentoPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialize os componentes
        txtIdCliente = (JTextField) createStyledTextField(false);
        txtIdUsuario = (JTextField) createStyledTextField(false);
        txtIdProduto = (JTextField) createStyledTextField(false);
        txtDescricao = (JTextArea) ((JScrollPane) createStyledTextField(true)).getViewport().getView();
        txtDataOrcamento = (JTextField) createStyledTextField(false);
        chkStatus = new JCheckBox("Orçamento Aprovado?");
        chkStatus.setOpaque(false); // Para ver o fundo do painel
        btnSalvar = createSaveButton("Lançar Orçamento");

        // --- Montagem do Layout ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("ID Cliente"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.4; add(txtIdCliente, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("ID Usuário"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 0.4; add(txtIdUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("ID Produto"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(txtIdProduto, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTH; add(new JLabel("Descrição"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 3; gbc.ipady = 60; add(new JScrollPane(txtDescricao), gbc); gbc.gridwidth = 1; gbc.ipady = 0;

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Data do Orçamento"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(txtDataOrcamento, gbc);
        gbc.gridx = 2; gbc.gridy = 3; add(chkStatus, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        add(btnSalvar, gbc);
        
        // Adicione o ActionListener
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if (txtIdCliente.getText().trim().isEmpty() || txtIdUsuario.getText().trim().isEmpty() || txtIdProduto.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os campos de ID (Cliente, Usuário, Produto) são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Orcamento novoOrcamento = new Orcamento();
                
                try {
                    novoOrcamento.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
                    novoOrcamento.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
                    novoOrcamento.setIdProduto(Integer.parseInt(txtIdProduto.getText()));
                    
                    novoOrcamento.setDescricao(txtDescricao.getText());
                    novoOrcamento.setStatus(chkStatus.isSelected()); // Pega o valor do JCheckBox

                    String dataOrcamentoTexto = txtDataOrcamento.getText();
                    if(dataOrcamentoTexto != null && !dataOrcamentoTexto.trim().isEmpty()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        novoOrcamento.setDataOrcamento(LocalDate.parse(dataOrcamentoTexto, formatter));
                    } else {
                        novoOrcamento.setDataOrcamento(LocalDate.now());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "IDs inválidos. Por favor, insira apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Chama o método de cadastro
                novoOrcamento.lancarOrcamento();
                
                JOptionPane.showMessageDialog(null, "Orçamento para o cliente ID " + novoOrcamento.getIdCliente() + " lançado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    // --- MÉTODOS DE AJUDA PARA ESTILIZAÇÃO ---
    private JComponent createStyledTextField(boolean isTextArea) {
        final Color borderColor = new Color(244, 114, 182);
        Border line = new LineBorder(borderColor, 2);
        Border padding = new EmptyBorder(10, 10, 10, 10);

        if (isTextArea) {
            JTextArea textArea = new JTextArea();
            textArea.setFont(new Font("Inter", Font.PLAIN, 14));
            textArea.setBorder(new CompoundBorder(line, padding));
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            return new JScrollPane(textArea);
        } else {
            JTextField textField = new JTextField();
            textField.setFont(new Font("Inter", Font.PLAIN, 14));
            textField.setBorder(new CompoundBorder(line, padding));
            return textField;
        }
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
