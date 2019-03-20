package project;

import java.awt.Color;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.Action.Type;
import naturesimulator.LocalInformation;
import ui.GridPanel;
/**
 * 
 * @author Mehmet Emin Arslan
 * Class representing plants
 */
public class Plant extends Creature {

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Plant (int x, int y) {
		super(x,y);

	}
	/**
	 * 
	 */
	public void draw(GridPanel panel) {
		Color color = Color.GREEN;
		Color color1 = new Color(1,195,1);
		Color color2 = new Color(1,205,1);
		Color color3 = new Color(1,220,1);
		Color color4 = new Color(1,235,1);
		Color color5 = new Color(1,255,1);
		if(this.getHealth()<0.4) {
			color=color5;
		}
		else if(this.getHealth()<0.70) {
			color=color4;
		}
		else if(this.getHealth()<0.80) {
			color=color3;
		}
		else if(this.getHealth()<0.95) {
			color=color2;
		}
		else {
			color=color1;
		}
		panel.drawSquare(super.getX(), super.getY(), color);
	}
	/**
	 * 
	 */
	public Action chooseAction(LocalInformation localInformation) {
		if(this.getHealth() >= 0.75 & localInformation.getFreeDirections().size()>0) {
			Direction randomDirection = LocalInformation.getRandomDirection(localInformation.getFreeDirections());
			return new Action (Type.REPRODUCE,randomDirection);
		}
		else {
			return new Action(Type.STAY);
		}
	}
	/**
	 * 
	 */
	public void stay() {
		this.setHealth(this.getHealth()+0.05);
	}
	/**
	 * 
	 */
	public Creature reproduce(Direction direction) {
		int newX;
		int newY;

		if(direction == Direction.UP) {
			newX=this.getX();
			newY=this.getY()-1;
		}
		else if(direction == Direction.DOWN){
			newX=this.getX();
			newY=this.getY()+1;
		}
		else if(direction == Direction.RIGHT) {
			newX=this.getX()+1;
			newY=this.getY();
		}
		else {
			newX=this.getX()-1;
			newY=this.getY();
		}
		Plant newPlant = new Plant(newX,newY);
		newPlant.setHealth(this.getHealth()/10);
		this.setHealth(this.getHealth()*0.7);
		return newPlant;
	}


}