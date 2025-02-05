import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class DioceseVisao extends JFrame implements ActionListener {
    private JTextField txtIdDiocese, txtNome;
    private JButton btnSalvar;
    private ArrayList<DiocesesModelo> listaDioceses;

    public DioceseVisao() {
        super("Nova Diocese");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para a nova diocese
        add(new JLabel("ID da Diocese:"));
        txtIdDiocese = new JTextField(10);
        add(txtIdDiocese);

        add(new JLabel("Nome da Diocese:"));
        txtNome = new JTextField(20);
        add(txtNome);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Dioceses (para manter o controle das dioceses criadas)
        listaDioceses = new ArrayList<>();

        // Carregar dioceses existentes do arquivo Diocese.TAB (se houver)
        carregarDioceses();

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
                String idDiocese = txtIdDiocese.getText();
                String nome = txtNome.getText();

                // Cria um novo objeto DiocesesModelo
                DiocesesModelo diocese = new DiocesesModelo(idDiocese, nome);

                // Adiciona a nova diocese à lista
                listaDioceses.add(diocese);

                // Salva as dioceses no arquivo
                salvarDioceses();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Diocese cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as dioceses do arquivo Diocese.TAB
    public void carregarDioceses() {
        File arquivoDiocese = new File("Diocese.TAB");

        if (arquivoDiocese.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoDiocese))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma diocese
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaDioceses.add(new DiocesesModelo(dados[0], dados[1]));  // ID e Nome
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as dioceses.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as dioceses no arquivo Diocese.TAB
    public void salvarDioceses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Diocese.TAB", false))) {
            for (DiocesesModelo diocese : listaDioceses) {
                writer.write(diocese.getIdDiocese() + "\t" + diocese.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as dioceses.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DioceseVisao();
    }
}
