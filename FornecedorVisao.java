import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class FornecedorVisao extends JFrame implements ActionListener {
    private static ArrayList<FornecedorModelo> listaFornecedores = new ArrayList<>();
    private JTextField txtNomeFornecedor, txtContacto, txtNif, txtEndereco, txtOrigem;
    private JButton btnSalvar, btnEditar;
    private FornecedorModelo fornecedorParaEditar;

    public FornecedorVisao() {
        super("Novo Fornecedor");
        setLayout(new GridLayout(6, 2));

        // Adicionando campos para o novo fornecedor
        add(new JLabel("Nome do Fornecedor:"));
        txtNomeFornecedor = new JTextField(20);
        add(txtNomeFornecedor);

        add(new JLabel("Contacto:"));
        txtContacto = new JTextField(20);
        add(txtContacto);

        add(new JLabel("NIF:"));
        txtNif = new JTextField(20);
        add(txtNif);

        add(new JLabel("Endereço:"));
        txtEndereco = new JTextField(20);
        add(txtEndereco);

        add(new JLabel("Origem:"));
        txtOrigem = new JTextField(20);
        add(txtOrigem);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        // Adicionando o ActionListener
        btnSalvar.addActionListener(this);

        // Configurações da janela
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public FornecedorVisao(FornecedorModelo fornecedorModelo) {
        super("Editar Fornecedor");
        setLayout(new GridLayout(6, 2));

        // Adicionando campos para editar o fornecedor
        add(new JLabel("Nome do Fornecedor:"));
        txtNomeFornecedor = new JTextField(fornecedorModelo.getNomeFornecedor(), 20);
        add(txtNomeFornecedor);

        add(new JLabel("Contacto:"));
        txtContacto = new JTextField(fornecedorModelo.getContacto(), 20);
        add(txtContacto);

        add(new JLabel("NIF:"));
        txtNif = new JTextField(fornecedorModelo.getNif(), 20);
        add(txtNif);

        add(new JLabel("Endereço:"));
        txtEndereco = new JTextField(fornecedorModelo.getEndereco(), 20);
        add(txtEndereco);

        add(new JLabel("Origem:"));
        txtOrigem = new JTextField(fornecedorModelo.getOrigem(), 20);
        add(txtOrigem);

        this.fornecedorParaEditar = fornecedorModelo;

        // Botão de editar
        btnEditar = new JButton("Salvar");
        add(btnEditar);

        // Espaço vazio para alinhar o botão
        add(new JLabel(""));

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Atualiza os dados do fornecedor com os novos valores
                    fornecedorParaEditar.setNomeFornecedor(txtNomeFornecedor.getText());
                    fornecedorParaEditar.setContacto(txtContacto.getText());
                    fornecedorParaEditar.setNif(txtNif.getText());
                    fornecedorParaEditar.setEndereco(txtEndereco.getText());
                    fornecedorParaEditar.setOrigem(txtOrigem.getText());

                    // Atualiza o fornecedor no arquivo
                    atualizarFornecedor(fornecedorParaEditar);

                    JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
                    dispose(); // Fecha a janela após salvar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar o fornecedor. Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configurações da janela
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            try {
                // Pega dados dos campos de texto
                String nomeFornecedor = txtNomeFornecedor.getText();
                String contacto = txtContacto.getText();
                String nif = txtNif.getText();
                String endereco = txtEndereco.getText();
                String origem = txtOrigem.getText();

                // Cria um novo objeto FornecedorModelo
                FornecedorModelo fornecedor = new FornecedorModelo(nomeFornecedor, contacto, nif, endereco, origem);

                // Salva o objeto FornecedorModelo no arquivo
                salvarFornecedor(fornecedor);

                // Exibe uma mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!");
                dispose();
            } catch (Exception ex) {
                // Trata erros de entrada
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos estão corretos.", 
                                              "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para salvar o fornecedor no arquivo
    public void salvarFornecedor(FornecedorModelo fornecedor) {
        try {
            File file = new File("Fornecedores.DAT");
            boolean arquivoExistente = file.exists();

            ArrayList<FornecedorModelo> listaFornecedoresExistentes = new ArrayList<>();
            if (arquivoExistente) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            FornecedorModelo f = (FornecedorModelo) ois.readObject();
                            listaFornecedoresExistentes.add(f);
                        } catch (EOFException e) {
                            break; // Fim de arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            listaFornecedoresExistentes.add(fornecedor);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (FornecedorModelo f : listaFornecedoresExistentes) {
                    oos.writeObject(f);
                }
            }

            JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o fornecedor: " + e.getMessage());
        }
    }

    // Método para atualizar o fornecedor no arquivo
    private void atualizarFornecedor(FornecedorModelo fornecedorAlterado) {
        try {
            File file = new File("Fornecedores.DAT");
            ArrayList<FornecedorModelo> listaFornecedoresExistentes = new ArrayList<>();

            // Carregar os fornecedores existentes
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            FornecedorModelo fornecedor = (FornecedorModelo) ois.readObject();
                            if (fornecedor.getId() == fornecedorAlterado.getId()) {
                                listaFornecedoresExistentes.add(fornecedorAlterado); // Substitui o fornecedor editado
                            } else {
                                listaFornecedoresExistentes.add(fornecedor); // Mantém os outros fornecedores
                            }
                        } catch (EOFException e) {
                            break; // Fim do arquivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Salva a lista atualizada de fornecedores
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (FornecedorModelo fornecedor : listaFornecedoresExistentes) {
                    oos.writeObject(fornecedor);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o fornecedor: " + e.getMessage());
        }
    }
}
