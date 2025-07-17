package app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)
    {
        try{
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server has started on port 9999");
            int i =1;
            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client "+ i + "connected");
                i = i+1;

                ClientHandler handler = new ClientHandler(clientSocket);
                Thread t = new Thread(handler);
                t.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);

                String command = in.readLine();

                if (command.equals("GET_COURSES")) {
                    out.println("Math 101|Basic Math|1");
                    out.println("Physics 101|Basic Physics|2");
                    out.println("END");
                } else if (command.startsWith("ENROLL:")) {
                    String[] parts = command.split(":");
                    int studentId = Integer.parseInt(parts[1]);
                    int courseId = Integer.parseInt(parts[2]);

                    System.out.println("Enrolling student " + studentId + " in course " + courseId);
                    out.println("SUCCESS");
                }
                else if(command.startsWith("SIGN UP"))
                {

                }
                else if(command.startsWith("LOGIN"))
                {

                }
                else if(command.startsWith("REMOVE COURSE"))
                {

                }
                else if(command.startsWith("ADD EXAM"))
                {

                }
                else if(command.startsWith("LEADERBOARDS"))
                {

                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
