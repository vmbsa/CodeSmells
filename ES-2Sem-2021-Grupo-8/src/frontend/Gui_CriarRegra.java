package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;

public class Gui_CriarRegra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField Limite;

	private String rules = "";
	private String filename = "";
	private ArrayList<String> ArrayMethods = new ArrayList<String>();
	private JTextField textField;
	private JTextField textField_1;

	public Gui_CriarRegra() {
		initialize();
	}

	public void initialize() {
		setBounds(100, 100, 864, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("Adicionar Regra");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

		File dir = new File("Regras");
		ArrayMethods.add("isLongMethod");
		ArrayMethods.add("isGodClass");
		File[] files = dir.listFiles();
		for (File file : files) {
			String a = file.getName();
			String splited = a.split("\\.")[0];
			// System.out.println(splited);
			ArrayMethods.add(splited);
		}

		String[] String_Methods = new String[ArrayMethods.size()];
		for (int i = 0; i < ArrayMethods.size(); i++) {
			String_Methods[i] = ArrayMethods.get(i);
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new CompoundBorder());

		JButton Button_Adicionar = new JButton("Adicionar");
		Button_Adicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Button_Adicionar.setBackground(SystemColor.textHighlight);
		Button_Adicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				filename = textField.getText();

				try {
					File myObj = new File("Regras/" + filename + ".txt");
					if (myObj.createNewFile()) {
					} else {
						System.out.println("File already exists.");
					}
					FileWriter myWriter = new FileWriter("Regras/" + filename + ".txt");
					myWriter.write(rules);
					myWriter.close();

				} catch (IOException e1) {
					System.out.println("An error occurred.");
					e1.printStackTrace();
				}
				System.out.println("Nova regra adicionada!");
			}

		});

		JButton Button_Remover = new JButton("Limpar");

		Button_Remover.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Button_Remover.setBackground(SystemColor.textHighlight);

		Button_Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rules = "";

			}
		});

		JComboBox ComboBoxMetricas = new JComboBox();

		ComboBoxMetricas.setModel(new DefaultComboBoxModel(new String[] { "LOC_Class", "WMC_Class", "NOM_class" }));

		JComboBox comboBoxBigSmall = new JComboBox();
		comboBoxBigSmall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxBigSmall.setModel(new DefaultComboBoxModel(new String[] { ">", "<" }));

		Limite = new JTextField();

		Limite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Limite.setColumns(10);

		JCheckBox CheckBoxOperadores = new JCheckBox("");
		JComboBox comboBoxOperadores = new JComboBox();

		comboBoxOperadores.setModel(new DefaultComboBoxModel(new String[] { "OR", "AND" }));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		Button_Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Operador = "";
				if (CheckBoxOperadores.isSelected()) {
					Operador = comboBoxOperadores.getSelectedItem().toString();
				} else {
					Operador = "";
				}
				if (rules == "") {

					rules = ComboBoxMetricas.getSelectedItem().toString() + " "
							+ comboBoxBigSmall.getSelectedItem().toString() + " " + Limite.getText().toString() + " "
							+ Operador + " ";
				} else {
					rules = rules + ComboBoxMetricas.getSelectedItem().toString()
							+ comboBoxBigSmall.getSelectedItem().toString() + Limite.getText().toString() + " "
							+ Operador + " ";
				}

				textField_1.setText(rules);

			}

		});

		JLabel lblNewLabel_1 = new JLabel("Filename:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RuleIntoJP();
				setVisible(false);

			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(228)
						.addComponent(Button_Adicionar, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addGap(50)
						.addComponent(Button_Remover, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(341, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addGap(284))
				.addGroup(groupLayout.createSequentialGroup().addGap(132).addComponent(lblNewLabel_1).addGap(18)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(289, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(85)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 698,
										Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 698,
										Short.MAX_VALUE))
						.addGap(81))
				.addGroup(groupLayout.createSequentialGroup().addGap(14).addComponent(btnNewButton).addContainerGap(779,
						Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(21)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(44)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(31)
				.addComponent(
						textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Button_Adicionar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(Button_Remover, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnNewButton).addGap(13)));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(SystemColor.text);

		CheckBoxOperadores.setBackground(Color.WHITE);

		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(CheckBoxOperadores, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBoxOperadores, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addComponent(CheckBoxOperadores, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxOperadores, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE))
						.addGap(13)));
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(29)
				.addComponent(ComboBoxMetricas, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE).addGap(90)
				.addComponent(comboBoxBigSmall, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE).addGap(84)
				.addComponent(Limite, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE).addGap(69)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(21)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(Limite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBoxBigSmall, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(ComboBoxMetricas, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
	}

	public void RuleIntoJP() {
		Gui_Regras.addToPane("Regra:" + filename + " " + rules + "\n");
	}

}
