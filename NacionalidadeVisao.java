import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class NacionalidadeVisao extends JFrame implements ActionListener {
    private JTextField txtIdNacionalidade, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<NacionalidadeModelo> listaNacionalidades;

    public NacionalidadeVisao() {
        super("Nova Nacionalidade");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para a nova nacionalidade
        add(new JLabel("ID da Nacionalidade:"));
        txtIdNacionalidade = new JTextField(10);
        add(txtIdNacionalidade);

        add(new JLabel("Descrição da Nacionalidade:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Nacionalidades (para manter o controle das nacionalidades criadas)
        listaNacionalidades = new ArrayList<>();

        // Carregar nacionalidades existentes do arquivo Nacionalidade.TAB (se houver)
        carregarNacionalidades();

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
                String idNacionalidade = txtIdNacionalidade.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto NacionalidadeModelo
                NacionalidadeModelo nacionalidade = new NacionalidadeModelo(idNacionalidade, descricao);

                // Adiciona a nova nacionalidade à lista
                listaNacionalidades.add(nacionalidade);

                // Salva as nacionalidades no arquivo
                salvarNacionalidades();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Nacionalidade cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as nacionalidades do arquivo Nacionalidade.TAB
    public void carregarNacionalidades() {
        File arquivoNacionalidade = new File("Nacionalidade.TAB");

        if (arquivoNacionalidade.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoNacionalidade))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma nacionalidade
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaNacionalidades.add(new NacionalidadeModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as nacionalidades.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as nacionalidades no arquivo Nacionalidade.TAB
    public void salvarNacionalidades() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Nacionalidade.TAB", false))) {
            for (NacionalidadeModelo nacionalidade : listaNacionalidades) {
                writer.write(nacionalidade.getIdNacionalidade() + "\t" + nacionalidade.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as nacionalidades.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new NacionalidadeVisao();
    }
}
