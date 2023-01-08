import Connect.Connect;
import Parameters.District;
import Parameters.Education;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.util.List;

public class Csv {

    public static void main(String[] args) throws Exception
    {
        CsvToBean csv = new CsvToBean();
        Connect connect = new Connect();
        String csvFilename = "src/main/resources/Школы.csv";

        CSVReader csvReader1 = new CSVReader(new FileReader(csvFilename));
        List list1 = csv.parse(setColumMapping(0), csvReader1);
        for (Object object : list1) {
            Education education = (Education) object;
            try {connect.insertToDB(0, education,null);}
            catch (Exception e) {}}

        CSVReader csvReader2 = new CSVReader(new FileReader(csvFilename));
        List list2 = csv.parse(setColumMapping(1), csvReader2);
        for (Object object : list2) {
            District district = (District) object;
            try {connect.insertToDB(1, null, district);}
            catch (Exception e) {}}
    }

    private static ColumnPositionMappingStrategy setColumMapping(int i)
    {
        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        if (i == 1){strat.setType(District.class);}
        else{strat.setType(Education.class);}
        String[] columns = new String[] {"id","dir", "school",
                                        "country","grades","students",
                                        "teachers","calworks","lunch",
                                        "computer","expenditure", "income",
                                        "english","read","math"};
        strat.setColumnMapping(columns);
        return strat;
    }
}
