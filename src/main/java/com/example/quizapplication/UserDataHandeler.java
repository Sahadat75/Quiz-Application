package com.example.quizapplication;

import java.io.*;

public class UserDataHandeler {
    private static final String FILE_PATH = "/com/example/quizapplication/Database/users.txt";

    public static boolean saveUser(String username, String id, String program, String password) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(username + "," + id + "," + program + "," + password);
                writer.newLine();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean userExists(String id) {
        File file = new File(FILE_PATH);
        if (!file.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(id)) {
                    return true; // user already exists
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
