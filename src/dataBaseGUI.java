import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class dataBaseGUI implements ActionListener{
    JFrame frame, frame1;
    JTextField textBox;
    JLabel label;
    JButton button;
    JPanel panel;
    static JTable table;

    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("Show Password");

    String[] col = { "role", "id", "startDate", "endDate" };
    static JComboBox colList;

    String url = "jdbc:mysql://localhost:3306/mydb";
    String userName = "js1";
    String password = "1225";
    String[] columnNames = {"role", "id", "startDate", "endDate"};

    public void createUI() {
        frame = new JFrame("SunLab Entrance and Exit Logs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        textBox = new JTextField();

        int searchLoc = 200;
        int loginLoc = 30;

        textBox.setBounds(220,searchLoc + 1,150,20);
        label = new JLabel("Search");
        label.setBounds(50, searchLoc, 100, 20);
        button = new JButton("Search");
        button.setBounds(390,searchLoc,80,20);
        button.addActionListener(this);

        colList = new JComboBox(col);
        colList.setBounds(110,searchLoc + 1,100,20);
        colList.setSelectedIndex(3);
        colList.addActionListener(this);

        userLabel.setBounds(50, loginLoc, 100, 30);
        passwordLabel.setBounds(50, loginLoc+50, 100, 30);
        userTextField.setBounds(150, loginLoc, 150, 30);
        passwordField.setBounds(150, loginLoc+50, 150, 30);
        showPassword.setBounds(150, loginLoc+80, 150, 30);
        loginButton.setBounds(50, loginLoc+110, 100, 30);
        resetButton.setBounds(200, loginLoc+110, 100, 30);
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);

        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(showPassword);
        frame.add(loginButton);
        frame.add(resetButton);

        frame.add(textBox);
        frame.add(label);
        frame.add(button);

        frame.add(colList);

        colList.setVisible(false);

        button.setVisible(false);
        label.setVisible(false);
        textBox.setVisible(false);
        frame.setVisible(true);
        frame.setSize(500, 400);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == loginButton) {
            loginButton = (JButton) ae.getSource();
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("password")) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                colList.setVisible(true);
                button.setVisible(true);
                label.setVisible(true);
                textBox.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }

        }
        if (ae.getSource() == resetButton) {
            resetButton = (JButton) ae.getSource();
            userTextField.setText("");
            passwordField.setText("");
        }
        if (ae.getSource() == showPassword) {
            showPassword = (JCheckBox) ae.getSource();
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }

        if (ae.getSource() == button) {
            button = (JButton) ae.getSource();
            System.out.println("Showing Table Data.......");
            //colList = (JComboBox) ae.getSource();
            String selCol = (String) colList.getSelectedItem();
            showTableData(selCol);
        }
    }

    public void showTableData(String val) {
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
//TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
//table = new JTable(model);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String textValue = textBox.getText();
        String role= "";
        String id= null;
        String startDate = "";
        String endDate = "";
        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            //String sql = "select * from labLog where id = " + textValue;
            String sqlDB = "select * from labLog";
            String sqlWhere = " where " + val + " = " + textValue;
            PreparedStatement ps = con.prepareStatement(sqlDB + sqlWhere);
            ResultSet rs = ps.executeQuery();
            int i =0;
            if(rs.next()) {
                role = rs.getString("role");
                id = rs.getString("id");
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                model.addRow(new Object[]{role, id, startDate, endDate});
                i++;
            }
            if(i <1) {
                JOptionPane.showMessageDialog(null, "No Record Found","Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            if(i ==1) {
                System.out.println(i+" Record Found");
            } else {
                System.out.println(i+" Records Found");
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400,300);
    }

    public static void main(String[] args) {
        dataBaseGUI sr = new dataBaseGUI();
        sr.createUI();
    }
}