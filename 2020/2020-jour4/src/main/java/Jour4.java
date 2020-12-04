import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jour4 {

    private static final String input = "eyr:2028 iyr:2016 byr:1995 ecl:oth\n" +
            "pid:543685203 hcl:#c0946f\n" +
            "hgt:152cm\n" +
            "cid:252\n" +
            "\n" +
            "hcl:#733820 hgt:155cm\n" +
            "iyr:2013 byr:1989 pid:728471979\n" +
            "ecl:grn eyr:2022\n" +
            "\n" +
            "hgt:171cm\n" +
            "iyr:2013 pid:214368857 hcl:#cfa07d byr:1986 eyr:2028 ecl:grn\n" +
            "\n" +
            "hgt:167cm cid:210 ecl:brn pid:429131951 hcl:#cfa07d eyr:2029 iyr:2010\n" +
            "byr:1945\n" +
            "\n" +
            "hcl:#888785 iyr:2015\n" +
            "hgt:170cm pid:893805464 ecl:amb byr:1966 eyr:2028\n" +
            "\n" +
            "hgt:170cm ecl:amb\n" +
            "hcl:#c0946f eyr:2020 iyr:2016 pid:725010548\n" +
            "byr:1928\n" +
            "\n" +
            "byr:1999 hcl:#888785\n" +
            "eyr:2026\n" +
            "ecl:hzl\n" +
            "iyr:2016 hgt:193cm pid:170608679\n" +
            "\n" +
            "eyr:2024 iyr:2016 hcl:#cfa07d ecl:grn byr:2001 pid:391942873 cid:104 hgt:164cm\n" +
            "\n" +
            "iyr:2019\n" +
            "eyr:2025 pid:138912840 byr:1996\n" +
            "hgt:166cm\n" +
            "hcl:#888785 ecl:grn\n" +
            "\n" +
            "iyr:2023 hcl:a58381 pid:#401a29 eyr:1940\n" +
            "byr:1920\n" +
            "ecl:utc hgt:183cm\n" +
            "\n" +
            "pid:493510244 ecl:gry hgt:153cm byr:1950 cid:181 eyr:2028\n" +
            "hcl:#ceb3a1\n" +
            "iyr:2020\n" +
            "\n" +
            "iyr:2018 pid:074340974 hgt:182cm\n" +
            "hcl:#866857 byr:1988 ecl:hzl eyr:2023\n" +
            "\n" +
            "hcl:#866857 ecl:oth byr:1977 iyr:2014 hgt:180cm pid:860745884\n" +
            "eyr:2023\n" +
            "\n" +
            "eyr:2026 pid:815594641\n" +
            "ecl:gry iyr:2012 byr:1992 hgt:161cm hcl:#b6652a\n" +
            "\n" +
            "ecl:gry cid:338 eyr:2021 pid:777099878 hgt:193cm hcl:#efcc98\n" +
            "byr:1945\n" +
            "iyr:2015\n" +
            "\n" +
            "iyr:2016 byr:1934 hcl:#b6652a\n" +
            "hgt:162cm ecl:hzl\n" +
            "cid:296\n" +
            "pid:742610207\n" +
            "eyr:2022\n" +
            "\n" +
            "ecl:#ba3242\n" +
            "hgt:80 byr:1931\n" +
            "pid:550004054 iyr:1949 eyr:1944 hcl:fb3859\n" +
            "\n" +
            "ecl:amb eyr:2024\n" +
            "byr:1965 iyr:2010 pid:094059049\n" +
            "hcl:#fffffd\n" +
            "hgt:168cm\n" +
            "\n" +
            "pid:159cm\n" +
            "iyr:1923 eyr:2032 hcl:701107 cid:343 ecl:gmt byr:2010\n" +
            "hgt:177cm\n" +
            "\n" +
            "eyr:2021\n" +
            "ecl:grn byr:1991\n" +
            "hcl:#fffffd hgt:167cm pid:243218792 iyr:2019\n" +
            "\n" +
            "hgt:157cm byr:2017 ecl:grn iyr:2012\n" +
            "eyr:2030 hcl:#18171d pid:173cm\n" +
            "\n" +
            "pid:260101979 hgt:187cm eyr:2033 ecl:lzr\n" +
            "byr:2020 hcl:1058ce cid:133 iyr:2012\n" +
            "\n" +
            "hcl:#7d3b0c\n" +
            "pid:307828343 byr:2001\n" +
            "cid:317 iyr:2013\n" +
            "eyr:2029\n" +
            "\n" +
            "pid:472940417 eyr:1960\n" +
            "hgt:181cm hcl:#c0946f cid:269\n" +
            "byr:2014\n" +
            "iyr:1956\n" +
            "\n" +
            "hcl:#18171d eyr:2021 byr:2001 pid:421443124\n" +
            "ecl:brn iyr:2020 hgt:156cm\n" +
            "\n" +
            "cid:347 hgt:60in pid:359783692 byr:1932\n" +
            "ecl:hzl\n" +
            "eyr:2023\n" +
            "hcl:#888785 iyr:2019\n" +
            "\n" +
            "pid:230915137\n" +
            "byr:1999\n" +
            "iyr:2011 eyr:2020 hcl:#7d3b0c ecl:hzl\n" +
            "hgt:164cm\n" +
            "\n" +
            "iyr:1989\n" +
            "byr:2008\n" +
            "hgt:154cm\n" +
            "eyr:2028 pid:280298169\n" +
            "cid:208\n" +
            "ecl:oth\n" +
            "\n" +
            "byr:1954 iyr:2017\n" +
            "ecl:hzl\n" +
            "eyr:2026\n" +
            "pid:966957581 hgt:175cm hcl:#18171d\n" +
            "\n" +
            "pid:308053355 hgt:192cm eyr:2022 ecl:amb cid:146 iyr:2015\n" +
            "byr:1991 hcl:#c0946f\n" +
            "\n" +
            "hcl:#a97842 pid:244441133 iyr:2019\n" +
            "hgt:182cm\n" +
            "ecl:amb cid:172 byr:1973 eyr:2029\n" +
            "\n" +
            "iyr:2017\n" +
            "byr:1985 cid:215\n" +
            "ecl:blu hcl:#623a2f hgt:160cm pid:157856689 eyr:2030\n" +
            "\n" +
            "eyr:2027 ecl:#d72f9b hgt:162cm\n" +
            "iyr:2018 hcl:#a97842\n" +
            "byr:1945\n" +
            "pid:131243258\n" +
            "\n" +
            "hcl:#b3f2f0 pid:204254353 cid:169 eyr:2020\n" +
            "iyr:2013 hgt:172cm ecl:blu byr:1950\n" +
            "\n" +
            "byr:1957 hcl:#c0946f hgt:152cm ecl:blu eyr:2027 pid:325917033\n" +
            "iyr:2010\n" +
            "\n" +
            "ecl:oth byr:1950 hgt:166cm pid:007352351\n" +
            "hcl:#b6652a\n" +
            "iyr:2020\n" +
            "eyr:2024\n" +
            "\n" +
            "hgt:165 eyr:2030 iyr:2027\n" +
            "ecl:#1a34f1 pid:2894591864 byr:2024 hcl:z\n" +
            "\n" +
            "byr:1971 ecl:oth\n" +
            "hgt:163cm eyr:2021 pid:040443396\n" +
            "\n" +
            "hgt:177cm\n" +
            "byr:1955 pid:585735590 iyr:2010 ecl:grn eyr:2024\n" +
            "hcl:#602927\n" +
            "\n" +
            "cid:74\n" +
            "iyr:2010\n" +
            "pid:014378493 hgt:174cm eyr:2020\n" +
            "ecl:grn byr:1944\n" +
            "\n" +
            "pid:404141049\n" +
            "byr:1947 ecl:blu hgt:170cm iyr:2011\n" +
            "eyr:2028\n" +
            "hcl:#cfa07d\n" +
            "\n" +
            "ecl:hzl byr:1938 pid:235085606 cid:180 hcl:8fb74c eyr:2021 hgt:73 iyr:2015\n" +
            "\n" +
            "pid:860077423 ecl:gry\n" +
            "hcl:#3e845b\n" +
            "hgt:167cm byr:1933 iyr:2016 eyr:2021\n" +
            "\n" +
            "hcl:#733820 hgt:66in eyr:1920\n" +
            "ecl:oth byr:1941 pid:979460474 iyr:2010\n" +
            "cid:247\n" +
            "\n" +
            "hcl:#cfa07d ecl:#13bd36 hgt:193cm eyr:2027 pid:181cm byr:1952 iyr:1951\n" +
            "\n" +
            "ecl:brn hcl:#602927\n" +
            "hgt:161cm\n" +
            "eyr:2027 pid:822749462 byr:1946\n" +
            "iyr:2014\n" +
            "\n" +
            "byr:2013\n" +
            "iyr:2021 ecl:zzz eyr:2032 hgt:193in hcl:#a97842 pid:163cm\n" +
            "\n" +
            "eyr:2029 cid:140\n" +
            "byr:1984\n" +
            "iyr:2018 hgt:187cm hcl:#b6652a pid:910674579\n" +
            "\n" +
            "ecl:hzl hgt:173cm pid:096026282\n" +
            "iyr:2014 byr:1956\n" +
            "eyr:2029 hcl:#866857\n" +
            "\n" +
            "eyr:2024 iyr:2019 pid:301205967\n" +
            "cid:276 byr:1957 hcl:#3fec29 ecl:gry hgt:165cm\n" +
            "\n" +
            "iyr:2013 ecl:oth hgt:177cm hcl:#6b5442 eyr:2021 byr:1962 pid:006347857\n" +
            "\n" +
            "ecl:grt byr:1983 hcl:#cfa07d\n" +
            "hgt:163cm\n" +
            "eyr:1979\n" +
            "iyr:1958 pid:796395720\n" +
            "\n" +
            "iyr:2011 pid:415403544 hcl:#c0946f byr:1990 ecl:oth eyr:2023 hgt:73in\n" +
            "cid:107\n" +
            "\n" +
            "hgt:166cm eyr:2029 iyr:2015\n" +
            "hcl:#c0946f ecl:brn\n" +
            "byr:1964\n" +
            "pid:469449137\n" +
            "\n" +
            "eyr:2023\n" +
            "byr:1969 iyr:2010 hgt:163cm hcl:#a97842 pid:570942274\n" +
            "ecl:blu\n" +
            "\n" +
            "hcl:#623a2f\n" +
            "ecl:brn hgt:183cm pid:524675399\n" +
            "eyr:2020 iyr:2012 byr:1981\n" +
            "\n" +
            "iyr:2017 hcl:#fffffd eyr:2026\n" +
            "ecl:gry byr:1979 hgt:152cm pid:505790864\n" +
            "\n" +
            "hgt:68in\n" +
            "hcl:#c0946f iyr:2012\n" +
            "eyr:2023 pid:933562997 byr:1993\n" +
            "ecl:grn\n" +
            "\n" +
            "pid:267705171\n" +
            "hgt:166cm byr:1970 iyr:2019 hcl:#341e13 ecl:oth\n" +
            "eyr:2030\n" +
            "\n" +
            "ecl:brn byr:1972 eyr:2026 pid:774637408 hgt:189cm iyr:2015 hcl:#341e13\n" +
            "\n" +
            "hgt:175cm eyr:2026 byr:2001 iyr:2020\n" +
            "hcl:#733820 ecl:blu pid:686996160\n" +
            "\n" +
            "hgt:190cm hcl:#c0946f pid:228444464 byr:1987\n" +
            "iyr:2020 eyr:2030\n" +
            "ecl:blu\n" +
            "\n" +
            "byr:1990 hgt:179cm\n" +
            "pid:885359438 eyr:2028 iyr:2010 ecl:amb\n" +
            "hcl:#67067e\n" +
            "\n" +
            "byr:1945 hcl:#866857 eyr:2022 iyr:2019\n" +
            "pid:708146656 cid:65\n" +
            "hgt:172cm ecl:brn\n" +
            "\n" +
            "ecl:hzl hgt:191cm\n" +
            "cid:260 pid:010716679 iyr:2011 eyr:2029 byr:1920 hcl:#efcc98\n" +
            "\n" +
            "iyr:2012\n" +
            "cid:313 pid:264894705 byr:1951 hcl:#733820 eyr:2030 ecl:blu\n" +
            "hgt:178cm\n" +
            "\n" +
            "eyr:2027 pid:790510379\n" +
            "iyr:2013\n" +
            "ecl:amb\n" +
            "hgt:186cm\n" +
            "hcl:#866857\n" +
            "byr:1926\n" +
            "\n" +
            "pid:535750794 hgt:191cm iyr:2016 hcl:#a97842 eyr:2029\n" +
            "ecl:hzl byr:1923\n" +
            "\n" +
            "byr:2023 pid:#eb4c2a iyr:1939 ecl:grn hcl:06d729 hgt:73 eyr:2038\n" +
            "\n" +
            "pid:792365221 iyr:2013 ecl:oth\n" +
            "byr:1997\n" +
            "hgt:170cm hcl:#efcc98\n" +
            "eyr:2022\n" +
            "\n" +
            "hgt:192cm pid:874141668\n" +
            "byr:1957 iyr:2015\n" +
            "ecl:gry\n" +
            "\n" +
            "hcl:#b6652a pid:770238761 eyr:2029 byr:1934 iyr:2013\n" +
            "ecl:blu cid:177\n" +
            "hgt:184cm\n" +
            "\n" +
            "ecl:hzl eyr:2024 hgt:72in pid:546439165\n" +
            "iyr:2013\n" +
            "hcl:#c0946f cid:223 byr:1989\n" +
            "\n" +
            "byr:1985\n" +
            "ecl:utc pid:#ff1cbf\n" +
            "iyr:2018 hcl:#866857 hgt:169cm eyr:2026 cid:194\n" +
            "\n" +
            "hgt:189cm\n" +
            "eyr:2026 pid:120642045 ecl:blu\n" +
            "hcl:#602927 cid:177\n" +
            "byr:1954 iyr:2012\n" +
            "\n" +
            "pid:314624973\n" +
            "byr:1959 iyr:2015 hcl:#c0946f ecl:grn\n" +
            "eyr:2027 cid:349 hgt:156cm\n" +
            "\n" +
            "byr:1978\n" +
            "iyr:2020 hgt:150cm cid:266 eyr:2026\n" +
            "pid:443912835 hcl:#b6652a\n" +
            "\n" +
            "hgt:174cm byr:1974 pid:729198828\n" +
            "ecl:brn iyr:2014\n" +
            "hcl:#18171d eyr:2027\n" +
            "\n" +
            "pid:472891001 ecl:xry\n" +
            "hgt:96 hcl:1b816a iyr:1954\n" +
            "byr:2015 eyr:2037\n" +
            "\n" +
            "byr:1966 eyr:2022\n" +
            "iyr:2014\n" +
            "pid:848187688 hcl:#602927 ecl:gry hgt:152cm\n" +
            "\n" +
            "hgt:129 eyr:2037 cid:61 iyr:2009 byr:2027 hcl:#c0946f\n" +
            "pid:3569865\n" +
            "ecl:#4e3d72\n" +
            "\n" +
            "ecl:gry\n" +
            "eyr:2021 pid:234525998 byr:1964 hgt:168cm cid:140\n" +
            "hcl:#7d3b0c iyr:2013\n" +
            "\n" +
            "ecl:xry\n" +
            "cid:86\n" +
            "hgt:172in\n" +
            "byr:1972\n" +
            "iyr:2015 hcl:#7d3b0c pid:833809421 eyr:2030\n" +
            "\n" +
            "pid:444365280 hgt:72in\n" +
            "ecl:brn\n" +
            "hcl:#b6652a byr:1985 eyr:2027 iyr:2012\n" +
            "\n" +
            "iyr:2010 byr:2013 hgt:181cm eyr:2021\n" +
            "pid:072317444\n" +
            "ecl:oth hcl:#866857\n" +
            "cid:118\n" +
            "\n" +
            "pid:4354408888 iyr:2012\n" +
            "hcl:#b6652a cid:104\n" +
            "hgt:96 eyr:2020\n" +
            "byr:1933 ecl:amb\n" +
            "\n" +
            "eyr:2023 ecl:gry hcl:#a97842 pid:287719484 byr:1994\n" +
            "iyr:2011 hgt:163cm cid:299\n" +
            "\n" +
            "byr:1932\n" +
            "hgt:170cm\n" +
            "iyr:2014 pid:777844412 eyr:2040 hcl:#cfa07d ecl:brn\n" +
            "\n" +
            "cid:160 hgt:191cm eyr:2020 iyr:2012\n" +
            "ecl:brn byr:1981 pid:077027782\n" +
            "\n" +
            "cid:182 hgt:176cm hcl:#7d3b0c\n" +
            "eyr:2030 ecl:blu pid:096742425 iyr:2010 byr:1963\n" +
            "\n" +
            "byr:2010 cid:337 hcl:z pid:525126586 iyr:2010 hgt:73cm eyr:2040 ecl:blu\n" +
            "\n" +
            "ecl:gry\n" +
            "iyr:2017\n" +
            "hgt:185cm hcl:#6b5442 byr:1993\n" +
            "eyr:2029 pid:366083139 cid:343\n" +
            "\n" +
            "eyr:2028 ecl:amb\n" +
            "pid:878658841 byr:1960 hgt:179cm hcl:#18171d iyr:2010\n" +
            "\n" +
            "pid:537309261 iyr:2015 hgt:187cm\n" +
            "hcl:#4fe831 eyr:2026\n" +
            "ecl:blu byr:1982\n" +
            "\n" +
            "ecl:brn hgt:163cm\n" +
            "eyr:2021 hcl:#6b5442 byr:1979 iyr:2013 pid:924759517\n" +
            "\n" +
            "pid:683651053 hcl:#179c55\n" +
            "ecl:blu byr:1989 hgt:190cm\n" +
            "iyr:2016\n" +
            "eyr:2030\n" +
            "\n" +
            "ecl:grn\n" +
            "iyr:2016 hcl:#b6652a\n" +
            "byr:1994 eyr:2020 pid:448424292 hgt:174cm\n" +
            "\n" +
            "hgt:157cm\n" +
            "ecl:grn\n" +
            "byr:2000\n" +
            "pid:734707993 hcl:#341e13 iyr:2020\n" +
            "\n" +
            "hcl:#341e13 hgt:156cm iyr:2020 pid:299213638\n" +
            "byr:1947 ecl:hzl eyr:2023\n" +
            "\n" +
            "hgt:193cm hcl:#b6652a iyr:2014 ecl:hzl byr:1947 eyr:2025\n" +
            "pid:044486467\n" +
            "\n" +
            "byr:1975\n" +
            "hgt:159cm\n" +
            "ecl:grn pid:318489576 eyr:2029 hcl:#6b5442\n" +
            "iyr:2020\n" +
            "\n" +
            "iyr:2018 pid:512971930\n" +
            "hcl:#888785 byr:1966 eyr:2024 hgt:158cm\n" +
            "cid:100 ecl:gry\n" +
            "\n" +
            "ecl:amb eyr:2030 hgt:171cm hcl:#efcc98 pid:800921581 cid:339 byr:1980 iyr:2017\n" +
            "\n" +
            "iyr:2019 cid:172\n" +
            "hgt:152cm\n" +
            "eyr:2022 ecl:oth hcl:#602927 byr:1960\n" +
            "\n" +
            "iyr:2019 pid:762312913\n" +
            "eyr:2029\n" +
            "ecl:hzl\n" +
            "hcl:#6b5442\n" +
            "byr:1940\n" +
            "hgt:169cm cid:289\n" +
            "\n" +
            "eyr:2022 ecl:gry byr:1976\n" +
            "iyr:2020 hcl:#733820 hgt:172cm pid:040331561\n" +
            "\n" +
            "hgt:171cm ecl:brn iyr:2013 eyr:2027 byr:1940 hcl:#a6e32a pid:223986941\n" +
            "\n" +
            "hcl:#341e13\n" +
            "eyr:2028 ecl:amb byr:1942\n" +
            "hgt:166cm pid:435382099 iyr:2020\n" +
            "\n" +
            "cid:298 pid:641326891\n" +
            "hgt:155cm hcl:#623a2f ecl:grn byr:1981 eyr:2025\n" +
            "iyr:2010\n" +
            "\n" +
            "iyr:2015 pid:472000322 eyr:2021 byr:1977\n" +
            "ecl:gry hgt:165cm cid:270\n" +
            "\n" +
            "eyr:2027 byr:1956\n" +
            "pid:193087729 hcl:#ceb3a1\n" +
            "cid:213 hgt:193cm ecl:oth\n" +
            "\n" +
            "iyr:2014\n" +
            "byr:1971 cid:96\n" +
            "hgt:74in\n" +
            "pid:136003336\n" +
            "eyr:2020 ecl:hzl hcl:#efcc98\n" +
            "\n" +
            "hcl:z pid:097595072 ecl:amb\n" +
            "iyr:2015 byr:2021\n" +
            "eyr:2039 hgt:188cm\n" +
            "\n" +
            "pid:74823273\n" +
            "hcl:#341e13\n" +
            "cid:166 hgt:182cm byr:2026 iyr:2027 ecl:amb\n" +
            "eyr:2032\n" +
            "\n" +
            "byr:1932 eyr:2022 pid:367248062 hgt:182cm ecl:oth hcl:#c0946f\n" +
            "iyr:2020\n" +
            "\n" +
            "hgt:72cm\n" +
            "iyr:2015 cid:234 byr:2013\n" +
            "ecl:brn pid:9401866358\n" +
            "\n" +
            "pid:022399779 iyr:2010 byr:1969 hcl:#6b5442\n" +
            "ecl:grn eyr:2020\n" +
            "hgt:189cm\n" +
            "\n" +
            "byr:1971 iyr:2011 cid:161 ecl:brn hgt:153cm\n" +
            "eyr:2028 pid:819137905 hcl:#cfa07d\n" +
            "\n" +
            "cid:161 hgt:159cm iyr:2011 pid:815860793 hcl:#a97842 ecl:grn byr:1972 eyr:2027\n" +
            "\n" +
            "ecl:amb\n" +
            "hgt:118 byr:1981 iyr:2019\n" +
            "hcl:#a97842 eyr:2021 pid:270790642\n" +
            "\n" +
            "hcl:#b6652a pid:732272914 eyr:2030 hgt:183cm ecl:hzl\n" +
            "byr:1934\n" +
            "iyr:2018\n" +
            "\n" +
            "eyr:2027\n" +
            "pid:877388498 hcl:#ceb3a1\n" +
            "byr:1925 cid:236 ecl:grn\n" +
            "iyr:2019 hgt:191cm\n" +
            "\n" +
            "eyr:2020 ecl:brn hcl:#fffffd hgt:181cm pid:801311341 byr:1986 iyr:2010\n" +
            "\n" +
            "byr:1925 cid:179 ecl:hzl pid:360641953 eyr:2030\n" +
            "hgt:171in iyr:2015\n" +
            "hcl:#602927\n" +
            "\n" +
            "cid:83 hgt:181cm\n" +
            "eyr:2028 byr:1941 pid:165937945 hcl:#888785 iyr:2014\n" +
            "ecl:grn\n" +
            "\n" +
            "hcl:#a97842 byr:1928\n" +
            "iyr:2013\n" +
            "pid:870072019 hgt:76in\n" +
            "ecl:oth cid:127 eyr:2026\n" +
            "\n" +
            "cid:169\n" +
            "hgt:187cm pid:008180128 iyr:2013 byr:1991 hcl:#7d3b0c ecl:hzl eyr:2026\n" +
            "\n" +
            "ecl:amb\n" +
            "eyr:2027 hgt:155cm pid:586151564 iyr:2010\n" +
            "byr:1949\n" +
            "hcl:#18171d\n" +
            "\n" +
            "hgt:167cm\n" +
            "iyr:2010 byr:1982 ecl:amb\n" +
            "cid:235 pid:557737957 eyr:2020\n" +
            "hcl:#ceb3a1\n" +
            "\n" +
            "ecl:grn byr:1939 hcl:#733820\n" +
            "eyr:2026 pid:993218958 iyr:2010\n" +
            "hgt:150cm\n" +
            "\n" +
            "hgt:68in ecl:blu\n" +
            "byr:1965 iyr:2017 pid:854858050 eyr:2021\n" +
            "\n" +
            "ecl:gry pid:347763159 eyr:2024 iyr:2017 byr:1961\n" +
            "hgt:151cm\n" +
            "hcl:#623a2f\n" +
            "\n" +
            "ecl:utc hcl:#602927\n" +
            "pid:#1408ff byr:1941\n" +
            "cid:82\n" +
            "iyr:2015 hgt:185cm eyr:2028\n" +
            "\n" +
            "iyr:2020 hgt:151cm eyr:2025\n" +
            "byr:1934 hcl:#888785\n" +
            "pid:396545094 ecl:oth\n" +
            "\n" +
            "hgt:153cm\n" +
            "eyr:2028 hcl:#733820 ecl:gry iyr:2019\n" +
            "pid:081352630 byr:1943\n" +
            "\n" +
            "eyr:2030\n" +
            "iyr:2011\n" +
            "ecl:grn pid:313741119\n" +
            "hgt:161cm byr:1946\n" +
            "hcl:#a97842\n" +
            "\n" +
            "byr:1968 ecl:gry\n" +
            "pid:742357550\n" +
            "eyr:2024 hcl:#18171d iyr:2018\n" +
            "hgt:157cm\n" +
            "\n" +
            "pid:387505919\n" +
            "ecl:oth byr:1945\n" +
            "iyr:2014\n" +
            "hgt:190cm hcl:#888785\n" +
            "eyr:2028\n" +
            "\n" +
            "iyr:2017 hgt:175cm\n" +
            "byr:1989 eyr:2022\n" +
            "hcl:#b6652a pid:499016802 ecl:gry cid:136\n" +
            "\n" +
            "pid:490807331 iyr:2016\n" +
            "hcl:#ceb3a1\n" +
            "hgt:150cm eyr:2026\n" +
            "ecl:amb byr:1967\n" +
            "\n" +
            "iyr:2011\n" +
            "hgt:155in\n" +
            "hcl:#ceb3a1 pid:118497416\n" +
            "eyr:2029 byr:2011 ecl:oth\n" +
            "\n" +
            "hcl:03a888 byr:2029\n" +
            "ecl:#6f7292 eyr:1969 iyr:2028 hgt:162cm pid:73551266\n" +
            "\n" +
            "iyr:2016 hgt:182cm\n" +
            "byr:1966 ecl:grn eyr:2022\n" +
            "hcl:#fffffd pid:061720787\n" +
            "\n" +
            "byr:1971 hcl:z\n" +
            "eyr:2035 pid:158cm\n" +
            "ecl:#d3ec19\n" +
            "\n" +
            "hcl:#623a2f hgt:156cm eyr:2028\n" +
            "ecl:brn iyr:2013\n" +
            "byr:1980 pid:112283719\n" +
            "\n" +
            "eyr:2020\n" +
            "byr:1956 iyr:2013\n" +
            "hcl:#6b5442\n" +
            "ecl:grn pid:876589775 hgt:179cm\n" +
            "\n" +
            "hgt:138\n" +
            "byr:2013 eyr:2040 iyr:2028 cid:197 ecl:#8844fd pid:8524414485\n" +
            "hcl:z\n" +
            "\n" +
            "eyr:2040\n" +
            "hgt:173in hcl:z pid:#654654 byr:2016 iyr:2022 ecl:#452d22\n" +
            "\n" +
            "iyr:2012 cid:265 eyr:2021 hgt:192cm\n" +
            "byr:1993 ecl:brn\n" +
            "\n" +
            "eyr:2026 hcl:#888785\n" +
            "hgt:158cm byr:1942\n" +
            "iyr:2015\n" +
            "ecl:amb pid:546984106\n" +
            "\n" +
            "iyr:2019\n" +
            "ecl:hzl\n" +
            "byr:1922 eyr:2028 hgt:172cm\n" +
            "pid:465052232 hcl:#602927\n" +
            "\n" +
            "pid:710362693 eyr:2023\n" +
            "hcl:#c0946f byr:1951 ecl:grn\n" +
            "iyr:2019 hgt:190cm\n" +
            "\n" +
            "iyr:2024 pid:#a08e69\n" +
            "hcl:z byr:1966 ecl:#7b9978 eyr:2035\n" +
            "hgt:69cm\n" +
            "\n" +
            "hcl:#efcc98\n" +
            "pid:164cm\n" +
            "iyr:2010 cid:194 hgt:71cm byr:1923 eyr:2026\n" +
            "\n" +
            "hgt:65in\n" +
            "iyr:2019 byr:1969 pid:466669360 eyr:2022 ecl:brn hcl:#b6652a\n" +
            "\n" +
            "pid:42472559 hcl:#6f5763\n" +
            "eyr:2035\n" +
            "iyr:2014 hgt:154in byr:1939 ecl:grt cid:323\n" +
            "\n" +
            "pid:715680334 hgt:166cm cid:283\n" +
            "byr:1982\n" +
            "iyr:2015 eyr:2030 hcl:#ceb3a1 ecl:grn\n" +
            "\n" +
            "eyr:2018 iyr:2029\n" +
            "ecl:brn\n" +
            "byr:2022 pid:#ff6df1\n" +
            "hcl:z\n" +
            "hgt:68cm\n" +
            "\n" +
            "pid:094541122\n" +
            "eyr:2024 byr:1940\n" +
            "ecl:amb iyr:2019 hgt:64in hcl:#733820\n" +
            "\n" +
            "hgt:163in\n" +
            "eyr:2022 ecl:utc hcl:#ceb3a1 iyr:2028\n" +
            "\n" +
            "ecl:gry pid:53552934\n" +
            "hgt:193 byr:2021\n" +
            "eyr:2028\n" +
            "iyr:2011 cid:98 hcl:90c63f\n" +
            "\n" +
            "eyr:2024 hcl:#cfa07d ecl:brn\n" +
            "iyr:2019 byr:1993 hgt:156cm pid:449484188\n" +
            "\n" +
            "iyr:2020\n" +
            "hgt:164cm hcl:#623a2f\n" +
            "pid:820731743 eyr:2025\n" +
            "byr:1997 ecl:hzl\n" +
            "\n" +
            "hcl:47242b ecl:utc hgt:156\n" +
            "pid:#9a9903 eyr:2030 iyr:1990\n" +
            "byr:2011\n" +
            "\n" +
            "hcl:#602927\n" +
            "hgt:189cm\n" +
            "pid:949021883 iyr:2014 ecl:oth cid:327\n" +
            "eyr:2027 byr:1953\n" +
            "\n" +
            "hgt:189cm cid:301\n" +
            "byr:1982\n" +
            "ecl:grn\n" +
            "eyr:2028 hcl:#733820 pid:796040143 iyr:2015\n" +
            "\n" +
            "cid:169 iyr:2013 pid:355177646 byr:1988\n" +
            "ecl:oth\n" +
            "hcl:#cfa07d\n" +
            "hgt:185cm eyr:2022\n" +
            "\n" +
            "pid:563150261 eyr:2020 ecl:brn byr:1996 hcl:#7d3b0c iyr:2018 hgt:189cm cid:84\n" +
            "\n" +
            "cid:188 eyr:2027\n" +
            "byr:1944\n" +
            "pid:486184923\n" +
            "iyr:2010 hgt:193cm hcl:#341e13 ecl:oth\n" +
            "\n" +
            "iyr:2019\n" +
            "byr:1969 hgt:152cm pid:430698432 ecl:gry hcl:#888785 eyr:2026 cid:293\n" +
            "\n" +
            "ecl:gry\n" +
            "cid:270 hcl:#602927 iyr:2017 hgt:151cm eyr:2029 pid:051398739 byr:1954\n" +
            "\n" +
            "ecl:oth eyr:2030 pid:024655030\n" +
            "hgt:184cm byr:1969\n" +
            "hcl:#18171d\n" +
            "\n" +
            "eyr:2030\n" +
            "pid:899973263 hgt:178cm byr:1987 hcl:#cfa07d iyr:2012\n" +
            "ecl:amb\n" +
            "\n" +
            "iyr:1958 hgt:165cm pid:377677319\n" +
            "ecl:grt eyr:2032 byr:2025\n" +
            "hcl:bbfbe2\n" +
            "\n" +
            "ecl:blu\n" +
            "iyr:2016\n" +
            "hgt:152cm byr:1964\n" +
            "hcl:#c4f777\n" +
            "eyr:2021\n" +
            "pid:044307549 cid:80\n" +
            "\n" +
            "ecl:brn pid:330836320\n" +
            "byr:1963 cid:217 hgt:169cm\n" +
            "eyr:2024\n" +
            "iyr:2019 hcl:#ceb3a1\n" +
            "\n" +
            "byr:1976 eyr:2027\n" +
            "pid:452662874 hgt:192cm ecl:oth iyr:2018 hcl:#602927\n" +
            "\n" +
            "eyr:2027 hgt:183cm ecl:brn iyr:2017 hcl:#341e13 pid:827463598\n" +
            "\n" +
            "ecl:brn pid:930667228 cid:310 iyr:2020\n" +
            "eyr:2027 hgt:160cm byr:1932 hcl:#c0946f\n" +
            "\n" +
            "pid:955804028 byr:1983\n" +
            "hcl:#fffffd\n" +
            "hgt:178cm iyr:2013\n" +
            "eyr:2021 ecl:gry\n" +
            "\n" +
            "hgt:189cm eyr:2021 pid:430243363 iyr:2015 hcl:#ceb3a1\n" +
            "byr:2000 ecl:oth cid:284\n" +
            "\n" +
            "pid:436671537 hcl:#cfa07d iyr:2011 cid:106 hgt:171cm\n" +
            "ecl:blu eyr:2021 byr:1943\n" +
            "\n" +
            "eyr:2028 hgt:169cm\n" +
            "iyr:2015 pid:177443573 byr:1945\n" +
            "hcl:#c0946f ecl:gry\n" +
            "\n" +
            "hcl:#fffffd byr:1995 eyr:2021\n" +
            "ecl:grn\n" +
            "hgt:192cm iyr:2010 pid:754912745\n" +
            "\n" +
            "pid:330882171 iyr:2015 cid:211 ecl:grn byr:1961 eyr:2021 hcl:z\n" +
            "hgt:169cm\n" +
            "\n" +
            "byr:1926 eyr:2029 pid:178633665 cid:141 iyr:2017 hcl:#b99eb9\n" +
            "hgt:178cm ecl:brn\n" +
            "\n" +
            "eyr:2022 ecl:hzl hcl:#cfa07d hgt:168cm iyr:2015\n" +
            "byr:1982 pid:645675448\n" +
            "\n" +
            "ecl:blu byr:1980 hgt:186cm iyr:2010 cid:94 hcl:#c0946f eyr:2027 pid:384440210\n" +
            "\n" +
            "cid:309 hcl:#602927 hgt:192cm eyr:2027 ecl:amb\n" +
            "pid:527932745 iyr:2012 byr:1982\n" +
            "\n" +
            "cid:132\n" +
            "ecl:blu iyr:2016\n" +
            "eyr:2027 byr:1940 hcl:#341e13 hgt:166cm pid:613386501\n" +
            "\n" +
            "pid:360563823 eyr:2028 byr:1990 iyr:2016\n" +
            "ecl:blu cid:287 hgt:162cm hcl:#888785\n" +
            "\n" +
            "hgt:161cm\n" +
            "byr:2002\n" +
            "hcl:#623a2f pid:535361632\n" +
            "ecl:gry eyr:2021 iyr:2013\n" +
            "\n" +
            "hgt:67in\n" +
            "byr:1967\n" +
            "cid:333 hcl:#cfa07d\n" +
            "iyr:2012 eyr:2024 ecl:hzl pid:538161833\n" +
            "\n" +
            "ecl:#2bc145 eyr:1963 iyr:2030\n" +
            "cid:241 hcl:2fc384 hgt:156in pid:2899917140\n" +
            "byr:2005\n" +
            "\n" +
            "eyr:2021 pid:021590229 ecl:gry\n" +
            "hgt:164cm iyr:2013 hcl:#efcc98 byr:1985\n" +
            "\n" +
            "ecl:hzl byr:1943\n" +
            "cid:279 pid:979130395\n" +
            "iyr:2011\n" +
            "hgt:165cm\n" +
            "eyr:2021\n" +
            "hcl:#f331b3\n" +
            "\n" +
            "hgt:161cm\n" +
            "hcl:#888785 byr:1981 pid:835477382 eyr:2025 iyr:2012\n" +
            "cid:348\n" +
            "ecl:blu\n" +
            "\n" +
            "hgt:159cm hcl:b4ce6a cid:319 eyr:2035 iyr:1965 ecl:oth\n" +
            "byr:2010 pid:158cm\n" +
            "\n" +
            "iyr:2020\n" +
            "eyr:2026 ecl:grn hcl:#a97842 pid:126915503\n" +
            "hgt:178cm byr:1986\n" +
            "\n" +
            "hgt:184cm ecl:hzl\n" +
            "cid:67 iyr:2020 eyr:2026 pid:168775568 byr:1944 hcl:#a97842\n" +
            "\n" +
            "hcl:#fffffd iyr:2016 pid:379463363\n" +
            "ecl:oth\n" +
            "hgt:179cm byr:1988\n" +
            "eyr:2028\n" +
            "\n" +
            "hcl:#cfa07d ecl:amb eyr:2030 pid:320360020\n" +
            "iyr:2016 hgt:172cm byr:1961\n" +
            "\n" +
            "cid:221 hcl:#cfa07d byr:1946 eyr:2024 ecl:oth pid:066950409 hgt:173cm\n" +
            "iyr:2020\n" +
            "\n" +
            "hcl:#602927 eyr:2028 ecl:gry iyr:2019 pid:583204134 byr:1966 hgt:178cm\n" +
            "\n" +
            "byr:1930\n" +
            "iyr:2020 ecl:hzl\n" +
            "hcl:#ceb3a1 pid:285751767 cid:287 eyr:2023 hgt:192cm\n" +
            "\n" +
            "eyr:2024\n" +
            "ecl:hzl cid:87 iyr:2015\n" +
            "hgt:152cm hcl:#18171d pid:959574669\n" +
            "byr:1990\n" +
            "\n" +
            "pid:45938863\n" +
            "hcl:49c7ce cid:349 hgt:181cm\n" +
            "eyr:2023 ecl:grn iyr:2015 byr:1948\n" +
            "\n" +
            "hcl:#866857 iyr:2012 ecl:amb cid:132 byr:1955 hgt:162cm pid:597748286 eyr:2023\n" +
            "\n" +
            "pid:293364535 byr:2024\n" +
            "hgt:177cm eyr:2039\n" +
            "iyr:2020 hcl:#dae928 ecl:hzl\n" +
            "\n" +
            "pid:212659709 iyr:2018\n" +
            "hgt:188cm\n" +
            "hcl:#efcc98 byr:1974 eyr:2029 ecl:oth cid:244\n" +
            "\n" +
            "cid:140\n" +
            "ecl:amb\n" +
            "eyr:2022 hgt:181cm hcl:#efcc98\n" +
            "byr:1943\n" +
            "iyr:2016\n" +
            "\n" +
            "cid:71 hgt:151cm pid:5063555219 eyr:2023 ecl:hzl\n" +
            "byr:2019\n" +
            "hcl:#7d3b0c iyr:2023\n" +
            "\n" +
            "hgt:157in pid:#298b06 iyr:2030 ecl:#66a631 eyr:2035 hcl:z byr:2019\n" +
            "\n" +
            "hgt:190cm iyr:1943\n" +
            "pid:644021656 hcl:#6b621c\n" +
            "ecl:oth eyr:2021 byr:1923\n" +
            "\n" +
            "ecl:hzl iyr:2012 eyr:2023 pid:881271720 hcl:#ceb3a1 hgt:172cm\n" +
            "byr:1957\n" +
            "\n" +
            "iyr:2017 hcl:#888785\n" +
            "ecl:amb hgt:170cm byr:1967 pid:198856675 eyr:2027\n" +
            "\n" +
            "eyr:2026\n" +
            "ecl:gry\n" +
            "pid:834980363 hcl:#733820 byr:1930\n" +
            "hgt:175cm iyr:2018\n" +
            "cid:214\n" +
            "\n" +
            "hcl:#efcc98 eyr:2029 iyr:2010 pid:980087545\n" +
            "ecl:brn hgt:157cm\n" +
            "\n" +
            "pid:57513658 iyr:2011 byr:1993 ecl:brn eyr:2027 hcl:#6b5442 hgt:165cm\n" +
            "\n" +
            "ecl:hzl\n" +
            "eyr:2025\n" +
            "hcl:#733820\n" +
            "hgt:169cm iyr:2018 cid:328 byr:1999 pid:694719489\n" +
            "\n" +
            "eyr:2023\n" +
            "cid:125 byr:1925\n" +
            "hgt:185cm pid:806769540 iyr:2013 ecl:hzl\n" +
            "hcl:#866857\n" +
            "\n" +
            "iyr:2010 cid:225\n" +
            "ecl:hzl eyr:2027 pid:615545523\n" +
            "hcl:#733820\n" +
            "byr:1994\n" +
            "hgt:166cm\n" +
            "\n" +
            "byr:1941 ecl:gry iyr:2019 eyr:2026 hgt:73cm hcl:#602927\n" +
            "pid:352996721\n" +
            "\n" +
            "pid:140250433\n" +
            "eyr:2030 ecl:grn\n" +
            "hcl:#fffffd iyr:2011 byr:1937 hgt:185cm\n" +
            "\n" +
            "ecl:gry byr:2002 iyr:2017 hcl:#b6652a cid:261 pid:178cm eyr:2022 hgt:166cm\n" +
            "\n" +
            "ecl:grn iyr:2010 eyr:2022 byr:1924\n" +
            "pid:214641920 hcl:#ceb3a1\n" +
            "hgt:155cm\n" +
            "\n" +
            "hcl:z pid:150cm ecl:utc iyr:1981\n" +
            "eyr:2034\n" +
            "hgt:156in cid:260 byr:2027\n" +
            "\n" +
            "byr:1987 hgt:66in\n" +
            "eyr:2021 pid:876757018 iyr:2015 hcl:d596e4 ecl:hzl\n" +
            "\n" +
            "cid:116 ecl:oth hgt:180cm\n" +
            "iyr:2020 byr:1942 hcl:#2fc31f\n" +
            "eyr:2027\n" +
            "pid:253569416\n" +
            "\n" +
            "pid:509387921\n" +
            "eyr:2022\n" +
            "hcl:#888785 ecl:oth hgt:193cm\n" +
            "iyr:2012 cid:97\n" +
            "byr:1975\n" +
            "\n" +
            "hcl:#18171d hgt:190cm pid:062827417 byr:1939\n" +
            "iyr:2019 eyr:2022\n" +
            "ecl:hzl\n" +
            "\n" +
            "iyr:2025\n" +
            "byr:2028\n" +
            "hgt:165in eyr:2027 pid:6259332452\n" +
            "hcl:#478251\n" +
            "\n" +
            "iyr:2018 eyr:2026 pid:523863237\n" +
            "hgt:187cm\n" +
            "ecl:oth\n" +
            "byr:1944\n" +
            "hcl:#a97842\n" +
            "\n" +
            "hgt:181cm hcl:#733820 pid:923996316\n" +
            "cid:110\n" +
            "iyr:2011 byr:1949 ecl:blu eyr:2023\n" +
            "\n" +
            "pid:304792392 hcl:487823 eyr:2020\n" +
            "hgt:70cm byr:2024\n" +
            "iyr:1953\n" +
            "ecl:blu\n" +
            "\n" +
            "pid:142200694\n" +
            "ecl:oth hcl:#888785 eyr:2028\n" +
            "hgt:152cm byr:1954 iyr:2018\n" +
            "\n" +
            "ecl:utc\n" +
            "iyr:2015 byr:1932 hcl:#623a2f\n" +
            "eyr:2027 hgt:183cm pid:036300444\n" +
            "\n" +
            "iyr:2014 ecl:hzl byr:1935 hgt:190cm hcl:#efcc98 pid:945893288\n" +
            "eyr:2025\n" +
            "\n" +
            "hcl:#efcc98 pid:252639104 hgt:188cm\n" +
            "byr:1998 iyr:2019 ecl:grn\n" +
            "eyr:2023\n" +
            "\n" +
            "hcl:58aa4a byr:1930 hgt:193cm\n" +
            "iyr:1998 cid:196 ecl:brn\n" +
            "eyr:2032\n" +
            "\n" +
            "iyr:2015 ecl:hzl\n" +
            "hgt:193cm pid:653794674 eyr:2024\n" +
            "hcl:#fffffd byr:1921\n" +
            "\n" +
            "pid:980680460 byr:1962 ecl:blu\n" +
            "iyr:2013\n" +
            "hcl:#72cace\n" +
            "eyr:2030\n" +
            "hgt:180cm\n" +
            "\n" +
            "eyr:2025\n" +
            "hgt:182cm hcl:#ceb3a1 iyr:2010 byr:1945 cid:314 pid:597769706 ecl:amb\n" +
            "\n" +
            "pid:761757504\n" +
            "hcl:#888785 hgt:161cm iyr:2015\n" +
            "byr:1939 eyr:2025\n" +
            "cid:326 ecl:blu\n" +
            "\n" +
            "ecl:gry\n" +
            "hgt:163cm byr:1981\n" +
            "pid:330818500 iyr:2017 eyr:2024\n" +
            "cid:71 hcl:#888785\n" +
            "\n" +
            "pid:190cm cid:267 iyr:2015 ecl:brn\n" +
            "hcl:869252\n" +
            "byr:1935 hgt:142 eyr:2033\n" +
            "\n" +
            "cid:239\n" +
            "eyr:2038 ecl:lzr hcl:z iyr:1987 pid:4632768239\n" +
            "hgt:162in\n" +
            "\n" +
            "pid:158038227 ecl:brn byr:1995 eyr:2028 hcl:#efcc98\n" +
            "cid:252 iyr:2021\n" +
            "hgt:184cm\n" +
            "\n" +
            "eyr:2027\n" +
            "cid:124 ecl:amb hgt:165cm byr:1949\n" +
            "pid:727126101 iyr:2010 hcl:#602927\n" +
            "\n" +
            "ecl:grn\n" +
            "byr:1966 pid:184245393 hgt:164cm\n" +
            "eyr:2022\n" +
            "iyr:2014 hcl:#866857\n" +
            "\n" +
            "cid:62 hgt:180cm eyr:2027 hcl:#18171d\n" +
            "iyr:2017 ecl:blu byr:1942 pid:930210027\n" +
            "\n" +
            "ecl:grn hgt:171cm iyr:2017 hcl:#fffffd eyr:2029 byr:1946 pid:863414762\n" +
            "cid:95\n" +
            "\n" +
            "eyr:2025 ecl:grn iyr:2019 cid:226 hcl:#b6652a\n" +
            "byr:1932 pid:715708549\n" +
            "hgt:156cm\n" +
            "\n" +
            "pid:505158338 iyr:2019 byr:1981 hgt:193cm\n" +
            "hcl:#696a5c cid:57 ecl:hzl eyr:2023\n" +
            "\n" +
            "byr:1987\n" +
            "hgt:155cm cid:99 ecl:grn iyr:2010\n" +
            "hcl:#c0946f eyr:2023\n" +
            "pid:431067921\n" +
            "\n" +
            "hgt:190in\n" +
            "hcl:z eyr:2029 pid:74228790\n" +
            "iyr:2016 byr:2018 ecl:brn\n" +
            "\n" +
            "eyr:2022\n" +
            "ecl:xry hgt:154cm pid:62205162\n" +
            "iyr:2014 byr:1936\n" +
            "cid:61\n" +
            "\n" +
            "ecl:amb eyr:2026\n" +
            "byr:1966 cid:95 hcl:#733820 pid:957767251 iyr:2013 hgt:157cm\n" +
            "\n" +
            "byr:1969\n" +
            "hgt:156cm iyr:2013 ecl:blu hcl:#a97842\n" +
            "cid:183\n" +
            "pid:960672229 eyr:2020\n" +
            "\n" +
            "iyr:2013\n" +
            "cid:243 eyr:2028 hgt:192cm hcl:#efcc98\n" +
            "ecl:grn pid:222407433 byr:1978\n" +
            "\n" +
            "iyr:2014 byr:1935\n" +
            "eyr:2021 cid:235 pid:#1b34e1\n" +
            "hcl:#89313f hgt:164cm ecl:blu\n" +
            "\n" +
            "ecl:hzl iyr:2016 cid:327\n" +
            "byr:1923 pid:695935353 hgt:184cm\n" +
            "hcl:#a97842\n" +
            "eyr:2028\n" +
            "\n" +
            "pid:6010745668\n" +
            "byr:1934 ecl:oth eyr:2020 hgt:164cm\n" +
            "hcl:#733820\n" +
            "iyr:2016\n" +
            "\n" +
            "ecl:blu pid:071991002 eyr:2021 byr:1978 cid:321\n" +
            "hcl:#efcc98\n" +
            "iyr:2013 hgt:68in\n" +
            "\n" +
            "ecl:grn iyr:2015 pid:137792524 cid:156\n" +
            "hcl:#efcc98\n" +
            "eyr:2029 byr:1955\n" +
            "hgt:165cm\n" +
            "\n" +
            "byr:1949\n" +
            "hgt:176cm pid:531868428\n" +
            "hcl:#cfa07d ecl:brn iyr:2014 eyr:2024\n" +
            "\n" +
            "iyr:1955 cid:108 pid:712137140 byr:2019 eyr:2040 hgt:184cm hcl:220cfe ecl:#551592\n" +
            "\n" +
            "iyr:2016 eyr:2030\n" +
            "hgt:177cm cid:137 ecl:brn\n" +
            "hcl:#efcc98 pid:712202745 byr:1938\n" +
            "\n" +
            "pid:357180007 iyr:2010 ecl:grn\n" +
            "byr:1991\n" +
            "hcl:#341e13\n" +
            "eyr:2020 hgt:159cm\n" +
            "\n" +
            "eyr:2023 ecl:grn\n" +
            "hcl:#733820 iyr:2020 byr:1927 hgt:151cm\n" +
            "pid:165936826\n" +
            "\n" +
            "ecl:gry\n" +
            "pid:794227261 iyr:2014 eyr:2030\n" +
            "hcl:#18171d\n" +
            "byr:1994\n" +
            "hgt:162cm\n" +
            "\n" +
            "iyr:2017 eyr:2024\n" +
            "hcl:#7d3b0c cid:279\n" +
            "ecl:gry byr:1981 hgt:176cm pid:973822115\n" +
            "\n" +
            "eyr:2029\n" +
            "hgt:152cm hcl:#fffffd ecl:amb byr:1946 iyr:2013\n" +
            "cid:62 pid:005240023\n" +
            "\n" +
            "iyr:2010\n" +
            "ecl:amb hcl:#341e13 hgt:184cm\n" +
            "eyr:2027\n" +
            "pid:976217816 byr:1950\n" +
            "\n" +
            "ecl:grn hgt:178cm cid:192 hcl:#602927 pid:684333017 eyr:2022\n" +
            "iyr:2011 byr:1987\n" +
            "\n" +
            "pid:306960973 ecl:hzl hgt:168cm\n" +
            "byr:1954 iyr:2015 eyr:2029 hcl:#602927\n" +
            "\n" +
            "hcl:#18171d\n" +
            "byr:1973 ecl:hzl hgt:174cm pid:922891164\n" +
            "iyr:2013\n" +
            "eyr:2023\n" +
            "\n" +
            "byr:1998 hgt:189cm pid:472066200 ecl:gry iyr:2012 eyr:2021 hcl:#c0946f cid:299\n" +
            "\n" +
            "iyr:2014\n" +
            "eyr:2028 byr:1922 pid:594856217 hgt:158cm\n" +
            "ecl:oth\n" +
            "hcl:#623a2f\n" +
            "\n" +
            "pid:215206381 byr:1928\n" +
            "hgt:163cm\n" +
            "hcl:#b6652a ecl:oth iyr:2011\n" +
            "\n" +
            "cid:145 iyr:2013\n" +
            "ecl:#38a290\n" +
            "eyr:2034\n" +
            "hcl:#602927 hgt:186cm pid:517498756\n" +
            "byr:1945\n" +
            "\n" +
            "hcl:#5637d2 eyr:2030 byr:1955\n" +
            "hgt:187cm\n" +
            "pid:862655087 iyr:2014 ecl:grn\n" +
            "\n" +
            "hcl:#7d3b0c hgt:176cm iyr:2019\n" +
            "eyr:2029 byr:1980 ecl:hzl\n" +
            "cid:346 pid:703908707\n" +
            "\n" +
            "hgt:185cm\n" +
            "iyr:2017\n" +
            "cid:120 eyr:2020 hcl:#733820 ecl:blu pid:458522542 byr:1966\n" +
            "\n" +
            "pid:#725759\n" +
            "hcl:#602927 iyr:2013 byr:2003 eyr:2023 cid:100";

    private static List<Passport> passports = new ArrayList<>();
    private static List<Passport> part1ValidPassport = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("------ Start ------");
        populatePassportList();

        solvePart1();
        solvePart2();

        System.out.println("------ End ------");
    }

    private static void solvePart2() {
        System.out.println("------ Part2 : Start ------");
        int validPassport = 0;
        for (Passport p : part1ValidPassport){
            boolean validBirthYear = validateBirthYear(p.getBirthYear());
            boolean validIssueYear = validateIssueYear(p.getIssueYear());
            boolean validExpirationYear = validateExpirationYear(p.getExpirationYear());
            boolean validHeight = valiHeight(p.getHeight());
            boolean validHairColor = validateHairColor(p.getHairColor());
            boolean validEyesColor = validateEyesColor(p.getEyeColor());
            boolean validPassportID = validatePassportId(p.getPassportID());

            if(validBirthYear && validIssueYear && validExpirationYear && validHeight && validHairColor && validEyesColor && validPassportID){
                System.out.println(p);
                validPassport++;
            }
        }
        System.out.println("Number of valid passport : " + validPassport);
        System.out.println("------ Part2 : End ------");
    }

    private static boolean validatePassportId(String passportID) {
        return passportID.matches("^[0-9]{9}$");
    }

    private static boolean validateHairColor(String hairColor) {
        return hairColor.matches("^(#[a-f0-9]{6}$)");
    }

    private static boolean validateEyesColor(String eyeColor) {
        return eyeColor.matches("^((amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth))$");
    }

    private static boolean valiHeight(String height) {
        if(height.contains("cm")){
            int h = Integer.valueOf(height.split("cm")[0]);
            return h >= 150 && h <= 193;
        } else if(height.contains("in")){
            int h = Integer.valueOf(height.split("in")[0]);
            return h >= 59 && h <= 76;
        } else {
            return false;
        }
    }

    private static boolean validateExpirationYear(String expirationYear){
        if (expirationYear.length() != 4){
            return false;
        }
        int expiration = Integer.valueOf(expirationYear);
        return expiration >= 2020 && expiration <= 2030;
    }

    private static boolean validateIssueYear(String issueYear){
        if (issueYear.length() != 4){
            return false;
        }
        int issue = Integer.valueOf(issueYear);
        return issue >= 2010 && issue <= 2020;
    }

    private static boolean validateBirthYear(String birthYear){
        if (birthYear.length() != 4){
            return false;
        }
        int birth = Integer.valueOf(birthYear);
        return birth >= 1920 && birth <= 2002;
    }

    private static void solvePart1() {
        System.out.println("------ Part1 : Start ------");
        for (Passport p : passports) {
            if(StringUtils.isEmpty(p.getBirthYear()) || StringUtils.isEmpty(p.getIssueYear()) || StringUtils.isEmpty(p.getExpirationYear())
                    || StringUtils.isEmpty(p.getHeight()) || StringUtils.isEmpty(p.getEyeColor()) || StringUtils.isEmpty(p.getHairColor()) || StringUtils.isEmpty(p.getPassportID())) {
            } else {
                part1ValidPassport.add(p);
            }
        }
        System.out.println("Number of valid passport : " + part1ValidPassport.size());
        System.out.println("------ Part1 : End ------");
    }

    private static void populatePassportList() {
        System.out.println("------ populatePassportList : Start ------");
        String[] batchs = input.split("\n\n");
        for (String batch : batchs) {
            String inputLine = batch;
            inputLine = inputLine.replaceAll("\n", " ");

            Passport p = new Passport();

            String birthYear;
            String issueYear;
            String expirationYear;
            String height;
            String hairColor;
            String eyeColor;
            String passportID;
            String countryID;

            String searchString;
            int start;

            if (inputLine.contains("byr")) {
                searchString = "byr:";
                start = inputLine.indexOf(searchString);
                birthYear = inputLine.substring(start + searchString.length(), start + searchString.length() + 4);
                p.setBirthYear(birthYear);
            }
            if (inputLine.contains("iyr")) {
                searchString = "iyr:";
                start = inputLine.indexOf(searchString);
                issueYear = inputLine.substring(start + searchString.length(), start + searchString.length() + 4);
                p.setIssueYear(issueYear);
            }
            if (inputLine.contains("eyr")) {
                searchString = "eyr:";
                start = inputLine.indexOf(searchString);
                expirationYear = inputLine.substring(start + searchString.length(), start + searchString.length() + 4);
                p.setExpirationYear(expirationYear);
            }
            if (inputLine.contains("hgt")) {
                searchString = "hgt:";
                start = inputLine.indexOf(searchString);
                try {
                    height = inputLine.substring(start + searchString.length(), start + searchString.length() + 5);
                } catch (StringIndexOutOfBoundsException ex) {
                    height = inputLine.substring(start + searchString.length());
                }
                p.setHeight(height);
            }
            if (inputLine.contains("hcl")) {
                searchString = "hcl:";
                start = inputLine.indexOf(searchString);
                try {
                    hairColor = inputLine.substring(start + searchString.length(), inputLine.indexOf(" ", start));
                } catch (StringIndexOutOfBoundsException ex) {
                    hairColor = inputLine.substring(start + searchString.length());
                }
                p.setHairColor(hairColor);
            }
            if (batch.contains("ecl")) {
                searchString = "ecl:";
                start = inputLine.indexOf(searchString);
                eyeColor = inputLine.substring(start + searchString.length(), start + searchString.length() + 3);
                p.setEyeColor(eyeColor);
            }
            if (batch.contains("pid")) {
                searchString = "pid:";
                start = inputLine.indexOf(searchString);
                try {
                    passportID = inputLine.substring(start + searchString.length(), start + searchString.length() + 9);
                } catch (StringIndexOutOfBoundsException ex) {
                    passportID = inputLine.substring(start + searchString.length());
                }
                p.setPassportID(passportID);
            }
            if (batch.contains("cid")) {
                searchString = "cid:";
                start = inputLine.indexOf(searchString);
                try {
                    countryID = inputLine.substring(start + searchString.length(), start + searchString.length() + 3);
                } catch (StringIndexOutOfBoundsException ex) {
                    countryID = inputLine.substring(start + searchString.length());
                }
                p.setCountryID(countryID);
            }


            passports.add(p);
        }
        System.out.println(passports.toString());
        System.out.println("------ populatePassportList : End ------");
    }
}