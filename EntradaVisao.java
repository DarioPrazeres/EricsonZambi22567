import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class EntradaVisao extends JFrame implements ActionListener {

    private static ArrayList<MedicamentoModelo> listaMedicamento = new ArrayList<>();
    private JTextField txtQuantidadeEntrada, txtNomeFuncionario, txtNomeFornencedor;
    private JSpinner txtDataEntrada;
    private JComboBox<String> cmbMed;
    private JButton btnSalvar, btnEditar;
    private EntradaModelo entradaModeloParaEditar;

    public EntradaVisao() {
        super("Nova Entrada");
        setLayout(new GridLayout(6, 2));

        // Carregar os medicamentos do arquivo
        carregarMedicamentos();

        add(new JLabel("Medicamento:"));
        cmbMed = new JComboBox<>();
        
        // Preencher o JComboBox com os medicamentos carregados
        for (MedicamentoModelo medicamento : listaMedicamento) {
            cmbMed.addItem(medicamento.getNome()); // Supondo que MedicamentoModelo tenha um método getNome()
        }
        add(cmbMed);

        add(new JLabel("Quantidade:"));
        txtQuantidadeEntrada = new JTextField(20);
        add(txtQuantidadeEntrada);

        add(new JLabel("Nome Funcionario:"));
        txtNomeFuncionario = new JTextField(10);
        add(txtNomeFuncionario);

        add(new JLabel("Nome Fornecedor:"));
        txtNomeFornencedor = new JTextField(10);
        add(txtNomeFornencedor);

        // Configuração do JSpinner para selecionar a data
        add(new JLabel("Data Entrada:"));
        SpinnerDateModel model = new SpinnerDateModel();
        txtDataEntrada = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(txtDataEntrada, "dd/MM/yyyy");
        txtDataEntrada.setEditor(editor);  // Definindo o formato de data
        add(txtDataEntrada);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Configurações da janela
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public EntradaVisao(EntradaModelo entradaModelo) {
        super("Editar Entrada");
        setLayout(new GridLayout(6, 2));

        // Carregar os medicamentos do arquivo
        carregarMedicamentos();

        add(new JLabel("Medicamento:"));
        cmbMed = new JComboBox<>();
        
        // Preencher o JComboBox com os medicamentos carregados
        for (MedicamentoModelo medicamento : listaMedicamento) {
            cmbMed.addItem(medicamento.getNome()); // Supondo que MedicamentoModelo tenha um método getNome()
        }
        // Selecionar o medicamento da entrada para edição
        cmbMed.setSelectedItem(entradaModelo.getNomeMedicamento()); // Seleciona o medicamento correspondente

        add(cmbMed);

        add(new JLabel("Quantidade:"));
        txtQuantidadeEntrada = new JTextField(String.valueOf(entradaModelo.getQuantidadeEntrada()), 20);
        add(txtQuantidadeEntrada);

        add(new JLabel("Nome Funcionario:"));
        txtNomeFuncionario = new JTextField(entradaModelo.getNomeFuncionario(), 10);
        add(txtNomeFuncionario);

        add(new JLabel("Nome Fornecedor:"));
        txtNomeFornencedor = new JTextField(entradaModelo.getNomeFornecedor(), 10);
        add(txtNomeFornencedor);

        // Configuração do JSpinner para selecionar a data
        add(new JLabel("Data Entrada:"));
        SpinnerDateModel model = new SpinnerDateModel();
        txtDataEntrada = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(txtDataEntrada, "dd/MM/yyyy");
        txtDataEntrada.setEditor(editor);  // Definindo o formato de data
        txtDataEntrada.setValue(entradaModelo.getDataEntrada()); // Definindo a data existente
        add(txtDataEntrada);

        this.entradaModeloParaEditar = entradaModelo;
        // Botão de salvar
        btnEditar = new JButton("Salvar");
        add(btnEditar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Atualiza os dados da entrada com os novos valores
                    entradaModeloParaEditar.setNomeMedicamento((String) cmbMed.getSelectedItem());
                    entradaModeloParaEditar.setQuantidadeEntrada(Integer.parseInt(txtQuantidadeEntrada.getText())); 
                    entradaModeloParaEditar.setNomeFuncionario(txtNomeFuncionario.getText()); 
                    entradaModeloParaEditar.setNomeFornecedor(txtNomeFornencedor.getText()); 
                    
                    Date dataEntrada = (Date) txtDataEntrada.getValue(); 
                    entradaModeloParaEditar.setDataEntrada(dataEntrada);
        
                    Date dataUltimaAlteracao = new Date();
                    entradaModeloParaEditar.setDataAtualizacao(dataUltimaAlteracao);
                    atualizarEntrada(entradaModeloParaEditar); 
        
                    JOptionPane.showMessageDialog(null, "Entrada atualizada com sucesso!");
                    dispose(); // Fecha a janela após salvar
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar a entrada. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        // Configurações da janela
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            try {
                // Pegar dados dos campos de texto
                int quantidade = Integer.parseInt(txtQuantidadeEntrada.getText());
                String nomeFuncionario = txtNomeFuncionario.getText();
                String nomeFornecedor = txtNomeFornencedor.getText();
                
                // Pegar a data selecionada
                Date dataEntrada = (Date) txtDataEntrada.getValue(); // Pegando a data do JSpinner
                
                String nomeMedicamento = (String) cmbMed.getSelectedItem();

                // Cria um novo objeto EntradaModelo
                EntradaModelo entrada = new EntradaModelo(
                    nomeMedicamento, 
                    1, 
                    quantidade, 
                    nomeFuncionario, 
                    nomeFornecedor, 
                    dataEntrada, 
                    new Date(), 
                    new Date()
                );
                
                // Salvando o objeto EntradaModelo em um arquivo
                salvarEntrada(entrada);

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Entrada cadastrada com sucesso!");
                dispose();

            } catch (NumberFormatException ex) {
                // Trata erros de conversão de dados numéricos
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos numéricos estão corretos.", 
                                              "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar os medicamentos do arquivo
    private void carregarMedicamentos() {
        try {
            File file = new File("Medicamentos.DAT");
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            MedicamentoModelo medicamento = (MedicamentoModelo) ois.readObject();
                            listaMedicamento.add(medicamento);  // Adicionando medicamento à lista
                        } catch (EOFException e) {
                            break;  // Fim do arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar medicamentos: " + e.getMessage());
        }
    }

    public void salvarEntrada(EntradaModelo entrada) {
        try {
            File file = new File("Entradas.DAT");    
            boolean arquivoExistente = file.exists();
    
            ArrayList<EntradaModelo> listaEntradasExistentes = new ArrayList<>();
            if (arquivoExistente) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            EntradaModelo m = (EntradaModelo) ois.readObject();
                            listaEntradasExistentes.add(m);
                        } catch (EOFException e) {
                            break; // Fim de arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    
            listaEntradasExistentes.add(entrada);
    
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (EntradaModelo m : listaEntradasExistentes) {
                    oos.writeObject(m);
                }
            }
    
            JOptionPane.showMessageDialog(this, "Entrada cadastrada com sucesso!");
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar a entrada: " + e.getMessage());
        }
    }

    private void atualizarEntrada(EntradaModelo entradaAlterada) {
        try {
            File file = new File("Entrada.DAT");
            ArrayList<EntradaModelo> listaEntradasExistentes = new ArrayList<>();
    
            // Carregar as entradas existentes
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            EntradaModelo entrada = (EntradaModelo) ois.readObject();
                            if (entrada.getId() == entradaAlterada.getId()) {
                                listaEntradasExistentes.add(entradaAlterada); // Substitui a entrada editada
                            } else {
                                listaEntradasExistentes.add(entrada); // Mantém as outras entradas
                            }
                        } catch (EOFException e) {
                            break; // Fim do arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Salva a lista atualizada de entradas
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (EntradaModelo entrada : listaEntradasExistentes) {
                    oos.writeObject(entrada);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a entrada: " + e.getMessage());
        }
    }
}
