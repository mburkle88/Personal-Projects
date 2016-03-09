/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.*;
import java.util.*;

/**
 *
 * @author Mollie
 */
public class BloomFilter {
    double FP_Rate;
    int num_Keys;
    int BVect_Size;
    int hash_Num;
    HashFactory functions;
    BitSet BitVector;
    
    
    public BloomFilter(double c, int n) {
        FP_Rate = c;
        num_Keys= n;
        BVect_Size = calculateM();
        hash_Num = calculateK();
        functions = new HashFactory(hash_Num);
        BitVector = new BitSet(BVect_Size);
    }
    public BloomFilter(double c, int n, int m) {
        FP_Rate = c;
        num_Keys= n;
        BVect_Size = m;
        hash_Num = calculateK();
        functions = new HashFactory(hash_Num);
        BitVector = new BitSet(BVect_Size);
    }
    
    private int calculateK() {
        int m;
        double k;
        m = calculateM();
        k = Math.round((m/num_Keys)*(Math.log(2)));
        return (int)k;
    }
    
    private int calculateM() {
        double m;
        double temp;
        m = ((num_Keys * Math.log(FP_Rate))/Math.pow(Math.log(2), 2));
        return Math.abs((int)m);
    }
    
    public void add(int value) {
        int[] positions = functions.hash(value, BitVector.size());
        for(int i = 0; i < positions.length; i++) {
                BitVector.flip(positions[i]);
            }
    }
    
    public boolean checkSet(int value) {
        int hitsCounter = 0;
        int[] positions = functions.hash(value, BVect_Size);
        
        for(int i = 0; i < positions.length; i++) {
            if(positions[i] > BVect_Size) {
                if((BitVector.get(positions[i]%BVect_Size)) && 
                        (BitVector.get(positions[i]))) {
                    hitsCounter++;
                }
            }
        }
        if(positions.length != hitsCounter) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public double getExpectFP_Rate() {
        return FP_Rate;
    }
    
    public int getHashNum() {
        return hash_Num;
    }
    
}
    
  


