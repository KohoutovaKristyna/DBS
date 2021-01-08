-- Table: Nemocnice_LDN
CREATE TABLE Nemocnice_LDN (
    id_nemocnice int  NOT NULL,
    nazev varchar(50)  NOT NULL,
    adresa varchar(100)  NOT NULL,
    primar varchar(40)  NOT NULL,
    CONSTRAINT Nemocnice_LDN_pk PRIMARY KEY  (id_nemocnice)
);

-- Table: Obvodni_lekar
CREATE TABLE Obvodni_lekar (
    id_lekare int  NOT NULL,
    jmeno varchar(30)  NOT NULL,
    prijmeni varchar(30)  NOT NULL,
    adresa_ordinace varchar(100)  NOT NULL,
    CONSTRAINT Obvodni_lekar_pk PRIMARY KEY  (id_lekare)
);

-- Table: Pacient
CREATE TABLE Pacient (
    rodne_cislo varchar(13)  NOT NULL,
    jmeno varchar(30)  NOT NULL,
    prijmeni varchar(30)  NOT NULL,
    id_lekare int  NOT NULL,
    id_pokoje int  NOT NULL,
    CONSTRAINT Pacient_pk PRIMARY KEY  (rodne_cislo)
);

-- Table: Pokoj
CREATE TABLE Pokoj (
    id_pokoje int  NOT NULL,
    poschodi int  NOT NULL,
    kapacitu int  NOT NULL,
    id_nemocnice int  NOT NULL,
    CONSTRAINT Pokoj_pk PRIMARY KEY  (id_pokoje)
);

-- Table: Rehab_sestra
CREATE TABLE Rehab_sestra (
    id_sestry int  NOT NULL,
    jmeno varchar(30)  NOT NULL,
    prijmeni varchar(30)  NOT NULL,
    CONSTRAINT Rehab_sestra_pk PRIMARY KEY  (id_sestry)
);

-- Table: Rehabilitace
CREATE TABLE Rehabilitace (
    id_rehabilitace int  NOT NULL,
    nazev varchar(50)  NOT NULL,
    frekvence_cetnost int  NOT NULL,
    rodne_cislo_pacienta varchar(13)  NOT NULL,
    id_sestry int  NOT NULL,
    CONSTRAINT Rehabilitace_pk PRIMARY KEY  (id_rehabilitace)
);

-- foreign keys
-- Reference: Pacient_Obvodni_lekar (table: Pacient)
ALTER TABLE Pacient ADD CONSTRAINT Pacient_Obvodni_lekar
    FOREIGN KEY (id_lekare)
    REFERENCES Obvodni_lekar (id_lekare);

-- Reference: Pacient_Pokoj (table: Pacient)
ALTER TABLE Pacient ADD CONSTRAINT Pacient_Pokoj
    FOREIGN KEY (id_pokoje)
    REFERENCES Pokoj (id_pokoje);

-- Reference: Pokoj_Nemocnice_LDN (table: Pokoj)
ALTER TABLE Pokoj ADD CONSTRAINT Pokoj_Nemocnice_LDN
    FOREIGN KEY (id_nemocnice)
    REFERENCES Nemocnice_LDN (id_nemocnice);

-- Reference: Rehabilitace_Pacient (table: Rehabilitace)
ALTER TABLE Rehabilitace ADD CONSTRAINT Rehabilitace_Pacient
    FOREIGN KEY (rodne_cislo_pacienta)
    REFERENCES Pacient (rodne_cislo);

-- Reference: Rehabilitace_Rehab_sestra (table: Rehabilitace)
ALTER TABLE Rehabilitace ADD CONSTRAINT Rehabilitace_Rehab_sestra
    FOREIGN KEY (id_sestry)
    REFERENCES Rehab_sestra (id_sestry);

-- End of file.

