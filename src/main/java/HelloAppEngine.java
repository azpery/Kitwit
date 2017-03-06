import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class HelloAppEngine extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  Twitter twitter = TwitterFactory.getSingleton();
	twitter.setOAuthConsumer("Lqm5FAXfFRCvDEGm3UeaIwpfs", "iSbYBRRvqfuLXmSPpLLr999dxugyeLinjhoQag8ZjPKyGhp6Le");
	Query query = new Query("source:twitter4j yusukey");
    QueryResult result;
	try {
		result = twitter.search(query);
	    for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	    }
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	response.setContentType("text/plain");
	response.getWriter().print("Hello App Engine!\r\n");

  }
}