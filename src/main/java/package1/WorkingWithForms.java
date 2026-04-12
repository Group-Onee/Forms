package package1;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import com.toedter.calendar.JDateChooser;


public class WorkingWithForms {


    JTextField nameField, emailField, dobField;
    JPasswordField passwordField;
    JComboBox<String> departmentBox;
    JTree orgTree;
    JDateChooser dateChooser;

    public WorkingWithForms() {

        JFrame frame = new JFrame("Employee Registration System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 2, 10, 10));


        frame.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Email:"));
        emailField = new JTextField();
        frame.add(emailField);


        frame.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);


        frame.add(new JLabel("Department:"));
        String[] departments = {"IT", "HR", "Finance", "Marketing"};
        departmentBox = new JComboBox<>(departments);
        frame.add(departmentBox);


        frame.add(new JLabel("Date of Birth:"));
        JDateChooser dateChooser = new JDateChooser();
        frame.add(dateChooser);

        frame.add(new JLabel("Organization:"));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");
        root.add(new DefaultMutableTreeNode("IT"));
        root.add(new DefaultMutableTreeNode("HR"));
        root.add(new DefaultMutableTreeNode("Finance"));

        orgTree = new JTree(root);
        JScrollPane treePane = new JScrollPane(orgTree);
        frame.add(treePane);


        JButton submitBtn = new JButton("Submit");
        frame.add(new JLabel("")); // empty space
        frame.add(submitBtn);


        submitBtn.addActionListener(e -> {

            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String department = (String) departmentBox.getSelectedItem();
            String dob = dobField.getText();

            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) orgTree.getLastSelectedPathComponent();

            String org = (selectedNode != null) ? selectedNode.toString() : "None";


            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || dob.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!");
                return;
            }

            String hiddenPassword = "********";


            String summary = "Full Name: " + name +
                    "\nEmail: " + email +
                    "\nPassword: " + hiddenPassword +
                    "\nDepartment: " + department +
                    "\nDate of Birth: " + dob +
                    "\nOrganization: " + org;

            JOptionPane.showMessageDialog(frame, summary, "Registration Details", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.setVisible(true);
    }


}