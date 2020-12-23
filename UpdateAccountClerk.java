package project.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;

import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class UpdateAccountClerk extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
    private ResultSet rs=null;
    private JComboBox comboBox=null;
    private JPasswordField password=null;
	private JButton btnUpdate=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAccountClerk frame = new UpdateAccountClerk();
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
	public UpdateAccountClerk() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateAccountClerk.class.getResource("/images/images.jpeg")));
		createGui();
		cn=CrudOperation.createConnection();
		populateCombo();
	}
	
	public void populateCombo()
	{
		
		String strsql="select userid from login where usertype=?";
		
		try{
			
			ps=cn.prepareStatement(strsql);
			ps.setString(1, "clerk");
			rs=	ps.executeQuery();
		if(rs!=null)
		{			
			while(rs.next())
			{
			 String nm=rs.getString("userid");	
			 comboBox.addItem(nm);
						
			}
		}
		
		}
		catch(SQLException se)
		{
			
		System.out.println(se);
		}
		
		finally{
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

public void createGui()
{
	setTitle("Update Clerk Account");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 328, 207);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Clerk User-ID"}));
	comboBox.setBounds(87, 21, 142, 20);
	comboBox.addActionListener(this);
	contentPane.add(comboBox);
	
	JLabel lblPassword = new JLabel("New Password");
	lblPassword.setBounds(40, 79, 99, 20);
	contentPane.add(lblPassword);
	
	btnUpdate = new JButton("Update");
	btnUpdate.setBounds(100, 130, 89, 23);
	btnUpdate.addActionListener(this);
	contentPane.add(btnUpdate);
	
	password = new JPasswordField();
	password.setBounds(151, 79, 112, 20);
	contentPane.add(password);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
int index=comboBox.getSelectedIndex();
		
		if(index==0)
		{
			JOptionPane.showMessageDialog(this, "Please Select Valid User-ID!!","Account",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			
			String cid=(String)comboBox.getSelectedItem();
			
			if(e.getSource()==comboBox)
			{
				String strsql="select * from login where userid=?";
				try{
					ps=cn.prepareStatement(strsql);
					ps.setString(1, cid);
					rs=ps.executeQuery();
					if(rs.next())
					{
						String pass=rs.getString("userpass");
						password.setText(pass);
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
				
				if(e.getSource()==btnUpdate)
				{
					char[] pass=password.getPassword();
					String userpass=new String(pass);
					if(userpass.isEmpty())
					{
						JOptionPane.showMessageDialog(this, "Please Enter Password!!","Account",JOptionPane.WARNING_MESSAGE);
					}
				
					else{
					String strupdate="update login set userpass=? where userid=?";
					try{
						ps=cn.prepareStatement(strupdate);
						ps.setString(1, userpass);
						ps.setString(2, (String) comboBox.getSelectedItem());
						int rw=ps.executeUpdate();
						if(rw>0)
						{
							JOptionPane.showMessageDialog(this, "Password Modified!!","Password",JOptionPane.WARNING_MESSAGE);
						    comboBox.setSelectedItem(null);
						    password.setText(null);
						}
					}
						catch(SQLException se)
						{
							System.out.println(se);
						}
					finally{
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
	}
}

				
