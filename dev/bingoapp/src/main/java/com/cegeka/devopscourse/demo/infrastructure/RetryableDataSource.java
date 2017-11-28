package com.cegeka.devopscourse.demo.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class RetryableDataSource extends AbstractDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryableDataSource.class);

    private DataSource delegate;

    public RetryableDataSource(DataSource delegate) {
        this.delegate = delegate;
    }

    @Override
    @Retryable(maxAttempts = 10, backoff = @Backoff(multiplier = 2.3, maxDelay = 30000))
    public Connection getConnection() throws SQLException {
        LOGGER.info("Trying to connect to the database");
        return delegate.getConnection();
    }

    @Override
    @Retryable(maxAttempts = 10, backoff = @Backoff(multiplier = 2.3, maxDelay = 30000))
    public Connection getConnection(String username, String password) throws SQLException {
        LOGGER.info("Trying to connect to the database");
        return delegate.getConnection(username, password);
    }

}