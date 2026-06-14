
## Présentation du Projet Fil Rouge : Le Système "Wallet"
Tout au long de cette formation, l'apprentissage sera articulé autour d'un projet de développement unique et évolutif : la conception d'un **Système de Gestion de Wallet (Portefeuille Numérique)**.
Ce fil rouge simulera le cycle de vie réel d'une application logicielle. Les apprenants débuteront le cours en codant une version monolithique et fortement couplée du Wallet (gérant pêle-mêle les soldes, les méthodes de paiement, la blockchain et l'envoi de SMS). 
À chaque nouveau module, leur mission sera d'auditer cette base de code et de la refactoriser concrètement en appliquant le principe SOLID tout juste étudié, pour finalement aboutir à une architecture propre, robuste et totalement extensible.

## Module 0 : Le Monolithe (Anti-pattern)

> [!WARNING] Problèmes de cette implémentation
> - **Couplage fort** : `GestionService` s'occupe de tout (Base de données, SMS, règles métier).
> - **Difficile à tester** : Pour tester un simple dépôt, il faut envoyer un SMS ou écrire en base de données.
> - **Difficile à maintenir** : Tout changement impacte une seule classe énorme.
> 
> **Solution à venir (Module 2) :** Séparer ces responsabilités dans différentes classes selon le principe SRP (Single Responsibility Principle).
