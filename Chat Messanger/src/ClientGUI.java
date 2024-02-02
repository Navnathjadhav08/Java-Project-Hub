// Import Swing components for creating the graphical user interface (GUI)
import javax.swing.*;

// Import classes related to text attributes in Swing
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

// Import classes for handling color in AWT and Swing
import java.awt.*;

// Import classes related to handling events in AWT and Swing
import java.awt.event.ActionEvent;

// Import classes for handling input/output streams and file operations
import java.io.*;

// Import classes for working with network sockets
import java.net.Socket;

// Import classes for formatting dates and times
import java.text.SimpleDateFormat;

// Import classes for representing dates
import java.util.Date;

// Define a class named ClientGUI that extends JFrame (Swing window)
public class ClientGUI extends JFrame {

    // Declare instance variables
    private Socket socket;
    private PrintStream ps;
    private BufferedReader br1;
    private JTextField messageField;
    private JTextPane chatArea;
    private BufferedWriter logWriter;

    // Constructor: Initializes the GUI, connects to the server, starts threads, and opens a log file.
    public ClientGUI() {
        initGUI();
        connectToServer();
        startThreads();
        openLogFile();
    }

    // Initializes the graphical user interface (GUI) components.
    private void initGUI() {
        // JFrame setup
        setTitle("Chat Client");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JTextPane setup for displaying chat messages
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(240, 240, 240));

        // JScrollPane setup for scrolling through chat messages
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JTextField setup for typing messages, and buttons for sending and logging
        messageField = new JTextField();
        messageField.setBackground(new Color(230, 230, 230));
        messageField.setForeground(Color.BLACK);
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(0, 153, 51));
        sendButton.setForeground(Color.WHITE);

        JButton logButton = new JButton("Log");
        logButton.setBackground(new Color(51, 102, 255));
        logButton.setForeground(Color.WHITE);
        logButton.addActionListener(e -> saveLogToFile());

        // JPanel setup for organizing input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(logButton, BorderLayout.WEST);

        // Set up the main layout of the JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(new Color(255, 255, 255));
    }

    // Connects to the server by creating a socket and setting up input/output streams.
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 2100);
            ps = new PrintStream(socket.getOutputStream());
            br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            handleConnectionError(e);
        }
    }

    // Starts threads for receiving messages and handling message input.
    private void startThreads() {
        // SwingWorker for receiving messages from the server
        SwingWorker<Void, String> receiveWorker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                String str;
                while ((str = br1.readLine()) != null) {
                    publish(str);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                // Process received messages and update the chat area
                for (String message : chunks) {
                    appendToChatArea(formatMessage("Server", message), Color.RED);
                }
            }
        };

        // Execute the SwingWorker to start receiving messages
        receiveWorker.execute();

        // ActionListener for handling "Enter" key in the messageField for sending messages
        messageField.addActionListener(e -> sendMessage());
    }

    // Sends a message to the server and updates the chat area.
    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            ps.println(message);
            appendToChatArea(formatMessage("You", message), Color.BLUE);
            messageField.setText("");
        }
    }

    // Appends a message to the chat area with specified text color and writes it to the log file.
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

    // Writes a message to the log file.
    private void writeToLogFile(String message) {
        try {
            logWriter.write(message + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Opens or creates a log file for writing.
    private void openLogFile() {
        try {
            logWriter = new BufferedWriter(new FileWriter("client_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Closes the current log file, renames it, and opens a new log file.
    private void saveLogToFile() {
        try {
            logWriter.close();
            File logFile = new File("client_log.txt");
            File destinationFile = new File("client_log_backup.txt");
            if (logFile.renameTo(destinationFile)) {
                JOptionPane.showMessageDialog(this, "Log file saved to: " + destinationFile.getAbsolutePath(), "Log Saved", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save log file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            openLogFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Formats a message with timestamp, sender, and content.
    private String formatMessage(String sender, String message) {
        String timestamp = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy").format(new Date());
        return "[" + timestamp + "] " + sender + ": " + message;
    }

    // Handles connection errors by displaying an error message.
    private void handleConnectionError(IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error connecting to the server.", "Connection Error", JOptionPane.ERROR_MESSAGE);
    }

    // Main method: Creates an instance of ClientGUI and makes it visible.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI().setVisible(true));
    }
}
