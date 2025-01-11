# Spring Batch Job: Traitement de Commandes depuis un Fichier CSV

## Objectif

Ce projet vise à créer un **job Spring Batch** capable de traiter une liste de commandes provenant d'un fichier CSV. Chaque commande contient un identifiant, un nom de client et un montant. Le job doit :
1. Lire les commandes depuis un fichier CSV.
2. Appliquer une remise de 10% sur le montant de chaque commande.
3. Écrire les commandes transformées dans une base de données HSQLDB.
4. Afficher les commandes insérées à la fin de l'exécution.

---

## Fonctionnalités

### 1. Lecture des données depuis un fichier CSV
Le fichier `orders.csv` contient les commandes initiales avec les colonnes suivantes :
- `orderId` : Identifiant de la commande.
- `customerName` : Nom du client.
- `amount` : Montant de la commande.

### 2. Transformation des données
Une remise de **10%** est appliquée sur le montant (`amount`) de chaque commande.

### 3. Écriture des données dans une base de données HSQLDB
Les commandes transformées sont enregistrées dans une base de données HSQLDB.

### 4. Affichage des commandes insérées à la fin de l'exécution
Un **JobCompletionNotificationListener** affiche les commandes insérées après l'exécution du job.

---

## Technologies Utilisées

- **Spring Boot** : Framework principal pour la création de l'application.
- **Spring Batch** : Utilisé pour gérer le traitement en batch.
- **HSQLDB** : Base de données embarquée pour stocker les commandes.
- **Maven** : Outil de gestion de dépendances et de build.
- **Java 11+** : Langage utilisé pour le développement.

---

## Prérequis

Avant de démarrer le projet, assurez-vous d'avoir installé les éléments suivants :

- **Java 11 ou supérieur**.
- **Maven** pour la gestion des dépendances et la construction du projet.
- **Spring Boot** et **Spring Batch**.
- **HSQLDB** pour la base de données embarquée.

---

## Configuration du Projet

### 1. Créer le fichier `orders.csv`

z
Créez un fichier `orders.csv` avec les données suivantes dans le répertoire racine du projet :

```csv
orderId,customerName,amount
1,John Doe,100.00
2,Jane Smith,200.00
3,Alan Walker,150.00
