package project.gui;

import java.sql.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dbInfo.CrudOperation;

import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddMember extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMemberID;
	private JTextField txtMemberName;
	private JTextField txtEmail;
	private JTextField txtAdd;
	private JTextField txtPh;
	private JTextField txtOccupation;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private JButton btnAdd=null;
	private JComboBox gameID=null;
	private JComboBox planID=null;
	private ResultSet rs=null;
	private JDateChooser dob=null;
	private JDateChooser doe=null;
	private JDateChooser dom=null;
	private java.sql.Date dbb=null;
	private java.sql.Date dmm=null;
	private java.sql.Date dee=null;
	private java.util.Date dm=null;
	private JButton btnFetch=null;
	private String pid=null;
	private String id=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
	createGui();	
	cn=CrudOperation.createConnection();
	populateComboPlanID();
	populateComboGameID();
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
    			
    			String ut=rs.getString("planid");
    			planID.addItem(ut);
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
	    			
	    			String ut=rs.getString("gameid");
	    			gameID.addItem(ut);
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
	setIconImage(Toolkit.getDefaultToolkit().getImage(AddMember.class.getResource("/images/images.jpeg")));
	setTitle("Add Member");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 499, 501);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setForeground(Color.LIGHT_GRAY);
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblMemberId = new JLabel("Member ID");
	lblMemberId.setBounds(48, 37, 102, 22);
	contentPane.add(lblMemberId);
	
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
	lblDateOfMembership.setBounds(48, 301, 131, 22);
	contentPane.add(lblDateOfMembership);
	
	JLabel lblDateOfExpiry = new JLabel("Date Of Expiry");
	lblDateOfExpiry.setBounds(48, 334, 102, 22);
	contentPane.add(lblDateOfExpiry);
	
	JLabel lblGameId = new JLabel("Game ID");
	lblGameId.setBounds(48, 367, 102, 22);
	contentPane.add(lblGameId);
	
	btnAdd = new JButton("Add");
	btnAdd.setBounds(168, 422, 89, 23);
	btnAdd.addActionListener(this);
	contentPane.add(btnAdd);
	
	txtMemberID = new JTextField();
	txtMemberID.setBounds(226, 38, 204, 22);
	contentPane.add(txtMemberID);
	txtMemberID.setColumns(10);
	
	txtMemberName = new JTextField();
	txtMemberName.setBounds(226, 71, 204, 22);
	contentPane.add(txtMemberName);
	txtMemberName.setColumns(10);
	
	txtEmail = new JTextField();
	txtEmail.setBounds(226, 104, 204, 21);
	contentPane.add(txtEmail);
	txtEmail.setColumns(10);
	
	txtAdd = new JTextField();
	txtAdd.setBounds(226, 137, 204, 21);
	contentPane.add(txtAdd);
	txtAdd.setColumns(10);
	
	txtPh = new JTextField();
	txtPh.setBounds(226, 170, 204, 21);
	contentPane.add(txtPh);
	txtPh.setColumns(10);
	
	txtOccupation = new JTextField();
	txtOccupation.setBounds(226, 236, 204, 22);
	contentPane.add(txtOccupation);
	txtOccupation.setColumns(10);
	
	dob = new JDateChooser();
	dob.setBounds(226, 202, 204, 20);
	contentPane.add(dob);
	
	dom = new JDateChooser();
	dom.setBounds(226, 301, 204, 20);
	contentPane.add(dom);
	
	planID = new JComboBox();
	planID.setModel(new DefaultComboBoxModel(new String[] {"Select Plan-ID"}));
	planID.setBounds(226, 269, 204, 21);
	contentPane.add(planID);
	
	gameID = new JComboBox();
	gameID.setModel(new DefaultComboBoxModel(new String[] {"Select Game-ID"}));
	gameID.setBounds(226, 368, 204, 21);
	contentPane.add(gameID);
	
	doe = new JDateChooser();
	doe.setBounds(226, 334, 121, 22);
	contentPane.add(doe);
	
	btnFetch = new JButton("Fetch");
	btnFetch.setBounds(357, 334, 73, 23);
	btnFetch.addActionListener(this);
	contentPane.add(btnFetch);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
	
	String cap=e.getActionCommand();

	id=txtMemberID.getText().trim();
	String nm=txtMemberName.getText().trim();
	String em=txtEmail.getText().trim();
	String add=txtAdd.getText().trim();
	String ph=txtPh.getText().trim();
	
	java.util.Date db=	dob.getDate();	
	if(db!=null)
	dbb=new java.sql.Date(db.getTime());
	
    String oc=txtOccupation.getText().trim();
	pid=(String)planID.getSelectedItem();

	dm=	dom.getDate();
	if(dm!=null)
	dmm=new java.sql.Date(dm.getTime());
						
	String gi=(String) gameID.getSelectedItem();

	
			if(cap.equals("Add"))
		{
						
			if(id.isEmpty()||nm.isEmpty()||add.isEmpty()||ph.isEmpty()||oc.isEmpty()||dbb.equals(null)||dee.equals(null)||planID.getSelectedIndex()==0||dmm.equals(null)||gameID.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "Please Enter Proper Data!!","Data",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				checkMemId();
				String strinsert="insert into memberdetail values(?,?,?,?,?,?,?,?,?,?,?)";
				try
				{
					ps=cn.prepareStatement(strinsert);
					ps.setString(1, id);
					ps.setString(2, nm);
					ps.setString(3, em);
					ps.setString(4, add);
					ps.setLong(5, Long.parseLong(ph));
					ps.setDate(6, dbb);
					ps.setString(7, oc);
					ps.setString(8, pid);
					ps.setDate(9, dmm);
					ps.setDate(10, dee);
					ps.setString(11, gi);
					int rw=ps.executeUpdate();//insert update delete query
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Member Added!!","Member",JOptionPane.INFORMATION_MESSAGE);
						txtMemberID.setText("");
						txtMemberName.setText("");
						txtEmail.setText("");
						txtAdd.setText("");
						txtPh.setText("");
						dob.setDate(null);
						txtOccupation.setText("");
						dom.setDate(null);
						doe.setDate(null);
						planID.setSelectedIndex(0);
						gameID.setSelectedIndex(0);
						

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
			if(cap.equals("Fetch"))
			{
				String strsql="select duration from plans where planid=?";
				try{
					String pid=(String) planID.getSelectedItem();
					ps=cn.prepareStatement(strsql);
					ps.setString(1, pid);
					rs=ps.executeQuery();
					if(rs.next())
					{
				
						int d=rs.getInt("duration");
						java.util.Date de=null;
						Calendar cal=Calendar.getInstance();
						cal.setTime(dm);
						cal.add(Calendar.DATE, d);
					    de=cal.getTime();
					    if(de!=null)
						dee=new java.sql.Date(de.getTime());
					    doe.setDate(dee);

						
						
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
public void checkMemId()
{
	int cnt=0;
	String strcount="select count(*) from memberdetail where memberid=?";
	try{
		
		ps=cn.prepareStatement(strcount);
		ps.setString(1, id);
		
		rs=ps.executeQuery();
		
		
		if(rs!=null &&rs.next())
		{
			
			cnt=	rs.getInt(1);
			if(cnt>=1)
				JOptionPane.showMessageDialog(this, "Already Assigned Member-ID: "+id+"\nPlease Enter Another!!","Member",JOptionPane.WARNING_MESSAGE);
			
			
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


