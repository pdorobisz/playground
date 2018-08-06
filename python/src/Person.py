'''
Created on 2010-03-06

@author: Piotrek
'''

class Callable:
    def __init__(self, anycallable):
        self.__call__ = anycallable

class Person:
    
    #prywatne pola i metody zaczynaja sie (ale nie koncza sie) od: '__' (wszystkie inne sa publiczne)
    #zakresy widocznosci: public, private (nie ma protected)
    #zmienne statyczne (class attribute) sa definiowane zaraz po definicji klasy
    #zmienne instancji (data attribute) sa definiowane w metodzie __init__
    publicStaticField = "publiczna zmienna klasowa (statyczna)"
    __privateStaticField = "prywatna zmienna statyczna" 
    
    #nie jest to konstruktor - jest to metody wywolywana juz po utworzeniu obiektu (nie tworzy go)
    #wiec jako pierwszy argument otrzymuje referencje do obiektu
    def __init__(self, name, lastname):
        print "__init__ z klasy Person"
        self.instanceVariable = "zmienna instancji"
        self.name = name
        self.lastname = lastname
        Person.publicStaticField2 = "publiczna zmienna klasowa 2"
        pass
    
    def __privateMethod(self, s):
        print "prywatna metoda: %s" % s

    def publicMethod(self, s):
        print "publiczna metoda instancji z Person: %s, %s" % (s, self.instanceVariable)
        self.__privateMethod(s)
        
    def __privateStaticMethod(): #@NoSelf
        print "prywatna metoda statyczna z Person"
    __privateStaticMethod = Callable(__privateStaticMethod)

    def publicStaticMethod(): #@NoSelf
        print "publiczna metoda statyczna z Person"
        Person.__privateStaticMethod()
    publicStaticMethod = Callable(publicStaticMethod)

    @staticmethod
    def publicStaticMethod2():
        print "publiczna metoda statyczna2 z Person"
