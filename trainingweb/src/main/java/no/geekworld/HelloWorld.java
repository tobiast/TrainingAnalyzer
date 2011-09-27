package no.geekworld;

import javax.servlet.ServletContext;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.http.WebApplication;

public class HelloWorld extends WebPage
{
  public HelloWorld()
  {
    add(new Label("message", "Hello World!"));

    add(new Label("message2", "Hello World!"));

    final ServletContext ctx = ((WebApplication) getApplication()).getServletContext();

    add (new Label("root", ctx.getRealPath("/")));




  }
}
