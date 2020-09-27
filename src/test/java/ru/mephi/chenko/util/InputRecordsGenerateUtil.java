package ru.mephi.chenko.util;

public class InputRecordsGenerateUtil {

    public static String getValidRecord() {
        StringBuilder validRecord = new StringBuilder();
        validRecord.append("2e72d1bd7185fb76d69c852c57436d37\t");
        validRecord.append("20131019025500549\t");
        validRecord.append("1\t");
        validRecord.append("CAD06D3WCtf\t");
        validRecord.append("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)\t");
        validRecord.append("113.117.187.*\t");
        validRecord.append("216\t");
        validRecord.append("222\t"); //cityId
        validRecord.append("2\t");
        validRecord.append("33235ca84c5fee9254e6512a41b3ad5e\t");
        validRecord.append("8bbb5a81cc3d680dd0c27cf4886ddeae\t");
        validRecord.append("null\t");
        validRecord.append("3061584349\t");
        validRecord.append("728\t");
        validRecord.append("90\t");
        validRecord.append("OtherView\t");
        validRecord.append("Na\t");
        validRecord.append("5\t");
        validRecord.append("7330\t");
        validRecord.append("277\t"); //binding price
        validRecord.append("48\t");
        validRecord.append("null\t");
        validRecord.append("2259\t");
        validRecord.append("10057,13800,13496,10079,10076,10075,10093,10129,10024,10006,10110,13776,10146,10120,10115,10063");

        return validRecord.toString();
    }

    public static String getInvalidRecord() {
        StringBuilder invalidRecord = new StringBuilder();
        invalidRecord.append("93074d8125fa8945c5a971c2374e55a8\t");
        invalidRecord.append("20131019161502142\t");
        invalidRecord.append("1\t");
        invalidRecord.append("CAH9FYCtgQf\t");
        invalidRecord.append("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)\t");
        invalidRecord.append("119.145.140.*\t");
        invalidRecord.append("216\t");
        invalidRecord.append("222\t"); //cityId
        invalidRecord.append("1\t");
        invalidRecord.append("20fc675468712705dbf5d3eda94126da\t");
        invalidRecord.append("9c1ecbb8a301d89a8d85436ebf393f7f\t");
        invalidRecord.append("null\t");
        invalidRecord.append("mm_10982364_973726_8930541\t");
        invalidRecord.append("300\t");
        invalidRecord.append("250\t");
        invalidRecord.append("FourthView\t");
        invalidRecord.append("Na\t");
        invalidRecord.append("0\t");
        invalidRecord.append("7323\t");
        invalidRecord.append("234\t"); //binding price
        invalidRecord.append("201\t");
        invalidRecord.append("null\t");
        invalidRecord.append("2259\t");
        invalidRecord.append("10057,10059,10083,10102,10024,10006,10110,10031,10063,10116\n");

        return invalidRecord.toString();
    }
}
