package com.site.todolist.list;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TodoListTests {
    @LocalServerPort
    private int port = 8080;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + port + "/api/todolist");
    }

    @Test
    public void getAll() throws Exception {
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress())
                .build();
        ResponseEntity<List<TodoList>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<TodoList>>(){});
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void delete() throws Exception {
        RequestEntity<Void> request = RequestEntity
                .delete(new URI("http://localhost:" + port + "/api/todolist/1"))
                .build();
        ResponseEntity<List<TodoList>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<TodoList>>(){});
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    // not existing object
    public void badDelete() throws Exception {
        RequestEntity<Void> request = RequestEntity
                .delete(new URI("http://localhost:" + port + "/api/todolist/3"))
                .build();
        ResponseEntity<List<TodoList>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<TodoList>>(){});
        assertTrue(response.getStatusCode().is5xxServerError());
    }

    @Test
    public void put() throws Exception {
        RequestEntity<Void> request = RequestEntity
                .put(new URI("http://localhost:" + port + "/api/todolist/2?task=Spacer"))
                .build();
        ResponseEntity<List<TodoList>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<TodoList>>(){});
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    // not existing object
    public void badPut() throws Exception {
        RequestEntity<Void> request = RequestEntity
                .put(new URI("http://localhost:" + port + "/api/todolist/3?task=Spacer"))
                .build();
        ResponseEntity<List<TodoList>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<TodoList>>(){});
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void post() throws Exception {
        RequestEntity<TodoList> request = RequestEntity
                .post("http://localhost:8080/api/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TodoList("Smieci", LocalDate.parse("2030-01-01")));
        ResponseEntity<List<TodoList>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<TodoList>>(){});
        assertTrue(response.getStatusCode().is2xxSuccessful());
        System.out.print(response.toString());
    }

}
