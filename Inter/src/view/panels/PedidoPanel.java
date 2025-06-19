package view.panels;

import model.Pedido;

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

public class PedidoPanel extends JPanel {

    // Declare os componentes da UI
    private JTextField txtIdCliente;
    private JTextField txtIdUsuario;
    private JTextField txtIdProduto;
    private JTextArea txtDescricao;
    private JTextField txtDataPedido;
    private JTextField txtDataCancel;
    private JButton btnSalvar;

    public PedidoPanel() {
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
        txtDataPedido = (JTextField) createStyledTextField(false);
        txtDataCancel = (JTextField) createStyledTextField(false);
        btnSalvar = createSaveButton("Lançar Pedido");
        
        // --- Montagem do Layout ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("ID Cliente"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.4; add(txtIdCliente, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("ID Usuário"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 0.4; add(txtIdUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("ID Produto"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(txtIdProduto, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTH; add(new JLabel("Descrição"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 3; gbc.ipady = 60; add(new JScrollPane(txtDescricao), gbc); gbc.gridwidth = 1; gbc.ipady = 0;

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Data do Pedido"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(txtDataPedido, gbc);
        gbc.gridx = 2; gbc.gridy = 3; add(new JLabel("Data de Cancelamento"), gbc);
        gbc.gridx = 3; gbc.gridy = 3; add(txtDataCancel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        add(btnSalvar, gbc);

        //Adicione o ActionListener
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação de campos
                if (txtIdCliente.getText().trim().isEmpty() || txtIdUsuario.getText().trim().isEmpty() || txtIdProduto.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os campos de ID (Cliente, Usuário, Produto) são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Pedido novoPedido = new Pedido();

                try {
                    // Preenche os IDs
                    novoPedido.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
                    novoPedido.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
                    novoPedido.setIdProduto(Integer.parseInt(txtIdProduto.getText()));

                    // Preenche a descrição
                    novoPedido.setDescricao(txtDescricao.getText());

                    // Trata as datas
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dataPedidoTexto = txtDataPedido.getText();
                    if(dataPedidoTexto != null && !dataPedidoTexto.trim().isEmpty()) {
                        novoPedido.setDataPedido(LocalDate.parse(dataPedidoTexto, formatter));
                    } else {
                        // Poderia definir a data atual como padrão se o campo estiver vazio
                        novoPedido.setDataPedido(LocalDate.now());
                    }

                    String dataCancelTexto = txtDataCancel.getText();
                    if(dataCancelTexto != null && !dataCancelTexto.trim().isEmpty()) {
                        novoPedido.setDataCancel(LocalDate.parse(dataCancelTexto, formatter));
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "IDs inválidos. Por favor, insira apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Chama o método de cadastro
                novoPedido.lancarPedido();

                // Mostra mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Pedido para o cliente ID " + novoPedido.getIdCliente() + " lançado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
