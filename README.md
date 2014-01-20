ProgrammeTV
===========

Web application Bootstrap / Java REST.
Programme télé des 19 chaînes de la TNT Française.

Web Services
===========

-	/chaines : Toutes les chaînes
-	/chaines/<id_chaine> : Détails d'une chaîne
-	/chaines/precedente/<id_chaine> : La chaîne précédente
-	/chaines/suivante/<id_chaine> : La chaîne suivante

-	/programmes : Tous les programmes
-	/programmes/<id_programme> : Détails d'un programme
-	/programmes/soir : Les programmes du soir
-	/programmes/soir/<id_chaine> : Les programmes du soir d'une chaîne
-	/programmes/moment : Les programmes du moment
-	/programmes/moment/<id_chaine> : Les programmes du moment d'une chaîne
-	/programmes/chaine/<id_chaine> : Les programmes d'une chaîne
-	/programmes/chaine/<id_chaine>/<date_yyyyMMdd> : Les programmes d'une chaîne selon une date
-	/programmes/precedent/<id_programme> : Le programme précédent
-	/programmes/suivant/<id_programme> : Le programme suivant
