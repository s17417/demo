package base.Config;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Scheduler;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import base.Model.baza.Tenant;
import base.Utils.Security.TokenAuthenticationProvider;

@Configuration
@EnableCaching
public class CachingConfig {
	
	private Logger logger=LoggerFactory.getLogger(Cache.class);
	
	@Bean
	public Caffeine<Object, Object> caffeineConfig() {
	    return Caffeine
	    		.newBuilder()
	    		.scheduler(Scheduler.systemScheduler()) 
	    		.expireAfterWrite(60, TimeUnit.MINUTES);
	}
	
	@Bean
	public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
	    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
	    caffeineCacheManager.setCaffeine(caffeine);
	    Cache cache=caffeine.<Tenant,DataSource>removalListener((Tenant tenant, DataSource dataSource, RemovalCause cause) ->{
	    			logger.info(tenant.getName()+" dataSource evicted");
	    			((ComboPooledDataSource) dataSource).close();})
	    		.build();
	    caffeineCacheManager.registerCustomCache("tenantDataSource", cache);
	    return caffeineCacheManager;
	}

	/*@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("tenantDataSource");
    }*/
}
