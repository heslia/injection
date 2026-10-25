# Inject

Petit injecteur Java qui charge un **Java agent**

Essayer de pas vous faire ban ^^

## Prérequis

- **JDK**
- Le jeu ne doit pas être lancé avec `-XX:+DisableAttachMechanism`.

## Compilation

```bash
javac Inject.java
```

## Utilisation

Lance d'abord le jeu, puis :

```bash
# 1. Lister les JVM joignables et repérer celle de Minecraft
java -cp . Inject list

# 2. Injecter le jar dans le pid choisi
java -cp . Inject <pid> chemin/vers/cheat.jar
```

