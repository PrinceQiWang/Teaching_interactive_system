import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
	final JFrame jFrame = new JFrame("教学互动系统");
	private Component frame;
	String SaveValueL = null;
	String SaveValueS = null;
	String ID=null;
	
	public void LogIn(String UserID, String UserPW,String UserType) {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String RightPassword = null;
		String RightUserType = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "123456");
			sql = con.createStatement();
			rs= sql.executeQuery(" select UserPW,UserType from User " + "where UserID = '" + UserID + "'");
			if (rs.next()){
				RightUserType = rs.getString("UserType");
				RightPassword = rs.getString("UserPW");
				if (UserType.equals(RightUserType)){
					if (UserPW.equals(RightPassword)) {
						if (UserType == "0"){
							ID=UserID;
							JOptionPane.showMessageDialog(null, "学生登陆成功!","系统信息", JOptionPane.INFORMATION_MESSAGE);
							new Student(ID);
							jFrame.dispose();
						}
						else if(UserType == "1"){
							ID=UserID;
							JOptionPane.showMessageDialog(null, "教师登陆成功!","系统信息", JOptionPane.INFORMATION_MESSAGE);
							new Teacher(ID);
							jFrame.dispose();
						}
						else if(UserType == "2"){
							ID=UserID;
							JOptionPane.showMessageDialog(null, "管理员登陆成功!","系统信息", JOptionPane.INFORMATION_MESSAGE);
							new Manager(ID);
							jFrame.dispose();
						}	
					}
					else 
						JOptionPane.showMessageDialog(null, "密码错误!","系统信息", JOptionPane.ERROR_MESSAGE);
					}
				else 
					JOptionPane.showMessageDialog(null, "用户类型选择错误!","系统信息", JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "该用户不存在!","系统信息", JOptionPane.ERROR_MESSAGE);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void SignIn(String UserID, String UserPW,String RUserPW,String UserType) {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs_1;
		int rs_2;
		String ID = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs_1= sql.executeQuery(" select UserID from User " + "where UserID = '" + UserID + "'");
			if(rs_1.next()){
				JOptionPane.showMessageDialog(null, "该用户名已存在，请更换用户名!","系统信息", JOptionPane.ERROR_MESSAGE);
			}
			else{
				if (UserPW.equals(RUserPW)){
					rs_2 = sql.executeUpdate(" insert into User (UserID,UserPW,UserType)  " + "values ( '"+UserID+"','"+UserPW+"','"+UserType+"')");
					//INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
					JOptionPane.showMessageDialog(null, "注册成功!","系统信息", JOptionPane.INFORMATION_MESSAGE);
					}
					else 
						JOptionPane.showMessageDialog(null, "两次密码输入不一致!","系统信息", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Login(){
		
	    jFrame.setSize(800,600);
		jFrame.setResizable(false);//窗体固定大小
		jFrame.setLocationRelativeTo(null);//窗体出现在桌面中央
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    
        //////////////////////////////////////////////////////////////////////
		
	    ImageIcon icon_1=new ImageIcon("images/1.jpg");//加载图片
	    final JLabel jLabel_BackGround_1=new JLabel(icon_1);//将图片放入label
	    jLabel_BackGround_1.setBounds(0,0,icon_1.getIconWidth(),icon_1.getIconHeight());//设置label的大小
	    jFrame.getLayeredPane().add(jLabel_BackGround_1,new Integer(Integer.MIN_VALUE)); //获取窗口的第二层，并将label放入
	    JPanel jPanel_BackGround_1=(JPanel)jFrame.getContentPane(); //获取jFrame的顶层容器
	    jPanel_BackGround_1.setOpaque(false);//设置jFrame为透明
	    
	    ImageIcon icon_2=new ImageIcon("images/2.jpg");//加载图片
	    final JLabel jLabel_BackGround_2=new JLabel(icon_2);//将图片放入label
	    jLabel_BackGround_2.setBounds(0,0,icon_2.getIconWidth(),icon_2.getIconHeight());//设置label的大小
	    jFrame.getLayeredPane().add(jLabel_BackGround_2,new Integer(Integer.MIN_VALUE)); //获取窗口的第二层，并将label放入
	    JPanel jPanel_BackGround_2=(JPanel)jFrame.getContentPane(); //获取jFrame的顶层容器
	    jPanel_BackGround_2.setOpaque(false);//设置jFrame为透明
	    
	    //////////////////////////////////////////////////////////////////////
	    
		final JPanel jPanel_a=new JPanel();
		jPanel_a.setLayout(null);
		
		final JPanel jPanel_b=new JPanel();
		jPanel_b.setLayout(null);
		
		//////////////////////////////////////////////////////////////////////
		
		JLabel label_a_1 = new JLabel("账号：");
		label_a_1.setBounds(285, 300, 100, 30);
		jPanel_a.add(label_a_1);

		JLabel label_a_2 = new JLabel("密码：");
		label_a_2.setBounds(285, 340, 100, 30);
		jPanel_a.add(label_a_2);

		final JTextField text_field_a = new JTextField();
		text_field_a.setBounds(325, 300, 190, 30);
		jPanel_a.add(text_field_a);
		text_field_a.addKeyListener (new KeyListener (){//添加事件
			public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyChar=e.getKeyChar();
			if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {

			} else {
			e.consume(); 
			}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			}
		});
		

		final JPasswordField password_field_a = new JPasswordField();
		password_field_a.setBounds(325, 340, 190, 30);
		jPanel_a.add(password_field_a);
		
		final JRadioButton radio_button_a_1 = new JRadioButton("学生");
		radio_button_a_1.setBounds(285, 380, 80, 30);
		radio_button_a_1.setOpaque(false);//JRadioButton背景色透明
		jPanel_a.add(radio_button_a_1);
		radio_button_a_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton radio_button_a_1=(JRadioButton)e.getSource();
				if(radio_button_a_1.isSelected()){
					SaveValueL="0";
				}
			}
		});

		JRadioButton radio_button_a_2 = new JRadioButton("教师");
		radio_button_a_2.setBounds(365, 380, 80, 30);
		radio_button_a_2.setOpaque(false);//JRadioButton背景色透明
		jPanel_a.add(radio_button_a_2);
		radio_button_a_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton radio_button_a_2=(JRadioButton)e.getSource();
				if(radio_button_a_2.isSelected()){
					SaveValueL="1";
				}
			}
		});

		JRadioButton radio_button_a_3 = new JRadioButton("管理员");
		radio_button_a_3.setBounds(445, 380, 80, 30);
		radio_button_a_3.setOpaque(false);//JRadioButton背景色透明
		jPanel_a.add(radio_button_a_3);
		radio_button_a_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton radio_button_a_3=(JRadioButton)e.getSource();
				if(radio_button_a_3.isSelected()){
					SaveValueL="2";
				}
			}
		});
		
		final ButtonGroup bGroup_a = new ButtonGroup();//建立ButtonGroup实现3选1
		bGroup_a.add(radio_button_a_1);
		bGroup_a.add(radio_button_a_2);
		bGroup_a.add(radio_button_a_3);
		
		JButton button_a_1 = new JButton("登陆");
		button_a_1.setBounds(285, 420, 70, 30);
		jPanel_a.add(button_a_1);
		button_a_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(text_field_a.getText().trim().length()<1 || password_field_a.getText().trim().length()<1||bGroup_a.isSelected(null)){
					JOptionPane.showMessageDialog(null, "信息不全，无法登陆", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else{
					LogIn(text_field_a.getText(),password_field_a.getText(),SaveValueL);
				}
			}
		});

		JButton button_a_2 = new JButton("重置");
		button_a_2.setBounds(365, 420, 70, 30);
		jPanel_a.add(button_a_2);
		button_a_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text_field_a.setText("");
				password_field_a.setText("");	
				bGroup_a.clearSelection();//重置ButtonGroup
			}
		});
		
		JButton button_a_3 = new JButton("注册");
		button_a_3.setBounds(445, 420, 70, 30);
		jPanel_a.add(button_a_3);
		button_a_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel_a.setVisible(false);
				jFrame.add(jPanel_b);
				jPanel_b.setVisible(true);
				
				//↓切换页面自动重置输入框内容
				text_field_a.setText("");
				password_field_a.setText("");	
				bGroup_a.clearSelection();//重置ButtonGroup
				
				//↓切换页面背景图片
				jLabel_BackGround_1.setVisible(false);
				jLabel_BackGround_2.setVisible(true);
				
			}
			
		});
		
		jPanel_a.setVisible(true);
		jPanel_a.setOpaque(false);
		
		//////////////////////////////////////////////////////////////////////
	
		JLabel label_b_1 = new JLabel("注册类型：");
		label_b_1.setBounds(285, 180, 100, 30);
		jPanel_b.add(label_b_1);
		
		JLabel label_b_2 = new JLabel("设置账号：");
		label_b_2.setBounds(285, 220, 100, 30);
		jPanel_b.add(label_b_2);

		JLabel label_b_3 = new JLabel("设置密码：");
		label_b_3.setBounds(285, 260, 100, 30);
		jPanel_b.add(label_b_3);
		
		JLabel label_b_4 = new JLabel("确认密码：");
		label_b_4.setBounds(285, 300, 100, 30);
		jPanel_b.add(label_b_4);
		
		JRadioButton radio_button_b_1 = new JRadioButton("学生");
		radio_button_b_1.setBounds(365, 180, 80, 30);
		radio_button_b_1.setOpaque(false);//JRadioButton背景色透明
		jPanel_b.add(radio_button_b_1);
		radio_button_b_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton radio_button_b_1=(JRadioButton)e.getSource();
				if(radio_button_b_1.isSelected()){
					SaveValueS="0";
				}
			}
		});

		JRadioButton radio_button_b_2 = new JRadioButton("教师");
		radio_button_b_2.setBounds(445, 180, 80, 30);
		jPanel_b.add(radio_button_b_2);
		radio_button_b_2.setOpaque(false);//JRadioButton背景色透明
		radio_button_b_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton radio_button_b_2=(JRadioButton)e.getSource();
				if(radio_button_b_2.isSelected()){
					SaveValueS="1";
				}
			}
		});
		
		final ButtonGroup bGroup_b = new ButtonGroup();
		bGroup_b.add(radio_button_b_1);
		bGroup_b.add(radio_button_b_2);
		
		final JTextField text_field_b = new JTextField();
		text_field_b.setBounds(365, 220, 150, 30);
		jPanel_b.add(text_field_b);
		text_field_b.addKeyListener (new KeyListener (){//添加事件
			public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyChar=e.getKeyChar();
			if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {

			} else {
			e.consume(); 
			}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			}
		});

		final JPasswordField password_field_b_1 = new JPasswordField();
		password_field_b_1.setBounds(365, 260, 150, 30);
		jPanel_b.add(password_field_b_1);
		
		final JPasswordField password_field_b_2 = new JPasswordField();
		password_field_b_2.setBounds(365, 300, 150, 30);
		jPanel_b.add(password_field_b_2);
		
		JButton button_b_1 = new JButton("确定");
		button_b_1.setBounds(285, 340, 70, 30);
		jPanel_b.add(button_b_1);
		button_b_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(text_field_b.getText().trim().length()<1 || password_field_b_1.getText().trim().length()<1 || password_field_b_2.getText().trim().length()<1||bGroup_b.isSelected(null))
					JOptionPane.showMessageDialog(null, "信息不全，无法注册", "错误", JOptionPane.ERROR_MESSAGE); 
				else{
					SignIn(text_field_b.getText(),password_field_b_1.getText(),password_field_b_2.getText(),SaveValueS);
					//bGroup_b.clearSelection();//重置ButtonGroup
					//text_field_b.setText("");
					password_field_b_1.setText("");	
					password_field_b_2.setText("");
				}
			}
		});

		JButton button_b_2 = new JButton("重置");
		button_b_2.setBounds(365, 340, 70, 30);
		jPanel_b.add(button_b_2);
		button_b_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bGroup_b.clearSelection();//重置ButtonGroup
				text_field_b.setText("");
				password_field_b_1.setText("");	
				password_field_b_2.setText("");
			}
		});
		
		JButton button_b_3 = new JButton("返回");
		button_b_3.setBounds(445, 340, 70, 30);
		jPanel_b.add(button_b_3);
		button_b_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel_b.setVisible(false);
				jFrame.add(jPanel_a);
				jPanel_a.setVisible(true);
				
				//↓切换页面自动重置输入框内容
				bGroup_b.clearSelection();//重置ButtonGroup
				text_field_b.setText("");
				password_field_b_1.setText("");	
				password_field_b_2.setText("");
				
				//↓切换页面背景图片
				jLabel_BackGround_2.setVisible(false);
				jLabel_BackGround_1.setVisible(true);
			}
		});
		
		jPanel_b.setVisible(true);
		jPanel_b.setOpaque(false);
		
		//////////////////////////////////////////////////////////////////////
		
		//jFrame.add(jPanel_b);//后出现的JPanel覆盖上一个，此句可省略
		jFrame.add(jPanel_a);
		jFrame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new Login();
	}
	
}