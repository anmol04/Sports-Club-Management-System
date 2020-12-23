package project.gui;

import java.awt.BorderLayout;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class PlanWiseMembers extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	private Connection cn=null;
	private PreparedStatement ps=null;
	
	private ResultSet rs=null;
	JTable jt=null;
	JScrollPane jsp=null;
	private JButton btnView =null;
	private JComboBox<String> comboBox=null;
	String[]colname={"Member-ID","Member-Name","Email","Address","Phone-No","DOB","Occupation","Date Of Membership","Date Of Expiry","Game-ID"};
	Object[][]data=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanWiseMembers frame = new PlanWiseMembers();
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
	public PlanWiseMembers() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
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
    			
    			String pi=rs.getString("planid");
    			comboBox.addItem(pi);
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlanWiseMembers.class.getResource("/images/images.jpeg")));
		setTitle("Plan Wise Members");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Plan-ID"}));
		comboBox.setBounds(526, 37, 121, 20);
		contentPane.add(comboBox);
		
		btnView = new JButton("View");
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnView.setBounds(657, 36, 81, 20);
		btnView.addActionListener(this);
		contentPane.add(btnView);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		fillData();
		jt=new JTable(data, colname);
		jsp=new JScrollPane(jt);
		jsp.setBounds(65, 150, 1250,400);
		contentPane.add(jsp);
	}
	public void fillData()
	{
		int rowcnt=0;
		String pi=(String) comboBox.getSelectedItem();
		String strcount="select count(*) from memberdetail where planid=?";
		try{
			
			ps=cn.prepareStatement(strcount);
			ps.setString(1, pi);
			
			rs=ps.executeQuery();
			
			
			if(rs!=null &&rs.next())
			{
				
				rowcnt=	rs.getInt(1);
				data=new Object[rowcnt][10];
				
				
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
			
			int row=0;
		String strsql="select memberid,membername,email,address,phoneno,dob,occupation,dateofmembership,dateofexpiry,gameid from memberdetail where planid=?";
		try{
			
			ps=cn.prepareStatement(strsql);
			ps.setString(1, pi);
			rs=ps.executeQuery();
			if(rs!=null)
			{
			while(rs.next())
			{
				
				data[row][0]=rs.getString("memberid");
				data[row][1]=rs.getString("membername");
				data[row][2]=rs.getString("email");
				data[row][3]=rs.getString("address");
				data[row][4]=rs.getLong("phoneno");
				data[row][5]=rs.getDate("dob");
				data[row][6]=rs.getString("occupation");
				data[row][7]=rs.getDate("dateofmembership");
				data[row][8]=rs.getDate("dateofexpiry");
				data[row][9]=rs.getString("gameid");
				row++;
				
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
}
