package HeartGuide;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class NewRecord extends JFrame {

	private JPanel contentPane;
	private JTextField txtSystolic;
	private JTextField txtDiastolic;
	private JTextField txtClassification;
	private JTextField txtPulseRate;
	private String[] classification = {"Optimal", "Normal", "High Normal", "Grade 1 Hypertension", "Grade 2 Hypertension", "Grade 3 Hypertension"};
	private int sysFinding = 0, diaFinding = 0;
	
	private Dashboard dash = new Dashboard();
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
					NewRecord frame = new NewRecord();
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
	public NewRecord() {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("New Record");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 420);
		contentPane = new JPanel();
		contentPane.setVerifyInputWhenFocusTarget(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSchedule.setBounds(30, 82, 61, 14);
		contentPane.add(lblSchedule);
		
		JComboBox cmbxSchedule = new JComboBox();
		cmbxSchedule.setFocusable(false);
		cmbxSchedule.setBackground(Color.WHITE);
		cmbxSchedule.setBorder(new LineBorder(new Color(0, 0, 0)));
		cmbxSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbxSchedule.setModel(new DefaultComboBoxModel(new String[] {"Morning", "Evening"}));
		cmbxSchedule.setBounds(30, 107, 170, 32);
		contentPane.add(cmbxSchedule);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(218, 82, 36, 14);
		contentPane.add(lblDate);
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setDateFormatString("MMMMMMMM d, y");
		txtDate.setRequestFocusEnabled(false);
		txtDate.getCalendarButton().setOpaque(false);
		txtDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate.getCalendarButton().setFocusPainted(false);
		txtDate.getCalendarButton().setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDate.setOpaque(false);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate.setFocusable(false);
		txtDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDate.setBounds(218, 107, 192, 32);
		contentPane.add(txtDate);
		
		JLabel lblSystolic = new JLabel("Systolic Blood Pressure");
		lblSystolic.setToolTipText("The pressure when your heart pushes blood out");
		lblSystolic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSystolic.setBounds(30, 150, 149, 26);
		contentPane.add(lblSystolic);
		
		txtSystolic = new JTextField();
		txtSystolic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtDiastolic.getText().isEmpty() || txtSystolic.getText().isEmpty())
				{
					
				}else
				{
					double sys = Double.parseDouble(txtSystolic.getText());
					double dia = Double.parseDouble(txtDiastolic.getText());
					CompareSys(sys);
					CompareDia(dia);
					if(sysFinding > diaFinding)
					{
						txtClassification.setText(classification[sysFinding]);
					}else{
						txtClassification.setText(classification[diaFinding]);
					}
				}
			}
		});
		txtSystolic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSystolic.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtSystolic.setBounds(30, 187, 113, 32);
		contentPane.add(txtSystolic);
		
		JLabel lblDiastolic = new JLabel("Diastolic Blood Pressure");
		lblDiastolic.setToolTipText("The pressure when your heart rests between beats");
		lblDiastolic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiastolic.setBounds(218, 150, 149, 26);
		contentPane.add(lblDiastolic);
		
		txtDiastolic = new JTextField();
		txtDiastolic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtSystolic.getText().isEmpty() || txtDiastolic.getText().isEmpty())
				{
					
				}else
				{
					if(Integer.parseInt(txtDiastolic.getText()) > 0 || Integer.parseInt(txtDiastolic.getText()) < 8)
					{
						
					}
					double sys = Double.parseDouble(txtSystolic.getText());
					double dia = Double.parseDouble(txtDiastolic.getText());
					CompareSys(sys);
					CompareDia(dia);
					if(sysFinding > diaFinding)
					{
						txtClassification.setText(classification[sysFinding]);
					}else{
						txtClassification.setText(classification[diaFinding]);
					}
				}
			}
		});
		txtDiastolic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDiastolic.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtDiastolic.setBounds(218, 187, 113, 32);
		contentPane.add(txtDiastolic);
		
		JLabel lblSysMmhg = new JLabel("mmHg");
		lblSysMmhg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSysMmhg.setBounds(153, 193, 47, 26);
		contentPane.add(lblSysMmhg);
		
		JLabel lblDiaMmhg = new JLabel("mmHg");
		lblDiaMmhg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaMmhg.setBounds(341, 193, 47, 26);
		contentPane.add(lblDiaMmhg);
		
		JLabel lblPulseRate = new JLabel("Pulse Rate");
		lblPulseRate.setToolTipText("The pressure when your heart rests between beats");
		lblPulseRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPulseRate.setBounds(30, 230, 70, 26);
		contentPane.add(lblPulseRate);
		
		JLabel lblBPM = new JLabel("bpm");
		lblBPM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBPM.setBounds(153, 273, 36, 26);
		contentPane.add(lblBPM);
		
		JLabel lblClassification = new JLabel("Blood Pressure Classification");
		lblClassification.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClassification.setBounds(218, 230, 177, 26);
		contentPane.add(lblClassification);
		
		txtClassification = new JTextField();
		txtClassification.setEditable(false);
		txtClassification.setText("Please enter a value");
		txtClassification.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtClassification.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtClassification.setBounds(218, 267, 192, 32);
		contentPane.add(txtClassification);
		
		txtPulseRate = new JTextField();
		txtPulseRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPulseRate.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtPulseRate.setBounds(30, 267, 113, 32);
		contentPane.add(txtPulseRate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setFocusPainted(false);
		btnCancel.setBorder(null);
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(98, 333, 106, 26);
		contentPane.add(btnCancel);
		
		JButton btnRegister = new JButton("Save Record");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtClassification.getText().isEmpty() || txtClassification.getText().equals("Please enter a value") || txtDiastolic.getText().isEmpty() || txtSystolic.getText().isEmpty() || txtPulseRate.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Error! Please fill all fields.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Connection con = getConnection();
						PreparedStatement insert = con.prepareStatement("INSERT INTO user_record (account_id, record_date, schedule, systolic_bp, diastolic_bp, pulse_rate) VALUES (?,?,?,?,?,?)");
						insert.setString(1, String.valueOf(dash.getCurrentID()));
						String dateInString = String.valueOf(sdf.format(txtDate.getDate()));
						java.util.Date util_StartDate = sdf.parse(dateInString);
						java.sql.Date sql_StartDate = new java.sql.Date(util_StartDate.getTime());
						insert.setDate(2, sql_StartDate);
						insert.setString(3, String.valueOf(cmbxSchedule.getSelectedItem()));
						insert.setInt(4, Integer.valueOf(txtSystolic.getText()));
						insert.setInt(5, Integer.valueOf(txtDiastolic.getText()));
						insert.setInt(6, Integer.valueOf(txtPulseRate.getText()));
						insert.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Save success!", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setFocusPainted(false);
		btnRegister.setBorder(null);
		btnRegister.setBackground(new Color(210, 104, 110));
		btnRegister.setBounds(216, 333, 129, 26);
		contentPane.add(btnRegister);
		Date date = new Date();
		txtDate.setDate(date);
		
		JLabel lblTipIcon = new JLabel("");
		lblTipIcon.setBounds(20, 0, 80, 75);
		contentPane.add(lblTipIcon);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/tip.png"));
            Image red_logo = img.getScaledInstance(lblTipIcon.getWidth(), lblTipIcon.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblTipIcon.setIcon(new ImageIcon(red_logo));
            contentPane.add(lblTipIcon);
            
            JLabel lblTipTitle = new JLabel("Tip: Increase activity and exercise more");
            lblTipTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblTipTitle.setBounds(110, 25, 300, 17);
            contentPane.add(lblTipTitle);
            
            JLabel lblTipText = new JLabel("You can do it by simply going for a bike ride.");
            lblTipText.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblTipText.setBounds(110, 43, 300, 17);
            contentPane.add(lblTipText);
            } catch (Exception e) {
            System.out.println(e);
            }
		this.setLocationRelativeTo(null);
	}
	
	public void CompareSys(double x){
		if(x<=119)
		{
			sysFinding = 0;
		}else if(x==120 || x<=129){
			sysFinding = 1;
		}else if(x==130 || x<=139){
			sysFinding = 2;
		}else if(x==140 || x<=159){
			sysFinding = 3;
		}else if(x==160 || x<=179){
			sysFinding = 4;
		}else if(x>179){
			sysFinding = 5;
		}
	}
 
	public void CompareDia(double x){
		if(x<=79)
		{
			diaFinding = 0;
		}else if(x==80 || x<=84){
			diaFinding = 1;
		}else if(x==85 || x<=89){
			diaFinding = 2;
		}else if(x==90 || x<=99){
			diaFinding = 3;
		}else if(x==100 || x<=109){
			diaFinding = 4;
		}else if(x>109){
			diaFinding = 5;
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
