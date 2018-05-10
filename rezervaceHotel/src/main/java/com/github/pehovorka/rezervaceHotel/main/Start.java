package com.github.pehovorka.rezervaceHotel.main;

import com.github.pehovorka.rezervaceHotel.ui.HomeController;
import com.github.pehovorka.rezervaceHotel.logika.Rezervace;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/MainWindow.fxml"));
		Parent root = loader.load();

		HomeController controller = loader.getController();
		Rezervace rezervace = new Rezervace();
		//controller.inicializuj(rezervace);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Správa rezervací");
	}

}
