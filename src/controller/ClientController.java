/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KlijentskaForma;
import java.util.ArrayList;
import model.Slovo;

/**
 *
 * @author Acer
 */
public class ClientController {
    private static ClientController instance;
    
    private KlijentskaForma cf;
    
    private ClientController(){
        
    }
    
    public static ClientController getInstance(){
        if(instance==null)
            instance=new ClientController();
        
        return instance;
    }

    public KlijentskaForma getCf() {
        return cf;
    }

    public void setCf(KlijentskaForma cf) {
        this.cf = cf;
    }

    public void obavestiKlijentaOPocetku() {


        cf.obavestiKlijentaOPocetku();


    }

    public void dodajSlovaNaFormu(ArrayList<Slovo> arrayList) {


        
        System.out.println("CLIENT CONTROLLER JE DOBIO OD SERVERA SLEDECA SLOVA: ");
        for(Slovo s:arrayList){
            System.out.println(s.getSlovo());
        }
        
        
        cf.dodajSlovaNaFormu(arrayList);

    }

    public void obavesti(String string) {

        cf.obavesti(string);

    }
    
    
    
    
    
    
}
