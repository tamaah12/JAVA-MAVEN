package com.ism;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.ism.entities.Article;
import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.entities.Paiement;
import com.ism.entities.User;
import com.ism.repository.ClientRepository;
import com.ism.repository.UserRepository;
import com.ism.repository.bd.ClientRepositoryBD;
import com.ism.repository.bd.UserRepositoryBD;
import com.ism.services.ClientService;
import com.ism.services.UserService;
import com.ism.services.impl.ArticlesServiceImpl;
import com.ism.services.impl.ClientServiceImpl;
import com.ism.services.impl.DetteServiceImpl;
import com.ism.services.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        int choix = 0;
        Scanner scanner = new Scanner(System.in);

        // Fabrique ou factory
        UserRepository userRepository = new UserRepositoryBD();
        ClientRepository clientRepository = new ClientRepositoryBD();
        ClientService clientServiceImpl = new ClientServiceImpl(clientRepository, userRepository);
        UserService userServiceImpl = new UserServiceImpl(userRepository, clientRepository);
        ArticlesServiceImpl articlesServiceImpl = new ArticlesServiceImpl();
        DetteServiceImpl detteServiceImpl = new DetteServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository, userRepository);
        UserService userService = new UserServiceImpl(userRepository, clientRepository);

        


        Client client;
        User user;
        Article article;
        Dette dette;

        do {
            System.out.println("1- Créer un client");
            System.out.println("2- Lister les clients");
            System.out.println("3- Rechercher un client par téléphone");
            System.out.println("4- Créer un compte User");
            System.out.println("5- Lister les comptes utilisateur");
            System.out.println("6- Lister les clients avec un compte utilisateur");
            System.out.println("7- Lister les clients sans compte utilisateur");
            System.out.println("8- Créer un article");
            System.out.println("9- Lister les articles disponibles");
            System.out.println("10- Mettre à jour le stock d'un article");
            System.out.println("11- Gérer les dettes");
            System.out.println("12- Quitter");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    client = new Client();
                    System.out.println("Entrez le surnom : ");
                    String surname = scanner.nextLine();
                    if (clientServiceImpl.searchClientBySurname(surname) == null) {
                        client.setSurname(surname);
                        System.out.println("Entrer le téléphone");
                        client.setTelephone(scanner.nextLine());
                        System.out.println("Entrer l'adresse");
                        client.setAddress(scanner.nextLine());
                        clientServiceImpl.createClient(client);
                    } else {
                        System.out.println("Le surnom existe déjà.");
                    }
                    break;

                case 2:
                    List<Client> list = clientServiceImpl.findAllClients();
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Entrer le téléphone");
                    String phone = scanner.nextLine();
                    client = clientServiceImpl.search(phone);
                    if (client != null) {
                        System.out.println("Client trouvé : " + client);
                    } else {
                        System.out.println("Aucun client trouvé avec ce téléphone.");
                    }
                    break;

                case 4:
                    user = new User();
                    System.out.println("Entrer l'email : ");
                    user.setEmail(scanner.nextLine());
                    System.out.println("Entrer le login : ");
                    user.setLogin(scanner.nextLine());
                    System.out.println("Entrer le mot de passe : ");
                    user.setPassword(scanner.nextLine());
                    userServiceImpl.createUser(user);
                    break;

                case 5:

                List<User> users = userService.findAllUser();
                    if (users.isEmpty()) {
                        System.out.println("Aucun utilisateur trouvé.");
                    } else {
                        users.forEach(u -> System.out.println(u.getLogin()));
                        }
                        break;
                case 6:

                List<Client> clientsWithUser = clientService.findClientsWithUser();
                if (clientsWithUser.isEmpty()) {
                        System.out.println("Aucun client avec un compte utilisateur trouvé.");
                    } else {
                        clientsWithUser.forEach(c -> System.out.println(c.getSurname() + " : " + c.getUser().getLogin()));
                        }
                        break;

                

                case 7:
                    List<Client> clientsSansCompte = clientServiceImpl.findClientsWithoutUser();
                    clientsSansCompte.forEach(System.out::println);
                    break;

                case 8:
                    article = new Article();
                    System.out.println("Entrer le libelle de l'article : ");
                    article.setLibelle(scanner.nextLine());
                    System.out.println("Entrer la reference de l'article : ");
                    article.setReference(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println("Entrer la quantité en stock : ");
                    article.setQuantiteEnStock(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Entrer la description de l'article : ");
                    article.setDescription(scanner.nextLine());
                    articlesServiceImpl.createArticle(article);
                    break;

                case 9:
                    List<Article> availableArticles = articlesServiceImpl.filterAvailableArticles();
                    availableArticles.forEach(System.out::println);
                    break;

                case 10:
                    System.out.println("Entrer le libelle de l'article à mettre à jour : ");
                    String articleLibelle = scanner.nextLine();
                    System.out.println("Entrer la nouvelle quantité : ");
                    int quantite = scanner.nextInt();
                    articlesServiceImpl.updateStock(articleLibelle, quantite);
                    break;

                case 11:
                    System.out.println("1- Créer une dette");
                    System.out.println("2- Lister les dettes d'un client");
                    System.out.println("3- Enregistrer un paiement pour une dette");
                    System.out.println("4- Retour");
                    int choixDette = scanner.nextInt();
                    scanner.nextLine();

                    switch (choixDette) {
                        case 1:
                            System.out.println("Entrer l'id du client");
                            Long clientId = scanner.nextLong();
                            scanner.nextLine();
                            client = clientServiceImpl.findClientById(clientId);
                            if (client != null) {
                                dette = new Dette();
                                System.out.println("Entrer le montant total de la dette");
                                Double montantTotal = scanner.nextDouble();
                                scanner.nextLine();
                                dette.setMontant(montantTotal);
                                dette.setClient(client);
                                dette.setDateCreation(LocalDate.now());
                                detteServiceImpl.createDette(dette);
                                System.out.println("Dette créée : " + dette);
                            } else {
                                System.out.println("Client non trouvé.");
                            }
                            break;

                        case 2:
                            System.out.println("Entrer l'id du client");
                            Long clientId2 = scanner.nextLong();
                            scanner.nextLine();
                            client = clientServiceImpl.findClientById(clientId2);
                            if (client != null) {
                                List<Dette> dettes = detteServiceImpl.findDettesByClient(client);
                                dettes.forEach(System.out::println);
                            } else {
                                System.out.println("Client non trouvé.");
                            }
                            break;

                        case 3:
                            System.out.println("Entrer l'id de la dette à payer");
                            Long detteId = scanner.nextLong();
                            scanner.nextLine();
                            dette = detteServiceImpl.findDetteById(detteId);
                            if (dette != null) {
                                System.out.println("Montant actuel versé : " + dette.getMontantVerse() + " €");
                                System.out.print("Entrez le montant du paiement : ");
                                Double montantPaiement = scanner.nextDouble();
                                scanner.nextLine();


                                Paiement paiement = new Paiement(montantPaiement, LocalDate.now(), dette);


                                dette.ajouterPaiement(paiement);


                                detteServiceImpl.updateDette(dette);

                                if (dette.isSolde()) {
                                    System.out.println("Dette entièrement soldée.");
                                } else {
                                    System.out.println("Montant restant à payer : " + (dette.getMontant() - dette.getMontantVerse()) + " €.");
                                }
                            } else {
                                System.out.println("Dette non trouvée.");
                            }
                            break;

                        case 4:
                            break;

                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }
                    break;

                case 12:
                    System.out.println("Quitter...");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        } while (choix != 12);

        scanner.close();
    }
}
