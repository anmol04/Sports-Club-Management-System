package project.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;

import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class CreateAccountClerk extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtName;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountClerk frame = new CreateAccountClerk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateAccountClerk() {
	
		createGui();
		cn=CrudOperation.createConnection();
	}
	
	public void createGui()
	{
		setBackground(Color.LIGHT_GRAY);
		setTitle("Create Clerk Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 234);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateAccountClerk.class.getResource("/images/images.jpeg")));
		
		JLabel lblName = new JLabel("New User-ID");
		lblName.setBounds(37, 51, 89, 20);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(158, 50, 140, 23);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(37, 99, 97, 20);
		contentPane.add(lblNewPassword);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(118, 152, 89, 23);
		btnCreate.addActionListener(this);
		contentPane.add(btnCreate);
		
		password = new JPasswordField();
		password.setBounds(158, 99, 140, 20);
		contentPane.add(password);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name=txtName.getText().trim();
		char[] pass=password.getPassword();
		String userpass=new String(pass);
		if(name.isEmpty()||userpass.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please Enter Proper User-Name/ID!!","Account", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			String strinsert="insert into login(userid,userpass,usertype) values(?,?,?)";
			try
			{
				ps=cn.prepareStatement(strinsert);
				ps.setString(1, name);
				ps.setString(2, userpass);
				ps.setString(3, "clerk");
				int rw=ps.executeUpdate();//insert update delete query				
				if(rw>0)
				{
					JOptionPane.showMessageDialog(this, "Clerk-Account Created!!","Account",JOptionPane.INFORMATION_MESSAGE);
					password.setText("");
					txtName.setText("");
				}
			}
				catch(SQLException se)
				{
					System.out.println(se);
				}
				finally
				{
					try {
				
						if(ps!=null)
	
						ps.close();
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		
		
	}
}
