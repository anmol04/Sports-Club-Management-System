package project.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddMembershipPlans extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtPlanID;
	private JTextField txtPlanName;
	private JTextField txtFacilities;
	private JTextField txtCharge;
	private JTextField txtDuration;
	private JButton btnAdd;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private String id=null;
	private ResultSet rs=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMembershipPlans frame = new AddMembershipPlans();
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
	public AddMembershipPlans() {
	createGui();
	cn=CrudOperation.createConnection();
	}
public void createGui()
{
	setIconImage(Toolkit.getDefaultToolkit().getImage(AddMembershipPlans.class.getResource("/images/images.jpeg")));
	setTitle("Add Membership Plan");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 415, 358);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblPlanId = new JLabel("Plan ID");
	lblPlanId.setBounds(52, 51, 102, 20);
	contentPane.add(lblPlanId);
	
	JLabel lblPlanName = new JLabel("Plan Name");
	lblPlanName.setBounds(52, 96, 102, 17);
	contentPane.add(lblPlanName);
	
	JLabel lblFacilities = new JLabel("Facilities");
	lblFacilities.setBounds(52, 137, 102, 20);
	contentPane.add(lblFacilities);
	
	JLabel lblCharge = new JLabel("Charges");
	lblCharge.setBounds(52, 175, 102, 20);
	contentPane.add(lblCharge);
	
	JLabel lblDuration = new JLabel("Duration");
	lblDuration.setBounds(52, 218, 102, 20);
	contentPane.add(lblDuration);
	
	txtPlanID = new JTextField();
	txtPlanID.setBounds(198, 51, 153, 20);
	contentPane.add(txtPlanID);
	txtPlanID.setColumns(10);
	
	txtPlanName = new JTextField();
	txtPlanName.setBounds(198, 94, 153, 20);
	contentPane.add(txtPlanName);
	txtPlanName.setColumns(10);
	
	txtFacilities = new JTextField();
	txtFacilities.setBounds(198, 137, 153, 20);
	contentPane.add(txtFacilities);
	txtFacilities.setColumns(10);
	
	txtCharge = new JTextField();
	txtCharge.setBounds(198, 175, 89, 20);
	contentPane.add(txtCharge);
	txtCharge.setColumns(10);
	
	txtDuration = new JTextField();
	txtDuration.setBounds(198, 218, 89, 20);
	contentPane.add(txtDuration);
	txtDuration.setColumns(10);
	
	btnAdd = new JButton("Add");
	btnAdd.setBounds(136, 273, 89, 23);
	btnAdd.addActionListener(this);
	contentPane.add(btnAdd);
	
	JLabel lblRs = new JLabel("Rs.");
	lblRs.setBounds(297, 178, 54, 17);
	contentPane.add(lblRs);
	
	JLabel lblDays = new JLabel("Days");
	lblDays.setBounds(297, 221, 54, 20);
	contentPane.add(lblDays);
	
}

public void checkPlanId()
{
	int cnt=0;
	String strcount="select count(*) from plans where planid=?";
	try{
		
		ps=cn.prepareStatement(strcount);
		ps.setString(1, id);
		
		rs=ps.executeQuery();
		
		
		if(rs!=null &&rs.next())
		{
			
			cnt=	rs.getInt(1);
			if(cnt>=1)
				JOptionPane.showMessageDialog(this, "Already Assigned Plan-ID: "+id+"\nPlease Enter Another!!","Plans",JOptionPane.WARNING_MESSAGE);
			
			
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	    id=txtPlanID.getText().trim();
		String nm=txtPlanName.getText().trim();
		String f=txtFacilities.getText().trim();
		String c=txtCharge.getText().trim();
		String d=txtDuration.getText().trim();
		
		if(id.isEmpty()||nm.isEmpty()||c.isEmpty()||d.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please Enter Proper Data!!","Data",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			checkPlanId();
			String strinsert="insert into plans(planid,planname,facilities,charge,duration) values(?,?,?,?,?)";
			try
			{
				ps=cn.prepareStatement(strinsert);
				ps.setString(1, id);
				ps.setString(2, nm);
				ps.setString(3, f);
				ps.setLong(4, Long.parseLong(c));
				ps.setLong(5, Integer.parseInt(d));
				int rw=ps.executeUpdate();//insert update delete query
				if(rw>0)
				{
					JOptionPane.showMessageDialog(this, "Plan Added!!","Plan",JOptionPane.INFORMATION_MESSAGE);
					txtPlanID.setText("");
					txtPlanName.setText("");
					txtFacilities.setText("");
					txtCharge.setText("");
					txtDuration.setText("");
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
