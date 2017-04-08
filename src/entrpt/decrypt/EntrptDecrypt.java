/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrpt.decrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EntrptDecrypt {

    /**
     * @param args the command line arguments
     */
    public static void encrypt(String key) throws FileNotFoundException, IOException{
        Path path = Paths.get("input.txt");
        byte[] data = Files.readAllBytes(path);
        byte[] k=key.getBytes();
        for(int i=0;i<data.length;i++){
            data[i]=(byte)(data[i]^k[(i%k.length)]);
        }
        int per=k.length%data.length;
        byte[] p1=Arrays.copyOfRange(data, 0, per);
        byte[] p2=Arrays.copyOfRange(data, per, data.length);
        byte[] c = new byte[p1.length+p2.length];
        int count = 0;
      
        for(int i = 0; i<p2.length; i++) { 
         c[i] = p2[i];
         count++;
        } 
        for(int j = 0;j<p1.length;j++) { 
         c[count++] = p1[j];
        } 

        FileOutputStream fos = new FileOutputStream("output.txt");
        fos.write(c);
        fos.close();
       
    
    }
    
    public static void decrypt(String key) throws IOException{
        Path path = Paths.get("output.txt");
        byte[] data = Files.readAllBytes(path);
        byte[] k=key.getBytes();
        int per=k.length%data.length;
        byte[] p1=Arrays.copyOfRange(data, 0,data.length- per);
        byte[] p2=Arrays.copyOfRange(data,data.length- per, data.length);
        byte[] c = new byte[p1.length+p2.length];
        int count = 0;
      
        for(int i = 0; i<p2.length; i++) { 
         c[i] = p2[i];
         count++;
        } 
        for(int j = 0;j<p1.length;j++) { 
         c[count++] = p1[j];
        } 
        
        for(int i=0;i<data.length;i++){
            c[i]=(byte)(c[i]^k[(i%k.length)]);
        }
        
        FileOutputStream fos = new FileOutputStream("outputde.txt");
        fos.write(c);
        fos.close();
    
    }
    public static void main(String[] args) {
        Home h=new Home();
        h.setVisible(true);
        
        // TODO code application logic here
    }
    
}
