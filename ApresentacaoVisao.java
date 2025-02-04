import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ApresentacaoVisao extends JFrame{
	
	private PainelSul sul;
	private PainelNorte norte;

	public ApresentacaoVisao()
	{
		super("Sistema de Getão de Depositos de Medicamentos");	
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		ImageIcon img = new ImageIcon(getClass().getResource("/img/logoFarmacia.png")); 
        setIconImage(img.getImage());

		getContentPane().add(norte = new PainelNorte(), BorderLayout.NORTH);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
		setSize(500, 550);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	class PainelNorte extends JPanel
	{
		
		public PainelNorte()
		{
			setLayout(null);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			JLabel lb = new JLabel(new ImageIcon("img/logoFarmacia.png"));
			add( lb );
			JLabel lblNameAluno = new JLabel("Ericson Zambi");
			add(lblNameAluno);
	
			JLabel lblId = new JLabel("22567");
			add(lblId);
	
			JLabel lblObjectivo = new JLabel("<html>Um sistema para *gestão eficiente de estoque" + 
			"de medicamentos*, garantindo controle, segurança e rastreabilidade.</html>");
			lblObjectivo.setPreferredSize(new java.awt.Dimension(250, 100));
			add(lblObjectivo);
		}
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		private JButton entrarJBT, cancelarJBT;
		
		public PainelSul()
		{
			setLayout( new FlowLayout() );
			
			entrarJBT = new JButton("Entrar", new ImageIcon("C:\\Users\\HP\\Documents\\UCAN\\Aulas UCAN\\24-25\\FPG 2\\OsvaldoRamos2817\\image\\novo24.PNG"));
			cancelarJBT = new JButton("Cancelar");
			
			add( entrarJBT );
			add( cancelarJBT );
			
			entrarJBT.addActionListener( this );
			cancelarJBT.addActionListener( this );
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == entrarJBT)
			{
				dispose();
				new LoginVisao();
				//new MenuPrincipalVisao();
			}
			else
				dispose();	//fechar o formulario
		}
	}
	
	
}