package sstar.prospero.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Sergey.Tarasenko on 14.02.2015.
 */
public class DBStructureCreator extends JdbcDaoSupport {
    private  Logger logger = Logger.getLogger("DBStructureCreator");
    
    private String pathToSql;

    private static final String SQL_CREATE_PERSON = 
            "CREATE TABLE PERSON(" +
                "person_id INT not null primary key" +
                    "        GENERATED ALWAYS AS IDENTITY" +
                    "        (START WITH 1, INCREMENT BY 1)" +
                ",create_date date" +
                ",label varchar(100)" +
                ",puser_puser_id integer" +
             ")";
    
    public void checkStructure() {

        if(getJdbcTemplate()!=null) {
            System.out.println("-============SERG=============- Jdbc taken");
        }
        else {
            System.out.println("-============SERG=============- No Jdbc Template ");
        }
        logger.info("Processing file "+pathToSql);
        //Resource res = new ClassPathResource(pathToSql);
        Scanner scan=null;
        try {
            scan = new Scanner(/*res.getInputStream()*/ new FileInputStream(pathToSql),"UTF-8");
            while(scan.hasNext()) {
                String line = scan.nextLine();
                String []args = line.split("\\|");
                
                if(args.length>2 && args[2].equals("NOREWRITE") && tableExists(args[1])) {
                    logger.info(line+" - not executed");
                    continue;
                }

                if(!args[0].equals("")) {
                    logger.info(line + " - executing");
                    if(args.length>1 && !args[1].equals("") && tableExists(args[1])) {
                        logger.info("Dropping table "+args[1]);
                        getJdbcTemplate().execute("drop table "+args[1]);
                    }
                    getJdbcTemplate().execute(args[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            scan.close();
        }

        
        //checkPersonInfo();
        
    }
    
    private void checkPersonInfo() {
        if(tableExists("PERSON")) return;
        getJdbcTemplate().execute(SQL_CREATE_PERSON);        
    }
    
    private boolean tableExists(String tableName) {        
        boolean result = false;
        try {
            ResultSet rs = getConnection().getMetaData().getTables(null, null, tableName, null);
            result = rs.next();
            rs.close();
        }catch (SQLException sex) {
            sex.printStackTrace();
        }
        return result;
    }

    public String getPathToSql() {
        return pathToSql;
    }

    public void setPathToSql(String pathToSql) {
        this.pathToSql = pathToSql;
    }
}
