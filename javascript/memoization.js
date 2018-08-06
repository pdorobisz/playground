// technika, w ktore zapamietujemy wyniki dotychczasowych wywolan funkcji

// opakowuje funkcje w funkcjonalnosc zapamietywania i zwraca funkcje jednoargumentowa
var memoizer = function (memo, formula) {
    var recur = function (n) {
        var result = memo[n];
        if (typeof result !== 'number') {
            result = formula(recur, n); // rekurencyjne wywolanie recur, moglibysmy tez zdefiniowac funkcje tak: function recur(memo, formula){...
            memo[n] = result;
        }
        return result;
    };
    return recur;
};

// zdefiniowanie funkcji fibonacci, nigdy nie podajemy definicji funkcji 'recur'. Definiujemy jedynie jak sie poslugiwac
// ta funkcja w sposob rekurencyjny. Nie trzeba tez sprawdzac, czy ma nastapic kolejne wywolanie rekurencyjne czy tez
// nalezy zwrocic jakas wartosc gdyz poczatkowe wartosci sa juz podane w tablicy memo.
var fibonacci = memoizer([0, 1], function (recur, n) {
    return recur(n - 1) + recur(n - 2);
});

// powstaje jednoargumentowa funkcja:
console.log(fibonacci(6))