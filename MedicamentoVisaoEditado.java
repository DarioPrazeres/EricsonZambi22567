import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MedicamentoVisaoEditado extends JFrame implements ActionListener {

    // Componentes do JFrame
    private JTextField txtLote, txtQuantidade, txtNome, txtFabricante,
                       txtCondicoesArmazenamento, txtDescricao, txtDosagem, txtPrecoCompra, txtPrecoVenda;
    private JComboBox<String> cmbTipo, cmbFormaFarmaceutica, cmbOrigem, cmbDioceses, cmbParoquia;
    private JButton btnSalvar;
    private JSpinner spinnerDataFundacao;

    String[] origemList = {"Indiano", "Portugues", "Americano"};
    String[] tipoList = {"Analgegesic", "Xarope", "Pomada"};
    String[] formaFarmaceuticaList = {"Comprimido", "Xarope", "Injetavel"};

    public MedicamentoVisaoEditado() {
        super("Novo Medicamento");

        // Definindo layout
        setLayout(new GridLayout(18, 2));

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

        // Novo ComboBox para Dioceses
        add(new JLabel("Dioceses:"));
        cmbDioceses = new JComboBox<>();
        add(cmbDioceses);

        // Novo ComboBox para Paróquia
        add(new JLabel("Paróquia:"));
        cmbParoquia = new JComboBox<>();
        add(cmbParoquia);

        // Adicionando campo de data com JSpinner
        add(new JLabel("Data de Fundação:"));
        spinnerDataFundacao = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerDataFundacao, "dd/MM/yyyy");
        spinnerDataFundacao.setEditor(dateEditor);
        add(spinnerDataFundacao);

        // Carregar opções dos ComboBoxes e a configuração inicial
        carregarFormaFarmaceutica();
        carregarOrigem();
        carregarTipoMedicamento();
        carregarDioceses();
        carregarParoquia();

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Configurações da janela
        setSize(400, 500);
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
                String tipo = (String) cmbTipo.getSelectedItem();
                String fabricante = txtFabricante.getText();
                String condicoesArmazenamento = txtCondicoesArmazenamento.getText();
                String descricao = txtDescricao.getText();
                String formaFarmaceutica = (String) cmbFormaFarmaceutica.getSelectedItem();
                String origem = (String) cmbOrigem.getSelectedItem();
                String dosagem = txtDosagem.getText();
                float precoCompra = Float.parseFloat(txtPrecoCompra.getText());
                float precoVenda = Float.parseFloat(txtPrecoVenda.getText());
                String dioceses = (String) cmbDioceses.getSelectedItem();
                String paroquia = (String) cmbParoquia.getSelectedItem();

                // Pegar a data de fundação do JSpinner
                Date dataFundacao = (Date) spinnerDataFundacao.getValue();

                // Criar um novo objeto MedicamentoModelo
                MedicamentoModeloEditado medicamento = new MedicamentoModeloEditado(
                    lote, quantidade, nome, tipo, fabricante,dioceses, paroquia, dataFundacao, condicoesArmazenamento,
                    descricao, formaFarmaceutica, origem, dosagem, precoCompra, precoVenda);

                // Salvar o medicamento no arquivo
                salvarMedicamento(medicamento);

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Medicamento cadastrado com sucesso!");
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Carregar os dados dos ComboBoxes de arquivos
    public void carregarDioceses() {
        File arquivoDioceses = new File("Dioceses.TAB");

        if (arquivoDioceses.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoDioceses))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbDioceses.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as dioceses.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void carregarParoquia() {
        File arquivoParoquia = new File("Paroquias.TAB");

        if (arquivoParoquia.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoParoquia))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbParoquia.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as paróquias.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void carregarTipoMedicamento() {
        File arquivoTipo = new File("TipoMedicamento.TAB");

        if (arquivoTipo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTipo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbTipo.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os tipos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void carregarFormaFarmaceutica() {
        File arquivoForma = new File("FormaFarmaceutica.TAB");

        if (arquivoForma.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoForma))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbFormaFarmaceutica.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as formas farmacêuticas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void carregarOrigem() {
        File arquivoOrigem = new File("Origem.TAB");

        if (arquivoOrigem.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoOrigem))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    cmbOrigem.addItem(linha.trim());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar as origens.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void salvarMedicamento(MedicamentoModeloEditado medicamento) {
        try {
            File file = new File("MedicamentosEditado.DAT");    
            boolean arquivoExistente = file.exists();
    
            ArrayList<MedicamentoModeloEditado> listaMedicamentosExistentes = new ArrayList<>();
            if (arquivoExistente) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            MedicamentoModeloEditado m = (MedicamentoModeloEditado) ois.readObject();
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
                for (MedicamentoModeloEditado m : listaMedicamentosExistentes) {
                    oos.writeObject(m);
                }
            }
    
            JOptionPane.showMessageDialog(this, "Medicamento cadastrado com sucesso!");
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o medicamento: " + e.getMessage());
        }
    }
   

    public static void main(String[] args) {
        new MedicamentoVisaoEditado();
    }
}
