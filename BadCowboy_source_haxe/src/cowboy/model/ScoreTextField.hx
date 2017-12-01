package cowboy.model;
import openfl.Assets;
import openfl.text.TextField;
import openfl.text.TextFormat;

/**
 * ...
 * @author Dmitriy
 */
class ScoreTextField extends TextField
{
	private var score:Int;
	private static inline var scoreText:String = " < 30 to WIN"; 
	
	public function new() 
	{
		super();
		
		score = 0;
		
		this.selectable = false;
		this.text = score + scoreText;
		this.textColor = 0xffffff;
		this.defaultTextFormat = new TextFormat("_sans", 17);
		this.x = 350;
		this.y = 10;
		this.width = 300;
	}
	
	public function addScore() {
		score++;
		this.text = score + scoreText;
	}
	
	public function getScore() {
		return score;
	}
	
	public function setScore(score:Int) {
		this.score = score;
	}
	
}