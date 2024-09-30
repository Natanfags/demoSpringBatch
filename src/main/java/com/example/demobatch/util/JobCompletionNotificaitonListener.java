package com.example.demobatch.util;

import com.example.demobatch.bus.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JobCompletionNotificaitonListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificaitonListener.class);

    private final JdbcTemplate jdbcTemplate;


    public JobCompletionNotificaitonListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution job) {
        if (job.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! Job is finished, time to verify the results");
        }

        final List<Person> query = jdbcTemplate.query("SELECT first_name, last_name FROM people", new DataClassRowMapper<>(Person.class));
        query.forEach(p -> log.info("Found <{{}}> in the dataBase.", p));

    }
}
