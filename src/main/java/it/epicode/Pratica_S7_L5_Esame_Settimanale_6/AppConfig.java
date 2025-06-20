package it.epicode.Pratica_S7_L5_Esame_Settimanale_6;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.cloud_name}") String cloudName,
                                    @Value("${cloudinary.api_key}") String apiKey,
                                    @Value("${cloudinary.api_secret}") String apiSecret) {
        Map<String, String> configCloudinary = new HashMap<>();

        configCloudinary.put("cloud_name", cloudName);
        configCloudinary.put("api_key", apiKey);
        configCloudinary.put("api_secret", apiSecret);

        return new Cloudinary(configCloudinary);
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSender(
            @Value("${gmail.mail.transport.protocol}") String protocol,
            @Value("${gmail.mail.smtp.auth}") boolean auth,
            @Value("${gmail.mail.smtp.starttls.enable}") boolean starttls,
            @Value("${gmail.mail.debug}") boolean debug,
            @Value("${gmail.mail.from}") String from,
            @Value("${gmail.mail.from.password}") String password,
            @Value("${gmail.mail.smtp.ssl.enable}") boolean ssl,
            @Value("${gmail.smtp.host}") String host,
            @Value("${gmail.smtp.port}") int port) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(from);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("mail.smtp.ssl.enable", ssl);

        return mailSender;
    }
}

