import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ListagemUsuarioVisao extends JFrame {

    private static ArrayList<UsuarioModelo> listaUsuarios;
    private JList<String> listaExibicao;
    private DefaultListModel<String> modeloLista;

    public ListagemUsuarioVisao() {
        // Carregar usuários do arquivo
        carregarUsuarios();

        // Configurar a interface gráfica
        setTitle("Gestão de Usuários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Definir layout
        setLayout(new BorderLayout());

        // Listagem de usuários
        modeloLista = new DefaultListModel<>();
        for (UsuarioModelo usuario : listaUsuarios) {
            modeloLista.addElement(usuario.getEmail() + " - " + usuario.getId());
        }

        listaExibicao = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaExibicao);
        add(scrollPane, BorderLayout.CENTER);

        // Botões de manipulação
        JPanel painelBotoes = new JPanel();
        JButton botaoEditar = new JButton("Editar");
        JButton botaoApagar = new JButton("Apagar");

        // Função para editar usuário
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    UsuarioModelo usuarioSelecionado = listaUsuarios.get(indiceSelecionado);
                    new UsuarioVisao(usuarioSelecionado);  // Passa o objeto selecionado para a tela de edição
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para editar.");
                }
            }
        });

        // Função para apagar usuário
        botaoApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    UsuarioModelo usuarioSelecionado = listaUsuarios.get(indiceSelecionado);
                    apagarUsuario(usuarioSelecionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para apagar.");
                }
            }
        });

        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoApagar);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para carregar usuários do arquivo
    private void carregarUsuarios() {
        listaUsuarios = new ArrayList<>();
        File arquivo = new File("Usuarios.DAT");

        // Verifica se o arquivo existe, se não, cria um novo arquivo vazio
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();  // Cria o arquivo vazio se não existir
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo Usuarios.Dat");
            }
            return;  // Arquivo criado, mas sem dados (a lista ficará vazia)
        }

        // Se o arquivo existe, tenta carregar os usuários
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                UsuarioModelo usuario = (UsuarioModelo) ois.readObject();
                listaUsuarios.add(usuario);
            }
        } catch (EOFException e) {
            // Fim do arquivo (não há mais dados)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para editar usuário
    private void editarUsuario(UsuarioModelo usuario) {
        // Exemplo de edição: só modificando o nome do usuário
        String novoNome = JOptionPane.showInputDialog("Novo nome do usuário:", usuario.getEmail());
        if (novoNome != null && !novoNome.isEmpty()) {
            usuario.setEmail(novoNome);
            atualizarLista();
        }
    }

    // Atualizar a lista de usuários na interface
    private void atualizarLista() {
        modeloLista.clear();
        for (UsuarioModelo usuario : listaUsuarios) {
            modeloLista.addElement(usuario.getEmail() + " - " + usuario.getId());
        }
    }

    private void apagarUsuario(UsuarioModelo usuario) {
        listaUsuarios.remove(usuario);
    
        salvarUsuariosNoArquivo();
    
        atualizarLista();
    }

    private void salvarUsuariosNoArquivo() {
        try {
            File arquivo = new File("Usuarios.DAT");
    
            // Reescrever os dados no arquivo com a lista de usuários atualizada
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                for (UsuarioModelo usuario : listaUsuarios) {
                    oos.writeObject(usuario);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os usuários no arquivo.");
        }
    }

    public static void main(String[] args) {
        new ListagemUsuarioVisao();
    }
}
