package frontend;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import backend.Excel_Helper;

public class Gui_Metricas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableExcel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_Metricas window = new Gui_Metricas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_Metricas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		getContentPane().setBackground(SystemColor.text);

		JLabel lblNewLabel = new JLabel("N\u00FAmero de packages");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNmeroDeClasses = new JLabel("N\u00FAmero de classes");
		lblNmeroDeClasses.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNmeroDeMtodos = new JLabel("N\u00FAmero de m\u00E9todos");
		lblNmeroDeMtodos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNmeroTotalDe = new JLabel("N\u00FAmero total de linhas de c\u00F3digo");
		lblNmeroTotalDe.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1_1 = new JLabel("0");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1_2 = new JLabel("0");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1_3 = new JLabel("0");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_2 = new JLabel("M\u00E9tricas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton btnVerExcel = new JButton("Ver Excel");
		btnVerExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String excelPath = fc.getSelectedFile().getAbsolutePath();
					Excel_Helper eh = new Excel_Helper(excelPath);
					ArrayList<String> helper=eh.readExcelSheet(true);
					System.out.println(helper.get(0));
					

					
					DefaultTableModel dtm = new DefaultTableModel();
					dtm.addColumn("MethodID");
					dtm.addColumn("package");
					dtm.addColumn("class");
					dtm.addColumn("method");
					dtm.addColumn("NOM_class");
					dtm.addColumn("LOC_class");
					dtm.addColumn("WMC_class");
					dtm.addColumn("LOC_method");
					dtm.addColumn("CYCLO_method");
					dtm.addColumn("is_God_Class");
					dtm.addColumn("is_Long_Method");
					for (int a= 1; a<helper.size();a++) {
						String[] abc = helper.get(a).split("\\|");
						System.out.println(Arrays.toString(abc));
						dtm.addRow(new Object[] {(int) Double.parseDouble(abc[0]),abc[1],abc[2],abc[3],
								(int) Double.parseDouble(abc[4]),(int) Double.parseDouble(abc[5]),(int) Double.parseDouble(abc[6])
								,abc[7],(int) Double.parseDouble(abc[8]),(int) Double.parseDouble(abc[9]),abc[10]});
					}
					tableExcel.setModel(dtm);

				}
			}
		});
		btnVerExcel.setFocusable(false);
		btnVerExcel.setBorderPainted(false);
		btnVerExcel.setBorder(null);
		btnVerExcel.setBackground(SystemColor.textHighlight);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(457)
					.addComponent(lblNewLabel_2)
					.addContainerGap(609, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmeroDeClasses, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmeroDeMtodos, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmeroTotalDe, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
					.addGap(112)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(170)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
						.addComponent(btnVerExcel, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
					.addGap(47))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNmeroDeClasses, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNmeroDeMtodos, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNmeroTotalDe, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
							.addGap(79))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(btnVerExcel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);

		tableExcel = new JTable();
		
		JScrollBar scrollBar = new JScrollBar();
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setOrientation(JScrollBar.HORIZONTAL);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(tableExcel, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
						.addComponent(scrollBar_1, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tableExcel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setBackground(SystemColor.text);
		setBounds(100, 100, 1227, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}