var Obj2 = function Obj2() {
}
Obj2.prototype.obj2Property = 'object 2 property'

obj = new Obj2()
obj.objProperty = 'object property'

console.log(obj.objProperty) // property przypisane bezposrednio do obiektu
console.log(obj.obj2Property) // property nie przypisane do obiektu, ale do jego prototypu (gdy nie mozna znalezc w obiekcie to przeszukiwany jest prototyp)
console.log(obj['objProperty']) // inny sposob odczytania property

console.log('-------')
// iterowanie po properties (uwzglednia takze te z prototypu)
for (propertyName in obj) {
    console.log(propertyName + ': ' + obj[propertyName] + ' (own property: ' + obj.hasOwnProperty(propertyName) + ')')
}

console.log('-------')
obj.obj2Property = 'updated object 2 property' // tworzymy property w obj, a nie uaktualniamy w prototypie
console.log(obj.obj2Property)
console.log(Obj2.prototype.obj2Property)


console.log('-------')
delete obj.objProperty // usuniete property
console.log(obj.objProperty) // gdy property nie istnieje to zwracane jest undefined