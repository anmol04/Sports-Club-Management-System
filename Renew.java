package project.gui;

import java.awt.BorderLayout;

import java.sql.*;
import java.util.Calendar;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dbInfo.CrudOperation;

import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Renew extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
    private ResultSet rs=null;
    private JComboBox<String> memberID=null;
    private JComboBox<String> planID=null;
    private JButton btnRenew=null;
    private JButton btnClear=null;
    private JDateChooser doe=null;
    private JTextField txtDom;
    private String cid=null;
	private java.util.Date dm=null,dee=null;
	private java.sql.Date de=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Renew frame = new Renew();
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
	public Renew() {
	createGui();
	cn=CrudOperation.createConnection();
	populateComboMemberID();
	populateComboPlanID();
}
	
public void populateComboMemberID() {
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


	public void createGui()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Renew.class.getResource("/images/images.jpeg")));
		setTitle("Renew Membership");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 299);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		memberID = new JComboBox<String>();
		memberID.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Member-ID"}));
		memberID.setBounds(139, 24, 131, 26);
		memberID.addActionListener(this);
		contentPane.add(memberID);
		
		JLabel lblPlanId = new JLabel("Plan ID");
		lblPlanId.setBounds(50, 85, 109, 22);
		contentPane.add(lblPlanId);
		
		JLabel lblDateOfMembership = new JLabel("Date Of Membership");
		lblDateOfMembership.setBounds(50, 121, 136, 22);
		contentPane.add(lblDateOfMembership);
		
		JLabel lblDateOfExpiry = new JLabel("Date Of Expiry");
		lblDateOfExpiry.setBounds(50, 154, 109, 22);
		contentPane.add(lblDateOfExpiry);
		
		btnRenew = new JButton("Renew");
		btnRenew.setBounds(112, 213, 76, 22);
		btnRenew.addActionListener(this);
		contentPane.add(btnRenew);
		
		doe= new JDateChooser();
		doe.setBounds(208, 154, 143, 22);
		contentPane.add(doe);
		
		planID = new JComboBox<String>();
		planID.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Plan-ID"}));
		planID.setBounds(208, 85, 143, 22);
		contentPane.add(planID);
		
		txtDom = new JTextField();
		txtDom.setBounds(208, 121, 143, 22);
		contentPane.add(txtDom);
		txtDom.setColumns(10);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(this);
		btnClear.setBounds(219, 213, 76, 22);
		contentPane.add(btnClear);

	}

	public void updateData()
	{
        String pi=(String) planID.getSelectedItem();
        String dm1=txtDom.getText().trim();
		java.util.Date dee1=doe.getDate();
		if(dee1!=null)
		 de=new java.sql.Date(dee1.getTime());
 
		if(pi.isEmpty()||dm1.isEmpty()||de.equals(null))
		{
			JOptionPane.showMessageDialog(this, "Please Select/Enter Valid Data!!","Renew",JOptionPane.WARNING_MESSAGE);
		}
	
		else{
		String strupdate="update memberdetail set planid=?,dateofexpiry=? where memberid=?";
		try{
			ps=cn.prepareStatement(strupdate);
			ps.setString(1, pi);
			ps.setDate(2, de);
			ps.setString(3, cid);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "Membership Renewed!!");
				
			
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
		int index=memberID.getSelectedIndex();
		if(index==0)
		{
			JOptionPane.showMessageDialog(this, "Please Select Valid Member-ID!!","Member",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			cid=(String)memberID.getSelectedItem();
			if(e.getSource()==memberID)
			{
				String strsql="select planid,dateofmembership,dateofexpiry from memberdetail where memberid=?";
				try{
					ps=cn.prepareStatement(strsql);
					ps.setString(1, cid);
					rs=ps.executeQuery();
					
					if(rs.next())
					{
						String pi=rs.getString("planid");
					    dm=rs.getDate("dateofmembership");
						dee=rs.getDate("dateofexpiry");
					    planID.setSelectedItem(pi);
						txtDom.setText(String.valueOf(dm));
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
			if(e.getSource()==btnRenew)
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
						java.util.Date de1=null;
						Calendar cal=Calendar.getInstance();
						cal.setTime(dee);
						cal.add(Calendar.DATE, d);
					    de1=cal.getTime();
					    if(de1!=null)
						dee=new java.sql.Date(de1.getTime());
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


				updateData();
			}
		
				
					/*String strinsert="insert into renew values(?,?,?,?)";
					try{
						ps=cn.prepareStatement(strinsert);
						ps.setString(1, cid);
						ps.setString(2, pi);
						ps.setString(3, dm);
						ps.setDate(4, de);
						ps.executeUpdate();
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
		*/		
		
		if(e.getSource()==btnClear)
		{
			planID.setSelectedIndex(0);
			txtDom.setText("");
			doe.setDate(null);
			memberID.setSelectedItem(null);
		}
		}
		
		
	}
}

