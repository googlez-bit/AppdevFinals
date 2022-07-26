package HeartGuide;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.Font;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.Cursor;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblIcon;
	private JTextField txtUsername;
	private JLabel lblSignIn;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JCheckBox chckbxRemember;
	private boolean remember=false;

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
					Login frame = new Login();
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
	public Login() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblIcon = new JLabel("");
		lblIcon.setBounds(145, 22, 163, 76);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/hg_logo_red.png"));
            Image red_logo = img.getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblIcon.setIcon(new ImageIcon(red_logo));
            contentPane.add(lblIcon);
            } catch (Exception e) {
            System.out.println(e);
            }
		
		lblSignIn = new JLabel("SIGN IN TO YOUR ACCOUNT");
        lblSignIn.setBounds(115, 109, 227, 32);
        lblSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSignIn.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        contentPane.add(lblSignIn);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(115, 156, 226, 32);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setText("Username");
		txtUsername.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), 
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtUsername);
         
        txtPassword = new JPasswordField();
        txtPassword.setText("Password");
        txtPassword.setBounds(115, 199, 226, 32);
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(txtPassword);
         
        btnLogin = new JButton("Login");
        btnLogin.setBounds(231, 271, 110, 26);
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(210, 104, 110));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(null);
        btnLogin.addActionListener(this);
        contentPane.add(btnLogin);
         
        chckbxRemember = new JCheckBox("Remember me");
        chckbxRemember.setBorderPaintedFlat(true);
        chckbxRemember.setForeground(Color.BLACK);
        chckbxRemember.setBounds(113, 237, 111, 23);
        chckbxRemember.setRequestFocusEnabled(false);
        chckbxRemember.setOpaque(false);
        chckbxRemember.setFocusPainted(false);
        chckbxRemember.setFont(new Font("Tahoma", Font.PLAIN, 14));
        chckbxRemember.setBorder(new LineBorder(new Color(0, 0, 0)));
        chckbxRemember.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if(e.getStateChange()==ItemEvent.SELECTED) {
        			remember=true;
        		}else {
        			remember=false;		
        		}
        	}
        });
        contentPane.add(chckbxRemember);
         
        JLabel lblNewOnHG = new JLabel("New on Heart Guide?");
        lblNewOnHG.setBounds(115, 308, 120, 14);
        lblNewOnHG.setForeground(Color.BLACK);
        lblNewOnHG.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contentPane.add(lblNewOnHG);
         
        JLabel lblCreateAccount = new JLabel("Create an account!");
        lblCreateAccount.setBounds(241, 308, 109, 14);
        lblCreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCreateAccount.setForeground(new Color(210, 104, 110));
        contentPane.add(lblCreateAccount);
         
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(115, 271, 106, 26);
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(null);
        btnExit.setBackground(Color.LIGHT_GRAY);
        contentPane.add(btnExit);
		
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String password = new String(txtPassword.getPassword());
		String username = txtUsername.getText();
		//int x = 0;
		if(e.getSource() == btnLogin){
			try {
	            Connection con = getConnection();
	            PreparedStatement select = con.prepareStatement("Select account_id from user_account where username = ? and password = ?");
	            select.setString(1, username);
	            select.setString(2, password);
	            ResultSet rs= select.executeQuery();
	            int UID = 0;
	            int count = 0;
	            while (rs.next()) {  
	                UID=rs.getInt(1);
	              }
	            if(UID != 0) {
	            	Dashboard dashboard = new Dashboard();
	            	dashboard.setVisible(true);
	            	dashboard.setCurrentID(UID);
	            	this.dispose();
	            	if(remember == true) {
	            		PreparedStatement selectUser = con.prepareStatement("Select count(account_id) from remember_user where account_id = ?");
	            		selectUser.setInt(1, UID);
	            		ResultSet rs2 = select.executeQuery();
	    	            while (rs2.next()) {  
	    	                count = rs2.getInt(1);
	    	              }
		            		PreparedStatement insert = con.prepareStatement("INSERT INTO remember_user(account_id) VALUES (?)");
				            insert.setInt(1, UID);
				            insert.executeUpdate();
	            		
	    			}else {
	    				PreparedStatement insert = con.prepareStatement("Delete from remember_user where account_id = ?");
			            insert.setInt(1, UID);
			            insert.executeUpdate();
	    				
	    			}
	            	
	            }else {
	            	JOptionPane.showMessageDialog(null, "Username and password may be incorrect. Please try again.","Login Failed", JOptionPane.ERROR_MESSAGE);
	            }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
	            
	       
		}else {
		
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
}
