package project.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbInfo.CrudOperation;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GameWiseMembers extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Connection cn=null;
	private PreparedStatement ps=null;
	
	private ResultSet rs=null;
	JTable jt=null;
	JScrollPane jsp=null;
	private JButton btnView =null;
	private JComboBox<String> comboBox=null;
	String[]colname={"Member-ID","Member-Name","Email","Address","Phone-No","DOB","Occupation","Plan-ID","Date Of Membership","Date Of Expiry"};
	Object[][]data=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWiseMembers frame = new GameWiseMembers();
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
	public GameWiseMembers() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		createGui();		
        cn=CrudOperation.createConnection();
		populateCombo();
			}
	
	public void populateCombo() {
	// TODO Auto-generated method stub
	String strsql="select gameid from game";
    try{
    	ps=cn.prepareStatement(strsql);
    	rs=ps.executeQuery();
    	if(rs!=null)
    	{
    		while(rs.next())
    		{
    			
    			String gi=rs.getString("gameid");
    			comboBox.addItem(gi);
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
	setIconImage(Toolkit.getDefaultToolkit().getImage(GameWiseMembers.class.getResource("/images/images.jpeg")));
	setTitle("Game Wise Members");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 467, 472);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(153, 153, 255));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	comboBox = new JComboBox();
	comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Game-ID"}));
	comboBox.setBounds(554, 44, 119, 23);
	contentPane.add(comboBox);
	
	btnView = new JButton("View");
	btnView.setFont(new Font("Tahoma", Font.PLAIN, 13));
	btnView.setBounds(677, 44, 69, 23);
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
		String gi=(String) comboBox.getSelectedItem();
		String strcount="select count(*) from memberdetail where gameid=?";
		try{
			
			ps=cn.prepareStatement(strcount);
			ps.setString(1, gi);
			
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
		String strsql="select memberid,membername,email,address,phoneno,dob,occupation,planid,dateofmembership,dateofexpiry from memberdetail where gameid=?";
		try{
			
			ps=cn.prepareStatement(strsql);
			ps.setString(1, gi);
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
				data[row][7]=rs.getString("planid");
				data[row][8]=rs.getDate("dateofmembership");
				data[row][9]=rs.getDate("dateofexpiry");
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
