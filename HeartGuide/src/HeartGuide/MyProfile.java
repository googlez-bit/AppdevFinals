package HeartGuide;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JSeparator;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyProfile extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtFirst;
	private JTextField txtMiddle;
	private JTextField textField;
	private JTextField txtAddress;
	private JTextField txtHeight;
	private JTextField txtWeight;
	private JTextField txtUsername;
	private JPasswordField txtNewPass;
	private JPasswordField txtConfirmPass;
	private JDateChooser txtBirthdate;
	private JButton btnRegister;
	private boolean isUsernameValid = true;
	private JButton btnCancel;
	private int currentid = 0;
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
					MyProfile frame = new MyProfile();
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
	public MyProfile() {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("My Profile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/hg_logo_red.png"));
            contentPane.setLayout(null);
            } catch (Exception e) {
            System.out.println(e);
            }
        
        JLabel lblFirst = new JLabel("First Name");
        lblFirst.setBounds(31, 117, 70, 14);
        lblFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblFirst);
        
        txtFirst = new JTextField();
        txtFirst.setBounds(31, 142, 135, 32);
        txtFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFirst.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtFirst);
        
        JLabel lblMiddle = new JLabel("Middle Name");
        lblMiddle.setBounds(176, 117, 76, 14);
        lblMiddle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblMiddle);
        
        txtMiddle = new JTextField();
        txtMiddle.setBounds(176, 142, 135, 32);
        txtMiddle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMiddle.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtMiddle);
        
        JLabel lblLast = new JLabel("Last Name");
        lblLast.setBounds(321, 117, 76, 14);
        lblLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblLast);
        
        textField = new JTextField();
        textField.setBounds(321, 142, 135, 32);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(textField);
        
        JLabel lblBirthdate = new JLabel("Birthdate");
        lblBirthdate.setBounds(31, 185, 55, 14);
        lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblBirthdate);
        
        txtBirthdate = new JDateChooser();
        txtBirthdate.setDateFormatString("MMMMM d, yyyy");
        txtBirthdate.setBounds(31, 210, 215, 32);
        txtBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBirthdate.getCalendarButton().setOpaque(false);
        txtBirthdate.setOpaque(false);
        txtBirthdate.getCalendarButton().setBorder(new LineBorder(new Color(0, 0, 0)));
        txtBirthdate.getCalendarButton().setFocusPainted(false);
        txtBirthdate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBirthdate.setFocusable(false);
        txtBirthdate.setBorder(new LineBorder(new Color(0, 0, 0)));
        contentPane.add(txtBirthdate);
        
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(31, 253, 55, 14);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblAddress);
        
        txtAddress = new JTextField();
        txtAddress.setBounds(31, 278, 425, 32);
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtAddress.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtAddress);
        
        JLabel lblHeight = new JLabel("Height (cm)");
        lblHeight.setBounds(31, 321, 76, 17);
        lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblHeight);
        
        txtHeight = new JTextField();
        txtHeight.setBounds(31, 349, 135, 32);
        txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtHeight.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtHeight);
        
        JLabel lblWeight = new JLabel("Weight (kg)");
        lblWeight.setBounds(176, 321, 76, 17);
        lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblWeight);
        
        txtWeight = new JTextField();
        txtWeight.setBounds(176, 349, 135, 32);
        txtWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtWeight.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtWeight);
        
        JSeparator registerSeparator = new JSeparator();
        registerSeparator.setBounds(31, 407, 425, 2);
        registerSeparator.setForeground(Color.BLACK);
        registerSeparator.setBackground(Color.BLACK);
        contentPane.add(registerSeparator);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(31, 430, 70, 14);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(31, 455, 209, 32);
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtUsername);
        
        JLabel lblConfirmPass = new JLabel("Confirm New Password");
        lblConfirmPass.setBounds(244, 498, 140, 14);
        lblConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblConfirmPass);
        
        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(31, 523, 209, 32);
        txtNewPass.setText("Password");
        txtNewPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNewPass.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtNewPass);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.setBounds(121, 589, 106, 26);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(null);
        btnCancel.setBackground(Color.LIGHT_GRAY);
        contentPane.add(btnCancel);
        
        btnRegister = new JButton("Save Changes");
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setBounds(239, 589, 129, 26);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(null);
        btnRegister.setBackground(new Color(210, 104, 110));
        btnRegister.addActionListener(this);
        contentPane.add(btnRegister);
        
        JLabel lblNewPass = new JLabel("New Password");
        lblNewPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewPass.setBounds(30, 498, 98, 14);
        contentPane.add(lblNewPass);
        
        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setText("Password");
        txtConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtConfirmPass.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        txtConfirmPass.setBounds(247, 523, 209, 32);
        contentPane.add(txtConfirmPass);
        
        JLabel lblTipIcon = new JLabel("");
        lblTipIcon.setBounds(31, 18, 84, 81);
        contentPane.add(lblTipIcon);
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/profile.png"));
            Image red_logo = img.getScaledInstance(lblTipIcon.getWidth(), lblTipIcon.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblTipIcon.setIcon(new ImageIcon(red_logo));
            contentPane.add(lblTipIcon);
            } catch (Exception e) {
            System.out.println(e);
            }
        
        JLabel lblTipTitle = new JLabel("EDIT YOUR PROFILE");
        lblTipTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTipTitle.setBounds(133, 41, 300, 17);
        contentPane.add(lblTipTitle);
        
        JLabel lblTipText = new JLabel("Customize your personal details anytime");
        lblTipText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTipText.setBounds(133, 59, 300, 17);
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
			String user_first= "";
            String user_middle= "";
            String user_last= "";
            String user_address= "";
            String user_height="";
            String user_weight="";
            String bdate= "";
            String user="";
            String pass="";
            
            Connection con = getConnection();
            PreparedStatement select = con.prepareStatement("Select * from user_info where user_id = ?");
            select.setInt(1,  currentID);
            ResultSet rs= select.executeQuery();
            while (rs.next()) {  
                int user_id = rs.getInt(1);
                user_first = rs.getString(2);
                user_middle = rs.getString(3);
                user_last = rs.getString(4);
                user_address = rs.getString(5);
                user_height=rs.getString(6);
                user_weight=rs.getString(7);
                bdate = rs.getString(8);
                
              }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date ndate =dateFormat.parse(bdate); 
            
            txtFirst.setText(user_first);
        	txtMiddle.setText(user_middle);
        	textField.setText(user_last);
        	txtAddress.setText(user_address);
        	txtHeight.setText(user_height);
        	txtWeight.setText(user_weight);
        	txtBirthdate.setDate(ndate);
   	
        	 PreparedStatement select2 = con.prepareStatement("Select username, password from user_account where user_id = ?");
             select2.setInt(1,  currentID);
             ResultSet rs2= select2.executeQuery();
             while (rs2.next()) {  
                 user=rs2.getString("username");
                 pass=rs2.getString(2);        
               }
             txtUsername.setText(user);
             txtNewPass.setText(pass);
             txtConfirmPass.setText(pass);
    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegister){
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to update your information?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(confirm== JOptionPane.YES_OPTION)
			{
				try {
				double height = Double.valueOf(txtHeight.getText());
				double weight = Double.valueOf(txtWeight.getText());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String bdate =sdf.format(txtBirthdate.getDate());
				String password = new String(txtNewPass.getPassword());
				
				try {
					if(txtFirst.getText().equals("") || txtMiddle.getText().equals("") || textField.getText().equals("") || txtAddress.getText().equals("") || txtHeight.getText().equals("") || txtWeight.getText().equals("") || txtUsername.getText().equals("") || txtNewPass.getText().equals(""))
					{
						JOptionPane.showInternalMessageDialog(null, "Error! Please fill all fields.", "Error!", JOptionPane.ERROR_MESSAGE);
					}else
					{
						if(isUsernameValid == true)
						{
							Connection con = getConnection();
				            PreparedStatement insert = con.prepareStatement("Update user_info set user_first = ?, user_middle = ?, user_last = ?, user_address = ?, user_height = ?, user_weight = ?, user_birthdate = ? where user_id=?");
				            insert.setString(1, txtFirst.getText());
				            insert.setString(2, txtMiddle.getText());
				            insert.setString(3, textField.getText());
				            insert.setString(4, txtAddress.getText());
				            insert.setDouble(5, height);
				            insert.setDouble(6, weight);
				            insert.setString(7, bdate);
				            insert.setInt(8, currentid);
				            insert.executeUpdate();
				            PreparedStatement select = con.prepareStatement("Select MAX(user_id) from user_info");
				            ResultSet rs= select.executeQuery();
				            String uid = null;
				            while(rs.next()) {
				            uid=rs.getString("MAX(user_id)");
				            }
				            JOptionPane.showMessageDialog(this, "Updated Informatin Saved!", "Information!" ,JOptionPane.INFORMATION_MESSAGE);
				            if(txtNewPass.getText().equals(txtConfirmPass.getText()))
				            {
				            	PreparedStatement insert2 = con.prepareStatement(" Update user_account set username = ?, password = ? where user_id = ?");
					            insert2.setInt(3, currentid);
					            insert2.setString(1, txtUsername.getText());
					            insert2.setString(2, password );
					            insert2.executeUpdate();
				            }else
				            {
				            	JOptionPane.showMessageDialog(null, "Account Information Update Failed! Password doesn't match, Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				            }
				            
				            this.dispose();
						}
					}
		            
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        }
			}catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Please check if the inputted value is valid.", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			}
			
			
		}else if(e.getSource()== btnCancel ) {
			
			
		}else {
		
		}
		
	}
}
