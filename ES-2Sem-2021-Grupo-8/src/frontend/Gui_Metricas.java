package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Gui_Metricas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		JTextArea textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblNewLabel_2 = new JLabel("M\u00E9tricas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(457).addComponent(lblNewLabel_2)
						.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(66, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNmeroDeClasses, GroupLayout.PREFERRED_SIZE, 330,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNmeroDeMtodos, GroupLayout.PREFERRED_SIZE, 330,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNmeroTotalDe, GroupLayout.PREFERRED_SIZE, 330,
										GroupLayout.PREFERRED_SIZE))
						.addGap(112)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE))
						.addGap(148).addComponent(textArea, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
						.addGap(47)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(37)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(108).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(
										Alignment.TRAILING,
										groupLayout.createSequentialGroup().addGap(8)
												.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 231,
														GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 54,
												GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNmeroDeClasses, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNmeroDeMtodos, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNmeroTotalDe, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(158)));
		getContentPane().setLayout(groupLayout);
		setBackground(SystemColor.text);
		setBounds(100, 100, 1227, 690);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}