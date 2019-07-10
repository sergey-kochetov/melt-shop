package ru.com.melt.info.configuration;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@PropertySource("classpath:properties/elasticsearch.properties")
@EnableElasticsearchRepositories("ru.com.melt.info.repository.search")
public class ElasticSearchConfig {
    @Value("${elasticsearch.home}")
    private String elasticSearchHome;

    @Autowired
    private Environment environment;

    @Bean(/*destroyMethod = "close"*/)
    public Node node() {
        return new NodeBuilder()
                .local(true)
                .settings(Settings.builder().put("path.home", environment.getRequiredProperty("elasticsearch.home")))
                .node();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(node().client());
    }
}
