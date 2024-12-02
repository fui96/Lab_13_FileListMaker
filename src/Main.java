import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.nio.file.StandardOpenOption.CREATE;


public class Main {
    private static final ArrayList<String> WorkSpace = new ArrayList<>();
    public static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Boolean Cont = false;
        Boolean NeedsToBeSaved = false;
        int Newpos = 0;
        int Index = 0;
        Path WorkingPath = null;
        try {


            do {
                String Input = "";
                System.out.println("Please select an option");
                System.out.println(" Add \n Delete \n Insert \n View \n Move \n Open \n Save \n Clear \n Quit");
                String Addition = "";
                Input = in.nextLine();
                int Pos = 0;
                // Basic Add
                if (isMatch(Input, "^[Aa]")) {
                    Addition = SafeInput.getNonZeroLenString(in,"Please enter what you would like to add");
                    AddToList(Addition);
                    NeedsToBeSaved = true;
                }
                // Delete
                else if (isMatch(Input, "^[Dd]")) {
                    ViewList();
                    Pos = SafeInput.getInt(in,"What would you like to remove?") - 1;
                    DeleteFromList(Pos);
                    ViewList();
                    NeedsToBeSaved = true;

                }
                //Insert
                else if (isMatch(Input, "^[Ii]")) {
                    SafeInput.getRangedInt(in,"Please enter the index you would like",0,(WorkSpace.size()-1));
                    SafeInput.getNonZeroLenString(in,"Please enter what you would like to add");
                    NeedsToBeSaved = true;
                }
                //View
                else if (isMatch(Input, "^[Vv]")) {
                    ViewList();
                    NeedsToBeSaved = true;
                }
                //Move
                else if (isMatch(Input, "^[Mm]")) {
                    ViewList();
                    Index = (SafeInput.getInt(in,"Please enter the line number that you would like to move") - 1);
                    Newpos = SafeInput.getRangedInt(in,"Please enter the location you would like to move it to",0,WorkSpace.size() - 1);
                    MoveInList(Index, Newpos);
                    NeedsToBeSaved = true;
                }
                //Open
                else if (isMatch(Input, "^[Oo]")) {
                    if(NeedsToBeSaved) {
                        Boolean Save = SafeInput.getYNConfirm(in,"You have unsaved changes \n Would you like to save the list?");
                        if(Save) {
                            File WorkingFile = WorkingPath.toFile();
                            SaveList(WorkingFile);
                        }
                        else {
                            break;
                        }
                    }
                    WorkSpace.clear();
                    WorkingPath = OpenFile();
                    InputStream Reader =
                            new BufferedInputStream(Files.newInputStream(WorkingPath, CREATE));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(Reader));
                    while(reader.ready()){
                        String line = reader.readLine();
                        if(line != null && !line.isEmpty()){
                        WorkSpace.add(line);
                        }
                    }
                }
                //Save
                else if (isMatch(Input, "^[Ss]")) {
                   File WorkingFile = WorkingPath.toFile();
                   SaveList(WorkingFile);
                }
                //Clear
                else if (isMatch(Input, "^[Cc]")) {
                    ClearList();
                }
                // Quit
                else if (isMatch(Input, "^[Qq]")) {
                    if(NeedsToBeSaved) {
                        Boolean Save = SafeInput.getYNConfirm(in,"You have unsaved changes \n Would you like to save the list?");
                        if(Save) {
                            File WorkingFile = WorkingPath.toFile();
                            SaveList(WorkingFile);
                        }
                        else {
                            break;
                        }
                    }
                    Cont = SafeInput.getYNConfirm(in, "Are you sure you want to quit? [Y/N]");
                }
            } while (!Cont);
        }catch (Exception e) {
            System.out.println("File not Found!");

        }

    }
    //Menu Selection
    private static Boolean isMatch(String Input, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Input);
        Boolean retbool = false;
        retbool = matcher.find();
        return retbool;
    }
    //Add to list
    private static void AddToList(String input) {
        WorkSpace.add(input);

    }
    //Delete
    private static void DeleteFromList(int pos){
        WorkSpace.remove(pos);
    }
    //Insert
    private static void listInsert(int Index, String Addition){
        WorkSpace.add(Index,Addition);
    }
    //View List
    private static void ViewList(){
        for(int i = 0; i < WorkSpace.size(); i++){
            System.out.println( (i + 1 ) + " " + WorkSpace.get(i));
        }
    }
    //Move
    private static void MoveInList(int Index, int newpos){
        String MovingItem  = WorkSpace.get(Index);
        WorkSpace.remove(Index);
        WorkSpace.add(newpos, MovingItem);
    }
    //Open File
    private static Path OpenFile() throws IOException {
        String FileName = SafeInput.getNonZeroLenString(in, "Please enter the file name");
        String WorkingDir = System.getProperty("user.dir") + "/src";
        String FullFileName = WorkingDir + "/" + FileName + ".txt";
        File DataFile = new File(FullFileName);
        Path WorkingFile = DataFile.toPath();
        if (DataFile.createNewFile()) {
            System.out.println("File created: " + FullFileName);
            return WorkingFile;
        } else {
            System.out.println("File already exists");
            return WorkingFile;
        }

    }
    //Save
    private static void SaveList(File DataFile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(DataFile));
        for(int i = 0; i < WorkSpace.size(); i++) {
            bw.write(WorkSpace.get(i));
            bw.newLine();
        }
        bw.close();

    }
    //Clear
    private static void ClearList(){
        WorkSpace.clear();
    }


}