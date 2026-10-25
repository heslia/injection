# Inject

Petit injecteur Java pour minecraft

Essayer de pas vous faire ban ^^

J'ai rajouter une version de doomsday cheat patcher pour pouvoir ce faire injecter
mais d'autre cheats peuvent fonctionner (ex: vape)


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

