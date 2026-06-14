## Exercice 2 : Système Domotique (Smart Home)

### Contexte
Vous développez l'ordinateur central d'une maison intelligente. Le programme pilote les ampoules connectées, le chauffage, et l'alarme. L'ordinateur central est un gigantesque "God Object".

### Module 0 : Le Monolithe (Anti-pattern) - Point de départ

**PHP :**
```php
class SmartHomeApp {
    public function turnOnLight(string $room) {
        echo "Lumière allumée dans : {$room}\n";
    }

    public function setTemperature(float $temp) {
        echo "Thermostat réglé sur {$temp}°C\n";
    }

    public function lockDoors() {
        echo "Portes verrouillées.\n";
    }

    public function turnOffAll() {
        echo "Extinction globale...\n";
        echo "Lumières éteintes.\n";
        echo "Thermostat en mode éco.\n";
        // DANGER : On désactive l'alarme au lieu de l'activer !
        echo "Alarme DÉSACTIVÉE (Faille de sécurité !).\n";
    }
}
```

**Java :**
```java
class SmartHomeApp {
    public void turnOnLight(String room) {
        System.out.println("Lumière allumée dans : " + room);
    }

    public void setTemperature(float temp) {
        System.out.println("Thermostat réglé sur " + temp + "°C");
    }

    public void lockDoors() {
        System.out.println("Portes verrouillées.");
    }

    public void turnOffAll() {
        System.out.println("Extinction globale...");
        System.out.println("Lumières éteintes.");
        System.out.println("Thermostat en mode éco.");
        // DANGER : On désactive l'alarme au lieu de l'activer !
        System.out.println("Alarme DÉSACTIVÉE (Faille de sécurité !).");
    }
}
```

### Vos missions de Refactoring (Étape par étape)

1. **SRP (Single Responsibility Principle)** : La classe `SmartHomeApp` connaît trop de détails. Déléguez la gestion à des contrôleurs spécifiques (`LightController`, `ThermostatController`, `SecurityController`).
2. **OCP (Open/Closed Principle)** : Au lieu d'appeler les méthodes une par une dans `turnOffAll()`, créez une liste d'équipements (`IDevice`) possédant une méthode `turnOff()`. La maison itérera sur cette liste.
3. **LSP (Liskov Substitution Principle)** : Le problème de `turnOffAll()` est que pour l'alarme de sécurité, "s'éteindre" signifie se désactiver, ce qui est dangereux. Il faut redéfinir la hiérarchie. L'alarme ne doit pas hériter du même comportement "On/Off" qu'une ampoule, ou son `turnOff()` doit être bloqué/sécurisé.
4. **ISP (Interface Segregation Principle)** : Un capteur de température (`TemperatureSensor`) ne fait que lire des données. Il ne doit pas être forcé d'implémenter l'interface `ISwitchable` (qui possède `turnOn` / `turnOff`). Créez des interfaces distinctes : `ISwitchable` et `IReadable`.
5. **DIP (Dependency Inversion Principle)** : La `SmartHomeApp` ne doit pas dépendre des classes concrètes (ex: `PhilipsHueLight`). Elle doit dépendre d'abstractions (`ILightBulb`), permettant de brancher n'importe quelle marque de domotique sans changer le code de l'application centrale.