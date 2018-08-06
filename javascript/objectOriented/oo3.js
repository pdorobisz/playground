function A(name, age) {
    var name = name;

    this.age = age;

    this.getName = function () {
        return name;
    }

    this.hello = function () {
        return 'My name is ' + name + ', age ' + age;
    }
}

A.prototype.getAge = function () {
    return this.age;
};

function B() {
    A.apply(this, arguments); // wywolanie konstruktora klasy bazowej
    var that = this;

    this.hello2 = function () {
        return that.hello() + '!!!'; // dzieki 'that' mozna sie odwolac do obiektu B, ktorego prototyp (obiekt A) posiada metode 'hello'
    }
};

B.prototype = new A(); // dziedziczenie z A

var a = new A("John", 21);
console.log(a.getName() + ', ' + a.getAge());
console.log(a.hello());

console.log('---');

var b = new B("Mark", 34);
console.log(b.getName() + ', ' + b.getAge());
console.log(b.hello());
console.log(b.hello2());