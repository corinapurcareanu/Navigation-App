# Navigation-App
Proiectul implementeaza gasirea celui mai scurt drum dintre doua puncte folosind
alogritmul Dijkstra.

Citirea din fisierul "map.in" se face in clasa Main cu ajutorul metodei Citire 
si scrie output-ul in fisierul "map.out". Metoda Citire citeste din fisier linie
cu linie. Pe prima linie se afla numarul de muchii si numarul de strazi pe care 
lestocheaza in variabile de tip int, nr_muchii, respectiv nr_noduri. Pentru 
urmatoarele nr_muchii linii, citeste strazile, si anume punctul de pornire, punctul
destinatie si costul de pe strada respectiva si le stocheaza intr-un graf reprezentat
prin lista de adiacenta. Pentru urmatoarele linii, daca primul cuvant nu este "drive",
atunci este un tip de ambuteiaj, pe care il adauga in lista specifica tipului de
ambuteiaj, iar daca primul cuvant este "drive" se executa comanda drive, tinand cont
de tipul vehiculului condus si se gaseste cel mai scurt drum intre cele doua puncte 
pe care s-ar putea deplasa vehiculul. In metoda statica main se apeleaza functia de
citire si scrie in fisierul "map.out" output-ul rezultat in urma tuturor apelurilor
metodei drive.

Clasa Harta contine informatii despre numarul maxim de noduri, graful si listele
de ambuteiaje. Contine un constructor cu parametrul "max" ce reprezinta numarul 
maxim de noduri de pe harta si initializeaza atat vectorul pentru strazi cat si
vectorul pentru ambuteiaje cu null. Metoda addStreet adauga strazile in graful 
reprezentat prin lista de adiacenta. Metoda addRestriction adauga restrictiile
in vectorul pentru restrictii. Pe pozitia 0 este lista pentru restrictiile de tip
"accident", pe pozitia 1 este lista celor de tip "trafic" si pe pozitia 2 este
lista celor de tip "blocaj". Metoda drive ce executa comanda drive si returneaza intr-un 
string punctele prin care trece vehiculul, pornind de la nodul returnat de metoda
Dijkstra, caruia ii parcurge stramosii pana la final si ii adauga in string la inceput
precum si costul total al drumului. Daca nu exista drum intre acele puncte, costul va
fi null si in string se vor gasi cele 2 puncte urmate de "null". Gasirea celui mai
scurt drum se face folosind metoda Dijkstra. Ea primeste ca parametru un vehicul 
initializat in functie de tipul de vehicul specificat, punctul de pornire, punctul
destinatie, un vector de costuri in care toate valorile sunt infinit initial, si
un vector vizitat in care initial este valoarea 0 pe toate pozitiile, iar cand un
punct a fost vizitat, se macheaza cu valoarea 1. Metoda Dijkstra foloseste o coada 
de prioritati. Initial, coada de prioritati contine nodul format din punctul de
pornire si costul 0, apoi sunt adaugate si celelalte cu costul infinit. Cat timp
exista noduri nevizitate, daca coada de prioritati nu este goala, este eliminat
primul element din coada. Daca elementul eliminat contine punctul destinatie,
atunci se opreste executia. Altfel, nodul se marcheaza ca fiind vizitat si se
parcurg strazile care au ca punct de pornire punctul nodului curent. Se intra 
pe strada respectiva doar daca vehiculul nu depasteste limita de gabarit. Costul
nou este format din costul suplimentar cauzat de ambuteiaje daca exista, costul
nodului curent si costul de la punctul nodului curent la punctul destinatie. Daca
este mai mic decat costul care era deja in vectorul de costuri pe pozitia specifica
punctului destinatie, i se actualizeaza costul si se adauga nodul format din punctul
destinatie si noul cost, ce nodul curent ca parinte, in coada de prioritate. Costul
suplimentar este generat de metoda costSuplimentar care primeste ca parametrii
punctul de pornire si punctul destinatie si se verifica in listele celor 3 tipuri
de ambuteiaje daca exista ambuteiaje pe strada respectiva si se adauga in variabila
cost de tip int, care are initial valoarea 0 si pe care o returneaza metoda la final.
Metoda tipVehicul initializeaza vehiculul in functie de tipul speficiat.

Clasa PriorityQueue implementeaza o coada de prioritate. Are ca parametrii nodurile
inceput si sfarsit pentru a marca inceputul si sfarsitul cozii. Constructorul
primeste ca parametru un nod cu care este initializata coada. Metoda Elimina
elimina primul nod din coada si il returneaza. Metoda AdaugaDupaPrioritate primeste
ca parametru un nod. Daca coada e goala, se adauga pur si simplu in coada. Altfel,
daca are costul cel mai mic, este adaugat primul in coada. Daca nu, se parcurg 
elementele din coada atata timp cat costul nodului curent este mai mare sau egal
decat costul lor, apoi se insereaza pe pozitia respectiva in coada. Daca exista
initial un nod cu acelasi punct, este eliminat.

Clasa Nod contine informatii despre punctul destinatie, costul dintre punctul
de pornire si cel destinatie, nodul urmator si nodul parinte al nodului curent. 
Are un constructor ce initializeaza nodul urmator si nodul curent cu null si 
seteaza datele despre punctul destinatie si cost. Mai are un constructor care
seteaza datele despre punct si cost, dar si nodul parinte, iar campul urm ramane
tot null.

Clasa Strada contine informatii despre punctul destinatie, costul dintre
punctul de pornire si cel destinatie, limita de gabarit de pe strada si strada 
urmatoare. Constructorul seteaza punctul destinatie, costul dintre punctul de 
pornire si cel destinatie, limita de gabarit de pe strada respectiva si seteaza
campul urm ca fiind null.

Clasa abstracta Ambuteiaj ce contine informatii despre punctul de pornire si punctul 
destinatie al strazii unde are loc ambuteiajul, si costul suplimentar cauzat de
ambuteiajul respectiv. Constructorul seteaza punctul de pornire, punctul destinatie
si costul suplimentar cauzat de ambuteiaj. Casele Accident, Trafic si Blocaj mostenesc
clasa Ambuteiaj si au in plus un camp urm, care initializat cu null initial. Am
ales sa fac clasa Ambuteiaj de tip abstract deocarece nu este nevoie sa instantiez
direct un obiect de acest tip, intrucat apar doar cele 3 tipuri de ambuteiaje deja
cunoscute. Acestea mostenesc clasa Ambuteiaj deoarece se folosesc de metodele definite
in aceasta, in schimb constructorii lor au in plus initializarea campului urm cu null. 

Clasa abstracta Vehicul ce contine informatii despre tipul vehiculului condus,
gabaritul si costul acestuia. Constructorul seteaza tipul vehiculului, gabaritul
si costul acestuia. Clasele Bicicleta, Autoturism, Motocicleta si Camion mostenesc
clasa Vehicul si initializeaza tipul, gabaritul si costul cu valori deja cunoscute.
Am ales sa fac clasa Vehicul de tip abstract deocarece nu este nevoie sa instantiez
direct un obiect de acest tip, intrucat pot fi conduse doar cele 4 tipuri de vehicule
deja cunoscute. Acestea mostenesc clasa Vehicul deoarece se folosesc de metodele 
definite in aceasta.
