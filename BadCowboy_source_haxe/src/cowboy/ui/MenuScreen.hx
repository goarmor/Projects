package cowboy.ui;
import openfl.Assets;
import openfl.display.Bitmap;
import openfl.display.Sprite;
import openfl.Lib;
import openfl.display.SimpleButton;
import openfl.events.MouseEvent;
import openfl.events.Event;
import cowboy.ui.gameplay.GameplayScreen;

/**
 * ...
 * @author Dmitry
 */
class MenuScreen extends Sprite 
{
	private var keys:Array<Bool>;
	private var currentScreen:Sprite;
	
	function init() {
		//set background
		addChild(new Bitmap(Assets.getBitmapData("img/background/empty_background.png")));
		
		//buttonStart
		var bitmapButtonStart = Assets.getBitmapData("img/button/button_start.png");
		var b1 = new Bitmap(bitmapButtonStart);
		var b2 = new Bitmap(bitmapButtonStart);
		var b3 = new Bitmap(bitmapButtonStart);
		var b4 = new Bitmap(bitmapButtonStart);
		var pb1 = new SimpleButton (b1, b2, b3, b4);
		pb1.addEventListener(MouseEvent.CLICK, toGameplay);
		pb1.x = 350;
		pb1.y = 90;
        addChild(pb1);
		
		//buttonRules
		var bitmapButtonRules = Assets.getBitmapData("img/button/button_rules.png");
		var b1 = new Bitmap(bitmapButtonRules);
		var b2 = new Bitmap(bitmapButtonRules);
		var b3 = new Bitmap(bitmapButtonRules);
		var b4 = new Bitmap(bitmapButtonRules);
		var pb2 = new SimpleButton (b1, b2, b3, b4);
		pb2.addEventListener(MouseEvent.CLICK, toRules);
		pb2.x = 350;
		pb2.y = 230;
        addChild(pb2);
	}

	public function new() 
	{
		super();
		init();
		
    }
	
	private function toRules(e:Event):Void {
		Lib.current.removeChild(this);
		Lib.current.addChild(new RulesScreen());
	}
	
	
	private function toGameplay(e:Event):Void {
		Lib.current.removeChild(this);		
		Lib.current.addChild(new GameplayScreen());
	}
		
}
 

