package com.codelab.app.config;

import com.codelab.app.model.Bolsa;
import com.codelab.app.utils.DateUtils;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.UUID;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Value("input/prouni*.csv")
    private Resource[] inputResources;

    @Bean
    public FlatFileItemReader<Bolsa> reader() {
        return new FlatFileItemReaderBuilder<Bolsa>()
                .linesToSkip(1)
                .name("csvItemReader")
                .delimited()
                .delimiter(";")
                .names("anoConcessao", "codigoEmec", "nomeIes",
                        "tipoBolsa", "modalidadeEnsino", "nomeCurso", "nomeTurnoCurso",
                        "cpfBeneficiario", "sexoBeneficiario", "racaBeneficiario", "dataNascimento",
                        "beneficiarioDeficienteFisico", "regiaoBeneficiario", "ufBeneficiario",
                        "municipioBeneficiario")
                .fieldSetMapper(fieldSet -> {
                    return Bolsa.builder()
                            .id(UUID.randomUUID().toString())
                            .anoConcessao(fieldSet.readInt("anoConcessao"))
                            .codigoEmec(fieldSet.readInt("codigoEmec"))
                            .nomeIes(fieldSet.readString("nomeIes"))
                            .tipoBolsa(fieldSet.readString("tipoBolsa"))
                            .modalidadeEnsino(fieldSet.readString("modalidadeEnsino"))
                            .nomeCurso(fieldSet.readString("nomeCurso"))
                            .nomeTurnoCurso(fieldSet.readString("nomeTurnoCurso"))
                            .cpfBeneficiario(fieldSet.readString("cpfBeneficiario"))
                            .sexoBeneficiario(fieldSet.readString("sexoBeneficiario"))
                            .racaBeneficiario(fieldSet.readString("racaBeneficiario"))
                            .dataNascimento(DateUtils.parseDate(fieldSet.readString("dataNascimento")))
                            .beneficiarioDeficienteFisico(fieldSet.readString("beneficiarioDeficienteFisico"))
                            .regiaoBeneficiario(fieldSet.readString("regiaoBeneficiario"))
                            .ufBeneficiario(fieldSet.readString("ufBeneficiario"))
                            .municipioBeneficiario(fieldSet.readString("municipioBeneficiario"))
                            .build();
                })
                .build();

    }

    @Bean
    public JpaItemWriter<Bolsa> writer(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Bolsa>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public MultiResourceItemReader<Bolsa> multiResourceItemReader(){
        return new MultiResourceItemReaderBuilder<Bolsa>()
                .name("multiResourceItemReader")
                .resources(inputResources)
                .delegate(reader())
                .build();
    }

    @Bean
    public Step csvImporterStep(ItemReader<Bolsa> reader, ItemWriter<Bolsa> writer,
                                JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csvImporterStep", jobRepository)
                .<Bolsa, Bolsa>chunk(50, transactionManager)
                .reader(multiResourceItemReader())
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job csvImporterJob(Step csvImporterStep, JobRepository jobRepository, ImportJobListener listener) {
        return new JobBuilder("csvImporterJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvImporterStep)
                .end()
                .build();
    }
}
