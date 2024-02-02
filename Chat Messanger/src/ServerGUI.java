// Import Swing components for creating the graphical user interface (GUI)
import javax.swing.*;

// Import classes related to text attributes in Swing
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

// Import classes for handling color in AWT and Swing
import java.awt.*;

// Import classes related to handling events in AWT and Swing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Import classes for handling input/output streams and file operations
import java.io.*;

// Import classes for working with network sockets
import java.net.ServerSocket;
import java.net.Socket;

// Import classes for formatting dates and times
import java.text.SimpleDateFormat;

// Import classes for representing dates
import java.util.Date;

// Define a class named ServerGUI that extends JFrame (Swing window)
public class ServerGUI extends JFrame {

    // Declare instance variables
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintStream ps;
    private BufferedReader br1;
    private JTextField messageField;
    private JTextPane chatArea;
    private JLabel statusLabel;
    private ImageIcon onlineIcon;
    private ImageIcon offlineIcon;
    private BufferedWriter logWriter;

    // Constructor: Initializes the GUI and opens a log file.
    public ServerGUI() {
        SwingUtilities.invokeLater(this::initGUI);
        openLogFile();
    }

    // Initializes the graphical user interface (GUI) components.
    private void initGUI() {
        // JFrame setup
        setTitle("Chat Server");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JTextPane setup for displaying chat messages
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(240, 240, 240));

        // JScrollPane setup for scrolling through chat messages
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JTextField setup for typing messages
        messageField = new JTextField();
        messageField.setBackground(new Color(230, 230, 230));

        // JButton setup for sending messages
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(0, 153, 51));
        sendButton.setForeground(Color.BLACK);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // JButton setup for logging chat messages
        JButton logButton = new JButton("Log");
        logButton.setBackground(new Color(51, 102, 255));
        logButton.setForeground(Color.WHITE);
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveLogToFile();
            }
        });

        // JPanel setup for organizing input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(logButton, BorderLayout.WEST);

        // JLabel setup for displaying server status
        statusLabel = new JLabel("Server Status: Offline");
        onlineIcon = new ImageIcon("online_icon.png");
        offlineIcon = new ImageIcon("offline_icon.png");
        statusLabel.setIcon(offlineIcon);

        // JPanel setup for organizing status label
        JPanel statusPanel = new JPanel();
        statusPanel.add(statusLabel);

        // Set up the main layout of the JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statusPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(255, 255, 255));

        // Start the server and threads
        startServer();
        startThreads();
    }

    // Start the server by creating a ServerSocket and accepting a client connection.
    private void startServer() {
        try {
            serverSocket = new ServerSocket(2100);
            clientSocket = serverSocket.accept();
            ps = new PrintStream(clientSocket.getOutputStream());
            br1 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            statusLabel.setText("Server Status: Online");
            statusLabel.setIcon(onlineIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Start threads for receiving messages and handling message input.
    private void startThreads() {
        // Thread for receiving messages from the client
        Thread receiveThread = new Thread(() -> {
            try {
                String str;
                while ((str = br1.readLine()) != null) {
                    appendToChatArea(formatMessage("Client", str), Color.RED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        receiveThread.start();

        // ActionListener for handling "Enter" key in the messageField for sending messages
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    // Send a message to the client and update the chat area.
    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            ps.println(message);
            appendToChatArea(formatMessage("Server", message), Color.BLUE);
            messageField.setText("");
        }
    }

    // Append a message to the chat area with specified text color and write it to the log file.
    private void appendToChatArea(String message, Color textColor) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, textColor);
        try {
            chatArea.getDocument().insertString(chatArea.getDocument().getLength(), message + "\n", attributes);
            writeToLogFile(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Format a message with timestamp, sender, and content.
    private String formatMessage(String sender, String message) {
        String timestamp = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy").format(new Date());
        return "[" + timestamp + "] " + sender + ": " + message;
    }

    // Open or create a log file for writing.
    private void openLogFile() {
        try {
            logWriter = new BufferedWriter(new FileWriter("server_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write a message to the log file.
    private void writeToLogFile(String message) {
        try {
            logWriter.write(message + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save all chat messages to a log file
    private void saveLogToFile() {
        try (BufferedWriter logFileWriter = new BufferedWriter(new FileWriter("chat_log.txt"))) {
            String[] messages = chatArea.getText().split("\\n");
            for (String message : messages) {
                logFileWriter.write(message + "\n");
            }
            JOptionPane.showMessageDialog(this, "Chat log saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving chat log.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method: Creates an instance of ServerGUI and makes it visible.
    public static void main(String[] args) {
        new ServerGUI().setVisible(true);
    }
}
