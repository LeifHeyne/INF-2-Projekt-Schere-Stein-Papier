

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.javafx.sg.prism.NGPhongMaterial;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;



public class GameSolo extends Node implements Initializable {
	Player player;
	
	Pane p ;
	Label playerName;
	Label timer = new Label();
	int timerInt;
	VBox main;
	BorderPane top;
	
	
	
	
	
	
	 private static final String MESH_FILENAME =
			   "C:/Users/Leif/workspace/Info2/src/sdfjkbsdkfsdkjbf.stl";
	 			
	 private static final double MODEL_SCALE_FACTOR = 3;
	 private static final double MODEL_X_OFFSET = 0; // standard
	 private static final double MODEL_Y_OFFSET = 0; // standard
	 private static final int VIEWPORT_SIZE = 800;
	
	 private static final Color lightColor = Color.rgb(244, 255, 250);
	 private static final Color jewelColor = Color.rgb(0, 190, 222);
	
	 private Group root;
	 private PointLight pointLight;
	
	 static MeshView[] loadMeshViews() {
				    
		File file = new File(MESH_FILENAME);
		System.out.println(file.exists());
		StlMeshImporter importer = new StlMeshImporter();
		importer.read(file);
		Mesh mesh = importer.getImport();
	
		return new MeshView[] { new MeshView(mesh) };
	}
	
	private Group buildScene() {
					  
	   MeshView[] meshViews = loadMeshViews();
	   for (int i = 0; i < meshViews.length; i++) {
	      meshViews[i].setTranslateX(VIEWPORT_SIZE / 2 + MODEL_X_OFFSET);
	      meshViews[i].setTranslateY(VIEWPORT_SIZE / 2 + MODEL_Y_OFFSET);
	      meshViews[i].setTranslateZ(VIEWPORT_SIZE / 2);
	      meshViews[i].setScaleX(MODEL_SCALE_FACTOR);
	      meshViews[i].setScaleY(MODEL_SCALE_FACTOR);
	      meshViews[i].setScaleZ(MODEL_SCALE_FACTOR);
	      PhongMaterial sample = new PhongMaterial(jewelColor);
	      sample.setSpecularColor(lightColor);
	      sample.setSpecularPower(16);
	      meshViews[i].setMaterial(sample);
	      meshViews[i].getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(80, Rotate.X_AXIS));
		}
	
		pointLight = new PointLight(lightColor);
		pointLight.setTranslateX(VIEWPORT_SIZE*3/4);
		pointLight.setTranslateY(VIEWPORT_SIZE/2);
		pointLight.setTranslateZ(VIEWPORT_SIZE/2);
		PointLight pointLight2 = new PointLight(lightColor);
		pointLight2.setTranslateX(VIEWPORT_SIZE*1/4);
		pointLight2.setTranslateY(VIEWPORT_SIZE*3/4);
		pointLight2.setTranslateZ(VIEWPORT_SIZE*3/4);
		PointLight pointLight3 = new PointLight(lightColor);
		pointLight3.setTranslateX(VIEWPORT_SIZE*5/8);
		pointLight3.setTranslateY(VIEWPORT_SIZE/2);
		pointLight3.setTranslateZ(0);
		Color ambientColor = Color.rgb(80, 80, 80, 0);
		AmbientLight ambient = new AmbientLight(ambientColor);
	
		root = new Group(meshViews);
		root.getChildren().add(pointLight);
		root.getChildren().add(pointLight2);
		root.getChildren().add(pointLight3);
		root.getChildren().add(ambient);
	    return root;
	 }
	
	 private PerspectiveCamera addCamera(Scene scene) {
	    PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
	    System.out.println("Near Clip: " + perspectiveCamera.getNearClip());
		System.out.println("Far Clip:  " + perspectiveCamera.getFarClip());
		System.out.println("FOV:       " + perspectiveCamera.getFieldOfView());
	    scene.setCamera(perspectiveCamera);
	    return perspectiveCamera;
	 }
		
	
		
	
	public GameSolo(String name) {
		Group group = buildScene();
		group.setScaleX(2);
	    group.setScaleY(2);
	    group.setScaleZ(2);
	    group.setTranslateX(50);
	    group.setTranslateY(450);
		
		
		
		
		main = new VBox();
		
		top = new BorderPane();
		
		
		playerName = new Label(name);
		playerName.setFont( Font.font("System",FontPosture.ITALIC, 30));
		timerInt = 3;
		;
		timer.setFont(Font.font("System", 30));
		
		top.setLeft(playerName);
		top.setRight(timer);
		
		
		
		
		
		
		main.getChildren().add(top);
		main.getChildren().add(group);
	
			
	}
	
	public VBox getNode() {
		return main;

	}

	public void startGame() {
		
		
		
	}
	public Label getTimer() {
		return timer;
	}
	
	public void coolDown() {
		timerInt--;
		showTimer(timerInt);
		
	}
	
	public void showTimer(int time) {
		timer.setText("TIMER : " + time);

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setTimer() {
		timer.setText("TIMER : " + timerInt);
		timerInt--;
	}

	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
