package com.logic;

import com.gui.ShopController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;


/**
 * Sizable class that contains all the logic for reading and writing saves
 */
public class SaveManager {
    //Methods for points/reis may be unneccesary
    Gson gson = new Gson();
    String dir = System.getProperty("user.dir")+"\\data\\";
    ArrayList<File> fileList = new ArrayList<>(seedSaveFiles());


    //Temp solution
    public ArrayList<File> seedSaveFiles() {
        ArrayList<File> fileList = new ArrayList<>();
        fileList.add(new File(dir+"Users.json"));
        fileList.add(new File(dir+"Verhicles.json"));
        fileList.add(new File(dir+"Travels.json"));
        fileList.add(new File(dir+"Points.json")); 
        fileList.add(new File(dir+"Filialen.json")); 
        fileList.add(new File(dir+"Items.json"));
        return fileList;
    }


    /**
     * Saves the currently existing Users & Transportmidddelen to their resective files
     */
    public void saveState() {
        System.out.println("================");//Debug
        System.out.println("Making savestate");//Debug
        cleanAllFiles();
      
        for(User u : Leaderboard.getUsers("")){
            this.writeToSave(u);
        }
        for(Transportmiddel t : Transportmiddel.getTransportmiddelen()){
            this.writeToSave(t);
        }
        for(Filiaal f : Filiaal.filialen){
            SaveManager.writeToSave(f);
        }
        for (Item i : ShopController.itemList){
            SaveManager.writeToSave(i);
        }
    }

    /**
     * Loads the saved data from the respective savefiles
     */
    public void loadAllFiles() {
        System.out.println("================");//Debug
        System.out.println("Loading files");//Debug
        
        for(File f : fileList){
            ArrayList<String> readLines = readFile(f);

            for(String s : readLines){
                if(isUsersFile(f.getName())){
                    Leaderboard.addUser(gson.fromJson(s, User.class));
                }
                else if(isVerhiclesFile(f.getName())){
                    Transportmiddel.transportmiddelen.add(gson.fromJson(s, Transportmiddel.class));                    
                }
                else if(isTravelsFile(f.getName())){
                    gson.fromJson(s, Reis.class);                    
                }
                else if(isPointsFile(f.getName())){
                    gson.fromJson(s, Point.class);
                }
                else if(f.getName().contains("Filialen")){
                    Filiaal.filialen.add(gson.fromJson(s, Filiaal.class));
                }
                else if(f.getName().contains("Items")){
                    ShopController.itemList.add(gson.fromJson(s, Item.class));
                }
            }
        }
    }

    //Solution to long method/Switch smell

    public boolean isUsersFile(String s){
        return s.contains("Users");
    }

    public boolean isVerhiclesFile(String s){
        return s.contains("Verhicles");
    }

    public boolean isTravelsFile(String s){
        return s.contains("Travels");
        
    }

    public boolean isPointsFile(String s){
        return s.contains("Points");
    }






    /**
     * Writes the given {@link User} in JSON format to the Users file.
     * @param user the to be written User object
     */
    public void writeToSave(User user) {
        File savefile = new File(dir+"Users.json");
        write(savefile, user);
    }

    /**
     * Writes the given {@link Reis} in JSON format to the Travels file.
     * @param reis the to be written Reis object
     */
    public void writeToSave(Reis reis) {
        File savefile = new File(dir+"Travels.json");
        write(savefile, reis);
    }
    /**
     * Writes the given {@link Transportmiddel} in JSON format to the Verhicles file.
     * @param transportmiddel the to be written Transportmiddel object
     */
    public void writeToSave(Transportmiddel transportmiddel) {
        File savefile = new File(dir+"Verhicles.json");
        write(savefile, transportmiddel);
    }

    public void writeToSave(Point point) {
        File savefile = new File(dir+"Points.json");
        write(savefile, point);
    }

    /**
     * Writes the given {@link Filiaal} in JSON format to the Filialen file.
     * @param filiaal the to be written Filiaal object
     */
    public static void writeToSave(Filiaal filiaal) {
        File savefile = new File(dir+"Filialen.json");
        write(savefile, filiaal);
    }

    public static void writeToSave(Item item) {
        File savefile = new File(dir+"Items.json");
        write(savefile, item);
    }

    /**
     * Support method
     * <p>
     * Writes any given object in JSON format to the given file.
     * @param  savefile The {@link File} where the data is saved to.
     * @param object Any {@link Object} will do.
     */
    private void write(File savefile, Object object) {
        try{
            checkFS(savefile);
            FileWriter writer = new FileWriter(savefile,true);
            try{savefile.createNewFile();}catch(Exception e){} //Simple Onliners good?
            writer.append(makeString(object)+"\n");
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
    public String makeString(Object object) {
        return gson.toJson(object);
    }

    /**
     * Reads the lines of an JSON file located in ~\data\ and returns them as entries in an ArrayList
     * @param savefile
     * @return {@link ArrayList<String>}
     */
    private ArrayList<String> readFile(File savefile) {
        ArrayList<String> saveFileContents = new ArrayList<>();
        try {
            Scanner james = new Scanner(savefile);
            while (james.hasNextLine()) {
                String contents = james.nextLine();
                if(!contents.equals("")){
                    saveFileContents.add(contents);
                }
            }
            james.close();
        }
        catch(Exception e){}
        return saveFileContents;
    }

    //FileSystem checks

    /**
     * Cleans a file by deleting & remaking it.
     * @param savefile the {@link File} object to be cleaned.
     * @param makefile If you wish to only create a file.
     * @return boolean true if successfull, false if not.
     * @throws IOException If the file does not exist
     */
    public boolean cleanFile(File savefile,boolean makefile) {
        try{
            if(savefile.delete()){
                return savefile.createNewFile();
            }
            else if(makefile){
                return savefile.createNewFile();
            }
            return false;
        }
        catch(IOException e){
            System.out.println(e);
            return false;
        }
    }

    /**
     * Cleans all the files made by SaveManager.
     * @return {@code True} if succesfull.<p>
     *         {@code False} if not.
     */
    public boolean cleanAllFiles() {
        try{
            for(File f : fileList){
                f.delete();
                f.createNewFile();
            }
            return true;
        }
        catch(Exception e){System.out.println(e);}
        return false;        
    }

    /**
     * Checks if the file/filesystem exists. If not it calls {@link #generateFS(File)}.
     * @param file
     * @return {@code true} if the filesystem was succesfully created or already exists.<p>
     *         {@code false} if the filesystem couldn't be made.
     */
    public boolean checkFS(File file){
        try{
            if(file.exists()){
                return true;
            }
            else if(generateFS(file)){
                file.createNewFile();
                return true;
            }
            return false;
        }
        catch(Exception e){System.out.println(e);}
        return false;
        
    }

    /**
     * Generates the Filesystem for a specified file.
     * @param file
     * @return {@code true} if the filesystem was succesfully created.<p>
     *         {@code false} if the filesystem couldn't be made.
     */
    public boolean generateFS(File file) {
        //Split the actual file from the dirs
        String path = file.getAbsolutePath();
        String[] splitPath = path.split("\\\\");
        //Recombine the split string, discarding the last object in the path
        String newPath = "";
        for(int i=0;i<splitPath.length-1;i++){
            newPath += splitPath[i]+"\\";
        }
        //Make a new file with only the dir path & generate said path
        File fsGenFile = new File(newPath);
        if(fsGenFile.mkdirs()){
            return true;
        }
        return false;
    }
}
