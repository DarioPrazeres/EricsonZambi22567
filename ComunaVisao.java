import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ComunaVisao extends JFrame implements ActionListener {
    private JTextField txtIdComuna, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<ComunaModelo> listaComunas;

    public ComunaVisao() {
        super("Nova Comuna");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para a nova comuna
        add(new JLabel("ID da Comuna:"));
        txtIdComuna = new JTextField(10);
        add(txtIdComuna);

        add(new JLabel("Descrição da Comuna:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Comunas (para manter o controle das comunas criadas)
        listaComunas = new ArrayList<>();

        // Carregar comunas existentes do arquivo Comuna.TAB (se houver)
        carregarComunas();

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
                String idComuna = txtIdComuna.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto ComunaModelo
                ComunaModelo comuna = new ComunaModelo(idComuna, descricao);

                // Adiciona a nova comuna à lista
                listaComunas.add(comuna);

                // Salva as comunas no arquivo
                salvarComunas();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Comuna cadastrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar as comunas do arquivo Comuna.TAB
    public void carregarComunas() {
        File arquivoComuna = new File("Comuna.TAB");

        if (arquivoComuna.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoComuna))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa uma comuna
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaComunas.add(new ComunaModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as comunas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar as comunas no arquivo Comuna.TAB
    public void salvarComunas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Comuna.TAB", false))) {
            for (ComunaModelo comuna : listaComunas) {
                writer.write(comuna.getIdComuna() + "\t" + comuna.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as comunas.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ComunaVisao();
    }
}
