import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class MunicipioVisao extends JFrame implements ActionListener {
    private JTextField txtIdMunicipio, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<MunicipioModelo> listaMunicipios;

    public MunicipioVisao() {
        super("Novo Município");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para o novo município
        add(new JLabel("ID do Município:"));
        txtIdMunicipio = new JTextField(10);
        add(txtIdMunicipio);

        add(new JLabel("Descrição do Município:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Municípios (para manter o controle dos municípios criados)
        listaMunicipios = new ArrayList<>();

        // Carregar municípios existentes do arquivo Municipio.TAB (se houver)
        carregarMunicipios();

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
                String idMunicipio = txtIdMunicipio.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto MunicipioModelo
                MunicipioModelo municipio = new MunicipioModelo(idMunicipio, descricao);

                // Adiciona o novo município à lista
                listaMunicipios.add(municipio);

                // Salva os municípios no arquivo
                salvarMunicipios();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Município cadastrado com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar os municípios do arquivo Municipio.TAB
    public void carregarMunicipios() {
        File arquivoMunicipio = new File("Municipio.TAB");

        if (arquivoMunicipio.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoMunicipio))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa um município
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaMunicipios.add(new MunicipioModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os municípios.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar os municípios no arquivo Municipio.TAB
    public void salvarMunicipios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Municipio.TAB", false))) {
            for (MunicipioModelo municipio : listaMunicipios) {
                writer.write(municipio.getIdMunicipio() + "\t" + municipio.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os municípios.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MunicipioVisao();
    }
}
