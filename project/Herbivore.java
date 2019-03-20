package project;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;



import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import naturesimulator.Action.Type;
import ui.GridPanel;
/**
 * 
 * @author Mehmet
 *
 */
public class Herbivore extends Creature {
	public Herbivore(int x, int y) {
		super(x,y);
	}
/**
 * 
 */
	@Override
	public  void draw(GridPanel panel) {
		Color color = null;
		Color color1 = new Color(160,1,1);
		Color color2 = new Color(195,1,1);
		Color color3 = new Color(225,1,1);
		Color color4 = new Color(255,1,1);
		if(this.getHealth()<6) {
			color=color4;
		}
		else if(this.getHealth()<10) {
			color = color3;	
		}
		else if(this.getHealth()<16) {
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
	@Override
	public Action chooseAction(LocalInformation localInformation) {
		List<Direction> toRandom = new ArrayList<Direction>();
		Direction randomDirection;
		if(canAttack(localInformation.getCreatureDown()) & !(localInformation.getFreeDirections().contains(Direction.DOWN))){
			toRandom.add(Direction.DOWN);
		}
		if(canAttack(localInformation.getCreatureUp()) & !(localInformation.getFreeDirections().contains(Direction.UP))){
			toRandom.add(Direction.UP);
		}
		if(canAttack(localInformation.getCreatureLeft()) & !(localInformation.getFreeDirections().contains(Direction.LEFT))){
			toRandom.add(Direction.LEFT);
		}
		if(canAttack(localInformation.getCreatureRight()) & !(localInformation.getFreeDirections().contains(Direction.RIGHT))){
			toRandom.add(Direction.RIGHT);
		}
		if(this.getHealth() == 20.0 & localInformation.getFreeDirections().size()>0) {
			randomDirection = LocalInformation.getRandomDirection(localInformation.getFreeDirections());
			return new Action (Type.REPRODUCE,randomDirection);
		}
		else if(localInformation.getFreeDirections().size()==4) {
			if(this.getHealth() <= 1.0) {
				return new Action(Type.STAY);
			}
			else {
				randomDirection = LocalInformation.getRandomDirection(localInformation.getFreeDirections());
				return new Action(Type.MOVE,randomDirection);
			}
		}
		else {
			randomDirection = LocalInformation.getRandomDirection(toRandom);
			if(randomDirection == Direction.DOWN) {
				toRandom.removeAll(toRandom);
				return new Action(Type.ATTACK,Direction.DOWN);
			}
			else if(randomDirection == Direction.UP) {
				toRandom.removeAll(toRandom);
				return new Action(Type.ATTACK,Direction.UP);
			}
			else if(randomDirection == Direction.LEFT) {
				toRandom.removeAll(toRandom);
				return new Action(Type.ATTACK,Direction.LEFT);
			}
			else if(randomDirection == Direction.RIGHT) {
				toRandom.removeAll(toRandom);
				return new Action(Type.ATTACK,Direction.RIGHT);
			}
			else {
				if(localInformation.getFreeDirections().size()>0) {
					if(this.getHealth() <= 1.0) {
						return new Action(Type.STAY);
					}
					randomDirection = LocalInformation.getRandomDirection(localInformation.getFreeDirections());
					return new Action(Type.MOVE,randomDirection);
				}
				else {
					return new Action(Type.STAY);
				}
			}
		}
	}
	/**
	 * 
	 */
	@Override
	public void stay() {
		this.setHealth(this.getHealth() - 0.1);

	}
	@Override
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
		Herbivore newHerbivore = new Herbivore(newX,newY);
		newHerbivore.setHealth(this.getHealth()/5);
		this.setHealth(this.getHealth()*0.4);
		return newHerbivore;

	}
	/**
	 * 
	 */
	@Override
	public void move(Direction direction) {
		int newX = 0;
		int newY = 0;
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
		this.setHealth(this.getHealth()-1.0);
		this.setX(newX);
		this.setY(newY);
	}
	@Override
	public void attack(Creature attackedCreature) {	
		int newX = attackedCreature.getX();
		int newY = attackedCreature.getY();
		this.setHealth(this.getHealth()  + attackedCreature.getHealth());
		this.setX(newX);
		this.setY(newY);
		attackedCreature.setHealth(0.0);
	}
	/**
	 * 
	 * @param targetGrid
	 * @return
	 */
	public boolean canAttack(Creature targetGrid) {
		if(targetGrid == null) {
			return false;
		}
		if(targetGrid.getClass()==Plant.class) {
			return true;
		}
		else {
			return false;
		}
	}


}
