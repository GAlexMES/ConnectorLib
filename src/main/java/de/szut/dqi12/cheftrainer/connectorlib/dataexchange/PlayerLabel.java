package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Robin
 * A Label which contains required informations and methods for a Player.
 * These informations are the id of the Player, this position and an image of the Player.
 */
public class PlayerLabel extends Label {
	private int id;
	private Image image;
	private String position;

	//getters and setters
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

	public void setImage(Image image) {
		ImageView iv = new ImageView();
		iv.fitHeightProperty().bind(this.heightProperty());
		iv.fitWidthProperty().bind(this.heightProperty());
		iv.setImage(image);
		this.setGraphic(iv);
		this.image = image;
	}

}
