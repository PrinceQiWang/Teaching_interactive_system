import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher extends JFrame {
	String[] columnNames_b = {"题号","日期","题目","问题","学生","解答","教师"};//表格列名
	String [][][][][][][]tableVales_b={};//数据    
	
	String[] columnNames_c = {"题号","日期","题目","问题","学生"};//表格列名
	String [][][][][]tableVales_c={};//数据 
	
	String[] columnNames_d = {"题号","日期","题目","问题","教师","学生","解答","得分"};//表格列名
	String [][][][][][][][]tableVales_d={};//数据    
	
	final DefaultTableModel defaultTableModel_b = new DefaultTableModel(tableVales_b,columnNames_b);
	final JTable jTable_b = new JTable(defaultTableModel_b);
	
	final DefaultTableModel defaultTableModel_c = new DefaultTableModel(tableVales_c,columnNames_c);
	final JTable jTable_c = new JTable(defaultTableModel_c);
	
	final DefaultTableModel defaultTableModel_d = new DefaultTableModel(tableVales_d,columnNames_d);
	final JTable jTable_d = new JTable(defaultTableModel_d);
	
	final JTextField jTextField_b_1 = new JTextField();
	final JTextField jTextField_b_2 = new JTextField();
	final JTextField jTextField_b_3 = new JTextField();
	final JTextField jTextField_b_4 = new JTextField();
	final JTextField jTextField_b_5 = new JTextField();
	final JTextArea jTextArea_b_1 = new JTextArea();
	final JTextArea jTextArea_b_2 = new JTextArea();
	final JTextField jTextField_c_1 = new JTextField();
	final JTextField jTextField_c_2 = new JTextField();
	final JTextField jTextField_c_3 = new JTextField();
	final JTextArea jTextArea_c_1 = new JTextArea();
	final JTextField jTextField_c_4 = new JTextField();
	final JTextField jTextField_d_1 = new JTextField();
	final JTextField jTextField_d_2 = new JTextField();
	final JTextField jTextField_d_3 = new JTextField();
	final JTextField jTextField_d_4 = new JTextField();
	final JTextField jTextField_d_5 = new JTextField();
	final JTextField jTextField_d_6 = new JTextField();
	final JTextArea jTextArea_d_1 = new JTextArea();
	final JTextArea jTextArea_d_2 = new JTextArea();
	
	//int flageH=0;
	//int flageQ=0;
	//int flageA=0;
	
	String ID=null;
	String NumberH=null;
	String NumberA=null;
	String NumberS=null;
	
	public void Export_Questions() {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String StudentID = null;
		String Number = null;
		String Title = null;
		String Question = null;
		String Answer = null;
		String TeacherID = null;
		String Date = null;
		try {
			int j=defaultTableModel_b.getRowCount();//删除原有输出内容
			//System.out.println(j);
			for(int i=0;i<j;i++)
				defaultTableModel_b.removeRow(0);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs= sql.executeQuery(" select StudentID,Number,Title,Question,Answer,TeacherID,Date from Questions ");
			while(rs.next()){
					TeacherID = rs.getString("TeacherID");
					Number = rs.getString("Number");
					Title=rs.getString("Title");
					Question = rs.getString("Question");
					Answer = rs.getString("Answer");
					StudentID = rs.getString("StudentID");
					Date = rs.getString("Date");				
					if(TeacherID.equals(ID)){
						String[]rowValues_b = {Number,Date,Title,Question,StudentID,Answer,TeacherID};
						defaultTableModel_b.addRow(rowValues_b);  //添加一行
						//int rowCount_c = table_c.getRowCount()+1;//表格增加一行
					}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update_Question(int selectedRow,String Answer){
		Connection con;//
		java.sql.Statement sql;
		//defaultTableModel_b.setValueAt(jTextField_b_5.getText(), selectedRow, 6);//将表格中的值修改为输入框中的值
		defaultTableModel_b.setValueAt(jTextArea_b_2.getText(), selectedRow, 5);
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			sql.execute("update Questions set Answer='"+Answer+"' "+" where Number='"+NumberA+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Export_Homeworks() {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String StudentID = null;
		String Number = null;
		String Title = null;
		String Question = null;
		String Date = null;
		String TeacherID = null;
		//Date DATE = null;
		try {
			int j=defaultTableModel_c.getRowCount();//删除原有输出内容
			//System.out.println(j);
			for(int i=0;i<j;i++)
				defaultTableModel_c.removeRow(0);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs= sql.executeQuery(" select StudentID,Number,Title,Question,TeacherID,Date from Homeworks ");
			while(rs.next()){
					TeacherID = rs.getString("TeacherID");
					Number = rs.getString("Number");
					Title=rs.getString("Title");
					Question = rs.getString("Question");
					StudentID = rs.getString("StudentID");
					Date = rs.getString("Date");
					//DATE = rs.getTimestamp("Date");
					//Date = DATE.toString();
					if(TeacherID.equals(ID)){
						String[]rowValues_c = {Number,Date,Title,Question,StudentID};
						defaultTableModel_c.addRow(rowValues_c);  //添加一行
						//int rowCount_c = table_c.getRowCount()+1;//表格增加一行
					}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Add_Homework(String Number, String Date,String Title, String Question,String StudentID) {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs_1;
		int rs_2;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs_1= sql.executeQuery(" select Number from Questions " + "where Number = '" + Number + "'");
			if(rs_1.next()){
				JOptionPane.showMessageDialog(null, "该题号已存在，请更换用户名!","系统信息", JOptionPane.ERROR_MESSAGE);
			}
			else{
				jTextField_c_2.setText(Date);
				String[]rowValues = {jTextField_c_1.getText(),jTextField_c_2.getText(),jTextField_c_3.getText(),jTextArea_c_1.getText(),jTextField_c_4.getText()};
				defaultTableModel_c.addRow(rowValues);  //添加一行
				//int rowCount_c = table_c.getRowCount()+1;//表格增加一行
				rs_2 = sql.executeUpdate(" insert into Homeworks (TeacherID,Number,Title,Question,StudentID,Date)  " + "values ( '"+ID+"','"+Number+"','"+Title+"','"+Question+"','"+StudentID+"','"+Date+"')");
//				INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
				jTextField_c_1.setText("");
				jTextField_c_2.setText("");
				jTextField_c_3.setText("");
				jTextArea_c_1.setText("");
				jTextField_c_4.setText("");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update_Homework(int selectedRow,String Number, String Date,String Title, String Question,String StudentID){
		Connection con;//
		java.sql.Statement sql;
		defaultTableModel_c.setValueAt(jTextField_c_1.getText(), selectedRow, 0);//将表格中的值修改为输入框中的值
		defaultTableModel_c.setValueAt(jTextField_c_2.getText(), selectedRow, 1);//将表格中的值修改为输入框中的值
		defaultTableModel_c.setValueAt(jTextField_c_3.getText(), selectedRow, 2);//将表格中的值修改为输入框中的值
		defaultTableModel_c.setValueAt(jTextArea_c_1.getText(), selectedRow, 3);//将表格中的值修改为输入框中的值
		defaultTableModel_c.setValueAt(jTextField_c_4.getText(), selectedRow, 4);//将表格中的值修改为输入框中的值
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			sql.execute("update Homeworks set TeacherID='"+ID+"',Number='"+Number+"',Title='"+Title+"',Question='"+Question+"',StudentID='"+StudentID+"',Date='"+Date+"' "+" where Number='"+NumberH+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Delete_Homework(int selectedRow){
		Connection con;//
		java.sql.Statement sql;
		int rs;
		defaultTableModel_c.removeRow(selectedRow);//删除选中行
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs = sql.executeUpdate("  delete from Homeworks  where Number = '" + NumberH +"'");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void Export_Score() {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String TeacherID = null;
		String Number = null;
		String Title = null;
		String Question = null;
		String Answer = null;
		String StudentID = null;
		String Date = null;
		String Score = null;
		try {
			int j=defaultTableModel_d.getRowCount();//删除原有输出内容
			//System.out.println(j);
			for(int i=0;i<j;i++)
				defaultTableModel_d.removeRow(0);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			rs= sql.executeQuery(" select StudentID,Number,Title,Question,Answer,TeacherID,Date,Score from Homeworks ");
			while(rs.next()){
					TeacherID = rs.getString("TeacherID");
					Number = rs.getString("Number");
					Title=rs.getString("Title");
					Question = rs.getString("Question");
					Answer = rs.getString("Answer");
					StudentID = rs.getString("StudentID");
					Date = rs.getString("Date");
					Score = rs.getString("Score");			
					if(TeacherID.equals(ID)){
						String[]rowValues_d = {Number,Date,Title,Question,TeacherID,StudentID,Answer,Score};
						defaultTableModel_d.addRow(rowValues_d);  //添加一行
						//int rowCount_c = table_c.getRowCount()+1;//表格增加一行
					}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update_Score(int selectedRow,String Answer,String Score){
		Connection con;//
		java.sql.Statement sql;
		//defaultTableModel_d.setValueAt(jTextField_d_4.getText(), selectedRow, 4);//将表格中的值修改为输入框中的值
		defaultTableModel_d.setValueAt(jTextArea_d_2.getText(), selectedRow, 6);
		defaultTableModel_d.setValueAt(jTextField_d_6.getText(), selectedRow, 7);//将表格中的值修改为输入框中的值
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Login", "root", "wzq95617");
			sql = con.createStatement();
			sql.execute("update Homeworks set Answer='"+Answer+"',Score='"+Score+"' "+" where Number='" + NumberS + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Change_PW(String OriginalPW, String UserPW,String RUserPW) {
		Connection con;//
		java.sql.Statement sql;
		ResultSet rs;
		String RightPassword = null;
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
	
	public Teacher(String IDT){
		
		ID=IDT;
		
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
    	final JPanel jPanel_a = new JPanel();
    	jPanel_a.setLayout(null);
    	JTabbedPane jTabbedPane = new JTabbedPane();
    	jTabbedPane.addTab("欢迎",null,jPanel_a,null);//加入第一个页面
    	jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
            	JTabbedPane jTabbedPane=(JTabbedPane) e.getSource();
            	 int selectedIndex = jTabbedPane.getSelectedIndex();
            	    switch (selectedIndex) {
            	    	case 1:{
            	    		//if(flageH!=1){
            	    			Export_Questions();
            	    		//	flageH=1;
            	    		//}
            	    		jTextField_b_1.setText("");
        					jTextField_b_2.setText("");
        					jTextField_b_3.setText("");
            	    		jTextArea_b_1.setText("");
            	    		jTextField_b_4.setText("");
        					jTextArea_b_2.setText("");
        					//jTextField_b_5.setText("");
            	    	}
            	    	break;
            	    	case 2:{
            	    		//if(flageQ!=1){
            	    			Export_Homeworks();
            	    		//	flageQ=1;
            	    		//}
            	    		jTextField_c_1.setText("");
        					jTextField_c_2.setText("");
        					jTextField_c_3.setText("");
        					jTextArea_c_1.setText("");
        					jTextField_c_4.setText("");
            	    }
            	     break;
            	    	case 3:{
            	    		//if(flageA!=1){
            	    			Export_Score();
            	    		//	flageA=1;
            	    		//}
            	    		jTextField_d_1.setText("");
        					jTextField_d_2.setText("");
        					jTextField_d_3.setText("");
            	    		jTextArea_d_1.setText("");
            	    		//jTextField_d_4.setText("");
            	    		jTextField_d_5.setText("");
        					jTextArea_d_2.setText("");
        					jTextField_d_6.setText("");
            	    	}
            	    	break;
            	    }
            }
        });
		
		//////////////////////////////////////////////////////////////////////
    	
		//第二个标签下的JPanel
    	JPanel jPanel_b = new JPanel();
    	jPanel_b.setLayout(null);
    	jTabbedPane.addTab("学生问题",null,jPanel_b,null);//加入第一个页面
    	
    	//////////////////////////////////////////////////////////////////////
    	
    	//第三个标签下的JPanel
    	JPanel jPanel_c = new JPanel();
    	jPanel_c.setLayout(null);
    	jTabbedPane.addTab("布置作业", null, jPanel_c, null);//加入第一个页面
    	
    	//////////////////////////////////////////////////////////////////////	    
	    
    	//第四个标签下的JPanel
    	final JPanel jPanel_d = new JPanel();
    	jPanel_d.setLayout(null);
    	jTabbedPane.addTab("学生作业",null,jPanel_d,null);//加入第一个页面    	
    	//////////////////////////////////////////////////////////////////////	    
	    
    	//第四个标签下的JPanel
    	final JPanel jPanel_e = new JPanel();
    	jPanel_e.setLayout(null);
    	jTabbedPane.addTab("密码安全",null,jPanel_e,null);//加入第一个页面
  		
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
		

		JLabel Label_b_1= new JLabel("题号：");
		Label_b_1.setBounds(50, 160, 50, 30);
		jPanel_b.add(Label_b_1);
		
		JLabel Label_b_2= new JLabel("日期：");
		Label_b_2.setBounds(50, 200, 50, 30);
		jPanel_b.add(Label_b_2);
		
		JLabel Label_b_3= new JLabel("题目：");
		Label_b_3.setBounds(50, 240, 50, 30);
		jPanel_b.add(Label_b_3);
		
		JLabel Label_b_4= new JLabel("提问学生：");
		Label_b_4.setBounds(50, 420, 100, 30);
		jPanel_b.add(Label_b_4);
		
		JLabel Label_b_5= new JLabel("答疑教师：");
		Label_b_5.setBounds(405, 380, 100, 30);
		jPanel_b.add(Label_b_5);
		
        jTextField_b_1.setBounds(90, 160, 305, 30);
		jPanel_b.add(jTextField_b_1);		
		jTextField_b_1.setEditable(false);
		
        jTextField_b_2.setBounds(90, 200, 305, 30);
		jPanel_b.add(jTextField_b_2);
		jTextField_b_2.setEditable(false);
		
        jTextField_b_3.setBounds(90, 240, 305, 30);
		jPanel_b.add(jTextField_b_3);
		jTextField_b_3.setEditable(false);
		
        jTextField_b_4.setBounds(115, 420, 280, 30);
		jPanel_b.add(jTextField_b_4);
		jTextField_b_4.setEditable(false);
		
        jTextField_b_5.setBounds(470, 380, 280, 30);
		jPanel_b.add(jTextField_b_5);
		jTextField_b_5.setText(ID);
		jTextField_b_5.setEditable(false);
		
		jTextArea_b_1.setLineWrap(true);//激活自动换行功能
		JScrollPane scrollPane_b_jTextArea_b_1 = new JScrollPane(jTextArea_b_1);//添加滚动条    
		getContentPane().add(scrollPane_b_jTextArea_b_1,BorderLayout.CENTER);
		scrollPane_b_jTextArea_b_1.setBounds(50, 280, 345, 130);
		scrollPane_b_jTextArea_b_1.setOpaque(false);
		jPanel_b.add(scrollPane_b_jTextArea_b_1);
		jTextArea_b_1.setEditable(false);
		
		jTextArea_b_2.setLineWrap(true);//激活自动换行功能
		JScrollPane scrollPane_b_jTextArea_b_2 = new JScrollPane(jTextArea_b_2);//添加滚动条    
		getContentPane().add(scrollPane_b_jTextArea_b_2,BorderLayout.CENTER);
		scrollPane_b_jTextArea_b_2.setBounds(405, 160, 345, 210);
		scrollPane_b_jTextArea_b_2.setOpaque(false);
		jPanel_b.add(scrollPane_b_jTextArea_b_2);
		
	    //////////////////////////////////////////////////////////////////////
		
		JScrollPane scrollPane_b = new JScrollPane(jTable_b);//添加滚动条    
		getContentPane().add(scrollPane_b,BorderLayout.CENTER);
	            
		jTable_b.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//表格中可进行单选    
		jTable_b.addMouseListener(new MouseAdapter(){//添加鼠标事件        
			public void mouseClicked(MouseEvent e){    
				int selectedRow = jTable_b.getSelectedRow();//获得选中行索引  
				Object oa = defaultTableModel_b.getValueAt(selectedRow, 0);   
				Object ob = defaultTableModel_b.getValueAt(selectedRow, 1);	
				Object oc = defaultTableModel_b.getValueAt(selectedRow, 2);   
				Object od = defaultTableModel_b.getValueAt(selectedRow, 3);
				Object oe = defaultTableModel_b.getValueAt(selectedRow, 4);
				Object of = defaultTableModel_b.getValueAt(selectedRow, 5);   
				Object og = defaultTableModel_b.getValueAt(selectedRow, 6);
				
				jTextField_b_1.setText(oa.toString());//给文本框赋值 
				jTextField_b_2.setText(ob.toString());
				jTextField_b_3.setText(oc.toString());	
				jTextField_b_4.setText(oe.toString());
				//jTextField_b_5.setText(og.toString());
				jTextArea_b_1.setText(od.toString());
				if(of!=null)
					jTextArea_b_2.setText(of.toString());
				else
					jTextArea_b_2.setText("");
				NumberA=oa.toString();
	            }
	        });
	        
		scrollPane_b.setBounds(50, 50, 700, 100);
		scrollPane_b.setOpaque(false);
		jPanel_b.add(scrollPane_b);

		//////////////////////////////////////////////////////////////////////
		
		final JButton addButton_b = new JButton("提交");//按钮"添加"
		addButton_b.setBounds(495, 420, 70, 30);
		jPanel_b.add(addButton_b);
		addButton_b.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_b.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_b_5.getText().trim().length()<1 || jTextArea_b_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "回答未填写完整，无法提交", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						Update_Question(selectedRow,jTextArea_b_2.getText());
						//jTextField_b_5.setText("");
						//jTextArea_b_2.setText("");
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});
		  	
		final JButton updateButton_b = new JButton("重置");//按钮"修改"
		updateButton_b.setBounds(595, 420, 70, 30);
		jPanel_b.add(updateButton_b);
		updateButton_b.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_b.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_b_5.getText().trim().length()<1 && jTextArea_b_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "回答未填写，无法重置", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						//jTextField_b_5.setText("");
						//jTextArea_b_2.setText("");
						//defaultTableModel_b.setValueAt(jTextField_b_5.getText(), selectedRow, 6);//将表格中的值修改为输入框中的值
						//defaultTableModel_b.setValueAt(jTextArea_b_2.getText(), selectedRow, 5);
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});

		//////////////////////////////////////////////////////////////////////
		//222222222222222222222222222222222222222222222222222222222222222222//
		//////////////////////////////////////////////////////////////////////
		

		//////////////////////////////////////////////////////////////////////
		//333333333333333333333333333333333333333333333333333333333333333333//
		//////////////////////////////////////////////////////////////////////
		
		JLabel Label_c_1= new JLabel("题号：");
		Label_c_1.setBounds(405, 50, 50, 30);
		jPanel_c.add(Label_c_1);
		
		JLabel Label_c_2= new JLabel("日期：");
		Label_c_2.setBounds(405, 90, 50, 30);
		jPanel_c.add(Label_c_2);
		
		JLabel Label_c_3= new JLabel("题目：");
		Label_c_3.setBounds(405, 130, 50, 30);
		jPanel_c.add(Label_c_3);
		
		JLabel Label_c_4 = new JLabel("答题学生：");
		Label_c_4.setBounds(405, 380, 100, 30);
		jPanel_c.add(Label_c_4);
		
        jTextField_c_1.setBounds(445, 50, 305, 30);
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
		
        jTextField_c_2.setBounds(445, 90, 305, 30);
		jPanel_c.add(jTextField_c_2);
		jTextField_c_2.setEditable(false);
		
        jTextField_c_3.setBounds(445, 130, 305, 30);
		jPanel_c.add(jTextField_c_3);
		
		jTextField_c_4.setBounds(470, 380, 280, 30);
		jPanel_c.add(jTextField_c_4);
		jTextField_c_4.addKeyListener (new KeyListener (){//添加事件
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

		jTextArea_c_1.setLineWrap(true);//激活自动换行功能
		JScrollPane scrollPane_c_jTextArea_c_1 = new JScrollPane(jTextArea_c_1);//添加滚动条    
		getContentPane().add(scrollPane_c_jTextArea_c_1,BorderLayout.CENTER);
		scrollPane_c_jTextArea_c_1.setBounds(405, 170, 345, 200);
		scrollPane_c_jTextArea_c_1.setOpaque(false);
		jPanel_c.add(scrollPane_c_jTextArea_c_1);

	    //////////////////////////////////////////////////////////////////////
		
		JScrollPane scrollPane_c = new JScrollPane(jTable_c);//添加滚动条    
		getContentPane().add(scrollPane_c,BorderLayout.CENTER);
	            
		jTable_c.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//表格中可进行单选    
		jTable_c.addMouseListener(new MouseAdapter(){//添加鼠标事件        
			public void mouseClicked(MouseEvent e){    
				int selectedRow = jTable_c.getSelectedRow();//获得选中行索引  
				Object oa = defaultTableModel_c.getValueAt(selectedRow, 0);   
				Object ob = defaultTableModel_c.getValueAt(selectedRow, 1);	
				Object oc = defaultTableModel_c.getValueAt(selectedRow, 2);   
				Object od = defaultTableModel_c.getValueAt(selectedRow, 3);
				Object oe = defaultTableModel_c.getValueAt(selectedRow, 4);
				
				jTextField_c_1.setText(oa.toString());//给文本框赋值 
				jTextField_c_2.setText(ob.toString());
				jTextField_c_3.setText(oc.toString());
				jTextArea_c_1.setText(od.toString());
				jTextField_c_4.setText(oe.toString());
				NumberH=oa.toString();
	            }
	        });
	        
		scrollPane_c.setBounds(50, 50, 345, 400);
		scrollPane_c.setOpaque(false);
		jPanel_c.add(scrollPane_c);

		//////////////////////////////////////////////////////////////////////
		
		final JButton addButton_c = new JButton("添加");//按钮"添加"
		addButton_c.setBounds(445, 420, 70, 30);
		jPanel_c.add(addButton_c);
		addButton_c.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				if(jTextField_c_1.getText().trim().length()<1 || /*jTextField_c_2.getText().trim().length()<1 || */jTextField_c_3.getText().trim().length()<1 || jTextArea_c_1.getText().trim().length()<1 || jTextField_c_4.getText().trim().length()<1){
					JOptionPane.showMessageDialog(null, "问题信息不全，无法添加", "错误", JOptionPane.ERROR_MESSAGE); 
					}
				else{
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String DT = sdf.format(date);
					Add_Homework(jTextField_c_1.getText(),DT,jTextField_c_3.getText(),jTextArea_c_1.getText(),jTextField_c_4.getText());
					//jTextField_c_1.setText("");
					//jTextField_c_2.setText("");
					//jTextField_c_3.setText("");
					//jTextArea_c_1.setText("");
					//jTextField_c_4.setText("");
					}
				}
			});
		  	
		final JButton updateButton_c = new JButton("修改");//按钮"修改"
		updateButton_c.setBounds(545, 420, 70, 30);
		jPanel_c.add(updateButton_c);
		updateButton_c.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_c.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_c_1.getText().trim().length()<1 || jTextField_c_2.getText().trim().length()<1 || jTextField_c_3.getText().trim().length()<1 || jTextArea_c_1.getText().trim().length()<1 || jTextField_c_4.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "问题信息不全，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String DT = sdf.format(date);
						Update_Homework(selectedRow,jTextField_c_1.getText(),DT,jTextField_c_3.getText(),jTextArea_c_1.getText(),jTextField_c_4.getText());
						//jTextField_c_1.setText("");
						//jTextField_c_2.setText("");
						//jTextField_c_3.setText("");
						//jTextArea_c_1.setText("");
						//jTextField_c_4.setText("");
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});
		  
		final JButton delButton_c = new JButton("删除");//按钮"删除"
		delButton_c.setBounds(645, 420, 70, 30);
		jPanel_c.add(delButton_c);
		delButton_c.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_c.getSelectedRow();//获得选中行的索引
				if(selectedRow!=-1)//判断是否存在选中行
					{
					Delete_Homework(selectedRow);
					jTextField_c_1.setText("");
					jTextField_c_2.setText("");
					jTextField_c_3.setText("");
					jTextArea_c_1.setText("");
					jTextField_c_4.setText("");
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
		
		JLabel Label_d_1= new JLabel("题号：");
		Label_d_1.setBounds(50, 160, 50, 30);
		jPanel_d.add(Label_d_1);
		
		JLabel Label_d_2= new JLabel("日期：");
		Label_d_2.setBounds(50, 200, 50, 30);
		jPanel_d.add(Label_d_2);
		
		JLabel Label_d_3= new JLabel("题目：");
		Label_d_3.setBounds(50, 240, 50, 30);
		jPanel_d.add(Label_d_3);
		
		JLabel Label_d_4= new JLabel("出题教师：");
		Label_d_4.setBounds(50, 420, 100, 30);
		jPanel_d.add(Label_d_4);
		
		JLabel Label_d_5= new JLabel("答题学生：");
		Label_d_5.setBounds(405, 160, 100, 30);
		jPanel_d.add(Label_d_5);
		
		JLabel Label_d_6= new JLabel("得分：");
		Label_d_6.setBounds(405, 380, 50, 30);
		jPanel_d.add(Label_d_6);
		
        jTextField_d_1.setBounds(90, 160, 305, 30);
		jPanel_d.add(jTextField_d_1);		
		jTextField_d_1.setEditable(false);
		
        jTextField_d_2.setBounds(90, 200, 305, 30);
		jPanel_d.add(jTextField_d_2);
		jTextField_d_2.setEditable(false);
		
        jTextField_d_3.setBounds(90, 240, 305, 30);
		jPanel_d.add(jTextField_d_3);
		jTextField_d_3.setEditable(false);
		
        jTextField_d_4.setBounds(115, 420, 280, 30);
		jPanel_d.add(jTextField_d_4);
		jTextField_d_4.setText(ID);
		jTextField_d_4.setEditable(false);
		
        jTextField_d_5.setBounds(470, 160, 280, 30);
		jPanel_d.add(jTextField_d_5);
		jTextField_d_5.setEditable(false);
		
        jTextField_d_6.setBounds(445, 380, 305, 30);
		jPanel_d.add(jTextField_d_6);
		jTextField_d_6.addKeyListener (new KeyListener (){//添加事件
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
		
		jTextArea_d_1.setLineWrap(true);//激活自动换行功能
		JScrollPane scrollPane_d_jTextArea_d_1 = new JScrollPane(jTextArea_d_1);//添加滚动条    
		getContentPane().add(scrollPane_d_jTextArea_d_1,BorderLayout.CENTER);
		scrollPane_d_jTextArea_d_1.setBounds(50, 280, 345, 130);
		scrollPane_d_jTextArea_d_1.setOpaque(false);
		jPanel_d.add(scrollPane_d_jTextArea_d_1);
		jTextArea_d_1.setEditable(false);
		
		jTextArea_d_2.setLineWrap(true);//激活自动换行功能
		JScrollPane scrollPane_d_jTextArea_d_2 = new JScrollPane(jTextArea_d_2);//添加滚动条    
		getContentPane().add(scrollPane_d_jTextArea_d_2,BorderLayout.CENTER);
		scrollPane_d_jTextArea_d_2.setBounds(405, 200, 345, 170);
		scrollPane_d_jTextArea_d_2.setOpaque(false);
		jPanel_d.add(scrollPane_d_jTextArea_d_2);

	    //////////////////////////////////////////////////////////////////////
		
		JScrollPane scrollPane_d = new JScrollPane(jTable_d);//添加滚动条    
		getContentPane().add(scrollPane_d,BorderLayout.CENTER);
	            
		jTable_d.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//表格中可进行单选    
		jTable_d.addMouseListener(new MouseAdapter(){//添加鼠标事件        
			public void mouseClicked(MouseEvent e){    
				int selectedRow = jTable_d.getSelectedRow();//获得选中行索引  
				Object oa = defaultTableModel_d.getValueAt(selectedRow, 0);   
				Object ob = defaultTableModel_d.getValueAt(selectedRow, 1);	
				Object oc = defaultTableModel_d.getValueAt(selectedRow, 2);   
				Object od = defaultTableModel_d.getValueAt(selectedRow, 3);
				Object oe = defaultTableModel_d.getValueAt(selectedRow, 4);
				Object of = defaultTableModel_d.getValueAt(selectedRow, 5);
				Object og = defaultTableModel_d.getValueAt(selectedRow, 6);
				Object oh = defaultTableModel_d.getValueAt(selectedRow, 7);
				
				jTextField_d_1.setText(oa.toString());//给文本框赋值 
				jTextField_d_2.setText(ob.toString());
				jTextField_d_3.setText(oc.toString());
				jTextArea_d_1.setText(od.toString());
				//jTextField_d_4.setText(oe.toString());
				jTextField_d_5.setText(of.toString());
				if(og!=null)
					jTextArea_d_2.setText(og.toString());
				else
					jTextArea_d_2.setText("");
				if(oh!=null)
					jTextField_d_6.setText(oh.toString());
				else
					jTextField_d_6.setText("");
				NumberS=oa.toString();
	            }
	        });
	        
		scrollPane_d.setBounds(50, 50, 700, 100);
		scrollPane_d.setOpaque(false);
		jPanel_d.add(scrollPane_d);
		
		//////////////////////////////////////////////////////////////////////
		
		final JButton addButton_d = new JButton("提交");//按钮"添加"
		addButton_d.setBounds(495, 420, 70, 30);
		jPanel_d.add(addButton_d);
		addButton_d.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_d.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_d_6.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "未设有效分数，无法提交", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						Update_Score(selectedRow,jTextArea_d_2.getText(),jTextField_d_6.getText());
						//jTextField_d_6.setText("");
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});
		  	
		final JButton updateButton_d = new JButton("重置");//按钮"修改"
		updateButton_d.setBounds(595, 420, 70, 30);
		jPanel_d.add(updateButton_d);
		updateButton_d.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
				int selectedRow = jTable_d.getSelectedRow();//获得选中行的索引
				if(selectedRow!= -1)//判断是否存在选中行
					{
					if(jTextField_d_6.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null, "得分栏为空，无法重置", "错误", JOptionPane.ERROR_MESSAGE); 
						}
					else{
						//jTextField_d_6.setText("");
						//defaultTableModel_d.setValueAt(jTextField_d_6.getText(), selectedRow, 7);//将表格中的值修改为输入框中的值
						}
					}
				else
					JOptionPane.showMessageDialog(null, "未选中用户，无法进行修改", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			});

    	//////////////////////////////////////////////////////////////////////
    	//444444444444444444444444444444444444444444444444444444444444444444//
    	//////////////////////////////////////////////////////////////////////
	
		//////////////////////////////////////////////////////////////////////
    	//555555555555555555555555555555555555555555555555555555555555555555//
    	//////////////////////////////////////////////////////////////////////

		JLabel label_e_1 = new JLabel("原始密码：");
		label_e_1.setBounds(285, 180, 100, 30);
		jPanel_e.add(label_e_1);

		JLabel label_e_2 = new JLabel("设置密码：");
		label_e_2.setBounds(285, 220, 100, 30);
		jPanel_e.add(label_e_2);
		
		JLabel label_e_3 = new JLabel("确认密码：");
		label_e_3.setBounds(285, 260, 100, 30);
		jPanel_e.add(label_e_3);
		
		final JPasswordField password_field_e_1 = new JPasswordField();
		password_field_e_1.setBounds(365, 180, 150, 30);
		jPanel_e.add(password_field_e_1);

		final JPasswordField password_field_e_2 = new JPasswordField();
		password_field_e_2.setBounds(365, 220, 150, 30);
		jPanel_e.add(password_field_e_2);
		
		final JPasswordField password_field_e_3 = new JPasswordField();
		password_field_e_3.setBounds(365, 260, 150, 30);
		jPanel_e.add(password_field_e_3);
		
		JButton button_e_1 = new JButton("确定");
		button_e_1.setBounds(315, 300, 70, 30);
		jPanel_e.add(button_e_1);
		button_e_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(password_field_e_1.getText().trim().length()<1 || password_field_e_2.getText().trim().length()<1 || password_field_e_3.getText().trim().length()<1)
					JOptionPane.showMessageDialog(null, "信息不全，无法修改", "错误", JOptionPane.ERROR_MESSAGE); 
				else{
					Change_PW(password_field_e_1.getText(),password_field_e_2.getText(),password_field_e_3.getText());
					//password_field_e_1.setText("");	
					//password_field_e_2.setText("");
					//password_field_e_3.setText("");
				}
			}
		});

		JButton button_e_2 = new JButton("重置");
		button_e_2.setBounds(415, 300, 70, 30);
		jPanel_e.add(button_e_2);
		button_e_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				password_field_e_1.setText("");	
				password_field_e_2.setText("");
				password_field_e_3.setText("");
			}
		});
    	
    	//////////////////////////////////////////////////////////////////////
    	//555555555555555555555555555555555555555555555555555555555555555555//
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
		
		ImageIcon imageIcon_d = new ImageIcon("images/4.jpg");//写入文件路径 
		JLabel imageLabel_d = new JLabel(imageIcon_d);//初始化JLabel
		
		ImageIcon imageIcon_e = new ImageIcon("images/5.jpg");//写入文件路径 
		JLabel imageLabel_e = new JLabel(imageIcon_e);//初始化JLabel
	    
		jPanel_a.add(imageLabel_a);
		imageLabel_a.setBounds(0, 0, 800, 600);
		
		jPanel_b.add(imageLabel_b);
		imageLabel_b.setBounds(0, 0, 800, 600);
		
		jPanel_c.add(imageLabel_c);
		imageLabel_c.setBounds(0, 0, 800, 600);		
		
		jPanel_d.add(imageLabel_d);
		imageLabel_d.setBounds(0, 0, 800, 600);
		
		jPanel_e.add(imageLabel_e);
		imageLabel_e.setBounds(0, 0, 800, 600);
		
	    //////////////////////////////////////////////////////////////////////
	    //iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii//
	    //////////////////////////////////////////////////////////////////////
    	
    	jFrame.add(jTabbedPane);
	    jFrame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new Teacher("13570126");
	}
	
}