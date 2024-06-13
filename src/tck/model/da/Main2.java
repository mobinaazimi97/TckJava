package tck.model.da;

import lombok.extern.log4j.Log4j;
import java.sql.SQLException;

@Log4j
public class Main2 {
 //   static Logger log = Logger.getLogger(Main2.class);
    public static void main(String[] args) throws SQLException {
        log.info("Saved");
        log.error("SaveError");
 //       ConnectionProvider.getConnectionProvider().getConnecton();



        //try with resources
//       try(PersonDa personDa = new PersonDa()){
//           personDa.save(null);
//       }catch(Exception e){
//           throw new RuntimeException(e);
    }
}
