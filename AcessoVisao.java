import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class AcessoVisao extends JFrame implements ActionListener {
    private JTextField txtIdAcesso, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<AcessoModelo> listaAcessos;

    public AcessoVisao() {
        super("Novo Acesso");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para o novo acesso
        add(new JLabel("ID de Acesso:"));
        txtIdAcesso = new JTextField(10);
        add(txtIdAcesso);

        add(new JLabel("Descrição do Acesso:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Acessos (para manter o controle dos acessos criados)
        listaAcessos = new ArrayList<>();

        // Carregar acessos existentes do arquivo Acesso.TAB (se houver)
        carregarAcessos();

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
                String idAcesso = txtIdAcesso.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto AcessoModelo
                AcessoModelo acesso = new AcessoModelo(idAcesso, descricao);

                // Adiciona o novo Acesso à lista
                listaAcessos.add(acesso);

                // Salva o novo acesso no arquivo
                salvarAcessos();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Acesso cadastrado com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar os acessos do arquivo Acesso.TAB
    public void carregarAcessos() {
        File arquivoAcesso = new File("Acesso.TAB");

        if (arquivoAcesso.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAcesso))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa um Acesso
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaAcessos.add(new AcessoModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os acessos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar os acessos no arquivo Acesso.TAB
    public void salvarAcessos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Acesso.TAB", false))) {
            for (AcessoModelo acesso : listaAcessos) {
                writer.write(acesso.getIdAcesso() + "\t" + acesso.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os acessos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AcessoVisao();
    }
}
