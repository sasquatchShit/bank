package bank;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Window extends JFrame {
	protected final String author = "Brady L Kerins";
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					Check.runner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	protected static final JComboBox<String> comboBox = new JComboBox<String>();
	protected static final double hash1= Math.random(), hash2= Math.random();
	protected final String mutables[] = {
			"0.0","0.0",Double.toString(hash1),Double.toString(hash2),"c0de"
	};
	protected final String messages[]= {
			"Checking","Savings","Bank Accounts","Bank"
	};
	protected static DefaultMutableTreeNode node_1, node_2;
	protected final static JTree tree = new JTree();	
	protected final JScrollPane scrollPane = new JScrollPane();	
	protected final JLabel lblNewLabel = new JLabel(messages[3]);
	protected static JTextArea textArea = new JTextArea();
	protected final void loadCombo() {
		comboBox.addItem(mutables[4]);
		comboBox.addItem(mutables[2]);
		comboBox.addItem(mutables[3]);
	}
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(6, -1, 40, 22);
		contentPane.add(lblNewLabel);	
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(messages[2]) {
				private static final long serialVersionUID = -1116656696702746188L;
				{
						node_1 = new DefaultMutableTreeNode(messages[0]);
							node_1.add(new DefaultMutableTreeNode(mutables[0]));
						add(node_1);
						node_2 = new DefaultMutableTreeNode(messages[1]);
							node_2.add(new DefaultMutableTreeNode(mutables[1]));
						add(node_2);
				}
			}
		));
		tree.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		tree.setBounds(16, 34, 261, 232);	
		tree.setFocusable(false);
		contentPane.add(tree);	
		scrollPane.setBounds(287, 0, 157, 266);
		scrollPane.add(new JTextArea());
		contentPane.add(scrollPane);	
		textArea.setBackground(Color.black);
		textArea.setEditable(false);
		textArea.setForeground(Color.orange);
		scrollPane.setViewportView(textArea);
		comboBox.setForeground(new Color(30, 144, 255));
		comboBox.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		comboBox.setBounds(58, -2, 231, 27);	
		loadCombo();
		comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textArea.setText(comboBox.getSelectedItem().toString());
				}			
		});	
		contentPane.add(comboBox);
	}
}
