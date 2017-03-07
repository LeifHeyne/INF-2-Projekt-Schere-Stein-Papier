import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.swing.internal.plaf.metal.resources.metal;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;


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
	
	private HBox n;
	
	private Main m;
	
	private Button b;
	
	
	
	

	 
	
	public Controller(Model model, Main m) {
		this.model = model;
		this.m = m;
		this.b = new Button();
	}
	
	
	
	
	@FXML
	private void onNewGame(){
		
	}
	@FXML
	private void onClose(){
		System.exit(0);
		
	}
	@FXML
	private void onInstructions(){
		
	}
	@FXML
	private void onAbout(){
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		startOne.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(!onePlayerText.getText().isEmpty()){
					
					
					Node ets = menu.getCenter();
					gameSolo = new GameSolo(onePlayerText.getText());
					menu.setCenter(gameSolo.getNode());
					
					
							
							
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
		
		startTwo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(!twoPlayerText1.getText().isEmpty() && !twoPlayerText2.getText().isEmpty()){
					
					System.out.println(onePlayerText.getText());
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
	
	
	
}
