package domein;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class FileServer {
    //attributen voor netwerkconnectie en streams
    private final String EOF= "*E*O*F*";
    private Socket socket;
    private Scanner socketInput;
    private Formatter socketOutput;

    public void run() {
        //initialiseer server
        try (ServerSocket serverSocket = new ServerSocket(44444, 10)) { //10: "wachtende klanten"
            System.out.println("Fileserver up");
            //wacht tot een client verbindig maakt
            //verwerk al de verzoeken van een client tot deze afsluit
            //          delegeer naar hulpmethode processClient
            //wacht opnieuw op een client, blijft dit doen
            while (true) {
                try {
                    System.out.println("Fileserver waiting...");
                    socket = serverSocket.accept();
                    processClient(); //HIER actie in afzonderlijke thread starten
                                //=> Dan heb je een multihreaded server
                } catch (IOException ex) {
                    System.out.println("Problemen : " + ex.getMessage());
                } 
            }
        } catch (IOException ex) {
            System.out.println("Problemen met server connectie : " + 
                                                ex.getMessage());
        } 
    }

    private void processClient() {
        //verwerk al de verzoeken van een client volgens het afgesproken protocol 
        //tot deze afsluit
        //sluit dan ook de connectie met deze client
        //maak gebruik van de 3 onderstaande hulpmethoden
        try {
            socketInput = new Scanner(socket.getInputStream());
            socketOutput = new Formatter(socket.getOutputStream());
            File file;
            while (socketInput.hasNextLine()){
                String actie = socketInput.nextLine();
                switch (actie){
                    case "READ" -> {
                        file = new File(socketInput.nextLine());
                        System.out.println("READ " + file.getName());
                        if (file.exists()){
                            sendFile(file);
                        } else {
                            sendNoFile();
                        }
                    }
                    case "REWRITE" -> {
                        file = new File(socketInput.nextLine());
                        System.out.println("REWRITE " + file.getName());
                        readAndSaveUpdateFile(file);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Problemen met client connectie : " + 
                    ex.getMessage());
        }
    }

    private void sendFile(File file) throws IOException {
        socketOutput.format("%s%n", "FOUND");
        socketOutput.flush();
        //nog iets
        try (Scanner diskFile = new Scanner(file)){
            while(diskFile.hasNext()){
                socketOutput.format("%s%n", diskFile.nextLine());
                //socketOutput.flush();
            }
            socketOutput.format("%s%n", EOF);
            socketOutput.flush();
        }
    }

    private void sendNoFile() {
        socketOutput.format("%s%n", "NOT FOUND");
        socketOutput.flush();
    
    }
    
    private void readAndSaveUpdateFile(File file) throws IOException {
        try (Formatter naarDisk = new Formatter(file)){
            String line;
            while (!(line = socketInput.nextLine()).equals(EOF)){
                naarDisk.format("%s%n", line);
            }
        }
    }
}