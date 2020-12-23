package project.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountClerk extends JFrame implements ActionListener{

	private JPanel contentPane;	
	private Connection cn=null;
	private  PreparedStatement ps=null;
	private ResultSet rs=null;
	private JComboBox comboBox=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAccountClerk frame = new DeleteAccountClerk();
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
	public DeleteAccountClerk() {
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteAccountClerk.class.getResource("/images/images.jpeg")));
		setTitle("Delete Clerk Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 315, 208);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Clerk User-ID"}));
		comboBox.setBounds(76, 36, 142, 20);
		//comboBox.addActionListener(this);
		contentPane.add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(108, 105, 85, 23);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int option=	JOptionPane.showConfirmDialog(this, "Are You Sure?");
		int index=comboBox.getSelectedIndex();
		if(index==0)
			JOptionPane.showMessageDialog(this, "Please Select Valid Clerk User-ID!!","Account",JOptionPane.WARNING_MESSAGE);
			else if(option==0)
		{
		String nm=(String)comboBox.getSelectedItem();
		
		String strdelete="delete from login where userid=?";
			try{
			ps=cn.prepareStatement(strdelete);
			ps.setString(1, nm);
			int rw=	ps.executeUpdate();
			if(rw>0)
			{
	JOptionPane.showMessageDialog(this, "Clerk Account Deleted!!","Account",JOptionPane.INFORMATION_MESSAGE);
			comboBox.removeAllItems();
			comboBox.addItem("Select Clerk User-ID");
			populateCombo();
	
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
				
				
			}
			catch(SQLException se)
			{
				
				System.out.println(se);
			}
			
			
		}
		
		}
	}
}
