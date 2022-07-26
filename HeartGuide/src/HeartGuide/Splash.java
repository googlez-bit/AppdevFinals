package HeartGuide;

import java.awt.EventQueue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.Color;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Splash extends JFrame {

	private JPanel contentPane;
	private JLabel lblTips;
	private Login showLogin = new Login();
	private int x = 0;
	private String[] tips = {"Tip: Make exercise a regular part of your life.", "Tip: Keep your diet in balance.", "Tip: Keep your blood pressure in check.", "Tip: Work on losing weight.", "Tip: Get enough regular sleep each night."};
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
					Splash splash = new Splash();
					splash.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Splash() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210,104,110));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHGLogo = new JLabel("");
		lblHGLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHGLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblHGLogo.setBounds(77, 56, 291, 157);
		lblHGLogo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/img/hg_logo_white.png"));
            Image white_logo = img.getScaledInstance(lblHGLogo.getWidth(), lblHGLogo.getHeight(), Image.SCALE_SMOOTH);
            contentPane.setLayout(null);
            lblHGLogo.setIcon(new ImageIcon(white_logo));
            contentPane.add(lblHGLogo);
            } catch (Exception e) {
            System.out.println(e);
            }
		
		lblTips = new JLabel("lblTips");
		lblTips.setVerticalAlignment(SwingConstants.TOP);
		lblTips.setHorizontalAlignment(SwingConstants.CENTER);
		lblTips.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTips.setForeground(Color.WHITE);
		lblTips.setBounds(10, 234, 430, 19);
		contentPane.add(lblTips);
		 
		startCount();
		this.setLocationRelativeTo(null);
	}
	
	public void startCount()
	{
		Random random = new Random();
		Timer timer = new Timer("Countdown");
		lblTips.setText("Tip: Keep your blood pressure in check");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	lblTips.setText(tips[random.nextInt(5)]);
            	if(x==3)
                {
            		dispose();
                	showLogin.setVisible(true);
                }
            	x++;
            }
        };
        timer.scheduleAtFixedRate(task, 0, 2000);
        
	}
}
