-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 09 Kwi 2017, 17:23
-- Wersja serwera: 10.1.21-MariaDB
-- Wersja PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `biblioteka`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `imie` varchar(20) NOT NULL,
  `nazwisko` varchar(20) NOT NULL,
  `login` varchar(15) NOT NULL,
  `haslo` varchar(15) NOT NULL,
  `ulica` varchar(30) NOT NULL,
  `num_miesz` varchar(5) DEFAULT NULL,
  `kod_pocz` varchar(6) NOT NULL,
  `pesel` varchar(11) NOT NULL,
  `miasto` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`user_id`, `imie`, `nazwisko`, `login`, `haslo`, `ulica`, `num_miesz`, `kod_pocz`, `pesel`, `miasto`) VALUES
(2, 'Damian', 'Rybakrybak', 'damian', 'haslo', 'Pokorniewskiego 5a', '6', '76-777', '8765432', 'Sopot'),
(3, 'Iga', 'Jarocka', 'Iga', '123', 'kwiatowa 10', '', '82-300', '123456', 'Elbl?g'),
(4, 'Asia', 'Pieni??ek', 'asia', '123', 'Ró?ana 6', '19', '55-300', '55566677788', '?wiebodzin'),
(5, 'Bartosz', 'Kucharczyk', 'bart', 'bart', 'Kie?basiana 8', '', '66-200', '977666555', 'Serdelki');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
