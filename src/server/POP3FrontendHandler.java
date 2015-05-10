package server;

import java.io.*;
import java.net.*;

public class POP3FrontendHandler extends Thread {

  private Socket socket;
  
  public POP3FrontendHandler(Socket socket) {
    super();
    this.socket = socket;
  }
   
  public void run() {
    try (
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
      new InputStreamReader(
        socket.getInputStream()));
      ) {
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        out.println(inputLine);
        System.out.println(inputLine);
      }  
      socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
}
