
import java.awt.event.*;
import javax.swing.*;

public class MenuPrincipalVisao extends JFrame implements ActionListener {

    private JMenu menuFicheiro, menuOperacoes, menuListagens, menuUsuario, menuFornencedores, menuTabelas, menuAjuda, menuDefesa;
    private JMenuItem novoMedicamentoItem, editarMedicamentoItem, eliminarMedicamentoItem, sairItem, listagemMedicamentoItem;
    private JMenuItem entradaIntem, saidaItem, stockItem;
    private JMenuItem listarCadaversItem, listarEntradasItem, listarSaidasItem;
    private JMenuItem acessoItem, origemItem, formaFarmaceuticaItem, tipoMedicamentoItem, nacionalidadesItem, provinciasItem, municipiosItem, comunasItem, cadastroItem;
    private JMenuBar menuBar;
    private JMenuItem novoUsarioItem, editarUsarioItem, eliminarUsarioItem, listagemUsarioItem;
    private JMenuItem novoFornecedorItem, editarFornecedorItem, eliminarFornecedorItem, listagemFornecedorItem;
    private JMenuItem paroquiaItem, diocesesItem;

    public MenuPrincipalVisao() {
        super("Menu Principal");

        menuBar = new JMenuBar();

        ImageIcon img = new ImageIcon(getClass().getResource("/img/logoFarmacia.png"));
        setIconImage(img.getImage());

        setJMenuBar(menuBar);

        instanciarMenuItems();

        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void instanciarMenuItems() {
        menuBar.add(menuFicheiro = new JMenu("Ficheiro"));
        menuBar.add(menuOperacoes = new JMenu("Operações"));
        //menuBar.add(menuListagens = new JMenu("Listagens"));
        menuBar.add(menuUsuario = new JMenu("Usuário"));
        menuBar.add(menuFornencedores = new JMenu("Fornecedores"));
        menuBar.add(menuTabelas = new JMenu("Tabelas"));
        menuBar.add(menuAjuda = new JMenu("Ajuda"));
        menuBar.add(menuDefesa = new JMenu("Defesa"));

        menuFicheiro.setMnemonic('F');
        menuOperacoes.setMnemonic('O');
        //menuListagens.setMnemonic('L');
        menuTabelas.setMnemonic('T');
        menuAjuda.setMnemonic('A');

        //menuFicheiro
        menuFicheiro.add(novoMedicamentoItem = new JMenuItem("Novo Medicamento"));
        menuFicheiro.add(listagemMedicamentoItem = new JMenuItem("Listagens Medicamento"));
        menuFicheiro.addSeparator();
        menuFicheiro.add(sairItem = new JMenuItem("Sair da aplicacao"));

        //adicionar Operacoes
        menuOperacoes.add(entradaIntem = new JMenuItem("Entrada de Medicamentos"));
        menuOperacoes.add(saidaItem = new JMenuItem("Saída de Medicamentos"));
        menuOperacoes.add(stockItem = new JMenuItem("Stock"));

        //menuUsuario
        menuUsuario.add(novoUsarioItem = new JMenuItem("Novo Usuário"));
        menuUsuario.add(listagemUsarioItem = new JMenuItem("Listagem de Usuário"));

        //menuFornecedor
        menuFornencedores.add(novoFornecedorItem = new JMenuItem("Novo Fornecedores"));
        menuFornencedores.add(listagemFornecedorItem = new JMenuItem("Listagem de Fornecedores"));

        //adicionar tabelas
        menuTabelas.add(acessoItem = new JMenuItem("Acesso"));
        menuTabelas.add(origemItem = new JMenuItem("Origem"));
        menuTabelas.add(formaFarmaceuticaItem = new JMenuItem("Forma Farmacêutica"));
        menuTabelas.add(tipoMedicamentoItem = new JMenuItem("Tipo Medicamento"));

        menuTabelas.add(paroquiaItem = new JMenuItem("Paroquia"));
        menuTabelas.add(diocesesItem = new JMenuItem("Dioceses"));
        //menuTabelas.add(municipiosItem = new JMenuItem("Municipios"));

        menuDefesa.add(cadastroItem = new JMenuItem("Cadastro"));

        //adicionar eventos
        acessoItem.addActionListener(this);
        novoMedicamentoItem.addActionListener(this);
        sairItem.addActionListener(this);
        cadastroItem.addActionListener(this);
        //comunasItem.addActionListener(this);
        //nacionalidadesItem.addActionListener(this);
        saidaItem.addActionListener(this);
        entradaIntem.addActionListener(this);
        formaFarmaceuticaItem.addActionListener(this);
        origemItem.addActionListener(this);
        //municipiosItem.addActionListener(this);
        listagemMedicamentoItem.addActionListener(this);
        listagemUsarioItem.addActionListener(this);
        listagemFornecedorItem.addActionListener(this);
        novoFornecedorItem.addActionListener(this);
        //provinciasItem.addActionListener(this);
        stockItem.addActionListener(this);
        novoUsarioItem.addActionListener(this);
        tipoMedicamentoItem.addActionListener(this);
        paroquiaItem.addActionListener(this);
        diocesesItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        
        if (evt.getSource() == novoMedicamentoItem) {
            new MedicamentoVisao();
        } 
        else if (evt.getSource() == listagemMedicamentoItem) {
            new InterfaceMedicamento();
        }
        else if(evt.getSource() == saidaItem){
            new SaidaVisao();
        }
        else if(evt.getSource() == entradaIntem){
            new EntradaVisao();
        }
        else if(evt.getSource() == stockItem){
            new StockVisao();
        }
        else if(evt.getSource() == novoUsarioItem){
            new UsuarioVisao();
        }    
        else if(evt.getSource() == listagemUsarioItem){
            new ListagemUsuarioVisao();
        }    
        else if(evt.getSource() == novoFornecedorItem){
            new FornecedorVisao();
        }
        else if (evt.getSource() == listagemFornecedorItem) {
            new ListagemFornecedores();
        }        
        else if (evt.getSource() == acessoItem) {
            new AcessoVisao();
        }        
        else if (evt.getSource() == origemItem) {
            new OrigemVisao();
        }        
        else if (evt.getSource() == formaFarmaceuticaItem) {
            new FormaFarmaceuticaVisao();
        }
        else if (evt.getSource() == tipoMedicamentoItem) {
            new TipoMedicamentoVisao();
        }
        else if (evt.getSource() == nacionalidadesItem) {
            new NacionalidadeVisao();
        }
        else if (evt.getSource() == comunasItem) {
            new ComunaVisao();
        }
        else if (evt.getSource() == provinciasItem) {
            new ProvinciaVisao();
        }
        else if(evt.getSource() == municipiosItem){
            new MunicipioVisao();
        }
        else if(evt.getSource() == cadastroItem){
            new MedicamentoVisaoEditado();
        }
        else if(evt.getSource() == diocesesItem){
            new DioceseVisao();
        }
        else if(evt.getSource() == paroquiaItem){
            new ParoquiaVisao();
        }
    }

    public static void main(String[] args) {
        new MenuPrincipalVisao();
    }
}
