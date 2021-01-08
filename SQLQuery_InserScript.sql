use DBS2020_KristynaKohoutova

INSERT INTO Nemocnice_LDN VALUES (1,'Newlife hospital', '108 Harley Street , London W1G 7ET , United Kingdom', 'Dr Brown');
INSERT INTO Nemocnice_LDN VALUES(2,'Care & Cure Hospital', '13-16 Craven Hill Gardens , London W2 3EH , United Kingdom', 'Dr Miller');
INSERT INTO Nemocnice_LDN VALUES(3,'Medwin Cares', '39 Harley Street , London W1G 6BG , United Kingdom','Dr Smith');
INSERT INTO Nemocnice_LDN VALUES(4,'Remedy plus care', '9 Harley Street , London W1G 9AL , United Kingdom', 'Dr Wilson');
INSERT INTO Nemocnice_LDN VALUES(5,'Health Object  Hospital', '2B Heath Hurst Road , London NW3 2RX , United Kingdom', 'Dr Wilson');
INSERT INTO Nemocnice_LDN VALUES(6,'NorthMark', '13 Upper Wimpole St , London W1G 6LP , United Kingdom', 'Dr Jones');
INSERT INTO Nemocnice_LDN VALUES(7,'HealthyWave', '42-48 New Kings Road , London SW6 4LS , United Kingdom', 'Dr Williams');
INSERT INTO Nemocnice_LDN VALUES(8,'HealthStreet', '133 Lillie Road , London SW6 7SX , United Kingdom', 'Dr Rodriguez');
INSERT INTO Nemocnice_LDN VALUES(9,'Mercy medicare', '21 Addison Place , London W11 4RJ , United Kingdom', 'Dr Davis');
INSERT INTO Nemocnice_LDN VALUES(10,'Intense Hospital', '39 Harley St , London W1G 8QH , United Kingdom', 'Dr Garcia');

INSERT INTO Obvodni_lekar VALUES (1532,'Martin','Dr Anthal','250 Hendon Way , London NW4 3NL , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1533,'Greg','Dr Popwell','2 Devonshire Place , 1st floor , London W1G 6HJ , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1534,'Luke','Dr Wack','57 Portland Place , London W1B 1QN , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1535,'William','Dr Handler','33 Weymouth Street , London W1G 7BY , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1536,'John','Dr Mash','42-46 Weymouth Street , London W1G 6NP , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1537,'Philip','Dr Bonebrake','St Thomas Hospital , Westminsterm Bridge Road , London SE1 7EH , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1538,'Henry','Dr Butcher','23 Ansdell St , London W8 5BN , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1539,'James','Dr Bender','27 Haven Lane , Ealing , London W5 2HZ , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1540,'Liam','Dr Carey','191 Wardour Street , London W1F 8ZE , United Kingdom');
INSERT INTO Obvodni_lekar VALUES (1541,'Benjamin','Dr Lipp','2 Connaught Court , Connaught Street , London W2 2AJ , United Kingdom');

INSERT INTO Pokoj VALUES (1, 1, 2, 1);
INSERT INTO Pokoj VALUES (2, 2, 3, 1);
INSERT INTO Pokoj VALUES (3, 2, 3, 1);
INSERT INTO Pokoj VALUES (4, 1, 3, 3);
INSERT INTO Pokoj VALUES (5, 1, 2, 3);
INSERT INTO Pokoj VALUES (6, 3, 2, 3);
INSERT INTO Pokoj VALUES (7, 1, 3, 10);
INSERT INTO Pokoj VALUES (8, 2, 3, 10);
INSERT INTO Pokoj VALUES (9, 1, 3, 7);
INSERT INTO Pokoj VALUES (10, 1, 3, 5);

INSERT INTO Pacient VALUES ('AA_01_23_44_B', 'Jack', 'Black', 1532, 1);
INSERT INTO Pacient VALUES ('AB_02_22_44_C', 'Colton', 'Green', 1533, 1);
INSERT INTO Pacient VALUES ('BA_03_21_44_D', 'Hunter', 'Blue', 1533, 3);
INSERT INTO Pacient VALUES ('BB_04_20_44_E', 'Hailey', 'Yellow', 1535, 5);
INSERT INTO Pacient VALUES ('AC_05_19_44_F', 'Robert', 'White', 1536, 2);
INSERT INTO Pacient VALUES ('BC_06_18_44_G', 'Greyson', 'Red', 1537, 7);
INSERT INTO Pacient VALUES ('CC_07_17_44_H', 'Piper', 'Brown', 1538, 9);
INSERT INTO Pacient VALUES ('CB_08_16_44_I', 'Piper', 'Gray', 1540, 2);
INSERT INTO Pacient VALUES ('CA_09_15_44_J', 'Jack', 'Red', 1540, 10);
INSERT INTO Pacient VALUES ('DD_10_14_44_K', 'Wesley', 'Black', 1540, 10);

INSERT INTO Rehab_sestra VALUES (1, 'Olivia', 'Grass');
INSERT INTO Rehab_sestra VALUES (2, 'Emma', 'Grasshopper');
INSERT INTO Rehab_sestra VALUES (3, 'Ava', 'Hopper');
INSERT INTO Rehab_sestra VALUES (4, 'Sophia', 'Luck');
INSERT INTO Rehab_sestra VALUES (5, 'Isabella', 'House');
INSERT INTO Rehab_sestra VALUES (6, 'Mia', 'Great');
INSERT INTO Rehab_sestra VALUES (7, 'Evelyn', 'Small');
INSERT INTO Rehab_sestra VALUES (8, 'Ella', 'Tree');
INSERT INTO Rehab_sestra VALUES (9, 'Emily', 'Steak');
INSERT INTO Rehab_sestra VALUES (10, 'Camila', 'Lancer');

INSERT INTO Rehabilitace VALUES (1,'Knee', 2, 'AA_01_23_44_B', 1);
INSERT INTO Rehabilitace VALUES (2,'Tigh', 3, 'AB_02_22_44_C', 2);
INSERT INTO Rehabilitace VALUES (3,'Calf', 1, 'BA_03_21_44_D', 2);
INSERT INTO Rehabilitace VALUES (4,'Spine', 1, 'BB_04_20_44_E', 3);
INSERT INTO Rehabilitace VALUES (5,'Fitness_exercise', 2, 'AA_01_23_44_B', 5);
INSERT INTO Rehabilitace VALUES (6,'Neck_vertebrae', 4, 'AB_02_22_44_C', 5);
INSERT INTO Rehabilitace VALUES (7,'Shoulders', 5, 'BA_03_21_44_D', 5);
INSERT INTO Rehabilitace VALUES (8,'Correction_of_deportment', 2, 'BA_03_21_44_D', 4);
INSERT INTO Rehabilitace VALUES (9,'Correction_of_walking', 1, 'BB_04_20_44_E', 4);
INSERT INTO Rehabilitace VALUES (10,'Toes_rehabilitation', 6, 'BB_04_20_44_E', 2);


