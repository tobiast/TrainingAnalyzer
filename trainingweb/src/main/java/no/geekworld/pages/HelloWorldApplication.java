package no.geekworld.pages;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IResourceSettings;

public class HelloWorldApplication extends WebApplication
{


    @Override
    protected void init(){
        // add another resource path 
        IResourceSettings resourceSettings = getResourceSettings();
        resourceSettings.addResourceFolder("");
    }


    @Override
    public Class getHomePage()
    {
        //return HelloWorld.class;
        return UploadPage.class;
    }
    
}
