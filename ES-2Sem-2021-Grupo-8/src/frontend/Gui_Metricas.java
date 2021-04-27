package frontend;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import backend.Excel_Helper;

public class Gui_Metricas extends JFrame {
	
	private String excel_path;

	private static final long serialVersionUID = 1L;
	private JTable tableExcel;

	public Gui_Metricas(String excel_path) {
		this.excel_path = excel_path;
		initialize();
	}

	private void initialize() {
		
		tableExcel = new JTable();
		
		Excel_Helper eh = new Excel_Helper(excel_path);
		ArrayList<String> helper=eh.readExcelSheet(true);
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
			dtm.addRow(new Object[] {(int) Double.parseDouble(abc[0]),abc[1],abc[2],abc[3],
					(int) Double.parseDouble(abc[4]),(int) Double.parseDouble(abc[5]),(int) Double.parseDouble(abc[6])
					,abc[7],(int) Double.parseDouble(abc[8]),(int) Double.parseDouble(abc[9]),abc[10]});
		}
		tableExcel.setModel(dtm);

		getContentPane().setBackground(SystemColor.text);

		JLabel lblNewLabel_2 = new JLabel("M\u00E9tricas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(486)
					.addComponent(lblNewLabel_2)
					.addContainerGap(581, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
					.addGap(218))
		);
		
		scrollPane.setViewportView(tableExcel);
		
				JLabel lblNewLabel = new JLabel("N\u00FAmero de packages");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNmeroDeClasses = new JLabel("N\u00FAmero de classes");
				lblNmeroDeClasses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNmeroDeMtodos = new JLabel("N\u00FAmero de m\u00E9todos");
				lblNmeroDeMtodos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNmeroTotalDe = new JLabel("N\u00FAmero total de linhas de c\u00F3digo");
				lblNmeroTotalDe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNewLabel_1_3 = new JLabel("0");
				lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNewLabel_1_2 = new JLabel("0");
				lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNewLabel_1_1 = new JLabel("0");
				lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JLabel lblNewLabel_1 = new JLabel("0");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmeroDeClasses, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmeroDeMtodos, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmeroTotalDe, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
					.addGap(73)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(75))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNmeroDeClasses, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNmeroDeMtodos, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(3)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNmeroTotalDe, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setBackground(SystemColor.text);
		setBounds(100, 100, 1227, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}