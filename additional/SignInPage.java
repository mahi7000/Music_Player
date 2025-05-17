package additional;
import javax.swing.*;
import java.awt.*;

public class SignInPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton signUpButton;
    private JButton googleSignInButton;

    public SignInPage() {
        setTitle("Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Username field
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.setMaximumSize(new Dimension(350, 40));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        mainPanel.add(usernamePanel);

        // Email field
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.setMaximumSize(new Dimension(350, 40));
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        mainPanel.add(emailPanel);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setMaximumSize(new Dimension(350, 40));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        mainPanel.add(passwordPanel);

        // Sign Up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setPreferredSize(new Dimension(200, 40));
        signUpButton.addActionListener(e -> handleSignUp());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(signUpButton);

        // Divider
        JPanel dividerPanel = new JPanel();
        dividerPanel.setLayout(new BoxLayout(dividerPanel, BoxLayout.X_AXIS));
        dividerPanel.setMaximumSize(new Dimension(350, 20));
        JLabel orLabel = new JLabel("OR");
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dividerPanel.add(Box.createHorizontalGlue());
        dividerPanel.add(orLabel);
        dividerPanel.add(Box.createHorizontalGlue());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(dividerPanel);

        // Google Sign In button (disabled for now)
        googleSignInButton = new JButton("Sign in with Google");
        googleSignInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        googleSignInButton.setPreferredSize(new Dimension(200, 40));
        googleSignInButton.setEnabled(false); // Disabled until we implement Google auth
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(googleSignInButton);

        add(mainPanel);
    }

    private void handleSignUp() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Basic email validation
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Password length validation
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Here you would typically save the user data to a database
        JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear the form
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignInPage().setVisible(true);
        });
    }
} 