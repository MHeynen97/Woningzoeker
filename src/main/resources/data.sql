INSERT INTO gebruikers (id, gebruikersnaam, wachtwoord, rol) VALUES (1, 'Birdy', '$2a$10$04/2Vjpr0UySTD3fC/BDqezdNkhAiREFZYBuz9itIp9RomglzqYMa', 'USER');
INSERT INTO gebruikers (id, gebruikersnaam, wachtwoord, rol) VALUES (2, 'mheynen', '$2a$10$JvFDc5Mhs76JHfSpq7WPYOgYnHk8nMdAogdlk466XYaMj.pEM0PWC', 'ADMIN');

-- Locaties
INSERT INTO locaties (id, adres, woonplaats, postcode) VALUES
    (1, 'Kerkstraat 10', 'Amsterdam', '1017GA'),
    (2, 'Marktplein 5', 'Utrecht', '3511CE');

-- Huizen
INSERT INTO huizen (id, locatie_id, prijs, aantal_kamers, energie_label, omschrijving, huurkoop)
VALUES (1, 1, 250000, 4, 'A', 'Ruim huis in het centrum van Amsterdam', 'Koop');

-- Profielen
INSERT INTO profielen (id, naam, geboortedatum, locatie_id, omschrijving, gebruiker_id)
VALUES (1, 'Jan de Vries', '1990-04-15', 2, 'Zoekt een ruim appartement in de stad', 1);

