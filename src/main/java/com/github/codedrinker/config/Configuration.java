package com.github.codedrinker.config;

import lombok.Data;

/**
 * @Author huanghe
 * @Date 2019/6/30 9:50
 * @Description
 */
@Data
public class Configuration {
    private String sfUsername;
    private String sfPassword;
    private String githubUsername;
    private String githubPassword;
    private String jianshuUsername;
    private String jianshuPassword;
}
