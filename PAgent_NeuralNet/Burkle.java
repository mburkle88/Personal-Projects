import org.neuroph.core.*;
import java.util.Vector;

public class Burkle extends Player {

	// Mollie Burkle
	// Poker agent version 3
	// Intelligent Systems
	// Dr. Schafer

    //import trained neural network
    NeuralNetwork pokerNNet= NeuralNetwork.load("NewNeuralNetwork1.nnet");
    
    Burkle_Helper M_tools;
    double[] normPocket;
    double sameSuit;
    double normPosition;
    int mySeat;
    String name;
    int[] boardCards;		// current board cards (either size= 0/3/5 depending on 	
    int[] myCards;		
    TableData playerCount;


    public Burkle (){
            name= "Mollie";
            myCards= null;

    }


    public String getAction(TableData data) {
    	// prepare NNet input data
        mySeat= data.getMySeatNumber();
        sameSuit= M_tools.sameSuit(myCards);
        normPosition= M_tools.normPosition(mySeat);
        myCards= data.getPocket();
        normPocket= M_tools.convertPocket(myCards);

        // feed NNet input
        pokerNNet.setInput(normPocket[0], normPocket[1], sameSuit, normPosition);
        pokerNNet.calculate();

        // get NNet output
        double[] networkOutput= pokerNNet.getOutput();

        // interpret output
        if ((networkOutput[0]> networkOutput[1])&& (networkOutput[0]> networkOutput[2])) {
            return "fold";
        }
        else if ((networkOutput[1]> networkOutput[0])&& (networkOutput[1]> networkOutput[2])) {
            if (data.getValidActions().contains("call")) {
                return "call";
            }
            else {
                return "check";
            }
        }
        else {
            if (data.getValidActions().contains("raise")) {
                return "raise";
            }
            else if (data.getValidActions().contains("bet")) {
                return "bet";
            }
            else {
                return "call";
            }
        }
    }
    
// returns a string representation of the the agent's name.
    @Override
	public String getScreenName() {
		return name;
	}

}
