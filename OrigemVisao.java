import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class OrigemVisao extends JFrame implements ActionListener {
    private JTextField txtIdOrigem, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<OrigemModelo> listaOrigens;

    public OrigemVisao() {
        super("Nova Origem");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para o novo origem
        add(new JLabel("ID de Origem:"));
        txtIdOrigem = new JTextField(10);
        add(txtIdOrigem);

        add(new JLabel("Descrição da Origem:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Origens (para manter o controle das origens criadas)
        listaOrigens = new ArrayList<>();

        // Carregar origens existentes do arquivo Origem.TAB (se houver)
        carregarOrigens();

        // Configurações da janela
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            try {
                // Pega dados dos campos de texto
                String idOrigem = txtIdOrigem.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto OrigemModelo
                OrigemModelo origem = new OrigemModelo(idOrigem, descricao);

                // Adiciona a nova Origem à lista
                listaOrigens.add(origem);

                // Salva a nova origem no arquivo
                salvarOrigens();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Origem cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as origens do arquivo Origem.TAB
    public void carregarOrigens() {
        File arquivoOrigem = new File("Origem.TAB");

        if (arquivoOrigem.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoOrigem))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma Origem
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaOrigens.add(new OrigemModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as origens.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as origens no arquivo Origem.TAB
    public void salvarOrigens() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Origem.TAB", false))) {
            for (OrigemModelo origem : listaOrigens) {
                writer.write(origem.getIdOrigem() + "\t" + origem.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as origens.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new OrigemVisao();
    }
}
