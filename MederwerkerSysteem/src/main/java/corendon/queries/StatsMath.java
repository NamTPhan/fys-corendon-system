package corendon.queries;

/**
 *
 * @author huipvandenende
 */
public class StatsMath {
    
    /**
     * @param days the days to do the calculations over
     * @return results of the calculations
     */
    public static String getTotalDays(int days){
        MyJDBC myJDBC = new MyJDBC();
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE DATE(created_at) <= NOW() AND "
                + "DATE(created_at) >= DATE(NOW() - interval " + days +" day);" 
                );
        
        String res = "/" + myJDBC.executeStringListQuery(query);
        myJDBC.close();
        return res;
    }
     
    
    /**
     * Computes the results of the luggage returned in the last 3 days
     * @return results of the calculations
     */
    public static String threeDaysResults(){
        MyJDBC myJDBC = new MyJDBC();
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE status_id = '2' AND "
                + "DATE(created_at) <= NOW() AND "
                + "DATE(created_at) >= DATE(NOW() - interval 3 day);"
                );
        
        String res = myJDBC.executeStringListQuery(query);
        myJDBC.close();
        return res;
    }
    
   
    /**
     * Computes the results of the luggage returned in the last 21 days
     * @return results of the calculations
     */
    public static String twentyOneDaysResults(){
        MyJDBC myJDBC = new MyJDBC();
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE status_id = '2' AND "
                + "DATE(created_at) <= NOW() AND "
                + "DATE(created_at) >= DATE(NOW() - interval 21 day);"
                );      
        
        String res = myJDBC.executeStringListQuery(query);
        myJDBC.close();
        return res;
    }
    
    
    /**
     * Computes the results of the luggage returned in the last month
     * @return results of the calculations
     */
    public static String oneMonthResults(){
        MyJDBC myJDBC = new MyJDBC();
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE status_id = '2' AND "
                + "DATE(created_at) >= DATE(NOW() - interval 31 day); "
                );     
        
        String res = myJDBC.executeStringListQuery(query);
        myJDBC.close();
        return res;
    }
    
    
    /**
     * Computes the results of the luggage returned in the last year
     * @return results of the calculations
     */
    public static String oneYearResults(){
        MyJDBC myJDBC = new MyJDBC();
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM luggage "
                + "WHERE DATE(created_at) >= DATE(NOW() - interval 365 day) "
                );
        
        String res = myJDBC.executeStringListQuery(query);
        myJDBC.close();
        return res;
    }
    
    /**
     * Takes input from a linechart loop to populate the chart with
     * lost luggage data from the databse
     * @param monthCounter tthe amout of days back you want the data
     * @return results of query
     */
    public static int getMonthlyLost(int monthCounter){
        MyJDBC myJDBC = new MyJDBC();
        String counter = Integer.toString(monthCounter);
        int queryBorder = monthCounter + 1;
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE DATE(created_at)< NOW() - INTERVAL " + monthCounter +" MONTH AND "
                + "DATE(created_at) > NOW() - INTERVAL " + queryBorder +" MONTH; "
                );
        
        String dbres = myJDBC.executeStringListQuery(query);
        int res = Integer.parseInt(dbres);
        myJDBC.close();
        return res;
    }
    
    /**
     * Takes input from a linechart loop to populate the chart with 
     * found luggage data form the database
     * @param monthCounter the amout of days back you want the data
     * @return results of query
     */
    public static int getMonthlyFound(int monthCounter){
        MyJDBC myJDBC = new MyJDBC();
        String counter = Integer.toString(monthCounter);
        int queryBorder = monthCounter + 1;
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE status_id = '2' AND "
                + "DATE(created_at)< NOW() - INTERVAL " + monthCounter +" MONTH AND "
                + "DATE(created_at) > NOW() - INTERVAL " + queryBorder +" MONTH; "
                );
        
        String dbres = myJDBC.executeStringListQuery(query);
        int res = Integer.parseInt(dbres);
        myJDBC.close();
        return res;
    }
    
     /**
     * Computes the unsolved cases per year
     * @return results of the calculations
     */
    public static String oneYearUnsolvedResults(){
        MyJDBC myJDBC = new MyJDBC();
        String query = (""
                + "SELECT COUNT(*) "
                + "FROM issues "
                + "WHERE status_id = '1' OR "
                + "status_id = '0' AND "
                + "DATE(created_at) >= DATE(NOW() - INTERVAL 1 YEAR)"
                );
        // inner join op issues zodat issues.created 
        String res = myJDBC.executeStringListQuery(query);
        myJDBC.close();
        return res;
    }
}

