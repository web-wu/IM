package com.tabwu.IM;

import com.tabwu.IM.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @PROJECT_NAME: IM
 * @USER: tabwu
 * @DATE: 2023/6/9 15:42
 * @DESCRIPTION:
 */
@SpringBootApplication
@MapperScan("com.tabwu.IM.mapper")
public class ImApplication implements InitializingBean {
    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new NettyServer().serverStart("127.0.0.1", 666);
    }
}
