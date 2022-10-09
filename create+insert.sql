
-- Database: `gamestore`

CREATE TABLE `carts` (
  `UserID` char(5) NOT NULL,
  `BeverageID` char(5) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `detailtransactions` (
  `TransactionID` char(5) NOT NULL,
  `GameID` char(5) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `detailtransactions` (`TransactionID`, `GameID`, `Quantity`) VALUES
('TR001', 'GA001', 71),
('TR001', 'GA002', 230),
('TR002', 'GA001', 140),
('TR002', 'GA002', 44);


CREATE TABLE `games` (
  `GameID` char(5) NOT NULL,
  `GameName` varchar(30) NOT NULL,
  `GameType` varchar(30) NOT NULL,
  `GamePrice` int(11) NOT NULL,
  `GameStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `games` (`GameID`, `GameName`, `GameType`, `GamePrice`, `GameStock`) VALUES
('GA001', 'Minecraft', 'RPG', 650000, 82),
('GA002', 'Dota 2', 'FPS', 4500000, 178);


CREATE TABLE `headertransactions` (
  `TransactionID` char(5) NOT NULL,
  `UserID` char(5) DEFAULT NULL,
  `TransactionDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `headertransactions` (`TransactionID`, `UserID`, `TransactionDate`) VALUES
('TR001', 'US002', '2021-06-08'),
('TR002', 'US002', '2021-06-08');


CREATE TABLE `users` (
  `UserID` char(5) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  `UserEmail` varchar(50) DEFAULT NULL,
  `UserPassword` varchar(30) DEFAULT NULL,
  `UserDOB` date DEFAULT NULL,
  `UserGender` varchar(10) DEFAULT NULL,
  `UserAddress` varchar(255) DEFAULT NULL,
  `UserPhone` varchar(30) DEFAULT NULL,
  `UserRole` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `users` (`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserDOB`, `UserGender`, `UserAddress`, `UserPhone`, `UserRole`) VALUES
('US001', 'Liu Kang', 'admin', 'admin', NULL, 'Male', 'asdasdasdasd Street', '0920398193812319', 'Admin'),
('US002', 'Kung Lao', 'customer', 'customer', NULL, 'Male', 'realm Street', '012345678911', 'Customer');


ALTER TABLE `carts`
  ADD PRIMARY KEY (`UserID`,`BeverageID`);

ALTER TABLE `detailtransactions`
  ADD PRIMARY KEY (`TransactionID`,`GameID`);

ALTER TABLE `headertransactions`
  ADD PRIMARY KEY (`TransactionID`);
COMMIT;
