import org.neuroph.core.NeuralNetwork;

public class Burkle extends Player {

	// Mollie Burkle
	// Poker agent version 3
	// Intelligent Systems
	// Dr. Schafer

    //import trained neural network
    NeuralNetwork pokerNNet= NeuralNetwork.load("NewNeuralNetwork3.nnet");
    
    double[] normPocket;
    double sameSuit;
    double normPosition;
    String name;
    int card1;
    int card2;		
    TableData gameData;


    public Burkle (){
            name= "Mollie";
            int[]myCards= null;
    }

    public String getAction(TableData data) {
        normPocket= normalizeCards(data);
        sameSuit = checkSuit(data);
        normPosition= normPosition(data);

        //System.out.println(normPosition);
        pokerNNet.setInput(normPocket[0], normPocket[1], sameSuit, normPosition);
        pokerNNet.calculate();

        // get NNet output
        double[] networkOutput= pokerNNet.getOutput();

        
        // interpret output
        if (networkOutput[0] == 1.0) {
            return "fold";
        }
        if (networkOutput[1] == 1.0) {
            if (data.getValidActions().contains("call")) {
                return "call";
            }
            else {
                return "check";
            }
        }
        if (networkOutput[2] == 1.0) {
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
        return "call";
    }
    
    private double[] normalizeCards(TableData data) {
                int holder1;
        int holder2;
        double offset;
        double round1;
        double round2;
        double[] normPocket = new double[2];
        
        card1 = data.getPocket()[0];
        card2 = data.getPocket()[1];
        holder1 = card1%13;
        offset = (double)holder1/13.0;
        round1 = offset*1000;
        round1 = Math.round(round1);
        round1 = round1/1000;
        normPocket[0] = round1;
        holder2 = card2%13;
        offset = (double)holder2/13.0;
        round2 = offset*1000;
        round2 = Math.round(round2);
        round2 = round2/1000;
        normPocket[1] = round2;
        return normPocket;
    }
    
    private double checkSuit(TableData data) {
        
        if ((data.getPocket()[0]%13)== (data.getPocket()[1]%13)) {
            return 1.0;
        }
        else {
            return 0.0;
        }
    }
    
    private double normPosition (TableData data) {
        double position;
        double roundOff;
        
        position= (data.getMySeatNumber()/data.getPlayerCount());
        roundOff = position * 10;
        roundOff= Math.round(roundOff);
        roundOff= roundOff / 10;
        return roundOff;
    }
// returns a string representation of the the agent's name.
    @Override
	public String getScreenName() {
		return name;
	}

}
