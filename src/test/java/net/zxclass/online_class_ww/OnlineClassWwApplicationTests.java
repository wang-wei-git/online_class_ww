package net.zxclass.online_class_ww;

import io.jsonwebtoken.Claims;
import net.zxclass.online_class_ww.model.entity.User;
import net.zxclass.online_class_ww.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineClassWwApplicationTests {

    @Test
    public void testGeneJwt(){


        User user = new User();
        user.setId(66);
        user.setName("javaEE实践");
        user.setHeadImg("png");

        String token = JWTUtils.geneJsonWebToken(user);

        System.out.println(token);

//        try {
//            Thread.sleep(4000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Claims claims = JWTUtils.checkJWT(token);


        System.out.println(claims.get("name"));

    }

}
