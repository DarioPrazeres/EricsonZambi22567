import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ListagemFornecedores extends JFrame {

    private static ArrayList<FornecedorModelo> listaFornecedores;
    private JList<String> listaExibicao;
    private DefaultListModel<String> modeloLista;

    public ListagemFornecedores() {
        // Carregar fornecedores do arquivo
        carregarFornecedores();

        // Configurar a interface gráfica
        setTitle("Gestão de Fornecedores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Definir layout
        setLayout(new BorderLayout());

        // Listagem de fornecedores
        modeloLista = new DefaultListModel<>();
        for (FornecedorModelo fornecedor : listaFornecedores) {
            modeloLista.addElement(fornecedor.getNomeFornecedor() + " - " + fornecedor.getId());
        }

        listaExibicao = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaExibicao);
        add(scrollPane, BorderLayout.CENTER);

        // Botões de manipulação
        JPanel painelBotoes = new JPanel();
        JButton botaoEditar = new JButton("Editar");
        JButton botaoApagar = new JButton("Apagar");

        // Função para editar fornecedor
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    FornecedorModelo fornecedorSelecionado = listaFornecedores.get(indiceSelecionado);
                    new FornecedorVisao(fornecedorSelecionado);  // Passa o objeto selecionado para a tela de edição
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um fornecedor para editar.");
                }
            }
        });

        // Função para apagar fornecedor
        botaoApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    FornecedorModelo fornecedorSelecionado = listaFornecedores.get(indiceSelecionado);
                    apagarFornecedor(fornecedorSelecionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um fornecedor para apagar.");
                }
            }
        });

        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoApagar);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para carregar fornecedores do arquivo
    private void carregarFornecedores() {
        listaFornecedores = new ArrayList<>();
        File arquivo = new File("Fornecedores.DAT");

        // Verifica se o arquivo existe, se não, cria um novo arquivo vazio
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();  // Cria o arquivo vazio se não existir
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo Fornecedores.Dat");
            }
            return;  // Arquivo criado, mas sem dados (a lista ficará vazia)
        }

        // Se o arquivo existe, tenta carregar os fornecedores
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                FornecedorModelo fornecedor = (FornecedorModelo) ois.readObject();
                listaFornecedores.add(fornecedor);
            }
        } catch (EOFException e) {
            // Fim do arquivo (não há mais dados)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para editar fornecedor
    private void editarFornecedor(FornecedorModelo fornecedor) {
        // Exemplo de edição: só modificando o nome do fornecedor
        String novoNome = JOptionPane.showInputDialog("Novo nome do fornecedor:", fornecedor.getNomeFornecedor());
        if (novoNome != null && !novoNome.isEmpty()) {
            fornecedor.setNomeFornecedor(novoNome);
            atualizarLista();
        }
    }

    // Atualizar a lista de fornecedores na interface
    private void atualizarLista() {
        modeloLista.clear();
        for (FornecedorModelo fornecedor : listaFornecedores) {
            modeloLista.addElement(fornecedor.getNomeFornecedor() + " - " + fornecedor.getId());
        }
    }

    private void apagarFornecedor(FornecedorModelo fornecedor) {
        listaFornecedores.remove(fornecedor);
    
        salvarFornecedoresNoArquivo();
    
        atualizarLista();
    }

    private void salvarFornecedoresNoArquivo() {
        try {
            File arquivo = new File("Fornecedores.DAT");
    
            // Reescrever os dados no arquivo com a lista de fornecedores atualizada
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                for (FornecedorModelo fornecedor : listaFornecedores) {
                    oos.writeObject(fornecedor);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os fornecedores no arquivo.");
        }
    }

    public static void main(String[] args) {
        new ListagemFornecedores();
    }
}
