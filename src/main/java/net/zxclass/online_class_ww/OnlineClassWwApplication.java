package net.zxclass.online_class_ww;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("net.zxclass.online_class_ww.mapper")
@EnableTransactionManagement
public class OnlineClassWwApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineClassWwApplication.class, args);
    }

}
