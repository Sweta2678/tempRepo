package com.tapestry.moic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:overviewOrder.tmpl")
@PropertySource("classpath:moqReport.tmpl")
@PropertySource("classpath:customerReport.tmpl")
@PropertySource("classpath:preBuyListReport.tmpl")
@PropertySource("classpath:downloadMoq.tmpl")
@PropertySource("classpath:message.properties")
public class MoicConfiguration {

}
