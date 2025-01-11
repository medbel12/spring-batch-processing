package ma.enset.tp6_bach_processing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<Order> reader() {
        return new FlatFileItemReaderBuilder<Order>()
                .name("orderItemReader")
                .resource(new ClassPathResource("orders.csv"))
                .delimited()
                .names("orderId", "name", "montant") // Correspond aux colonnes du CSV
                .linesToSkip(1) // Ignore la première ligne (en-tête)
                .targetType(Order.class)
                .build();
    }

    @Bean
    public OrderItemProcessor processor() {
        return new OrderItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Order> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Order>()
                .sql("INSERT INTO ORDERS (order_id, name, montant) VALUES (:orderId, :name, :montant)")
                .dataSource(dataSource)
                .beanMapped() // Mappe automatiquement les champs de l'objet à la requête SQL
                .build();
    }

    @Bean
    public Job importOrderJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importOrderJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<Order> reader, OrderItemProcessor processor,
                      JdbcBatchItemWriter<Order> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Order, Order>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }



}
