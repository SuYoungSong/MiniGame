package application.bluemarble;

import application.Main;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class testController{
	
	void diceMake() {
		// MeshView에 올릴 이미지
		Image diffuseMap = new Image(Main.class.getResource("texture/dice.png").toExternalForm());
		Material diceSurface = new PhongMaterial(Color.WHITE, diffuseMap, null, null, null);
		
		// 이미지를 도형에 맞게 맵핑
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
				0.25f, 0, 					// T0 가로(왼쪽 부터) 4칸 > 25%
				0.5f, 0, 					// T1 세로(상단 부터) 3칸 > 33%
				0, 0.33f, 					// T2 왼쪽에 작성할때는 가로, 세로 순으로
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
		// MeshView 생성
		MeshView meshView = new MeshView(mesh);
		// MeshView 표면 설정
		meshView.setMaterial(diceSurface);
		// 그룹 설정
		SmartGroup group = new SmartGroup();
		group.getChildren().add(meshView);
		// 카메라 설정
		PerspectiveCamera camera = new PerspectiveCamera();
		// 그룹 초기 위치 설정
		group.translateXProperty().set(50);
		group.translateYProperty().set(50);
		group.translateZProperty().set(50);
}

//		// 키보드 컨트롤
//		void onPressKey(KeyEvent e) {
//			switch (e.getCode()) {
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
//			}
//		scene.setCamera(camera);
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
