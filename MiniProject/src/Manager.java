import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Manager extends JFrame {
	
	String[] columnNames_b = {"账号","密码"};//表格列名
	String [][]tableVales_b={};//数据
	
	String[] columnNames_c = {"账号","密码"};//表格列名
	String [][]tableVales_c={};//数据    
	
	final DefaultTableModel defaultTableModel_b = new DefaultTableModel(tableVales_b,columnNames_b);
	final JTable jTable_b = new JTable(defaultTableModel_b);
	
	final DefaultTableModel defaultTableModel_c = new DefaultTableModel(tableVales_c,columnNames_c);
	final JTable jTable_c = new JTable(defaultTableModel_c);
	
	final JTextField jTextField_b_1 = new JTextField();
	final JTextField jTextField_b_2 = new JTextField();
	
	final JTextField jTextField_c_1 = new JTextField();
	final JTextField jTextField_c_2 = new JTextField();
	
	//int flageS=0;
	//int flageT=0;
	
	final String SaveValueS = "0";
	final String SaveValueT = "1";
	final String SaveValueM = "2";
	
	String IDS=null;
	String IDT=null;
	String ID=null;
	
	
	public void Export_Student() {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String UserID = null;
		String UserPW = null;
		String UserType = null;
		int UserTP;
		try {
			int j=defaultTableModel_b.getRowCount();
			//System.out.println(j);
			for(int i=0;i<j;i++)
				defaultTableModel_b.removeRow(0);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs= sql.executeQuery(" select UserID,UserPW,UserType from User ");
			while(rs.next()){
					UserID = rs.getString("UserID");
					UserPW = rs.getString("UserPW");
					UserType=rs.getString("UserType");
					UserTP=Integer.parseInt(UserType);
					if(UserTP==0){
					String []rowValues = {UserID,UserPW};
					defaultTableModel_b.addRow(rowValues);  //添加一行
					}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Add_Student(String UserID, String UserPW) {
		Connection con;//
		java.sql.Statement sql;
		int rs;
		String []rowValues = {jTextField_b_1.getText(),jTextField_b_2.getText()};
		defaultTableModel_b.addRow(rowValues);  //添加一行
		String UserType=SaveValueS;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs = sql.executeUpdate(" insert into User (UserID,UserPW,UserType)  " + "values ( '"+UserID+"','"+UserPW+"','"+UserType+"')");
//			INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update_Student(int selectedRow,String UserID, String UserPW){
		Connection con;//
		java.sql.Statement sql;
		String UserType=SaveValueS;
		defaultTableModel_b.setValueAt(jTextField_b_1.getText(), selectedRow, 0);//将表格中的值修改为输入框中的值
		defaultTableModel_b.setValueAt(jTextField_b_2.getText(), selectedRow, 1);//将表格中的值修改为输入框中的值
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			sql.execute("update User set UserID='"+UserID+"',UserPW='"+UserPW+"',UserType='"+UserType+"' "+" where UserID='"+IDS+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Delete_Student(){
		Connection con;//
		java.sql.Statement sql;
		int rs;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs = sql.executeUpdate("  delete from User  where UserID = '" + IDS +"'");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void Export_Teacher() {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String UserID = null;
		String UserPW = null;
		String UserType = null;
		int UserTP;
		try {
			int j=defaultTableModel_c.getRowCount();
			//System.out.println(j);
			for(int i=0;i<j;i++)
				defaultTableModel_c.removeRow(0);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs= sql.executeQuery(" select UserID,UserPW,UserType from User ");
			while(rs.next()){
					UserID = rs.getString("UserID");
					UserPW = rs.getString("UserPW");
					UserType=rs.getString("UserType");
					UserTP=Integer.parseInt(UserType);
					if(UserTP==1){
					String []rowValues = {UserID,UserPW};
					defaultTableModel_c.addRow(rowValues);  //添加一行
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Add_Teacher(String UserID, String UserPW) {
		Connection con;//
		java.sql.Statement sql;
		int rs;
		String[]rowValues_c = {jTextField_c_1.getText(),jTextField_c_2.getText()};
		defaultTableModel_c.addRow(rowValues_c);  //添加一行
		String UserType=SaveValueT;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs = sql.executeUpdate(" insert into User (UserID,UserPW,UserType)  " + "values ( '"+UserID+"','"+UserPW+"','"+UserType+"')");
//			INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update_Teacher(int selectedRow,String UserID, String UserPW){
		Connection con;//
		java.sql.Statement sql;
		String UserType=SaveValueT;
		defaultTableModel_c.setValueAt(jTextField_c_1.getText(), selectedRow, 0);//将表格中的值修改为输入框中的值
		defaultTableModel_c.setValueAt(jTextField_c_2.getText(), selectedRow, 1);//将表格中的值修改为输入框中的值
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			sql.execute("update User set UserID='"+UserID+"',UserPW='"+UserPW+"',UserType='"+UserType+"' "+" where UserID='"+IDT+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Delete_Teacher(){
		Connection con;//
		java.sql.Statement sql;
		int rs;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs = sql.executeUpdate("  delete from User  where UserID = '" + IDT +"'");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void Change_PW(String OriginalPW, String UserPW,String RUserPW) {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String RightPassword = null;
		String UserType=SaveValueM;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs= sql.executeQuery(" select UserPW from User " + "where UserID = '"+ID+"'");
			if (rs.next()){
				RightPassword = rs.getString("UserPW");
				if (OriginalPW.equals(RightPassword)){
					if (UserPW.equals(RUserPW)){
						sql.execute("update User set UserPW='"+UserPW+"' " + "where UserID = '"+ID+"'");
						JOptionPane.showMessageDialog(null, "密码修改成功!","系统信息", JOptionPane.INFORMATION_MESSAGE);
					}
					else 
						JOptionPane.showMessageDialog(null, "两次密码输入不一致!","系统信息", JOptionPane.ERROR_MESSAGE);
					}
				else 
					JOptionPane.showMessageDialog(null, "密码错误!","系统信息", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Manager(String IDM){
		
		ID=IDM;
		
	    final JFrame jFrame = new JFrame("教学互动系统");
	    jFrame.setSize(800,600);
		jFrame.setResizable(false);//窗体固定大小
		jFrame.setLocationRelativeTo(null);//窗体出现在桌面中央
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    
        //////////////////////////////////////////////////////////////////////
		
	    ImageIcon icon_1=new ImageIcon("images/0.jpg");//加载图片
	    final JLabel jLabel_BackGround_1=new JLabel(icon_1);//将图片放入label
	    jLabel_BackGround_1.setBounds(0,0,icon_1.getIconWidth(),icon_1.getIconHeight());//设置label的大小
	    jFrame.getLayeredPane().add(jLabel_BackGround_1,new Integer(Integer.MIN_VALUE)); //获取窗口的第二层，并将label放入
	    JPanel jPanel_BackGround_1=(JPanel)jFrame.getContentPane(); //获取jFrame的顶层容器
	    jPanel_BackGround_1.setOpaque(false);//设置jFrame为透明
	    
	    //////////////////////////////////////////////////////////////////////
	    
	    //第一个标签下的JPanel
    	JPanel jPanel_a = new JPanel();
    	jPanel_a.setLayout(null);
    	JTabbedPane jTabbedPane = new JTabbedPane();
    	jTabbedPane.addTab("欢迎",null,jPanel_a,null);//加入第一个页面
    	jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
            	JTabbedPane jTabbedPane=(JTabbedPane) e.getSource();
            	 int selectedIndex = jTabbedPane.getSelectedIndex();
            	    switch (selectedIndex) {
            	    	case 1:{
            	    		//if(flageS!=1){
            	    			Export_Student();
            	    		//	flageS=1;
            	    		//}
            	    		jTextField_b_1.setText("");
            	    		jTextField_b_2.setText("");
            	    	}
            	    	break;
            	    	case 2:{
            	    		//if(flageT!=1){
            	    			Export_Teacher();
            	    		//	flageT=1;
            	    		//}
            	    		jTextField_c_1.setText("");
            	    		jTextField_c_2.setText("");
            	    }
            	     break;
            	    }
            }
        });
    	//JLabel label_a_1 = new JLabel("标签1的内容");
		//label_a_1.setBounds(270, 200, 100, 30);
		//jPanel_a.add(label_a_1);
		
		//////////////////////////////////////////////////////////////////////
    	
		//第二个标签下的JPanel
    	JPanel jPanel_b = new JPanel();
    	jPanel_b.setLayout(null);
    	jTabbedPane.addTab("学生账户管理",null,jPanel_b,null);//加入第一个页面
    	
    	//JLabel label_b_1 = new JLabel("标签2的内容");
		//label_b_1.setBounds(270, 200, 100, 30);
		//jPanel_b.add(label_b_1);
    	
    	//////////////////////////////////////////////////////////////////////
    	
    	//第三个标签下的JPanel
    	JPanel jPanel_c = new JPanel();
    	jPanel_c.setLayout(null);
    	jTabbedPane.addTab("教师账户管理", null, jPanel_c, null);//加入第一个页面
    	
    	//JLabel label_c_1 = new JLabel("标签3的内容");
		//label_c_1.setBounds(270, 200, 100, 30);
		//jPanel_c.add(label_c_1);
    	
    	//////////////////////////////////////////////////////////////////////
	    
    	//第四个标签下的JPanel
    	final JPanel jPanel_d = new JPanel();
    	jPanel_d.setLayout(null);
    	jTabbedPane.addTab("密码安全",null,jPanel_d,null);//加入第一个页面

    	//JLabel label_d_1 = new JLabel("标签4的内容");
    	//label_d_1.setBounds(270, 200, 100, 30);
    	//jPanel_d.add(label_d_1);

    	//////////////////////////////////////////////////////////////////////
    	//111111111111111111111111111111111111111111111111111111111111111111//
    	//////////////////////////////////////////////////////////////////////

		JButton button_a_1 = new JButton("用户登出");
		button_a_1.setBounds(330, 400, 140, 30);
		jPanel_a.add(button_a_1);
		button_a_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jFrame.dispose();
				new Login();
				}
		});

		//////////////////////////////////////////////////////////////////////
		//111111111111111111111111111111111111111111111111111111111111111111//
		//////////////////////////////////////////////////////////////////////
    	
	    //////////////////////////////////////////////////////////////////////
    	//222222222222222222222222222222222222222222222222222222222222222222//
		//////////////////////////////////////////////////////////////////////
		
		JLabel Label_b_1= new JLabel("账号：");
		Label_b_1.setBounds(200, 360, 100, 30);
		jPanel_b.add(Label_b_1);
		
		JLabel Label_b_2 = new JLabel("密码：");
		Label_b_2.setBounds(400, 360, 100, 30);
		jPanel_b.add(Label_b_2);
		
        jTextField_b_1.setBounds(240, 360, 150, 30);
		jPanel_b.add(jTextField_b_1);
		jTextField_b_1.addKeyListener (new KeyListener (){//添加事件
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
		
		jTextField_b_2.setBounds(440, 360, 150, 30);
		jPanel_b.add(jTextField_b_2);

	    //////////////////////////////////////////////////////////////////////
	    
		JScrollPane scrollPane_b = new JScrollPane(jTable_b);//添加滚动条    
		getContentPane().add(scrollPane_b,BorderLayout.CENTER);
	            
		jTable_b.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//表格中可进行单选    
		jTable_b.addMouseListener(new MouseAdapter(){//添加鼠标事件        
			public void mouseClicked(MouseEvent e){    
				int selectedRow_b = jTable_b.getSelectedRow();//获得选中行索引  
				Object ob_1 = defaultTableModel_b.getValueAt(selectedRow_b, 0);   
				Object ob_2 = defaultTableModel_b.getValueAt(selectedRow_b, 1);
				jTextField_b_1.setText(ob_1.toString());//给文本框赋值 
				jTextField_b_2.setText(ob_2.toString());
				IDS=ob_1.toString();
	            }
	        });
	        
		scrollPane_b.setBounds(200, 50, 400, 300);
		scrollPane_b.setOpaque(false);
		jPanel_b.add(scrollPane_b);

		//////////////////////////////////////////////////////////////////////
		
		final JButton addButton_b = new JButton("添加");//按钮"添加"
		addButton_b.setBounds(275, 400, 70, 30);
		jPanel_b.add(addButton_b);
		addButton_b.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				if(jTextField_b_1.getText().trim().length()<1 && jTextField_b_2.getText().trim().length()>1){
					JOptionPane.showMessageDialog(null, "尚未设置账号，无法添加用户", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else if(jTextField_b_1.getText().trim().length()>1 && jTextField_b_2.getText().trim().length()<1){
					JOptionPane.showMessageDialog(null, "尚未设置密码，无法添加用户", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else if(jTextField_b_1.getText().trim().length()<1 && jTextField_b_2.getText().trim().length()<1){
					JOptionPane.showMessageDialog(null, "尚未设置账号和密码，无法添加用户", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else{
					Add_Student(jTextField_b_1.getText(),jTextField_b_2.getText());
					jTextField_b_1.setText("");
					jTextField_b_2.setText("");
					}
				}
			});
		  	
		final JButton updateButton_b = new JButton("修改");//按钮"修改"
		updateButton_b.setBounds(375, 400, 70, 30);
		jPanel_b.add(updateButton_b);
		updateButton_b.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_b.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_b_1.getText().trim().length()<1 && jTextField_b_2.getText().trim().length()>1){
						JOptionPane.showMessageDialog(null, "未设置新的账号，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else if(jTextField_b_1.getText().trim().length()>1 && jTextField_b_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "未设置新的密码，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else if(jTextField_b_1.getText().trim().length()<1 && jTextField_b_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "未设置新的账号和密码，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						Update_Student(selectedRow,jTextField_b_1.getText(),jTextField_b_2.getText());
						jTextField_b_1.setText("");
						jTextField_b_2.setText("");
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});
		  
		final JButton delButton_b = new JButton("删除");//按钮"删除"
		delButton_b.setBounds(475, 400, 70, 30);
		jPanel_b.add(delButton_b);
		delButton_b.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_b.getSelectedRow();//获得选中行的索引
				if(selectedRow!=-1)//判断是否存在选中行
					{
					defaultTableModel_b.removeRow(selectedRow);//删除选中行
					Delete_Student();
					jTextField_b_1.setText("");
					jTextField_b_2.setText("");
	                }
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行删除", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});
		
		//////////////////////////////////////////////////////////////////////
		//222222222222222222222222222222222222222222222222222222222222222222//
		//////////////////////////////////////////////////////////////////////
		

		//////////////////////////////////////////////////////////////////////
		//333333333333333333333333333333333333333333333333333333333333333333//
		//////////////////////////////////////////////////////////////////////
		
		JLabel jLabel_c_1= new JLabel("账号：");
		jLabel_c_1.setBounds(200, 360, 100, 30);
		jPanel_c.add(jLabel_c_1);
		
		JLabel Label_c_2 = new JLabel("密码：");
		Label_c_2.setBounds(400, 360, 100, 30);
		jPanel_c.add(Label_c_2);
		
		jTextField_c_1.setBounds(240, 360, 150, 30);
		jPanel_c.add(jTextField_c_1);
		jTextField_c_1.addKeyListener (new KeyListener (){//添加事件
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
		
		jTextField_c_2.setBounds(440, 360, 150, 30);
		jPanel_c.add(jTextField_c_2);

	    //////////////////////////////////////////////////////////////////////
		
		JScrollPane scrollPane_c = new JScrollPane(jTable_c);//添加滚动条    
		getContentPane().add(scrollPane_c,BorderLayout.CENTER);
	            
		jTable_c.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//表格中可进行单选    
		jTable_c.addMouseListener(new MouseAdapter(){//添加鼠标事件        
			public void mouseClicked(MouseEvent e){    
				int selectedRow = jTable_c.getSelectedRow();//获得选中行索引  
				Object oc_1 = defaultTableModel_c.getValueAt(selectedRow, 0);   
				Object oc_2 = defaultTableModel_c.getValueAt(selectedRow, 1);
				jTextField_c_1.setText(oc_1.toString());//给文本框赋值 
				jTextField_c_2.setText(oc_2.toString());
				IDT=oc_1.toString();
	            }
	        });
	        
		scrollPane_c.setBounds(200, 50, 400, 300);
		scrollPane_c.setOpaque(false);
		jPanel_c.add(scrollPane_c);

		//////////////////////////////////////////////////////////////////////
		
		final JButton addButton_c = new JButton("添加");//按钮"添加"
		addButton_c.setBounds(275, 400, 70, 30);
		jPanel_c.add(addButton_c);
		addButton_c.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				if(jTextField_c_1.getText().trim().length()<1 && jTextField_c_2.getText().trim().length()>1){
					JOptionPane.showMessageDialog(null, "尚未设置账号，无法添加用户", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else if(jTextField_c_1.getText().trim().length()>1 && jTextField_c_2.getText().trim().length()<1){
					JOptionPane.showMessageDialog(null, "尚未设置密码，无法添加用户", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else if(jTextField_c_1.getText().trim().length()<1 && jTextField_c_2.getText().trim().length()<1){
					JOptionPane.showMessageDialog(null, "尚未设置账号和密码，无法添加用户", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				else{
					Add_Teacher(jTextField_c_1.getText(),jTextField_c_2.getText());
					jTextField_c_1.setText("");
					jTextField_c_2.setText("");
					}
				}
			});
		  	
		final JButton updateButton_c = new JButton("修改");//按钮"修改"
		updateButton_c.setBounds(375, 400, 70, 30);
		jPanel_c.add(updateButton_c);
		updateButton_c.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_c.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_c_1.getText().trim().length()<1 && jTextField_c_2.getText().trim().length()>1){
						JOptionPane.showMessageDialog(null, "未设置新的账号，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else if(jTextField_c_1.getText().trim().length()>1 && jTextField_c_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "未设置新的密码，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else if(jTextField_c_1.getText().trim().length()<1 && jTextField_c_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "未设置新的账号和密码，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						Update_Teacher(selectedRow,jTextField_c_1.getText(),jTextField_c_2.getText());
						jTextField_c_1.setText("");
						jTextField_c_2.setText("");
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});
		  
		final JButton delButton_c = new JButton("删除");//按钮"删除"
		delButton_c.setBounds(475, 400, 70, 30);
		jPanel_c.add(delButton_c);
		delButton_c.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_c.getSelectedRow();//获得选中行的索引
				if(selectedRow!=-1)//判断是否存在选中行
					{
					defaultTableModel_c.removeRow(selectedRow);//删除选中行
					Delete_Teacher();
					jTextField_c_1.setText("");
					jTextField_c_2.setText("");
	                }
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行删除", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});

		//////////////////////////////////////////////////////////////////////
		//333333333333333333333333333333333333333333333333333333333333333333//
		//////////////////////////////////////////////////////////////////////
    	
		//////////////////////////////////////////////////////////////////////
		//444444444444444444444444444444444444444444444444444444444444444444//
		//////////////////////////////////////////////////////////////////////

		JLabel label_d_1 = new JLabel("原始密码：");
		label_d_1.setBounds(285, 180, 100, 30);
		jPanel_d.add(label_d_1);

		JLabel label_d_2 = new JLabel("设置密码：");
		label_d_2.setBounds(285, 220, 100, 30);
		jPanel_d.add(label_d_2);

		JLabel label_d_3 = new JLabel("确认密码：");
		label_d_3.setBounds(285, 260, 100, 30);
		jPanel_d.add(label_d_3);

		final JPasswordField password_field_d_1 = new JPasswordField();
		password_field_d_1.setBounds(365, 180, 150, 30);
		jPanel_d.add(password_field_d_1);

		final JPasswordField password_field_d_2 = new JPasswordField();
		password_field_d_2.setBounds(365, 220, 150, 30);
		jPanel_d.add(password_field_d_2);

		final JPasswordField password_field_d_3 = new JPasswordField();
		password_field_d_3.setBounds(365, 260, 150, 30);
		jPanel_d.add(password_field_d_3);

		JButton button_d_1 = new JButton("确定");
		button_d_1.setBounds(315, 300, 70, 30);
		jPanel_d.add(button_d_1);
		button_d_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(password_field_d_1.getText().trim().length()<1 || password_field_d_2.getText().trim().length()<1 || password_field_d_3.getText().trim().length()<1)
					JOptionPane.showMessageDialog(null, "信息不全，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
				else{
					Change_PW(password_field_d_1.getText(),password_field_d_2.getText(),password_field_d_3.getText());
					password_field_d_1.setText("");	
					password_field_d_2.setText("");
					password_field_d_3.setText("");
				}
			}
		});

		JButton button_d_2 = new JButton("重置");
		button_d_2.setBounds(415, 300, 70, 30);
		jPanel_d.add(button_d_2);
		button_d_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				password_field_d_1.setText("");	
				password_field_d_2.setText("");
				password_field_d_3.setText("");
			}
		});

		//jPanel_b.setVisible(true);
		//jPanel_b.setOpaque(false);

		//////////////////////////////////////////////////////////////////////
		//444444444444444444444444444444444444444444444444444444444444444444//
		//////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////
		//iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii//
		//////////////////////////////////////////////////////////////////////

		ImageIcon imageIcon_a = new ImageIcon("images/3.jpg");//写入文件路径 
		JLabel imageLabel_a = new JLabel(imageIcon_a);//初始化JLabel

		ImageIcon imageIcon_b = new ImageIcon("images/4.jpg");//写入文件路径 
		JLabel imageLabel_b = new JLabel(imageIcon_b);//初始化JLabel

		ImageIcon imageIcon_c = new ImageIcon("images/4.jpg");//写入文件路径 
		JLabel imageLabel_c = new JLabel(imageIcon_c);//初始化JLabel

		ImageIcon imageIcon_d = new ImageIcon("images/5.jpg");//写入文件路径 
		JLabel imageLabel_d = new JLabel(imageIcon_d);//初始化JLabel

		jPanel_a.add(imageLabel_a);
		imageLabel_a.setBounds(0, 0, 800, 600);

		jPanel_b.add(imageLabel_b);
		imageLabel_b.setBounds(0, 0, 800, 600);

		jPanel_c.add(imageLabel_c);
		imageLabel_c.setBounds(0, 0, 800, 600);

		jPanel_d.add(imageLabel_d);
		imageLabel_d.setBounds(0, 0, 800, 600);

		//////////////////////////////////////////////////////////////////////
		//iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii//
		//////////////////////////////////////////////////////////////////////
		
    	jFrame.add(jTabbedPane);
	    jFrame.setVisible(true);
		
	}
	public static void main(String[] args){
		new Manager("13570109");
	}
}