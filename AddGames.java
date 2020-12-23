package project.gui;

import java.awt.BorderLayout;

import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AddGames extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtGameID;
	private JTextField txtGameName;
	private JTextField txtCharges;
	private Connection cn=null;
	private PreparedStatement ps=null;
	private JButton btnAdd=null;
	private JLabel lblRs;
    private String id=null;
    private ResultSet rs=null;
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGames frame = new AddGames();
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
	public AddGames() {
		
	createGui();
	cn=CrudOperation.createConnection();
	}
	
	public void createGui()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddGames.class.getResource("/images/images.jpeg")));
		setTitle("Add Games");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 220);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGameId = new JLabel("Game ID");
		lblGameId.setBackground(Color.LIGHT_GRAY);
		lblGameId.setBounds(33, 31, 86, 20);
		contentPane.add(lblGameId);
		
		JLabel lblGameName = new JLabel("Game Name");
		lblGameName.setBounds(33, 62, 86, 20);
		contentPane.add(lblGameName);
		
		JLabel lblCharges = new JLabel("Charges");
		lblCharges.setBounds(33, 93, 86, 20);
		contentPane.add(lblCharges);
		
		txtGameID = new JTextField();
		txtGameID.setBounds(165, 31, 134, 20);
		contentPane.add(txtGameID);
		txtGameID.setColumns(10);
		
		txtGameName = new JTextField();
		txtGameName.setBounds(165, 62, 134, 20);
		contentPane.add(txtGameName);
		txtGameName.setColumns(10);
		
		txtCharges = new JTextField();
		txtCharges.setBounds(165, 93, 86, 20);
		contentPane.add(txtCharges);
		txtCharges.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(95, 147, 89, 23);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		lblRs = new JLabel("Rs.");
		lblRs.setBounds(261, 95, 49, 17);
		contentPane.add(lblRs);

	}
	
	public void checkGameId()
	{
		int cnt=0;
		String strcount="select count(*) from game where gameid=?";
		try{
			
			ps=cn.prepareStatement(strcount);
			ps.setString(1, id);
			
			rs=ps.executeQuery();
			
			
			if(rs!=null &&rs.next())
			{
				
				cnt=	rs.getInt(1);
				if(cnt>=1)
					JOptionPane.showMessageDialog(this, "Already Assigned Game-ID: "+id+"\nPlease Enter Another!!","Game",JOptionPane.WARNING_MESSAGE);
				
				
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

		id=txtGameID.getText().trim();
		String nm=txtGameName.getText().trim();
		String c=txtCharges.getText().trim();
		if(id.isEmpty()||nm.isEmpty()||c.length()==0)
		{
			JOptionPane.showMessageDialog(this, "Please Enter Data!!","Data",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			checkGameId();
			String strinsert="insert into game(gameid,gamename,charges) values(?,?,?)";
			try
			{
				ps=cn.prepareStatement(strinsert);
				ps.setString(1, id);
				ps.setString(2, nm);
				ps.setLong(3, Long.parseLong(c));
				int rw=ps.executeUpdate();//insert update delete query
				if(rw>0)
				{
					JOptionPane.showMessageDialog(this, "Game Added!!","Data",JOptionPane.INFORMATION_MESSAGE);
					txtGameID.setText("");
					txtGameName.setText("");
					txtCharges.setText("");
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


