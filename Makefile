JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Accident.java \
	Ambuteiaj.java \
	Autoturism.java \
	Bicicleta.java \
	Blocaj.java \
	Camion.java \
	Harta.java \
	Main.java \
	Motocicleta.java \
	Nod.java \
	PriorityQueue.java \
	Strada.java \
	Trafic.java \
	Vehicul.java \

default: classes

classes: $(CLASSES:.java=.class)

build:
	javac Main.java

run: Main.class
	java Main

clean:
	$(RM) *.class