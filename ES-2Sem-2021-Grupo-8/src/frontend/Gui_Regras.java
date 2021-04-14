package frontend;

import java.awt.Color;
import java.awt.EventQueue;
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
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;

public class Gui_Regras extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField N_Classes;
	private JTextField N_Ciclos;
	private JTextField N_Metodos;
	private String N_Classes_Regras = "0";
	private String N_Ciclos_Regras = "0";
	private String N_Metodos_Regras = "0";
	private String Operator = "";
	private String Operator2 = "";
	private File file;
	
	private String excel_file_path;

	/**
	 * Create the application.
	 */
	public Gui_Regras(String excel_file_path) {
		this.excel_file_path = excel_file_path;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 864, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("Regras");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JCheckBox CheckBoxClasses = new JCheckBox("");
		JCheckBox CheckBoxCiclos = new JCheckBox("");
		JCheckBox CheckBoxMetodos = new JCheckBox("");
		JCheckBox CheckBoxOperadores = new JCheckBox("");
		JCheckBox CheckBoxOperadores_2 = new JCheckBox("");
		JComboBox comboBox = new JComboBox();
		JComboBox comboBox_2 = new JComboBox();

		JButton btnNewButton = new JButton("Guardar Regra");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				N_Classes_Regras = N_Classes.getText();
				N_Ciclos_Regras = N_Ciclos.getText();
				N_Metodos_Regras = N_Metodos.getText();
				if (!CheckBoxClasses.isSelected())
					N_Classes_Regras = "_";
				if (!CheckBoxCiclos.isSelected())
					N_Ciclos_Regras = "_";
				if (!CheckBoxMetodos.isSelected())
					N_Metodos_Regras = "_";
				if (!CheckBoxOperadores.isSelected())
					Operator = "_";
				if (!CheckBoxOperadores_2.isSelected())
					Operator2 = "_";
				Operator2 = comboBox_2.getSelectedItem().toString();
				Operator = comboBox.getSelectedItem().toString();
				System.out.println(Operator);
				System.out.println(Operator2);
				String Regra = N_Classes_Regras + ":" + Operator + ":" + N_Ciclos_Regras + ":" +Operator2 + ":" + N_Metodos_Regras;
				String filename = JOptionPane.showInputDialog("File Name:");
				try {
					File myObj = new File("Regras/" + filename + ".txt");
					if (myObj.createNewFile()) {
					} else {
						System.out.println("File already exists.");
					}
					FileWriter myWriter = new FileWriter("Regras/" + filename + ".txt");
					myWriter.write(Regra);
					myWriter.close();
				} catch (IOException e1) {
					System.out.println("An error occurred.");
					e1.printStackTrace();
				}
				System.out.println("Nova regra adicionada!");
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnNewButton_1 = new JButton("Importar Regra");
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

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
				try {
					Scanner myReader = new Scanner(file);
					while (myReader.hasNextLine()) {
						String data = myReader.nextLine();
						System.out.println(data);
						String[] parser = data.split(":");
						if (!(parser[0].equals("_"))) {
							CheckBoxClasses.setSelected(true);
							N_Classes_Regras = parser[0];
							N_Classes.setText(parser[0]);
						} else {
							N_Classes_Regras = "0";
							N_Classes.setText("0");
							
						}

						if (!(parser[2].equals("_"))) {
							CheckBoxCiclos.setSelected(true);
							N_Ciclos_Regras = parser[2];
							N_Ciclos.setText(parser[2]);
						} else {
							N_Ciclos_Regras = "0";
							N_Ciclos.setText("0");
							
						}

						if (!(parser[4].equals("_"))){
							CheckBoxMetodos.setSelected(true);
							N_Metodos_Regras = parser[4];
							N_Metodos.setText(parser[4]);
						}else{
							N_Metodos_Regras = "0";
							N_Metodos.setText("0");
							
						}
						if (!(parser[1].equals("_"))){
							CheckBoxOperadores.setSelected(true);
							comboBox.setSelectedItem(parser[1]);
							Operator = parser[1];
						}
						if (!(parser[3].equals("_"))){
							CheckBoxOperadores_2.setSelected(true);
							comboBox_2.setSelectedItem(parser[3]);
							Operator2 = parser[3];
						}
					}
					myReader.close();
				} catch (FileNotFoundException e1) {
					System.out.println("An error occurred.");
					e1.printStackTrace();
				}
				repaint();
			}
		});

		JButton btnNewButton_1_1 = new JButton("Go!");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui_Metricas gui_metrics = new Gui_Metricas(excel_file_path);
				gui_metrics.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBorder(new CompoundBorder());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new CompoundBorder());
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);
		panel_2.setBorder(new CompoundBorder());
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.text);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(Color.WHITE);
		
		
		CheckBoxOperadores_2.setBackground(Color.WHITE);
		
		
		

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(338)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE)
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(144)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(45)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_3_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_3_1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1)
					.addGap(68))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(84)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(60))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(84)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(198)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
		);
		
				
				CheckBoxOperadores.setBackground(Color.WHITE);
				
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"OR", "AND"}));
						Operator2 = comboBox_2.getSelectedItem().toString();
				GroupLayout gl_panel_3_1 = new GroupLayout(panel_3_1);
				gl_panel_3_1.setHorizontalGroup(
					gl_panel_3_1.createParallelGroup(Alignment.LEADING)
						.addGap(0, 124, Short.MAX_VALUE)
						.addGroup(gl_panel_3_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(CheckBoxOperadores_2)
							.addGap(18)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(19, Short.MAX_VALUE))
				);
				gl_panel_3_1.setVerticalGroup(
					gl_panel_3_1.createParallelGroup(Alignment.LEADING)
						.addGap(0, 62, Short.MAX_VALUE)
						.addGroup(gl_panel_3_1.createSequentialGroup()
							.addContainerGap(18, Short.MAX_VALUE)
							.addComponent(CheckBoxOperadores_2)
							.addGap(23))
						.addGroup(gl_panel_3_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(18, Short.MAX_VALUE))
				);
				panel_3_1.setLayout(gl_panel_3_1);
				
						
						comboBox.setModel(new DefaultComboBoxModel(new String[] { "OR", "AND" }));
						Operator = comboBox.getSelectedItem().toString();
				GroupLayout gl_panel_3 = new GroupLayout(panel_3);
				gl_panel_3.setHorizontalGroup(
					gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(CheckBoxOperadores)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(224, Short.MAX_VALUE))
				);
				gl_panel_3.setVerticalGroup(
					gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap(18, Short.MAX_VALUE)
							.addComponent(CheckBoxOperadores)
							.addGap(23))
						.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(18, Short.MAX_VALUE))
				);
				panel_3.setLayout(gl_panel_3);
		
				
				CheckBoxMetodos.setBackground(Color.WHITE);
				
						JLabel lblNmeroDeMtodos = new JLabel("N\u00FAmero de m\u00E9todos");
						lblNmeroDeMtodos.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
						N_Metodos = new JTextField();
						N_Metodos_Regras = N_Metodos.getText();
						N_Metodos.setFont(new Font("Tahoma", Font.PLAIN, 20));
						N_Metodos.setColumns(10);
				
				JComboBox comboBox_1_2 = new JComboBox();
				comboBox_1_2.setModel(new DefaultComboBoxModel(new String[] {">", "<"}));
				comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
				GroupLayout gl_panel_2 = new GroupLayout(panel_2);
				gl_panel_2.setHorizontalGroup(
					gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(CheckBoxMetodos)
							.addGap(18)
							.addComponent(lblNmeroDeMtodos, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(comboBox_1_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(N_Metodos, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
				gl_panel_2.setVerticalGroup(
					gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(20)
							.addComponent(CheckBoxMetodos)
							.addContainerGap(24, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap(13, Short.MAX_VALUE)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNmeroDeMtodos, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(N_Metodos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_1_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				panel_2.setLayout(gl_panel_2);
				
						JLabel lblNewLabel_1_1 = new JLabel("N\u00FAmero Ciclos");
						lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
						N_Ciclos = new JTextField();
						N_Ciclos_Regras = N_Ciclos.getText();
						N_Ciclos.setFont(new Font("Tahoma", Font.PLAIN, 20));
						N_Ciclos.setColumns(10);
				
						
						CheckBoxCiclos.setBackground(Color.WHITE);
				
				JComboBox comboBox_1_1 = new JComboBox();
				comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {">", "<"}));
				comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(CheckBoxCiclos)
							.addGap(18)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(N_Ciclos, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(21)
									.addComponent(CheckBoxCiclos))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
											.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
											.addComponent(N_Ciclos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
							.addContainerGap())
				);
				panel_1.setLayout(gl_panel_1);
		
				
				CheckBoxClasses.setBackground(Color.WHITE);
				
						JLabel lblNmeroDeClasses = new JLabel("N\u00FAmero de classes");
						lblNmeroDeClasses.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
						N_Classes = new JTextField();
						N_Classes_Regras = N_Classes.getText();
						N_Classes.setFont(new Font("Tahoma", Font.PLAIN, 20));
						N_Classes.setColumns(10);
				
				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] {">", "<"}));
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(CheckBoxClasses)
							.addGap(18)
							.addComponent(lblNmeroDeClasses)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(N_Classes, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addContainerGap()
									.addComponent(comboBox_1, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNmeroDeClasses, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(N_Classes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGap(21)
									.addComponent(CheckBoxClasses)))
							.addContainerGap())
				);
				panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}