-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 24 2020 г., 09:48
-- Версия сервера: 10.4.8-MariaDB
-- Версия PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `vokrug_obs`
--

-- --------------------------------------------------------

--
-- Структура таблицы `table 1`
--

CREATE TABLE `table 1` (
  `id` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(6) DEFAULT NULL,
  `podtype` varchar(10) DEFAULT NULL,
  `nameO` varchar(13) DEFAULT NULL,
  `Address` varchar(26) DEFAULT NULL,
  `comment` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 1`
--

INSERT INTO `table 1` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,945747', '30,483548', 'Аптеки', '', 'Озерки', 'ул. Осипенко 2', 'круглосуточная'),
(2, '59,944373', '30,47929', 'Аптеки', '', 'Вита', 'пр. Индустриальный 19', ''),
(3, '59,938604', '30,477655', 'Аптеки', '', 'Озерки', 'пр. Индустриальный 11 к. 1', ''),
(4, '59,945747', '30,483548', 'Аптеки', '', 'Живика', 'ул. Осипенко 2', ''),
(5, '59,945594', '30,486296', 'Аптеки', '', 'Первая помощь', 'пр. Косыгина 26', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 2`
--

CREATE TABLE `table 2` (
  `id` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `podtype` varchar(10) DEFAULT NULL,
  `nameO` varchar(17) DEFAULT NULL,
  `Address` varchar(25) DEFAULT NULL,
  `comment` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 2`
--

INSERT INTO `table 2` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,934006', '30,409131', 'Гос. учереждения', '', 'Военкомат', 'Республиканская 16', ''),
(2, '59,938847', '30,485802', 'Гос. учереждения', '', 'МФЦ', 'пр. Наставников 6 к. 2', ''),
(3, '59,937752', '30,472121', 'Гос. учереждения', '', 'Отдел полиции №13', 'ул. Передовиков 3', ''),
(4, '59,953903', '30,41471', 'Гос. учереждения', '', 'Налоговая', 'пр. Среднеохтинский 34/12', ''),
(5, '59,937752', '30,472121', 'Гос. учереждения', '', 'Паспортный стол', 'ул. Передовиков 3', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 3`
--

CREATE TABLE `table 3` (
  `id` int(2) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(13) DEFAULT NULL,
  `podtype` varchar(20) DEFAULT NULL,
  `nameO` varchar(16) DEFAULT NULL,
  `Address` varchar(36) DEFAULT NULL,
  `comment` varchar(31) DEFAULT NULL,
  `Null00` varchar(10) DEFAULT NULL,
  `Null1` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 3`
--

INSERT INTO `table 3` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`, `Null00`, `Null1`) VALUES
(1, '59,944431', '30,48335', 'Магазины быта', 'Строительный магазин', 'Народный', 'Косыгина 21', 'В хозяйственных целях норм', '', ''),
(2, '59,946905', '30,457883', 'Магазины быта', 'Строительный магазин', 'Максидом', 'ул. Передовиков 18 к.2', '', '', ''),
(3, '59,894075', '30,514755', 'Магазины быта', 'Строительный магазин', 'Икея', 'г. Кудрово, Мурманское шоссе 12 км 1', 'Можно проехать на 153 маршрутке', '', ''),
(4, '59,946175', '30,473981', 'Магазины быта', 'Одежда и обувь', 'Июнь', 'пр. Индустриальный 24', '', '', ''),
(5, '59,933023', '30,437635', 'Магазины быта', 'Одежда и обувь', 'Заневский каскад', 'пр. Заневский 67 к. 2', '', '', ''),
(6, '59,894075', '30,514755', 'Магазины быта', 'Одежда и обувь', 'Мега', 'г. Кудрово, Мурманское шоссе 12 км 1', '', '', ''),
(7, '59,933663', '30,439', 'Магазины быта', 'ХозМаг', 'Домовой', 'пр. Заневский 71 к. 2', '', '', ''),
(8, '59,947672', '30,490635', 'Магазины быта', 'ХозМаг', 'Улыбка радуги', 'пр. Наставников 27', '', '', ''),
(9, '59,945806', '30,481751', 'Магазины быта', 'концелярский', 'АзъБука', 'пр. Косыгина 24 к. 2', '', '', ''),
(10, '59,946175', '30,473981', 'Магазины быта', 'книжный', 'Буквоед', 'пр. Индустриальный 24', '', '', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 4`
--

CREATE TABLE `table 4` (
  `id` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(8) DEFAULT NULL,
  `podtype` varchar(12) DEFAULT NULL,
  `nameO` varchar(33) DEFAULT NULL,
  `Address` varchar(18) DEFAULT NULL,
  `comment` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 4`
--

INSERT INTO `table 4` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,941367', '30,482479', 'Медицина', 'Поликлиника', 'Поликлиника №120', 'ул. Ленская 4', ''),
(2, '59,946396', '30,462132', 'Медицина', 'Поликлиника', 'Дет. Поликлиника №68', 'ул. Передовиков 21', ''),
(3, '59,870863', '30,308673', 'Медицина', 'Поликлиника', 'Студ. Поликлиника №75', 'ул. Кузнецовская 9', ''),
(4, '59,9444', '30,489396', 'Медицина', 'Стоматология', 'Стоматологическая поликлиника №32', 'пр. Наставников 22', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 5`
--

CREATE TABLE `table 5` (
  `id` int(2) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(6) DEFAULT NULL,
  `podtype` varchar(8) DEFAULT NULL,
  `nameO` varchar(18) DEFAULT NULL,
  `Address` varchar(24) DEFAULT NULL,
  `comment` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 5`
--

INSERT INTO `table 5` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,938135', '30,47734', 'Поесть', 'Шаверма', 'Шаверма', 'пр. Индустриальный 9а', ''),
(2, '59,941367', '30,467746', 'Поесть', 'Столовая', 'Столовая ГУАП', 'пр. Передовиков 13', ''),
(3, '59,945513', '30,482371', 'Поесть', 'Пиццерия', 'Ямм Пицца', 'пр. Косыгина 24 к. 1', ''),
(4, '59,948194', '30,479784', 'Поесть', 'Пиццерия', 'Папа Джонс', 'пр. Энтузиастов 33 к. 1', ''),
(5, '59,945806', '30,481751', 'Поесть', 'Пиццерия', 'Pizza Hut', 'пр. Косыгина 24 к. 2', ''),
(6, '59,933023', '30,437635', 'Поесть', 'Фудкорт', 'Заневский каскад', 'пр. Заневский 67 к. 2', ''),
(7, '59,946175', '30,473981', 'Поесть', 'Фудкорт', 'Июнь', 'пр. Индустриальный 24', ''),
(8, '59,947946', '30,4739', 'Поесть', 'Кафе', 'Токио-Сити', 'пр. Индустриальный 26/24', ''),
(9, '59,933023', '30,437635', 'Поесть', 'Кафе', 'Евразия', 'пр. Заневский 67 к. 2', ''),
(10, '59,948194', '30,479784', 'Поесть', 'Кафе', 'Хачапури и Хинкали', 'пр. Энтузиастов 33 к. 1', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 6`
--

CREATE TABLE `table 6` (
  `id` int(2) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(8) DEFAULT NULL,
  `podtype` varchar(10) DEFAULT NULL,
  `nameO` varchar(11) DEFAULT NULL,
  `Address` varchar(26) DEFAULT NULL,
  `comment` varchar(23) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 6`
--

INSERT INTO `table 6` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,942092', '30,469615', 'Продукты', '', 'Дикси', 'пр. Косыгина 7 к. 2', ''),
(2, '59,947023', '30,477466', 'Продукты', '', 'Окей', 'пр. Индустриальный 25', ''),
(3, '59,946175', '30,473981', 'Продукты', '', 'Карусель', 'пр. Индустриальный 24', ''),
(4, '59,933023', '30,437635', 'Продукты', '', 'Перекрёсток', 'пр. Заневский 67 к. 2', ''),
(5, '59,934483', '30,497543', 'Продукты', '', 'Лента', 'ул. Хасанская 17 к. 1', ''),
(6, '59,944431', '30,48335', 'Продукты', '', 'Народный', 'Косыгина 21 к. 1', 'Ходить в крайнем случае'),
(7, '59,938685', '30,474816', 'Продукты', '', 'Пятерочка', 'пр. Индустриальный 10 к. 1', ''),
(8, '59,933104', '30,440626', 'Продукты', '', 'Лента', 'пр. Заневский 71', ''),
(9, '59,941948', '30,478355', 'Продукты', '', 'Магнит', 'пр. Индустриальный 15', ''),
(10, '59,948834', '30,475463', 'Продукты', '', 'Ермолино', 'пр. Индустриальный 27', 'Дешево и сердито');

-- --------------------------------------------------------

--
-- Структура таблицы `table 7`
--

CREATE TABLE `table 7` (
  `id` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL,
  `podtype` varchar(15) DEFAULT NULL,
  `nameO` varchar(26) DEFAULT NULL,
  `Address` varchar(23) DEFAULT NULL,
  `comment` varchar(21) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 7`
--

INSERT INTO `table 7` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,946175', '30,473981', 'Развлечения', 'Боулинг/бильярд', 'Июнь', 'пр. Индустриальный 24', ''),
(2, '59,944706', '30,494462', 'Развлечения', 'Кальянная', 'ENDORPHIN', 'пр. Косыгина 27 к. 1', 'Для лиц старше 18 лет'),
(3, '59,946175', '30,473981', 'Развлечения', 'Кинотеатр', 'MORI CINEMA', 'пр. Индустриальный 24', ''),
(4, '59,933023', '30,437635', 'Развлечения', 'Кинотеатр', 'Формула Кино', 'пр. Заневский 67 к. 2', ''),
(5, '59,945373', '30,45888', 'Развлечения', 'Досуг', 'Молодёжный центр \"Квадрат\"', 'ул. Передовиков 16 к. 2', ''),
(6, '59,950956', '30,473693', 'Развлечения', 'Батутный центр', 'Fun Jump', 'пр. Индустриальный 31', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 8`
--

CREATE TABLE `table 8` (
  `id` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL,
  `podtype` varchar(8) DEFAULT NULL,
  `nameO` varchar(20) DEFAULT NULL,
  `Address` varchar(26) DEFAULT NULL,
  `comment` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 8`
--

INSERT INTO `table 8` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,941407', '30,466938', 'Спорт', 'Спортзал', 'Фитнес ГУАП', 'ул. Передовиков 13 к. 2', ''),
(2, '59,945373', '30,45888', 'Спорт', 'Спортзал', 'Квадрат', 'ул. Передовиков 16 к. 2', 'бесплатно'),
(3, '59,937918', '30,482901', 'Спорт', 'Спортзал', 'Fitness House', 'ул. Хасанская 10 к. 2', 'с бассейном'),
(4, '59,946175', '30,473981', 'Спорт', 'Спортзал', 'Extra Sport', 'пр. Индустриальный 24', ''),
(5, '59,945373', '30,45888', 'Спорт', 'Кольцо', 'Баскетбольное кольцо', 'ул. Передовиков 16 к. 2', 'во дворе'),
(6, '59,93887', '30,473585', 'Спорт', 'Поле', 'Футбольное поле', 'пр. Индустриальный 10 к. 2', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 9`
--

CREATE TABLE `table 9` (
  `id` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL,
  `podtype` varchar(10) DEFAULT NULL,
  `nameO` varchar(25) DEFAULT NULL,
  `Address` varchar(22) DEFAULT NULL,
  `comment` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 9`
--

INSERT INTO `table 9` (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,929556', '30,296671', 'Университет', '', 'Главный', 'ул. Большая морская 67', ''),
(2, '59,857364', '30,327771', 'Университет', '', 'Для 1-2 курса', 'ул. Гастелло 15', ''),
(3, '59,855814', '30,330475', 'Университет', '', 'Для юристов и экономистов', 'ул. Ленсовета 14', '');

-- --------------------------------------------------------

--
-- Структура таблицы `table 10`
--

CREATE TABLE `table 10` (
  `Код` int(1) DEFAULT NULL,
  `lat` varchar(9) DEFAULT NULL,
  `lng` varchar(9) DEFAULT NULL,
  `type` varchar(6) DEFAULT NULL,
  `podtype` varchar(16) DEFAULT NULL,
  `nameO` varchar(10) DEFAULT NULL,
  `Address` varchar(26) DEFAULT NULL,
  `comment` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `table 10`
--

INSERT INTO `table 10` (`Код`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES
(1, '59,94179', '30,468429', 'Услуги', 'Парикмахерская', 'Альбина', 'пр. Косыгина 7 к. 1', ''),
(2, '59,947023', '30,477466', 'Услуги', 'Химчистка', 'Apetta', 'пр. Индустриальный 25', ''),
(3, '59,946905', '30,457883', 'Услуги', 'Ключи', 'Максидом', 'ул. Передовиков 18 к. 2', ''),
(4, '59,938685', '30,474816', 'Услуги', 'Ателье', 'Пятерочка', 'пр. Индустриальный 10 к. 1', ''),
(5, '59,94083', '30,456176', 'Услуги', 'Ремонт телефонов', 'Imaster', 'Косыгина 4а', ''),
(6, '59,914033', '30,297857', 'Услуги', 'Автошкола', 'ФАРА', 'пр. Лермонтовский 44', ''),
(7, '59,945806', '30,481751', 'Услуги', 'Фотоцентр', 'Printsburg', 'пр. Косыгина 24 к. 2', '');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
