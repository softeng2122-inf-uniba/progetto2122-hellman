# **Relazione tecnica**

## Indice

---
[1. Introduzione del progetto](#introduzione-del-progetto)

[2. Modello di dominio](#modello-di-dominio)

[3. Requisiti specifici](#requisiti-specifici)

- [3.1 Requisiti funzionali](#requisiti-funzionali)
- [3.2 Requisiti non funzionali](#requisiti-non-funzionali)

[4. OO Design](#oo-design)
- [4.1 Diagrammi delle classi](#diagrammi-delle-classi)
- [4.2 Diagrammi di sequenza](#diagrammi-di-sequenza)

---

## **Introduzione del progetto**

---


>Il seguente progetto si basa sulla realizzazione del gioco Wordle a riga di comando.
Il gioco si basa sul cercare di indovinare una parola composta da 5 lettere effettuando un massimo di 6 tentativi.
La parola da indovinare viene decisa dal paroliere, prima che il gioco inizi.

>L’utente, dunque, quando inserirà una parola, avrà un riscontro positivo o negativo in base alla correttezza della parola. Questo riscontro consiste nel colorare di verde la singola lettera se essa è presente nella posizione giusta della parola, di giallo se la singola lettera è presente nella parola ma inserita nella posizione errata oppure non colorarla se è errata, cioè se non è presente nella parola da indovinare.

---

## **Modello di dominio**

---
Di seguito è presentato il modello di dominio del progetto.

![dominio](./img/Dominio.png)