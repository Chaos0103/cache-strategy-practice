package practice.cache_strategy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import practice.cache_strategy.config.LookAsidePatternConfig;
import practice.cache_strategy.config.NoCacheConfig;

@EnableCaching
//@Import(NoCacheConfig.class)
@Import(LookAsidePatternConfig.class)
@SpringBootApplication(scanBasePackages = {
    "practice.cache_strategy.controller",
    "practice.cache_strategy.repository",
    "practice.cache_strategy.redisconfig"
})
public class CacheStrategyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheStrategyApplication.class, args);
    }

}
