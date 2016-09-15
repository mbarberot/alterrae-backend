package com.alterrae.spark.view;

import com.alterrae.view.response.View;
import com.alterrae.view.response.ViewException;
import org.junit.Test;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AnswerTest {

    public static final String BODY = "Lorem ipsum";

    @Test
    public void testSimplestAnswer() throws Exception {
        Response response = mock(Response.class);
        assertEquals("", new Answer(response).build());
        verify(response).status(200);
        verify(response).body("");
    }

    @Test
    public void testAnswer_WithBody_String() throws Exception {
        Response response = mock(Response.class);
        assertEquals(BODY, new Answer(response).body(BODY).build());
        verify(response).status(200);
        verify(response).body(BODY);
    }

    @Test
    public void testAnswer_WithBody_StringNull() throws Exception {
        Response response = mock(Response.class);
        assertEquals("", new Answer(response).body((String)null).build());
        verify(response).status(500);
        verify(response).body("");
    }

    @Test
    public void testAnswer_WithBody_View() throws Exception {
        Response response = mock(Response.class);
        assertEquals(BODY, new Answer(response).body(getView(BODY)).build());
        verify(response).status(200);
        verify(response).body(BODY);
    }

    @Test
    public void testAnswer_WithBody_ViewNull() throws Exception {
        Response response = mock(Response.class);
        assertEquals("", new Answer(response).body((View)null).build());
        verify(response).status(500);
        verify(response).body("");
    }

    @Test
    public void testAnswer_WithStatus() throws Exception {
        Response response = mock(Response.class);
        assertEquals("", new Answer(response).status(400).build());
        verify(response).status(400);
        verify(response).body("");
    }

    @Test
    public void testAnswer_WithStringBodyAndStatus() throws Exception {
        Response response = mock(Response.class);
        assertEquals(BODY, new Answer(response).body(BODY).status(400).build());
        verify(response).status(400);
        verify(response).body(BODY);
    }

    @Test
    public void testAnswer_WithViewBodyAndStatus() throws Exception {
        Response response = mock(Response.class);
        assertEquals(BODY, new Answer(response).body(getView(BODY)).status(400).build());
        verify(response).status(400);
        verify(response).body(BODY);
    }

    private View getView(String rendered) throws ViewException {
        View view = mock(View.class);
        when(view.render()).thenReturn(rendered);
        return view;
    }
}