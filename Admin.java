package project.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.border.LineBorder;



import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;

public class Admin extends JFrame implements ActionListener {

	
	private JPanel contentPane;
	private JButton btnSignOut=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		createGui();
		
	}
	public void createGui()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/images/images.jpeg")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Club Owner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem mntmCreate = new JMenuItem("Create");
		mntmCreate.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmCreate.addActionListener(this);
		mnAccount.add(mntmCreate);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmUpdate.addActionListener(this);
		mnAccount.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmDelete.addActionListener(this);
		mnAccount.add(mntmDelete);
		
		JMenu mnGames = new JMenu("Games");
		menuBar.add(mnGames);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmAdd.addActionListener(this);
		mnGames.add(mntmAdd);
		
		
		JMenu mnMembershipPlans = new JMenu("Membership Plans");
		menuBar.add(mnMembershipPlans);
		
		JMenuItem mntmAddMem = new JMenuItem("Add Plan");
		mntmAddMem.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmAddMem.addActionListener(this);
		mnMembershipPlans.add(mntmAddMem);
		
		JMenuItem mntmUpdateMem = new JMenuItem("Update Plan");
		mntmUpdateMem.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmUpdateMem.addActionListener(this);
		mnMembershipPlans.add(mntmUpdateMem);
		
		JMenuItem mntmDeleteMem = new JMenuItem("Delete Plan");
		mntmDeleteMem.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmDeleteMem.addActionListener(this);
		mnMembershipPlans.add(mntmDeleteMem);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmMembership = new JMenuItem("Membership");
		mntmMembership.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmMembership.addActionListener(this);
		mnReports.add(mntmMembership);
		
		JMenuItem mntmPlanWiseMembers = new JMenuItem("Plan Wise Members");
		mntmPlanWiseMembers.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmPlanWiseMembers.addActionListener(this);
		mnReports.add(mntmPlanWiseMembers);
		
		JMenuItem mntmGameWiseMembers = new JMenuItem("Game Wise Members");
		mntmGameWiseMembers.setIcon(new ImageIcon(Admin.class.getResource("/images/sports-vector-icons - Copy.jpg")));
		mntmGameWiseMembers.addActionListener(this);
		mnReports.add(mntmGameWiseMembers);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Admin.class.getResource("/images/FreeVector-Sports-Designs.jpg")));
		lblNewLabel_3.setBounds(86, 11, 1026, 662);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblS = new JLabel("S");
		lblS.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblS.setBounds(1241, 11, 54, 72);
		contentPane.add(lblS);
		
		JLabel lblP = new JLabel("P");
		lblP.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblP.setBounds(1241, 76, 54, 62);
		contentPane.add(lblP);
		
		JLabel lblO = new JLabel("O");
		lblO.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblO.setBounds(1241, 133, 54, 62);
		contentPane.add(lblO);
		
		JLabel lblR = new JLabel("R");
		lblR.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblR.setBounds(1241, 189, 54, 62);
		contentPane.add(lblR);
		
		JLabel lblT = new JLabel("T");
		lblT.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblT.setBounds(1241, 247, 54, 62);
		contentPane.add(lblT);
		
		JLabel lblS_1 = new JLabel("S");
		lblS_1.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblS_1.setBounds(1241, 301, 54, 71);
		contentPane.add(lblS_1);
		
		JLabel lblH = new JLabel("H");
		lblH.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblH.setBounds(1241, 383, 54, 71);
		contentPane.add(lblH);
		
		JLabel lblU = new JLabel("U");
		lblU.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblU.setBounds(1241, 438, 54, 71);
		contentPane.add(lblU);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("AR DARLING", Font.PLAIN, 70));
		lblB.setBounds(1241, 497, 54, 71);
		contentPane.add(lblB);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/images/images.jpeg")));
		lblNewLabel.setBounds(1194, 567, 146, 110);
		contentPane.add(lblNewLabel);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.setBackground(new Color(51, 153, 102));
		btnSignOut.setBounds(95, 638, 94, 23);
		btnSignOut.addActionListener(this);
		contentPane.add(btnSignOut);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String caption=e.getActionCommand();
		if(e.getSource()==btnSignOut)
		{
			Login l=new Login();
			l.setVisible(true);
			this.dispose();
		}
		if(caption.equals("Create"))
		{
			CreateAccountClerk cac=new CreateAccountClerk();
			cac.setVisible(true);
		}
		if(caption.equals("Delete"))
		{
			DeleteAccountClerk dac=new DeleteAccountClerk();
			dac.setVisible(true);
		}
		if(caption.equals("Update"))
		{
			UpdateAccountClerk uac=new UpdateAccountClerk();
			uac.setVisible(true);
		}
		if(caption.equals("Add"))
		{
			AddGames ag=new AddGames();
			ag.setVisible(true);
		}
		if(caption.equals("Add Plan"))
		{
			AddMembershipPlans amp=new AddMembershipPlans();
			amp.setVisible(true);
		}
		if(caption.equals("Update Plan"))
		{
			UpdateMembershipPlan ump=new UpdateMembershipPlan();
			ump.setVisible(true);
		}
		if(caption.equals("Delete Plan"))
		{
			DeleteMembershipPlan dmp=new DeleteMembershipPlan();
			dmp.setVisible(true);
		}
		if(caption.equals("Membership"))
		{
			Membership m=new Membership();
			m.setVisible(true);
		}
		if(caption.equals("Plan Wise Members"))
		{
			PlanWiseMembers pwm=new PlanWiseMembers();
			pwm.setVisible(true);
		}
		if(caption.equals("Game Wise Members"))
		{
			GameWiseMembers gwm=new GameWiseMembers();
			gwm.setVisible(true);
		}
		
	
		
	}
}
