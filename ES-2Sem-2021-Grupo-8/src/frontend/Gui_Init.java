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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Gui_Init {

	private JFrame frame;
	private JTextPane txtpnAsdsa = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_Init window = new Gui_Init();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_Init() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1227, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		JLabel lblNewLabel = new JLabel("");
		String basePath = new File("logo_ISCTE-IUL.jpg").getAbsolutePath();
		lblNewLabel.setIcon(new ImageIcon(basePath));

		JLabel lblNewLabel_1 = new JLabel("Projeto ES ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton btnNewButton = new JButton("Selecione um projeto java");
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(0, 120, 215));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.print("Opening: " + file.getName() + ".");
					txtpnAsdsa.setText(file.getName());
				}
			}
		});
		txtpnAsdsa.setEditable(false);
		txtpnAsdsa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		txtpnAsdsa.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnNewButton_1 = new JButton("Extrair Metodos");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Gui_Metricas gui_metrics = new Gui_Metricas();
				//gui_metrics.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBorder(null);

		JButton btnNewButton_1_1 = new JButton("Definir Regras");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//Gui_Regras gui_regras = new Gui_Regras();
				//gui_regras.setVisible(true);
			}
		});
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(1096, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(115))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(468, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(548))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(359)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtpnAsdsa, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 302, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(99)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtpnAsdsa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(51))
		);
		frame.getContentPane().setLayout(groupLayout);

	}
}
