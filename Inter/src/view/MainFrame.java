package view;

import view.panels.*;
import view.panels.ClientePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentArea;
    private Map<String, JButton> navButtons = new HashMap<>();
    private final Color activeColor = new Color(244, 114, 182); // Rosa
    private final Color inactiveColor = new Color(203, 213, 225); // Cinza claro

    public MainFrame() {
        setTitle("Sistema de Gerenciamento para Gráfica");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        // Barra de Navegação Lateral (Esquerda)
        add(createSidebar(), BorderLayout.WEST);

        // Área de Conteúdo (Centro) 
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(new Color(241, 245, 249));

        // Cria e adiciona todas as páginas
        contentArea.add(createFormPanel("Cadastro de Clientes", new ClientePanel()), "CLIENTES");
        contentArea.add(createFormPanel("Cadastro de Usuários", new UsuarioPanel()), "USUARIOS");
        contentArea.add(createFormPanel("Cadastro de Produtos", new ProdutoPanel()), "PRODUTOS");
        contentArea.add(createFormPanel("Lançar Orçamento", new OrcamentoPanel()), "ORCAMENTOS");
        contentArea.add(createFormPanel("Lançar Pedido", new PedidoPanel()), "PEDIDOS");
        
        add(contentArea, BorderLayout.CENTER);

        // Mostra a primeira página ao iniciar
        showPage("CLIENTES");
    }

    private JPanel createSidebar() {
        // Painel com fundo gradiente
        JPanel sidebar = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(30, 41, 59);
                Color color2 = new Color(49, 46, 129);
                g2d.setPaint(new GradientPaint(0, 0, color1, 0, getHeight(), color2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sidebar.setPreferredSize(new Dimension(256, 0));

        // Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(49, 46, 129)));
        logoPanel.setPreferredSize(new Dimension(0, 96));
        JLabel logoLabel = new JLabel("G");
        logoLabel.setFont(new Font("Inter", Font.BOLD, 40));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setOpaque(true);
        logoLabel.setBackground(new Color(34, 211, 238));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setPreferredSize(new Dimension(64, 64));
        logoPanel.add(logoLabel);
        sidebar.add(logoPanel, BorderLayout.NORTH);

        // Botões do navegador
        JPanel navBox = new JPanel();
        navBox.setLayout(new BoxLayout(navBox, BoxLayout.Y_AXIS));
        navBox.setOpaque(false);
        navBox.setBorder(new EmptyBorder(16, 16, 16, 16));
        navBox.add(createNavButton("Cadastro Clientes", "CLIENTES"));
        navBox.add(Box.createRigidArea(new Dimension(0, 8)));
        navBox.add(createNavButton("Cadastro Usuários", "USUARIOS"));
        navBox.add(Box.createRigidArea(new Dimension(0, 8)));
        navBox.add(createNavButton("Cadastro Produtos", "PRODUTOS"));
        navBox.add(Box.createRigidArea(new Dimension(0, 8)));
        navBox.add(createNavButton("Lançar Orçamento", "ORCAMENTOS"));
        navBox.add(Box.createRigidArea(new Dimension(0, 8)));
        navBox.add(createNavButton("Lançar Pedido", "PEDIDOS"));
        sidebar.add(navBox, BorderLayout.CENTER);
        
        // Sair
        JPanel SairPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        SairPanel.setOpaque(false);
        SairPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
        JButton btnSair = createNavButton("Sair", "SAIR");
        btnSair.addActionListener(e -> System.exit(0));
        SairPanel.add(btnSair);
        sidebar.add(SairPanel, BorderLayout.SOUTH);
        
        return sidebar;
    }

    // Cria o botão de navegação
    private JButton createNavButton(String text, String pageName) {
        JButton button = new JButton(text);
        button.setForeground(inactiveColor);
        button.setFont(new Font("Inter", Font.PLAIN, 16));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        if (!pageName.equals("SAIR")) {
            button.addActionListener(e -> showPage(pageName));
            navButtons.put(pageName, button);
        }
        return button;
    }
    
    // Mostra a página correspondente ao botão clicado
    private void showPage(String pageName) {
        cardLayout.show(contentArea, pageName);
        for (Map.Entry<String, JButton> entry : navButtons.entrySet()) {
            entry.getValue().setForeground(entry.getKey().equals(pageName) ? activeColor : inactiveColor);
            entry.getValue().setFont(entry.getValue().getFont().deriveFont(entry.getKey().equals(pageName) ? Font.BOLD : Font.PLAIN));
        }
    }
    
    // Cria o painel de formulário com título e conteúdo
    private JPanel createFormPanel(String title, JPanel formGrid) {
        JPanel formContainer = new JPanel(new BorderLayout(0, 20));
        formContainer.setOpaque(false);
        formContainer.setBorder(new EmptyBorder(24, 24, 24, 24));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 28));
        titleLabel.setForeground(new Color(30, 41, 59));
        formContainer.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        contentPane.add(formGrid, BorderLayout.CENTER);

        formContainer.add(contentPane, BorderLayout.CENTER);
        return formContainer;
    }
}