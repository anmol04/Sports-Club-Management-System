package project.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Welcome extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {

		createGui();
	}
public void createGui()
{
	setExtendedState(Frame.MAXIMIZED_BOTH);
	setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/images/images.jpeg")));
	setTitle("Welcome");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(255, 204, 102));
	contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel label = new JLabel("SPORTS HUB");
	label.setFont(new Font("AR DARLING", Font.PLAIN, 75));
	label.setBounds(346, 11, 490, 90);
	contentPane.add(label);
	
	JLabel label_1 = new JLabel("New label");
	label_1.setIcon(new ImageIcon(Welcome.class.getResource("/images/images.jpeg")));
	label_1.setBounds(831, 11, 140, 110);
	contentPane.add(label_1);
	
	JButton btnLogin = new JButton("RUN");
	btnLogin.setBackground(new Color(255, 204, 0));
	btnLogin.setFont(new Font("AR BLANCA", Font.PLAIN, 22));
	btnLogin.setBounds(1211, 646, 122, 35);
	btnLogin.addActionListener(this);
	contentPane.add(btnLogin);
	
	JLabel lblNewLabel = new JLabel("New label");
	lblNewLabel.setIcon(new ImageIcon(Welcome.class.getResource("/images/vector-football-sport-jersey-illustration.jpg")));
	lblNewLabel.setBounds(26, 133, 383, 280);
	contentPane.add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("New label");
	lblNewLabel_1.setIcon(new ImageIcon(Welcome.class.getResource("/images/basketball-sports-jersey-vectors_2.jpg")));
	lblNewLabel_1.setBounds(346, 416, 383, 280);
	contentPane.add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("New label");
	lblNewLabel_2.setIcon(new ImageIcon(Welcome.class.getResource("/images/45944923-sports-on-a-white-background-illustration.jpg")));
	lblNewLabel_2.setBounds(443, 133, 450, 272);
	contentPane.add(lblNewLabel_2);
	
	JLabel lblWelcome = new JLabel("WELCOME");
	lblWelcome.setFont(new Font("Mistral", Font.PLAIN, 90));
	lblWelcome.setBounds(19, 445, 317, 90);
	contentPane.add(lblWelcome);
	
	JLabel lblNewLabel_3 = new JLabel("New label");
	lblNewLabel_3.setIcon(new ImageIcon(Welcome.class.getResource("/images/32972992-plan-to-playing-football-in-school.jpg")));
	lblNewLabel_3.setBounds(10, 530, 133, 166);
	contentPane.add(lblNewLabel_3);
	
	JLabel lblNewLabel_4 = new JLabel("New label");
	lblNewLabel_4.setIcon(new ImageIcon(Welcome.class.getResource("/images/hand_drawn_with_graffiti_sport_background_art_vector_546966.jpg")));
	lblNewLabel_4.setBounds(153, 530, 183, 166);
	contentPane.add(lblNewLabel_4);
	
	JLabel lblNewLabel_5 = new JLabel("New label");
	lblNewLabel_5.setIcon(new ImageIcon(Welcome.class.getResource("/images/sports-vector-icons.jpg")));
	lblNewLabel_5.setBounds(914, 125, 402, 296);
	contentPane.add(lblNewLabel_5);
	
	JLabel lblNewLabel_6 = new JLabel("New label");
	lblNewLabel_6.setIcon(new ImageIcon(Welcome.class.getResource("/images/hand_drawn_with_graffiti_sport_background_art_vector_546967.jpg")));
	lblNewLabel_6.setBounds(739, 427, 189, 217);
	contentPane.add(lblNewLabel_6);
	
	JLabel lblNewLabel_7 = new JLabel("New label");
	lblNewLabel_7.setIcon(new ImageIcon(Welcome.class.getResource("/images/hand_drawn_with_graffiti_sport_background_art_vector_546964.jpg")));
	lblNewLabel_7.setBounds(941, 427, 189, 217);
	contentPane.add(lblNewLabel_7);
	
	JLabel lblNewLabel_8 = new JLabel("New label");
	lblNewLabel_8.setIcon(new ImageIcon(Welcome.class.getResource("/images/hand_drawn_with_graffiti_sport_background_art_vector_546963.jpg")));
	lblNewLabel_8.setBounds(1140, 435, 193, 200);
	contentPane.add(lblNewLabel_8);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Login l=new Login();
		l.setVisible(true);
		this.dispose();
	}
}
