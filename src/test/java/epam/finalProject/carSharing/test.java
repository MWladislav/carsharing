package epam.finalProject.carSharing;

import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class test {

    @Test
    public void test(){
        Timestamp format=Timestamp.valueOf("2018-10-10 12:12:12");
        LocalDateTime localDateTime=format.toLocalDateTime();
        System.out.println(format.toString());
        System.out.println(localDateTime.toString());
    }
}
