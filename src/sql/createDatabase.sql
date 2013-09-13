/* --------------------------------------------------------
    using Single Table Inheritance pattern here
    to display User class inheritance structure
  -------------------------------------------------------- */
CREATE TABLE Users (
    Id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Type int NOT NULL CHECK (Type >= 0),
    Login varchar(20) NOT NULL UNIQUE,
    Password int NOT NULL,
    Name varchar(20) NOT NULL,
    SecondName varchar(20),
    Email varchar(320) NOT NULL,
    Rate int DEFAULT 0
);

-----------------------------------------------------------------

CREATE TABLE Category(
    Id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Name varchar(40) NOT NULL UNIQUE
);

CREATE TABLE Lots(
    Id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Name varchar(40) NOT NULL UNIQUE,
    Description varchar(200) NOT NULL, --описание
    Price double precision NOT NULL CHECK (Price >= 0),
    StartDate date NOT NULL,
    FinishDate timestamp NOT NULL,
    CategoryId int REFERENCES Category(Id),
    SellerId int REFERENCES Users(Id),
    LastCustomerId int REFERENCES Users(Id),
    Status boolean
);

CREATE TABLE Deliveries(
    Id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Name varchar(40) NOT NULL UNIQUE
);

CREATE TABLE Payments(
    Id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Name varchar(40) NOT NULL UNIQUE
);

CREATE TABLE Letters(
    Id int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    FromName varchar(50),
    ToId int REFERENCES Users(Id),
    Subject varchar(200), 
    DeliveryId int REFERENCES Deliveries(Id),
    PaymentId int REFERENCES Payments(Id),
    Description varchar(200) NOT NULL
);