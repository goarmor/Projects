package cowboy.ui.gameplay;
import cowboy.common.DistanceCalculator;
import openfl.Lib;
import openfl.events.Event;
import openfl.events.KeyboardEvent;
import openfl.utils.Timer;
import openfl.events.TimerEvent;
import openfl.events.MouseEvent;
import cowboy.model.Bullet;
import cowboy.model.Enemy;
import cowboy.model.Bonus;
import cowboy.ui.gameplay.GameplayScreen;
import cowboy.ui.MenuScreen;

/**
 * ...
 * @author Dmitriy
 */
class GameplayLogic
{
	private var keys:Array<Bool>;
	private var enemies:Array<Enemy> = [];
	private var bullets:Array<Bullet> = [];
	private var bonuses:Array<Bonus> = [];
	private var alltimeToCreateEnemy:Float;
	private var timeToCreateEnemy:Float;
	private var enemySpeed:Float;
	private var gameplayScreen:GameplayScreen;
	private var timeSlowSpeed:Float;
	
	public function new(gameplayScreen:GameplayScreen) 
	{
		//timer 
		timeSlowSpeed = 0;
		
		//time to create enemy
		alltimeToCreateEnemy = 46;
		timeToCreateEnemy = alltimeToCreateEnemy;
		
		this.gameplayScreen = gameplayScreen;
		
		enemySpeed = 3;
		
		// Keyboard input detection
		keyboardDetection();
	
		//set focus
		gameplayScreen.stage.focus = gameplayScreen;
									
		//start stand animation
		gameplayScreen.getPlayer().startStand();
	
		//start game loop
		gameplayScreen.addEventListener(Event.ENTER_FRAME, gameLoop);
	}
	
	
	private function gameLoop(e:Event):Void {
		createEnemy();
				
		moveEnemies();
		
		collisions();
		
		moveBullets();
		
		if (gameplayScreen.getPlayer().getBonus() != null) {
			timeSlowSpeed++;
			if (timeSlowSpeed == 600) {
				normalSpeed();
				timeSlowSpeed = 0;
			}
		}
		
		if (gameplayScreen.getPlayer().isOnGround() && keys[87]) 
			gameplayScreen.getPlayer().startJump();
				
		if (!gameplayScreen.getPlayer().isOnGround()) 
			gameplayScreen.getPlayer().jump();
		
		
		if (keys[68]) {
			if (gameplayScreen.getPlayer().x < 770)
			gameplayScreen.getPlayer().moveRight();
			
		} else if (keys[65]) 
			if (gameplayScreen.getPlayer().x > 0)
			gameplayScreen.getPlayer().moveLeft();
	}
	
	private function createEnemy() {
		timeToCreateEnemy--;
		if (timeToCreateEnemy <= 0)
		{
			timeToCreateEnemy = alltimeToCreateEnemy;
			var enemy = new Enemy(800, 304, enemySpeed);
			gameplayScreen.addChild(enemy);
			enemies.push(enemy);
		}
	}
	
	public function pause(e:MouseEvent) {
		stopKeyboardDetection();
		gameplayScreen.removeChild(gameplayScreen.getPauseButton());
		gameplayScreen.addChild(gameplayScreen.getResumeButton());
		gameplayScreen.removeEventListener(Event.ENTER_FRAME, gameLoop);
		gameplayScreen.getBackground().removeEventListener(MouseEvent.MOUSE_DOWN, onMouseClick);
		gameplayScreen.getPlayer().pause();
	}
	
	public function resume(e:MouseEvent) {
		keyboardDetection();
		gameplayScreen.removeChild(gameplayScreen.getResumeButton());
		gameplayScreen.addChild(gameplayScreen.getPauseButton());
		gameplayScreen.getBackground().addEventListener(MouseEvent.MOUSE_DOWN, onMouseClick);
		gameplayScreen.getPlayer().resume();
		gameplayScreen.addEventListener(Event.ENTER_FRAME, gameLoop);
	}
	
	private function gameEnd() {
		pause(new MouseEvent("game End"));
		gameplayScreen.removeChild(gameplayScreen.getResumeButton());
        gameplayScreen.addChild(gameplayScreen.getTryagainButton());	
	}
	
	public function collisions() {
		//check collisions for enemies and player
		for (field in enemies) {
			field.setSpeed(enemySpeed);
			var enemy = field;
			if ((DistanceCalculator.distanceX(gameplayScreen.getPlayer(), enemy) < 30) && 
				(DistanceCalculator.distanceY(gameplayScreen.getPlayer(), enemy) < 54)) 
				gameEnd();
				
			//check collisions for enemies and bullets
			for (field in bullets) {
				var bullet = field;
				if (enemy.x > gameplayScreen.getPlayer().x) {
					if (((enemy.x - bullet.x) < 5) && 
						((enemy.y - bullet.y) < 5)) {
						killEnemy(enemy);
						removeBullet(bullet);
					}
				}
			}
		}
		//check collisions for player and bonuses
		for (field in bonuses) {
			if ((DistanceCalculator.distanceX(gameplayScreen.getPlayer(), field) < 20) && 
				(DistanceCalculator.distanceY(gameplayScreen.getPlayer(), field) < 40)) {
				if (field.getType() == TypeBonus.KILLENEMIES) {
					bonusPicked(field);
					killAllEnemies();
				} else if (gameplayScreen.getPlayer().getBonus() == null) 
						pickTimeFreezeBonus(field);	
			}
		}
	}	
	
	
	private function pickTimeFreezeBonus(bonus:Bonus) {
		setLevelButtonsInvisible();
		gameplayScreen.getPlayer().pickBonus(bonus);
		bonusPicked(bonus);
		//set enemy slow speed
		alltimeToCreateEnemy *= 4;
		timeToCreateEnemy *= 4;
		enemySpeed /= 4;
	}
	
	private function normalSpeed() {
		gameplayScreen.getPlayer().bonusUsed();
		setLevelButtonsVisible();
		enemySpeed *= 4;
		alltimeToCreateEnemy /= 4;
		timeToCreateEnemy /= 4;
	}
	
	public function setLevelButtonsInvisible(){
		gameplayScreen.getLevel1Button().visible = false;
		gameplayScreen.getLevel2Button().visible = false;
		gameplayScreen.getLevel3Button().visible = false;
	}
	
	public function setLevelButtonsVisible(){
		gameplayScreen.getLevel1Button().visible = true;
		gameplayScreen.getLevel2Button().visible = true;
		gameplayScreen.getLevel3Button().visible = true;
	}
	
	public function setLevel1(evt:Event) {
		enemySpeed = 3;
		alltimeToCreateEnemy = 46;
	}
	
	public function setLevel2(evt:Event) {
		enemySpeed = 4;
		alltimeToCreateEnemy = 40;
	}
	
	public function setLevel3(evt:Event) {
		enemySpeed = 5;
		alltimeToCreateEnemy = 37;
	}
			
	private function bonusPicked(bonus:Bonus) {
		addScore();
		gameplayScreen.removeChild(bonus);
		bonuses.remove(bonus);
	}
	
	
	private function killAllEnemies() {
		while (enemies.length != 0) 
			killEnemy(enemies.pop());
	}
	
	
	private function killEnemy(enemy:Enemy) {
		dropBonus(enemy);
		removeEnemy(enemy);
		addScore();
	}
	
	
	private function dropBonus(enemy:Enemy) {
		var bonus = enemy.getBonus();
		if (bonus != null) {
			bonuses.push(bonus);
			gameplayScreen.addChild(bonus);
		}
	}
	
	
	private function addScore() {
		gameplayScreen.getScoreTextField().addScore();
		if (gameplayScreen.getScoreTextField().getScore() == 30) 
			gameEnd();
	}
	
	private function removeBullet(bullet:Bullet) {
		gameplayScreen.removeChild(bullet);
		bullets.remove(bullet);
	}
	
	private function removeEnemy(enemy:Enemy) {
		gameplayScreen.removeChild(enemy);
		enemies.remove(enemy);
	}
	
	
	private function moveEnemies() {
		for (field in enemies) {
			field.moveLeft();
			if (field.x < -20) 
				removeEnemy(field);
		}
	}
	
	private function moveBullets():Void {
		var bullet = gameplayScreen.getPlayer().getBullet();
		if (bullet != null) {
			gameplayScreen.getPlayer().delBullet();
			gameplayScreen.addChild(bullet);
			bullets.push(bullet);
		}
		for (field in bullets) {
			field.moveBullet();
			if (field.x > 800) 
				removeBullet(field);
		}
	}
		
	private function onKeyDown(evt:KeyboardEvent):Void {
		keys[evt.keyCode] = true;
		if (evt.keyCode == 32) 
			gameplayScreen.getPlayer().tryShoot();
	}
	
	private function onKeyUp(evt:KeyboardEvent):Void {
		keys[evt.keyCode] = false;
		if ((evt.keyCode == 68) || (evt.keyCode == 65)) 
			gameplayScreen.getPlayer().stand();
	}
	
	
	public function onMouseClick(evt:MouseEvent):Void {
		gameplayScreen.getPlayer().tryShoot();
	}
	
	public function toMenu(e:Event):Void {
		Lib.current.removeChild(gameplayScreen);
		gameplayScreen.removeEventListener(Event.ENTER_FRAME, gameLoop);
		Lib.current.addChild(new MenuScreen());
	}
	
	private function keyboardDetection() {
		keys = [];
	    gameplayScreen.stage.addEventListener(KeyboardEvent.KEY_DOWN, onKeyDown);
		gameplayScreen.stage.addEventListener(KeyboardEvent.KEY_UP, onKeyUp);
	}
	
	private function stopKeyboardDetection() {
		gameplayScreen.stage.removeEventListener(KeyboardEvent.KEY_DOWN, onKeyDown);
		gameplayScreen.stage.removeEventListener(KeyboardEvent.KEY_UP, onKeyUp);
	}
	
	public function tryAgain(e:MouseEvent):Void{
		Lib.current.removeChild(gameplayScreen);
		Lib.current.addChild(new GameplayScreen());		
	}
	
	
}
	
