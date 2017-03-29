import org.scenicview.ScenicView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	Stage window;
	Scene scene;
	Scene save;
	
	
	
	public static void main(String[] args) {
		
		launch(args);
		
		
	}

	@Override
	public void start(Stage s) throws Exception {
		this.window = s;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
		loader.setControllerFactory(t -> new Controller(this));
		scene = new Scene(loader.load() );
		window.setTitle("Scher, Stein, Papier");
		
		window.setScene(scene);
		window.show();
		
//		ScenicView.show(scene);
	}
	
	 public Scene getScene() {
		return scene;
	}


	

	

}
