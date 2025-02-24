You will implement a software product that:
reads some movie metadata (source: IMDb) from a CSV le, on disk
calculates some statistical values*
writes the resulting statistical data to a CSV le, on disk
takes the input le path and the output le path from a user’s preferences le
runs as a stand-alone executable, from the command line (i.e., there is no GUI)
runs transparently on both MS-Windows, MacOS, and GNU/Linux

* total number of movies, average movies run-time, best director, most present actor/actress, and most
productive year

//core, package
Gestisce la lettura e scrittura dei file CSV.
Contiene le classi che elaborano i dati e calcolano le statistiche.

//app, package
Contiene la classe Main che avvia il programma.
Recupera i percorsi dei file dalle preferenze.
Coordina la lettura, elaborazione e scrittura dei risultati.


//io, package
Responsabile di leggere il file di configurazione utente per i percorsi dei CSV.
Implementa le funzioni di parsing CSV per estrarre i dati dai file.
