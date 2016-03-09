/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mollie
 */
public class HashFunctions {
    
    private int function;
    
    public HashFunctions(int prev_Function) {
        function = prev_Function + 1;
    }
    
    public int hash(int value, int BV_Size) {
        return (int)Math.abs(Math.round(Math.log(Math.pow(value, function)
                /Math.pow(function*value, function)) * value) % BV_Size);
    }
    
    public int getFunction() {
        return function;
    }
}
