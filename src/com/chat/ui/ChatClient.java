package com.chat.ui;

import com.chat.Main;
import com.chat.client.ClientSvc;
import com.chat.client.impl.Client;
import com.chat.common.data.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Represents the Chat Client UI.
 */
public class ChatClient extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JLabel lbUsername = new JLabel("Username: ", SwingConstants.CENTER);
    private JTextField tfUsername = new JTextField(Main.userName);
    private JButton btJoin = new JButton("Join");
    private JButton btLeave = new JButton("Leave");
    private JButton ptActiveUsers = new JButton("Active Users");
    private JTextArea taChatRoom = new JTextArea("Welcome to the Chat room\n", 80, 60);
    private JTextField tfHost = new JTextField();
    private JLabel lHost = new JLabel("Chat Server: ", SwingConstants.CENTER);
    private JTextField tfPort = new JTextField();
    private JLabel lPort = new JLabel("Port: ", SwingConstants.CENTER);
    private boolean isConnected;
    private ClientSvc clientSvc;
    private int port;
    private String host;

    
    /**
     * Constructs a ChatClient instance with the specified host and port.
     *
     * @param host the host address of the chat server
     * @param port the port number of the chat server
     */
    public ChatClient(String host, int port) {
        
        super("Chat Client");
        this.port = port;
        this.host = host;

        JPanel pnlNorth = new JPanel(new GridLayout(3, 1));

        pnlNorth.add(lHost);
        pnlNorth.add(tfHost);
        tfHost.setText(host);
        pnlNorth.add(lPort);
        pnlNorth.add(tfPort);
        tfPort.setText(String.valueOf(port));
        pnlNorth.add(lbUsername);
        tfUsername.setBackground(Color.WHITE);
        tfUsername.addActionListener(new EnterActionListener());
        tfUsername.setEditable(true);
        pnlNorth.add(tfUsername);
        add(pnlNorth, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.add(new JScrollPane(taChatRoom));
        taChatRoom.setEditable(false);
        taChatRoom.setLineWrap(true);
        add(centerPanel, BorderLayout.CENTER);

        btJoin.addActionListener(new JoinActionListener());
        btLeave.addActionListener(new LeaveActionListener());
        btLeave.setEnabled(false);
        ptActiveUsers.addActionListener(new ActiveUsersActionListener());
        ptActiveUsers.setEnabled(false);

        JPanel southPanel = new JPanel();
        southPanel.add(btJoin);
        southPanel.add(btLeave);
        southPanel.add(ptActiveUsers);
        add(southPanel, BorderLayout.SOUTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize((screenSize.width) / 2, screenSize.height - 45);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        tfUsername.requestFocus();
    }

    
    /**
     * Appends a message to the chat room.
     *
     * @param msg the message to append
     */
    public void appendText(Message msg) {
        
        String message = msg.toString();
        taChatRoom.append(message);
        taChatRoom.setCaretPosition(taChatRoom.getText().length() - 1);
        ChatClient.this.setTitle(msg.getUsername() + ": " + msg.getMsg());
    }

    
    /**
     * Notifies that the connection to the chat server has failed.
     */
    public void connectionFailed() {
        
        btJoin.setEnabled(true);
        btLeave.setEnabled(false);
        ptActiveUsers.setEnabled(false);
        lbUsername.setText("Username: ");
        tfUsername.setText(Main.userName);
        tfHost.setEnabled(true);
        tfPort.setEnabled(true);
        isConnected = false;
    }

    
    public static void start() {
        
        try {
            new ChatClient(InetAddress.getLocalHost().getHostAddress(), 2000);
        } 
        
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    
    private class JoinActionListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String username = tfUsername.getText().trim();
            if(username.length() == 0)
                return;
            
            host = tfHost.getText().trim();
            if(host.length() == 0)
                return;
            
            try {
                port = Integer.parseInt(tfPort.getText().trim());
            }
            
            catch(Exception ex){
                return;
            }

            clientSvc = new Client(host, port, username, ChatClient.this);
            host = clientSvc.start();
            tfHost.setText(host);
            tfUsername.setText("");
            lbUsername.setText("Enter your message below");
            isConnected = true;

            btJoin.setEnabled(false);
            btLeave.setEnabled(true);
            tfHost.setEnabled(false);
            tfPort.setEnabled(false);

            ptActiveUsers.setEnabled(true);
            ChatClient.this.setTitle(username + " Joined");
        }
    }

    
    private class LeaveActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            clientSvc.sendMessage(new Message("", com.chat.common.utils.Type.SIGNOUT));
            ChatClient.this.setTitle("Signed-out");
        }
    }

    
    private class ActiveUsersActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            clientSvc.sendMessage(new Message("", com.chat.common.utils.Type.ALL_USERS));
            ChatClient.this.setTitle("Active users");
        }
    }

    
    private class EnterActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(isConnected) {
                clientSvc.sendMessage(new Message(tfUsername.getText(), com.chat.common.utils.Type.MSG));
                tfUsername.setText("");
                ChatClient.this.setTitle(tfUsername.getText());
            } 
            
            else {
                JOptionPane.showMessageDialog(ChatClient.this, "Not connected to the chat server.", "Not connected",
                        JOptionPane.OK_OPTION);
            }
        }
    }
}

