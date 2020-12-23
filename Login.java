package project.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtName;
	private Connection cn=null;
	private PreparedStatement ps=null;
    private ResultSet rs=null;
    private JComboBox comboBox=null;
    private JPasswordField password;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		createGui();
		cn=CrudOperation.createConnection();
	}
		public void createGui()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/images.jpeg")));
		setBackground(Color.GRAY);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 240);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("User-ID");
		lblName.setBounds(46, 41, 67, 23);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("User-Password");
		lblPassword.setBounds(46, 94, 103, 23);
		contentPane.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setBounds(159, 42, 126, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSubmit = new JButton("Sign In");
		btnSubmit.setBounds(108, 153, 89, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		password = new JPasswordField();
		password.setBounds(159, 95, 126, 20);
		contentPane.add(password);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String userid=txtName.getText();
		char[] pass=password.getPassword();
		String userpass=new String(pass);
		if(userid.isEmpty()&&userpass.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter Valid User-Name or Password!!", "Account", JOptionPane.WARNING_MESSAGE);
		
		}
		else
		{
			String strsql="select * from  login where userid=? and userpass=?";
			
			try{
				ps=cn.prepareStatement(strsql);
				ps.setString(1, userid);
				ps.setString(2, userpass);
				rs=ps.executeQuery();
				if(rs.next())
				{
					String utype=rs.getString("usertype");
					if(utype.equals("admin"))
					{
						JOptionPane.showMessageDialog(this, "Successfully Signed In.\nClick OK to Run....!!","Club Owner Account",JOptionPane.INFORMATION_MESSAGE);
						Admin a=new Admin();
						a.setVisible(true);
						this.dispose();
					}
					if(utype.equals("clerk"))
					{

						JOptionPane.showMessageDialog(this, "Successfully Signed In.\nClick OK to Run....!!","Clerk Account",JOptionPane.INFORMATION_MESSAGE);
						Clerk c=new Clerk();
						c.setVisible(true);
						this.dispose();
					}

				
				}
				
				else
				{
					JOptionPane.showMessageDialog(this,"Invalid User-Name or Password!!","Account",JOptionPane.ERROR_MESSAGE);
				}
			
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
			finally
			{
				try{
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}
		}

		
	}
}
