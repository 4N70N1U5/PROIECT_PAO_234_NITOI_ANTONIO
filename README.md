# Proiect PAO - Nitoi Antonio, grupa 234

Acest program permite crearea, afisarea, modificarea si stergerea unor agentii imobiliare, fiecare dintre acestea gestionand locuinte de diverse tipuri. Agentiile imobiliare sunt implementate cu ajutorul clasei `AgentieImobiliara`.

In cadrul agentiilor, sunt posibile adaugarea, afisarea, modificarea si stergerea locuintelor, cat si calculul si afisarea pretului de cumparare si chiriei, in functie de formule specifice fiecarui tip de locuinta ce iau in calcul lucruri precum suprafata utila si, unde este cazul, suprafata curtii/gradinii si dimensiunile piscinei.

Toate aceste optiuni sunt accesibile cu ajutorul unui meniu interactiv, care verifica de asemenea inputul primit pentru evitarea erorilor.

Prin urmare, o lista completa a optiunilor este:
1. Adaugarea unei agentii imobiliare
2. Afisarea agentiilor imobiliare
3. Modificarea unei agentii imobiliare
4. Stergerea unei agentii imobiliare
5. Adaugarea unei locuinte
6. Afisarea locuintelor
7. Modificarea unei locuinte
8. Stergerea unei locuinte
9. Calculul si afisarea preturilor de cumparare
10. Calculul si afisarea chiriilor

Programul permite crearea a mai multor tipuri de locuinte, precum apartamente, apartamente duplex, apartamente cu gradina, case, case cu curte sau case cu curte si piscina. Acest lucru este realizat folosind mai multe clase. Clasa de baza a tuturor acestor tipuri de locuinte este clasa `Locuinta`, care contine datele comune tuturor tipurilor de locuinte, care este si abstracta. Din aceasta clasa sunt apoi mostenite clasele `Apartament` si `Casa`. Din clasa `Apartament` mai sunt mostenite inca doua clase, `ApartamentDuplex` si `ApartamentCuGradina`, iar din clasa `Casa` este mostenita clasa `CasaCuCurte`, din care mai apoi este mostenita clasa `CasaCuPiscina`, fiecare dintre acestea extinzand modelul cu noi atribute specifice:

![Diagrama clase](https://user-images.githubusercontent.com/64855012/231111775-bf150aa6-824c-48e5-95c9-6629df9d80a3.png)

Astfel, o lista completa a claselor din cadrul proiectului si a atributelor acestora este:
1. `AgentieImobiliara` - numele agentiei si o lista a locuintelor
2. `Locuinta` - numele clientului, discountul aplicat optional, materialul* din care este realizata structura de rezistenta, suprafata utila si numarul de camere
3. `Apartament` - toate atributele locuintei + etaj
4. `ApartamentDuplex` - toate atributele apartamentului + numarul de etaje
5. `ApartamentCuGradina` - toate atributele apartamentului (etajul este obligatoriu 0) + suprafata gradinii
6. `Casa` - toate atributele locuintei + numarul de etaje
7. `CasaCuCurte` - toate atributele casei + suprafata curtii
8. `CasaCuPiscina` - toate atributele casei cu curte + dimensiunile (lungime si latime) piscinei

*Materialele disponibile sunt definite intr-un enum.
