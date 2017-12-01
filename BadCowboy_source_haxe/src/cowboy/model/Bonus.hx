package cowboy.model;
import openfl.display.Sprite;
import openfl.display.Bitmap;
import openfl.Assets;
/**
 * ...
 * @author Dmitriy
 */
class Bonus extends Sprite
{
	private var typeBonus:TypeBonus;
	
	public function new(typeBonus:TypeBonus) 
	{
		super();
		this.typeBonus = typeBonus;
		switch (typeBonus) {
			case KILLENEMIES: {
				addChild(new Bitmap(Assets.getBitmapData("img/bonuses/kill_enemies.png")));
			}
			case TIMEFREEZE: {
				addChild(new Bitmap(Assets.getBitmapData("img/bonuses/slowing_time.png")));
			}
		}
	}
	

	public function getType() {
		return typeBonus;
	}
	
}

enum TypeBonus {
		KILLENEMIES;
		TIMEFREEZE;
}