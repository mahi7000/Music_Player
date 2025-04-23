import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class SignUpPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton signUpButton;
    private JButton googleSignInButton;
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "http://localhost:8080/oauth2callback";

    public SignUpPage() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Create Account");
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

        // Google Sign In button
        googleSignInButton = new JButton("Sign in with Google");
        googleSignInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        googleSignInButton.setPreferredSize(new Dimension(200, 40));
        googleSignInButton.addActionListener(e -> handleGoogleSignIn());
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

        // Here you would typically validate the input and create the user account
        // For now, we'll just show a success message
        JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleGoogleSignIn() {
        try {
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                CLIENT_ID,
                CLIENT_SECRET,
                java.util.Arrays.asList("email", "profile"))
                .build();

            String url = flow.newAuthorizationUrl()
                .setRedirectUri(REDIRECT_URI)
                .build();

            // Open the authorization URL in the default browser
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

            // Note: In a real application, you would need to set up a local server
            // to handle the OAuth callback and exchange the authorization code for tokens
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during Google sign-in: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignUpPage().setVisible(true);
        });
    }
}
