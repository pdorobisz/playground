'''
Created on 2010-03-06

@author: Piotrek
'''
from Person import Person

#mozliwe jest wielokrotne dziedziczenie
class Student(Person):

    def __init__(self, name, lastname):
        print "__init__ z klasy Student"
        #nalezy "recznie" wywolac metode __init__ nadklasy
        Person.__init__(self, name, lastname)
        pass
    
    def publicMethod(self, s):
        print "publiczna metoda instancji z Student: %s, %s" % (s, self.instanceVariable)
        