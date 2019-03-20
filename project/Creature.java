package project;



import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;

import ui.GridPanel;
/**
 * 
 * @author Mehmet
 *Class that implements creatures' health and locations.
 */
public  class Creature implements Drawable{
	private int x;
	private int y;
	private double health;

	/**
	 * Creates a creature with location and health.
	 * @param x represents the location of creature at x axis.
	 * @param y represents the location of creature at y axis.
	 */
	public Creature(int x, int y) {
		this.x = x;
		this.y = y;
		Object other = this;
		if(other.getClass() == Plant.class) {
			this.setHealth(0.5);
		}
		else {
			this.setHealth(10.0);
		}

	}
	/**
	 * 
	 */
	@Override
	public void draw(GridPanel panel) {
	}
	/**
	 * Getter for the health of the creature.
	 * @return the value of creature's health.
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * 
	 * @param health_ the value of creature's health.
	 */
	public void setHealth(double health_) {
		if(this.getClass() == Plant.class) {
			if(health_>=1.0) {
				this.health = 1.0;
			}
			else {
				this.health = health_;
			}
		}
		else {
			if(health_>=20.0) {
				this.health = 20.0;
			}
			else {
				this.health = health_;
			}

		}
	}
	/**
	 * Getter for the location of creature at x axis..
	 * @return the value of x.
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * 
	 * @return the value of y.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * 
	 * @param x location of creature at x axis.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @param y location of creature at y axis.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 */
	public void stay() {
	}
	/**
	 * 
	 * @param direction the possible direction which creature can reproduce
	 * @return
	 */
	public Creature reproduce(Direction direction) {
		return null;
	}
	/**
	 * 
	 * @param localInformation represents the location of creauture.
	 * @return an Action according to some requirement.
	 */
	public Action chooseAction(LocalInformation localInformation) {
		return null;
	}
	/**
	 * 
	 * @param direction the possible direction which creature can move.
	 */
	
	public void move(Direction direction) {
	}
	/**
	 * 
	 * @param attackedCreature the possible direction which creature can attack.
	 */
	public void attack(Creature attackedCreature) {
	}


}
