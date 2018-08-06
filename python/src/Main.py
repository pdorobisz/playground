'''
Created on 2010-03-06

@author: Piotrek
'''
#import Person
from Person import Person
from Student import Student


p = Person("Ala", "Ma-Kota")
p.publicMethod("a")
print "p.instanceVariable=%s" % p.instanceVariable
print Person.publicStaticField
print Person.publicStaticField2 #@UndefinedVariable
Person.publicStaticMethod()
Person.publicStaticMethod2()

print ""

s = Student("Jan", "Kowalski")
print s.name
print s.instanceVariable

#porownanie obiektow (czy referencja do tego samego obiektu)
str1 = "ala ma kota";
str2 = str1
print (str1 is str2)

str3 = "ala ma kota"
#porownanie napisow)
print (str1 == str3)


#bool ? a : b
def bool(b):
    true = "TAK"
    false = "NIE"
    #and - zwraca pierwszy argument odpowiadajacy false lub ostatni gdy zaden z wczesniejszych nie byl false
    #or - zwraca pierszy argument odpowiadajacy true lub ostatni gdy zaden z wczesniejszych nie byl true
    #return b and [true] or [false] nie zadziala gdy false bedzie np. pustym stringiem
    return (b and [true] or [false])[0]

print bool(True)
print bool(False)

#sprawdzenie czy modul zostal bezposrednio uruchomiony
if __name__ == '__main__':
    print "ala ma kota"
    pass
