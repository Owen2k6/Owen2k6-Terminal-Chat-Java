package com.owen2k6.chat.configuration;

import com.google.gson.Gson;
import com.owen2k6.chat.Server;
import com.owen2k6.chat.account.user;
import com.owen2k6.chat.network.messageoftheday;
import com.owen2k6.chat.threads.Client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class motd extends Client {

    public static String MOTD = "Welcome to wyvern";
    public static Gson gson = new Gson();
    public motd(Server server, Socket socket) {
        super(server, socket);
    }

    public static String getMOTD(){
        return(MOTD);
    }

    public static void load() {
        try (FileReader reader = new FileReader("data/settings/motd.json")) {
            messageoftheday motive = gson.fromJson(reader, messageoftheday.class);
            MOTD = motive.message;
            System.out.println("Created MOTD Setting file");
        } catch(IOException h){
            System.out.println("Ay ay ay! Dios Mio");
            try {
                save("Welcome to wyvern");
            } catch (IOException e) {
                System.out.println("Shisse");
            }
        }
    }

    public static void save(String i) throws IOException {
        try (FileWriter writer = new FileWriter("data/settings/motd.json")) {
            messageoftheday motive = new messageoftheday();
            if(i == null || i.isEmpty())
                i = "Welcome to wyvern";
            motive.message = i;
            gson.toJson(motive, messageoftheday.class, writer);
        }
    }
}
