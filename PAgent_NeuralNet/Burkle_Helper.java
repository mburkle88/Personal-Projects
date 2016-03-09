/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mollie
 */
import java.math.*;

public class Burkle_Helper extends EstherTools {
    
    double[] updatedPocket;
    int card1;
    int card2;
    TableData table;
    double position;
  
    public double[] convertPocket(int[] pocket) {
        
        card1= (pocket[0]%13);
        card2= (pocket[1]%13);
        double round1= Math.round((card1/13)*10)/10;
        double round2= Math.round((card2/13)*10/10);
        updatedPocket[0]= round1;
        updatedPocket[1]= round2;
        
        return updatedPocket;
    }
    
    public double sameSuit(int[] pocket) {
        
        if ((pocket[0]%13)== (pocket[1]%13)) {
            return 1.0;
         }
        else {
            return 0.0;
        }
    }
    
    public double normPosition (int seat) {
        
        position= (seat/table.getPlayerCount());
        double roundOff= Math.round(position*10)/10;
        return roundOff;
    }
}
