import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class TipoMedicamentoVisao extends JFrame implements ActionListener {
    private JTextField txtIdTipoMedicamento, txtDescricao;
    private JButton btnSalvar;
    private ArrayList<TipoMedicamentoModelo> listaTipoMedicamentos;

    public TipoMedicamentoVisao() {
        super("Novo Tipo de Medicamento");
        setLayout(new GridLayout(3, 2));  // Layout com 3 linhas e 2 colunas

        // Adicionando campos para o novo tipo de medicamento
        add(new JLabel("ID do Tipo de Medicamento:"));
        txtIdTipoMedicamento = new JTextField(10);
        add(txtIdTipoMedicamento);

        add(new JLabel("Descrição do Tipo de Medicamento:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Lista de Tipos de Medicamentos (para manter o controle dos tipos criados)
        listaTipoMedicamentos = new ArrayList<>();

        // Carregar tipos de medicamentos existentes do arquivo TipoMedicamento.TAB (se houver)
        carregarTiposMedicamentos();

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
                String idTipoMedicamento = txtIdTipoMedicamento.getText();
                String descricao = txtDescricao.getText();

                // Cria um novo objeto TipoMedicamentoModelo
                TipoMedicamentoModelo tipoMedicamento = new TipoMedicamentoModelo(idTipoMedicamento, descricao);

                // Adiciona o novo Tipo de Medicamento à lista
                listaTipoMedicamentos.add(tipoMedicamento);

                // Salva os tipos de medicamentos no arquivo
                salvarTiposMedicamentos();

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Tipo de Medicamento cadastrado com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar os tipos de medicamentos do arquivo TipoMedicamento.TAB
    public void carregarTiposMedicamentos() {
        File arquivoTipoMedicamento = new File("TipoMedicamento.TAB");

        if (arquivoTipoMedicamento.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTipoMedicamento))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Cada linha do arquivo representa um Tipo de Medicamento
                    String[] dados = linha.split("\t");
                    if (dados.length == 2) {
                        listaTipoMedicamentos.add(new TipoMedicamentoModelo(dados[0], dados[1]));  // ID e Descrição
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os tipos de medicamentos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar os tipos de medicamentos no arquivo TipoMedicamento.TAB
    public void salvarTiposMedicamentos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TipoMedicamento.TAB", false))) {
            for (TipoMedicamentoModelo tipoMedicamento : listaTipoMedicamentos) {
                writer.write(tipoMedicamento.getIdTipoMedicamento() + "\t" + tipoMedicamento.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os tipos de medicamentos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TipoMedicamentoVisao();
    }
}
