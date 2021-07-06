package com.admin.zhonghe;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ZhongheApplicationTests {

    @Test
    void contextLoads() {
        Date date=new Date( System.currentTimeMillis()+1000*6000);
         JwtBuilder jwtBuilder= Jwts.builder().setId("888")
                .setSubject("zhonglx")
                .setIssuedAt(new Date())
                .claim("name","测试")
                .setExpiration(date).signWith(SignatureAlgorithm.HS256,"ceshi");
         System.out.println(jwtBuilder.compact());

    }
    @Test
    public void parseJwt(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJ6aG9uZ2x4IiwiaWF0IjoxNjI0MjQxNDIwLCJuYW1lIjoi5rWL6K-VIiwiZXhwIjoxNjI0MjQ3NDIwfQ.DQEI8iFx46hVUkNzmMhyT6VxIYK2jOsWwoQVWHNS9Ac";
        Claims ceshi = Jwts.parser().setSigningKey("ceshi").parseClaimsJws(token).getBody();
       System.out.println( ceshi.get("name"));

    }

}
