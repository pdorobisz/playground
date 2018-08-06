// pakiety mozna zaimplementowac przy pomocy obiektow

var package = package || {}; // jezeli istnieje juz 'package' to uzywamy go, jezeli nie to tworzymy pusty obiekt

package.subpackage1 = {};
package.subpackage1._init = function () {
    this.A = function () {
    };

    this.A.prototype.method = function () {
        return "method in A";
    };
};

package.subpackage1._init();