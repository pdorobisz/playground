// http://zeekat.nl/articles/constructors-considered-mildly-confusing.html

function Foo1() {
}

Foo1.prototype.x = 'Foo1 x';
Foo1.prototype.y = 'Foo1 y';

// Foo1.prototype NIE JEST prototypem obiektu funkcyjnego Foo1

// podczas tworzenia nowego obiektu jego prototypem staje sie obiekt wskazywany przez Foo1.prototype
var o = new Foo1();
console.log(Object.getPrototypeOf(o) === Foo1.prototype);
console.log(o.x);
console.log(o.y);
console.log(o.constructor); // Foo1.prototype posiada takze property 'constructor'...
console.log(o.constructor === Foo1); // ... wskazujace na obiekt Foo1

console.log('---------')


function Foo2() {
}

Foo2.prototype.x = 'Foo2 x';
Foo2.prototype.z = 'Foo2 z';

Foo1.prototype = new Foo2();
// po zmianie obiektu wskazywanego przez Foo1.prototype prototypy dotychczas stworzonych obiektow przy pomocy
// new Foo1 nie ulegaja zmianie i dalej odnosza sie do poprzedniego prototypu:
console.log(Object.getPrototypeOf(o));
console.log(Object.getPrototypeOf(o) === Foo1.prototype); // false (Foo1.prototype wskazuje juz na inny obiekt)

var o2 = new Foo1();
console.log(Object.getPrototypeOf(o2));
console.log(o2.constructor); // Foo2, bo odczytywane jest pole 'constructor' z prototypu obiektu (Foo2.prototype)
console.log(o2.constructor === Foo2); // true (Foo2.prototype.constructor odnosi sie do Foo2)
console.log(o2.x);
console.log(o2.y); // undefined (Foo1.prototype nie zawiera juz pola y)
console.log(o2.z);

console.log('---------')

Foo1.prototype = {}; // prototypem {} jest Object.prototype, ktory posiada property 'constructor' wskazujace na Object
var o3 = new Foo1();
console.log(o3.constructor === Object); // true