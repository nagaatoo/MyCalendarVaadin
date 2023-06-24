package ru.numbDev.MyCalendarVaadin.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.Feign;
//import feign.Logger;
//import feign.codec.ErrorDecoder;
//import feign.slf4j.Slf4jLogger;
//import org.springframework.cloud.openfeign.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

//    @Bean
//    public ErrorDecoder documentsErrorDecoder(ObjectMapper objectMapper) {
//        return (methodKey, response) -> {
//            try {
//                byte[] responseBuf = response.body().asInputStream().readAllBytes();
//                return objectMapper.readValue(responseBuf, Exception.class);
//            } catch (IOException ex) {
//                return new Exception(ex);
//            }
//        };
//    }
//
//    @Bean
//    public CalendarApi calendarApi() {
//        return new CaleApi
//    }

//    @Bean
//    public ApiClient documentsApiClient(
//            ErrorDecoder documentsErrorDecoder,
//            ObjectFactory<HttpMessageConverters> messageConverters,
//            ObjectProvider<HttpMessageConverterCustomizer> customizers,
//            @Value("${app.api.url}") String baseUrl
//    ) {
//        baseUrl = StringUtils.trimTrailingCharacter(baseUrl, '/');
//        return Feign.builder()
//                .logger(new Slf4jLogger(ApiClient.class))
//                .encoder(new SpringEncoder(messageConverters))
//                .decoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters, customizers)))
//                .errorDecoder(documentsErrorDecoder)
//                .logLevel(Logger.Level.FULL)
//                .contract(new SpringMvcContract())
//                .target(
//                        ApiClient.class,
//                        MessageFormat.format("{0}/", baseUrl)
//                );
//    }
}
