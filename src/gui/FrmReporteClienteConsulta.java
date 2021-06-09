package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Cliente;
import model.ClienteModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteClienteConsulta extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JButton btnFiltrar;
	private JPanel ReportePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteClienteConsulta frame = new FrmReporteClienteConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmReporteClienteConsulta() {
		setFont(new Font("Dialog", Font.BOLD, 15));
		setTitle("                CONSULTA POR NOMBRE:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(281, 92, 108, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(401, 93, 277, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(791, 85, 120, 39);
		contentPane.add(btnFiltrar);
		
		ReportePanel = new JPanel();
		ReportePanel.setBounds(12, 156, 913, 496);
		contentPane.add(ReportePanel);
		ReportePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblConsultaDeClientes = new JLabel("CONSULTA DE CLIENTES POR NOMBRE");
		lblConsultaDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeClientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConsultaDeClientes.setBounds(0, 13, 952, 33);
		contentPane.add(lblConsultaDeClientes);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			handle_btnFiltrar_actionPerformed(e);
		}
	}
	protected void handle_btnFiltrar_actionPerformed(ActionEvent e) {
		String nom = txtNombre.getText().trim();
		
		ClienteModel model = new ClienteModel();
		List<Cliente> lstdata = null;
		
		if(nom.equals("")) {
			lstdata = model.listaCliente();
		}
		else {
			lstdata = model.consultaCliente(nom);
		}
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstdata);
		String file = "reporteCliente.jasper";
		
		JasperPrint jasprint = GeneradorReporte.genera(file, dataSource, null);
		
		JRViewer jrviwer = new JRViewer(jasprint);
		
		ReportePanel.removeAll();
		ReportePanel.add(jrviwer);
		ReportePanel.repaint();
		ReportePanel.revalidate();
	}
}
