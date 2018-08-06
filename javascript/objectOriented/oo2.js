function A() {
    var x = 'private property x in A';
    this.a = 'public property A.a assigned via constructor';

    // publiczna metoda majaca dostep do prywatnej zmiennej dzieki domknieciu
    this.getX = function () {
        return x
    }
}

A.prototype.b = 'public property A.b assigned via prototype';

var o = new A();
console.log(o.a);
console.log(o.b);
console.log(o.getX());
