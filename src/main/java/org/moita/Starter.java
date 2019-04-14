package org.moita;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.moita.domain.Book;
import org.moita.handler.ResponseHandler;
import org.moita.persistence.ElasticSearch;

import java.io.IOException;

import static org.moita.domain.Book.Builder.aBook;

public class Starter {

    private static final String INDEX = "book";

    private ElasticSearch persistence;

    private ResponseHandler responseHandler;

    private Gson gson;

    public Starter() {
        persistence = new ElasticSearch(INDEX);
        responseHandler = new ResponseHandler();
        gson = new GsonBuilder().create();
    }

    public void execute() throws IOException {

        Book harryPotterBook = aBook()
                .withAuthor("J. K. Rowling")
                .withGenre("Novel, Fantasy Fiction")
                .withTitle("Harry Potter and the Philosopher's Stone")
                .build();

        Book donnieDarkoBook = aBook()
                .withAuthor("Geoff King")
                .withGenre("Film criticism")
                .withTitle("Donnie Darko")
                .build();

        try {
            IndexResponse harryPotterBookIndexResp = persistence.create(gson.toJson(harryPotterBook));
            IndexResponse donnieDarkoBookIndexResp = persistence.create(gson.toJson(donnieDarkoBook));

            responseHandler.checkResponse(harryPotterBookIndexResp);
            responseHandler.checkResponse(donnieDarkoBookIndexResp);

            if (persistence.isExists(harryPotterBookIndexResp.getId())) {
                System.out.println("Harry Potter ok");
            }

            if (persistence.isExists(donnieDarkoBookIndexResp.getId())) {
                System.out.println("Donnie Darko ok");
            }

            Book updateHarryPotterBook = aBook()
                    .withAuthor("J. K. Rowling")
                    .withGenre("Novel, Fantasy Fiction")
                    .withTitle("Harry Potter and the Chamber of Secrets")
                    .build();

            UpdateResponse updateResponse = persistence.update(harryPotterBookIndexResp.getId(), gson.toJson(updateHarryPotterBook));

            responseHandler.checkResponse(updateResponse);

            // TODO: delete

            // TODO: get by version

        } catch (ElasticsearchException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Starter().execute();
    }

}
