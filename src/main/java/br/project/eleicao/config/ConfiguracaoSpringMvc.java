package br.project.eleicao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

/**
 * @Configuration indica ao Spring é uma classe de configuração. Em seguida, é
 * preciso estender a classe WebMvcConfigurerAdapter.
 *
 * @Bean indica ao Spring o método deve ser chamado e que ele deve gerenciar o
 * objeto retornado
 *
 *
 * O metodo templateEngine diz ao Spring que ele irá lidar com um template do
 * tipo Thymeleaf
 *
 * O metodo addViewControllers com a chamada a registry.addViewController(),
 * registra um controller automático, definido pelo próprio Spring,
 * para atender a requisições direcionadas à URL '/' e '/home'.
 * 
 * 
 * @author Allan
 */
@Configuration
public class ConfiguracaoSpringMvc extends WebMvcConfigurerAdapter {

    @Bean
    public TemplateEngine templateEngine(SpringResourceTemplateResolver resolver) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }
}
