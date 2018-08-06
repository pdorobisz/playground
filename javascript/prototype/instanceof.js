// http://zeekat.nl/articles/constructors-considered-mildly-confusing.html
// instanceof sprawdza czy obiekt wskazywany przez pole 'property' podanej funkcji znajduje sie gdzies w lancuchu
// prototypow obiektu


function C() {
}
function D() {
}

var o = new C();
// o -(prototyp)-> C.prototype -(prototyp)-> Object.prototype

// sprawdzenie czy C.prototype znajduje sie w lancuchu prototypow obiektu o
console.log(o instanceof C); // true, Object.getPrototypeOf(o) === C.prototype
console.log(o instanceof D); // false, D.prototype nie znajduje sie w lancuchu prototypow o
console.log(o instanceof Object); // true, Object.prototype jest prototypem C.prototype (prototyp o)
console.log(C.prototype instanceof Object) // true

console.log('---------')

C.prototype = {};
var o2 = new C();
// o2 -(prototyp)-> {} -(prototyp)-> Object.prototype

console.log(o2 instanceof C); // true, prototypem o2 jest C.prototype
console.log(o instanceof C); // false, obecne C.prototype ({}) nie znajduje sie w lancuchu prototypow o.
// Prototypem o dalej jest obiekt poprzednio wskazywany przez C.prototype

console.log('---------')

D.prototype = new C();
var o3 = new D();
console.log(o3 instanceof D); // true
console.log(o3 instanceof C); // true, prototypem o3 jest new C(), a jego prototypem jest C.prototype