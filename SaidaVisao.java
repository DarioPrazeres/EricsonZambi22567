import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class SaidaVisao extends JFrame implements ActionListener {

    private static ArrayList<MedicamentoModelo> listaMedicamento = new ArrayList<>();
    private JTextField txtQuantidadeSaida, txtNomeFuncionario, txtNomeCliente, txtNif;
    private JSpinner txtDataSaida;
    private JComboBox<String> cmbMed;
    private JButton btnSalvar, btnEditar;
    private SaidaModelo saidaModeloParaEditar;

    public SaidaVisao() {
        super("Nova Saída");
        setLayout(new GridLayout(7, 2));

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
        txtQuantidadeSaida = new JTextField(20);
        add(txtQuantidadeSaida);

        add(new JLabel("Nome Funcionario:"));
        txtNomeFuncionario = new JTextField(10);
        add(txtNomeFuncionario);

        add(new JLabel("Nome Cliente:"));
        txtNomeCliente = new JTextField(10);
        add(txtNomeCliente);

        add(new JLabel("NIF:"));
        txtNif = new JTextField(10);
        add(txtNif);

        // Configuração do JSpinner para selecionar a data
        add(new JLabel("Data Saída:"));
        SpinnerDateModel model = new SpinnerDateModel();
        txtDataSaida = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(txtDataSaida, "dd/MM/yyyy");
        txtDataSaida.setEditor(editor);  // Definindo o formato de data
        add(txtDataSaida);

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

    public SaidaVisao(SaidaModelo saidaModelo) {
        super("Editar Saída");
        setLayout(new GridLayout(7, 2));

        // Carregar os medicamentos do arquivo
        carregarMedicamentos();

        add(new JLabel("Medicamento:"));
        cmbMed = new JComboBox<>();
        
        // Preencher o JComboBox com os medicamentos carregados
        for (MedicamentoModelo medicamento : listaMedicamento) {
            cmbMed.addItem(medicamento.getNome()); // Supondo que MedicamentoModelo tenha um método getNome()
        }
        // Selecionar o medicamento da saída para edição
        cmbMed.setSelectedItem(saidaModelo.getMedicamento()); // Seleciona o medicamento correspondente

        add(cmbMed);

        add(new JLabel("Quantidade:"));
        txtQuantidadeSaida = new JTextField(String.valueOf(saidaModelo.getQuantidadeSaida()), 20);
        add(txtQuantidadeSaida);

        add(new JLabel("Nome Funcionario:"));
        txtNomeFuncionario = new JTextField(saidaModelo.getNomeFuncionario(), 10);
        add(txtNomeFuncionario);

        add(new JLabel("Nome Cliente:"));
        txtNomeCliente = new JTextField(saidaModelo.getNomeCliente(), 10);
        add(txtNomeCliente);

        add(new JLabel("NIF:"));
        txtNif = new JTextField(saidaModelo.getNif(), 10);
        add(txtNif);

        // Configuração do JSpinner para selecionar a data
        add(new JLabel("Data Saída:"));
        SpinnerDateModel model = new SpinnerDateModel();
        txtDataSaida = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(txtDataSaida, "dd/MM/yyyy");
        txtDataSaida.setEditor(editor);  // Definindo o formato de data
        txtDataSaida.setValue(saidaModelo.getDataSaida()); // Definindo a data existente
        add(txtDataSaida);

        this.saidaModeloParaEditar = saidaModelo;

        // Botão de salvar
        btnEditar = new JButton("Salvar");
        add(btnEditar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Atualiza os dados da saída com os novos valores
                    saidaModeloParaEditar.setMedicamento((String) cmbMed.getSelectedItem());
                    saidaModeloParaEditar.setQuantidadeSaida(Integer.parseInt(txtQuantidadeSaida.getText())); 
                    saidaModeloParaEditar.setNomeFuncionario(txtNomeFuncionario.getText()); 
                    saidaModeloParaEditar.setNomeCliente(txtNomeCliente.getText()); 
                    saidaModeloParaEditar.setNif(txtNif.getText()); 

                    Date dataSaida = (Date) txtDataSaida.getValue(); 
                    saidaModeloParaEditar.setDataSaida(dataSaida);

                    Date dataUltimaAlteracao = new Date();
                    saidaModeloParaEditar.setDataAtualizacao(dataUltimaAlteracao);

                    atualizarSaida(saidaModeloParaEditar); 

                    JOptionPane.showMessageDialog(null, "Saída atualizada com sucesso!");
                    dispose(); // Fecha a janela após salvar
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar a saída. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
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
                int quantidade = Integer.parseInt(txtQuantidadeSaida.getText());
                String nomeFuncionario = txtNomeFuncionario.getText();
                String nomeCliente = txtNomeCliente.getText();
                String nif = txtNif.getText();
                
                // Pegar a data selecionada
                Date dataSaida = (Date) txtDataSaida.getValue(); // Pegando a data do JSpinner
                
                String nomeMedicamento = (String) cmbMed.getSelectedItem();

                // Cria um novo objeto SaidaModelo
                SaidaModelo saida = new SaidaModelo(
                    nomeMedicamento, 
                    1, // idUsuario
                    quantidade, 
                    nomeFuncionario, 
                    nomeCliente, 
                    nif, 
                    dataSaida, 
                    new Date(), // dataRegistro
                    new Date()  // dataAtualizacao
                );
                
                // Salvando o objeto SaidaModelo em um arquivo
                salvarSaida(saida);

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Saída cadastrada com sucesso!");
                dispose();

            } catch (NumberFormatException ex) {
                // Trata erros de conversão de dados numéricos
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos numéricos estão corretos.", 
                                              "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

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

    public void salvarSaida(SaidaModelo saida) {
        try {
            File file = new File("Saidas.DAT");    
            boolean arquivoExistente = file.exists();
    
            ArrayList<SaidaModelo> listaSaidasExistentes = new ArrayList<>();
            if (arquivoExistente) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            SaidaModelo s = (SaidaModelo) ois.readObject();
                            listaSaidasExistentes.add(s);
                        } catch (EOFException e) {
                            break; // Fim de arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    
            listaSaidasExistentes.add(saida);
    
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (SaidaModelo s : listaSaidasExistentes) {
                    oos.writeObject(s);
                }
            }
    
            JOptionPane.showMessageDialog(this, "Saída cadastrada com sucesso!");
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar a saída: " + e.getMessage());
        }
    }

    private void atualizarSaida(SaidaModelo saidaAlterada) {
        try {
            File file = new File("Saidas.DAT");
            ArrayList<SaidaModelo> listaSaidasExistentes = new ArrayList<>();
    
            // Carregar as saídas existentes
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            SaidaModelo saida = (SaidaModelo) ois.readObject();
                            if (saida.getId() == saidaAlterada.getId()) {
                                listaSaidasExistentes.add(saidaAlterada); // Substitui a saída editada
                            } else {
                                listaSaidasExistentes.add(saida); // Mantém as outras saídas
                            }
                        } catch (EOFException e) {
                            break; // Fim do arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Salva a lista atualizada de saídas
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (SaidaModelo saida : listaSaidasExistentes) {
                    oos.writeObject(saida);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a saída: " + e.getMessage());
        }
    }
}
