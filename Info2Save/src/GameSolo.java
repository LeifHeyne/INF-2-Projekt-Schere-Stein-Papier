


import java.util.Random;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Duration;



public class GameSolo extends Node{
	Player player;
	
	Pane p ;
	Label playerName;
	Label timerLabel;
	int timerInt;
	VBox main;
	BorderPane top;
	Group hands;


	String input;
	Random r = new Random();
	
	
	
	String comInput;
	
	
	private Model model;
	 			
	

	protected String inputfinal;

	protected boolean plaxerWin;
	

	private boolean tie;
	
	 
	
	
		
	
		
	
	public GameSolo(Player player) {
		this.model = new Model();
		
	    this.player = player;
	    
	    input = "ran";
	    
	    plaxerWin = true;
		
		main = new VBox();
		
		
		top = new BorderPane();
		
		timerInt = 3;
		timerLabel = new Label("TIMER : " + timerInt);
		timerLabel.setTextFill(Color.web("#828282"));
		
		
		
		playerName = new Label(player.getName());
		playerName.setTextFill(Color.web("#828282"));
		playerName.setFont( Font.font("System",FontPosture.ITALIC, 30));
		
		timerLabel.setFont(Font.font("System", 30));
		
		
		top.setLeft(playerName);
		top.setRight(timerLabel);
		
		
		
		
		
		
		main.getChildren().add(top);
		
	
			
	}
	
	public VBox getNode() {
		return main;

	}

	public void startGame() {
		
		
		
	}
	public Label getTimer() {
		return timerLabel;
	}
	

	




	public void setTimer() {
		timerInt--;
		timerLabel.setText("TIMER : " + Integer.toString(timerInt));
	}

//	public void doTime() {
//			
//		Timeline time = new Timeline();
//		time.setCycleCount(3);
//		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent e) {
//				setTimer();
//				
//				if(timerInt<=0){
//					
//					inputfinal = input;
//					showHands(inputfinal);
//					time.stop();
//				}
//				
//			}
//		});
//		time.getKeyFrames().add(frame);
//		time.playFromStart();
//		
//	}

	public void showHands(String player) {
		
			hands = loadHands(inputfinal,comInput);
			hands.setTranslateX(85);
			hands.setTranslateY(350);
			main.getChildren().add(hands);
			Timeline timeHands = new Timeline();
			timeHands.setCycleCount(Timeline.INDEFINITE);
			KeyFrame frame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
//					hands.getChildren().get(0).getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(i, Rotate.X_AXIS));;
					hands.setTranslateY(hands.getTranslateY()-1);
					if(hands.getTranslateY()==50){
						
						showWinnerText();
						timeHands.stop();
					}
					}
			});
			timeHands.getKeyFrames().add(frame);
			timeHands.playFromStart();
		
	}

	private Group loadHands(String player, String com) {
		return model.buildScene(player, com);
		
	}

	public void calculateWinner(String player) {
		// 0 = schere
		// 1 = stein
		// 2 = papier
		int com = (int)(Math.random() * 3);
		if(com==0){
			comInput = "Schere";
		}
		else if (com==1) {
			comInput = "Stein";
		}
		else{
			comInput = "Papier";
		}
		
		if(player.equals("ran")){
			autoLose(com);
			plaxerWin = false;
			System.out.println("player = " + inputfinal + "\n COM = " + com);
			return;
		}

		
		switch (com) {
		case 0:
				System.out.println("case 0");
				System.out.println("player = " + inputfinal + "\n COM = " + com);

				if(inputfinal.equalsIgnoreCase("schere")){
					tie = true;
				}
				else if(inputfinal.equalsIgnoreCase("stein")){
					tie = false;
					plaxerWin = true;
					
				}
				else{
					tie=false;
					plaxerWin= false;
				}
				break;
			
		case 1: 
			System.out.println("case 1");
			System.out.println("player = " + inputfinal + "\n COM = " + com);

			if(inputfinal.equalsIgnoreCase("schere")){
				tie=false;
				plaxerWin= false;
			}
			else if(inputfinal.equalsIgnoreCase("stein")){
				tie = true;
			}
			else{
				tie = false;
				plaxerWin = true;
			}
			break;
		case 2: 
			System.out.println("player = " + inputfinal + "\n COM = " + com);

			System.out.println("case 2");
			if(inputfinal.equalsIgnoreCase("schere")){
				tie = false;
				plaxerWin = true;
			}
			else if(inputfinal.equalsIgnoreCase("stein")){
				tie=false;
				plaxerWin= false;
			}
			else{
				tie = true;
			}
			break;
		
		}
		
	}

	private void autoLose(int com) {
		if(com==0){
			inputfinal = "Papier";
		}
		else if (com == 1) {
			inputfinal = "Schere";	
		}else {
			inputfinal = "Stein";
		}
	}

	protected void showWinnerText() {
		Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setHeaderText("SPIELENDE");
		alert.setTitle("ENDE");
		if(tie){
			alert.setContentText("Unentschiede!\n"
					+ "Viel Glück nächstes mal " + player.getName());
		}
		else if(plaxerWin){
//			player.score();
			alert.setContentText("Sie haben gewonnen!\n"
					+ "Herzlichen Glückwunsch " + player.getName());
			
		}
		else if(!plaxerWin){
			alert.setContentText("Sie haben leider verloren.\n"
					+ "Viel Glück nächstes mal " + player.getName());
		}
		alert.show();
		
	}
	
	void points(){
		if(plaxerWin){
			player.score();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Pane getP() {
		return p;
	}

	public void setP(Pane p) {
		this.p = p;
	}

	public Label getPlayerName() {
		return playerName;
	}

	public void setPlayerName(Label playerName) {
		this.playerName = playerName;
	}

	public Label getTimerLabel() {
		return timerLabel;
	}

	public void setTimerLabel(Label timerLabel) {
		this.timerLabel = timerLabel;
	}

	public VBox getMain() {
		return main;
	}

	public void setMain(VBox main) {
		this.main = main;
	}

	public BorderPane getTop() {
		return top;
	}

	public void setTop(BorderPane top) {
		this.top = top;
	}

	public Group getHands() {
		return hands;
	}

	public void setHands(Group hands) {
		this.hands = hands;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public String getComInput() {
		return comInput;
	}

	public void setComInput(String comInput) {
		this.comInput = comInput;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public boolean isPlaxerWin() {
		return plaxerWin;
	}

	public void setPlaxerWin(boolean plaxerWin) {
		this.plaxerWin = plaxerWin;
	}

	public boolean isTie() {
		return tie;
	}

	public void setTie(boolean tie) {
		this.tie = tie;
	}

	public void setTimerInt(int timerInt) {
		this.timerInt = timerInt;
	}

	public void setDes(String input) {
		this.input = input;
	}
	
	public String getDes() {
		return input;
	}



	
	public int getTimerInt() {
		return timerInt;
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
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public String getInputfinal() {
		return inputfinal;
	}
	
	public void setInputfinal(String inputfinal) {
		this.inputfinal = inputfinal;
	}

	public void addToMain(Group handsGameOne) {
		main.getChildren().add(handsGameOne);
		
	}
}
