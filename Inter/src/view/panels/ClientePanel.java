package view.panels;

import model.Cliente;

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

public class ClientePanel extends JPanel {

    // Declare os componentes
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtDataNascimento;
    private JTextArea txtEndereco;
    private JButton btnSalvar;

    public ClientePanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialize os componentes
        txtNome = (JTextField) createStyledTextField(false);
        txtEmail = (JTextField) createStyledTextField(false);
        txtTelefone = (JTextField) createStyledTextField(false);
        txtDataNascimento = (JTextField) createStyledTextField(false);
        txtEndereco = (JTextArea) ((JScrollPane) createStyledTextField(true)).getViewport().getView(); // Pega o JTextArea de dentro do JScrollPane
        btnSalvar = createSaveButton("Salvar Cliente");
        

        // Linha 0
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("ID Cliente"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.4; JTextField idCliente = new JTextField("1"); idCliente.setEnabled(false); add(idCliente, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("Data de Cadastro"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 0.4; JTextField dataCadastro = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); dataCadastro.setEnabled(false); add(dataCadastro, gbc);
        
        // Linha 1
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Nome Completo"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 3; add(txtNome, gbc); gbc.gridwidth = 1;

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Email"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(txtEmail, gbc);
        gbc.gridx = 2; gbc.gridy = 2; add(new JLabel("Telefone"), gbc);
        gbc.gridx = 3; gbc.gridy = 2; add(txtTelefone, gbc);
        
        // Linha 3
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Data de Nascimento"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(txtDataNascimento, gbc);

        // Linha 4
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.NORTH; add(new JLabel("Endereço"), gbc); gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 3; gbc.ipady = 40; add(new JScrollPane(txtEndereco), gbc); gbc.gridwidth = 1; gbc.ipady = 0;

        // Linha 5 (Botão)
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        add(btnSalvar, gbc);
        
        //Adicione o ActionListener ao botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Validação simples para ver se o nome não está vazio
                if (txtNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return; // Para a execução se a validação falhar
                }
                
                // Cria um novo objeto Cliente
                Cliente novoCliente = new Cliente();
                
                // Preenche o objeto com os dados da interface
                novoCliente.setNome(txtNome.getText());
                novoCliente.setEmail(txtEmail.getText());
                novoCliente.setTelefone(txtTelefone.getText());
                novoCliente.setEndereco(txtEndereco.getText());
                novoCliente.setDataCadastro(LocalDate.now()); // Pega a data atual
                
                try {
                    String dataNascTexto = txtDataNascimento.getText();
                    if (dataNascTexto != null && !dataNascTexto.trim().isEmpty()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        novoCliente.setDataNascimento(LocalDate.parse(dataNascTexto, formatter));
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido para 'Data de Nascimento'. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return; // Para a execução
                }
                
                // Chama o método de cadastro
                novoCliente.cadastrarCliente();
                
                // Mostra uma mensagem de sucesso para o usuário
                JOptionPane.showMessageDialog(null, "Cliente '" + novoCliente.getNome() + "' cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
