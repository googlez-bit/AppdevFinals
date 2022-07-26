package HeartGuide;

import java.awt.Component;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Register extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtFirst;
	private JTextField txtMiddle;
	private JTextField txtLast;
	private JTextField txtAddress;
	private JTextField txtHeight;
	private JTextField txtWeight;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JDateChooser txtBirthdate;
	private JButton btnRegister;
	private JLabel lblCreateAccount;
	private JButton btnCancel;
	private boolean isUsernameValid;

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
					Register frame = new Register();
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
	public Register() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(174, 22, 163, 76);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/hg_logo_red.png"));
            Image red_logo = img.getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblIcon.setIcon(new ImageIcon(red_logo));
            contentPane.add(lblIcon);
            } catch (Exception e) {
            System.out.println(e);
            }
		
		JLabel lblRegister = new JLabel("CREATE A HEART GUIDE ACCOUNT");
		lblRegister.setBounds(101, 109, 282, 32);
        lblRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRegister.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        contentPane.add(lblRegister);
        
        JLabel lblFirst = new JLabel("First Name");
        lblFirst.setBounds(31, 159, 70, 14);
        lblFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblFirst);
        
        txtFirst = new JTextField();
        txtFirst.setBounds(31, 184, 135, 32);
        txtFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFirst.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtFirst);
        
        JLabel lblMiddle = new JLabel("Middle Name");
        lblMiddle.setBounds(180, 159, 76, 14);
        lblMiddle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblMiddle);
        
        txtMiddle = new JTextField();
        txtMiddle.setBounds(180, 184, 135, 32);
        txtMiddle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMiddle.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtMiddle);
        
        JLabel lblLast = new JLabel("Last Name");
        lblLast.setBounds(327, 159, 76, 14);
        lblLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblLast);
        
        txtLast = new JTextField();
        txtLast.setBounds(327, 184, 135, 32);
        txtLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtLast.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtLast);
        
        JLabel lblBirthdate = new JLabel("Birthdate");
        lblBirthdate.setBounds(31, 227, 55, 14);
        lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblBirthdate);
        
        txtBirthdate = new JDateChooser();
        txtBirthdate.setDateFormatString("MMMMM d, yyyy");
        txtBirthdate.setBounds(31, 252, 215, 32);
        txtBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBirthdate.getCalendarButton().setOpaque(false);
        txtBirthdate.setOpaque(false);
        txtBirthdate.getCalendarButton().setBorder(new LineBorder(new Color(0, 0, 0)));
        txtBirthdate.getCalendarButton().setFocusPainted(false);
        txtBirthdate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBirthdate.setFocusable(false);
        txtBirthdate.setBorder(new LineBorder(new Color(0, 0, 0)));
        java.util.Date date = new java.util.Date();
        txtBirthdate.setDate(date);
        
        contentPane.add(txtBirthdate);
        
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(31, 295, 55, 14);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblAddress);
        
        txtAddress = new JTextField();
        txtAddress.setBounds(31, 320, 425, 32);
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtAddress.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtAddress);
        
        JLabel lblHeight = new JLabel("Height (cm)");
        lblHeight.setBounds(31, 363, 76, 17);
        lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblHeight);
        
        txtHeight = new JTextField();
        txtHeight.setBounds(31, 391, 135, 32);
        txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtHeight.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtHeight);
        
        JLabel lblWeight = new JLabel("Weight (kg)");
        lblWeight.setBounds(180, 363, 76, 17);
        lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblWeight);
        
        txtWeight = new JTextField();
        txtWeight.setBounds(180, 391, 135, 32);
        txtWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtWeight.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtWeight);
        
        JSeparator registerSeparator = new JSeparator();
        registerSeparator.setBounds(31, 442, 425, 2);
        registerSeparator.setForeground(Color.BLACK);
        registerSeparator.setBackground(Color.BLACK);
        contentPane.add(registerSeparator);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(31, 461, 70, 14);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(31, 486, 209, 32);
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtUsername);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(250, 461, 58, 14);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(txtPassword.getText().equals("") || (txtPassword.getText().equals("Password")))
        		{
        			txtPassword.setText("");
        			txtPassword.setEchoChar('•');
        		}
        		
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(txtPassword.getText().equals("") || (txtPassword.getText().equals("Password")))
        		{
        			txtPassword.setText("Password");
        			txtPassword.setEchoChar((char)0);
        		}
        	}
        });
        txtPassword.setEchoChar((char)0);
        txtPassword.setBounds(250, 486, 209, 32);
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtPassword);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.setBounds(128, 550, 106, 26);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(null);
        btnCancel.setBackground(Color.LIGHT_GRAY);
        contentPane.add(btnCancel);
        
        btnRegister = new JButton("Register");
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setBounds(245, 550, 110, 26);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(null);
        btnRegister.setBackground(new Color(210, 104, 110));
        btnRegister.addActionListener(this);
        contentPane.add(btnRegister);
        
        JLabel lblHaveHG = new JLabel("An existing user?");
        lblHaveHG.setBounds(128, 579, 97, 25);
        lblHaveHG.setForeground(Color.BLACK);
        lblHaveHG.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(lblHaveHG);
        
        lblCreateAccount = new JLabel("Sign in to your account!");
        lblCreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCreateAccount.setBounds(231, 579, 135, 25);
        lblCreateAccount.setForeground(new Color(210, 104, 110));
        lblCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(lblCreateAccount);
        
		
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegister){
			try {
				double height = Double.valueOf(txtHeight.getText());
				double weight = Double.valueOf(txtWeight.getText());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String bdate = sdf.format(txtBirthdate.getDate());
				String password = new String(txtPassword.getPassword());
				Integer.parseInt(txtHeight.getText());
				
				try {
					if(txtFirst.getText().equals("") || txtMiddle.getText().equals("") || txtLast.getText().equals("") || txtAddress.getText().equals("") || txtHeight.getText().equals("") || txtWeight.getText().equals("") || txtUsername.getText().equals("") || txtPassword.getText().equals(""))
					{
						JOptionPane.showInternalMessageDialog(null, "Error! Please fill all fields.", "Error!", JOptionPane.ERROR_MESSAGE);
					}else
					{
						checkUsername();
						if(isUsernameValid == true)
						{
							Connection con = getConnection();
				            PreparedStatement insert = con.prepareStatement("INSERT INTO user_info (user_first, user_middle, user_last, user_address, user_height, user_weight, user_birthdate) VALUES (?,?,?, ?,?,?,?)");
				            insert.setString(1, txtFirst.getText());
				            insert.setString(2, txtMiddle.getText());
				            insert.setString(3, txtLast.getText());
				            insert.setString(4, txtAddress.getText());
				            insert.setDouble(5, height);
				            insert.setDouble(6, weight);
				            insert.setString(7, bdate);
				            insert.executeUpdate();
				            PreparedStatement select = con.prepareStatement("Select MAX(user_id) from user_info");
				            ResultSet rs= select.executeQuery();
				            String uid = null;
				            while(rs.next()) {
				            uid=rs.getString("MAX(user_id)");
				            }
				            PreparedStatement insert2 = con.prepareStatement("INSERT INTO user_account (user_id, username, password) VALUES (?,?, ?)");
				            insert2.setString(1, uid);
				            insert2.setString(2, txtUsername.getText());
				            insert2.setString(3, password );
				            insert2.executeUpdate();
				            
				            JOptionPane.showMessageDialog(this, "Registration Success!", "Information!" ,JOptionPane.INFORMATION_MESSAGE);
				            txtAddress.setText("");
				            txtFirst.setText("");
				            txtHeight.setText("");
				            txtLast.setText("");
				            txtMiddle.setText("");
				            txtPassword.setText("Password");
				            txtPassword.setEchoChar((char)0);
				            txtUsername.setText("");
				            txtWeight.setText("");
						}
					}
		            
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        }
			}catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Please check if the inputted value is valid.", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource()== btnCancel ) {
			
			
		}else {
		
		}
	}
	
	public void checkUsername()
	{
		int x = 0;
		String userCheck = txtUsername.getText();
		try {
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heart_guide","root","falculan1234");
		      String query = "SELECT username FROM user_account";
		      Statement st = conn.createStatement();
		      ResultSet rs = st.executeQuery(query);
		      while (rs.next())
		      {
		    	  System.out.println(rs.getString("username"));
		    	if(rs.getString("username").equals(userCheck))
		    	{
		    		JOptionPane.showMessageDialog(null, "Sorry, this username is already taken.", "Error!", JOptionPane.ERROR_MESSAGE);
		    		isUsernameValid = false;
		    		txtUsername.requestFocus();
		    		System.out.println(rs.getString("username"));
		    		break;
		    	}else
		    	{
		    		System.out.println("From else" + rs.getString("username"));
		    		isUsernameValid = true;
		    	}
		      }
		      if(x==0)
		      {
		    	  isUsernameValid = true;
		      }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception{
		   try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heart_guide","root","falculan1234");//change password     
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return con;      
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }return null;
	   }
}
