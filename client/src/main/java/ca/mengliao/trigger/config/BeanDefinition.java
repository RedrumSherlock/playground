package ca.mengliao.trigger.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class BeanDefinition {


  public RestTemplate restTemplate() {
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(1 * 1000);
    requestFactory.setReadTimeout(1 * 1000);
    return new RestTemplate(requestFactory);
  }

  @Bean
  public RestTemplate pooledRestTemplate() {
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(5, TimeUnit.SECONDS);
    connectionManager.setMaxTotal(10);
    connectionManager.setDefaultMaxPerRoute(5);
    connectionManager.setValidateAfterInactivity(300 * 1000);
    RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(3000)
            .setConnectTimeout(3000)
            .setSocketTimeout(3000)
            .build();
    CloseableHttpClient httpClient = HttpClientBuilder
            .create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .build();
    requestFactory.setHttpClient(httpClient);
    return new RestTemplate(requestFactory);
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(new JavaTimeModule());
  }

}
