# Plan de Cours : Les Principes SOLID en Programmation Orientée Objet

## Public Cible
Développeurs, étudiants en informatique (niveau intermédiaire).

## Objectifs du Cours
- Comprendre les enjeux de la conception logicielle (maintenabilité, évolutivité, testabilité).
- Maîtriser chacun des 5 principes SOLID.
- Savoir identifier les "code smells" (mauvaises pratiques) liés au non-respect de ces principes.
- Être capable de refactoriser un code existant pour le rendre conforme à SOLID.



## Module 0 : Prérequis et Bases de la POO (1h)
Pour aborder ce cours dans de bonnes conditions, les apprenants doivent rafraîchir ou valider leurs connaissances sur la Programmation Orientée Objet.
*   **Concepts clés :** Classes, Objets, Méthodes, Attributs, Constructeurs.
*   **Mécanismes avancés :** Héritage, Encapsulation, Polymorphisme, Interfaces et Classes Abstraites.

> **💳 EXERCICE D'APPLICATION : PROJET FIL ROUGE - ÉTAPE 0 (Lancement)**
> Les apprenants s'échauffent sur les bases de la POO en créant l'état initial du projet :
> 1. **Encapsulation :** Créer une classe `Utilisateur` avec des propriétés privées (nom, email, solde) et des getters/setters sécurisés.
> 2. **Le Monolithe (Anti-pattern) :** Créer une énorme classe `GestionWallet`. Cette classe doit absolument tout faire : gérer le solde, valider les dépôts/retraits, calculer les frais de transaction, sauvegarder l'historique en base de données, et envoyer un SMS à chaque opération. Cette classe est volontairement mal conçue et très couplée, et servira de point de départ (legacy code) pour les futurs refactoring.

---

## Module 1 : Introduction à la Conception Logicielle (30 min)
*   **Les défis du développement logiciel :** La dette technique, le code spaghetti, la rigidité et la fragilité du code.
*   **Qu'est-ce que SOLID ?** Acronyme introduit par Robert C. Martin (Uncle Bob).
*   **Pourquoi SOLID ?** Réduire les couplages, augmenter la cohésion, faciliter les tests unitaires et le travail en équipe.

---

## Module 2 : [S] Single Responsibility Principle - Principe de Responsabilité Unique (1h)
> *"Une classe ne devrait avoir qu'une seule raison de changer."*

*   **Concept :** Séparation des préoccupations (Separation of Concerns). Chaque classe ou module doit avoir une seule responsabilité métier.
*   **Code Smell :** Les "God Classes" (classes à tout faire).

> **💳 EXERCICE D'APPLICATION : PROJET FIL ROUGE - ÉTAPE S**
> **Objectif : Refactorisation du monolithe.**
> *Contexte :* La classe `GestionWallet` (créée au Module 0) est devenue trop grosse et impossible à maintenir.
> *Consigne :* Découpez cette classe en responsabilités distinctes. Vous devez créer :
> - Une classe `Portefeuille` (qui gère uniquement l'état du solde).
> - Un `GestionnaireTransaction` (qui exécute les opérations mathématiques et valide les plafonds).
> - Un `HistoriqueRepository` (pour la logique de sauvegarde).
> - Un `ServiceNotification` (pour la logique d'envoi de messages).

---

## Module 3 : [O] Open/Closed Principle - Principe Ouvert/Fermé (1h)
> *"Les entités logicielles doivent être ouvertes à l'extension, mais fermées à la modification."*

*   **Concept :** Pouvoir ajouter de nouvelles fonctionnalités sans modifier le code source existant (utilisation du polymorphisme ou du pattern Strategy).
*   **Code Smell :** Des chaînes interminables de `if/else` ou `switch` pour gérer de nouveaux types.

> **💳 EXERCICE D'APPLICATION : PROJET FIL ROUGE - ÉTAPE O**
> **Objectif : Gérer plusieurs méthodes de paiement sans toucher au cœur.**
> *Contexte :* Le Wallet souhaite ajouter plusieurs méthodes de rechargement (Carte Bancaire, Virement, Crypto), chacune ayant un mode de calcul de frais différent.
> *Consigne :* Au lieu d'utiliser des blocs "if/else" dans le `GestionnaireTransaction` pour calculer les frais, créez une interface `IMethodePaiement` (avec une méthode `calculerFrais()`). Chaque méthode de paiement implémentera sa propre logique. Le système doit pouvoir accepter une future méthode (ex: PayPal) sans aucune modification du code existant.

---

## Module 4 : [L] Liskov Substitution Principle - Principe de Substitution de Liskov (1h)
> *"Les objets d'une classe dérivée doivent pouvoir remplacer les objets de la classe de base sans altérer le bon fonctionnement du programme."*

*   **Concept :** Les sous-classes doivent respecter le contrat défini par la classe parente. L'héritage doit être comportemental, pas juste structurel.
*   **Code Smell :** Une méthode héritée qui lance une exception `NotImplementedException` ou l'utilisation intensive de vérifications de type (`instanceof`).

> **💳 EXERCICE D'APPLICATION : PROJET FIL ROUGE - ÉTAPE L**
> **Objectif : Corriger une hiérarchie d'héritage abusive.**
> *Contexte :* Vous décidez d'introduire un `CryptoWallet` qui hérite directement de votre classe de base `Portefeuille`. Or, le `Portefeuille` classique possède une méthode `RetirerAuxDistributeurs(montant)`. Puisqu'on ne peut pas retirer directement de la cryptomonnaie liquide à un distributeur, votre `CryptoWallet` lève une exception non prévue lorsqu'on appelle cette méthode.
> *Consigne :* Identifiez la violation du principe de Liskov. Restructurez la hiérarchie d'héritage (par exemple avec une abstraction de plus haut niveau) pour isoler les portefeuilles "Fiat" (monnaie physique) des portefeuilles virtuels.

---

## Module 5 : [I] Interface Segregation Principle - Principe de Ségrégation des Interfaces (1h)
> *"Aucun client ne devrait être forcé de dépendre de méthodes qu'il n'utilise pas."*

*   **Concept :** Préférer plusieurs petites interfaces spécifiques plutôt qu'une seule grosse interface (Fat Interface).
*   **Code Smell :** L'implémentation de méthodes "vides" (dummy) justes pour satisfaire une interface trop large.

> **💳 EXERCICE D'APPLICATION : PROJET FIL ROUGE - ÉTAPE I**
> **Objectif : Éviter les interfaces "fourre-tout".**
> *Contexte :* Pour uniformiser votre code, vous aviez créé une grosse interface `IPortefeuille` qui contenait `getSolde()`, `effectuerVirement()`, mais aussi `getAdresseBlockchain()` et `signerSmartContract()`. Cela force le portefeuille classique (Euros/Dollars) à implémenter des méthodes blockchain vides qui ne lui servent à rien.
> *Consigne :* Découpez l'interface principale en une interface socle `IPortefeuilleDeBase`, et créez une interface spécifique `IWeb3Compatible` qui sera implémentée uniquement par les portefeuilles cryptos.

---

## Module 6 : [D] Dependency Inversion Principle - Principe d'Inversion des Dépendances (1h)
> *"Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau. Les deux doivent dépendre d'abstractions."*

*   **Concept :** Découpler les couches applicatives en utilisant l'Injection de Dépendances (DI).
*   **Code Smell :** Instancier directement des classes concrètes externes avec le mot-clé `new` à l'intérieur de la logique métier.

> **💳 EXERCICE D'APPLICATION : PROJET FIL ROUGE - ÉTAPE D**
> **Objectif : Découpler le service de notification.**
> *Contexte :* Le `ServiceNotification` de votre Wallet instancie toujours "en dur" une classe externe `FournisseurSMSTwilio` (via un `new FournisseurSMSTwilio()`). Le produit vous demande désormais d'envoyer des alertes par Email pour les transactions importantes, et par Push mobile.
> *Consigne :* Inverser la dépendance. Créez une interface d'abstraction `INotifier` (ou `IMessagerie`). Modifiez le constructeur du `ServiceNotification` pour qu'il exige l'injection d'un objet implémentant `INotifier`, rendant ainsi le Wallet totalement agnostique de la technologie d'envoi utilisée.

---

## Module 7 : Conclusion et Exercices de Synthèse (2h)
*   **Résumé :** Comment ces 5 principes s'articulent et se renforcent mutuellement.
*   **Mise en garde :** Ne pas faire de sur-ingénierie (YAGNI, KISS). Appliquer SOLID de manière pragmatique.

*   **Exercices de Synthèse Globale :**
    1. **Audit de code Legacy :** Produire un mini-rapport listant toutes les violations des 5 principes SOLID sur un projet mal conçu.
    2. **Refactorisation Architecturale :** Réécrire intégralement le code Legacy pour qu'il soit conforme.
    3. **Conception "From Scratch" :** Concevoir sur papier l'architecture d'un système financier complexe (ex: plateforme de trading).
    4. **L'épreuve d'Évolutivité (Proof of Concept) :** Ajouter une nouvelle fonctionnalité complexe au projet de l'exercice 2.