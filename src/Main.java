import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

import static java.nio.file.StandardOpenOption.CREATE;


public class Main {
    private static final ArrayList<String> WorkSpace = new ArrayList<>();
    private static final JFileChooser fc = new JFileChooser();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Boolean Cont = false;
        Boolean NeedsToBeSaved = false;
        String WorkingFile = null;

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
                    String response = "";
                    SafeInput.getNonZeroLenString(in,"Please enter what you would like to add");
                    AddToList(response);
                }
                // Delete
                else if (isMatch(Input, "^[Dd]")) {
                    ViewList();

                }
                //Insert
                else if (isMatch(Input, "^[Ii]")) {
                    SafeInput.getRangedInt(in,"Please enter the index you would like",0,(WorkSpace.size()-1));
                    SafeInput.getNonZeroLenString(in,"Please enter what you would like to add");
                }
                //View
                else if (isMatch(Input, "^[Vv]")) {
                    ViewList();
                }
                //Move
                else if (isMatch(Input, "^[Mm]")) {

                }
                //Open
                else if (isMatch(Input, "^[Oo]")) {
                    WorkingFile = OpenFile(fc,WorkSpace);
                    Path WorkingPath = Paths.get(WorkingFile);
                    InputStream Reader =
                            new BufferedInputStream(Files.newInputStream(WorkingPath, CREATE));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(Reader));
                    while(reader.ready()){
                        reader.readLine();
                        WorkSpace.add(reader.readLine());
                    }
                }
                //Save
                else if (isMatch(Input, "^[Ss]")) {

                }
                //Clear
                else if (isMatch(Input, "^[Cc]")) {

                }
                // Quit
                else if (isMatch(Input, "^[Qq]")) {
                    Cont = !SafeInput.getYNConfirm(in, "Are you sure you want to quit? [Y/N]");
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
    //Insert
    private static void listInsert(int Index, String Addition){
        WorkSpace.add(Index,Addition);
    }
    //View List
    private static void ViewList(){
        for(int i = 0; i < WorkSpace.size(); i++){
            System.out.println(WorkSpace.get(i));
        }
    }

    //Open File
    private static String OpenFile(JFileChooser fc,ArrayList<String> WorkSpace) throws IOException {
        File workingDirectory = new File(System.getProperty("user.dir") + "/src");
        fc.setCurrentDirectory(workingDirectory);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            System.out.println("The file " + selectedFile.getName() + " is opened!");
            return selectedFile.getAbsolutePath();
        }
        else{
            throw new FileNotFoundException("File selection cancelled by user");
        }

    }

}