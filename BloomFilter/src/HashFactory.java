/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mollie
 */
public class HashFactory {
    private HashFunctions[] hashFunctions;
    
    public HashFactory(int k) {
        hashFunctions = new HashFunctions[k];
        for(int i = 0; i < k; i++)
            hashFunctions[i] = new HashFunctions(i);
    }
    
    public int[] hash(int value, int BV_Size) {
        int[]positions = new int[hashFunctions.length];
        for(int i = 0; i < hashFunctions.length; i++)
            positions[i] = hashFunctions[i].hash(value, BV_Size);
        return positions;
    }
    
    public HashFunctions[] getAllFunctions() {
        return hashFunctions;
    }
    
    public HashFunctions getFunction(int function) {
        return hashFunctions[function];
    }
    
    public int size() {
        return hashFunctions.length;
    }
}
