package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * A Label which contains required informations and methods for a Player. These
 * informations are the id of the Player, this position and an image of the
 * Player.
 * 
 * @author Robin
 */
public class PlayerLabel extends Label {
	private int id;
	private VBox content;
	private Image image;
	private ImageView iv;
	private String position;
	private Player player;

	public PlayerLabel() {
	}

	@SuppressWarnings("static-access")
	public void setImage(Image image) {
		
		if(content == null){
			content = new VBox();
		}
		iv = new ImageView();
		iv.setImage(image);
		Label nl = new Label(player.getName());
		Label pl = new Label("("+player.getPoints()+" Punkte)");

		content.setVgrow(iv, Priority.NEVER);
		content.setVgrow(nl, Priority.NEVER);
		content.setVgrow(pl, Priority.NEVER);
		content.getChildren().addAll(iv, nl,pl);
		this.setGraphic(content);
		this.image = image;
	}

	
	public void setSize(double size) {
		iv.setFitHeight(size);
		double newWidth = size/180*150;
		iv.setFitWidth(newWidth);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	// GETTER AND SETTER
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPlayerId() {
		return this.id;
	}

	public void setPlayerId(int id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}
}
