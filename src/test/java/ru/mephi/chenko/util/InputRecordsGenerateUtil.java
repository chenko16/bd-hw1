package ru.mephi.chenko.util;

public class InputRecordsGenerateUtil {

    public static String getValidRecord() {
        String validRecord = "2e72d1bd7185fb76d69c852c57436d37\t" +
                "20131019025500549\t" +
                "1\t" +
                "CAD06D3WCtf\t" +
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)\t" +
                "113.117.187.*\t" +
                "216\t" +
                "222\t" + //cityId
                "2\t" +
                "33235ca84c5fee9254e6512a41b3ad5e\t" +
                "8bbb5a81cc3d680dd0c27cf4886ddeae\t" +
                "null\t" +
                "3061584349\t" +
                "728\t" +
                "90\t" +
                "OtherView\t" +
                "Na\t" +
                "5\t" +
                "7330\t" +
                "277\t" + //binding price
                "48\t" +
                "null\t" +
                "2259\t" +
                "10057,13800,13496,10079,10076,10075,10093,10129,10024,10006,10110,13776,10146,10120,10115,10063";

        return validRecord;
    }

    public static String getInvalidRecord() {
        String invalidRecord = "93074d8125fa8945c5a971c2374e55a8\t" +
                "20131019161502142\t" +
                "1\t" +
                "CAH9FYCtgQf\t" +
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)\t" +
                "119.145.140.*\t" +
                "216\t" +
                "222\t" + //cityId
                "1\t" +
                "20fc675468712705dbf5d3eda94126da\t" +
                "9c1ecbb8a301d89a8d85436ebf393f7f\t" +
                "null\t" +
                "mm_10982364_973726_8930541\t" +
                "300\t" +
                "250\t" +
                "FourthView\t" +
                "Na\t" +
                "0\t" +
                "7323\t" +
                "234\t" + //binding price
                "201\t" +
                "null\t" +
                "2259\t" +
                "10057,10059,10083,10102,10024,10006,10110,10031,10063,10116\n";

        return invalidRecord;
    }
}
