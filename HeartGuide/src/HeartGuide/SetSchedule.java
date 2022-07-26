package HeartGuide;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JSpinner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.SpinnerListModel;
import java.awt.Cursor;

public class SetSchedule extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSaveChanges;
	private boolean notify=false;
	private JSpinner spnrHrAM;
	private JSpinner spnrHrPM;
	private JSpinner spnrMinAM;
	private JSpinner spnrMinPM;
	private JCheckBox chkNotify;
	private int currentid;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetSchedule frame = new SetSchedule();
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
	public SetSchedule() {
		setResizable(false);
		setTitle("My Schedule");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMorning = new JLabel("MORNING SCHEDULE");
		lblMorning.setForeground(Color.BLACK);
		lblMorning.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMorning.setBounds(113, 95, 139, 17);
		contentPane.add(lblMorning);
		
		spnrHrAM = new JSpinner();
		spnrHrAM.setBorder(new LineBorder(new Color(0, 0, 0)));
		spnrHrAM.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spnrHrAM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrHrAM.setBounds(113, 123, 57, 34);
		contentPane.add(spnrHrAM);
		
		JLabel lblSeparateAM = new JLabel(":");
		lblSeparateAM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeparateAM.setBounds(180, 131, 5, 17);
		contentPane.add(lblSeparateAM);
		
		spnrMinAM = new JSpinner();
		spnrMinAM.setBorder(new LineBorder(new Color(0, 0, 0)));
		spnrMinAM.setModel(new SpinnerListModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		spnrMinAM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrMinAM.setBounds(195, 123, 57, 34);
		contentPane.add(spnrMinAM);
		
		JLabel lblAM = new JLabel("AM");
		lblAM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAM.setBounds(262, 128, 24, 23);
		contentPane.add(lblAM);
		
		JLabel lblEvening = new JLabel("EVENING SCHEDULE");
		lblEvening.setForeground(Color.BLACK);
		lblEvening.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEvening.setBounds(113, 168, 139, 17);
		contentPane.add(lblEvening);
		
		spnrHrPM = new JSpinner();
		spnrHrPM.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spnrHrPM.setBorder(new LineBorder(new Color(0, 0, 0)));
		spnrHrPM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrHrPM.setBounds(113, 196, 57, 34);
		contentPane.add(spnrHrPM);
		
		JLabel lblSeparatePM = new JLabel(":");
		lblSeparatePM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeparatePM.setBounds(180, 204, 5, 17);
		contentPane.add(lblSeparatePM);
		
		spnrMinPM = new JSpinner();
		spnrMinPM.setModel(new SpinnerListModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		spnrMinPM.setBorder(new LineBorder(new Color(0, 0, 0)));
		spnrMinPM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrMinPM.setBounds(195, 196, 57, 34);
		contentPane.add(spnrMinPM);
		
		JLabel lblPM = new JLabel("PM");
		lblPM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPM.setBounds(262, 201, 24, 23);
		contentPane.add(lblPM);
		
		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveChanges.setForeground(Color.WHITE);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveChanges.setFocusPainted(false);
		btnSaveChanges.setBorder(null);
		btnSaveChanges.setBackground(new Color(210, 104, 110));
		btnSaveChanges.setBounds(191, 289, 127, 26);
		btnSaveChanges.addActionListener(this);
		contentPane.add(btnSaveChanges);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setFocusPainted(false);
		btnCancel.setBorder(null);
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(74, 289, 106, 26);
		contentPane.add(btnCancel);
		
		chkNotify = new JCheckBox("Send me a notification");
		chkNotify.setRequestFocusEnabled(false);
		chkNotify.setOpaque(false);
		chkNotify.setForeground(Color.BLACK);
		chkNotify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkNotify.setFocusPainted(false);
		chkNotify.setBorderPaintedFlat(true);
		chkNotify.setBorder(new LineBorder(new Color(0, 0, 0)));
		chkNotify.setBounds(113, 241, 210, 23);
		chkNotify.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if(e.getStateChange()==ItemEvent.SELECTED) {
        			notify=true;
        		}else {
        			notify=false;		
        		}
        	}
        });
		contentPane.add(chkNotify);
		
		JLabel lblTipIcon = new JLabel("");
		lblTipIcon.setBounds(23, 11, 80, 75);
		contentPane.add(lblTipIcon);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/bell.png"));
            Image red_logo = img.getScaledInstance(lblTipIcon.getWidth(), lblTipIcon.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblTipIcon.setIcon(new ImageIcon(red_logo));
            contentPane.add(lblTipIcon);
            } catch (Exception e) {
            System.out.println(e);
            }
		
		JLabel lblTipTitle = new JLabel("SET YOUR SCHEDULE");
		lblTipTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipTitle.setBounds(113, 25, 300, 17);
		contentPane.add(lblTipTitle);
		
		JLabel lblTipText = new JLabel("You can turn on/off alarm anytime.");
		lblTipText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipText.setBounds(113, 43, 300, 17);
		contentPane.add(lblTipText);
		this.setLocationRelativeTo(null);
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
	
	public void display(int currentID) {
		this.currentid = currentID;
		try {
            String AM="";
            String PM="";
            String x="";
            Connection con = getConnection();
            PreparedStatement select = con.prepareStatement("Select morning_time, afternoon_time, time_notify from user_schedule where user_id = ?");
            select.setInt(1,  currentID);
            ResultSet rs= select.executeQuery();
            while (rs.next()) {  
            	AM=rs.getString(1);
            	PM=rs.getString(2);
            	x=rs.getString(3);   
              }
            
            String hr1=Character.toString(AM.charAt(0)).concat(Character.toString(AM.charAt(1)));
            String mn1=Character.toString(AM.charAt(3)).concat(Character.toString(AM.charAt(4)));
            Integer a= Integer. parseInt(hr1);
            spnrHrAM.setValue(a);
            spnrMinAM.setValue(mn1);
            String hr2=Character.toString(PM.charAt(0)).concat(Character.toString(PM.charAt(1)));
            String mn2=Character.toString(PM.charAt(3)).concat(Character.toString(PM.charAt(4)));
            Integer b= Integer. parseInt(hr2);
            spnrHrPM.setValue(b);
            spnrMinPM.setValue(mn2);
            
            if(x.equals("YES")) {	
            	chkNotify.setSelected(true);
            }else {          	
            	chkNotify.setSelected(false);
            }
            
    
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSaveChanges){
			try {
				String notif="";
				if(notify == true) {
					notif="YES";
				}else {
					notif="NO";
				}
				String minAm=spnrMinAM.getValue().toString();
				String hrAm=spnrHrAM.getValue().toString();
				String AM=hrAm.concat(":".concat(minAm));
				String minPm=spnrMinPM.getValue().toString();
				String hrPm=spnrHrPM.getValue().toString();
				String PM=hrPm.concat(":".concat(minPm));
				Connection con = getConnection();
				PreparedStatement select = con.prepareStatement("Select schedule_id from user_schedule where user_id = ?");
				select.setInt(1, currentid);
				ResultSet rs= select.executeQuery();
	            int sched_id=0;
	            while (rs.next()) {  
	            	sched_id=rs.getInt(1);
	              }
	            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure to set schedule?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	            if(confirmation == JOptionPane.YES_OPTION)
	            {
	            	if(sched_id == 0) {
		            	PreparedStatement insert = con.prepareStatement("INSERT into user_schedule(user_id, morning_time, afternoon_time, time_notify) VALUES (?, ?, ?, ?)");
			            insert.setInt(1,  1);
			            insert.setString(2, AM);
			            insert.setString(3, PM);
			            insert.setString(4, notif);
						insert.executeUpdate();
		            }else {
		            	PreparedStatement update = con.prepareStatement(" Update user_schedule set user_id = ?, morning_time = ? , afternoon_time = ? , time_notify = ? where schedule_id =?");
		            	update.setInt(1, 1);
		            	update.setString(2, AM);
		            	update.setString(3, PM);
		            	update.setString(4, notif);
		            	update.setInt(5, sched_id);
		            	update.executeUpdate();            	
		            }
	            	JOptionPane.showMessageDialog(this, "successfully set!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            	this.dispose();
	            }
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
	}
}
