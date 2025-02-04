import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginVisao extends JFrame {

    public LoginVisao() {
        setTitle("Login");
        setLayout(null);

        ImageIcon img = new ImageIcon(getClass().getResource("/img/logoFarmacia.png"));
        setIconImage(img.getImage());

        // Fecha o programa ao fechar a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int x = 100;
        int heightDefault = 40;

        JLabel labelName = new JLabel("Email:");
        JTextField nomeField = new JTextField();

        labelName.setBounds(x, 100, 300, 40);
        nomeField.setBounds(x, labelName.getY() + labelName.getHeight(), 300, heightDefault);

        JLabel lblPassword = new JLabel("Senha:");
        JPasswordField passwordField = new JPasswordField();

        lblPassword.setBounds(x, nomeField.getY() + nomeField.getHeight(), 300, heightDefault);
        passwordField.setBounds(x, lblPassword.getY() + lblPassword.getHeight(), 300, heightDefault);

        JButton botaoEnviar = new JButton("Enviar");
        botaoEnviar.setBounds(x, passwordField.getY() + passwordField.getHeight() + 10, 100, heightDefault);

        // Adiciona os componentes ao formulário
        add(labelName);
        add(nomeField);
        add(lblPassword);
        add(passwordField);
        add(botaoEnviar);

        // Adicionar listener ao botão
        botaoEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuPrincipalVisao();
            }
        });

        // Define o tamanho da janela
        setSize(500, 450);
        // Exibe a janela no centro da tela
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginVisao();
    }
}


