foo(); // funkcja nie widzi 'a' (wyrazenie nie zostalo jeszcze wykonane)
a = 123;
foo(); // funkcja widzi 'a'

// deklaracja funkcji (function declaration) - zdefiniowanie funkcji bez jej przypisywania.
// Taka funkcja moze byc wywolana wczesniej niz jej deklaracja (przed wykonaniem skryptu parser wykonuje wszystkie deklaracje).
// Jest to tzw. hoisting - przeniesienie wszystkich deklaracji na sama gore zakresu w ktorym jest zdefiniowana
// (dotyczy takze samych zmiennych 'var').
function foo() {
    try {
        console.log('foo: ' + a)
    } catch (e) {
        console.log('foo - exception: ' + e)
    }
}

foo2()
function foo2() {
    foo2_1(); // j.w. - foo2_1 bedzie tutaj widoczna
    var foo2x = 'foo2x';

    function foo2_1() {
        console.log('function foo 2_1');
    }
}

//////////////////

// Wyrazenie funkcyjne (function expression) - deklaracja jest czescia wiekszego wyrazenia
var f = function (name) {
    console.log('hello ' + name)
}
f('world');

// wykonanie function expression
(function () {
    console.log('executing function expression')
})();
