import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.text.SimpleDateFormat;

public class EmployeeRegistrationSystem extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passField;
    private JComboBox<String> deptBox;
    private JDateChooser dateChooser;
    private JTree orgTree;

    public EmployeeRegistrationSystem() {
        setTitle("Employee Registration System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- FORM PANEL (Left Side) ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Registration Form"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email Address:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);
        passField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        // Department
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Department:"), gbc);
        String[] depts = {"IT", "Finance", "HR", "Marketing"};
        deptBox = new JComboBox<>(depts);
        gbc.gridx = 1;
        formPanel.add(deptBox, gbc);

        // Date of Birth (JCalendar)
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Date of Birth:"), gbc);
        dateChooser = new JDateChooser();
        gbc.gridx = 1;
        formPanel.add(dateChooser, gbc);

        // --- TREE PANEL (Right Side) ---
        JPanel treePanel = new JPanel(new BorderLayout());
        treePanel.setBorder(BorderFactory.createTitledBorder("Organization Structure"));
        orgTree = new JTree(createOrgModel());
        treePanel.add(new JScrollPane(orgTree), BorderLayout.CENTER);

        // --- BUTTON PANEL ---
        JPanel btnPanel = new JPanel();
        JButton submitBtn = new JButton("Submit");
        JButton clearBtn = new JButton("Clear");
        btnPanel.add(submitBtn);
        btnPanel.add(clearBtn);

        // --- ACTIONS ---
        submitBtn.addActionListener(e -> handleSubmit());
        clearBtn.addActionListener(e -> clearFields());

        // Assembly
        add(formPanel, BorderLayout.WEST);
        add(treePanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private DefaultMutableTreeNode createOrgModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Global Corp");

        DefaultMutableTreeNode itDept = new DefaultMutableTreeNode("IT Department");
        itDept.add(new DefaultMutableTreeNode("Software Team"));
        itDept.add(new DefaultMutableTreeNode("DevOps Team"));

        DefaultMutableTreeNode hrDept = new DefaultMutableTreeNode("HR Department");
        hrDept.add(new DefaultMutableTreeNode("Recruitment"));

        root.add(itDept);
        root.add(hrDept);
        return root;
    }

    private void handleSubmit() {
        String name = nameField.getText();
        String email = emailField.getText();
        String pass = new String(passField.getPassword());
        String dept = (String) deptBox.getSelectedItem();

        // Validation
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(dateChooser.getDate());

        // Summary Dialog
        String summary = String.format(
                "Registration Successful!\n\nName: %s\nEmail: %s\nPassword: ******\nDepartment: %s\nDOB: %s",
                name, email, dept, dob
        );
        JOptionPane.showMessageDialog(this, summary, "Employee Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        passField.setText("");
        deptBox.setSelectedIndex(0);
        dateChooser.setDate(null);
    }
}
