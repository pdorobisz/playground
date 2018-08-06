function A() {
};
function B() {
};
function C() {
};
function D() {
};

B.prototype = new C();
A.prototype = new B();

var o = new A();
// Sprawdzenie czy dany obiekt (np. B.prototype) znajduje sie w lancuchu prototypow obiektu o.
console.log(B.prototype.isPrototypeOf(o));
console.log(C.prototype.isPrototypeOf(o));
console.log(D.prototype.isPrototypeOf(o));