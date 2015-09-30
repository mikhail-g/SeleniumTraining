package com.gl.training.jenkins;


import com.gl.training.utils.CommonOperations;
import org.testng.Assert;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Test {
    String a=null;
    String b="bob";
    private String MyIT;

    String getA(){
        return a;
    }

    String getB(){
        return b;
    }

    public static void main(String[] args) throws ParseException {
        Test test = new Test();
//        String result=test.getDeleteQuery("TableOfMine", "WHERE a=b");
//        System.out.println(result);
//        System.out.println("a= "+test.a);


    }

    public String getName(){
        String name = "parentName";
        System.out.println("name = " + name);
        return name;
    }


    private String getDeleteQuery(String tableName, String whereClause) {
        return a=String.format("DELETE FROM %s.%s.%s ", getDbName(), getUserName(), tableName).concat(whereClause);
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void getFileSize(){
        File imageFile = new File( getClass().getClassLoader().getResource("5mb.txt").getFile());
        long size = imageFile.length(), a = imageFile.getTotalSpace(), b = imageFile.getUsableSpace();

        System.out.println(size);
        System.out.println("size: '"+ CommonOperations.formatFileSize1(size)+"', name: '"+imageFile.getName()+"'");
    }

    

    public String getExtension(File file){
        String name = file.getName();
        System.out.println(name);
        String[] tokens = name.split("\\.");
        System.out.println(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        String ext = tokens[tokens.length-1];
        System.out.println(ext);
        return ext;
    }

    private void calendarTest(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new SimpleDateFormat("MMM").parse("July"));
//
//        calendar = new SimpleDateFormat("MMM").getCalendar();

//        String locator= "xpath=//div%s[2]";
//        String titleLocator = String.format(locator, "[title]");
//        System.out.println(titleLocator);
//        String itemLocator = locator = String.format(locator, "");
//        System.out.println(itemLocator);

        DateFormat dateFormat = new SimpleDateFormat("MMMMM dd, YYYY");
        Calendar cal = Calendar.getInstance(),
                cal2 = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 1);
        System.out.println("currentDate: " +dateFormat.format(currentDate.getTime()));
        Date date = null, date2 = null;
//        cal2.setTime(new SimpleDateFormat("MMMMM").parse("June"));


        String dateToVerify = "June 30, 2015";
        long ms1, ms2;
        try {
            date = new Date();
            date2=dateFormat.parse(dateToVerify);
            cal2.setTime(new SimpleDateFormat("MMMMM dd, yyyy").parse(dateToVerify));
            ms1 = date.getTime();
            ms2 = date2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String cal2Str = dateFormat.format(cal2.getTime());

        String expectedDate = new SimpleDateFormat("MMMMM dd, YYYY").format(cal.getTime());
        System.out.println("cal3: " + expectedDate);
        System.out.println("cal1: " +new SimpleDateFormat("MMMMM dd, yyyy").format(cal.getTime()));
        System.out.println("cal2: "+dateFormat.format(cal2.getTime()));
        int comparison = compareYearMonth(cal, cal2);
        System.out.println("comparison: "+comparison);
    }

    private void subStringTest(){
        String origin = "START278charText:Sample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateEND";
        String result = "START278charText:Sample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Order process templateSample SRD with Work Or";
        String cut = origin.substring(0,255);
        if (result.equals(cut)){
            System.out.println("pass: result=cut");
        }
        System.out.println("cut.le: "+cut.length());
        System.out.println("cut St: "+cut+"!");
        System.out.println("re.len: "+result.length());
        System.out.println("result: "+result+"!");
        System.out.println("or.len: "+origin.length());
        System.out.println("origin: "+origin);
    }

    private int compareYearMonth(Calendar cal1, Calendar cal2){
        if (cal1==null||cal2==null){
            throw new IllegalArgumentException("The dates must not be null");
        }
        int year1 = cal1.get(Calendar.YEAR),
                year2 = cal2.get(Calendar.YEAR),
                month1 = cal1.get(Calendar.MONTH),
                month2 = cal2.get(Calendar.MONTH);
        return year1>year2 ? 1 : year1<year2 ? -1 : month1>month2 ? 1 : (month1<month2) ? -1 : 0;
    }

    public String getDbName() {
        return "MyIT";
    }

    public Object getUserName() {
        return "MyITSM_Business";
    }
}


