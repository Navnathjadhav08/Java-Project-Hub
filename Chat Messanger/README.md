# Chat Server and Client Application

This Java application demonstrates a simple chat server and client using Swing for the graphical user interface (GUI) and sockets for communication between them.

## Functionality:

- **ServerGUI:**
  - The server opens a GUI window that displays the chat messages.
  - It allows the server to send messages to the connected client.
  - Server status is displayed, and the GUI includes a log button to save the chat log.

- **ClientGUI:**
  - The client opens a GUI window for chatting with the server.
  - Messages received from the server are displayed in different colors.
  - Chat messages can be sent to the server, and a log button allows saving the chat log.

## How to Run:

1. **Compile the Code:**
   - Open a terminal and navigate to the directory containing the Java files.
   - Compile both `ServerGUI.java` and `ClientGUI.java` using `javac`:

     ```bash
     javac ServerGUI.java
     javac ClientGUI.java
     ```
     ![Terminal](images/comandprompt.png)

2. **Run the Server:**
   - Start the server by running the compiled `ServerGUI` class:

     ```bash
     java ServerGUI
     ```

   ![Server GUI](images/server.png)

3. **Run the Client:**
   - Open a new terminal and run the compiled `ClientGUI` class:

     ```bash
     java ClientGUI
     ```

   ![Client GUI](images/client.png)

4. **Chatting:**
   - Once both the server and client are running, you can type messages in the client and send them to the server.
   - The server will display the received messages in the GUI.
   ![Chatting](images/apllication.png)

5. **Log Saving:**
   - Click the "Log" button in the server or client GUI to save the chat log to a file.

   ![Chat Log](images/logsave.png)

   - log save in the file server_log.txt and client_log.txt file in current directory.

   
   ![Chat Log](images/logfile.png)

   

## Advantages:

- **Swing GUI:** Learn how to create a graphical user interface (GUI) using Swing in Java.
- **Socket Programming:** Understand the basics of client-server communication using sockets.
- **Multithreading:** See how to implement multithreading for handling concurrent tasks (e.g., receiving messages).
- **File I/O:** Learn how to perform file input/output operations, such as saving chat logs.
- **Event Handling:** Explore event-driven programming by handling button clicks and text input events.

## What You'll Learn:

- **Java GUI Development:** Gain practical experience in developing graphical user interfaces using Swing.
- **Networking:** Understand the fundamentals of socket programming for communication between applications.
- **Multithreading:** Learn how to handle concurrent tasks efficiently by using threads.
- **File Operations:** Explore file input/output operations, including creating and saving log files.
- **Event-Driven Programming:** Understand event handling in Java applications.

## Notes:

- This is a simple example for educational purposes and may not cover security considerations or advanced features.
- Feel free to explore and extend the functionality, such as adding user authentication or improving the user interface.
