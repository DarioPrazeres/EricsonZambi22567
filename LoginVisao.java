import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class LoginVisao extends JFrame {

    private static ArrayList<UsuarioModelo> listaUsuarios;

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
                String email = nomeField.getText();
                String senha = new String(passwordField.getPassword());

                // Validar as credenciais do usuário
                if (validarCredenciais(email, senha)) {
                    dispose();
                    new MenuPrincipalVisao();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas. Tente novamente.");
                }
            }
        });

        // Carregar lista de usuários do arquivo
        carregarUsuarios();

        // Define o tamanho da janela
        setSize(500, 450);
        // Exibe a janela no centro da tela
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para carregar os usuários do arquivo
    private void carregarUsuarios() {
        listaUsuarios = new ArrayList<>();
        File arquivo = new File("Usuarios.DAT");

        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                while (true) {
                    UsuarioModelo usuario = (UsuarioModelo) ois.readObject();
                    listaUsuarios.add(usuario);
                }
            } catch (EOFException e) {
                // Fim do arquivo
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para validar as credenciais
    private boolean validarCredenciais(String email, String senha) {
        for (UsuarioModelo usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(senha)) {
                return true;  // Usuário encontrado e senha correta
            }
        }
        return false;  // Credenciais inválidas
    }

    public static void main(String[] args) {
        new LoginVisao();
    }
}
