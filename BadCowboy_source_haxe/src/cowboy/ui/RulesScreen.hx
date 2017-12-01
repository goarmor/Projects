package cowboy.ui;

import cowboy.ui.MenuScreen;
import openfl.Assets;
import openfl.display.Bitmap;
import openfl.display.Sprite;
import openfl.Lib;
import openfl.display.SimpleButton;
import openfl.events.MouseEvent;
import openfl.events.Event;
/**
 * ...
 * @author Dmitriy
 */

class RulesScreen extends Sprite {
	
	function init() {
		//background
		addChild(new Bitmap(Assets.getBitmapData("img/background/empty_background.png")));
		
		//buttonBack
		var bitmapButtonBack = Assets.getBitmapData("img/button/button_back.png");
		var b1 = new Bitmap(bitmapButtonBack);
		var b2 = new Bitmap(bitmapButtonBack);
		var b3 = new Bitmap(bitmapButtonBack);
		var b4 = new Bitmap(bitmapButtonBack);
		var pb = new SimpleButton (b1, b2, b3, b4);	
		pb.x = 350;
		pb.y = 230;
		pb.addEventListener(MouseEvent.CLICK, toMenu);
        addChild(pb);
	}

	public function new() 
	{
		super();
		init();
	}
	
	private function toMenu(e:Event):Void {
		Lib.current.removeChild(this);
		Lib.current.addChild(new MenuScreen());
	}
	
}