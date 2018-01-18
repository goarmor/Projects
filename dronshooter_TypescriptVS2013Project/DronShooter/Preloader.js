var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var Castlevania;
(function (Castlevania) {
    var Preloader = (function (_super) {
        __extends(Preloader, _super);
        function Preloader() {
            _super.apply(this, arguments);
        }
        Preloader.prototype.preload = function () {
            this.load.image("backgr", "2017.png");
        };
        Preloader.prototype.create = function () {
            this.game.state.start('MainMenu', true, false);
        };
        return Preloader;
    }(Phaser.State));
    Castlevania.Preloader = Preloader;
})(Castlevania || (Castlevania = {}));
//# sourceMappingURL=Preloader.js.map