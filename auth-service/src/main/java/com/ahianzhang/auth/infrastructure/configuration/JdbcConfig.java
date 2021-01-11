/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.infrastructure.configuration;

import com.ahianzhang.auth.infrastructure.repository.SpringDataJdbcClientRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/15 11:29 AM
 **/
@Configuration
@EnableJdbcRepositories(basePackageClasses = SpringDataJdbcClientRepository.class)
@EnableJdbcAuditing
public class JdbcConfig {

}
