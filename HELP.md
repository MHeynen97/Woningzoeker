
# API Documentatie – Woningzoeker Endpoints

## Authenticatie

| Methode | Endpoint         | Beschrijving                          |
|---------|------------------|---------------------------------------|
| POST    | `/auth/register` | Registreer een nieuwe gebruiker       |
| POST    | `/auth/login`    | Log in met gebruikersgegevens         |

---

## Gebruiker

| Methode | Endpoint        | Beschrijving                          |
|---------|-----------------|---------------------------------------|
| POST    | `/gebruiker`    | Maak een nieuwe gebruiker aan         |
| GET     | `/gebruiker/{id}` | Haal een gebruiker op via ID         |
| PUT     | `/gebruiker/{id}` | Update een bestaande gebruiker       |
| DELETE  | `/gebruiker/{id}` | Verwijder een gebruiker              |

---

## Profiel

| Methode | Endpoint        | Beschrijving                          |
|---------|-----------------|---------------------------------------|
| POST    | `/profiel`      | Maak een nieuw profiel aan            |
| GET     | `/profiel/{id}` | Haal een profiel op                   |
| PUT     | `/profiel/{id}` | Update een profiel                    |
| DELETE  | `/profiel/{id}` | Verwijder een profiel                 |

---

## Locatie

| Methode | Endpoint         | Beschrijving                          |
|---------|------------------|---------------------------------------|
| POST    | `/locatie`       | Voeg een locatie toe                  |
| GET     | `/locatie/{id}`  | Haal locatiegegevens op               |
| PUT     | `/locatie/{id}`  | Update locatiegegevens                |
| DELETE  | `/locatie/{id}`  | Verwijder een locatie                 |

---

## Huis

| Methode | Endpoint     | Beschrijving                            |
|---------|--------------|-----------------------------------------|
| POST    | `/huis`      | Voeg een nieuw huis toe                 |
| GET     | `/huis/{id}` | Haal een specifiek huis op              |
| PUT     | `/huis/{id}` | Update een huis                         |
| DELETE  | `/huis/{id}` | Verwijder een huis                      |

---

## Bericht

| Methode | Endpoint                          | Beschrijving                            |
|---------|-----------------------------------|-----------------------------------------|
| POST    | `/berichten`                      | Verstuur een nieuw bericht              |
| GET     | `/berichten/ontvangen/{gebruikerId}` | Haal ontvangen berichten op         |
| GET     | `/berichten/verzonden/{gebruikerId}` | Haal verzonden berichten op         |
| DELETE  | `/berichten/{id}`                 | Verwijder een bericht (optioneel)       |

---

## Bieding

| Methode | Endpoint      | Beschrijving                            |
|---------|---------------|-----------------------------------------|
| POST    | `/bieding`    | Plaats een bieding op een huis          |
| GET     | `/bieding/{id}` | Haal een bieding op                   |
| PUT     | `/bieding/{id}` | Update een bieding                     |
| DELETE  | `/bieding/{id}` | Verwijder een bieding                  |