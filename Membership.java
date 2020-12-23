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
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dbInfo.CrudOperation;

import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Membership extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtMemberName;
	private JTextField txtPlanID;
	
	private Connection cn=null;
	private PreparedStatement ps=null;
    private ResultSet rs=null;
    private JComboBox comboBox=null;
    private JButton btnOk=null;
    private JTextField txtDom;
    private JTextField txtDoe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Membership frame = new Membership();
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
	public Membership() {
	createGui();	
	cn=CrudOperation.createConnection();
	populateCombo();
}
public void populateCombo() {
	// TODO Auto-generated method stub
	String strsql="select memberid from memberdetail";
    try{
    	ps=cn.prepareStatement(strsql);
    	rs=ps.executeQuery();
    	if(rs!=null)
    	{
    		while(rs.next())
    		{
    			
    			String id=rs.getString("memberid");
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
	setIconImage(Toolkit.getDefaultToolkit().getImage(Membership.class.getResource("/images/images.jpeg")));
	setTitle("Membership");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 407, 290);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Member-ID"}));
	comboBox.setBounds(125, 26, 137, 20);
	comboBox.addActionListener(this);
	contentPane.add(comboBox);
	
	JLabel lblMemberName = new JLabel("Member Name");
	lblMemberName.setBounds(46, 73, 100, 20);
	contentPane.add(lblMemberName);
	
	JLabel lblPlanId = new JLabel("Plan ID");
	lblPlanId.setBounds(46, 104, 100, 20);
	contentPane.add(lblPlanId);
	
	JLabel lblDateOfMembership = new JLabel("Date Of Membership");
	lblDateOfMembership.setBounds(46, 135, 124, 24);
	contentPane.add(lblDateOfMembership);
	
	JLabel lblDateOfExpiry = new JLabel("Date  Of Expiry");
	lblDateOfExpiry.setBounds(46, 170, 100, 20);
	contentPane.add(lblDateOfExpiry);
	
	txtMemberName = new JTextField();
	txtMemberName.setBounds(192, 73, 148, 20);
	contentPane.add(txtMemberName);
	txtMemberName.setColumns(10);
	
	txtPlanID = new JTextField();
	txtPlanID.setBounds(192, 104, 148, 20);
	contentPane.add(txtPlanID);
	txtPlanID.setColumns(10);
	
	btnOk = new JButton("OK");
	btnOk.setBounds(156, 214, 52, 23);
	btnOk.addActionListener(this);
	contentPane.add(btnOk);
	
	txtDom = new JTextField();
	txtDom.setBounds(192, 137, 148, 20);
	contentPane.add(txtDom);
	txtDom.setColumns(10);
	
	txtDoe = new JTextField();
	txtDoe.setBounds(192, 170, 148, 20);
	contentPane.add(txtDoe);
	txtDoe.setColumns(10);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        int index=comboBox.getSelectedIndex();

		if(e.getSource()==comboBox)
		{
		if(index==0)
		{
			JOptionPane.showMessageDialog(this, "Please Select Valid Member-ID!!","Member",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			
			String cid=(String)comboBox.getSelectedItem();
				String strsql="select membername,planid,dateofmembership,dateofexpiry from memberdetail where memberid=?";
				try{
					ps=cn.prepareStatement(strsql);
					ps.setString(1, cid);
					rs=ps.executeQuery();
					if(rs.next())
					{
						String nm=rs.getString("membername");
						String pid=rs.getString("planid");
						String dom=rs.getString("dateofmembership");
						String doe=rs.getString("dateofexpiry");
						txtMemberName.setText(nm);
						txtPlanID.setText(pid);
						txtDom.setText(dom);
						txtDoe.setText(doe);
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
		if(e.getSource()==btnOk)
		{
		this.dispose();	
		}
	}
}
