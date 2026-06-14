# Exercices d'Architecture : Maîtriser les principes SOLID

Ce document contient deux exercices pratiques visant à refactoriser des architectures monolithiques (Anti-patterns) en architectures robustes, évolutives et maintenables en appliquant les 5 principes SOLID.

---

## Exercice 1 : Le Gestionnaire de Commandes E-commerce

### Contexte
Vous avez hérité d'un système de gestion de commandes pour une boutique en ligne. Malheureusement, le développeur précédent a tout regroupé dans une seule et unique classe : `OrderManager`. 

Cette classe s'occupe de tout :
1. Vérifier si un produit est en stock.
2. Calculer le prix final avec les réductions.
3. Déduire le stock.
4. Générer une facture au format PDF.
5. Envoyer un email de confirmation au client.

### Module 0 : Le Monolithe (Anti-pattern) - Point de départ

Voici le code de départ. Analysez-le et identifiez pourquoi il est difficile à maintenir et à faire évoluer.

**PHP :**
```php
class Product {
    public function __construct(public string $name, public float $price, public int $stock) {}
}

class OrderManager {
    public function processOrder(Product $product, int $quantity, string $discountType, string $userEmail) {
        // 1. Vérification du stock
        if ($product->stock < $quantity) {
            throw new Exception("Stock insuffisant pour {$product->name}");
        }

        // 2. Calcul du prix avec réduction
        $total = $product->price * $quantity;
        if ($discountType === "VIP") {
            $total = $total * 0.80; // 20% de réduction
        } elseif ($discountType === "BLACK_FRIDAY") {
            $total = $total * 0.50; // 50% de réduction
        }

        // 3. Mise à jour du stock
        $product->stock -= $quantity;

        // 4. Génération de la facture
        $this->generatePdfInvoice($userEmail, $total);

        // 5. Envoi de l'email
        $this->sendEmail($userEmail, "Votre commande de {$total}€ est confirmée.");
    }

    private function generatePdfInvoice(string $email, float $total) {
        // Logique complexe de génération PDF...
        echo "Génération de la facture PDF pour {$email} d'un montant de {$total}€\n";
    }

    private function sendEmail(string $email, string $message) {
        // Logique de connexion au serveur SMTP...
        echo "Email envoyé à {$email} : {$message}\n";
    }
}
```

**Java :**
```java
class Product {
    public String name;
    public float price;
    public int stock;

    public Product(String name, float price, int stock) {
        this.name = name; this.price = price; this.stock = stock;
    }
}

class OrderManager {
    public void processOrder(Product product, int quantity, String discountType, String userEmail) throws Exception {
        // 1. Vérification du stock
        if (product.stock < quantity) {
            throw new Exception("Stock insuffisant pour " + product.name);
        }

        // 2. Calcul du prix avec réduction
        float total = product.price * quantity;
        if (discountType.equals("VIP")) {
            total = total * 0.80f; // 20% de réduction
        } else if (discountType.equals("BLACK_FRIDAY")) {
            total = total * 0.50f; // 50% de réduction
        }

        // 3. Mise à jour du stock
        product.stock -= quantity;

        // 4. Génération de la facture
        generatePdfInvoice(userEmail, total);

        // 5. Envoi de l'email
        sendEmail(userEmail, "Votre commande de " + total + "€ est confirmée.");
    }

    private void generatePdfInvoice(String email, float total) {
        System.out.println("Génération de la facture PDF pour " + email + " d'un montant de " + total + "€");
    }

    private void sendEmail(String email, String message) {
        System.out.println("Email envoyé à " + email + " : " + message);
    }
}
```

### Vos missions de Refactoring (Étape par étape)

1. **SRP (Single Responsibility Principle)** : Séparez les responsabilités. Créez des classes distinctes pour la gestion de l'inventaire (`InventoryManager`), la facturation (`InvoiceGenerator`) et les notifications (`NotificationService`).
2. **OCP (Open/Closed Principle)** : Le calcul des réductions avec des `if/else` empêche d'ajouter facilement de nouvelles promos (ex: Promo Étudiant). Extrayez cette logique dans une interface `IDiscountStrategy` et créez des classes pour chaque stratégie.
3. **LSP (Liskov Substitution Principle)** : Demain, la boutique vendra des produits numériques (PDF, Logiciels) qui n'ont pas de "stock physique". Assurez-vous que le modèle `Product` est abstrait de manière à ce que les produits physiques et numériques puissent être traités sans déclencher d'erreurs (un produit numérique a un stock infini).
4. **ISP (Interface Segregation Principle)** : Imaginez qu'une interface `IProduct` oblige à implémenter `calculateShippingCost()`. Ségréguez cette interface pour que les produits numériques ne soient pas obligés d'implémenter des frais de port.
5. **DIP (Dependency Inversion Principle)** : La facturation et l'envoi d'emails sont en dur. Utilisez l'injection de dépendances pour passer par des interfaces (`IInvoiceService`, `IEmailSender`).

---
