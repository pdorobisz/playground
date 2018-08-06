// http://zeekat.nl/articles/constructors-considered-mildly-confusing.html

function Foo() {
}

// stworzona funkcja automatycznie otrzymuje property 'prototype', do ktorego zostaje przypisany nowy obiekt:
console.log(Foo.prototype);
// obiekt Foo.prototype posiada property 'constructor' wskazujace na obiekt funkcji:
console.log(Foo.prototype.constructor);
console.log(Foo.prototype.constructor === Foo); // true ('constructor' wskazuje na funkcje)
console.log(Foo.prototype === Foo); // false (Foo i Foo.prototype to dwa rozne obiekty)

console.log(Object.getPrototypeOf(Foo)); // prototypem Foo jest Function.prototype, ktorego prototypem jest Object.prototype
console.log(Object.getPrototypeOf(Foo.prototype)); // prototypem Foo.prototype jest Object.prototype


console.log('-------')

var o = {} // prototypem takiego obiektu jest Object.prototype
console.log(o.prototype); // proba odczytu property 'prototype' (nie mozna odczytac prototypu obiektu, standard na to nie pozwala)
console.log(o.__proto__); // niestandardowa zmienna umozliwiajaca odczytania prototypu (jest 'deprecated')
console.log(Object.getPrototypeOf(o)); // preferowany sposob odczytywania prototypu
console.log(Object.getPrototypeOf(o) === Object); // false (Object i Object.prototype to dwa rozne obiekty)
console.log(Object.getPrototypeOf(o) === Object.prototype); // true (prototypem obiektu jest obiekt Object.prototype)

console.log(Object.getPrototypeOf(Object.prototype)); // Object.prototype nie posiada juz prototypu
