package com.stylefeng.guns.rest.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Data
@Slf4j
@ConfigurationProperties(prefix = "ftp")
public class FTPUtils {
    private String ip;
    private Integer port;
    private String username;
    private String password;

    private FTPClient ftpClient;

    @PostConstruct
    public void init() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.connect(ip, port);
        ftpClient.login(username, password);
    }

    public String getSeats(String path) {
        BufferedReader br = null;
        try {
            if (ftpClient == null) {
                init();
            }
            StringBuffer sb = new StringBuffer();
            String line = null;
            br = new BufferedReader(new InputStreamReader(this.ftpClient.retrieveFileStream(path)));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            log.error("获取文件流失败: " + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (this.ftpClient != null) {
                    this.ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.ftpClient = null;
        }
        return null;
    }
}
