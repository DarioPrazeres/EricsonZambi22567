import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PesquisaParoquiaVisao extends JFrame implements ActionListener {

    private JComboBox<String> cmbParoquia;
    private JSpinner spinnerDataFundacao;
    private JButton btnPesquisar;
    private JTextArea resultadoArea;

    public PesquisaParoquiaVisao() {
        super("Pesquisa de Paróquia");

        // Definindo layout
        setLayout(new GridLayout(4, 2));

        // ComboBox de seleção de paróquia
        add(new JLabel("Paróquia:"));
        cmbParoquia = new JComboBox<>();
        add(cmbParoquia);
        carregarParoquia();  // Carregar opções do arquivo

        // JSpinner para a data de fundação
        add(new JLabel("Data de Fundação:"));
        spinnerDataFundacao = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerDataFundacao, "dd/MM/yyyy");
        spinnerDataFundacao.setEditor(dateEditor);
        add(spinnerDataFundacao);

        // Botão de pesquisa
        btnPesquisar = new JButton("Pesquisar");
        add(btnPesquisar);
        btnPesquisar.addActionListener(this);

        // Área de resultado da pesquisa
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        add(scrollPane);

        // Configurações da janela
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPesquisar) {
            String parSelected = (String) cmbParoquia.getSelectedItem();
            Date dataSelected = (Date) spinnerDataFundacao.getValue();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = sdf.format(dataSelected);

            // Exibir os resultados da pesquisa
            pesquisarParoquia(parSelected, dataFormatada);
        }
    }

    private void pesquisarParoquia(String paroquia, String dataFundacao) {
        File arquivoParoquias = new File("Paroquias.TAB");

        if (arquivoParoquias.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoParoquias))) {
                String linha;
                boolean encontrado = false;
                resultadoArea.setText("");  // Limpar área de resultados

                while ((linha = reader.readLine()) != null) {
                    // Supondo que o formato da linha seja "Paróquia;DataFundacao"
                    String[] partes = linha.split(";");
                    String nomeParoquia = partes[0].trim();
                    String data = partes[1].trim();

                    if (nomeParoquia.equals(paroquia) && data.equals(dataFundacao)) {
                        resultadoArea.append("Paróquia: " + nomeParoquia + "\n");
                        resultadoArea.append("Data de Fundação: " + data + "\n");
                        resultadoArea.append("------\n");
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    resultadoArea.setText("Nenhuma paróquia encontrada com os critérios fornecidos.");
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Arquivo de paróquias não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarParoquia() {
        File arquivoParoquia = new File("Paroquias.TAB");

        if (arquivoParoquia.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoParoquia))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbParoquia.addItem(linha.split(";")[0].trim());  // Adiciona apenas o nome da paróquia
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as paróquias.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new PesquisaParoquiaVisao();
    }
}
