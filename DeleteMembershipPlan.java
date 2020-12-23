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

public class DeleteMembershipPlan extends JFrame implements ActionListener {

	private JPanel contentPane;

	private Connection cn=null;
	private  PreparedStatement ps=null;
	private ResultSet rs=null;

	private JComboBox<String> comboBox=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteMembershipPlan frame = new DeleteMembershipPlan();
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
	public DeleteMembershipPlan() {
		createGui();
        cn=CrudOperation.createConnection();
		populateCombo();

	}
	public void populateCombo()
	{
		
		String strsql="select planid from plans";
		
		try{
			
			ps=cn.prepareStatement(strsql);
			
		rs=	ps.executeQuery();
		if(rs!=null)
		{
			while(rs.next())
			{
				
			String nm=rs.getString("planid");	
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
	setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteMembershipPlan.class.getResource("/images/images.jpeg")));
	setTitle("Delete Membership Plan");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 288, 180);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	comboBox = new JComboBox<String>();
	comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Plan-ID"}));
	comboBox.setBounds(71, 21, 123, 20);
	//1comboBox.addActionListener(this);
	contentPane.add(comboBox);
	
	JButton btnDelete = new JButton("Delete");
	btnDelete.addActionListener(this);
	btnDelete.setBounds(89, 95, 85, 23);
	contentPane.add(btnDelete);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int option=	JOptionPane.showConfirmDialog(this, "Are You Sure?");
		int index=comboBox.getSelectedIndex();
		if(index==0)
			JOptionPane.showMessageDialog(this, "Please Select Valid Plan-ID!!","Plan",JOptionPane.WARNING_MESSAGE);
			else if(option==0)
		{
		String nm=(String)comboBox.getSelectedItem();
		
		String strdelete="delete from plans where planid=?";
			try{
			ps=cn.prepareStatement(strdelete);
			ps.setString(1, nm);
			int rw=	ps.executeUpdate();//
			if(rw>0)
			{
	JOptionPane.showMessageDialog(this, "Plan Deleted!!","Plan",JOptionPane.INFORMATION_MESSAGE);
			comboBox.removeAllItems();
			comboBox.addItem("Select Plan-ID");
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
