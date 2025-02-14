
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class InterfaceMedicamento extends JFrame {

    private static ArrayList<MedicamentoModelo> listaMedicamentos;
    private JList<String> listaExibicao;
    private DefaultListModel<String> modeloLista;

    public InterfaceMedicamento() {
        // Carregar medicamentos do arquivo
        carregarMedicamentos();

        // Configurar a interface gráfica
        setTitle("Gestão de Medicamentos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Definir layout
        setLayout(new BorderLayout());

        // Listagem de medicamentos
        modeloLista = new DefaultListModel<>();
        for (MedicamentoModelo medicamento : listaMedicamentos) {
            modeloLista.addElement(medicamento.getNome() + " - " + medicamento.getId());
        }

        listaExibicao = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaExibicao);
        add(scrollPane, BorderLayout.CENTER);

        // Botões de manipulação
        JPanel painelBotoes = new JPanel();
        JButton botaoEditar = new JButton("Editar");
        JButton botaoApagar = new JButton("Apagar");

        // Função para editar medicamento
// Função para editar medicamento
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    MedicamentoModelo medicamentoSelecionado = listaMedicamentos.get(indiceSelecionado);
                    new MedicamentoVisao(medicamentoSelecionado);  // Passa o objeto selecionado para a tela de edição
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um medicamento para editar.");
                }
            }
        });

        // Função para apagar medicamento
        botaoApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    MedicamentoModelo medicamentoSelecionado = listaMedicamentos.get(indiceSelecionado);
                    apagarMedicamento(medicamentoSelecionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um medicamento para apagar.");
                }
            }
        });

        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoApagar);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para carregar medicamentos do arquivo
    private void carregarMedicamentos() {
        listaMedicamentos = new ArrayList<>();
        File arquivo = new File("Medicamentos.DAT");

        // Verifica se o arquivo existe, se não, cria um novo arquivo vazio
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();  // Cria o arquivo vazio se não existir
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo Medicamento.Dat");
            }
            return;  // Arquivo criado, mas sem dados (a lista ficará vazia)
        }

        // Se o arquivo existe, tenta carregar os medicamentos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                MedicamentoModelo medicamento = (MedicamentoModelo) ois.readObject();
                listaMedicamentos.add(medicamento);
            }
        } catch (EOFException e) {
            // Fim do arquivo (não há mais dados)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para editar medicamento
    private void editarMedicamento(MedicamentoModelo medicamento) {
        // Exemplo de edição: só modificando o nome do medicamento
        String novoNome = JOptionPane.showInputDialog("Novo nome do medicamento:", medicamento.getNome());
        if (novoNome != null && !novoNome.isEmpty()) {
            medicamento.setNome(novoNome);
            atualizarLista();
        }
    }

    // Atualizar a lista de medicamentos na interface
    private void atualizarLista() {
        modeloLista.clear();
        for (MedicamentoModelo medicamento : listaMedicamentos) {
            modeloLista.addElement(medicamento.getNome() + " - " + medicamento.getId());
        }
    }

    private void apagarMedicamento(MedicamentoModelo medicamento) {
        listaMedicamentos.remove(medicamento);
    
        salvarMedicamentosNoArquivo();
    
        atualizarLista();
    }

    private void salvarMedicamentosNoArquivo() {
        try {
            File arquivo = new File("Medicamentos.DAT");
    
            // Reescrever os dados no arquivo com a lista de medicamentos atualizada
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                for (MedicamentoModelo medicamento : listaMedicamentos) {
                    oos.writeObject(medicamento);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os medicamentos no arquivo.");
        }
    }
    

    public static void main(String[] args) {
        new InterfaceMedicamento();
    }
}
