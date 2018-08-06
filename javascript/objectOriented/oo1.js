function Foo(){}

var o1 = new Foo();
console.log(typeof o1);
console.log(o1);

console.log('----');

function Bar() {
    return o1;
}

var o2 = new Bar(); // poniewaz Bar zwraca obiekt nie bedacy nullem to zwracany obiekt zostanie przypisany
console.log(typeof o2);
console.log(o2);
console.log(o1 === o2);

console.log('----');

function Bar2() {
    return 1;
}

var o3 = new Bar2(); // zostanie zwrocony stworzony obiekt
console.log(typeof o3);
console.log(o3);
console.log(o1 === o3);