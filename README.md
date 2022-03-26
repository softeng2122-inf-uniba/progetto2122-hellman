[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7342071&assignment_repo_type=AssignmentRepo)
# Wordle [![Coverage Status](https://coveralls.io/repos/github/softeng2122-inf-uniba/progetto2122-hellman/badge.svg?branch=master&t=8q3IR1)](https://coveralls.io/github/softeng2122-inf-uniba/progetto2122-hellman?branch=master)


La struttura della repository si presenta nel seguente modo:
```
|-- .github
|    |-- workflows
|    |      |-- ingsw2122.yml
|-- build
|    |-- reports
|    |      |-- checkstyle
|    |      |-- spotbugs
|    |      |-- jacoco/tests
|    |      |-- tests/test
|–– config
|    |–– checkstyle
|–– docs
|    |–– Assegnazione progetto.md
|    |–– Guida per lo studente.md
|    |–– img 
|    |–– javadoc 
|    |–– Report.md
|–– drawings
|–– gradle
|–– lib
|–– res
|–– src
|    |–– main
|    |–– test
|–– .gitignore
|–– build.gradle
|–– README.md
|–– gradlew
|–– gradle.bat
|–– settings.gradle
```

Nel seguito si dettagliano i ruoli dei diversi componenti:

- `.github/workflows/ingsw2122.yml`: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di GitHub Actions;
- `build/`: ospita la sottocartella `reports/`, contenente gli output dei tool automatici di test e controllo di qualità;
- `config/`: ospita i file di configurazione. L’unica configurazione di base richiesta è quella per il tool checkstyle;
- `docs/`: ospita la documentazione di progetto, incluse le figure (nella sottocartella `img/`). 
<br>La sottocartella `javadoc/` conterrà la documentazione generata automaticamente per il codice Java. 
<br>Il file `Report.md` verrà usato per redigere la relazione finale del progetto. 
<br>La cartella raccoglie inoltre:
    - `Assegnazione progetto.md`: contenente la descrizione dettagliata del progetto assegnato;
    - `Guida per lo studente.md`: contenente la descrizione di tutti i passi di configurazione necessari per l'attivazione del flusso di lavoro a supporto dello sviluppo del progetto;
- `gradle/`: ospita il `.jar` relativo al sistema di gestione delle dipendenze *Gradle*.
- `lib`: include eventuali librerie esterne utilizzate dal progetto.
- `res`: contiene risorse varie utilizzate dal sistema 
- `src`: cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In `main/` ci saranno i file sorgente e `test/` conterrà i test di unità previsti.
- `drawings/`: contiene tutti i diagrammi UML usati per descrivere il progetto.
- `.gitignore`: specifica tutti i file che devono essere esclusi dal sistema di controllo versione.
- `build.gradle`: esplicita le direttive e la configurazione di *Gradle*. 
- `gradlew` e `gradlew.bat`: eseguibili di *Gradle*, rispettivamente dedicati a Unix e Windows.
- `settings.gradle`: file di configurazione di *Gradle*.

In alcune cartelle è possibile notare la presenza di un unico file nascosto `.keep`: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.
