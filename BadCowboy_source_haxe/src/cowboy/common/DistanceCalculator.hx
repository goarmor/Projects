package cowboy.common;
import openfl.display.Sprite;

/**
 * ...
 * @author Dmitriy
 */
class DistanceCalculator 
{
	public static function distanceY(sprite1:Sprite, sprite2:Sprite):Float {
		var deltaY = sprite1.y - sprite2.y;	
		if (deltaY < 0) deltaY *= -1;
		return deltaY;
	}
	
	public static function distanceX(sprite1:Sprite, sprite2:Sprite):Float {
		var deltaX = sprite1.x - sprite2.x;	
		if (deltaX < 0) deltaX *= -1;
		return deltaX;
	}
	
}