package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.JList;

public class Gui_Regras extends JFrame {

	private static final long serialVersionUID = 1L;
	private String N_Classes_Regras = "0";
	private String N_Ciclos_Regras = "0";
	private String N_Metodos_Regras = "0";
	private String Operator = "";
	private String Operator2 = "";
	private File file;
	private JTextField regra_nome;
	private JLabel label;

	private String excel_file_path;
	private JTextArea TextInputVars;
	private String rules = "";
	private ArrayList<String> ArrayMethods = new ArrayList<String>();
	public Gui_CriarRegra cria_Regra= new Gui_CriarRegra();
	
	ArrayList<String> arr = new ArrayList<String>();
	JList list = new JList();

	public Gui_Regras(String excel_file_path) {
		this.excel_file_path = excel_file_path;
		initialize();
	}

	public void Carrega_Regra(String filename) {
		File folder= new File("/Users/joaosantos/git/ES-2Sem-2021-Grupo-8/ES-2Sem-2021-Grupo-8/Regras");
		File[] files= folder.listFiles();
		for(File f : files) {
			if(filename.equals(f.getName())) {
				TextInputVars.setText(filename);
			}
		}
		
		
	}
	
	private void initialize() {
		setBounds(100, 100, 864, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Criar Regra");
		btnNewButton.setBounds(704, 323, 154, 29);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cria_Regra.setVisible(true);
			}
			
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JButton btnNewButton_1 = new JButton("Importar Regra");
		btnNewButton_1.setBounds(704, 358, 154, 29);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBackground(new Color(0, 120, 215));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					System.out.print("Opening: " + file.getName() + ".");
				}
				
				
//				Carrega_Regra(file.getName());
				
//					String rule=
					TextInputVars.append("Regra: "+ file.getName().split(".txt")[0]);
					TextInputVars.append("\n");
//					
					 
				
				repaint();
			}
		});
		
		File dir = new File("Regras");
		ArrayMethods.add("isLongMethod");
		ArrayMethods.add("isGodClass");
		 File[] files = dir.listFiles();
		    for (File file : files) {
		    	String a = file.getName();
		    	String splited = a.split("\\.")[0];
		    	System.out.println(splited);
		    	ArrayMethods.add(splited);
		    }
		    
		String[] String_Methods = new String[ArrayMethods.size()];
		for (int i = 0; i < ArrayMethods.size(); i++) {
			String_Methods[i]=ArrayMethods.get(i);
		}
		

		JButton btnNewButton_1_1 = new JButton("Go!");
		btnNewButton_1_1.setBounds(72, 383, 153, 29);
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui_Metricas gui_metrics = new Gui_Metricas(excel_file_path);
				gui_metrics.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);

		TextInputVars = new JTextArea();
		TextInputVars.setBounds(62, 80, 390, 256);
		TextInputVars.setEditable(false);
		TextInputVars.setColumns(10);
		
		JButton btnVisualizadorRegras = new JButton("Historico de Regras ");
		btnVisualizadorRegras.setBounds(704, 288, 154, 29);
		btnVisualizadorRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVisualizadorRegras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVisualizadorRegras.setBackground(SystemColor.textHighlight);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Regras");
		lblNewLabel.setBounds(89, 27, 120, 36);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel);
		getContentPane().add(TextInputVars);
		getContentPane().add(btnVisualizadorRegras);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnNewButton_1);
		getContentPane().add(btnNewButton_1_1);
		
		list = new JList();
		list.setBounds(354, 309, -275, -197);
		getContentPane().add(list);
	}
}