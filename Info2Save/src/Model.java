import java.io.File;

import com.interactivemesh.jfx.importer.stl.StlMeshImporter;

import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

public class Model {
	private static final double MODEL_SCALE_FACTOR = 20;
	private static final double modelZ = 30; // standard
	
	private static final Color lightColor = Color.rgb(244, 255, 250);
	private static final Color jewelColor = Color.WHITE;
	
	
	
	
	private static final String hand_Schere = "model/hand_Schere.stl";
	private static final String hand_Stein = "model/hand_Stein.stl";
	private static final String hand_Papier = "model/hand_Papier.stl";
	
	 private Group root;
	 private PointLight pointLightPlayerOne;
	 private PointLight pointLightPlayerTwo;
	 
	 public Model() {	
		
	}
	 
	 static MeshView[] loadMeshViews(String input) {
			 String s="sfgb" ;
			 System.out.println(input);
		    if(input.equalsIgnoreCase("Schere")){
		    	s=Model.hand_Schere;
		    }
		    else if (input.equalsIgnoreCase("Stein")) {
				s=hand_Stein;
			}
		    else if (input.equalsIgnoreCase("Papier")) {
				s=Model.hand_Papier;
			}
			File file = new File(s);
			String str = file.getAbsolutePath();
			str = str.replace('\\', '/');
			System.out.println(str);
			file = new File(str);
			if(file.exists()){
				System.out.println("DOCH");
			}
			
			
			StlMeshImporter importer = new StlMeshImporter();
			importer.read(file);
			Mesh mesh = importer.getImport();
		
			importer.close();
			return new MeshView[] { new MeshView(mesh) };
		}
	 
	 public Group buildScene(String modelPlayerOne, String modelPlayerTwo) {
		  jewelColor.darker();
		   MeshView[] meshViewsPlayerOne = loadMeshViews(modelPlayerOne);
		   for (int i = 0; i < meshViewsPlayerOne.length; i++) {
		      meshViewsPlayerOne[i].setTranslateX(0);
		      meshViewsPlayerOne[i].setTranslateY(0);
		      meshViewsPlayerOne[i].setTranslateZ(0);
		      meshViewsPlayerOne[i].setScaleX(MODEL_SCALE_FACTOR);
		      meshViewsPlayerOne[i].setScaleY(MODEL_SCALE_FACTOR);
		      meshViewsPlayerOne[i].setScaleZ(MODEL_SCALE_FACTOR);
		      PhongMaterial sample = new PhongMaterial(jewelColor);
		      sample.setSpecularColor(lightColor);
		      sample.setSpecularPower(16);
		      meshViewsPlayerOne[i].setMaterial(sample);
		      meshViewsPlayerOne[i].getTransforms().setAll(new Rotate(218, Rotate.Z_AXIS), new Rotate(80, Rotate.X_AXIS));
		      
			}
		   
		
		   pointLightPlayerOne = new PointLight(lightColor);
		   pointLightPlayerOne.setTranslateX(-50 );
		   pointLightPlayerOne.setTranslateY(0);
		   pointLightPlayerOne.setTranslateZ(modelZ*5);
			PointLight pointLightPlayerOne2 = new PointLight(lightColor);
			pointLightPlayerOne2.setTranslateX(50);
			pointLightPlayerOne2.setTranslateY(0);
			pointLightPlayerOne2.setTranslateZ(modelZ);
			PointLight pointLightPlayerOne3 = new PointLight(lightColor);
			pointLightPlayerOne3.setTranslateX(0);
			pointLightPlayerOne3.setTranslateY(0);
			pointLightPlayerOne3.setTranslateZ(modelZ);
			Color ambientColor = Color.rgb(80,80,80);
			AmbientLight ambient = new AmbientLight(ambientColor);
			
			MeshView[] meshViewsPlayerTwo = loadMeshViews(modelPlayerTwo);
			   for (int i = 0; i < meshViewsPlayerTwo.length; i++) {
				   meshViewsPlayerTwo[i].setTranslateX(350);
				   meshViewsPlayerTwo[i].setTranslateY(-15);
				   meshViewsPlayerTwo[i].setTranslateZ(0);
				   meshViewsPlayerTwo[i].setScaleX(MODEL_SCALE_FACTOR);
				   meshViewsPlayerTwo[i].setScaleY(-MODEL_SCALE_FACTOR);
				   meshViewsPlayerTwo[i].setScaleZ(-MODEL_SCALE_FACTOR);
			      PhongMaterial sample = new PhongMaterial(jewelColor);
			      sample.setSpecularColor(lightColor);
			      sample.setSpecularPower(50);
			      meshViewsPlayerTwo[i].setMaterial(sample);
			      meshViewsPlayerTwo[i].getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(80, Rotate.X_AXIS));
			      
			     
				}
			   
			
			   pointLightPlayerTwo = new PointLight(lightColor);
			   pointLightPlayerTwo.setTranslateX(250);
			   pointLightPlayerTwo.setTranslateY(0);
			   pointLightPlayerTwo.setTranslateZ(modelZ*2);
				PointLight pointLightPlayerTwo2 = new PointLight(lightColor);
				pointLightPlayerTwo2.setTranslateX(350);
				pointLightPlayerTwo2.setTranslateY(20);
				pointLightPlayerTwo2.setTranslateZ(0);
				PointLight pointLightPlayerTwo3 = new PointLight(lightColor);
				pointLightPlayerTwo3.setTranslateX(310);
				pointLightPlayerTwo3.setTranslateY(0);
				pointLightPlayerTwo3.setTranslateZ(modelZ);
				
				Color ambientColorPlayerTwo = Color.RED;
				ambientColorPlayerTwo.darker();
				AmbientLight ambientPlayerTwo = new AmbientLight(ambientColorPlayerTwo);
				ambientPlayerTwo.setTranslateX(80);
				ambientPlayerTwo.setTranslateZ(100);
		
			root = new Group(meshViewsPlayerOne);
			
//			root.getChildren().add(pointLightPlayerOne);
//			root.getChildren().add(pointLightPlayerOne2);
			root.getChildren().add(pointLightPlayerOne3);
			
			root.getChildren().addAll(meshViewsPlayerTwo);
			
//			root.getChildren().add(pointLightPlayerTwo);
//			root.getChildren().add(pointLightPlayerTwo2);
//			root.getChildren().add(pointLightPlayerTwo3);
			root.getChildren().add(ambient);
			root.getChildren().add(ambientPlayerTwo);
			
			return root;
		 }

	public Group getRoot() {
		return root;
	}

	public void setRoot(Group root) {
		this.root = root;
	}
		
//		 private PerspectiveCamera addCamera(Scene scene) {
//		    PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
//		    System.out.println("Near Clip: " + perspectiveCamera.getNearClip());
//			System.out.println("Far Clip:  " + perspectiveCamera.getFarClip());
//			System.out.println("FOV:       " + perspectiveCamera.getFieldOfView());
//		    scene.setCamera(perspectiveCamera);
//		    return perspectiveCamera;
//		 }
	

}
