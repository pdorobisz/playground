// pakiety mozna zaimplementowac przy pomocy obiektow

var package = package || {}; // jezeli istnieje juz 'package' to uzywamy go, jezeli nie to tworzymy pusty obiekt

package.subpackage2 = {};
package.subpackage2._init = function () {
    this.B = function () {
    };

    this.B.prototype.method = function () {
        return "method in B";
    };
};

package.subpackage2._init();