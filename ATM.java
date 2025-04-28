package atmsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMSystem extends JFrame {
    private JTextField userIdField;
    private JPasswordField pinField;
    private JTextArea displayArea;
    private JPanel cardPanel;

    private double balance = 1000; // Initial balance for demonstration purposes

    public ATMSystem() {
        setTitle("ATM Simulation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUI() {
        setLayout(new BorderLayout());

        cardPanel = new JPanel(new CardLayout());

        JPanel loginPanel = createLoginPanel();
        JPanel operationPanel = createOperationPanel();

        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(operationPanel, "OPERATIONS");

        add(cardPanel, BorderLayout.CENTER);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.SOUTH);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        JLabel pinLabel = new JLabel("PIN:");

        userIdField = new JTextField();
        pinField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        return panel;
    }

    private JPanel createOperationPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1)); // Increase the number of rows for the new button

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance"); // New button

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton); // Add the new button

        return panel;
    }

    private void login() {
        String userId = userIdField.getText();
        char[] pinChars = pinField.getPassword();
        String pin = new String(pinChars);

        // Perform authentication (you should replace this with your authentication logic)
        if (authenticateUser(userId, pin)) {
            displayArea.setText("Login successful!\nWelcome, " + userId + "!");
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "OPERATIONS");
        } else {
            displayArea.setText("Login failed. Please check your credentials.");
        }
    }

    private boolean authenticateUser(String userId, String pin) {
        // Replace this with your actual authentication logic
        return userId.equals("user123") && pin.equals("1234");
    }

    private void withdraw() {
        String amountString = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
        if (amountString != null && !amountString.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountString);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    displayArea.setText("Withdrawal successful. Remaining balance: $" + balance);
                } else {
                    displayArea.setText("Invalid withdrawal amount.");
                }
            } catch (NumberFormatException e) {
                displayArea.setText("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void deposit() {
        String amountString = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        if (amountString != null && !amountString.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountString);
                if (amount > 0) {
                    balance += amount;
                    displayArea.setText("Deposit successful. New balance: $" + balance);
                } else {
                    displayArea.setText("Invalid deposit amount.");
                }
            } catch (NumberFormatException e) {
                displayArea.setText("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void checkBalance() {
        displayArea.setText("Current Balance: $" + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATMSystem atmSystem = new ATMSystem();
        });
    }
}
