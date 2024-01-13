/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import controller.ClientController;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slovo;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class Komunikacija extends Thread {
    private static Komunikacija instance;

    private Socket socket;
    
    
    private Komunikacija() {
        try {
            socket=new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
       
        
        
        while(true){
            ServerskiOdgovor so=primiOdgovor();
          /*  System.out.println("SLOVA KOD KLIJENTA: ");
            for(Slovo s:(ArrayList<Slovo>)so.getOdgovor()){
                System.out.println(s.getSlovo());
            }*/
            switch(so.getOperacija()){
                case Operacije.OBAVESTI_KLIJENTA:
                    ClientController.getInstance().obavestiKlijentaOPocetku();
                break;
                
                case Operacije.IGRAJ:
                    ClientController.getInstance().dodajSlovaNaFormu((ArrayList<Slovo>)so.getOdgovor());
                    break;
                    
                    
                case Operacije.KRAJ:
                    ClientController.getInstance().obavesti((String)so.getOdgovor());
                
                
            }
            
            
            
        }
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    public static Komunikacija getInstance(){
        if(instance==null)
            instance=new Komunikacija();
        
        return instance;
    }
    
    
    public void posaljiZahtev(KlijentskiZahtev kz){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ServerskiOdgovor primiOdgovor(){
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
    
}
