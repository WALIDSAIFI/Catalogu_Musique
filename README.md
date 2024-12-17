// README.md
# Projet : API REST pour la gestion d'un catalogue musical

## Contexte
Une entreprise souhaite mettre en place une API REST pour gérer son catalogue musical. L'API doit permettre la gestion des albums et chansons avec différents niveaux d'accès (USER/ADMIN) via une authentification sécurisée stateless.

## Entités principales
- **Album** :
    - `id` (Long, généré automatiquement)
    - `titre` (String)
    - `artiste` (String)
    - `annee` (Integer)

- **Chanson** :
    - `id` (Long, généré automatiquement)
    - `titre` (String)
    - `duree` (Integer)
    - `trackNumber` (Integer)
    - `album` (Album)

- **User** :
    - `id` (Long, généré automatiquement)
    - `login` (String, unique)
    - `password` (String, encodé)
    - `active` (Boolean)
    - `roles` (Collection<String>)

## Fonctionnalités

### Gestion des Albums
- **Lister les albums** :
    - Endpoint : `/api/user/albums`
    - Méthode : `GET`
    - Accès : USER, ADMIN
    - Pagination & tri disponibles.

- **Rechercher les albums par titre** :
    - Endpoint : `/api/user/albums/search?title=...`
    - Méthode : `GET`
    - Accès : USER, ADMIN

- **Rechercher les albums par artiste** :
    - Endpoint : `/api/user/albums/search?artist=...`
    - Méthode : `GET`
    - Accès : USER, ADMIN

- **Filtrer les albums par année** :
    - Endpoint : `/api/user/albums/filter?year=...`
    - Méthode : `GET`
    - Accès : USER, ADMIN

- **Ajouter un nouvel album** :
    - Endpoint : `/api/admin/albums`
    - Méthode : `POST`
    - Accès : ADMIN uniquement

- **Modifier un album** :
    - Endpoint : `/api/admin/albums/{id}`
    - Méthode : `PUT`
    - Accès : ADMIN uniquement

- **Supprimer un album** :
    - Endpoint : `/api/admin/albums/{id}`
    - Méthode : `DELETE`
    - Accès : ADMIN uniquement

### Gestion des Chansons
- **Lister les chansons** :
    - Endpoint : `/api/user/songs`
    - Méthode : `GET`
    - Accès : USER, ADMIN
    - Pagination & tri disponibles.

- **Rechercher les chansons par titre** :
    - Endpoint : `/api/user/songs/search?title=...`
    - Méthode : `GET`
    - Accès : USER, ADMIN

- **Lister les chansons d'un album** :
    - Endpoint : `/api/user/songs/album/{albumId}`
    - Méthode : `GET`
    - Accès : USER, ADMIN

- **Ajouter une nouvelle chanson** :
    - Endpoint : `/api/admin/songs`
    - Méthode : `POST`
    - Accès : ADMIN uniquement

- **Modifier une chanson** :
    - Endpoint : `/api/admin/songs/{id}`
    - Méthode : `PUT`
    - Accès : ADMIN uniquement

- **Supprimer une chanson** :
    - Endpoint : `/api/admin/songs/{id}`
    - Méthode : `DELETE`
    - Accès : ADMIN uniquement

### Gestion des Utilisateurs
- **Authentification** :
    - Endpoint : `/api/auth/login`
    - Méthode : `POST`

- **Créer un compte** :
    - Endpoint : `/api/auth/register`
    - Méthode : `POST`

- **Lister les utilisateurs** :
    - Endpoint : `/api/admin/users`
    - Méthode : `GET`
    - Accès : ADMIN uniquement

- **Modifier les rôles d'un utilisateur** :
    - Endpoint : `/api/admin/users/{id}/roles`
    - Méthode : `PUT`
    - Accès : ADMIN uniquement

## Sécurité
- Authentification stateless avec JWT.
- Les rôles sont contrôlés sur chaque endpoint :
    - `/api/admin/*` nécessite le rôle ADMIN.
    - `/api/user/*` nécessite le rôle USER.
- Cryptage des mots de passe avec `BCryptPasswordEncoder` ou un encoder similaire.
- Les tokens JWT :
    - Incluent l'émetteur (`.withIssuer()`).
    - Stockent l'identité de l'utilisateur (`.withSubject()`).
    - Contiennent les rôles (`.withArrayClaim()` / `.withClaim()`).
    - Ont une durée de validité limitée (`.withExpiresAt()`).
    - Sont signés de manière sécurisée (`.sign()` avec HMAC ou RSA).

## Outils et technologies utilisés
- **Framework** : Spring Boot
- **Base de données** : MongoDB
- **Sécurité** : Spring Security avec JWT
- **Build** : Maven
- **Tests** : JUnit, Mockito
- **Déploiement** : Docker, Jenkins
- **Documentation API** : Swagger
