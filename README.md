# Grapher

J'ai créé ce plugin pour expérimenter avec des calculs mathématique et des jolis dessins.

Il inclut une commande: `/graph`

#### /graph enable

`/graph enable <nom>` active un graph sur votre postion.

Il ne peut y avoir qu'un seul graph à la fois, si la commande est executée alors qu'un autre graph est actif, ce dernier sera désactivé.

#### /graph disable

`/graph disable` désactive le graph.

#### Paramètres spéciaux

Si le graph `eight` est actif, certaines sous-commandes supplémentaires sont disponibles:

`/graph setAmplitude yaw <valeur>` change l'amplitude du mouvement yaw (horizontal).

`/graph setAmplitude pitch <valeur>` change l'amplitude du mouvement pitch (vertical).

`/graph setFrequency <yaw|pitch> <valeur>` change la "fréquence" du mouvement, autrement dit, le nombre de "boucles" ou "aller-retours" que le mouvement va faire en un seul cycle. Ce paramètre donne des résultats très intéressants avec l'utilisation de décimales.
