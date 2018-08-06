someprop = 'this is someprop in global object'

console.log(this.someprop) // odnosi sie do 'someprop' w globalnym obiekcie (this odnosi sie do globalnego obiektu)


//// Typy wywolywania metod (od nich zalezy to do czego odnosi sie 'this')

// 1. Wywolanie jako metoda obiektu
var myObject = {
    someprop: 'this is local someprop',

    method: function () {
        return this.someprop // 'this' odnosi sie do obiektu
    }
}

console.log('1: ' + myObject.method()) // wypisze 'this is local someprop'


// 2. Bezposrednie wywolanie funkcji ('this' odnosi sie do globalnego obiektu)
myObject.method2 = function () {

    var that = this;

    var localMethod = function () {
        return this.someprop + ' | ' + that.someprop;
    };
    console.log('inside method2: ' + this.someprop) // tutaj odnosi sie do obiektu

    // Bezposrednie wywowalnie - wewnatrz localMethod 'this' odnosi sie do globalnego obiektu,
    // ale dzieki 'that' (domkniecie) mamy dostep do obiektu.
    return localMethod()
}
console.log('2: ' + myObject.method2()) // wypisze 'this is someprop in global object'


// 3. Wywolanie jako konstruktor - 'this' odnosi sie do stworzonego obiektu
MyClass = function (value) {
    this.variable = 'MyClass: ' + value
}
MyClass('call without new') // 'this' odnosi sie do globalnego obiektu i jemu zostanie przypisana zmienna 'variable'
var myClass = new MyClass('call with new') // 'this' odnosie sie do stworzonego obiektu
console.log('3: variable: ' + this.variable)
console.log('3: variable: ' + myClass.variable)


// 4. Wywolanie przy pomocy 'apply' (pierwszy argument zostaje przypisany do 'this')
var readSomeProp = function () {
    return this.someprop // oodczytuje 'someprop'
}
console.log('4:' + readSomeProp()) // oodczyta 'someprop' z globalnego obiektu
console.log('4:' + readSomeProp.apply(myObject)) // odczyta 'someprop' z obiektu myObject


// przypisanie do this
console.log('-------');
var o = {
    name: 'John'
}

function hello() {
    console.log('hello ' + this.name);
    console.log(arguments);
}

// tworzy nowa funkcje, w ktorej 'o' zostaje przypisane do this, oraz pierwsze argumenty to 1 i 2.
var f = hello.bind(o, 1, 2);
f(3, 4); // lista argumentow przekazana do hello to [1, 2, 3, 4]