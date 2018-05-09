package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by iyou on 2017/4/16.
 */
@Configuration
@ImportResource(locations = {"classpath:config/property_config.xml"})
public class ProfilesConfig {

}
