package cn.medsci.framework;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author arvin
 */
@SpringBootApplication
@MapperScan("cn.medsci.framework.mapper*")
public class MedsciApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedsciApplication.class, args);
    }

}
