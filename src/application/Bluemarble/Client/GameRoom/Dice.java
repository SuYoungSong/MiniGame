package application.Bluemarble.Client.GameRoom;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;


public class Dice{
    SmartGroup group = new SmartGroup();
    
	public void diceMake(AnchorPane diceRolling,int x, int y, int z) {
		Image diffuseMap = new Image(Main.class.getResourceAsStream("texture/dice.png"));
		Material diceSurface = new PhongMaterial(Color.WHITE, diffuseMap, null, null, null);
	
		TriangleMesh mesh = new TriangleMesh();
		float points[] = {				
				0, 0, 50, 		// P0
				50, 0, 50, 		// P1
				0, 50, 50, 		// P2
				50, 50, 50, 	// P3
				0, 0, 0, 		// P4
				50, 0, 0,		// P5
				0, 50, 0, 		// P6
				50, 50, 0 		// P7
		};

		float[] tex = {
				0.25f, 0, 					// T0
				0.5f, 0, 					// T1 
				0, 0.33f, 					// T2
				0.25f, 0.33f, 				// T3
				0.5f, 0.33f, 				// T4
				0.75f, 0.33f, 				// T5
				1, 0.33f, 					// T6
				0, 0.66f, 					// T7
				0.25f, 0.66f, 				// T8
				0.5f, 0.66f, 				// T9
				0.75f, 0.66f, 				// T10
				1, 0.66f, 					// T11
				0.25f, 1, 					// T12
				0.50f, 1 					// T13
		};

		int[] faces = {
				5,1, 4,0, 0,3,     //P5,T1 ,P4,T0  ,P0,T3
				5,1, 0,3, 1,4,     //P5,T1 ,P0,T3  ,P1,T4
				0,3, 4,2, 6,7,     //P0,T3 ,P4,T2  ,P6,T7
				0,3, 6,7, 2,8,     //P0,T3 ,P6,T7  ,P2,T8
				1,4, 0,3, 2,8,     //P1,T4 ,P0,T3  ,P2,T8
				1,4, 2,8, 3,9,     //P1,T4 ,P2,T8  ,P3,T9
				5,5, 1,4, 3,9,     //P5,T5 ,P1,T4  ,P3,T9
				5,5, 3,9, 7,10,    //P5,T5 ,P3,T9  ,P7,T10
				4,6, 5,5, 7,10,    //P4,T6 ,P5,T5  ,P7,T10
				4,6, 7,10 ,6,11,   //P4,T6 ,P7,T10 ,P6,T11
				3,9, 2,8, 6,12,    //P3,T9 ,P2,T8  ,P6,T12
				3,9, 6,12 ,7,13    //P3,T9 ,P6,T12 ,P7,T13
		};
		int[] faceSmoothingGroups = {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};
		mesh.getFaceSmoothingGroups().addAll(faceSmoothingGroups);
		mesh.getPoints().addAll(points);
		mesh.getTexCoords().addAll(tex);
		mesh.getFaces().addAll(faces);
	
		MeshView meshView = new MeshView(mesh);
		meshView.setMaterial(diceSurface);

		
		group.getChildren().add(meshView);
//		PerspectiveCamera camera = new PerspectiveCamera();
		diceRolling.getChildren().add(group);
//		diceRolling.getScene().setCamera(camera);
		group.translateXProperty().set(x);
		group.translateYProperty().set(y);
		group.translateZProperty().set(z);		
	}
	@FXML
    void diceMove(KeyEvent e){
//    	switch (e.getCode()){
//			case W:
//				group.translateZProperty().set(group.getTranslateZ() + 100);
//				break;
//			case S:
//				group.translateZProperty().set(group.getTranslateZ() - 100);
//				break;
//			case Q:
//				group.rotateByX(10);
//				break;
//			case E:
//				group.rotateByX(-10);
//				break;
//			case D:
//				group.rotateByY(10);
//				break;
//			case A:
//				group.rotateByY(-10);
//				break;
//		}
    }

	}
	class SmartGroup extends Group {

		Rotate r;
		Transform t = new Rotate();

		void rotateByX(int ang) {
			r = new Rotate(ang, Rotate.X_AXIS);
			t = t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}

		void rotateByY(int ang) {
			r = new Rotate(ang, Rotate.Y_AXIS);
			t = t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}
	}



