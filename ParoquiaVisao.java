import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ParoquiaVisao extends JFrame implements ActionListener {
    private JTextField txtIdParoquia, txtNome;
    private JButton btnSalvar;
    private ArrayList<ParoquiaModelo> listaParoquias;

    public ParoquiaVisao() {
        super("Nova Paróquia");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para a nova paróquia
        add(new JLabel("ID da Paróquia:"));
        txtIdParoquia = new JTextField(10);
        add(txtIdParoquia);

        add(new JLabel("Nome da Paróquia:"));
        txtNome = new JTextField(20);
        add(txtNome);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Paróquias (para manter o controle das paróquias criadas)
        listaParoquias = new ArrayList<>();

        // Carregar paróquias existentes do arquivo Paroquia.TAB (se houver)
        carregarParoquias();

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
                String idParoquia = txtIdParoquia.getText();
                String nome = txtNome.getText();

                // Cria um novo objeto ParoquiaModelo
                ParoquiaModelo paroquia = new ParoquiaModelo(idParoquia, nome);

                // Adiciona a nova paróquia à lista
                listaParoquias.add(paroquia);

                // Salva as paróquias no arquivo
                salvarParoquias();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Paróquia cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as paróquias do arquivo Paroquia.TAB
    public void carregarParoquias() {
        File arquivoParoquia = new File("Paroquia.TAB");

        if (arquivoParoquia.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoParoquia))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma paróquia
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaParoquias.add(new ParoquiaModelo(dados[0], dados[1]));  // ID e Nome
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as paróquias.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as paróquias no arquivo Paroquia.TAB
    public void salvarParoquias() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Paroquia.TAB", false))) {
            for (ParoquiaModelo paroquia : listaParoquias) {
                writer.write(paroquia.getIdParoquia() + "\t" + paroquia.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as paróquias.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ParoquiaVisao();
    }
}
