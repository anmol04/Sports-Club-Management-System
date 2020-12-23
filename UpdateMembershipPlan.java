package project.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMembershipPlan extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtPlanName;
	private JTextField txtFacilities;
	private JTextField txtCharges;
	private JTextField txtDuration;
	private Connection cn=null;
	private PreparedStatement ps=null;
    private ResultSet rs=null;
    private JComboBox comboBox=null;
    private JButton btnUpdate=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMembershipPlan frame = new UpdateMembershipPlan();
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
	public UpdateMembershipPlan() {
	
		createGui();
		cn=CrudOperation.createConnection();
		populateCombo();
	}
	public void populateCombo() {
		// TODO Auto-generated method stub
		String strsql="select planid from plans";
        try{
        	ps=cn.prepareStatement(strsql);
        	rs=ps.executeQuery();
        	if(rs!=null)
        	{
        		while(rs.next())
        		{
        			
        			String id=rs.getString("planid");
        			comboBox.addItem(id);
        		}
        	}
        }
        catch(SQLException se)
        {
        	System.out.println(se);
        }
        finally
        {
        	try{
        		if(rs!=null)
        			rs.close();
        		if(ps!=null)
        			ps.close();
        	}
        	catch(SQLException se)
        	{
        		System.out.println(se);
        	}
        }
	}


public void createGui()
{
	setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateMembershipPlan.class.getResource("/images/images.jpeg")));
	setTitle("Update Membership Plan");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 380, 300);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Plan ID"}));
	comboBox.setBounds(122, 22, 117, 20);
	comboBox.addActionListener(this);
	contentPane.add(comboBox);
	
	JLabel lblPlanName = new JLabel("Plan Name ");
	lblPlanName.setBounds(47, 69, 89, 20);
	contentPane.add(lblPlanName);
	
	JLabel lblFacilities = new JLabel("Facilities");
	lblFacilities.setBounds(47, 100, 89, 20);
	contentPane.add(lblFacilities);
	
	JLabel lblCharges = new JLabel("Charges");
	lblCharges.setBounds(47, 131, 89, 20);
	contentPane.add(lblCharges);
	
	JLabel lblDuration = new JLabel("Duration");
	lblDuration.setBounds(47, 162, 89, 20);
	contentPane.add(lblDuration);
	
	btnUpdate = new JButton("Update");
	btnUpdate.setBounds(122, 212, 89, 23);
	btnUpdate.addActionListener(this);
	contentPane.add(btnUpdate);
	
	txtPlanName = new JTextField();
	txtPlanName.setBounds(164, 68, 152, 23);
	contentPane.add(txtPlanName);
	txtPlanName.setColumns(10);
	
	txtFacilities = new JTextField();
	txtFacilities.setBounds(164, 102, 152, 20);
	contentPane.add(txtFacilities);
	txtFacilities.setColumns(10);
	
	txtCharges = new JTextField();
	txtCharges.setBounds(164, 131, 89, 20);
	contentPane.add(txtCharges);
	txtCharges.setColumns(10);
	
	txtDuration = new JTextField();
	txtDuration.setBounds(164, 162, 89, 20);
	contentPane.add(txtDuration);
	txtDuration.setColumns(10);
	
	JLabel lblRs = new JLabel("Rs.");
	lblRs.setBounds(263, 133, 53, 17);
	contentPane.add(lblRs);
	
	JLabel lblDays = new JLabel("Days");
	lblDays.setBounds(263, 164, 53, 17);
	contentPane.add(lblDays);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index=comboBox.getSelectedIndex();
		
		if(index==0)
		{
			JOptionPane.showMessageDialog(this, "Please Select a Valid Plan-ID","Plan",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
		 String cid=(String)comboBox.getSelectedItem();
			
			if(e.getSource()==comboBox)
			{
				String strsql="select planname,facilities,charge,duration from plans where planid=?";
				try{
					ps=cn.prepareStatement(strsql);
					ps.setString(1, cid);
					rs=ps.executeQuery();
					if(rs.next())
					{
						String pn=rs.getString("planname");
						String f=rs.getString("facilities");
					    long c=rs.getLong("charge");
					    int d=rs.getInt("duration");
						txtPlanName.setText(pn);
						txtFacilities.setText(f);
						txtCharges.setText(String.valueOf(c));
						txtDuration.setText(String.valueOf(d));
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
					String pn=txtPlanName.getText().trim();
					String f=txtFacilities.getText().trim();
				    String c=txtCharges.getText().trim();
				    String d=txtDuration.getText().trim();
					if(pn.isEmpty()||c.isEmpty()||d.isEmpty())
					{
						JOptionPane.showMessageDialog(this, "Please Enter Proper Data!!","Plan",JOptionPane.ERROR_MESSAGE);
					}
				
					else{
					String strupdate="update plans set planname=?,facilities=?,charge=?,duration=? where planid=?";
					try{
						ps=cn.prepareStatement(strupdate);
						ps.setString(1, txtPlanName.getText());
						ps.setString(2, txtFacilities.getText());
						ps.setLong(3, Long.parseLong(txtCharges.getText()));
						ps.setLong(4, Integer.parseInt(txtDuration.getText()));
						ps.setString(5, (String) comboBox.getSelectedItem());
						int rw=ps.executeUpdate();
						if(rw>0)
						{
							JOptionPane.showMessageDialog(this, "Plan Updated!!","Plan",JOptionPane.INFORMATION_MESSAGE);
						
							txtPlanName.setText("");
							txtFacilities.setText("");
							txtCharges.setText(null);
							txtDuration.setText(null);
							comboBox.setSelectedItem(null);
							
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
