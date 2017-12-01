package cowboy.common;
import openfl.Assets;
import openfl.display.Bitmap;
import openfl.display.DisplayObject;
import openfl.display.Sprite;
import openfl.utils.Timer;
import openfl.events.TimerEvent;

/**
 * ...
 * @author Dmitriy
 */
class Animation 
{
	private var player:Sprite;
	private var timerStandAnim:Timer;
	private var timerWalkAnim:Timer;
	private var timerShootAnim:Timer;
	private var isWalkAnimStarted:Bool;
	private var isStandAnimStarted:Bool;
	private var isShootAnimStarted:Bool;
	
	public function new(player:Sprite)
	{
		this.player = player;
		
		//AnimStarted
		isWalkAnimStarted = false;
		isStandAnimStarted = false;
		isShootAnimStarted = false;
		
		timerStandAnim = new Timer(1000);
		timerWalkAnim = new Timer(500);
		timerShootAnim = new Timer(300);
	}
	
	public function startStandAnim():Void {
		if (!isShootAnimStarted) {
		stopWalkAnim();
		
		isStandAnimStarted = true;
		
		var bit1 = new Bitmap(Assets.getBitmapData("img/player/stand/Cowboy2_idle with gun_0.png"));
		var bit2  = new Bitmap(Assets.getBitmapData("img/player/stand/Cowboy2_idle with gun_3.png"));
		
		player.removeChildren();
		player.addChild(bit1);
		
		var isScreen1 = true;
		
		timerStandAnim.addEventListener(TimerEvent.TIMER, function(e:TimerEvent):Void{
			player.removeChildren();
			if (isScreen1){
				player.addChild(bit2);	
				isScreen1 = false;
			} else {
				player.addChild(bit1);
				isScreen1 = true;
			}
		});
		timerStandAnim.start();
		}
	}
	
	public function startWalkAnim():Void {
		if (!isShootAnimStarted && !isWalkAnimStarted) {
			stopStandAnim();
			isWalkAnimStarted = true;
		
			var bit1 = new Bitmap(Assets.getBitmapData("img/player/run/Cowboy2_walk with gun_0.png"));
			var bit2 = new Bitmap(Assets.getBitmapData("img/player/run/Cowboy2_walk with gun_1.png"));
			var bit3 = new Bitmap(Assets.getBitmapData("img/player/run/Cowboy2_walk with gun_2.png"));
			var bit4 = new Bitmap(Assets.getBitmapData("img/player/run/Cowboy2_walk with gun_3.png"));
		
			var runScreen = 1;
			player.removeChildren();
			player.addChild(bit1);
		
			timerWalkAnim.addEventListener(TimerEvent.TIMER, function(e:TimerEvent):Void{
				player.removeChildren();
				switch (runScreen) {
					case 1:{
						player.addChild(bit2);	
						runScreen = 2;
					}
					case 2: {
						player.addChild(bit3);	
						runScreen = 3;
					}
					case 3: {
						player.addChild(bit4);	
						runScreen = 4;
					}
					case 4: {
						player.addChild(bit1);	
						runScreen = 1;
					}
				};
			});
			timerWalkAnim.start();
		}
	}
	
	public function startShootAnim():Void {
		if (!isShootAnimStarted) {
	    stopStandAnim();
		stopWalkAnim();
		isShootAnimStarted = true;
		
		var bit1 = new Bitmap(Assets.getBitmapData("img/player/shoot/Cowboy2_shoot_1.png"));
		var bit2 = new Bitmap(Assets.getBitmapData("img/player/shoot/Cowboy2_shoot_2.png"));
		var bit3 = new Bitmap(Assets.getBitmapData("img/player/shoot/Cowboy2_shoot_3.png"));
		
		var runScreen = 1;

		player.removeChildren();
		player.addChild(bit1);
		
		timerShootAnim.addEventListener(TimerEvent.TIMER, function(e:TimerEvent):Void{
				switch (runScreen) {
					case 1:{
						player.removeChildren();
						player.addChild(bit2);	
						runScreen = 2;
					}
					case 2: {
						player.removeChildren();
						player.addChild(bit3);	
						runScreen = 3;
					}
					case 3: {
						player.removeChildren();
						player.addChild(bit1);	
						runScreen = 0;
						stopShootAnim();
						startStandAnim();
					}
					
				};			
		});
		timerShootAnim.start();
		}
	}
	
	public function stopStandAnim():Void {
		isStandAnimStarted = false;
		timerStandAnim.stop();
	}
	
	public function stopWalkAnim():Void {
		isWalkAnimStarted = false;
		timerWalkAnim.stop();
	}
	
	public function stopShootAnim():Void {
		isShootAnimStarted = false;
		timerShootAnim.stop();
	}
	
	public function pauseStandAnim() {
		timerStandAnim.stop();
	}
	
	public function pauseWalkAnim() {
		timerWalkAnim.stop();
	}
	
	public function pauseShootAnim() {
		timerShootAnim.stop();
	}
	
	public function resumeStandAnim() {
		timerStandAnim.start();
	}
	
	public function resumeShootAnim() {
		timerShootAnim.start();
	}
	
	public function resumeWalkAnim() {
		timerWalkAnim.start();
	}
	
	public function setDelayWalkAnim(delay:Int) {
		timerWalkAnim.delay = delay;
	}
	
	public function setDelayStandAnim(delay:Int) {
		timerStandAnim.delay = delay;
	}
	
	public function setDelayShootAnim(delay:Int) {
		timerShootAnim.delay = delay;
	}
	
	public function isShootAnimStartedd():Bool {
		return isShootAnimStarted;
	}
}