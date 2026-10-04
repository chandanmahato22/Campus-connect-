package in.chandan.CampusConnect.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {
    private final StringRedisTemplate redisTemplate;

    public RateLimitService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private static final int MAX_ATTEMPTS = 5;
    private static final Duration WINDOW  = Duration.ofMinutes(1);

    public boolean isBlocked(Long uid,String ip){
        String userKey = "login:failed:" + uid;
        String ipKey = "login:failed:ip:" + ip;

        return (isLimitReached(userKey,5) || isLimitReached(ipKey,20));
    }

    private boolean isLimitReached(String key , int limit){

        String value = redisTemplate.opsForValue().get(key);

        return value != null && Integer.parseInt(value) >= limit;
    }


    public void recordFailedAttempt(Long uid,String ip){
        String userKey = "login:failed:" + uid;
        String ipKey = "login:failed:ip:" + ip;

        increment(userKey,Duration.ofMinutes(1));
        increment(ipKey,Duration.ofMinutes(1));
    }
    public void resetAttempts(Long uid,String ip){
        String key = "login:failed:" + uid;
        String ipKey = "login:failed:ip:" + ip;
        redisTemplate.delete(ipKey);
        redisTemplate.delete(key);
    }
    private void increment(String key,Duration duration){
        Long count = redisTemplate.opsForValue().increment(key);
        if(count == 1){
            redisTemplate.expire(key,duration);
        }
    }
}
