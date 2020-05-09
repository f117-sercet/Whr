package Application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "org.vhr.mapper")
@EnableScheduling
public class VhrApplication {
    public static void main(String[] args) {
        SpringApplication.run(VhrApplication.class,args);
    }
}
