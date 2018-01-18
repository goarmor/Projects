declare class Gameplay extends Phaser.State {
    private static CANNON_SPEED;
    private static MISSILE_SPEED;
    private static TIME_TEXT;
    private static MISSILES_TEXT;
    private static BEST_TIME_TEXT;
    private _cannon;
    private _restart;
    private _mainMenu;
    private _cannonTip;
    private _space;
    private _drones;
    private _bonuses;
    private _bonusesCollisionGroup;
    private _dronesCollisionGroup;
    private _turret;
    private _turretCollisionGroup;
    private _missiles;
    private _missilesCollisionGroup;
    private _time;
    private _missilesCount;
    private _style;
    private _styleMissilesText;
    private _styleBestScore;
    private _scoreText;
    private _timeText;
    private _bestTimeText;
    private _missilesText;
    private _shootSound;
    private _explosionSound;
    private _game_overSound;
    private _new_gameSound;
    private _no_missilesSound;
    private _bonus_pickSound;
    private _bestTime;
    create(): void;
    private toMainMenu();
    private timeCounter();
    private moveDrone();
    update(): void;
    private restart();
    private saveBestTime();
    private newGame();
    private hitDron(aObject1, aObject2);
    private onComp(bonus);
    private pickBonus(aObject1, aObject2);
    private createDron();
}
declare class Dron extends Phaser.Sprite {
    private explodeAnim;
    setUp(): void;
    explode(_drones: Phaser.Group): void;
}
declare function wiggle(aProgress: number, aPeriod1: number, aPeriod2: number): number;
declare class MainMenu extends Phaser.State {
    private mouseOver;
    create(): void;
    private onOver();
    private toRulesMenu();
    private toGameplay();
}
declare class RulesMenu extends Phaser.State {
    private mouseOver;
    create(): void;
    private onOver();
    private toMainMenu();
}
declare class Preloader extends Phaser.State {
    preload(): void;
    create(): void;
}
declare class Boot extends Phaser.State {
    preload(): void;
    create(): void;
}
