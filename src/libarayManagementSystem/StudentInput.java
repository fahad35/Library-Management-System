package libarayManagementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentInput extends JFrame {

	private JPanel contentPane;
	Connection cn = null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	private JTextField barCode;
	private JTextField studentID;
	private JTextField studentName;
	private JTextField deperment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInput frame = new StudentInput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Add() {
		String one=barCode.getText();
		String three = studentID.getText();
		String  two = studentName.getText();
		String four =deperment.getText(); 
		String five ="Not delivered!!"; 
		String sql = "INSERT INTO student (barCode,Student_Name,Student_ID,Student_Dep,Message ) VALUES (?,?,?,?,?)";
		try {
		    ps = cn.prepareStatement(sql);
		    ps.setString(1, one);
		    ps.setString(2, two);
		    ps.setString(3, three);
		    ps.setString(4, four);
		    ps.setString(5, five);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added Sucessfully!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public StudentInput() {
		design();
		cn = SQLConnection.ConnecrDb();
	}

	/**
	 * Create the frame.
	 */
	public void design() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bar Code:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 33, 146, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentName.setBounds(20, 149, 146, 29);
		contentPane.add(lblStudentName);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentId.setBounds(20, 92, 146, 29);
		contentPane.add(lblStudentId);
		
		JLabel lblDeperment = new JLabel("Deperment: ");
		lblDeperment.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeperment.setBounds(20, 204, 146, 29);
		contentPane.add(lblDeperment);
		
		barCode = new JTextField();
		barCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					JOptionPane.showMessageDialog(null, "Please Input ID,Name,Deperment!");
				}
			}
		});
		barCode.setBounds(164, 31, 220, 34);
		contentPane.add(barCode);
		barCode.setColumns(10);
		
		studentID = new JTextField();
		studentID.setColumns(10);
		studentID.setBounds(164, 90, 220, 34);
		contentPane.add(studentID);
		
		studentName = new JTextField();
		studentName.setColumns(10);
		studentName.setBounds(164, 147, 220, 34);
		contentPane.add(studentName);
		
		deperment = new JTextField();
		deperment.setColumns(10);
		deperment.setBounds(164, 202, 220, 34);
		contentPane.add(deperment);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(355, 247, 89, 35);
		contentPane.add(btnNewButton);
	
	}
}
