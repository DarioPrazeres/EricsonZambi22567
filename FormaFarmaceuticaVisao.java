import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class FormaFarmaceuticaVisao extends JFrame implements ActionListener {
    private JTextField txtIdFormaFarmaceutica, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<FormaFarmaceuticaModelo> listaFormasFarmaceuticas;

    public FormaFarmaceuticaVisao() {
        super("Nova Forma Farmacêutica");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para a nova forma farmacêutica
        add(new JLabel("ID da Forma Farmacêutica:"));
        txtIdFormaFarmaceutica = new JTextField(10);
        add(txtIdFormaFarmaceutica);

        add(new JLabel("Descrição da Forma Farmacêutica:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Formas Farmacêuticas (para manter o controle das formas criadas)
        listaFormasFarmaceuticas = new ArrayList<>();

        // Carregar formas farmacêuticas existentes do arquivo FormaFarmaceutica.TAB (se houver)
        carregarFormasFarmaceuticas();

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
                String idFormaFarmaceutica = txtIdFormaFarmaceutica.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto FormaFarmaceuticaModelo
                FormaFarmaceuticaModelo formaFarmaceutica = new FormaFarmaceuticaModelo(idFormaFarmaceutica, descricao);

                // Adiciona a nova Forma Farmacêutica à lista
                listaFormasFarmaceuticas.add(formaFarmaceutica);

                // Salva a nova forma farmacêutica no arquivo
                salvarFormasFarmaceuticas();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Forma Farmacêutica cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as formas farmacêuticas do arquivo FormaFarmaceutica.TAB
    public void carregarFormasFarmaceuticas() {
        File arquivoFormaFarmaceutica = new File("FormaFarmaceutica.TAB");

        if (arquivoFormaFarmaceutica.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoFormaFarmaceutica))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma Forma Farmacêutica
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaFormasFarmaceuticas.add(new FormaFarmaceuticaModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as formas farmacêuticas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as formas farmacêuticas no arquivo FormaFarmaceutica.TAB
    public void salvarFormasFarmaceuticas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FormaFarmaceutica.TAB", false))) {
            for (FormaFarmaceuticaModelo formaFarmaceutica : listaFormasFarmaceuticas) {
                writer.write(formaFarmaceutica.getIdFormaFarmaceutica() + "\t" + formaFarmaceutica.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as formas farmacêuticas.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FormaFarmaceuticaVisao();
    }
}
