import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class MedicamentoVisao extends JFrame implements ActionListener {

    // Componentes do JFrame
    private JTextField txtLote, txtQuantidade, txtNome, txtFabricante,
                       txtCondicoesArmazenamento, txtDescricao, txtDosagem, txtPrecoCompra, txtPrecoVenda, txtOrigem;
    private JComboBox<String> cmbTipo, cmbFormaFarmaceutica, cmbOrigem;
    private JButton btnSalvar, btnEditar;

    String[] origemList = {"Indiano", "Portugues", "Americano"};
    String[] tipoList = {"Analgegesic", "Xarope", "Pomada"};
    String[] formaFarmaceuticaList = {"Comprimido", "Xarope", "Injetavel"};

    public MedicamentoVisao() {
        super("Novo Medicamento");

        // Definindo layout
        setLayout(new GridLayout(13, 2));

        // Criando e adicionando os componentes
        add(new JLabel("Nome:"));
        txtNome = new JTextField(20);
        add(txtNome);

        add(new JLabel("Lote:"));
        txtLote = new JTextField(10);
        add(txtLote);

        add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField(10);
        add(txtQuantidade);

        add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>();
        add(cmbTipo);

        add(new JLabel("Origem:"));
        cmbOrigem = new JComboBox<>();
        add(cmbOrigem);

        add(new JLabel("Fabricante:"));
        txtFabricante = new JTextField(20);
        add(txtFabricante);

        add(new JLabel("Condições de Armazenamento:"));
        txtCondicoesArmazenamento = new JTextField(20);
        add(txtCondicoesArmazenamento);

        add(new JLabel("Descrição:"));
        txtDescricao = new JTextField(20);
        add(txtDescricao);

        add(new JLabel("Forma Farmacêutica:"));
        cmbFormaFarmaceutica = new JComboBox<>();
        add(cmbFormaFarmaceutica);

        add(new JLabel("Dosagem:"));
        txtDosagem = new JTextField(20);
        add(txtDosagem);

        add(new JLabel("Preço de Compra:"));
        txtPrecoCompra = new JTextField(10);
        add(txtPrecoCompra);

        add(new JLabel("Preço de Venda:"));
        txtPrecoVenda = new JTextField(10);
        add(txtPrecoVenda);

        carregarFormaFarmaceutica();
        carregarOrigem();
        carregarTipoMedimento();
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

    public MedicamentoVisao(MedicamentoModelo medicamento) {
        super("Editar Medicamento");
    
        // Definindo layout
        setLayout(new GridLayout(13, 2));
    
        // Preenche os campos com os dados do medicamento
        add(new JLabel("Nome:"));
        txtNome = new JTextField(medicamento.getNome(), 20);
        add(txtNome);
    
        add(new JLabel("Lote:"));
        txtLote = new JTextField(medicamento.getLote(), 10);
        add(txtLote);
    
        add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField(String.valueOf(medicamento.getQuantidade()), 10);
        add(txtQuantidade);
    
        add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>();
        //cmbTipo.setSelectedItem(medicamento.getTipo());
        add(cmbTipo);
    
        add(new JLabel("Origem:"));
        cmbOrigem = new JComboBox<>(origemList);
        cmbOrigem.setSelectedItem(medicamento.getOrigem());
        add(cmbOrigem);
    
        add(new JLabel("Fabricante:"));
        txtFabricante = new JTextField(medicamento.getFabricante(), 20);
        add(txtFabricante);
    
        add(new JLabel("Condições de Armazenamento:"));
        txtCondicoesArmazenamento = new JTextField(medicamento.getCondicoesArmazenamento(), 20);
        add(txtCondicoesArmazenamento);
    
        add(new JLabel("Descrição:"));
        txtDescricao = new JTextField(medicamento.getDescricao(), 20);
        add(txtDescricao);
    
        add(new JLabel("Forma Farmacêutica:"));
        cmbFormaFarmaceutica = new JComboBox<>(formaFarmaceuticaList);
        cmbFormaFarmaceutica.setSelectedItem(medicamento.getFormaFarmaceutica());
        add(cmbFormaFarmaceutica);
    
        add(new JLabel("Dosagem:"));
        txtDosagem = new JTextField(medicamento.getDosagem(), 20);
        add(txtDosagem);
    
        add(new JLabel("Preço de Compra:"));
        txtPrecoCompra = new JTextField(String.valueOf(medicamento.getPrecoCompra()), 10);
        add(txtPrecoCompra);
    
        add(new JLabel("Preço de Venda:"));
        txtPrecoVenda = new JTextField(String.valueOf(medicamento.getPrecoVenda()), 10);
        add(txtPrecoVenda);
    
        carregarTipoMedimento();

        // Botão de editar
        btnEditar = new JButton("Salvar Alterações");
        add(btnEditar);
        
        // Espaço vazio para alinhar o botão
        add(new JLabel(""));
    
        // Adicionando o ActionListener para editar os dados
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualiza os dados do medicamento com os novos valores
                medicamento.setNome(txtNome.getText());
                medicamento.setLote(txtLote.getText());
                medicamento.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                medicamento.setTipo((String) cmbTipo.getSelectedItem());
                medicamento.setOrigem((String) cmbOrigem.getSelectedItem());
                medicamento.setFabricante(txtFabricante.getText());
                medicamento.setCondicoesArmazenamento(txtCondicoesArmazenamento.getText());
                medicamento.setDescricao(txtDescricao.getText());
                medicamento.setFormaFarmaceutica((String) cmbFormaFarmaceutica.getSelectedItem());
                medicamento.setDosagem(txtDosagem.getText());
                medicamento.setPrecoCompra(Float.parseFloat(txtPrecoCompra.getText()));
                medicamento.setPrecoVenda(Float.parseFloat(txtPrecoVenda.getText()));
    
                // Agora, atualize o arquivo com as alterações
                atualizarMedicamento(medicamento);
                JOptionPane.showMessageDialog(null, "Medicamento atualizado com sucesso!");
                dispose();
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
                String lote = txtLote.getText();
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                String nome = txtNome.getText();
                String tipo = (String) cmbTipo.getSelectedItem(); // Aqui, obtém o valor selecionado do ComboBox
                String fabricante = txtFabricante.getText();
                String condicoesArmazenamento = txtCondicoesArmazenamento.getText();
                String descricao = txtDescricao.getText();
                String formaFarmaceutica = (String) cmbFormaFarmaceutica.getSelectedItem(); // Aqui, obtém o valor selecionado do ComboBox
                String origem = (String) cmbOrigem.getSelectedItem(); // Aqui, obtém o valor selecionado do ComboBox
                String dosagem = txtDosagem.getText();
                float precoCompra = Float.parseFloat(txtPrecoCompra.getText());
                float precoVenda = Float.parseFloat(txtPrecoVenda.getText());

                // Cria um novo objeto Medicamento
                MedicamentoModelo medicamento = new MedicamentoModelo(
                    lote, 
                    quantidade, 
                    nome, 
                    tipo, 
                    fabricante,
                    condicoesArmazenamento, 
                    descricao, 
                    formaFarmaceutica, 
                    origem,
                    dosagem, 
                    precoCompra, 
                    precoVenda);
                
                // Salvando o objeto Medicamento em um arquivo
                salvarMedicamento(medicamento);

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Medicamento cadastrado com sucesso!");
                dispose();

            } catch (NumberFormatException ex) {
                // Trata erros de conversão de dados numéricos
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos numéricos estão corretos.", 
                                              "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } 
        }
        else if(e.getSource() == btnEditar){

        }
    }

    public void salvarMedicamento(MedicamentoModelo medicamento) {
        try {
            File file = new File("Medicamentos.DAT");    
            boolean arquivoExistente = file.exists();
    
            ArrayList<MedicamentoModelo> listaMedicamentosExistentes = new ArrayList<>();
            if (arquivoExistente) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            MedicamentoModelo m = (MedicamentoModelo) ois.readObject();
                            listaMedicamentosExistentes.add(m);
                        } catch (EOFException e) {
                            break; // Fim de arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    
            listaMedicamentosExistentes.add(medicamento);
    
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (MedicamentoModelo m : listaMedicamentosExistentes) {
                    oos.writeObject(m);
                }
            }
    
            JOptionPane.showMessageDialog(this, "Medicamento cadastrado com sucesso!");
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o medicamento: " + e.getMessage());
        }
    }
    
    public void atualizarMedicamento(MedicamentoModelo medicamentoAlterado) {
        try {
            File file = new File("Medicamentos.DAT");    
            ArrayList<MedicamentoModelo> listaMedicamentosExistentes = new ArrayList<>();
    
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        MedicamentoModelo m = (MedicamentoModelo) ois.readObject();
                        if (m.getId() == (medicamentoAlterado.getId())) {
                            listaMedicamentosExistentes.add(medicamentoAlterado);
                        } else {
                            listaMedicamentosExistentes.add(m);
                        }
                    } catch (EOFException e) {
                        break; // Fim de arquivo
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    
            // Salva a lista atualizada de medicamentos
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (MedicamentoModelo m : listaMedicamentosExistentes) {
                    oos.writeObject(m);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o medicamento: " + e.getMessage());
        }
    }

    public void carregarTipoMedimento() {
        File arquivoAcesso = new File("TipoMedicamento.TAB");
    
        if (arquivoAcesso.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAcesso))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbTipo.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os IDs de Acesso.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void carregarFormaFarmaceutica() {
        File arquivoAcesso = new File("FormaFarmaceutica.TAB");
    
        if (arquivoAcesso.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAcesso))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbFormaFarmaceutica.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os IDs de Acesso.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void carregarOrigem() {
        File arquivoAcesso = new File("Origem.TAB");
    
        if (arquivoAcesso.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAcesso))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbOrigem.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os IDs de Acesso.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        new MedicamentoVisao();
    }
}