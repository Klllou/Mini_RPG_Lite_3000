# Mini_RPG_Lite_3000

            Fonctionnements principaux du Jeu :

1) Le nombre d'ennemies générés s'adapte aux nombres de héros choisis.

2) A chaque combat, on ajoute un ennemie en plus pour améliorer la difficulté. 

3) Chaque héro possède sa propre arme, de la nourriture, les Spellcaster possède également des potions et les hunter possèdent des flèches.
Je n'ai cependant pas créer d'armure car je n'ai pas réussi à implémenter de méthode pour "se défendre". 
Je souhaitais utiliser l'armure dans cette méthode pour réduire les dégats subits. N'ayant alors aucune fonction j'ai retiré les armures.

4) Les actions possibles dépendent de la classe du héro. il peuvent tous Attaquer, ou Consommer de la nourriture 
(les Spellcasters peuvent consommer des potions en plus). Je n'ai donc pas l'option "se défendre" pour les mêmes raisons que précédemment.

5) A la fin de chaque combat, les héros peuvent tous choisir des récompenses (adaptées en fonction de la classe du héro).

6) Il y a 4 combats avant d'arriver au combat final contre le Boss

            Autres Précisions :

1) Je n'ai malheureusement pas eu le temps d'implémenter une interface Graphique fonctionnelle, le jeu est donc dans la console.

2) Je n'ai pas implémenter de Code unitaire, le concept étant un peu flou pour moi. Cependant j'ai essayé de réaliser et m'assurer que les 
règles soient respectées.

3) Je n'ai rien implémenter dans les classes/interface InputParser, ControleParser, GUIParser car au début du développement du jeu je n'avais pas saisi leur utilités...
Cependant j'ai compris mon "erreur" lorsque mon jeu était quasiment achevé (n'ayant plus assez de temps pour procéder à toutes les corrections, je n'ai pas pu modifier cela). 
Comme mon jeu se restreint à être dans la console cela ne pose cependant pas de problème au fonctionnement du jeu (chose à changer si je souhaite implémenter 
une interface graphique plus tard).
C'est donc un élément à améliorer.

