package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class Gui_Regras extends JFrame {

	private static final long serialVersionUID = 1L;

	private File file;

	private String excel_file_path;
	private static JTextArea TextInputVars;
	private String rules = "";
	private ArrayList<String> ArrayMethods = new ArrayList<String>();

	public Gui_CriarRegra cria_Regra = new Gui_CriarRegra();
  
	ArrayList<String> arr = new ArrayList<String>();
	JList list = new JList();

	public Gui_Regras(String excel_file_path) {
		this.excel_file_path = excel_file_path;
		initialize();
	}

	public void Carrega_Regra(String filename) {
		File folder = new File("Regras");
		File[] files = folder.listFiles();
		for (File f : files) {
			if (filename.equals(f.getName())) {
				TextInputVars.setText(filename);
			}
		}

	}

	private void initialize() {
		setBounds(100, 100, 864, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);

		File dir = new File("Regras");
		ArrayMethods.add("isLongMethod");
		ArrayMethods.add("isGodClass");
		File[] files = dir.listFiles();
		for (File file : files) {
			String a = file.getName();
			String splited = a.split("\\.")[0];
			ArrayMethods.add(splited);
		}

		String[] String_Methods = new String[ArrayMethods.size()];
		for (int i = 0; i < ArrayMethods.size(); i++) {
			String_Methods[i] = ArrayMethods.get(i);
		}

		TextInputVars = new JTextArea();
		TextInputVars.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		TextInputVars.setBounds(62, 80, 390, 256);
		TextInputVars.setEditable(false);
		TextInputVars.setColumns(10);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Regras");
		lblNewLabel.setBounds(173, 28, 120, 36);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel);
		getContentPane().add(TextInputVars);

		list = new JList();
		list.setBounds(354, 309, -275, -197);
		getContentPane().add(list);

		JButton btnNewButton_1_1 = new JButton("GO!");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1_1.setBounds(147, 357, 188, 33);
		getContentPane().add(btnNewButton_1_1);

		JButton btnVisualizadorRegras = new JButton("Historico de Regras");
		btnVisualizadorRegras.setBorder(null);
		btnVisualizadorRegras.setBackground(SystemColor.textHighlight);
		btnVisualizadorRegras.setBounds(537, 132, 188, 33);
		getContentPane().add(btnVisualizadorRegras);

		JButton btnNewButton = new JButton("Criar Regra");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(537, 176, 188, 33);
		getContentPane().add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cria_Regra.setVisible(true);
			}

		});

		JButton btnNewButton_1 = new JButton("Importar Regra");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBounds(537, 235, 188, 33);
		getContentPane().add(btnNewButton_1);


		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui_Metricas gui_metrics = new Gui_Metricas(excel_file_path);
				gui_metrics.setVisible(true);
				setVisible(false);
			}
		});
		
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

		btnVisualizadorRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "";
				/*for (int i = 0; i < ArrayMethods.size(); i++) {
					data = data + ArrayMethods.get(i) + "\n";
				}*/
				File[] files = dir.listFiles();
				String body = "";
				for (File file : files) {
					try {
						BufferedReader in = new BufferedReader(new FileReader(file));
						String line;
						
						while((line = in.readLine()) != null){
						    body+=line;
						}
						in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					data = data + file.getName().split(".txt")[0] + " --> " + body + "\n";
					body="";
				}
				
				Gui_Historico frame = new Gui_Historico(data);
				frame.setVisible(true);
			}
		});
	}
	public static void addToPane(String s) {
		TextInputVars.append(s);
	}

}