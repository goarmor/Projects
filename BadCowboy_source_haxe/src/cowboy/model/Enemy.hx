package cowboy.model;
import openfl.display.Sprite;
import openfl.display.Bitmap;
import openfl.Assets;
import cowboy.model.Bonus.TypeBonus;
/**
 * ...
 * @author Dmitriy
 */
class Enemy extends Sprite
{
	private var speed:Float;
	private var bonus:Bonus;
	//private var bonus:Sprite;
	
	public function new(coordX:Float, coordY:Float, speed:Float) 
	{
		super();
		var rand = Math.random();
		if ((rand > 0) && (rand < 0.05)) {
			bonus = new Bonus(TypeBonus.KILLENEMIES);
		} else if ((rand > 0.1) && (rand < 0.2)) {
			bonus = new Bonus(TypeBonus.TIMEFREEZE);
		} 
		addChild(new Bitmap(Assets.getBitmapData("img/enemy/enemy.png")));
		this.speed = speed;
		this.x = coordX;
		this.y = coordY;
	}
				
	public function moveLeft() {
		this.x -= speed;
	}	

	public function setSpeed(speed:Float) {
		this.speed = speed;
	}
	
	public function getSpeed():Float {
		return speed;
	}
	
	public function getBonus():Bonus {
		if (bonus == null) {
			return null;
		} else {
			bonus.x = this.x;
			bonus.y = 335;
			return bonus;
		}
			
	}
}