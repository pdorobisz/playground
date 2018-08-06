var i
var n = 3
var a = []
var a2 = []


// bledne tworzenie funkcji
for (i = 0; i < n; i++) {
    var x = i
    a[i] = function () {
        console.log('x=' + x) // kazda funkcja bedzie odwolywac sie do tego samego "x" (domkniecie), a nie do jego wartosci
    }
}

for (i = 0; i < n; i++) {
    a[i]() // kazde wywolanie wypisze to samo
}


// poprawne rozwiazanie
var helper = function (x) {
    // za kazdym razem tworzymy nowa funkcje, ktora jest domknieta inna wartoscia (aktualnym argumentem)
    return function () {
        console.log('x2=' + x) // "x" jest argumentem zewnetrznej funkcji
    }
}
for (i = 0; i < n; i++) {
    var x = i
    a2[i] = helper(x)
}

for (i = 0; i < n; i++) {
    a2[i]() // kazde wywolanie wypisze co innego
}
