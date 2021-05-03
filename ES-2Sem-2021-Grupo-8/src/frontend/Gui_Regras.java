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

	private static final long serialVersionUID = 1L;
	private JTextField Limite;
	private String N_Classes_Regras = "0";
	private String N_Ciclos_Regras = "0";
	private String N_Metodos_Regras = "0";
	private String Operator = "";
	private String Operator2 = "";
	private File file;

	private String excel_file_path;
	private JTextField TextInputVars;
	private String rules = "";

	public Gui_Regras(String excel_file_path) {
		this.excel_file_path = excel_file_path;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 864, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("Regras");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

		JButton btnNewButton = new JButton("Guardar Regra");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				N_Classes_Regras = Limite.getText();
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
				String Regra = N_Classes_Regras + ":" + Operator + ":" + N_Ciclos_Regras + ":" + Operator2 + ":"
						+ N_Metodos_Regras;
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
							Limite.setText(parser[0]);
						} else {
							N_Classes_Regras = "0";
							Limite.setText("0");

						}

						if (!(parser[2].equals("_"))) {
							CheckBoxCiclos.setSelected(true);
							N_Ciclos_Regras = parser[2];
							N_Ciclos.setText(parser[2]);
						} else {
							N_Ciclos_Regras = "0";
							N_Ciclos.setText("0");

						}

						if (!(parser[4].equals("_"))) {
							CheckBoxMetodos.setSelected(true);
							N_Metodos_Regras = parser[4];
							N_Metodos.setText(parser[4]);
						} else {
							N_Metodos_Regras = "0";
							N_Metodos.setText("0");

						}
						if (!(parser[1].equals("_"))) {
							CheckBoxOperadores.setSelected(true);
							comboBox.setSelectedItem(parser[1]);
							Operator = parser[1];
						}
						if (!(parser[3].equals("_"))) {
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new CompoundBorder());

		TextInputVars = new JTextField();
		TextInputVars.setEditable(false);
		TextInputVars.setColumns(10);

		JButton Button_Adicionar = new JButton("Adicionar");
		Button_Adicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Button_Adicionar.setBackground(SystemColor.textHighlight);

		JButton Button_Remover = new JButton("Limpar");

		

		Button_Remover.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Button_Remover.setBackground(SystemColor.textHighlight);

		Button_Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rules="";
				TextInputVars.setText(rules);
			}
		});
		
		JComboBox ComboBoxMetricas = new JComboBox();

		JComboBox ChecboxCodeSmell = new JComboBox();

		ChecboxCodeSmell.setModel(new DefaultComboBoxModel(new String[] { "IsLongMethod", "isGodClass" }));
		ComboBoxMetricas.setModel(new DefaultComboBoxModel(new String[] { "LOC_Class", "WMC_Class", "NOM_class" }));

		ChecboxCodeSmell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ChecboxCodeSmell.getSelectedItem().toString() == ("IsLongMethod")) {
					ComboBoxMetricas
							.setModel(new DefaultComboBoxModel(new String[] { "LOC_Class", "WMC_Class", "NOM_class" }));
				} else {
					ComboBoxMetricas.setModel(new DefaultComboBoxModel(new String[] { "LOC_Method", "CYCLO_Method" }));
				}
			}
		});

		JComboBox comboBoxBigSmall = new JComboBox();
		comboBoxBigSmall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxBigSmall.setModel(new DefaultComboBoxModel(new String[] { ">", "<" }));

		Limite = new JTextField();
		N_Classes_Regras = Limite.getText();
		Limite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Limite.setColumns(10);

		JCheckBox CheckBoxOperadores = new JCheckBox("");
		JComboBox comboBoxOperadores = new JComboBox();

		comboBoxOperadores.setModel(new DefaultComboBoxModel(new String[] { "OR", "AND" }));
		Operator = comboBoxOperadores.getSelectedItem().toString();

		Button_Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Operador = "";
				if (CheckBoxOperadores.isSelected()) {
					Operador = comboBoxOperadores.getSelectedItem().toString();
				} else {
					Operador = "";
				}
				if (rules == "") {

					rules = ComboBoxMetricas.getSelectedItem().toString()
							+ comboBoxBigSmall.getSelectedItem().toString() + Limite.getText().toString() + " "+ Operador
							+ " ";
				} else {
					rules = rules + ComboBoxMetricas.getSelectedItem().toString()
							+ comboBoxBigSmall.getSelectedItem().toString() + Limite.getText().toString() + Operador
							+ " ";
				}
				TextInputVars.setText(rules);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(72)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(TextInputVars, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(Button_Adicionar, GroupLayout.PREFERRED_SIZE, 192,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(Button_Remover,
										GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE).addComponent(
								btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(35))
				.addGroup(groupLayout.createSequentialGroup().addGap(336)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
						.addComponent(ChecboxCodeSmell, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addGap(34))
				.addGroup(groupLayout.createSequentialGroup().addGap(33)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(29)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(ChecboxCodeSmell, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGap(38)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(TextInputVars, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_1)
						.addComponent(Button_Remover, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(Button_Adicionar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
				.addGap(47)));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.text);

		CheckBoxOperadores.setBackground(Color.WHITE);

		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3
				.setHorizontalGroup(
						gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
										.addComponent(CheckBoxOperadores).addGap(18).addComponent(comboBoxOperadores,
												GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(19, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE)
						.addComponent(CheckBoxOperadores).addGap(23))
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
						.addComponent(comboBoxOperadores, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(39)
						.addComponent(ComboBoxMetricas, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addGap(95)
						.addComponent(comboBoxBigSmall, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addGap(105).addComponent(Limite, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE).addComponent(panel_3,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
														.addComponent(ComboBoxMetricas, GroupLayout.PREFERRED_SIZE, 33,
																GroupLayout.PREFERRED_SIZE)
														.addContainerGap())
												.addComponent(panel_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
												.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
														.addComponent(Limite, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(comboBoxBigSmall, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addContainerGap()))));
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
	}
}