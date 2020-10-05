package com.github.eikefs.core.sql.database;

import com.github.eikefs.core.sql.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DB {

    private final Connection connection;

    public DB(Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    public static DB from(Connection connection) {
        return new DB(Objects.requireNonNull(connection));
    }

    public Connection getConnection() {
        return connection;
    }

    public Document queryOne(Query query) {
        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            try (ResultSet set = statement.executeQuery()) {
                ResultSetMetaData metaData = set.getMetaData();
                Document document = new Document();

                if (!set.next()) return document;

                for (int index = 1; index <= metaData.getColumnCount(); index++) {
                    String column = metaData.getColumnName(index);

                    document.insert(column, set.getObject(column));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Document();
    }

    public List<Document> queryMany(Query query) {
        List<Document> documents = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            try (ResultSet set = statement.executeQuery()) {
                ResultSetMetaData metaData = set.getMetaData();

                while (set.next()) {
                    Document document = new Document();

                    for (int index = 1; index <= metaData.getColumnCount(); index++) {
                        String column = metaData.getColumnName(index);

                        document.insert(column, set.getObject(column));
                    }

                    documents.add(document);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return documents;
    }

    public void update(Query query) {
        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
