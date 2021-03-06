package client.view;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import server.SesameServerInterface;

import client.SesameObserver;
import client.controller.SchatkamerController;

public class SchatkamerView extends UnicastRemoteObject implements ViewInterface,
			SesameObserver {

	private static final long serialVersionUID = 1L;
	private SchatkamerController controller;
	private StackPane pane = new StackPane();
	private TilePane stapels = new TilePane();
	private int stapelIndex;
	private boolean enabled;

	public SchatkamerView(SchatkamerController controller) throws RemoteException{
		this.controller = controller;
		this.controller.setObserver(this, 3);

		stapels.setPrefColumns(3);
		stapels.setPrefRows(3);
		stapels.setHgap(10);
		stapels.setVgap(10);

		Image image = new Image(new File("src/client/resources/layout/achtergrond_610.png").toURI().toString());
		stapels.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

		pane.getChildren().add(stapels);
		this.createStapels();
		this.setEnabled(false);
	}

	public void createStapels() {
		stapelIndex = 0;
		stapels.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	Button button = new Button("Sesame");
            	button.setId(String.valueOf(stapelIndex));
            	button.setPrefSize(180, 180);
            	button.setOnAction(e-> {
            		this.controller.pakKaart(Integer.parseInt(button.getId()));
            	});
            	stapels.getChildren().add(button);
                stapelIndex++;
            }
        }
	}

	public ImageView createButton(String pad) {
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/" + pad + ".png").toURI().toString()));
		return graphic;
	}

	public void toonKaart(String kaart, int positie) {
		Button button = new Button();
		button.setGraphic(createButton("models/schat_" + kaart));
		button.setTranslateY(50);
		button.setOnAction(e-> {
			pane.getChildren().remove(1);
		});
		pane.getChildren().add(button);
		System.out.println("HEY");
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						String kaart = server.getSchatkamer().getKaart();
						System.out.println(kaart);
						int positie = server.getSchatkamer().getActiefStapelPositie();
						this.toonKaart(kaart, positie);
						this.controller.setServer(server);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		);
	}

	@Override
	public boolean isEnabled() throws RemoteException {
		return this.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {
		this.enabled = enabled;
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

	@Override
	public void updateMode() throws RemoteException {
		return;
	}

}
