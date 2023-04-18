package net.sevecek.videoboss;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.servlet.context.*;
import org.springframework.boot.web.servlet.support.*;
import org.springframework.context.annotation.*;
import org.springframework.context.event.*;
import org.springframework.core.env.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

@SpringBootApplication
public class VideoBossApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(VideoBossApplication.class);


    /**
     * Bootstrap method if the app is run as a .jar file
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder()
                .sources(VideoBossApplication.class)
                .build();
        app.run(args);
    }


    /**
     * Bootstrap method if the app is run as a .war file
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VideoBossApplication.class);
    }

    @Bean
    public RequestToViewNameTranslator viewNameTranslator(Environment environment) {
        DefaultRequestToViewNameTranslator viewNameTranslator = new DefaultRequestToViewNameTranslator();
        String thymeleafSuffix = environment.getProperty("spring.thymeleaf.suffix");
        if (thymeleafSuffix != null && thymeleafSuffix.isEmpty()) {
            viewNameTranslator.setStripExtension(false);
        }
        return viewNameTranslator;
    }

    @EventListener
    public void onAppEvent(ServletWebServerInitializedEvent evt) {
        int port = evt.getApplicationContext().getWebServer().getPort();
        logger.info("Your web app address: http://localhost:" + port +
                evt.getApplicationContext().getServletContext().getContextPath());
    }

}
