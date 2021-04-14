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

		JButton btnNewButton_1 = new JButton("Extrair Metricas");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selected_project_folder != null) {
					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Choose Excel File Directory");
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = fc.showOpenDialog(fc);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						String excel = file.getAbsolutePath() + "\\" + handler.getProjectName() + "_metrics.xlsx";
						Excel_Helper excel_helper = new Excel_Helper(handler, excel);
						excel_helper.writeExcel();
						Gui_Metricas gui_metrics = new Gui_Metricas(excel);
						gui_metrics.setVisible(true);
					}
				} else {
					error_message.setText("Seleciona um projeto v\u00E1lido");
					error_message.setForeground(Color.RED);
					txtpnAsdsa.setText("");
				}

			}
		});
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBorder(null);

		JButton btnNewButton_1_1 = new JButton("Definir Regras");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// Gui_Regras gui_regras = new Gui_Regras();
				// gui_regras.setVisible(true);
			}
		});
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(1098, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(115))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(469, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(548))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(359)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(error_message, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtpnAsdsa, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addComponent(error_message)
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
