import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class StockVisao extends JFrame {

    private static ArrayList<EntradaModelo> listaEntradas;
    private static ArrayList<SaidaModelo> listaSaidas;
    private JList<String> listaEntradasExibicao;
    private JList<String> listaSaidasExibicao;
    private DefaultListModel<String> modeloEntradasLista;
    private DefaultListModel<String> modeloSaidasLista;

    public StockVisao() {
        // Carregar entradas e saídas do arquivo
        carregarEntradasESaidas();

        // Configurar a interface gráfica
        setTitle("Gestão de Estoque");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Definir layout
        setLayout(new BorderLayout());

        // Listagem de entradas
        modeloEntradasLista = new DefaultListModel<>();
        for (EntradaModelo entrada : listaEntradas) {
            modeloEntradasLista.addElement(entrada.getNomeMedicamento() + " - " + entrada.getDataEntrada());
        }

        listaEntradasExibicao = new JList<>(modeloEntradasLista);
        JScrollPane scrollPaneEntradas = new JScrollPane(listaEntradasExibicao);
        add(scrollPaneEntradas, BorderLayout.WEST);

        // Listagem de saídas
        modeloSaidasLista = new DefaultListModel<>();
        for (SaidaModelo saida : listaSaidas) {
            modeloSaidasLista.addElement(saida.getMedicamento() + " - " + saida.getDataSaida());
        }

        listaSaidasExibicao = new JList<>(modeloSaidasLista);
        JScrollPane scrollPaneSaidas = new JScrollPane(listaSaidasExibicao);
        add(scrollPaneSaidas, BorderLayout.EAST);

        // Botões de manipulação
        JPanel painelBotoes = new JPanel();
        JButton botaoEditarEntrada = new JButton("Editar Entrada");
        JButton botaoApagarEntrada = new JButton("Apagar Entrada");
        JButton botaoEditarSaida = new JButton("Editar Saída");
        JButton botaoApagarSaida = new JButton("Apagar Saída");

        // Função para editar entrada
        botaoEditarEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaEntradasExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    EntradaModelo entradaSelecionada = listaEntradas.get(indiceSelecionado);
                    new EntradaVisao(entradaSelecionada);  // Passa o objeto selecionado para a tela de edição
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma entrada para editar.");
                }
            }
        });

        // Função para apagar entrada
        botaoApagarEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaEntradasExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    EntradaModelo entradaSelecionada = listaEntradas.get(indiceSelecionado);
                    apagarEntrada(entradaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma entrada para apagar.");
                }
            }
        });

        // Função para editar saída
        botaoEditarSaida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaSaidasExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    SaidaModelo saidaSelecionada = listaSaidas.get(indiceSelecionado);
                    new SaidaVisao(saidaSelecionada);  // Passa o objeto selecionado para a tela de edição
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma saída para editar.");
                }
            }
        });

        // Função para apagar saída
        botaoApagarSaida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaSaidasExibicao.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    SaidaModelo saidaSelecionada = listaSaidas.get(indiceSelecionado);
                    apagarSaida(saidaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma saída para apagar.");
                }
            }
        });

        painelBotoes.add(botaoEditarEntrada);
        painelBotoes.add(botaoApagarEntrada);
        painelBotoes.add(botaoEditarSaida);
        painelBotoes.add(botaoApagarSaida);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para carregar entradas e saídas do arquivo
    private void carregarEntradasESaidas() {
        listaEntradas = new ArrayList<>();
        listaSaidas = new ArrayList<>();
        File arquivoEntradas = new File("Entradas.DAT");
        File arquivoSaidas = new File("Saidas.DAT");

        // Carregar entradas
        if (arquivoEntradas.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoEntradas))) {
                while (true) {
                    EntradaModelo entrada = (EntradaModelo) ois.readObject();
                    listaEntradas.add(entrada);
                }
            } catch (EOFException e) {
                // Fim do arquivo
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Carregar saídas
        if (arquivoSaidas.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoSaidas))) {
                while (true) {
                    SaidaModelo saida = (SaidaModelo) ois.readObject();
                    listaSaidas.add(saida);
                }
            } catch (EOFException e) {
                // Fim do arquivo
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para apagar entrada
    private void apagarEntrada(EntradaModelo entrada) {
        listaEntradas.remove(entrada);
        salvarEntradasNoArquivo();
        atualizarListas();
    }

    // Método para apagar saída
    private void apagarSaida(SaidaModelo saida) {
        listaSaidas.remove(saida);
        salvarSaidasNoArquivo();
        atualizarListas();
    }

    // Método para salvar entradas no arquivo
    private void salvarEntradasNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Entradas.DAT"))) {
            for (EntradaModelo entrada : listaEntradas) {
                oos.writeObject(entrada);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar entradas no arquivo.");
        }
    }

    // Método para salvar saídas no arquivo
    private void salvarSaidasNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Saidas.DAT"))) {
            for (SaidaModelo saida : listaSaidas) {
                oos.writeObject(saida);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar saídas no arquivo.");
        }
    }

    // Atualizar as listas de entradas e saídas
    private void atualizarListas() {
        modeloEntradasLista.clear();
        for (EntradaModelo entrada : listaEntradas) {
            modeloEntradasLista.addElement(entrada.getNomeMedicamento() + " - " + entrada.getDataEntrada());
        }

        modeloSaidasLista.clear();
        for (SaidaModelo saida : listaSaidas) {
            modeloSaidasLista.addElement(saida.getMedicamento() + " - " + saida.getDataSaida());
        }
    }

    public static void main(String[] args) {
        new StockVisao();
    }
}
