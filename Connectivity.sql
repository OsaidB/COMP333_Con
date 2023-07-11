use COMP333_Con_Base;

drop database COMP333_Con_Base;

create database COMP333_Con_Base;

DROP TABLE useraccount;

CREATE TABLE `stat` (
  `connectivity` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`connectivity`));

delete from stat 
where connectivity=NULL;

insert into stat (connectivity)
values ('Connected Succssfully');

select * from stat;

select connectivity from stat;
#########################################################################################################################################################
CREATE TABLE SystemUsers (
	Username VARCHAR(25),
    uPassword VARCHAR(42) NOT NULL,
    uName VARCHAR(24),
    
    PRIMARY KEY (Username)
);

INSERT INTO SystemUsers(Username, uPassword) VALUES ('admin', 'admin'), ('root', '1234'), ('user', '0000');
#######

CREATE TABLE Employee (
	ID INT AUTO_INCREMENT,
    eName VARCHAR(32) NOT NULL,
    SSN BIGINT NOT NULL UNIQUE,
    Phone BIGINT NOT NULL,
    Salary REAL NOT NULL,
    StartDate DATE DEFAULT (now()),
    EndDate DATE,
    Address VARCHAR(128),
    
	PRIMARY KEY (ID)
);

CREATE TABLE EmployeeUsers (
	eID INT,
    uPassword VARCHAR(42) NOT NULL,
    
    PRIMARY KEY (eID, uPassword),
    FOREIGN KEY (eID) REFERENCES Employee(ID)
			ON UPDATE CASCADE ON DELETE CASCADE
);
###|||||||||||||||||||||||
INSERT INTO Employee (eName, SSN, Phone, Salary, Address) VALUES
	('Mohammed Anan', 406696325, 595654321, 1500.0, 'Jenin'),
	('Sami Ballot', 409654325, 598765432, 2100.0, 'Nablus'),
	('Rami Awad', 406693425, 595654987, 700.0, 'Jenin');


INSERT INTO EmployeeUsers(eID, uPassword) VALUES (2, '0000');
INSERT INTO EmployeeUsers(eID, uPassword) VALUES (1, '0000');

#########################################################################################################################################################
CREATE TABLE ItemType (
	ID INT AUTO_INCREMENT,
    tName VARCHAR(40) NOT NULL unique,
    tDescription VARCHAR(256),
    
    PRIMARY KEY (ID)
);
ALTER TABLE ItemType AUTO_INCREMENT = 10;



CREATE TABLE Supplier (
	ID INT AUTO_INCREMENT,
    sName VARCHAR(32) NOT NULL,
    Phone BIGINT NOT NULL,
    Website VARCHAR(512),
    DateOfAdding DATE DEFAULT (now()),
    MoreInfo VARCHAR(128),
    Address VARCHAR(128),
	
    UNIQUE (sName, Phone, Website, Address),
	PRIMARY KEY (ID)
);
ALTER TABLE Supplier AUTO_INCREMENT = 100;



CREATE TABLE Item (
	ID BIGINT AUTO_INCREMENT,
    ModelNumber VARCHAR(32),
    PurchasePrice REAL NOT NULL,
    SellingPrice REAL,
    DateOfAdding DATE DEFAULT (now()),
	iDescription VARCHAR(128),
    Quantity INT NOT NULL,
    SupplierID INT,
    ItemType INT NOT NULL,
    
    PRIMARY KEY (ID),
    UNIQUE (ModelNumber),
	FOREIGN KEY (ItemType) REFERENCES ItemType(ID)
			ON UPDATE CASCADE,
	FOREIGN KEY (SupplierID) REFERENCES Supplier(ID)
			ON UPDATE CASCADE ON DELETE SET NULL
);
ALTER TABLE Item AUTO_INCREMENT = 100000;
###|||||||||||||||||||||||
INSERT INTO ItemType (tName, tDescription) VALUES
	('Boneless Ribeye', 'a boneless cut taken from the rib section of beef cattle while the rib steak is a bone-in ribeye'),
	('Delmonico', 'A low-cost alternative to the Rib Eye Steak. A tender and savory cut great for grilling'),
	('Bone-ln Ribeye', 'cut from rib six spanning through twelve '),
	('Porterhouse Steak', 'a composite steak thats derived from the point where the tenderloin and top loin meet'),
	('Filet Mignon', 'a cut of meat taken from the smaller end of the tenderloin, or psoas major of a cow');
    
    INSERT INTO Supplier (sName, Phone, Website, MoreInfo, Address) VALUES
	('AbuAhmad', 966954326, 'www.AbuAhmad.com', 'the owner of alsaada Farm', 'Bethlahem'),
	('hossam', 974326564, 'www.hossam.tr', 'AL-Basha Barn', 'Ramallah'),
	('abdullah', 999999999, 'wear.abdullah.net', 'Imported Meat', 'Turky');
    
INSERT INTO Item(ModelNumber, PurchasePrice, SellingPrice,Quantity,  SupplierID,ItemType) VALUES
	('12-MDL-9943', 120.0, 290.99, 160, 102, 'OneSize', 'BLACK', 5),
	('12-MDL-9943', 120.0, 290.99, 160, 102, 'OneSize', 'RED', 5),
	('12-MDL-9943', 120.0, 290.99, 160, 102, 'OneSize', 'GREEN', 4),
	('12-MDL-9943', 120.0, 290.99, 160, 102, 'OneSize', 'WHITE', 2),
	('12-MDL-9943', 120.0, 290.99, 160, 102, 'OneSize', 'YELLO', 1);
#########################################################################################################################################################
CREATE TABLE Customer (
	ID BIGINT AUTO_INCREMENT,
    cName VARCHAR(32),
    PhoneNumber BIGINT,
    DateOfAdding DATE DEFAULT (now()),
    Address VARCHAR(128),
    
    PRIMARY KEY (ID)
);
ALTER TABLE Customer AUTO_INCREMENT = 10000;

###|||||||||||||||||||||||
#Insert Customers
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Edythe Yocum', 380486913, 'Beitunia');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Jeanette Storment', 975200812, 'Abu Dis');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Shanna Tall', 666132425, 'Jenin');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Mel Brumley', 248533070, 'Nablus');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Treena Marton', 798960861, 'Dura');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Loida Toft', 169948355, 'Dura');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Muriel Mazza', 851036460, 'Surif');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Toshiko Halderman', 054367552, 'Ramallah');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Mel Brumley', 424234649, 'Jenin');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Lesley Navarette', 969750550, 'Jenin');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Verline Hegland', 566599778, 'al-Bireh');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Deana Doris', 692539572, 'Abu Dis');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Deana Doris', 068828801, 'Beit Ummar');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Nan Beamon', 720634682, 'Jerusalem');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Steve Runge', 891629561, 'Bani Naeim');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Deana Doris', 697549326, 'Sair');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Jacinta Lockhart', 986210591, 'Yatta');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Steve Runge', 247394072, 'Beitunia');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Evita Raymo', 662223304, 'Ramallah');
INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('Kristine Clevenger', 077456613, 'Beit Sahour');
#########################################################################################################################################################
CREATE TABLE Orders (
	ID BIGINT AUTO_INCREMENT,
    DateAndTime DATETIME DEFAULT CURRENT_TIMESTAMP,
    cID BIGINT,
    eID INT,
    
    PRIMARY KEY (ID),
	FOREIGN KEY (cID) REFERENCES Customer(ID)
			ON UPDATE CASCADE ON DELETE SET NULL,
	FOREIGN KEY (eID) REFERENCES Employee(ID)
			ON UPDATE CASCADE ON DELETE SET NULL
);
ALTER TABLE Orders AUTO_INCREMENT = 100000;



CREATE TABLE OrderDetails (
	oID BIGINT,
	iID BIGINT,
    Price REAL NOT NULL,
    Quantity INT NOT NULL,
    
	CONSTRAINT OrderDetailsKEY PRIMARY KEY (oID, iID),
    FOREIGN KEY (oID) REFERENCES Orders(ID)
			ON UPDATE CASCADE,
	FOREIGN KEY (iID) REFERENCES Item(ID)
			ON UPDATE CASCADE
);
ALTER TABLE OrderDetails AUTO_INCREMENT = 100000;
    
    
    ###|||||||||||||||||||||||
    #Insert Orders With Details
INSERT INTO Orders (cID, eID) VALUES
	(10000, 1),
	(10004, 1),
	(10005, 1),
	(10004, 2),
	(10004, 3),
	(10000, 2);

#INSERT INTO OrderDetails (oID, iID, Price, Quantity) VALUES
#	(100000, 100000, 175, 1),
#	(100001, 100002, 160, 2),
#	(100002, 100010, 100, 1),
#	(100003, 100015, 25, 4),
#	(100004, 100014, 90, 1),
#	(100005, 100019, 30, 1);
    
#########################################################################################################################################################
