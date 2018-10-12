package project.team.cs310;
import java.sql.*;
import java.util.logging.*;

    //EXAMPLE CONSTRUCTORS START
    public class TASDatabase {
        /*This class represents the application's connection to the TAS database.
        It will use JDBC and the MySQL driver, which is usually included with NetBeans and other IDEs.
        The constructor for this class should open a connection to the database, and the class should provide a
        "close()" method so that this connection can be closed in an orderly fashion.
        (See the lecture notes on JDBC for more information and examples on Java database programming.)
        This class should also provide methods which create the objects (Badge, Shift, and Punch).*/
        Connection conn = null;
        
        PreparedStatement pstSelect = null, pstUpdate = null;
        ResultSet resultset = null;
        ResultSetMetaData metadata = null;
        
        String query, key, value;
        
        
        //CONSTRUCTORS
        //create and implement connection to databse
        public void Connection() {
            
            try{
        
                //Identify the server
                String server = ("jdbc:mysql://localhost/tas");
                String username = "tasuser";
                String password = "WarRoomF";
                System.out.println("Connecting to " + server + "...");
                
                //load the MYSQL JDBC Driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                //open the connection
                conn = DriverManager.getConnection(server, username,password);
                
                //test connection
                if (conn.isValid(0)) {
                    System.out.println("Connected Successfully!");
                }
               
            }
        
            catch(Exception ex){
                System.out.println(ex);
            } 
        }
        
        
        //Pull queries from the database
        
        
        //add new queries to the database
        
        /*constructors for creating Punch Objects*/
        //set punch object
        public void setPunch(){
            
        }
        
        //get punch object
        public Punch getPunch(int punchInt){
            
            
             //remember to change these once constructors are finished
            return null;
        }
        
        /*constructors for creating Badge Objects*/
        
        
        //set badge object
        public void setBadge(){
            
        }
        
        
        
        //get badge object
        public Badge getBadge(String ID){
            Connection();
            
            try{
                int BadgeID = 0;
                String FName = null;
                String MName = null;
                String LName = null;
                String TotalName = null;
                
                //prepare the query
                query = "SELECT * FROM badge";
                pstSelect = conn.prepareStatement(query);
            
                //execute the query
                System.out.println("Submitting Query...");
                boolean hasresults = pstSelect.execute();
            
                //get results
                System.out.println("Getting Results...");
            
                while (hasresults || pstSelect.getUpdateCount() != -1){
                
                    if (hasresults){
                    
                        //get result metadata
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        int columnCount = metadata.getColumnCount();
                    
                        /*get the key data*/
                        for (int i = 1; i <= columnCount; i++){
                            key = metadata.getColumnName(i);
                        }
                        
                        /*get data*/
                        while(resultset.next()){
                            /*begin next ResultRow set*/
                            
                            /*loop through ResultSet Columns, print values*/
                            //declare a string array
                            String[] ValueArray = new String[2];
                            
                            
                            for (int i = 1; i <= columnCount; i++){
                                value = resultset.getString(i);
                                
                                if (i == 1){
                                   ValueArray[0] = value; 
                                }
                                else if (i == 2){
                                    ValueArray[1] = value;
                                }
                                
                                if(ValueArray[0].equals(ID)){
                                   if (ValueArray[1] == value){
                                       //declare a new badge
                                       Badge badge = new Badge();
                                       
                                      badge.setId(ValueArray[0]);
                                      badge.setDescription(ValueArray[1]);
                                      return badge; 
                                    }
                                }   
                            }
                        }
                    }
                    else{
                        int resultCount = pstSelect.getUpdateCount();
                    
                        if (resultCount == -1){
                            break;
                        }
                    }
                    //check for more data
                    hasresults = pstSelect.getMoreResults();
                }
                
            }
            catch (SQLException ex) {
                Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            return null;
        }
        
        
        
        
        /*constructors for shift rules*/
        
        //get shifts 
        //(gets the rulesets for the badge?)
        public Shift getShift(int shiftID){
            
            
            //remember to change these once constructors are finished
            return null;
        }
        
        //this does badges
        public Shift getShift (Badge badge){
            
            //remember to change these once finished
            return null;
        }
        
        //close the connection
        public void Close(){
            //is ok i guess
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
