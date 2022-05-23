package com.logic;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class SaveManager {
    public static Gson gson = new Gson();

    /**
     * Writes the given {@link User} in JSON format to the Users file.
     * @param user the to be written User object
     */
    public static void writeToSave(User user) {
        String dir = System.getProperty("user.dir")+"\\data\\";
        File savefile = new File(dir+"Users.json");
        write(savefile, user);
    }

    /**
     * Writes the given {@link Reis} in JSON format to the Travels file.
     * @param reis the to be written Reis object
     */
    public static void writeToSave(Reis reis) {
        String dir = System.getProperty("user.dir")+"\\data\\";
        File savefile = new File(dir+"Travels.json");
        write(savefile, reis);
    }
    /**
     * Writes the given {@link Transportmiddel} in JSON format to the Verhicles file.
     * @param transportmiddel the to be written Transportmiddel object
     */
    public static void writeToSave(Transportmiddel transportmiddel) {
        String dir = System.getProperty("user.dir")+"\\data\\";
        File savefile = new File(dir+"Verhicles.json");
        write(savefile, transportmiddel);
    }

    public static void writeToSave(Point point) {
        String dir = System.getProperty("user.dir")+"\\data\\";
        File savefile = new File(dir+"Points.json");
        write(savefile, point);
    }

    /**
     * Support method
     * <p>
     * Writes any given object in JSON format to the given file.
     * @param  savefile The {@link File} where the data is saved to.
     * @param object Any {@link Object} will do.
     */
    private static void write(File savefile, Object object) {
        try{
            FileWriter writer = new FileWriter(savefile,true);
            try{savefile.createNewFile();}catch(Exception e){} //Simple Onliners good?
            writer.append("\n"+makeString(object)+"\n");
            writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Returns an object as a JSON-formatted string
     * @param object
     * @return String
     */
    public static String makeString(Object object) {
        return gson.toJson(object);
    }



    public static void load(){
        
        
        for(String s : readFile("Users")){
            User newStudent = gson.fromJson(s, User.class);
            Leaderboard.addUser(newStudent);
            System.out.println(newStudent.naam);
        }
        System.out.println("==========");

    }



    /**
     * Reads the lines of an JSON file located in ~\data and returns them as entries in an ArrayList
     * @param file
     * @return {@link ArrayList<String>}
     */
    private static ArrayList<String> readFile(String file) {
        String dir = System.getProperty("user.dir")+"\\data\\";
        File savefile = new File(dir+file+".json");
        ArrayList<String> saveFileContents = new ArrayList<>();
        try {
            Scanner james = new Scanner(savefile);
            while (james.hasNextLine()) {
                String contents = james.nextLine();
                if(!contents.equals("")){
                    saveFileContents.add(contents);
                }
                
            }
        }
        catch(Exception e){}
        return saveFileContents;
    }
}
