package cz2002assignment;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static ArrayList<ArrayList<String> > readFileByLines(String fileName) {

        ArrayList<ArrayList<String> > recordList = new ArrayList<ArrayList<String> >(); 
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {//outside array
                recordList.add(new ArrayList<String>());
                String val="";
                for (int i = 0; i < tempString.length(); i++) {//inner array
                    if (tempString.charAt(i)!='|') {
                        val=val+tempString.charAt(i);
                    }
                    else {
                        recordList.get(line).add(val);
                        val="";
                    }
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return recordList;
    }
    
    public static void writeFile1(String filename,ArrayList<String> writeData) throws IOException {
    	
    	ArrayList<ArrayList<String> > writeRec = new ArrayList<ArrayList<String> >(); 
    	writeRec = readFileByLines(filename);
    	File fout = new File(filename);
    	FileOutputStream fos = new FileOutputStream(fout);
    	writeRec.add(writeData);
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
     
    	for (int i = 0; i < writeRec.size(); i++) {
    		for (int j = 0; j < writeRec.get(i).size(); j++) {
				bw.write(writeRec.get(i).get(j));
				bw.write('|');
			}	
    		bw.newLine();
		}     
    	bw.close();
    }
    
public static void writeFileDelerow(String filename,ArrayList<String> deleteData) throws IOException {
    	
    	ArrayList<ArrayList<String> > writeRec = new ArrayList<ArrayList<String> >(); 
    	writeRec = readFileByLines(filename);
    	File fout = new File(filename);
    	FileOutputStream fos = new FileOutputStream(fout);
    	for(int i=0;i<writeRec.size();i++) {
    		if(writeRec.get(i).get(1).equals(deleteData.get(1))) {
    			if(writeRec.get(i).get(2).equals(deleteData.get(2))) {
    				if(writeRec.get(i).get(3).equals(deleteData.get(3))) {
            			writeRec.remove(i);
            		}
        		}
    		}
    	}
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
     
    	for (int i = 0; i < writeRec.size(); i++) {
    		for (int j = 0; j < writeRec.get(i).size(); j++) {
				bw.write(writeRec.get(i).get(j));
				bw.write('|');
			}	
    		bw.newLine();
		}     
    	bw.close();
    }


}