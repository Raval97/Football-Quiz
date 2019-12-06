-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Gru 2019, 19:57
-- Wersja serwera: 10.1.37-MariaDB
-- Wersja PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `quiz`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pytania`
--

CREATE TABLE `pytania` (
  `NrPytania` int(11) NOT NULL,
  `Tresc` text NOT NULL,
  `OdpA` text NOT NULL,
  `OdpB` text NOT NULL,
  `OdpC` text NOT NULL,
  `OdpD` text NOT NULL,
  `OdpPoprawna` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `pytania`
--

INSERT INTO `pytania` (`NrPytania`, `Tresc`, `OdpA`, `OdpB`, `OdpC`, `OdpD`, `OdpPoprawna`) VALUES
(1, '#Kto strzelil obie bramki w meczu Polska vs Portugalia w 2006r obie bramki dla Polski dajace zwyciestwo 2:1?', 'A   Maciej Zurawski', 'B   Jacek Krzynowek', 'C   Robert Lewandowski', 'D   Euzebiusz Smolarek', 'D'),
(2, '#Z jakim portugalskim pilkarzem wiarze sie niechlubna historia Tomasza Hajty podczas MS w 2002r?', 'A   Nuno Gomes', 'B   Luis Figo', 'C   Pedro Pauleta', 'D   Cristiano Ronaldo', 'C'),
(3, '#Jaki kraj reprezentuje Pierre-Emerick Aubameyang?', 'A   Gwinea', 'B   Gabon', 'C   Gambia', 'D   Wybrzeze Kosci Sloniowej', 'B'),
(4, '#Która reprezentacja moze poszczycic sie najwieksza ilooscia Mistrzostw Swiata (5 tytulow)?', 'A   Niemcy', 'B   Brazylia', 'C   Wlochy', 'D   Francja', 'B'),
(5, '#Jaka pozycja na boisku okresla numer 10?', 'A   Srodkowy obronca', 'B   Wysuniety napastnik', 'C   Prawy skrzydlowy', 'D   Ofensywny srodkowy pomocnik', 'D'),
(6, '#Który z tych zespo?ow ma najwiecej tytulów mistrza kraju?', 'A   Juventus Turyn', 'B   Manchester United', 'C   Bayern Monachium', 'D   Real Madryt', 'A'),
(7, '#Jak nazywa sie stadion Evertonu F.C.?', 'A   White Hart Lane', 'B   Goodison Park', 'C   Stamford Bridge', 'D   Emirates Stadium', 'B'),
(8, '#Naskuteczniejszym pilkarzem Mistrzostw Swiata jest Miroslav Kloes, jaki jest jego dorobek na MS?', 'A   12', 'B   18', 'C   16', 'D   14', 'C'),
(9, '#Kto jako ostatni zdobyl \"zlota pilke\" przed seria Cristiano Ronaldo i Lionela Messiego?', 'A   Kaka', 'B   Ronaldinho', 'C   Fabio Cannavaro', 'D   Thierry Henry', 'A'),
(10, '#Z jaka druzyna odpadla reprezentacja polski w cwiercfinale ME w 2016r?', 'A   Szwajcaria', 'B   Portugalia', 'C   Francja', 'D   Niemcy', 'B');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `pytania`
--
ALTER TABLE `pytania`
  ADD PRIMARY KEY (`NrPytania`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `pytania`
--
ALTER TABLE `pytania`
  MODIFY `NrPytania` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
