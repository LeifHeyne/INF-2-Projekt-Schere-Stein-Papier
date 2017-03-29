import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


public class Controller implements Initializable{
	
	 
	@FXML
	private Button startOne;
			
	@FXML
	private Button startTwo;
	
	@FXML
	private TextField onePlayerText;	
	
	@FXML
	private TextField twoPlayerText1;
	
	@FXML
	private TextField twoPlayerText2;
	
	@FXML
	private BorderPane menu;
	
	private Model model;
	
	private GameSolo gameSolo;
	
	
	private Main main;
	
	
	Node save;
	
	Boolean inGame = false;

	protected GameDuo gameDuo;
	
	private boolean newGameSet = false;

	
	
	

	 // Construcktor
	
	public Controller(Main m) {
		model = new Model();
		this.main = m;
	}
	
	
	//Menü mit funktionen versehen
	
	@FXML
	private void onNewGame(){
		if(inGame){
			newGameSet=true;
			menu.setCenter(save);
			
		}
	}
	@FXML
	private void onClose(){
		System.exit(0);
		
	}
	@FXML
	private void onInstructions(){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
        alert.setTitle("Rules");
        alert.setContentText("Das Spiel wird nach den Standart Schere, Stein, Papier-\nRegeln gespielt\n\n"
        		+ "Wer gewinnt kriegt ein Punkt.\n"
        		+ "Am Ende gewinnt der, der am meisten Punkte hat.\n\n"
        		+ "Für den Einspieler-Modus gilt wer nichts eingibt verliert automatisch.\n"
        		+ "Wenn man verliert wird man auch 0 Punkte zurückgesetzt.");
        alert.show();
		
	}
	@FXML
	private void onAbout(){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
        alert.setTitle("._.\"");
        alert.setContentText("Some messy Code Version 1.0");
        alert.show();
		
	}

	
	// Nötig das die Verbindungen geschaffen werden
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//Button OnePlayer-Mode
		
		startOne.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(!onePlayerText.getText().isEmpty()){
				
					//Setup
							inGame = true;
							
							save = menu.getCenter();
							gameSolo = new GameSolo(new Player(onePlayerText.getText()));
							menu.setCenter(gameSolo.getNode());
					//SetupEnde		
					
					main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							switch (event.getText()) {
							case "a":
								gameSolo.setDes("Schere");
								
								break;
							case "s":
								gameSolo.setDes("Stein");
								break;
							case "d":
								gameSolo.setDes("Papier");
								break;

							
							}
						}
					});	
					gameOne();
					
				}
				else{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
			        alert.setTitle("Hinweis");
			        alert.setContentText("Bitte gib einen Namen ein");
			        alert.show();
				}
			}

			

			
		});
		
		
		
		
		
		
		
		
		// Button TwoPlayerMode
		
		
		
		
		startTwo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(!twoPlayerText1.getText().isEmpty() && !twoPlayerText2.getText().isEmpty()){
					
					
					//Setup
							inGame = true;
							
							save = menu.getCenter();
							gameDuo = new GameDuo(new Player(twoPlayerText1.getText()), new Player(twoPlayerText2.getText()));
							menu.setCenter(gameDuo.getNode());
					//Setup Ende
					
						
					main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							switch (event.getText()) {
							case "a":
								gameDuo.setPlayerOneInput("Schere");
//								System.out.println("playerone");
								break;
							case "s":
								gameDuo.setPlayerOneInput("Stein");
//								System.out.println("playerone");
								break;
							case "d":
								gameDuo.setPlayerOneInput("Papier");
//								System.out.println("playerone");
								break;

							case "h":
								gameDuo.setPlayerTwoInput("Schere");
//								System.out.println("playerone");
								
								break;
							case "j":
								gameDuo.setPlayerTwoInput("Stein");
//								System.out.println("playerTow");
								break;
							case "k":
								gameDuo.setPlayerTwoInput("Papier");
//								System.out.println("playerone");
								break;

							}
						}
					});	
					gameTwo();
					
					
					
				}
				else{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
			        alert.setTitle("Hinweis");
			        alert.setContentText("Bitte gebt eure Namen ein");
			        alert.show();
				}
				
			}
		});
		
	}




	//Spielablauf vom OnePlayer-Mode
	
	protected void gameOne() {
		newGameSet = false;
		gameSolo.setTimerInt(4);
		gameSolo.setInput("ran");
		
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			private Group handsGameOne = null;

			
			@Override
			public void handle(ActionEvent e) {
				gameSolo.setTimer();
				
				if(gameSolo.getTimerInt()<=0){
					
					gameSolo.setInputfinal(gameSolo.getInput());
					gameSolo.calculateWinner(gameSolo.inputfinal);
					
					showHandsGameOne();
//					gameSolo.showHands(gameSolo.getInputfinal());
					time.stop();
				}
				
			}

			private void showHandsGameOne() {
				if(newGameSet){
					return;
				}
				handsGameOne = model.buildScene(gameSolo.inputfinal, gameSolo.comInput);
				handsGameOne.setTranslateX(85);
				handsGameOne.setTranslateY(350);
				gameSolo.addToMain(handsGameOne);
				
				Timeline timeHands = new Timeline();
				timeHands.setCycleCount(Timeline.INDEFINITE);
				KeyFrame frame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent e) {
						
//						playerHand.getChildren().get(0).getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(i, Rotate.X_AXIS));;
						handsGameOne.setTranslateY(handsGameOne.getTranslateY()-1);
						if(handsGameOne.getTranslateY()==50){
							
							
							showWinnerTextGameOne();
							timeHands.stop();
						}
						}
					
					
					private void showWinnerTextGameOne() {
						if(newGameSet){
							return;
						}
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Spielende");
						if(gameSolo.isTie()){
							alert.setHeaderText("Unentschiede!\n"
									+ "Viel Glück nächstes mal " + gameSolo.getPlayer().getName());
						}
						else if(gameSolo.isPlaxerWin()){
							gameSolo.points();
							alert.setHeaderText("Sie haben gewonnen!\n"
									+ "Herzlichen Glückwunsch " + gameSolo.getPlayer().getName()+"\n"
											+ "Damit haben Sie  : "+ gameSolo.getPlayer().getPoints() +" Point(s)");
							
						}
						else if(!gameSolo.isPlaxerWin()){
							gameSolo.getPlayer().setPoints(0);
							alert.setHeaderText("Sie haben leider Verloren!\n"
									+ "Viel Glück nächstes mal " + gameSolo.getPlayer().getName()
							+ "\nDamit haben Sie "+ gameSolo.getPlayer().getPoints() +" Point(s)");
						}
//						alert.setHeaderText("test");
						alert.getButtonTypes().clear();
						alert.getButtonTypes().addAll(ButtonType.YES);
						alert.setContentText("Nochmal?");
						Button b = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
						b.setText("Nochmal");
						b.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								handsGameOne.getChildren().clear();
								gameOne();
							}
						});
						alert.show();
					}
				});
				timeHands.getKeyFrames().add(frame);
				timeHands.playFromStart();
				
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
		
	}



	
	
	
	
	
	
	
	//Spielablauf vom TwoPlayer-Mode

	protected void gameTwo() {
		newGameSet = false;
		gameDuo.setTimerInt(4);
		
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			
			private Group handsPlayerTwo = null;

			@Override
			public void handle(ActionEvent e) {
				gameDuo.setTimerInt(gameDuo.getTimerInt()-1);
				gameDuo.setTimerLabel("TIMER : " + gameDuo.getTimerInt());
				
				if(gameDuo.getTimerInt()<=0){
					
					gameDuo.finelInputs();
					gameDuo.calculatWinner();
					
					showHandsGameTwo();
					time.stop();
				}
				
			}

			private void showHandsGameTwo() {
				
				handsPlayerTwo = model.buildScene(gameDuo.getFinalInputPlayerOne(), gameDuo.getFinalInputPlayerTwo());
				handsPlayerTwo.setTranslateX(85);
				handsPlayerTwo.setTranslateY(350);
				gameDuo.addToMain(handsPlayerTwo);
				Timeline timeHands = new Timeline();
				timeHands.setCycleCount(Timeline.INDEFINITE);
				KeyFrame frame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent e) {
						
//						playerHand.getChildren().get(0).getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(i, Rotate.X_AXIS));;
						handsPlayerTwo.setTranslateY(handsPlayerTwo.getTranslateY()-1);
						if(handsPlayerTwo.getTranslateY()==50){
							
							
							System.out.println(gameDuo.getPlayerOne().getPoints());
							showWinnerTextGameTwo();
							timeHands.stop();
						}
						}

					private void showWinnerTextGameTwo() {
						if(newGameSet){
							return;
						}
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Spielende");
						if(gameDuo.getTie()){
							alert.setHeaderText("Unentschiede!\n"
									+ "Viel Glück nächstes mal " + (gameDuo.getPlayerOne().getName() + " und " + gameDuo.getPlayerTwo().getName()));
						}
						else if(gameDuo.isPlayerOneWin()){
							gameDuo.poins();
							
							alert.setHeaderText("Sie haben gewonnen!\n"
									+ "Herzlichen Glückwunsch " + gameDuo.getPlayerOne().getName()+"\n"
											+ "Damit hat "+ gameDuo.getPlayerOne().getName() +" : "+ gameDuo.getPlayerOne().getPoints() +" Point(s)");
							
						}
						else if(!gameDuo.isPlayerOneWin()){
							gameDuo.poins();
							alert.setHeaderText("Sie haben gewonnen!\n"
									+ "Herzlichen Glückwunsch " + gameDuo.getPlayerTwo().getName()+"\n"
									+ "Damit hat "+ gameDuo.getPlayerTwo().getName() +" : "+ gameDuo.getPlayerTwo().getPoints() +" Point(s)");
						}
//						alert.setHeaderText("test");
						alert.getButtonTypes().clear();
						alert.getButtonTypes().addAll(ButtonType.YES);
						alert.setContentText("Nochmal?");
						Button b = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
						b.setText("Nochmal");
						b.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								handsPlayerTwo.getChildren().clear();
								gameTwo();
							}
						});
						alert.show();
					}
				});
				timeHands.getKeyFrames().add(frame);
				timeHands.playFromStart();
				
			}
		}); 
		time.getKeyFrames().add(frame);
		time.playFromStart();
		
	}
	
	
	
}
