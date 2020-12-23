package project.gui;

import java.awt.BorderLayout;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import dbInfo.CrudOperation;

import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMember extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtMemberName;
	private JTextField txtEmail;
	private JTextField txtAdd;
	private JTextField txtPh;
	private JTextField txtOccupation;
	private Connection cn=null;
	private PreparedStatement ps=null;
    private ResultSet rs=null;
    private JButton btnUpdate=null; 
    private JComboBox<String> memberID=null;
    private JComboBox gameID=null;
    private JComboBox planID=null;
    private JDateChooser dob=null;
    private JDateChooser dom=null;
    private JDateChooser doe=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMember frame = new UpdateMember();
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
	public UpdateMember() {
		createGui();
		cn=CrudOperation.createConnection();
		populateComboMemberID();
		populateComboPlanID();
		populateComboGameID();
	}
	public void populateComboMemberID() {
		// TODO Auto-generated method stud
		String strsql="select memberid from memberdetail";
	
		try{
        	ps=cn.prepareStatement(strsql);
        	rs=ps.executeQuery();
        	if(rs!=null)
        	{
        		while(rs.next())
        		{
        			
        			String id=rs.getString("memberid");
        			memberID.addItem(id);
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

	public void populateComboPlanID() {
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
        			planID.addItem(id);
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

	public void populateComboGameID() {
		// TODO Auto-generated method stub
		String strsql="select gameid from game";
        try{
        	ps=cn.prepareStatement(strsql);
        	rs=ps.executeQuery();
        	if(rs!=null)
        	{
        		while(rs.next())
        		{
        			
        			String id=rs.getString("gameid");
        			gameID.addItem(id);
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
	setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateMember.class.getResource("/images/images.jpeg")));
	setTitle("Update Member");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 455, 497);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setForeground(Color.LIGHT_GRAY);
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblMemberName = new JLabel("Member Name");
	lblMemberName.setBounds(48, 70, 102, 22);
	contentPane.add(lblMemberName);
	
	JLabel lblEmail = new JLabel("Email");
	lblEmail.setBounds(48, 103, 102, 22);
	contentPane.add(lblEmail);
	
	JLabel lblAddress = new JLabel("Address");
	lblAddress.setBounds(48, 136, 102, 22);
	contentPane.add(lblAddress);
	
	JLabel lblPhoneNo = new JLabel("Phone No.");
	lblPhoneNo.setBounds(48, 169, 102, 22);
	contentPane.add(lblPhoneNo);
	
	JLabel lblDob = new JLabel("DOB");
	lblDob.setBounds(48, 202, 102, 22);
	contentPane.add(lblDob);
	
	JLabel lblOccupation = new JLabel("Occupation");
	lblOccupation.setBounds(48, 235, 102, 22);
	contentPane.add(lblOccupation);
	
	JLabel lblPlanId = new JLabel("Plan ID");
	lblPlanId.setBounds(48, 268, 102, 22);
	contentPane.add(lblPlanId);
	
	JLabel lblDateOfMembership = new JLabel("Date Of Membership");
	lblDateOfMembership.setBounds(48, 301, 128, 22);
	contentPane.add(lblDateOfMembership);
	
	JLabel lblDateOfExpiry = new JLabel("Date Of Expiry");
	lblDateOfExpiry.setBounds(48, 334, 102, 22);
	contentPane.add(lblDateOfExpiry);
	
	JLabel lblGameId = new JLabel("Game ID");
	lblGameId.setBounds(48, 367, 102, 22);
	contentPane.add(lblGameId);
	
	btnUpdate = new JButton("Update");
	btnUpdate.setBounds(148, 421, 89, 23);
	btnUpdate.addActionListener(this);
	contentPane.add(btnUpdate);
	
	txtMemberName = new JTextField();
	txtMemberName.setBounds(226, 71, 153, 22);
	contentPane.add(txtMemberName);
	txtMemberName.setColumns(10);
	
	txtEmail = new JTextField();
	txtEmail.setBounds(226, 104, 153, 21);
	contentPane.add(txtEmail);
	txtEmail.setColumns(10);
	
	txtAdd = new JTextField();
	txtAdd.setBounds(226, 137, 153, 21);
	contentPane.add(txtAdd);
	txtAdd.setColumns(10);
	
	txtPh = new JTextField();
	txtPh.setBounds(226, 170, 153, 21);
	contentPane.add(txtPh);
	txtPh.setColumns(10);
	
	txtOccupation = new JTextField();
	txtOccupation.setBounds(226, 236, 153, 22);
	contentPane.add(txtOccupation);
	txtOccupation.setColumns(10);
	
	memberID = new JComboBox<String>();
	memberID.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Member-ID"}));
	memberID.setBounds(134, 26, 137, 21);
	memberID.addActionListener(this);
	contentPane.add(memberID);
	
	dob = new JDateChooser();
	dob.setBounds(226, 202, 153, 20);
	contentPane.add(dob);
	
	dom = new JDateChooser();
	dom.setBounds(226, 301, 153, 20);
	contentPane.add(dom);
	
	doe = new JDateChooser();
	doe.setBounds(226, 334, 153, 20);
	contentPane.add(doe);
	
	gameID = new JComboBox();
	gameID.setModel(new DefaultComboBoxModel(new String[] {"Select Game-ID"}));
	gameID.setBounds(226, 368, 153, 21);
	contentPane.add(gameID);
	
	planID = new JComboBox();
	planID.setModel(new DefaultComboBoxModel(new String[] {"Select Plan-ID"}));
	planID.setBounds(226, 269, 153, 20);
	contentPane.add(planID);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index=memberID.getSelectedIndex();
		
		if(index==0)
		{
			JOptionPane.showMessageDialog(this, "Please Select Valid Member-ID!!","Account",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
			String cid=(String)memberID.getSelectedItem();
			
			if(e.getSource()==memberID)
			{
				String strsql="select membername,email,address,phoneno,dob,occupation,planid,dateofmembership,dateofexpiry,gameid from memberdetail where memberid=?";
				try{
					ps=cn.prepareStatement(strsql);
					ps.setString(1, cid);
					rs=ps.executeQuery();
					if(rs.next())
					{
						String nm=rs.getString("membername");
						String em=rs.getString("email");
						String add=rs.getString("address");
						Long ph=rs.getLong("phoneno");
						Date db=rs.getDate("dob");
				     	String oc=rs.getString("occupation");
						String pi=rs.getString("planid");
						Date dm=rs.getDate("dateofmembership");
						Date de=rs.getDate("dateofexpiry");
						String gi=rs.getString("gameid");
						
						txtMemberName.setText(nm);
						txtEmail.setText(em);
						txtAdd.setText(add);
						txtPh.setText(String.valueOf(ph));
						dob.setDate(db);
						txtOccupation.setText(oc);
					    planID.setSelectedItem(pi);
						dom.setDate(dm);
						doe.setDate(de);
						gameID.setSelectedItem(gi);
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
					java.sql.Date dm=null;
					java.sql.Date db=null;
					java.sql.Date de=null;
					String nm=txtMemberName.getText().trim();
					String em=txtEmail.getText().trim();
					String add=txtAdd.getText().trim();
					String ph=txtPh.getText().trim();
		
					java.util.Date dbb=	dob.getDate();	
					if(dbb==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select a Valid Date!!","Member",JOptionPane.ERROR_MESSAGE);
					}
					else
					{long l=dbb.getTime();
					db=new java.sql.Date(l);
					}
								
					String oc=txtOccupation.getText().trim();
					String pi=(String) planID.getSelectedItem();

					java.util.Date dmm=	dom.getDate();		
					if(dmm==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select a Valid Date!!","Member",JOptionPane.ERROR_MESSAGE);
					}
					else
					{long l1=dmm.getTime();
					 dm=new java.sql.Date(l1);
					}
				    
					java.util.Date dee=	doe.getDate();
					
					if(dee==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select a Valid Date!!","Member",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						long l2=dee.getTime();
				   	de=new java.sql.Date(l2);

					}
					String gi=(String) gameID.getSelectedItem();
					if(nm.isEmpty()||em.isEmpty()||add.isEmpty()||ph.isEmpty()||oc.isEmpty()||planID.getSelectedIndex()==0||gameID.getSelectedIndex()==0)
					{
						JOptionPane.showMessageDialog(this, "Please Enter Proper Data!!","Member",JOptionPane.WARNING_MESSAGE);
					}
				
					else{
					String strupdate="update memberdetail set membername=?,email=?,address=?,phoneno=?,dob=?,occupation=?,planid=?,dateofmembership=?,dateofexpiry=?,gameid=? where memberid=?";
					try{
						ps=cn.prepareStatement(strupdate);
						ps.setString(1, txtMemberName.getText());
						ps.setString(2, txtEmail.getText());
						ps.setString(3, txtAdd.getText());
						ps.setLong(4, Long.parseLong(txtPh.getText()));
						ps.setDate(5, db);
						ps.setString(6, txtOccupation.getText());
						ps.setString(7, (String) planID.getSelectedItem());
						ps.setDate(8,  dm);
						ps.setDate(9, de);
						ps.setString(10, (String) gameID.getSelectedItem());
						ps.setString(11, cid);
						int rw=ps.executeUpdate();
						if(rw>0)
						{
							JOptionPane.showMessageDialog(this, "Member-Account Updated!!","Account",JOptionPane.INFORMATION_MESSAGE);
						
							memberID.setSelectedItem(null);
							txtMemberName.setText("");
							txtEmail.setText("");
							txtAdd.setText("");
							txtPh.setText(null);
							dob.setDate(null);
							txtOccupation.setText("");
							planID.setSelectedIndex(0);
							dom.setDate(null);
							doe.setDate(null);
							gameID.setSelectedIndex(0);
							
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
