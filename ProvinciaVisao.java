import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ProvinciaVisao extends JFrame implements ActionListener {
    private JTextField txtIdProvincia, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<ProvinciaModelo> listaProvincias;

    public ProvinciaVisao() {
        super("Nova Província");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para a nova província
        add(new JLabel("ID da Província:"));
        txtIdProvincia = new JTextField(10);
        add(txtIdProvincia);

        add(new JLabel("Descrição da Província:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Províncias (para manter o controle das províncias criadas)
        listaProvincias = new ArrayList<>();

        // Carregar províncias existentes do arquivo Provincia.TAB (se houver)
        carregarProvincias();

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
                String idProvincia = txtIdProvincia.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto ProvinciaModelo
                ProvinciaModelo provincia = new ProvinciaModelo(idProvincia, descricao);

                // Adiciona a nova província à lista
                listaProvincias.add(provincia);

                // Salva as províncias no arquivo
                salvarProvincias();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Província cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as províncias do arquivo Provincia.TAB
    public void carregarProvincias() {
        File arquivoProvincia = new File("Provincia.TAB");

        if (arquivoProvincia.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoProvincia))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma província
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaProvincias.add(new ProvinciaModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as províncias.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as províncias no arquivo Provincia.TAB
    public void salvarProvincias() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Provincia.TAB", false))) {
            for (ProvinciaModelo provincia : listaProvincias) {
                writer.write(provincia.getIdProvincia() + "\t" + provincia.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as províncias.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ProvinciaVisao();
    }
}
