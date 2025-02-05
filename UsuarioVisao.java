import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class UsuarioVisao extends JFrame implements ActionListener {
    private static ArrayList<UsuarioModelo> listaUsuarios = new ArrayList<>();
    private JTextField txtEmail, txtPassword, txtUsername, txtIdAcesso;
    private JButton btnSalvar, btnEditar;
    private UsuarioModelo usuarioParaEditar;

    public UsuarioVisao() {
        super("Novo Usuário");
        setLayout(new GridLayout(5, 2));

        // Adicionando campos para o novo usuário
        add(new JLabel("Email:"));
        txtEmail = new JTextField(20);
        add(txtEmail);

        add(new JLabel("Senha:"));
        txtPassword = new JTextField(20);
        add(txtPassword);

        add(new JLabel("Nome de Usuário:"));
        txtUsername = new JTextField(20);
        add(txtUsername);

        add(new JLabel("ID de Acesso:"));
        txtIdAcesso = new JTextField(10);
        add(txtIdAcesso);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Configurações da janela
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public UsuarioVisao(UsuarioModelo usuarioModelo) {
        super("Editar Usuário");
        setLayout(new GridLayout(5, 2));

        // Adicionando campos para editar o usuário
        add(new JLabel("Email:"));
        txtEmail = new JTextField(usuarioModelo.getEmail(), 20);
        add(txtEmail);

        add(new JLabel("Senha:"));
        txtPassword = new JTextField(usuarioModelo.getPassword(), 20);
        add(txtPassword);

        add(new JLabel("Nome de Usuário:"));
        txtUsername = new JTextField(usuarioModelo.getUsername(), 20);
        add(txtUsername);

        add(new JLabel("ID de Acesso:"));
        txtIdAcesso = new JTextField(String.valueOf(usuarioModelo.getIdAcesso()), 10);
        add(txtIdAcesso);

        this.usuarioParaEditar = usuarioModelo;

        // Botão de editar
        btnEditar = new JButton("Salvar");
        add(btnEditar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Atualiza os dados do usuário com os novos valores
                    usuarioParaEditar.setEmail(txtEmail.getText());
                    usuarioParaEditar.setPassword(txtPassword.getText());
                    usuarioParaEditar.setUsername(txtUsername.getText());
                    usuarioParaEditar.setIdAcesso(Integer.parseInt(txtIdAcesso.getText()));

                    // Atualiza o usuário no arquivo
                    atualizarUsuario(usuarioParaEditar);

                    JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                    dispose(); // Fecha a janela após salvar
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configurações da janela
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            try {
                // Pega dados dos campos de texto
                String email = txtEmail.getText();
                String password = txtPassword.getText();
                String username = txtUsername.getText();
                int idAcesso = Integer.parseInt(txtIdAcesso.getText());

                // Cria um novo objeto UsuarioModelo
                UsuarioModelo usuario = new UsuarioModelo(email, password, username, idAcesso);

                // Salva o objeto UsuarioModelo no arquivo
                salvarUsuario(usuario);

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                // Trata erros de conversão de dados numéricos
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos numéricos estão corretos.",
                        "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar o usuário no arquivo
    public void salvarUsuario(UsuarioModelo usuario) {
        try {
            File file = new File("Usuarios.DAT");
            boolean arquivoExistente = file.exists();

            ArrayList<UsuarioModelo> listaUsuariosExistentes = new ArrayList<>();
            if (arquivoExistente) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            UsuarioModelo m = (UsuarioModelo) ois.readObject();
                            listaUsuariosExistentes.add(m);
                        } catch (EOFException e) {
                            break; // Fim de arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            listaUsuariosExistentes.add(usuario);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (UsuarioModelo m : listaUsuariosExistentes) {
                    oos.writeObject(m);
                }
            }

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    // Método para atualizar o usuário no arquivo
    private void atualizarUsuario(UsuarioModelo usuarioAlterado) {
        try {
            File file = new File("Usuarios.DAT");
            ArrayList<UsuarioModelo> listaUsuariosExistentes = new ArrayList<>();

            // Carregar os usuários existentes
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            UsuarioModelo usuario = (UsuarioModelo) ois.readObject();
                            if (usuario.getId() == usuarioAlterado.getId()) {
                                listaUsuariosExistentes.add(usuarioAlterado); // Substitui o usuário editado
                            } else {
                                listaUsuariosExistentes.add(usuario); // Mantém os outros usuários
                            }
                        } catch (EOFException e) {
                            break; // Fim do arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Salva a lista atualizada de usuários
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (UsuarioModelo usuario : listaUsuariosExistentes) {
                    oos.writeObject(usuario);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário: " + e.getMessage());
        }
    }
}
