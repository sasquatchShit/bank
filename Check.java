package bank;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Check extends JFrame {
	protected final String author = "Brady L Kerins";
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField textField;
	protected JTextField textField_2;
	protected JButton btnNewButton;
	public static void runner() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Check frame = new Check();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	protected final String messages[]= {
			Double.toString(Window.hash1),"Sender",Double.toString(Window.hash2),"Deposit","Sender Code","Recepient Name",
			"Amount Number in USD","choose","Check","Source","Savings->"
			,"\nfailed"," Deposited: $"
	};
	Log CLog = new Log(messages[9], Window.node_1,Window.tree);
	Log SLog = new Log(messages[10],Window.node_2,Window.tree);
	protected JComboBox<String> comboBox = new JComboBox<String>();
	protected ArrayList<String> Senders = new ArrayList<String>();
	protected JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_1_1;
	protected JButton btnNewButton_1;
	protected final void loadSenders() {
		Senders.add(messages[0]);
		Senders.add(messages[2]);
	}
	protected final void reset() {
		textField.setText("");
		textField_2.setText("");
		comboBox.setSelectedItem(messages[7]);
		CLog.updateHistory(Window.textArea);
	}
	protected final void fillCombo(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem(messages[7]);
		comboBox.addItem(messages[1]);
	}
	public Check() {
		loadSenders();
		KeyAdapter enterDeposit = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {				
				if(e.getKeyChar() == '\n')
					btnNewButton.doClick();
			}
		};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		lblNewLabel = new JLabel(messages[4]);
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 13));
		lblNewLabel.setBounds(6, 0, 438, 16);
		contentPane.add(lblNewLabel);
		textField = new JTextField();
		textField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		textField.setBounds(6, 28, 130, 26);
		textField.addKeyListener(enterDeposit);	
		contentPane.add(textField);
		textField.setColumns(10);		
		lblNewLabel_1 = new JLabel(messages[5]);
		lblNewLabel_1.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 13));
		lblNewLabel_1.setBounds(6, 66, 438, 16);
		contentPane.add(lblNewLabel_1);		
		lblNewLabel_1_1 = new JLabel(messages[6]);
		lblNewLabel_1_1.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(6, 132, 438, 16);
		contentPane.add(lblNewLabel_1_1);		
		textField_2 = new JTextField();
		textField_2.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(6, 160, 130, 26);
		textField_2.addKeyListener(enterDeposit);
		contentPane.add(textField_2);
		btnNewButton = new JButton(messages[3]);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						double lump = Double.parseDouble(textField_2.getText());
						if(lump<CLog.getMaxTotal() && lump<SLog.getMaxTotal()) {
							if(!comboBox.getSelectedItem().toString().equals(messages[1])) {																										
									CLog.withdrawl(lump,messages[9]);															
							}else if(Senders.contains(textField.getText())) {
								if(!comboBox.getSelectedItem().equals(textField.getText())) {
										CLog.deposit(lump,messages[10]);
								}
							}	
						}else {
							CLog.maxSum(SLog);
						}
						reset();
					}catch(Exception ex) {
						System.out.println(ex.getCause());
					}
			}
		});
		btnNewButton.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 13));
		btnNewButton.setBounds(6, 198, 117, 29);
		contentPane.add(btnNewButton);
		comboBox.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));		
		fillCombo(comboBox);		
		comboBox.setBounds(6, 93, 135, 27);
		contentPane.add(comboBox);
		btnNewButton_1 = new JButton(messages[10]);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						double lump = Double.parseDouble(textField_2.getText());		
						CLog.transfer(lump, SLog);
						reset();
					}catch(Exception ex) {
						System.out.println(ex.getCause());
					}
			}
		});
		btnNewButton_1.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 13));
		btnNewButton_1.setBounds(148, 160, 117, 29);
		contentPane.add(btnNewButton_1);		
	}
}
