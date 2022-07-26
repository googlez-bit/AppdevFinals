package HeartGuide;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificationPM extends JFrame {

	private JPanel contentPane;
	private JLabel lblPMTime;
	private int currentID = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//NotificationPM frame = new NotificationPM();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NotificationPM(int id) {
		setUndecorated(true);
		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEvening = new JLabel("EVENING SCHEDULE");
		lblEvening.setBounds(120, 43, 129, 17);
		lblEvening.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblPMTime = new JLabel("7:00 PM");
		lblPMTime.setBounds(120, 60, 61, 17);
		lblPMTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAdd = new JButton("Add Record");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRecord newrecord = new NewRecord();
				newrecord.setVisible(true);
				dispose();
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBounds(120, 127, 114, 26);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(null);
		btnAdd.setBackground(new Color(210, 104, 110));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setBounds(244, 127, 86, 26);
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClose.setFocusPainted(false);
		btnClose.setBorder(null);
		btnClose.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblReminder = new JLabel("Kindly take your blood pressure now and record it in the system.");
		lblReminder.setBounds(120, 88, 395, 17);
		lblReminder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.setLayout(null);
		
		JPanel pnlAlarmTitle = new JPanel();
		pnlAlarmTitle.setBackground(new Color(210, 104, 110));
		pnlAlarmTitle.setBounds(0, 0, 534, 26);
		contentPane.add(pnlAlarmTitle);
		pnlAlarmTitle.setLayout(null);
		
		JLabel lblAlarmTitle = new JLabel("ALARM \u2022 now");
		lblAlarmTitle.setForeground(Color.WHITE);
		lblAlarmTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlarmTitle.setBounds(10, 0, 89, 26);
		pnlAlarmTitle.add(lblAlarmTitle);
		contentPane.add(lblEvening);
		contentPane.add(lblPMTime);
		contentPane.add(lblReminder);
		contentPane.add(btnClose);
		contentPane.add(btnAdd);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(22, 43, 77, 73);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/bell.png"));
            Image alarm = img.getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblIcon.setIcon(new ImageIcon(alarm));
            contentPane.add(lblIcon);
            } catch (Exception e) {
            System.out.println(e);
            }
		contentPane.add(lblIcon);
		display(id);
		this.setLocationRelativeTo(null);
	}
	public void display(int curID) {
		try {
            String PM="";
           
            Connection con = getConnection();
            PreparedStatement select = con.prepareStatement("SELECT afternoon_time FROM user_schedule WHERE user_id = ?");
            select.setInt(1,  curID);
            ResultSet rs= select.executeQuery();
            while (rs.next()) {
            	PM=rs.getString("afternoon_time");
              }
            String hr=Character.toString(PM.charAt(0)).concat(Character.toString(PM.charAt(1)));
            String mn=Character.toString(PM.charAt(3)).concat(Character.toString(PM.charAt(4)));
            int a= Integer. parseInt(hr);
            String s=String.valueOf(a);
           
            PM=s.concat(":".concat(mn.concat(" PM")));
            lblPMTime.setText(PM);
    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
	}
	public static Connection getConnection() throws Exception{
		   try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heart_guide","root","");//change password     
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return con;      
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }return null;
	   }
	
	public void setCurrentid(int id)
	{
		this.currentID = id;
		currentID = id;
	}
}
