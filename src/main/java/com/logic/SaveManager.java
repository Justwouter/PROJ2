package com.logic;

import com.gui.ShopController;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Sizable class that contains all the logic for reading and writing saves
 */
public class SaveManager {
    //Methods for points/reis may be unneccesary
    Gson gson;
    boolean fancy = false;
    String dir = System.getProperty("user.dir")+"/data/";
    ArrayList<String> fileList = new ArrayList<>();

    public SaveManager(){
        this(false);
    }

    public SaveManager(boolean fancy){
        if(fancy){
            this.fancy = fancy;
            this.gson = new GsonBuilder().setPrettyPrinting().create();
            this.fileList = seedFancySaveFiles();
        }
        else{
            this.gson = new Gson();
            this.dir +="Legacy/";
            this.fileList = seedStandardSaveFiles();
        }
    }

    private ArrayList<String> seedStandardSaveFiles() {
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add(dir+"Users.json");
        fileList.add(dir+"Verhicles.json");
        fileList.add(dir+"Travels.json");
        fileList.add(dir+"Points.json"); 
        fileList.add(dir+"Locations.json"); 
        fileList.add(dir+"Items.json");
        return fileList;
    }

    private ArrayList<String> seedFancySaveFiles(){
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add(dir+"Users/");
        fileList.add(dir+"Verhicles/");
        fileList.add(dir+"Locations/");
        fileList.add(dir+"Items/");
        return fileList;
    }

    /**
     * Saves the currently existing Users & Transportmidddelen to their resective files.
     */
    public void saveState() {
        System.out.println("================");//Debug
        System.out.println("Making savestate");//Debug
        cleanAllFiles();

        if(fancy){
            System.out.println("Using fancy Save");//Debug
            fancySave();
        }
        else{
            System.out.println("Using Standard Save");//Debug
            standardSave();
        }
    }

    /**
     * Saves the currently loaded objects to their respective savefiles in the old or "Standard" JSON format.
     */
    private void standardSave(){
        for(User u : Leaderboard.getUsers("")){
            this.writeToSave(u);
        }
        for(Transportmiddel t : Transportmiddel.getTransportmiddelen()){
            this.writeToSave(t);
        }
        for(Filiaal f : Filiaal.filialen){
            this.writeToSave(f);
        }
        for (Item i : ShopController.itemList){
            this.writeToSave(i);
        }
    }

    /**
     * Saves the currently loaded objects to their respective savefiles in the "Fancy" JSON format.
     */
    private void fancySave(){
        for(User u : Leaderboard.getUsers("")){
            this.writeToFancySave(u);
        }
        for(Transportmiddel t : Transportmiddel.getTransportmiddelen()){
            this.writeToFancySave(t);
        }
        for(Filiaal f : Filiaal.filialen){
            this.writeToFancySave(f);
        }
        for (Item i : ShopController.itemList){
            this.writeToFancySave(i);
        }
    }

    /**
     * Loads the saved data from the respective savefiles.
     */
    public void loadAllFiles() {
        System.out.println("================");//Debug
        System.out.println("Loading files");//Debug
        if(fancy){
            loadFancyFiles();
        }
        else{
            loadStandardFiles();
        }
    }

    /**
     * Loads the SaveManager Savefiles written in "Fancy" JSON format.
     */
    private void loadFancyFiles(){
        
        for(String s : fileList){
            for(File f : findFancySaves(s)){
                if(f.exists()){
                    if(isUsersFile(s)){
                        Leaderboard.addUser(gson.fromJson(readFancyFile(f), User.class));
                    }
                    else if(isVerhiclesFile(s)){
                        Transportmiddel.transportmiddelen.add(gson.fromJson(readFancyFile(f), Transportmiddel.class));                    
                    }
                    else if(isShopFile(s)){
                        Filiaal.filialen.add(gson.fromJson(readFancyFile(f), Filiaal.class));
                    }
                    else if(isItemsFile(s)){
                        ShopController.itemList.add(gson.fromJson(readFancyFile(f), Item.class));
                    }
                }
                
            }
        }
    }

    /**
     * Searches the given directory for .json files.<p>
     * If no files are found, returns a non-existing file.
     * @param dir
     * @return Array of files in directory.
     */
    private File[] findFancySaves(String dir) {
        File f = new File(dir);
        File[] dataFiles = f.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        });
        if(dataFiles != null){
            return dataFiles;
        }
        else {
            //On Windows it is impossible to name a file "con" and on linux the C:\ drive (Usally) doesn't exist.
            File nonexist = new File("C:/con");
            File[] toArray = {nonexist};
            return toArray;
        }
        
    }

    /**
     * Loads the SaveManager savefiles written in the old or "Standard" JSON format.
     */
    private void loadStandardFiles(){
        for(String st : fileList){
            File f = new File(st);
            ArrayList<String> readLines = readFile(f);

            for(String s : readLines){
                if(isUsersFile(f.getName())){
                    Leaderboard.addUser(gson.fromJson(s, User.class));
                }
                else if(isVerhiclesFile(f.getName())){
                    Transportmiddel.transportmiddelen.add(gson.fromJson(s, Transportmiddel.class));                    
                }
                else if(isShopFile(f.getName())){
                    Filiaal.filialen.add(gson.fromJson(s, Filiaal.class));
                }
                else if(isItemsFile(f.getName())){
                    ShopController.itemList.add(gson.fromJson(s, Item.class));
                }
            }
        }
    }

    //Solution to long method/Switch smell

    private boolean isUsersFile(String s){
        return s.contains("Users");
    }

    private boolean isVerhiclesFile(String s){
        return s.contains("Verhicles");
    }

    private boolean isItemsFile(String s){
        return s.contains("Items");
    }

    private boolean isShopFile(String s){
        return s.contains("Locations");
    }

    /**
     * Writes the given {@link User} in JSON format to the Users file.
     * @param user the to be written User object.
     */
    private void writeToSave(User user) {
        File savefile = new File(dir+"Users.json");
        write(savefile, user);
    }

    /**
     * Writes the given {@link Transportmiddel} in JSON format to the Verhicles file.
     * @param transportmiddel the to be written Transportmiddel object.
     */
    private void writeToSave(Transportmiddel transportmiddel) {
        File savefile = new File(dir+"Verhicles.json");
        write(savefile, transportmiddel);
    }

    /**
     * Writes the given {@link Filiaal} in JSON format to the Filialen file.
     * @param filiaal the to be written Filiaal object.
     */
    private void writeToSave(Filiaal filiaal) {
        File savefile = new File(dir+"Filialen.json");
        write(savefile, filiaal);
    }

    /**
     * Writes the given {@link Item} in JSON format to the Filialen file.
     * @param item the to be written item object.
     */
    private void writeToSave(Item item) {
        File savefile = new File(dir+"Items.json");
        write(savefile, item);
    }


    /**
     * Writes the given {@link User} in JSON format to a file in the ~/data/Users/ dir.
     * @param user
     */
    private void writeToFancySave(User user) {
        File savefile = new File(dir+"Users/"+"user"+filesInDir("Users", "user")+".json");
        write(savefile, user);
    }
    /**
     * Writes the given {@link Transportmiddel} in JSON format to a file in the ~/data/Verhicles/ dir.
     * @param transportmiddel
     */
    private void writeToFancySave(Transportmiddel transportmiddel) {
        File savefile = new File(dir+"Verhicles/"+"vehicle"+filesInDir("Verhicles", "vehicle")+".json");
        write(savefile, transportmiddel);
    }
    /**
     * Writes the given {@link Filiaal} in JSON format to a file in the ~/data/Loactions/ dir.
     * @param filiaal
     */
    private void writeToFancySave(Filiaal filiaal) {
        File savefile = new File(dir+"Locations/"+"filiaal"+filesInDir("Locations", "filiaal")+".json");
        write(savefile, filiaal);
    }
    /**
     * Writes the given {@link Item} in JSON format to a file in the ~/data/Items/ dir.
     * @param item
     */
    private void writeToFancySave(Item item) {
        File savefile = new File(dir+"Items/"+"item"+filesInDir("Items", "item")+".json");
        write(savefile, item);
    }


    /**
     *
     * Writes any given object in JSON format to the given file.
     * @param  savefile The {@link File} where the data is saved to.
     * @param object Any {@link Object} will do.
     */
    private void write(File savefile, Object object) {
        try{
            checkFS(savefile);
            FileWriter writer = new FileWriter(savefile,true);
            try{savefile.createNewFile();}catch(Exception e){}
            writer.append(makeString(object)+"\n");
            writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Returns an object as a JSON-formatted string.
     * @param object
     * @return String
     */
    public String makeString(Object object) {
        return gson.toJson(object);
    }

    /**
     * Reads the lines of an JSON file located in ~\data\ and returns them as entries in an ArrayList.
     * @param savefile
     * @return {@link ArrayList<String>}
     */
    @Deprecated
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


    /**
     * Reads an JSON file and returns the read data.
     * @param savefile
     * @return {@link Reader}
     */
    private Reader readFancyFile(File savefile){
        try{
            Reader reader = Files.newBufferedReader(savefile.toPath());
            return reader;
        }
        catch(IOException e){System.out.println(e); return null;}
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
            File currentDir = new File(dir);
            rmDir(currentDir);
            currentDir.mkdir();
            return true;
        }
        catch(Exception e){System.out.println(e);}
        return false;        
    }

    /**
     * Recursively deletes every file/folder till relative ~ for the given dir.
     * @param file
     */
    private void rmDir(File file){
        File[] contents = file.listFiles();
        if (contents != null) {
            
            for (File f : contents) {
                try{
                    if(!f.getPath().contains("Legacy")){
                        rmDir(f);
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        file.delete();
    }

    /**
     * 
     * @param subdir the directory under project root where the data is stored.
     * @param filter Filenames contain this string.
     * @return int amount of files corresponding to the filter in the dir
     */
    private int filesInDir(String subdir, String filter){
        try{
            String[] jsonInDir =  new File(dir+subdir+"/").list(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.contains(filter) && name.endsWith(".json");
                }
            });
            if(jsonInDir != null){
                return jsonInDir.length;
            }
            return 0;
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
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
