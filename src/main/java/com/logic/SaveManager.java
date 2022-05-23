package com.logic;

import java.io.File;
import java.io.FileWriter;

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
            writer.append(makeString(object)+"\n");
            writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static String makeString(Object object) {
        return gson.toJson(object);
    }
}
