import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class GameDuo extends Node{
	
	
	
	private VBox main;
	private BorderPane top;
	private int timerInt;
	private Label timerLabel;
	private Player playerOne;
	private Player playerTwo;
	private Label playerOneName;
	private Label playerTwoName;
	private String playerOneInput;
	private String playerTwoInput;
	private String finalInputPlayerOne;
	private String finalInputPlayerTwo;
	private boolean tie;
	private boolean playerOneWin;
	
	
	

	public GameDuo(Player player, Player player2) {
		this.playerOne = player;
		this.playerTwo = player2;
		
		
		buildNode();
		
	}

	
	
	
	
	
	
	
	private void buildNode() {
		main = new VBox();
		
		
		top = new BorderPane();
		
		timerInt = 3;
		timerLabel = new Label("TIMER : " + timerInt);
		timerLabel.setTextFill(Color.web("#828282"));
		
		
		
		playerOneName = new Label(playerOne.getName());
		playerOneName.setTextFill(Color.web("#828282"));
		playerOneName.setFont( Font.font("System",FontPosture.ITALIC, 30));
		
		timerLabel.setFont(Font.font("System", 30));
		
		
		playerTwoName = new Label(playerTwo.getName());
		playerTwoName.setTextFill(Color.web("#828282"));
		playerTwoName.setFont( Font.font("System",FontPosture.ITALIC, 30));
		
		
		top.setLeft(playerOneName);
		top.setCenter(timerLabel);
		top.setRight(playerTwoName);
//		timerLabel.setAlignment(Pos.CENTER);
		
		
		
		
		
		
		main.getChildren().add(top);		
	}



	public void addToMain(Node g) {
		main.getChildren().add(g);
	}




	public Player getPlayerOne() {
		return playerOne;
	}








	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}








	public Player getPlayerTwo() {
		return playerTwo;
	}








	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}








	public VBox getNode() {
		// TODO Auto-generated method stub
		return main;
	}
	
	
	
	
	public void setPlayerOneInput(String input) {
		this.playerOneInput = input;
		
	}
	
	
	
	
	public void setPlayerTwoInput(String input) {
		this.playerTwoInput = input;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getTimerInt() {
		return timerInt;
	}








	public void setTimerInt(int timerInt) {
		this.timerInt = timerInt;
	}








	public Label getTimerLabel() {
		return timerLabel;
	}








	public void setTimerLabel(String test) {
		this.timerLabel.setText(test);;
	}








	@Override
	protected boolean impl_computeContains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object impl_processMXNode(MXNodeAlgorithm arg0, MXNodeAlgorithmContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}



	void poins(){
		if(playerOneWin){
			playerOne.score();
		}
		else if (!playerOneWin) {
			playerTwo.score();
		}
	}




	public void finelInputs() {
		if(playerOneInput==null){
			int r = (int)(Math.random() * 3);
			if(r==0){
				finalInputPlayerOne="Schere";
			}
			else if(r==1){
				finalInputPlayerOne="Stein";
			}
			else if(r==2){
				finalInputPlayerOne="Papier";
			}
		}
		else{
			finalInputPlayerOne = playerOneInput;
		}
		System.out.println("player one" + finalInputPlayerOne);
		if(playerTwoInput==null){
			int r = (int)(Math.random() * 3);
			if(r==0){
				finalInputPlayerTwo="Schere";
			}
			else if(r==1){
				finalInputPlayerTwo="Stein";
			}
			else if(r==2){
				finalInputPlayerTwo="Papier";
			}
		}
		else{
			finalInputPlayerTwo = playerTwoInput;
		}
		System.out.println("player tow " + finalInputPlayerTwo );
		
	}








	public String getFinalInputPlayerOne() {
		return finalInputPlayerOne;
	}








	public void setFinalInputPlayerOne(String finalInputPlayerOne) {
		this.finalInputPlayerOne = finalInputPlayerOne;
	}








	public String getFinalInputPlayerTwo() {
		return finalInputPlayerTwo;
	}








	public void setFinalInputPlayerTwo(String finalInputPlayerTwo) {
		this.finalInputPlayerTwo = finalInputPlayerTwo;
	}








	public boolean isTie() {
		return tie;
	}








	public void setTie(boolean tie) {
		this.tie = tie;
	}








	public boolean isPlayerOneWin() {
		return playerOneWin;
	}








	public void setPlayerOneWin(boolean playerOneWin) {
		this.playerOneWin = playerOneWin;
	}








	public void calculatWinner() {
		switch (finalInputPlayerOne) {
		case "Schere":
			if(finalInputPlayerTwo=="Schere"){
				tie = true;
			}
			else if (finalInputPlayerTwo=="Stein"){
				tie=false;
				playerOneWin = false;
			}
			else if (finalInputPlayerTwo =="Papier") {
				tie=false;
				playerOneWin=true;
			}
			break;
		case "Stein":
			if(finalInputPlayerTwo=="Schere"){
				tie=false;
				playerOneWin=true;
			}
			else if (finalInputPlayerTwo=="Stein"){
				tie = true;
			}
			else if (finalInputPlayerTwo =="Papier") {
				tie=false;
				playerOneWin = false;
			}
			break;
		case "Papier":
			if(finalInputPlayerTwo=="Schere"){
				tie=false;
				playerOneWin = false;
			}
			else if (finalInputPlayerTwo=="Stein"){
				tie=false;
				playerOneWin=true;
			}
			else if (finalInputPlayerTwo =="Papier") {
				tie = true;
			}
			break;

		default:
			break;
		}
		
	}








	public boolean getTie() {
		// TODO Auto-generated method stub
		return tie;
	}








}
