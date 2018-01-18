var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var DronShooter;
(function (DronShooter) {
    var MainMenu = (function (_super) {
        __extends(MainMenu, _super);
        function MainMenu() {
            _super.apply(this, arguments);
        }
        MainMenu.prototype.preload = function () {
            this.load.image("2017", "2017.png");
        };
        MainMenu.prototype.create = function () {
            this.game.add.sprite(0, 0, "2017");
        };
        return MainMenu;
    }(Phaser.State));
    DronShooter.MainMenu = MainMenu;
})(DronShooter || (DronShooter = {}));
