package cowboy.model;
import openfl.display.Sprite;
import cowboy.common.Animation;
/**
 * ...
 * @author Dmitriy
 */
class Player extends Sprite
{
	private var moveSpeed:Float;
	private var timeFreezeBonus:Bonus;
	private var jumpSpeed:Float;
	private var animation:Animation;
	private var onGround:Bool;
	private var isUp:Bool;
	private var bullet:Bullet;
	
	public function new(playerCoordX:Float, playerCoordY:Float, moveSpeed:Float, jumpSpeed:Float) 
	{
		super();
		onGround = true;
		isUp = false;
		this.moveSpeed = moveSpeed;
		this.jumpSpeed = jumpSpeed;
		//animation
		animation = new Animation(this);
		this.x = playerCoordX;
		this.y = playerCoordY;
	}
	
	public function getMoveSpeed():Float {
		return moveSpeed;
	}
	
	public function setMoveSpeed(moveSpeed:Float) {
		this.moveSpeed = moveSpeed;
	}
	
	public function getBonus() {
		return timeFreezeBonus;
	}
	
	public function pickBonus(bonus:Bonus){
		this.timeFreezeBonus = bonus;
		slowSpeed();
	}
	
	public function bonusUsed() {
		timeFreezeBonus = null;
		normalSpeed();
	}
		
	public function moveRight() {
		this.x += moveSpeed;
		animation.startWalkAnim();
	}
	
	public function moveLeft() {
		this.x -= moveSpeed;
		animation.startWalkAnim();
	}
	
	public function getJumpSpeed() {
		return jumpSpeed;
	}
	
	public function setJumpSpeed(jumpSpeed:Float) {
		this.jumpSpeed = jumpSpeed;
	}
	
	public function startJump() {
		onGround = false;
		isUp = true;
	}
		
	public function tryShoot():Void {
		if (!animation.isShootAnimStartedd()) {
			bullet = new Bullet(this.x, this.y, 8);
			animation.startShootAnim();
		}
	}
	
	public function getBullet():Bullet {
		return bullet;
	}
	
	public function delBullet() {
		bullet = null;
	}
	
	public function startStand() {
		animation.startStandAnim();
	}
	
	public function isOnGround() {
		return onGround;
	}
	
	public function slowSpeed() {
		animation.setDelayWalkAnim(1000);
		animation.setDelayStandAnim(2000);
		jumpSpeed /= 2;
		moveSpeed /= 2;
	}
	
	public function normalSpeed() {
		jumpSpeed *= 2;
		moveSpeed *= 2;
		animation.setDelayWalkAnim(500);
		animation.setDelayStandAnim(1000);
	}
	
	public function pause() {
		animation.pauseShootAnim();
		animation.pauseStandAnim();
		animation.pauseWalkAnim();
	}
	
	public function resume() {
		animation.resumeShootAnim();
		animation.resumeWalkAnim();
		animation.resumeStandAnim();
	}
	
	public function stand() {
		animation.startStandAnim();
	}
	
	public function jump():Void {
		if (isUp) {
			if (this.y >= 200) { //up
				this.y -= jumpSpeed;
			}
			else isUp = false;
		} else if (this.y < 310) { //down
				this.y += jumpSpeed;
			} else onGround = true;
	}
	
}