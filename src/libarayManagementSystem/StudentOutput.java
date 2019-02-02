package libarayManagementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StudentOutput extends JFrame {

	private JPanel contentPane;
	Connection cn = null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	private JTextField barCode;
	private JTextField studentID;
	private JTextField studentName;
	private JTextField deperment;
	private JLabel messageLevel;
	private JButton btnDelivered;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentOutput frame = new StudentOutput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String SearchData() {
		String five =barCode.getText();
		String sql = "select * from student where barCode =?";
		String name=null;
		String id=null;
		String dep=null;
		String message=null;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				  name=rs.getString("Student_Name");
				  id=rs.getString("Student_ID");
				 dep=rs.getString("Student_Dep");
				 message=rs.getString("Message");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		setData(name, id, dep, message);
		return name;
	}
	public void setData(String name,String id,String dep,String message) {
		if(name!=null && id!=null && dep!=null && message!=null) {
			studentID.setText(id);
			deperment.setText(dep);
			studentName.setText(name);
			messageLevel.setText(message);
		}else {
			JOptionPane.showMessageDialog(null,"Student Not found!!");
		}
	}
	
	public void setname() {
		System.out.println("callll");
		String name=SearchData();
		System.out.println(name);
		studentName.setText(name);
	}
	private void refresh() {
		barCode.setText(null);
		studentID.setText(null);
		studentName.setText(null);
		deperment.setText(null);
		messageLevel.setText(null);
	}
	

	public void update() {
		String five =barCode.getText();
		String Message= "It is Delivered!";
		String sql = "update student SET Message = ? WHERE barCode = ?";
		try {
		    ps = cn.prepareStatement(sql);
			ps.setString(1, Message);
			ps.setString(2, five);
			ps.executeUpdate();
			//System.out.println("Data updated " + name + " " + pass);
            JOptionPane.showMessageDialog(null,"OK!");
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public StudentOutput() {
		design();
		cn=SQLConnection.ConnecrDb();
	}

	/**
	 * Create the frame.
	 */
	public void design() {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 350);
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
					//JOptionPane.showMessageDialog(null, "Please Input ID,Name,Deperment!");
					SearchData();
					//setname();
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
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(450, 55, 89, 35);
		contentPane.add(btnNewButton);
		
		messageLevel = new JLabel("");
		messageLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
		messageLevel.setBounds(111, 244, 300, 29);
		contentPane.add(messageLevel);
		
		btnDelivered = new JButton("Delivered");
		btnDelivered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		btnDelivered.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelivered.setBounds(450, 153, 103, 35);
		contentPane.add(btnDelivered);
	
	
	}

}
