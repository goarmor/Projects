package cowboy.ui.gameplay;
import openfl.Assets;
import openfl.display.Bitmap;
import openfl.display.Sprite;
import openfl.display.SimpleButton;
import openfl.events.Event;
import openfl.events.MouseEvent;
import cowboy.model.Player;
import cowboy.model.ScoreTextField;
import cowboy.ui.gameplay.GameplayLogic;

/**
 * ...
 * @author Dmitriy
 */
class GameplayScreen extends Sprite
{
	private var player:Player;
	private var backgr:Sprite;
	private var pauseButton:SimpleButton;
	private var resumeButton:SimpleButton;
	private var tryagainButton:SimpleButton;
	private var scoreTextField:ScoreTextField;
	private var quitButton:SimpleButton;
	private var level1Button:SimpleButton;
	private var level2Button:SimpleButton;
	private var level3Button:SimpleButton;
	
	function init() {
		
		//set background
		backgr = new Sprite();
		backgr.addChild(new Bitmap(Assets.getBitmapData("img/background/swamp.png")));
		addChild(backgr);
		
		//player
		player = new Player(100, 310, 3, 6);
		addChild(player);
				
		// Text
		scoreTextField = new ScoreTextField();
		addChild(scoreTextField);
		
		//buttonQuit
		var bitmapButtonQuit = Assets.getBitmapData("img/button/button_quit.png");
		quitButton = new SimpleButton (new Bitmap(bitmapButtonQuit), new Bitmap(bitmapButtonQuit), 
											new Bitmap(bitmapButtonQuit), new Bitmap(bitmapButtonQuit));
		quitButton.x = 670;
		quitButton.y = 12;
        addChild(quitButton);	
		
		//try_again button
		var bitmap = Assets.getBitmapData("img/button/button_tryagain.png");
		tryagainButton = new SimpleButton (new Bitmap(bitmap), new Bitmap(bitmap), 
											new Bitmap(bitmap), new Bitmap(bitmap));
		tryagainButton.x = 340;
		tryagainButton.y = 165;
		
		//buttonPause
		var bitmapButtonPause = Assets.getBitmapData("img/button/button_pause.png");
		pauseButton = new SimpleButton (new Bitmap(bitmapButtonPause), new Bitmap(bitmapButtonPause), 
											new Bitmap(bitmapButtonPause), new Bitmap(bitmapButtonPause));
		pauseButton.x = 670;
		pauseButton.y = 70;
        addChild(pauseButton);
		
		//buttonResume
		var bitmapButtonResume = Assets.getBitmapData("img/button/button_resume.png");
		resumeButton = new SimpleButton (new Bitmap(bitmapButtonResume), new Bitmap(bitmapButtonResume), 
											new Bitmap(bitmapButtonResume), new Bitmap(bitmapButtonResume));
		resumeButton.x = 670;
		resumeButton.y = 70;
		
		//level1 button
		var bitmapLevel1Button = Assets.getBitmapData("img/button/level_1_button.png");
		level1Button = new SimpleButton (new Bitmap(bitmapLevel1Button), new Bitmap(bitmapLevel1Button), 
											new Bitmap(bitmapLevel1Button), new Bitmap(bitmapLevel1Button));
		level1Button.x = 500;
		level1Button.y = 20;
		addChild(level1Button);
		
		//level2 button
		var bitmapLevel2Button = Assets.getBitmapData("img/button/level_2_button.png");
		level2Button = new SimpleButton (new Bitmap(bitmapLevel2Button), new Bitmap(bitmapLevel2Button), 
											new Bitmap(bitmapLevel2Button), new Bitmap(bitmapLevel2Button));
		level2Button.x = 550;
		level2Button.y = 20;
		addChild(level2Button);
		
		//level3 button
		var bitmapLevel3Button = Assets.getBitmapData("img/button/level_3_button.png");
		level3Button = new SimpleButton (new Bitmap(bitmapLevel3Button), new Bitmap(bitmapLevel3Button), 
											new Bitmap(bitmapLevel3Button), new Bitmap(bitmapLevel3Button));
		level3Button.x = 600;
		level3Button.y = 20;
		addChild(level3Button);
	}

	public function new() 
	{
		super();
		addEventListener(Event.ADDED_TO_STAGE, added);	
	}
	
	function added(e) 
	{
		init();
		var gameplayLogic = new GameplayLogic(this);
		backgr.addEventListener(MouseEvent.MOUSE_DOWN, gameplayLogic.onMouseClick);
		quitButton.addEventListener(MouseEvent.CLICK, gameplayLogic.toMenu);
		tryagainButton.addEventListener(MouseEvent.CLICK, gameplayLogic.tryAgain);
		pauseButton.addEventListener(MouseEvent.CLICK, gameplayLogic.pause);
		resumeButton.addEventListener(MouseEvent.CLICK, gameplayLogic.resume);
		level1Button.addEventListener(MouseEvent.CLICK, gameplayLogic.setLevel1);
		level2Button.addEventListener(MouseEvent.CLICK, gameplayLogic.setLevel2);
		level3Button.addEventListener(MouseEvent.CLICK, gameplayLogic.setLevel3);
	}
	
	
	public function getPlayer() {
		return player;
	}
	
	public function getPauseButton() {
		return pauseButton;
	}
	
	public function getResumeButton() {
		return resumeButton;
	}
	
	public function getTryagainButton() {
		return tryagainButton;
	}
	
	public function getLevel1Button() {
		return level1Button;
	}
	
	public function getLevel2Button() {
		return level2Button;
	}
	
	public function getLevel3Button() {
		return level3Button;
	}
	
	public function getScoreTextField() {
		return scoreTextField;
	}
	
	public function getBackground() {
		return backgr;
	}
}
	
