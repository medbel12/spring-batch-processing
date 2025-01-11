L'objectif est de créer un job Spring Batch qui traite une liste de
commandes provenant d'un fichier CSV. Chaque commande contient un identifiant,
un nom de client, et un montant. Le job doit transformer les données en
appliquant une remise sur le montant et écrire les commandes mises à jour dans
une base de données.

1.    Créer un fichier CSV orders.csv :
      o  Ce fichier contient les commandes initiales avec les colonnes : orderId, customerName, amount.
2.    Lire les commandes depuis le fichier CSV avec FlatFileItemReader.
3.    Transformer les données :
    o  Appliquer une remise de 10% sur le montant (amount) pour chaque commande.
4.    Écrire les commandes transformées dans une base de données HSQLDB.
5.    Afficher les commandes insérées à la fin de l'exécution avec un JobCompletionNotificationListener.
