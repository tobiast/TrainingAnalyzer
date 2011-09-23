package no.geekworld;

import org.apache.wicket.protocol.http.WebApplication;

public class HelloWorldApplication extends WebApplication
{
    @Override
    public Class getHomePage()
    {
        return HelloWorld.class;
    }
}
