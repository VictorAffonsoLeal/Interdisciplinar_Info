
package view.panels;

import model.Usuario;

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

public class UsuarioPanel extends JPanel {

    // Declare os componentes
    private JTextField txtNome;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JTextField txtDataNascimento;
    private JButton btnSalvar;

    public UsuarioPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialize os componentes
        txtNome = (JTextField) createStyledTextField(false);
        txtLogin = (JTextField) createStyledTextField(false);
        txtSenha = (JPasswordField) createStyledPasswordField();
        txtDataNascimento = (JTextField) createStyledTextField(false);
        btnSalvar = createSaveButton("Salvar Usuário");

        // --- Montagem do Layout ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.1; add(new JLabel("Nome"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.9; gbc.gridwidth = 3; add(txtNome, gbc); gbc.gridwidth = 1;
        
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Login"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.4; add(txtLogin, gbc);
        gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0.1; add(new JLabel("Senha"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 0.4; add(txtSenha, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Data de Nascimento"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(txtDataNascimento, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        add(btnSalvar, gbc);

        // Adicione o ActionListener ao botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validações
                if (txtNome.getText().trim().isEmpty() || txtLogin.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Os campos 'Nome' e 'Login' são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtSenha.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "O campo 'Senha' é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cria um novo objeto Usuario
                Usuario novoUsuario = new Usuario();

                // Preenche o objeto com os dados da interface
                novoUsuario.setNome(txtNome.getText());
                novoUsuario.setLogin(txtLogin.getText());
                novoUsuario.setSenha(new String(txtSenha.getPassword())); // Converte char[] para String

                // Tratamento da data de nascimento
                try {
                    String dataNascTexto = txtDataNascimento.getText();
                    if (dataNascTexto != null && !dataNascTexto.trim().isEmpty()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        novoUsuario.setDataNascimento(LocalDate.parse(dataNascTexto, formatter));
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Chama o método de cadastro
                novoUsuario.cadastrarUsuario();

                // Mostra mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Usuário '" + novoUsuario.getNome() + "' cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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

    private JPasswordField createStyledPasswordField() {
        final Color borderColor = new Color(244, 114, 182);
        Border line = new LineBorder(borderColor, 2);
        Border padding = new EmptyBorder(10, 10, 10, 10);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Inter", Font.PLAIN, 14));
        passwordField.setBorder(new CompoundBorder(line, padding));
        return passwordField;
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
