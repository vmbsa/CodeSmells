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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import backend.Excel_Helper;
import backend.JavaFilesHandler;

public class Gui_Init {

	private JFrame frame;
	private JTextPane txtpnAsdsa = new JTextPane();

	private File selected_project_folder = null;

	private JLabel error_message = new JLabel("");
	private JavaFilesHandler handler;
	private String excel ;

	public Gui_Init() {
		initialize();

	}
	
	public void show() {
		frame.setVisible(true);
	}
	
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
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selected_project_folder = fc.getSelectedFile();
					try {
						handler = new JavaFilesHandler(selected_project_folder.getAbsolutePath());
						txtpnAsdsa.setText(selected_project_folder.getAbsolutePath());
						error_message.setText("");
					} catch (Exception e1) {
						error_message.setText("Seleciona um projeto v\u00E1lido");
						error_message.setForeground(Color.RED);
						txtpnAsdsa.setText("");

					}
				}
			}
		});
		txtpnAsdsa.setEditable(false);
		txtpnAsdsa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		txtpnAsdsa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnExtrairMtricas = new JButton("Extrair M\u00E9tricas");
		btnExtrairMtricas.setFocusable(false);
		btnExtrairMtricas.setBorderPainted(false);
		btnExtrairMtricas.setBorder(null);
		btnExtrairMtricas.setBackground(SystemColor.textHighlight);
		btnExtrairMtricas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selected_project_folder != null) {
					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Choose Excel File Directory");
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = fc.showOpenDialog(fc);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						excel = file.getAbsolutePath() + "\\" + handler.getProjectName() + "_metrics.xlsx";
						Excel_Helper excel_helper = new Excel_Helper(handler, excel);
						excel_helper.writeExcel();
						Gui_Metricas gui_metrics = new Gui_Metricas(excel, handler);
						gui_metrics.setVisible(true);
						frame.setVisible(false);
					}
				} else {
					error_message.setText("Seleciona um projeto v\u00E1lido");
					error_message.setForeground(Color.RED);
					txtpnAsdsa.setText("");
				}

			}
		});

//		JButton btnNewButton_1_1 = new JButton("Definir Regras");
//		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				 Gui_Regras gui_regras = new Gui_Regras(excel);
//				 gui_regras.setVisible(true);
//			}
//		});
//		btnNewButton_1_1.setBorder(null);
//		btnNewButton_1_1.setBackground(SystemColor.textHighlight);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(1096, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(115))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(468, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(548))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(316)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExtrairMtricas, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(146)
									.addComponent(error_message, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtpnAsdsa, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)))))
					.addGap(283))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 347, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addComponent(error_message)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnAsdsa, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnExtrairMtricas, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(17))
		);
		frame.getContentPane().setLayout(groupLayout);

	}
}
