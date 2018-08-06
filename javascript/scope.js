(function f() {
    function print() {
        console.log("a=" + a + ", b=" + b)
    }

    var a = 1
    print();
    {
        var b = 2
    }
    print() // b dalej widoczne (w Javascripcie scope jest na funkcji, nie na blokach)
}) ()