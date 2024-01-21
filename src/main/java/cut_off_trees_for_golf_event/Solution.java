package cut_off_trees_for_golf_event;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        List<List<Integer>> trees = IntStream.range(0, forest.size())
                .boxed()
                .flatMap(i -> IntStream.range(0, forest.get(i).size())
                        .filter(j -> forest.get(i).get(j) > 1)
                        .mapToObj(j -> List.of(forest.get(i).get(j), i, j))
                )
                .sorted(Comparator.comparingInt(a -> a.get(0)))
                .toList();

        int result = 0, startY = 0, startX = 0;
        for (List<Integer> tree : trees) {
            int nextSteps = getSteps(tree.get(2), tree.get(1), startX, startY, forest);
            if (nextSteps < 0) return -1;
            result += nextSteps;
            startY = tree.get(1);
            startX = tree.get(2);
        }
        return result;
    }

    private int getSteps(int treeX, int treeY, int startX, int startY, List<List<Integer>> forest) {
        int height = forest.size();
        int width = forest.get(0).size();
        boolean[][] visited = new boolean[height][width];
        Queue<int[]> cellsToVisit = new LinkedList<>();
        cellsToVisit.add(new int[]{startY, startX, 0});

        while (!cellsToVisit.isEmpty()) {
            var currentCell = cellsToVisit.poll();
            int x = currentCell[1];
            int y = currentCell[0];
            if (x == treeX && y == treeY) {
                return currentCell[2];
            }

            Stream.of(
                            new int[]{y - 1, x, currentCell[2] + 1},
                            new int[]{y + 1, x, currentCell[2] + 1},
                            new int[]{y, x - 1, currentCell[2] + 1},
                            new int[]{y, x + 1, currentCell[2] + 1}
                    )
                    .filter(cell -> {
                        int cellX = cell[1];
                        int cellY = cell[0];

                        return cellX >= 0
                               && cellX < width
                               && cellY >= 0
                               && cellY < height
                               && (forest.get(cellY).get(cellX) != 0)
                               && !visited[cellY][cellX];
                    })
                    .forEach(cell -> {
                        visited[cell[0]][cell[1]] = true;
                        cellsToVisit.add(cell);
                    });
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.cutOffTree(List.of(
                List.of(1, 2, 3),
                List.of(0, 0, 4),
                List.of(7, 6, 5)
        )));

        System.out.println(solution.cutOffTree(List.of(
                List.of(1, 2, 3),
                List.of(0, 0, 0),
                List.of(7, 6, 5)
        )));

        System.out.println(solution.cutOffTree(List.of(
                List.of(2, 3, 4),
                List.of(0, 0, 5),
                List.of(8, 7, 6)
        )));

        System.out.println(solution.cutOffTree(List.of(
                List.of(1, 3, 4),
                List.of(2, 6, 5),
                List.of(9, 8, 7)
        )));

//        [[63750247,40643210,95516857,89928134,66334829,58741187,76532780,45104329],
//        [3219401,97566322,9135413,75944198,93735601,33923288,50116695,83660397],
//        [64460750,53045740,31903386,78155821,90848739,38769489,99349027,85982891],
//        [30628785,51077683,70534803,67460877,91077770,74197235,5696362,91459886],
//        [56105195,82479378,45937951,52817583,2768114,43329099,28189138,21418604]]
        System.out.println(solution.cutOffTree(List.of(
                List.of(63750247, 40643210, 95516857, 89928134, 66334829, 58741187, 76532780, 45104329),
                List.of(3219401, 97566322, 9135413, 75944198, 93735601, 33923288, 50116695, 83660397),
                List.of(64460750, 53045740, 31903386, 78155821, 90848739, 38769489, 99349027, 85982891),
                List.of(30628785, 51077683, 70534803, 67460877, 91077770, 74197235, 5696362, 91459886),
                List.of(56105195, 82479378, 45937951, 52817583, 2768114, 43329099, 28189138, 21418604)
        )));

//        [[69438,55243,0,43779,5241,93591,73380],
//        [847,49990,53242,21837,89404,63929,48214],
//        [90332,49751,0,3088,16374,70121,25385],
//        [14694,4338,87873,86281,5204,84169,5024],
//        [31711,47313,1885,28332,11646,42583,31460],
//        [59845,94855,29286,53221,9803,41305,60749],
//        [95077,50343,27947,92852,0,0,19731],
//        [86158,63553,56822,90251,0,23826,17478],
//        [60387,23279,78048,78835,5310,99720,0],
//        [74799,48845,60658,29773,96129,90443,14391],
//        [65448,63358,78089,93914,7931,68804,72633],
//        [93431,90868,55280,30860,59354,62083,47669],
//        [81064,93220,22386,22341,95485,20696,13436],
//        [50083,0,89399,43882,0,13593,27847],
//        [0,12256,33652,69301,73395,93440,0],
//        [42818,87197,81249,33936,7027,5744,64710],
//        [35843,0,99746,52442,17494,49407,63016],
//        [86042,44524,0,0,26787,97651,28572],
//        [54183,83466,96754,89861,84143,13413,72921],
//        [89405,52305,39907,27366,14603,0,14104],
//        [70909,61104,70236,30365,0,30944,98378],
//        [20124,87188,6515,98319,78146,99325,88919],
//        [89669,0,64218,85795,2449,48939,12869],
//        [93539,28909,90973,77642,0,72170,98359],
//        [88628,16422,80512,0,38651,50854,55768],
//        [13639,2889,74835,80416,26051,78859,25721],
//        [90182,23154,16586,0,27459,3272,84893],
//        [2480,33654,87321,93272,93079,0,38394],
//        [34676,72427,95024,12240,72012,0,57763],
//        [97957,56,83817,45472,0,24087,90245],
//        [32056,0,92049,21380,4980,38458,3490],
//        [21509,76628,0,90430,10113,76264,45840],
//        [97192,58807,74165,65921,45726,47265,56084],
//        [16276,27751,37985,47944,54895,80706,2372],
//        [28438,53073,0,67255,38416,63354,69262],
//        [23926,75497,91347,58436,73946,39565,10841],
//        [34372,69647,44093,62680,32424,69858,68719],
//        [24425,4014,94871,1031,99852,88692,31503],
//        [24475,12295,33326,37771,37883,74568,25163],
//        [0,18411,88185,60924,29028,69789,0],
//        [34697,75631,7636,16190,60178,39082,7052],
//        [24876,9570,53630,98605,22331,79320,88317],
//        [27204,89103,15221,91346,35428,94251,62745],
//        [26636,28759,12998,58412,38113,14678,0],
//        [80871,79706,45325,3861,12504,0,4872],
//        [79662,15626,995,80546,64775,0,68820],
//        [25160,82123,81706,21494,92958,33594,5243]]
        System.out.println(solution.cutOffTree(List.of(
                List.of(69438, 55243, 0, 43779, 5241, 93591, 73380),
                List.of(847, 49990, 53242, 21837, 89404, 63929, 48214),
                List.of(90332, 49751, 0, 3088, 16374, 70121, 25385),
                List.of(14694, 4338, 87873, 86281, 5204, 84169, 5024),
                List.of(31711, 47313, 1885, 28332, 11646, 42583, 31460),
                List.of(59845, 94855, 29286, 53221, 9803, 41305, 60749),
                List.of(95077, 50343, 27947, 92852, 0, 0, 19731),
                List.of(86158, 63553, 56822, 90251, 0, 23826, 17478),
                List.of(60387, 23279, 78048, 78835, 5310, 99720, 0),
                List.of(74799, 48845, 60658, 29773, 96129, 90443, 14391),
                List.of(65448, 63358, 78089, 93914, 7931, 68804, 72633),
                List.of(93431, 90868, 55280, 30860, 59354, 62083, 47669),
                List.of(81064, 93220, 22386, 22341, 95485, 20696, 13436),
                List.of(50083, 0, 89399, 43882, 0, 13593, 27847),
                List.of(0, 12256, 33652, 69301, 73395, 93440, 0),
                List.of(42818, 87197, 81249, 33936, 7027, 5744, 64710),
                List.of(35843, 0, 99746, 52442, 17494, 49407, 63016),
                List.of(86042, 44524, 0, 0, 26787, 97651, 28572),
                List.of(54183, 83466, 96754, 89861, 84143, 13413, 72921),
                List.of(89405, 52305, 39907, 27366, 14603, 0, 14104),
                List.of(70909, 61104, 70236, 30365, 0, 30944, 98378),
                List.of(20124, 87188, 6515, 98319, 78146, 99325, 88919),
                List.of(89669, 0, 64218, 85795, 2449, 48939, 12869),
                List.of(93539, 28909, 90973, 77642, 0, 72170, 98359),
                List.of(88628, 16422, 80512, 0, 38651, 50854, 55768),
                List.of(13639, 2889, 74835, 80416, 26051, 78859, 25721),
                List.of(90182, 23154, 16586, 0, 27459, 3272, 84893),
                List.of(2480, 33654, 87321, 93272, 93079, 0, 38394),
                List.of(34676, 72427, 95024, 12240, 72012, 0, 57763),
                List.of(97957, 56, 83817, 45472, 0, 24087, 90245),
                List.of(32056, 0, 92049, 21380, 4980, 38458, 3490),
                List.of(21509, 76628, 0, 90430, 10113, 76264, 45840),
                List.of(97192, 58807, 74165, 65921, 45726, 47265, 56084),
                List.of(16276, 27751, 37985, 47944, 54895, 80706, 2372),
                List.of(28438, 53073, 0, 67255, 38416, 63354, 69262),
                List.of(23926, 75497, 91347, 58436, 73946, 39565, 10841),
                List.of(34372, 69647, 44093, 62680, 32424, 69858, 68719),
                List.of(24425, 4014, 94871, 1031, 99852, 88692, 31503),
                List.of(24475, 12295, 33326, 37771, 37883, 74568, 25163),
                List.of(0, 18411, 88185, 60924, 29028, 69789, 0),
                List.of(34697, 75631, 7636, 16190, 60178, 39082, 7052),
                List.of(24876, 9570, 53630, 98605, 22331, 79320, 88317),
                List.of(27204, 89103, 15221, 91346, 35428, 94251, 62745),
                List.of(26636, 28759, 12998, 58412, 38113, 14678, 0),
                List.of(80871, 79706, 45325, 3861, 12504, 0, 4872),
                List.of(79662, 15626, 995, 80546, 64775, 0, 68820),
                List.of(25160, 82123, 81706, 21494, 92958, 33594, 5243)
        )));

//        [[3597103,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65,67,69,71,73,75,77,79,81,83,85,87,89,91,93,95,97,99],
//        [101,103,105,107,109,111,113,115,117,119,121,123,125,127,129,131,133,135,137,139,141,143,145,147,149,151,153,155,157,159,161,163,165,167,169,171,173,175,177,179,181,183,185,187,189,191,193,195,197,199],
//        [201,203,205,207,209,211,213,215,217,219,221,223,225,227,229,231,233,235,237,239,241,243,245,247,249,251,253,255,257,259,261,263,265,267,269,271,273,275,277,279,281,283,285,287,289,291,293,295,297,299],
//        [301,303,305,307,309,311,313,315,317,319,321,323,325,327,329,331,333,335,337,339,341,343,345,347,349,351,353,355,357,359,361,363,365,367,369,371,373,375,377,379,381,383,385,387,389,391,393,395,397,399],
//        [401,403,405,407,409,411,413,415,417,419,421,423,425,427,429,431,433,435,437,439,441,443,445,447,449,451,453,455,457,459,461,463,465,467,469,471,473,475,477,479,481,483,485,487,489,491,493,495,497,499],
//        [501,503,505,507,509,511,513,515,517,519,521,523,525,527,529,531,533,535,537,539,541,543,545,547,549,551,553,555,557,559,561,563,565,567,569,571,573,575,577,579,581,583,585,587,589,591,593,595,597,599],
//        [601,603,605,607,609,611,613,615,617,619,621,623,625,627,629,631,633,635,637,639,641,643,645,647,649,651,653,655,657,659,661,663,665,667,669,671,673,675,677,679,681,683,685,687,689,691,693,695,697,699],
//        [701,703,705,707,709,711,713,715,717,719,721,723,725,727,729,731,733,735,737,739,741,743,745,747,749,751,753,755,757,759,761,763,765,767,769,771,773,775,777,779,781,783,785,787,789,791,793,795,797,799],
//        [801,803,805,807,809,811,813,815,817,819,821,823,825,827,829,831,833,835,837,839,841,843,845,847,849,851,853,855,857,859,861,863,865,867,869,871,873,875,877,879,881,883,885,887,889,891,893,895,897,899],
//        [901,903,905,907,909,911,913,915,917,919,921,923,925,927,929,931,933,935,937,939,941,943,945,947,949,951,953,955,957,959,961,963,965,967,969,971,973,975,977,979,981,983,985,987,989,991,993,995,997,999],
//        [1001,1003,1005,1007,1009,1011,1013,1015,1017,1019,1021,1023,1025,1027,1029,1031,1033,1035,1037,1039,1041,1043,1045,1047,1049,1051,1053,1055,1057,1059,1061,1063,1065,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,1095,1097,1099],
//        [1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,1129,1131,1133,1135,1137,1139,1141,1143,1145,1147,1149,1151,1153,1155,1157,1159,1161,1163,1165,1167,1169,1171,1173,1175,1177,1179,1181,1183,1185,1187,1189,1191,1193,1195,1197,1199],
//        [1201,1203,1205,1207,1209,1211,1213,1215,1217,1219,1221,1223,1225,1227,1229,1231,1233,1235,1237,1239,1241,1243,1245,1247,1249,1251,1253,1255,1257,1259,1261,1263,1265,1267,1269,1271,1273,1275,1277,1279,1281,1283,1285,1287,1289,1291,1293,1295,1297,1299],
//        [1301,1303,1305,1307,1309,1311,1313,1315,1317,1319,1321,1323,1325,1327,1329,1331,1333,1335,1337,1339,1341,1343,1345,1347,1349,1351,1353,1355,1357,1359,1361,1363,1365,1367,1369,1371,1373,1375,1377,1379,1381,1383,1385,1387,1389,1391,1393,1395,1397,1399],
//        [1401,1403,1405,1407,1409,1411,1413,1415,1417,1419,1421,1423,1425,1427,1429,1431,1433,1435,1437,1439,1441,1443,1445,1447,1449,1451,1453,1455,1457,1459,1461,1463,1465,1467,1469,1471,1473,1475,1477,1479,1481,1483,1485,1487,1489,1491,1493,1495,1497,1499],
//        [1501,1503,1505,1507,1509,1511,1513,1515,1517,1519,1521,1523,1525,1527,1529,1531,1533,1535,1537,1539,1541,1543,1545,1547,1549,1551,1553,1555,1557,1559,1561,1563,1565,1567,1569,1571,1573,1575,1577,1579,1581,1583,1585,1587,1589,1591,1593,1595,1597,1599],
//        [1601,1603,1605,1607,1609,1611,1613,1615,1617,1619,1621,1623,1625,1627,1629,1631,1633,1635,1637,1639,1641,1643,1645,1647,1649,1651,1653,1655,1657,1659,1661,1663,1665,1667,1669,1671,1673,1675,1677,1679,1681,1683,1685,1687,1689,1691,1693,1695,1697,1699],
//        [1701,1703,1705,1707,1709,1711,1713,1715,1717,1719,1721,1723,1725,1727,1729,1731,1733,1735,1737,1739,1741,1743,1745,1747,1749,1751,1753,1755,1757,1759,1761,1763,1765,1767,1769,1771,1773,1775,1777,1779,1781,1783,1785,1787,1789,1791,1793,1795,1797,1799],
//        [1801,1803,1805,1807,1809,1811,1813,1815,1817,1819,1821,1823,1825,1827,1829,1831,1833,1835,1837,1839,1841,1843,1845,1847,1849,1851,1853,1855,1857,1859,1861,1863,1865,1867,1869,1871,1873,1875,1877,1879,1881,1883,1885,1887,1889,1891,1893,1895,1897,1899],
//        [1901,1903,1905,1907,1909,1911,1913,1915,1917,1919,1921,1923,1925,1927,1929,1931,1933,1935,1937,1939,1941,1943,1945,1947,1949,1951,1953,1955,1957,1959,1961,1963,1965,1967,1969,1971,1973,1975,1977,1979,1981,1983,1985,1987,1989,1991,1993,1995,1997,1999],
//        [2001,2003,2005,2007,2009,2011,2013,2015,2017,2019,2021,2023,2025,2027,2029,2031,2033,2035,2037,2039,2041,2043,2045,2047,2049,2051,2053,2055,2057,2059,2061,2063,2065,2067,2069,2071,2073,2075,2077,2079,2081,2083,2085,2087,2089,2091,2093,2095,2097,2099],
//        [2101,2103,2105,2107,2109,2111,2113,2115,2117,2119,2121,2123,2125,2127,2129,2131,2133,2135,2137,2139,2141,2143,2145,2147,2149,2151,2153,2155,2157,2159,2161,2163,2165,2167,2169,2171,2173,2175,2177,2179,2181,2183,2185,2187,2189,2191,2193,2195,2197,2199],
//        [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1109716],
//        [28009212,56398292,14350816,38327240,88225670,29386037,66710259,79627283,95281552,88557665,75249718,41199200,27648730,85799624,2899628,50470597,63402698,57754285,53004700,99882330,93301360,47951815,15621486,60607075,47818352,79744078,97160482,32701435,57066205,33752824,87181276,2543281,7897643,17399760,3410588,37879792,26501880,1751848,56355115,83041740,6799368,54840464,26862561,15107600,70066760,27580128,5217414,61819656,30166963,79212380],
//        [92187444,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
//        [91393931,92687716,77110826,2823546,27262485,853391,1462338,71862890,98076327,63567712,16726636,94614400,55226550,25370250,13521552,84113872,20500240,924320,1523205,39880719,22653774,13532814,54030537,53854120,1648912,33705012,44353236,28525527,70151745,41943535,63768790,36475802,94803887,4933040,17570955,46890767,5741232,35920320,946330,49658464,98386988,2473342,13375077,12920518,60128040,17509300,2632395,21996119,56053616,90695296],
//        [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,45979970],
//        [98879820,35132432,18264556,31205995,23075280,13619668,43114165,21553020,76549547,87103042,24881040,36824136,22861553,3826964,64319056,56807973,35054488,89872183,4735196,63597804,7512508,42551248,76187562,63045720,73130571,3469400,41845130,39479322,76755545,42881278,84852693,34962929,49192028,12467004,18397456,66718837,21947266,7852972,60647248,52658596,5585796,68519114,61177929,72276458,3569040,72854320,40614139,34106952,1484565,17456319],
//        [2102,2104,2106,2108,2110,2112,2114,2116,2118,2120,2122,2124,2126,2128,2130,2132,2134,2136,2138,2140,2142,2144,2146,2148,2150,2152,2154,2156,2158,2160,2162,2164,2166,2168,2170,2172,2174,2176,2178,2180,2182,2184,2186,2188,2190,2192,2194,2196,2198,2200],
//        [2002,2004,2006,2008,2010,2012,2014,2016,2018,2020,2022,2024,2026,2028,2030,2032,2034,2036,2038,2040,2042,2044,2046,2048,2050,2052,2054,2056,2058,2060,2062,2064,2066,2068,2070,2072,2074,2076,2078,2080,2082,2084,2086,2088,2090,2092,2094,2096,2098,2100],
//        [1902,1904,1906,1908,1910,1912,1914,1916,1918,1920,1922,1924,1926,1928,1930,1932,1934,1936,1938,1940,1942,1944,1946,1948,1950,1952,1954,1956,1958,1960,1962,1964,1966,1968,1970,1972,1974,1976,1978,1980,1982,1984,1986,1988,1990,1992,1994,1996,1998,2000],
//        [1802,1804,1806,1808,1810,1812,1814,1816,1818,1820,1822,1824,1826,1828,1830,1832,1834,1836,1838,1840,1842,1844,1846,1848,1850,1852,1854,1856,1858,1860,1862,1864,1866,1868,1870,1872,1874,1876,1878,1880,1882,1884,1886,1888,1890,1892,1894,1896,1898,1900],
//        [1702,1704,1706,1708,1710,1712,1714,1716,1718,1720,1722,1724,1726,1728,1730,1732,1734,1736,1738,1740,1742,1744,1746,1748,1750,1752,1754,1756,1758,1760,1762,1764,1766,1768,1770,1772,1774,1776,1778,1780,1782,1784,1786,1788,1790,1792,1794,1796,1798,1800],
//        [1602,1604,1606,1608,1610,1612,1614,1616,1618,1620,1622,1624,1626,1628,1630,1632,1634,1636,1638,1640,1642,1644,1646,1648,1650,1652,1654,1656,1658,1660,1662,1664,1666,1668,1670,1672,1674,1676,1678,1680,1682,1684,1686,1688,1690,1692,1694,1696,1698,1700],
//        [1502,1504,1506,1508,1510,1512,1514,1516,1518,1520,1522,1524,1526,1528,1530,1532,1534,1536,1538,1540,1542,1544,1546,1548,1550,1552,1554,1556,1558,1560,1562,1564,1566,1568,1570,1572,1574,1576,1578,1580,1582,1584,1586,1588,1590,1592,1594,1596,1598,1600],
//        [1402,1404,1406,1408,1410,1412,1414,1416,1418,1420,1422,1424,1426,1428,1430,1432,1434,1436,1438,1440,1442,1444,1446,1448,1450,1452,1454,1456,1458,1460,1462,1464,1466,1468,1470,1472,1474,1476,1478,1480,1482,1484,1486,1488,1490,1492,1494,1496,1498,1500],
//        [1302,1304,1306,1308,1310,1312,1314,1316,1318,1320,1322,1324,1326,1328,1330,1332,1334,1336,1338,1340,1342,1344,1346,1348,1350,1352,1354,1356,1358,1360,1362,1364,1366,1368,1370,1372,1374,1376,1378,1380,1382,1384,1386,1388,1390,1392,1394,1396,1398,1400],
//        [1202,1204,1206,1208,1210,1212,1214,1216,1218,1220,1222,1224,1226,1228,1230,1232,1234,1236,1238,1240,1242,1244,1246,1248,1250,1252,1254,1256,1258,1260,1262,1264,1266,1268,1270,1272,1274,1276,1278,1280,1282,1284,1286,1288,1290,1292,1294,1296,1298,1300],
//        [1102,1104,1106,1108,1110,1112,1114,1116,1118,1120,1122,1124,1126,1128,1130,1132,1134,1136,1138,1140,1142,1144,1146,1148,1150,1152,1154,1156,1158,1160,1162,1164,1166,1168,1170,1172,1174,1176,1178,1180,1182,1184,1186,1188,1190,1192,1194,1196,1198,1200],
//        [1002,1004,1006,1008,1010,1012,1014,1016,1018,1020,1022,1024,1026,1028,1030,1032,1034,1036,1038,1040,1042,1044,1046,1048,1050,1052,1054,1056,1058,1060,1062,1064,1066,1068,1070,1072,1074,1076,1078,1080,1082,1084,1086,1088,1090,1092,1094,1096,1098,1100],
//        [902,904,906,908,910,912,914,916,918,920,922,924,926,928,930,932,934,936,938,940,942,944,946,948,950,952,954,956,958,960,962,964,966,968,970,972,974,976,978,980,982,984,986,988,990,992,994,996,998,1000],
//        [802,804,806,808,810,812,814,816,818,820,822,824,826,828,830,832,834,836,838,840,842,844,846,848,850,852,854,856,858,860,862,864,866,868,870,872,874,876,878,880,882,884,886,888,890,892,894,896,898,900],
//        [702,704,706,708,710,712,714,716,718,720,722,724,726,728,730,732,734,736,738,740,742,744,746,748,750,752,754,756,758,760,762,764,766,768,770,772,774,776,778,780,782,784,786,788,790,792,794,796,798,800],
//        [602,604,606,608,610,612,614,616,618,620,622,624,626,628,630,632,634,636,638,640,642,644,646,648,650,652,654,656,658,660,662,664,666,668,670,672,674,676,678,680,682,684,686,688,690,692,694,696,698,700],
//        [502,504,506,508,510,512,514,516,518,520,522,524,526,528,530,532,534,536,538,540,542,544,546,548,550,552,554,556,558,560,562,564,566,568,570,572,574,576,578,580,582,584,586,588,590,592,594,596,598,600],
//        [402,404,406,408,410,412,414,416,418,420,422,424,426,428,430,432,434,436,438,440,442,444,446,448,450,452,454,456,458,460,462,464,466,468,470,472,474,476,478,480,482,484,486,488,490,492,494,496,498,500],
//        [302,304,306,308,310,312,314,316,318,320,322,324,326,328,330,332,334,336,338,340,342,344,346,348,350,352,354,356,358,360,362,364,366,368,370,372,374,376,378,380,382,384,386,388,390,392,394,396,398,400],
//        [202,204,206,208,210,212,214,216,218,220,222,224,226,228,230,232,234,236,238,240,242,244,246,248,250,252,254,256,258,260,262,264,266,268,270,272,274,276,278,280,282,284,286,288,290,292,294,296,298,300],
//        [102,104,106,108,110,112,114,116,118,120,122,124,126,128,130,132,134,136,138,140,142,144,146,148,150,152,154,156,158,160,162,164,166,168,170,172,174,176,178,180,182,184,186,188,190,192,194,196,198,200],
//        [2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,94,96,98,100]]
        long start = System.currentTimeMillis();
        System.out.println(solution.cutOffTree(List.of(
                List.of(3597103, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99),
                List.of(101, 103, 105, 107, 109, 111, 113, 115, 117, 119, 121, 123, 125, 127, 129, 131, 133, 135, 137, 139, 141, 143, 145, 147, 149, 151, 153, 155, 157, 159, 161, 163, 165, 167, 169, 171, 173, 175, 177, 179, 181, 183, 185, 187, 189, 191, 193, 195, 197, 199),
                List.of(201, 203, 205, 207, 209, 211, 213, 215, 217, 219, 221, 223, 225, 227, 229, 231, 233, 235, 237, 239, 241, 243, 245, 247, 249, 251, 253, 255, 257, 259, 261, 263, 265, 267, 269, 271, 273, 275, 277, 279, 281, 283, 285, 287, 289, 291, 293, 295, 297, 299),
                List.of(301, 303, 305, 307, 309, 311, 313, 315, 317, 319, 321, 323, 325, 327, 329, 331, 333, 335, 337, 339, 341, 343, 345, 347, 349, 351, 353, 355, 357, 359, 361, 363, 365, 367, 369, 371, 373, 375, 377, 379, 381, 383, 385, 387, 389, 391, 393, 395, 397, 399),
                List.of(401, 403, 405, 407, 409, 411, 413, 415, 417, 419, 421, 423, 425, 427, 429, 431, 433, 435, 437, 439, 441, 443, 445, 447, 449, 451, 453, 455, 457, 459, 461, 463, 465, 467, 469, 471, 473, 475, 477, 479, 481, 483, 485, 487, 489, 491, 493, 495, 497, 499),
                List.of(501, 503, 505, 507, 509, 511, 513, 515, 517, 519, 521, 523, 525, 527, 529, 531, 533, 535, 537, 539, 541, 543, 545, 547, 549, 551, 553, 555, 557, 559, 561, 563, 565, 567, 569, 571, 573, 575, 577, 579, 581, 583, 585, 587, 589, 591, 593, 595, 597, 599),
                List.of(601, 603, 605, 607, 609, 611, 613, 615, 617, 619, 621, 623, 625, 627, 629, 631, 633, 635, 637, 639, 641, 643, 645, 647, 649, 651, 653, 655, 657, 659, 661, 663, 665, 667, 669, 671, 673, 675, 677, 679, 681, 683, 685, 687, 689, 691, 693, 695, 697, 699),
                List.of(701, 703, 705, 707, 709, 711, 713, 715, 717, 719, 721, 723, 725, 727, 729, 731, 733, 735, 737, 739, 741, 743, 745, 747, 749, 751, 753, 755, 757, 759, 761, 763, 765, 767, 769, 771, 773, 775, 777, 779, 781, 783, 785, 787, 789, 791, 793, 795, 797, 799),
                List.of(801, 803, 805, 807, 809, 811, 813, 815, 817, 819, 821, 823, 825, 827, 829, 831, 833, 835, 837, 839, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861, 863, 865, 867, 869, 871, 873, 875, 877, 879, 881, 883, 885, 887, 889, 891, 893, 895, 897, 899),
                List.of(901, 903, 905, 907, 909, 911, 913, 915, 917, 919, 921, 923, 925, 927, 929, 931, 933, 935, 937, 939, 941, 943, 945, 947, 949, 951, 953, 955, 957, 959, 961, 963, 965, 967, 969, 971, 973, 975, 977, 979, 981, 983, 985, 987, 989, 991, 993, 995, 997, 999),
                List.of(1001, 1003, 1005, 1007, 1009, 1011, 1013, 1015, 1017, 1019, 1021, 1023, 1025, 1027, 1029, 1031, 1033, 1035, 1037, 1039, 1041, 1043, 1045, 1047, 1049, 1051, 1053, 1055, 1057, 1059, 1061, 1063, 1065, 1067, 1069, 1071, 1073, 1075, 1077, 1079, 1081, 1083, 1085, 1087, 1089, 1091, 1093, 1095, 1097, 1099),
                List.of(1101, 1103, 1105, 1107, 1109, 1111, 1113, 1115, 1117, 1119, 1121, 1123, 1125, 1127, 1129, 1131, 1133, 1135, 1137, 1139, 1141, 1143, 1145, 1147, 1149, 1151, 1153, 1155, 1157, 1159, 1161, 1163, 1165, 1167, 1169, 1171, 1173, 1175, 1177, 1179, 1181, 1183, 1185, 1187, 1189, 1191, 1193, 1195, 1197, 1199),
                List.of(1201, 1203, 1205, 1207, 1209, 1211, 1213, 1215, 1217, 1219, 1221, 1223, 1225, 1227, 1229, 1231, 1233, 1235, 1237, 1239, 1241, 1243, 1245, 1247, 1249, 1251, 1253, 1255, 1257, 1259, 1261, 1263, 1265, 1267, 1269, 1271, 1273, 1275, 1277, 1279, 1281, 1283, 1285, 1287, 1289, 1291, 1293, 1295, 1297, 1299),
                List.of(1301, 1303, 1305, 1307, 1309, 1311, 1313, 1315, 1317, 1319, 1321, 1323, 1325, 1327, 1329, 1331, 1333, 1335, 1337, 1339, 1341, 1343, 1345, 1347, 1349, 1351, 1353, 1355, 1357, 1359, 1361, 1363, 1365, 1367, 1369, 1371, 1373, 1375, 1377, 1379, 1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399),
                List.of(1401, 1403, 1405, 1407, 1409, 1411, 1413, 1415, 1417, 1419, 1421, 1423, 1425, 1427, 1429, 1431, 1433, 1435, 1437, 1439, 1441, 1443, 1445, 1447, 1449, 1451, 1453, 1455, 1457, 1459, 1461, 1463, 1465, 1467, 1469, 1471, 1473, 1475, 1477, 1479, 1481, 1483, 1485, 1487, 1489, 1491, 1493, 1495, 1497, 1499),
                List.of(1501, 1503, 1505, 1507, 1509, 1511, 1513, 1515, 1517, 1519, 1521, 1523, 1525, 1527, 1529, 1531, 1533, 1535, 1537, 1539, 1541, 1543, 1545, 1547, 1549, 1551, 1553, 1555, 1557, 1559, 1561, 1563, 1565, 1567, 1569, 1571, 1573, 1575, 1577, 1579, 1581, 1583, 1585, 1587, 1589, 1591, 1593, 1595, 1597, 1599),
                List.of(1601, 1603, 1605, 1607, 1609, 1611, 1613, 1615, 1617, 1619, 1621, 1623, 1625, 1627, 1629, 1631, 1633, 1635, 1637, 1639, 1641, 1643, 1645, 1647, 1649, 1651, 1653, 1655, 1657, 1659, 1661, 1663, 1665, 1667, 1669, 1671, 1673, 1675, 1677, 1679, 1681, 1683, 1685, 1687, 1689, 1691, 1693, 1695, 1697, 1699),
                List.of(1701, 1703, 1705, 1707, 1709, 1711, 1713, 1715, 1717, 1719, 1721, 1723, 1725, 1727, 1729, 1731, 1733, 1735, 1737, 1739, 1741, 1743, 1745, 1747, 1749, 1751, 1753, 1755, 1757, 1759, 1761, 1763, 1765, 1767, 1769, 1771, 1773, 1775, 1777, 1779, 1781, 1783, 1785, 1787, 1789, 1791, 1793, 1795, 1797, 1799),
                List.of(1801, 1803, 1805, 1807, 1809, 1811, 1813, 1815, 1817, 1819, 1821, 1823, 1825, 1827, 1829, 1831, 1833, 1835, 1837, 1839, 1841, 1843, 1845, 1847, 1849, 1851, 1853, 1855, 1857, 1859, 1861, 1863, 1865, 1867, 1869, 1871, 1873, 1875, 1877, 1879, 1881, 1883, 1885, 1887, 1889, 1891, 1893, 1895, 1897, 1899),
                List.of(1901, 1903, 1905, 1907, 1909, 1911, 1913, 1915, 1917, 1919, 1921, 1923, 1925, 1927, 1929, 1931, 1933, 1935, 1937, 1939, 1941, 1943, 1945, 1947, 1949, 1951, 1953, 1955, 1957, 1959, 1961, 1963, 1965, 1967, 1969, 1971, 1973, 1975, 1977, 1979, 1981, 1983, 1985, 1987, 1989, 1991, 1993, 1995, 1997, 1999),
                List.of(2001, 2003, 2005, 2007, 2009, 2011, 2013, 2015, 2017, 2019, 2021, 2023, 2025, 2027, 2029, 2031, 2033, 2035, 2037, 2039, 2041, 2043, 2045, 2047, 2049, 2051, 2053, 2055, 2057, 2059, 2061, 2063, 2065, 2067, 2069, 2071, 2073, 2075, 2077, 2079, 2081, 2083, 2085, 2087, 2089, 2091, 2093, 2095, 2097, 2099),
                List.of(2101, 2103, 2105, 2107, 2109, 2111, 2113, 2115, 2117, 2119, 2121, 2123, 2125, 2127, 2129, 2131, 2133, 2135, 2137, 2139, 2141, 2143, 2145, 2147, 2149, 2151, 2153, 2155, 2157, 2159, 2161, 2163, 2165, 2167, 2169, 2171, 2173, 2175, 2177, 2179, 2181, 2183, 2185, 2187, 2189, 2191, 2193, 2195, 2197, 2199),
                List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1109716),
                List.of(28009212, 56398292, 14350816, 38327240, 88225670, 29386037, 66710259, 79627283, 95281552, 88557665, 75249718, 41199200, 27648730, 85799624, 2899628, 50470597, 63402698, 57754285, 53004700, 99882330, 93301360, 47951815, 15621486, 60607075, 47818352, 79744078, 97160482, 32701435, 57066205, 33752824, 87181276, 2543281, 7897643, 17399760, 3410588, 37879792, 26501880, 1751848, 56355115, 83041740, 6799368, 54840464, 26862561, 15107600, 70066760, 27580128, 5217414, 61819656, 30166963, 79212380),
                List.of(92187444, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                List.of(91393931, 92687716, 77110826, 2823546, 27262485, 853391, 1462338, 71862890, 98076327, 63567712, 16726636, 94614400, 55226550, 25370250, 13521552, 84113872, 20500240, 924320, 1523205, 39880719, 22653774, 13532814, 54030537, 53854120, 1648912, 33705012, 44353236, 28525527, 70151745, 41943535, 63768790, 36475802, 94803887, 4933040, 17570955, 46890767, 5741232, 35920320, 946330, 49658464, 98386988, 2473342, 13375077, 12920518, 60128040, 17509300, 2632395, 21996119, 56053616, 90695296),
                List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 45979970),
                List.of(98879820, 35132432, 18264556, 31205995, 23075280, 13619668, 43114165, 21553020, 76549547, 87103042, 24881040, 36824136, 22861553, 3826964, 64319056, 56807973, 35054488, 89872183, 4735196, 63597804, 7512508, 42551248, 76187562, 63045720, 73130571, 3469400, 41845130, 39479322, 76755545, 42881278, 84852693, 34962929, 49192028, 12467004, 18397456, 66718837, 21947266, 7852972, 60647248, 52658596, 5585796, 68519114, 61177929, 72276458, 3569040, 72854320, 40614139, 34106952, 1484565, 17456319),
                List.of(2102, 2104, 2106, 2108, 2110, 2112, 2114, 2116, 2118, 2120, 2122, 2124, 2126, 2128, 2130, 2132, 2134, 2136, 2138, 2140, 2142, 2144, 2146, 2148, 2150, 2152, 2154, 2156, 2158, 2160, 2162, 2164, 2166, 2168, 2170, 2172, 2174, 2176, 2178, 2180, 2182, 2184, 2186, 2188, 2190, 2192, 2194, 2196, 2198, 2200),
                List.of(2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022, 2024, 2026, 2028, 2030, 2032, 2034, 2036, 2038, 2040, 2042, 2044, 2046, 2048, 2050, 2052, 2054, 2056, 2058, 2060, 2062, 2064, 2066, 2068, 2070, 2072, 2074, 2076, 2078, 2080, 2082, 2084, 2086, 2088, 2090, 2092, 2094, 2096, 2098, 2100),
                List.of(1902, 1904, 1906, 1908, 1910, 1912, 1914, 1916, 1918, 1920, 1922, 1924, 1926, 1928, 1930, 1932, 1934, 1936, 1938, 1940, 1942, 1944, 1946, 1948, 1950, 1952, 1954, 1956, 1958, 1960, 1962, 1964, 1966, 1968, 1970, 1972, 1974, 1976, 1978, 1980, 1982, 1984, 1986, 1988, 1990, 1992, 1994, 1996, 1998, 2000),
                List.of(1802, 1804, 1806, 1808, 1810, 1812, 1814, 1816, 1818, 1820, 1822, 1824, 1826, 1828, 1830, 1832, 1834, 1836, 1838, 1840, 1842, 1844, 1846, 1848, 1850, 1852, 1854, 1856, 1858, 1860, 1862, 1864, 1866, 1868, 1870, 1872, 1874, 1876, 1878, 1880, 1882, 1884, 1886, 1888, 1890, 1892, 1894, 1896, 1898, 1900),
                List.of(1702, 1704, 1706, 1708, 1710, 1712, 1714, 1716, 1718, 1720, 1722, 1724, 1726, 1728, 1730, 1732, 1734, 1736, 1738, 1740, 1742, 1744, 1746, 1748, 1750, 1752, 1754, 1756, 1758, 1760, 1762, 1764, 1766, 1768, 1770, 1772, 1774, 1776, 1778, 1780, 1782, 1784, 1786, 1788, 1790, 1792, 1794, 1796, 1798, 1800),
                List.of(1602, 1604, 1606, 1608, 1610, 1612, 1614, 1616, 1618, 1620, 1622, 1624, 1626, 1628, 1630, 1632, 1634, 1636, 1638, 1640, 1642, 1644, 1646, 1648, 1650, 1652, 1654, 1656, 1658, 1660, 1662, 1664, 1666, 1668, 1670, 1672, 1674, 1676, 1678, 1680, 1682, 1684, 1686, 1688, 1690, 1692, 1694, 1696, 1698, 1700),
                List.of(1502, 1504, 1506, 1508, 1510, 1512, 1514, 1516, 1518, 1520, 1522, 1524, 1526, 1528, 1530, 1532, 1534, 1536, 1538, 1540, 1542, 1544, 1546, 1548, 1550, 1552, 1554, 1556, 1558, 1560, 1562, 1564, 1566, 1568, 1570, 1572, 1574, 1576, 1578, 1580, 1582, 1584, 1586, 1588, 1590, 1592, 1594, 1596, 1598, 1600),
                List.of(1402, 1404, 1406, 1408, 1410, 1412, 1414, 1416, 1418, 1420, 1422, 1424, 1426, 1428, 1430, 1432, 1434, 1436, 1438, 1440, 1442, 1444, 1446, 1448, 1450, 1452, 1454, 1456, 1458, 1460, 1462, 1464, 1466, 1468, 1470, 1472, 1474, 1476, 1478, 1480, 1482, 1484, 1486, 1488, 1490, 1492, 1494, 1496, 1498, 1500),
                List.of(1302, 1304, 1306, 1308, 1310, 1312, 1314, 1316, 1318, 1320, 1322, 1324, 1326, 1328, 1330, 1332, 1334, 1336, 1338, 1340, 1342, 1344, 1346, 1348, 1350, 1352, 1354, 1356, 1358, 1360, 1362, 1364, 1366, 1368, 1370, 1372, 1374, 1376, 1378, 1380, 1382, 1384, 1386, 1388, 1390, 1392, 1394, 1396, 1398, 1400),
                List.of(1202, 1204, 1206, 1208, 1210, 1212, 1214, 1216, 1218, 1220, 1222, 1224, 1226, 1228, 1230, 1232, 1234, 1236, 1238, 1240, 1242, 1244, 1246, 1248, 1250, 1252, 1254, 1256, 1258, 1260, 1262, 1264, 1266, 1268, 1270, 1272, 1274, 1276, 1278, 1280, 1282, 1284, 1286, 1288, 1290, 1292, 1294, 1296, 1298, 1300),
                List.of(1102, 1104, 1106, 1108, 1110, 1112, 1114, 1116, 1118, 1120, 1122, 1124, 1126, 1128, 1130, 1132, 1134, 1136, 1138, 1140, 1142, 1144, 1146, 1148, 1150, 1152, 1154, 1156, 1158, 1160, 1162, 1164, 1166, 1168, 1170, 1172, 1174, 1176, 1178, 1180, 1182, 1184, 1186, 1188, 1190, 1192, 1194, 1196, 1198, 1200),
                List.of(1002, 1004, 1006, 1008, 1010, 1012, 1014, 1016, 1018, 1020, 1022, 1024, 1026, 1028, 1030, 1032, 1034, 1036, 1038, 1040, 1042, 1044, 1046, 1048, 1050, 1052, 1054, 1056, 1058, 1060, 1062, 1064, 1066, 1068, 1070, 1072, 1074, 1076, 1078, 1080, 1082, 1084, 1086, 1088, 1090, 1092, 1094, 1096, 1098, 1100),
                List.of(902, 904, 906, 908, 910, 912, 914, 916, 918, 920, 922, 924, 926, 928, 930, 932, 934, 936, 938, 940, 942, 944, 946, 948, 950, 952, 954, 956, 958, 960, 962, 964, 966, 968, 970, 972, 974, 976, 978, 980, 982, 984, 986, 988, 990, 992, 994, 996, 998, 1000),
                List.of(802, 804, 806, 808, 810, 812, 814, 816, 818, 820, 822, 824, 826, 828, 830, 832, 834, 836, 838, 840, 842, 844, 846, 848, 850, 852, 854, 856, 858, 860, 862, 864, 866, 868, 870, 872, 874, 876, 878, 880, 882, 884, 886, 888, 890, 892, 894, 896, 898, 900),
                List.of(702, 704, 706, 708, 710, 712, 714, 716, 718, 720, 722, 724, 726, 728, 730, 732, 734, 736, 738, 740, 742, 744, 746, 748, 750, 752, 754, 756, 758, 760, 762, 764, 766, 768, 770, 772, 774, 776, 778, 780, 782, 784, 786, 788, 790, 792, 794, 796, 798, 800),
                List.of(602, 604, 606, 608, 610, 612, 614, 616, 618, 620, 622, 624, 626, 628, 630, 632, 634, 636, 638, 640, 642, 644, 646, 648, 650, 652, 654, 656, 658, 660, 662, 664, 666, 668, 670, 672, 674, 676, 678, 680, 682, 684, 686, 688, 690, 692, 694, 696, 698, 700),
                List.of(502, 504, 506, 508, 510, 512, 514, 516, 518, 520, 522, 524, 526, 528, 530, 532, 534, 536, 538, 540, 542, 544, 546, 548, 550, 552, 554, 556, 558, 560, 562, 564, 566, 568, 570, 572, 574, 576, 578, 580, 582, 584, 586, 588, 590, 592, 594, 596, 598, 600),
                List.of(402, 404, 406, 408, 410, 412, 414, 416, 418, 420, 422, 424, 426, 428, 430, 432, 434, 436, 438, 440, 442, 444, 446, 448, 450, 452, 454, 456, 458, 460, 462, 464, 466, 468, 470, 472, 474, 476, 478, 480, 482, 484, 486, 488, 490, 492, 494, 496, 498, 500),
                List.of(302, 304, 306, 308, 310, 312, 314, 316, 318, 320, 322, 324, 326, 328, 330, 332, 334, 336, 338, 340, 342, 344, 346, 348, 350, 352, 354, 356, 358, 360, 362, 364, 366, 368, 370, 372, 374, 376, 378, 380, 382, 384, 386, 388, 390, 392, 394, 396, 398, 400),
                List.of(202, 204, 206, 208, 210, 212, 214, 216, 218, 220, 222, 224, 226, 228, 230, 232, 234, 236, 238, 240, 242, 244, 246, 248, 250, 252, 254, 256, 258, 260, 262, 264, 266, 268, 270, 272, 274, 276, 278, 280, 282, 284, 286, 288, 290, 292, 294, 296, 298, 300),
                List.of(102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132, 134, 136, 138, 140, 142, 144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 182, 184, 186, 188, 190, 192, 194, 196, 198, 200),
                List.of(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100)
        )));

        System.out.println("time: " + (System.currentTimeMillis() - start));
    }
}