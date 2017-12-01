package cowboy.model;

import openfl.display.Sprite;
import openfl.display.Bitmap;
import openfl.Assets;

/**
 * ...
 * @author Dmitriy
 */
class Bullet extends Sprite
{
	private var speed:Float;
	
	public function new(playerCoordX:Float, playerCoordY:Float, speed:Float) 
	{
		super();
		
		this.speed = speed;
		
		this.x = playerCoordX + 16;
		this.y = playerCoordY + 28;
		
		addChild(new Bitmap(Assets.getBitmapData("img/bullets/bullet.png")));
	}
	
	public function moveBullet() {
		this.x += speed;
	}
	
	public function setSpeed(speed:Float) {
		this.speed = speed;
	}
}