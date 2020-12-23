package project.gui;

import java.awt.BorderLayout;
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
public class Clerk extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSignOut=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clerk frame = new Clerk();
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
	public Clerk() {
		createGui();
	}
	public void createGui()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clerk.class.getResource("/images/images.jpeg")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Clerk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMembers = new JMenu("Members");
		menuBar.add(mnMembers);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(this);
		mntmAdd.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnMembers.add(mntmAdd);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(this);
		mntmUpdate.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnMembers.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(this);
		mntmDelete.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnMembers.add(mntmDelete);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmPlanWiseMembers = new JMenuItem("Plan Wise Members");
		mntmPlanWiseMembers.addActionListener(this);
		mntmPlanWiseMembers.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnReports.add(mntmPlanWiseMembers);
		
		JMenuItem mntmGameWiseMembers = new JMenuItem("Game Wise Members");
		mntmGameWiseMembers.addActionListener(this);
		mntmGameWiseMembers.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnReports.add(mntmGameWiseMembers);
		
		JMenu mnMembership = new JMenu("Membership");
		menuBar.add(mnMembership);
		
		JMenuItem mntmExpiry = new JMenuItem("Expiry");
		mntmExpiry.addActionListener(this);
		mntmExpiry.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnMembership.add(mntmExpiry);
		
		JMenuItem mntmRenew = new JMenuItem("Renew");
		mntmRenew.addActionListener(this);
		mntmRenew.setIcon(new ImageIcon(Clerk.class.getResource("/images/Free_Lawn_Bowls_Icons_Vector_2 - Copy.jpg")));
		mnMembership.add(mntmRenew);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 102, 51));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Clerk.class.getResource("/images/sports-images-background-screen-shot-2012-02-17-at-pm-01.01.00-mRaTSz.jpg")));
		label.setBounds(39, 48, 1279, 427);
		contentPane.add(label);
		
		JLabel lblSportsHub = new JLabel("SPORTS HUB");
		lblSportsHub.setFont(new Font("AR DARLING", Font.PLAIN, 75));
		lblSportsHub.setBounds(711, 545, 490, 90);
		contentPane.add(lblSportsHub);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Clerk.class.getResource("/images/images.jpeg")));
		lblNewLabel.setBounds(1211, 543, 141, 126);
		contentPane.add(lblNewLabel);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.setBackground(new Color(255, 153, 0));
		btnSignOut.setBounds(39, 638, 89, 23);
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
		if(caption.equals("Add"))
		{
			AddMember am=new AddMember();
			am.setVisible(true);
		}
		if(caption.equals("Update"))
		{
			UpdateMember am=new UpdateMember();
			am.setVisible(true);
		}
		if(caption.equals("Delete"))
		{
			DeleteMember dm=new DeleteMember();
			dm.setVisible(true);
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
		if(caption.equals("Expiry"))
		{
			Membership m=new Membership();
			m.setVisible(true);
		}
		if(caption.equals("Renew"))
		{
			Renew r=new Renew();
			r.setVisible(true);
		}
		
		
	}
}
