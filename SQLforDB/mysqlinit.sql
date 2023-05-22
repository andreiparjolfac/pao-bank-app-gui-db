create database bankdb;

create user 'bank'@'localhost' identified by "securepassword";

use bankdb;

grant all on bankdb.* to 'bank'@'localhost';

create table Users(ID int primary key auto_increment,
					FirstName  varchar(50),
                    LastName varchar(50),
                    CNP varchar(13)) Engine = InnoDB;
create table Accounts(ID int primary key auto_increment,
						Type varchar(50),
                        Balance double) Engine = InnoDB;
create table Mappings(ID int primary key auto_increment,
						UserId int,
                        AccountId int,
                        foreign key (UserId) references Users(ID) on delete cascade,
                        foreign key (AccountId) references Accounts(ID) on delete cascade) Engine = InnoDB;

create table Reporting(
				ID int primary key auto_increment,
                Actiune varchar(50),
                Timp timestamp default current_timestamp
)Engine = InnoDB;

create table Transactions(
	ID int primary key auto_increment,
    TipTranzactie varchar(50),
    Amount double,
    AccountId int,
    Timp timestamp default current_timestamp
)Engine=InnoDB;

select * from mappings;

select t.ID,FirstName,LastName,t.TipTranzactie,t.Amount,t.Timp,t.AccountId from transactions t left join mappings m on m.AccountId = t.AccountId left join users u on m.UserId=u.ID;