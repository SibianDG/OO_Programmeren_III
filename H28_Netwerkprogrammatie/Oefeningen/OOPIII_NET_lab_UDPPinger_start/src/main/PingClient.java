package main;

import java.io.IOException;
import java.net.*;
import java.rmi.server.ExportException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PingClient {
    private InetAddress host ;
    private String hostName = "localhost"; //default
    private int portnr = 5555;  //default
    private final int PINGAANTAL = 10;
    private final int TOKEN_TIMESTAMP = 2; //positie in packet
    private final int MAX_WAIT_TIME = 1000;
    private long min = 999999, max = 0, somRTT = 0;
    private int aangekomen = 0;

    public static void main(String[] args) {
        new PingClient().run(args);
    }

    public void run(String[] args)  {
        try {
            if (args.length > 0) {
                hostName = args[0];
            }
            if (args.length == 2) {
                portnr = Integer.parseInt(args[1]);
            }
            //maak netwerkconnectie
            host = InetAddress.getByName(hostName);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setSoTimeout(MAX_WAIT_TIME);

            // verstuur PINGAANTAL keer een bericht met huidig tijdstip
            int pingNr = 1;
            for (int ping = 1; ping < PINGAANTAL; ping++) {
                // data voor bericht:
                SimpleDateFormat timeNow = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String timedStr = timeNow.format(new Date(System.currentTimeMillis()));
                String message = String.format("Ping #%d %s (%s)", pingNr, System.currentTimeMillis(), timedStr);
                // verpak bericht, verstuur en wacht op antwoord print de ontvangenData
                // update de teller
                DatagramPacket ping_verzoek = new DatagramPacket(message.getBytes(), message.length(), host, portnr);
                datagramSocket.send(ping_verzoek);
                DatagramPacket ping_antwoord = new DatagramPacket(new byte[message.length()], message.length());
                try {
                    datagramSocket.receive(ping_antwoord);
                    aangekomen++;
                    printDataEnUpdateStat(ping_antwoord);
                } catch (SocketTimeoutException stoe) {
                    System.out.printf("Ping #%d: No response was recieved form the server%n", ping);
                }
            }

            System.out.printf("min: %d, max: %d, gem: %d, packets loss: %.0f%%%n", min, max, somRTT/PINGAANTAL
                                                                                     ,(PINGAANTAL-aangekomen)/(double)PINGAANTAL*100);
        } catch (UnknownHostException e) {
            System.out.println("Onbekende host: " + e.getMessage());
        } catch (SocketException e){
            System.out.println("Geen datagramsocket " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Probleem: ");
            e.printStackTrace();
        }
                
    }

    private void printDataEnUpdateStat(DatagramPacket request) {
        //haal de info uit het ontvangen packet, toon op de console
        String response = new String(request.getData(), request.getOffset(), request.getLength());
        String[] tokens = response.split(" ");
        long verzonden_timestamp = Long.parseLong(tokens[TOKEN_TIMESTAMP]);
        long ontvangen_timestamp = System.currentTimeMillis();
        long rtt = ontvangen_timestamp - verzonden_timestamp;
        System.out.printf("%s  Received from %s (RTT=%dms)%n", response,  request.getAddress().getHostAddress(), rtt);
        updateRTTs(rtt);
    }

    private void updateRTTs(long rtt) {
        //bereken  min, max, somRTT (voor gemiddelde)
        if (min > rtt){
            min = rtt;
        }
        if (max < rtt) {
            max = rtt;
        }
        somRTT += rtt;
    }
}