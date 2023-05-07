CREATE TABLE public.client (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nom VARCHAR(255) NOT NULL,
  cin VARCHAR(20) NOT NULL,
  adresse VARCHAR(255)
);

CREATE TABLE public.employe (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nom VARCHAR(255) NOT NULL,
  prenom VARCHAR(255) NOT NULL,
  adresse VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255)
);

CREATE TABLE public.voiture (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  immatriculation VARCHAR(20) NOT NULL,
  marque VARCHAR(255) NOT NULL,
  date_acquisition DATE
);

CREATE TABLE public.location (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  employe_id BIGINT NOT NULL,
  voiture_id BIGINT NOT NULL,
  client_id BIGINT NOT NULL,
  date_location DATE NOT NULL,
  duree INTEGER NOT NULL,
  date_debut DATE NOT NULL,
  CONSTRAINT fk_location_employe FOREIGN KEY (employe_id) REFERENCES public.employe(id),
  CONSTRAINT fk_location_voiture FOREIGN KEY (voiture_id) REFERENCES public.voiture(id),
  CONSTRAINT fk_location_client FOREIGN KEY (client_id) REFERENCES public.client(id)
);
