import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.scenicview.ScenicView;


public class Main extends Application {
	
	public static void main(String[] args) {
		
		launch(args);
		
		
	}

	@Override
	public void start(Stage s) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
		loader.setControllerFactory(t -> new Controller(new Model(),this));
		Scene scene = new Scene(loader.load());
		
		s.setScene(scene);
		s.show();
		//ScenicView.show(scene);
	}

	

	

}
