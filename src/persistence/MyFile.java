package persistence;

import model.Cyclist;

import java.io.*;
import java.util.ArrayList;

public class MyFile {

    File f;
    FileWriter fileWriter;
    FileReader fileReader;
    BufferedWriter bw = null;
    BufferedReader br = null;

    public MyFile(String nameFile){
        f = new File(nameFile);
    }

    public ArrayList<String> readFile (String nameFile)throws IOException{
        ArrayList<String> linesRead = new ArrayList<>();
        FileReader fileReader = new FileReader(nameFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lineRead;

        while((lineRead = bufferedReader.readLine()) != null){
            linesRead.add(lineRead);
        }
        bufferedReader.close();
        return linesRead;
    }

    public void openFile(char modo)	{
        try {
            //modo escritura "w" crea el archivo
            if (modo=='w'){
                fileWriter = new FileWriter(f);
                bw = new BufferedWriter(fileWriter);
            }
            else{
                //modo lectura "r" abre el archivo
                fileReader = new FileReader(f);
                br = new BufferedReader(fileReader);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Almacena la cadena ingresada por parametro
    public void recordFile(String cad){
        if (bw!=null){
            try {
                bw.write(cad);
                bw.newLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    // Lee una linea del archivo
    public String readFile(){
        String cad = "";
        try {
            cad= br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cad;
    }
    // cierra archivo modo R/W
    public void closeFile(){
        try {
            if (br!=null)
                br.close();
            if (bw!=null)
                bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
