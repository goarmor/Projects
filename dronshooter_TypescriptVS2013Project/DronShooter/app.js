// -------------------------------------------------------------------------
// -------------------------------------------------------------------------
// -------------------------------------------------------------------------
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var Gameplay = (function (_super) {
    __extends(Gameplay, _super);
    function Gameplay() {
        _super.apply(this, arguments);
        this._cannonTip = new Phaser.Point();
        this._style = { font: "20px Arial", fill: "#ff0000", align: "center" };
        this._styleMissilesText = { font: "16px Arial", fill: "#ffffff", align: "center" };
        this._styleBestScore = { font: "22px Arial", fill: "#c9815a", align: "center" };
    }
    Gameplay.prototype.create = function () {
        this._shootSound = this.game.add.audio("shoot");
        this._explosionSound = this.game.add.audio("explosion");
        this._game_overSound = this.game.add.audio("game_over");
        this._new_gameSound = this.game.add.audio("new_game");
        this._no_missilesSound = this.game.add.audio("no_missiles");
        this._bonus_pickSound = this.game.add.audio("pick_bonus");
        this._new_gameSound.play();
        // background
        this.add.image(0, 0, "BG");
        this._time = 0;
        this._missilesCount = 100;
        var bestTime = localStorage.getItem("bestTime");
        if (bestTime != null) {
            this._bestTime = bestTime;
            this._bestTimeText = this.game.add.text(3, 26, Gameplay.BEST_TIME_TEXT + this._bestTime, this._styleBestScore);
            this._bestTimeText.anchor.setTo(0, 0);
        }
        this._missilesText = this.game.add.text(3, this.world.height, Gameplay.MISSILES_TEXT + this._missilesCount.toString(), this._styleMissilesText);
        this._missilesText.anchor.setTo(0, 1);
        // set physiscs to P2 physics engin
        this.game.physics.startSystem(Phaser.Physics.P2JS);
        // cannon - place it in the bottom center
        this._cannon = this.game.add.sprite(this.world.centerX, this.world.height, "Atlas", "cannon");
        // offset it from position
        this._cannon.anchor.setTo(-0.75, 0.5);
        // make it point straight up
        this._cannon.rotation = -Math.PI / 2;
        //  Game input
        this.game.input.keyboard.addKey(Phaser.Keyboard.LEFT);
        this.game.input.keyboard.addKey(Phaser.Keyboard.RIGHT);
        this._space = this.game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
        // following keys will not be propagated to browser
        this.game.input.keyboard.addKeyCapture([Phaser.Keyboard.LEFT, Phaser.Keyboard.RIGHT, Phaser.Keyboard.SPACEBAR]);
        // allow inpact events
        this.game.physics.p2.setImpactEvents(true);
        //  collision groups for drones
        this._dronesCollisionGroup = this.game.physics.p2.createCollisionGroup();
        //  collision groups for missiles
        this._missilesCollisionGroup = this.physics.p2.createCollisionGroup();
        //  collision groups for turret
        this._turretCollisionGroup = this.physics.p2.createCollisionGroup();
        //  collision groups for bonuses
        this._bonusesCollisionGroup = this.physics.p2.createCollisionGroup();
        //turret group
        this._turret = this.add.group();
        this._turret.physicsBodyType = Phaser.Physics.P2JS;
        this._turret.enableBody = true;
        //create turret
        this._turret.create(this.world.centerX, this.world.height, "Atlas", "base");
        this._turret.forEach(function (tur) {
            // setup physics
            tur.anchor.setTo(0.5, 1);
            var body = tur.body;
            body.setRectangle(70, 50);
            body.setCollisionGroup(this._turretCollisionGroup);
            body.collides(this._dronesCollisionGroup, this.newGame, this);
        }, this);
        // drones group
        this._drones = this.add.group();
        this._drones.physicsBodyType = Phaser.Physics.P2JS;
        this._drones.enableBody = true;
        // create 8 drones
        this._drones.classType = Dron;
        this._drones.createMultiple(4, "Atlas", "dron1");
        this._drones.forEach(function (aDron) {
            // setup movements and animations
            aDron.setUp();
            // setup physics
            var body = aDron.body;
            body.setCircle(aDron.width / 2);
            body.kinematic = true; // does not respond to forces
            body.setCollisionGroup(this._dronesCollisionGroup);
            // adds group drones will collide with and callback
            body.collides(this._missilesCollisionGroup, this.hitDron, this);
            body.collides(this._turretCollisionGroup);
        }, this);
        // missiles group
        this._missiles = this.add.group();
        this._missiles.physicsBodyType = Phaser.Physics.P2JS;
        this._missiles.enableBody = true;
        // create 10 missiles
        this._missiles.createMultiple(10, "Atlas", "missile");
        this._missiles.forEach(function (aMissile) {
            aMissile.anchor.setTo(0.5, 0.5);
            // physics
            var body = aMissile.body;
            body.setRectangle(aMissile.width, aMissile.height);
            body.setCollisionGroup(this._missilesCollisionGroup);
            body.collides(this._dronesCollisionGroup);
            body.collides(this._bonusesCollisionGroup);
        }, this);
        // bonuses group
        this._bonuses = this.add.group();
        this._bonuses.physicsBodyType = Phaser.Physics.P2JS;
        this._bonuses.enableBody = true;
        this._restart = this.game.add.sprite(this.world.width - 6, 6, "restart");
        this._restart.anchor.setTo(1, 0);
        this._restart.inputEnabled = true;
        this._restart.events.onInputDown.add(this.restart, this);
        this._mainMenu = this.game.add.sprite(this.world.width - 6, this.world.height - 6, "main_menu");
        this._mainMenu.anchor.setTo(1, 1);
        this._mainMenu.inputEnabled = true;
        this._mainMenu.events.onInputDown.add(this.toMainMenu, this);
        this._timeText = this.game.add.text(3, 2, Gameplay.TIME_TEXT + this._time.toString(), this._style);
        this._timeText.anchor.setTo(0, 0);
        var moveDronTimer = this.time.create(false);
        moveDronTimer.loop(500, this.moveDrone, this);
        moveDronTimer.start();
        var createDronTimer = this.time.create(false);
        createDronTimer.loop(800, this.createDron, this);
        createDronTimer.start();
        var gameEndTimer = this.time.create(false);
        gameEndTimer.loop(Phaser.Timer.SECOND, this.timeCounter, this);
        gameEndTimer.start();
    };
    Gameplay.prototype.toMainMenu = function () {
        this.saveBestTime();
        this.game.state.start("MainMenu");
    };
    Gameplay.prototype.timeCounter = function () {
        this._time++;
        this._timeText.setText(Gameplay.TIME_TEXT + this._time.toString());
    };
    Gameplay.prototype.moveDrone = function () {
        var drone = this._drones.getRandom();
        this.game.add.tween(drone.body).to({ x: this._cannon.x, y: this._cannon.y }, 5000, Phaser.Easing.Linear.None, true);
    };
    // -------------------------------------------------------------------------
    Gameplay.prototype.update = function () {
        // shortcut
        var keyboard = this.game.input.keyboard;
        // left and right key
        if (keyboard.isDown(Phaser.Keyboard.LEFT)) {
            // calculate frame independent speed - 45 degrees (PI/4) in 1 second adjusted with cannon speed
            this._cannon.rotation -= this.time.elapsedMS * Gameplay.CANNON_SPEED / 1000 * (Math.PI / 4);
        }
        else if (keyboard.isDown(Phaser.Keyboard.RIGHT)) {
            this._cannon.rotation += this.time.elapsedMS * Gameplay.CANNON_SPEED / 1000 * (Math.PI / 4);
        }
        else if (this._space.justDown) {
            if (this._missilesCount != 0) {
                this._shootSound.play();
                this._missilesCount--;
                this._missilesText.setText(Gameplay.MISSILES_TEXT + this._missilesCount.toString());
                // get firtst missile from pool
                var missile = this._missiles.getFirstExists(false);
                if (missile) {
                    // calculate position of cannon tip. Put distance from cannon base along x axis and rotate it to cannon angle
                    this._cannonTip.setTo(this._cannon.width * 2, 0);
                    this._cannonTip.rotate(0, 0, this._cannon.rotation);
                    missile.reset(this._cannon.x + this._cannonTip.x, this._cannon.y + this._cannonTip.y);
                    missile.body.rotation = this._cannon.rotation;
                    // life of missile in millis
                    missile.lifespan = 1500;
                    // set velocity of missile in direction of cannon barrel
                    missile.body.velocity.x = this._cannonTip.x * Gameplay.MISSILE_SPEED;
                    missile.body.velocity.y = this._cannonTip.y * Gameplay.MISSILE_SPEED;
                }
            }
            else {
                this._no_missilesSound.play();
            }
        }
        // limit cannon rotation to left and right to +/- 45 degrees ... -135 to -45 degrees here
        this._cannon.rotation = Phaser.Math.clamp(this._cannon.rotation, -1.7 * Math.PI / 2, -0.3 * Math.PI / 2);
    };
    Gameplay.prototype.restart = function () {
        this.saveBestTime();
        this.game.state.restart();
    };
    Gameplay.prototype.saveBestTime = function () {
        if (this._bestTime == null) {
            localStorage.setItem("bestTime", this._time.toString());
        }
        else {
            if (this._time > parseInt(this._bestTime)) {
                localStorage.setItem("bestTime", this._time.toString());
            }
        }
    };
    Gameplay.prototype.newGame = function () {
        this.saveBestTime();
        this._game_overSound.play();
        this.game.state.restart();
    };
    // -------------------------------------------------------------------------
    Gameplay.prototype.hitDron = function (aObject1, aObject2) {
        // explode dron and remove missile - kill it, not destroy
        var bodyDron = aObject1.sprite.body;
        bodyDron.clearCollision();
        this._explosionSound.play();
        aObject1.sprite.explode(this._drones);
        aObject2.sprite.kill();
        if (this.game.rnd.between(1, 10) == 1) {
            var myBonus = this._bonuses.create(bodyDron.x, bodyDron.y, "bonusImage");
            var bodyBonus = myBonus.body;
            bodyBonus.setCircle(20);
            bodyBonus.kinematic = true; // does not respond to forces
            bodyBonus.setCollisionGroup(this._bonusesCollisionGroup);
            bodyBonus.collides(this._missilesCollisionGroup, this.pickBonus, this);
            var tween = this.game.add.tween(myBonus.body);
            tween.to({ y: 600 }, (600 - bodyBonus.y) * 12, Phaser.Easing.Linear.None, true);
        }
    };
    Gameplay.prototype.onComp = function (bonus) {
        bonus.kill();
    };
    Gameplay.prototype.pickBonus = function (aObject1, aObject2) {
        aObject1.sprite.kill();
        this._bonuses.remove(aObject1); //(<Phaser.Sprite>aObject1)
        this._missilesCount += 20;
        this._missilesText.setText(Gameplay.MISSILES_TEXT + this._missilesCount.toString());
        aObject2.sprite.kill();
        this._bonus_pickSound.play();
    };
    Gameplay.prototype.createDron = function () {
        var myDron = this._drones.create(this.game.rnd.between(40, 600), this.game.rnd.between(60, 150), "Atlas", "dron1");
        myDron.setUp();
        var body = myDron.body;
        body.setCircle(myDron.width / 2);
        body.kinematic = true; // does not respond to forces
        body.setCollisionGroup(this._dronesCollisionGroup);
        body.collides(this._missilesCollisionGroup, this.hitDron, this);
        body.collides(this._turretCollisionGroup);
    };
    Gameplay.CANNON_SPEED = 2;
    Gameplay.MISSILE_SPEED = 6;
    Gameplay.TIME_TEXT = "Time: ";
    Gameplay.MISSILES_TEXT = "Missiles equ: ";
    Gameplay.BEST_TIME_TEXT = "Best: ";
    return Gameplay;
}(Phaser.State));
var Dron = (function (_super) {
    __extends(Dron, _super);
    function Dron() {
        _super.apply(this, arguments);
    }
    // -------------------------------------------------------------------------
    Dron.prototype.setUp = function () {
        this.anchor.setTo(0.5, 0.5);
        // random position
        this.reset(this.game.rnd.between(40, 600), this.game.rnd.between(60, 150));
        // random movement range
        var range = this.game.rnd.between(60, 120);
        // random duration of complete move
        var duration = this.game.rnd.between(30000, 50000);
        // random parameters for wiggle easing function
        var xPeriod1 = this.game.rnd.between(2, 13);
        var xPeriod2 = this.game.rnd.between(2, 13);
        var yPeriod1 = this.game.rnd.between(2, 13);
        var yPeriod2 = this.game.rnd.between(2, 13);
        // set tweens for horizontal and vertical movement
        var xTween = this.game.add.tween(this.body);
        xTween.to({ x: this.position.x + range }, duration, function (aProgress) {
            return wiggle(aProgress, xPeriod1, xPeriod2);
        }, true, 0, -1);
        var yTween = this.game.add.tween(this.body);
        yTween.to({ y: this.position.y + range }, duration, function (aProgress) {
            return wiggle(aProgress, yPeriod1, yPeriod2);
        }, true, 0, -1);
        // define animations
        this.animations.add("anim", ["dron1", "dron2"], this.game.rnd.between(2, 5), true);
        this.animations.add("explosion", Phaser.Animation.generateFrameNames("Explosion", 1, 6, "", 3));
        // play first animation as default
        this.play("anim");
    };
    // -------------------------------------------------------------------------
    Dron.prototype.explode = function (_drones) {
        // remove movement tweens
        this.game.tweens.removeFrom(this.body);
        // explode dron and kill it on complet
        this.play("explosion", 8, false, true).onComplete.add(function () {
            _drones.remove(this);
        }, this);
    };
    return Dron;
}(Phaser.Sprite));
function wiggle(aProgress, aPeriod1, aPeriod2) {
    var current1 = aProgress * Math.PI * 2 * aPeriod1;
    var current2 = aProgress * Math.PI * 2 * aPeriod2;
    return Math.sin(current1) * Math.cos(current2);
}
window.onload = function () {
    var mygame = new Phaser.Game(640, 400, Phaser.AUTO);
    mygame.state.add("MainMenu", MainMenu);
    mygame.state.add("RulesMenu", RulesMenu);
    mygame.state.add("Gameplay", Gameplay);
    mygame.state.add("Preloader", Preloader);
    mygame.state.add("Boot", Boot);
    mygame.state.start("Boot");
};
var MainMenu = (function (_super) {
    __extends(MainMenu, _super);
    function MainMenu() {
        _super.apply(this, arguments);
    }
    MainMenu.prototype.create = function () {
        this.game.add.sprite(0, 0, "main_menuBackgr");
        this.mouseOver = this.game.add.audio("over_button");
        var startButton = this.game.add.button(this.game.world.centerX, 135, "startButton", this.toGameplay, this, 1, 0);
        startButton.anchor.setTo(0.5, 0.5);
        startButton.events.onInputOver.add(this.onOver, this);
        var rulesButton = this.game.add.button(this.game.world.centerX, 280, "rulesButton", this.toRulesMenu, this, 1, 0);
        rulesButton.anchor.setTo(0.5, 0.5);
        rulesButton.events.onInputOver.add(this.onOver, this);
    };
    MainMenu.prototype.onOver = function () {
        this.mouseOver.play();
    };
    MainMenu.prototype.toRulesMenu = function () {
        this.game.state.start("RulesMenu");
    };
    MainMenu.prototype.toGameplay = function () {
        this.game.state.start("Gameplay");
    };
    return MainMenu;
}(Phaser.State));
var RulesMenu = (function (_super) {
    __extends(RulesMenu, _super);
    function RulesMenu() {
        _super.apply(this, arguments);
    }
    RulesMenu.prototype.create = function () {
        this.mouseOver = this.game.add.audio("over_button");
        this.game.add.sprite(0, 0, "rulesBackgr");
        var backButton = this.game.add.button(this.game.world.centerX, 280, "backButton", this.toMainMenu, this, 1, 0);
        backButton.anchor.setTo(0.5, 0.5);
        backButton.onInputOver.add(this.onOver, this);
    };
    RulesMenu.prototype.onOver = function () {
        this.mouseOver.play();
    };
    RulesMenu.prototype.toMainMenu = function () {
        this.game.state.start("MainMenu");
    };
    return RulesMenu;
}(Phaser.State));
var Preloader = (function (_super) {
    __extends(Preloader, _super);
    function Preloader() {
        _super.apply(this, arguments);
    }
    Preloader.prototype.preload = function () {
        this.load.image("rulesBackgr", "rulesBackgr.jpg");
        this.add.sprite(0, 0, "main_menuBackgr");
        this.game.load.spritesheet('startButton', 'startButton.png', 140, 62);
        this.game.load.spritesheet('rulesButton', 'rulesButton.png', 138, 62);
        this.game.load.spritesheet('backButton', 'backButton.png', 129, 62);
        // background image
        this.game.load.image("BG", "bg.jpg");
        this.game.load.image("restart", "restart.png");
        this.game.load.image("main_menu", "homePicture.png");
        this.game.load.image("bonusImage", "boxWithAmmo.png");
        // load sprite images in atlas
        this.game.load.atlas("Atlas", "atlas.png", "atlas.json");
        this.game.load.audio("shoot", "assets/audio/shoot.mp3");
        this.game.load.audio("explosion", "assets/audio/explosion.mp3");
        this.game.load.audio("game_over", "assets/audio/game_over.mp3");
        this.game.load.audio("new_game", "assets/audio/new_game.mp3");
        this.game.load.audio("no_missiles", "assets/audio/no_missiles.mp3");
        this.game.load.audio("over_button", "assets/audio/over_button.mp3");
        this.game.load.audio("pick_bonus", "assets/audio/pick_bonus.mp3");
    };
    Preloader.prototype.create = function () {
        this.game.state.start("MainMenu");
    };
    return Preloader;
}(Phaser.State));
var Boot = (function (_super) {
    __extends(Boot, _super);
    function Boot() {
        _super.apply(this, arguments);
    }
    Boot.prototype.preload = function () {
        this.load.image('main_menuBackgr', 'main_menuBackgr.jpg');
    };
    Boot.prototype.create = function () {
        this.game.state.start("Preloader");
    };
    return Boot;
}(Phaser.State));
