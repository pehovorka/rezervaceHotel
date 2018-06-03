package com.github.pehovorka.rezervaceHotel.main;

import com.github.pehovorka.rezervaceHotel.ui.ControllerVyberRezimu;
import com.github.pehovorka.rezervaceHotel.logika.Hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***************************************************************************
 * Metoda, prostřednictvím níž se spouští celá aplikace.
 *
 * @param args
 *            Parametry příkazového řádku
 */
public final class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/vyberRezimu.fxml"));
		Parent root = loader.load();

		ControllerVyberRezimu controller = loader.getController();
		Hotel rezervace = new Hotel();
		controller.inicializuj(rezervace);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Výběr režimu");
		
	}

}
