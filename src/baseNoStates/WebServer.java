package baseNoStates;

import baseNoStates.requests.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

// Based on
// https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
// http://www.jcgonzalez.com/java-socket-mini-server-http-example

/**
 * Creates the web server.
 */
public class WebServer {
  private static final int PORT = 8080; // port to listen connection
  private static final DateTimeFormatter formatter =
          DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
  private static final Logger logger = LoggerFactory.getLogger(Lock.class);

  public WebServer() {
    try {
      ServerSocket serverConnect = new ServerSocket(PORT);
      logger.info("Server started.\nListening for connections on port : " + PORT + " ...\n");
      // we listen until user halts server execution
      while (true) {
        // each client connection will be managed in a dedicated Thread
        new SocketThread(serverConnect.accept());
        // create dedicated thread to manage the client connection
      }
    } catch (IOException e) {
      logger.error("Server Connection error : " + e.getMessage());
    }
  }


  private class SocketThread extends Thread {
    // as an inner class, SocketThread sees WebServer attributes
    private final Socket insocked; // client connection via Socket class

    SocketThread(Socket insocket) {
      this.insocked = insocket;
      this.start();
    }

    @Override
    public void run() {
      // we manage our particular client connection
      BufferedReader in;
      PrintWriter out;
      String resource;

      try {
        // we read characters from the client via input stream on the socket
        in = new BufferedReader(new InputStreamReader(insocked.getInputStream()));
        // we get character output stream to client
        out = new PrintWriter(insocked.getOutputStream());
        // get first line of the request from the client
        String input = in.readLine();
        // we parse the request with a string tokenizer

        logger.info("sockedthread : " + input);

        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
        if (!method.equals("GET")) {
          logger.warn("501 Not Implemented : " + method + " method.");
        } else {
          // what comes after "localhost:8080"
          resource = parse.nextToken();
          System.out.println("input " + input);
          System.out.println("method " + method);
          System.out.println("resource " + resource);

          parse = new StringTokenizer(resource, "/[?]=&");
          int i = 0;
          String[] tokens = new String[20]; // more than the actual number of parameters
          while (parse.hasMoreTokens()) {
            tokens[i] = parse.nextToken();
            logger.info(i + " " + tokens[i]);
            i++;
          }

          // Here is where we send the request and get the answer inside it
          Request request = makeRequest(tokens);
          if (request != null) {
            String typeRequest = tokens[0];
            logger.info("created request " + typeRequest + " " + request);
            request.process();
            logger.info("processed request " + typeRequest + " " + request);
            // Make the answer as a JSON string, to be sent to the Javascript client
            String answer = makeJsonAnswer(request);
            logger.info("answer\n" + answer);
            // Here we send the response to the client
            out.println(answer);
            out.flush(); // flush character output stream buffer
          }
        }

        in.close();
        out.close();
        insocked.close(); // we close socket connection
      } catch (Exception e) {
        logger.error("Exception : " + e);
      }
    }

    private Request makeRequest(String[] tokens) {
      // always return request because it contains the answer for the Javascript client
      logger.info("tokens : ");
      for (String token : tokens) {
        logger.info(token + ", ");
      }
      logger.info("");

      Request request;
      // assertions below evaluated to false won't stop the webserver, just print an
      // assertion error, maybe because the webserver runs in a socked thread
      switch (tokens[0]) {
        case "refresh":
          request = new RequestRefresh();
          break;
        case "reader":
          request = makeRequestReader(tokens);
          break;
        case "area":
          request = makeRequestArea(tokens);
          break;
        case "get_children":
          //TODO: this is to be implemented when programming the mobile app in Flutter
          // in order to navigate the hierarchy of partitions, spaces and doors
          request = makeRequestChildren(tokens);
          break;
        default:
          // just in case we change the user interface or the simulator
          assert false : "unknown request " + tokens[0];
          request = null;
          System.exit(-1);
      }
      return request;
    }

    private RequestReader makeRequestReader(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], formatter);
      String doorId = tokens[8];
      return new RequestReader(credential, action, dateTime, doorId);
    }

    private RequestArea makeRequestArea(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], formatter);
      String areaId = tokens[8];
      return new RequestArea(credential, action, dateTime, areaId);
    }

    private RequestChildren makeRequestChildren(String[] tokens) {
      String areaId = tokens[1];
      return new RequestChildren(areaId);
    }

    private String makeHeaderAnswer() {
      String answer = "";
      answer += "HTTP/1.0 200 OK\r\n";
      answer += "Content-type: application/json\r\n";
      answer += "Access-Control-Allow-Origin: *\r\n";
      // SUPERIMPORTANT to avoid the CORS problem :
      // "Cross-Origin Request Blocked: The Same Origin Policy disallows reading
      // the remote resource..."
      answer += "\r\n"; // blank line between headers and content, very important !
      return answer;
    }

    private String makeJsonAnswer(Request request) {
      String answer = makeHeaderAnswer();
      answer += request.answerToJson().toString();
      return answer;
    }

  }

}