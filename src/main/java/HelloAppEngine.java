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
import twitter4j.conf.ConfigurationBuilder;

public class HelloAppEngine extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  ConfigurationBuilder cb = new ConfigurationBuilder();
	  cb.setDebugEnabled(true)
	    .setOAuthConsumerKey("Lqm5FAXfFRCvDEGm3UeaIwpfs")
	    .setOAuthConsumerSecret("iSbYBRRvqfuLXmSPpLLr999dxugyeLinjhoQag8ZjPKyGhp6Le")
	    .setOAuthAccessToken("2447499750-vgTS2BReU4WThv4gDGbFacsyMDs9qvB76OS6y1F")
	    .setOAuthAccessTokenSecret("W09ihvscegGTcFKrSBOHU9lqq0jGSHKqJuxib7ZJgVdP2");
	  TwitterFactory tf = new TwitterFactory(cb.build());
	  Twitter twitter = tf.getInstance();
	//twitter.setOAuthConsumer("Lqm5FAXfFRCvDEGm3UeaIwpfs", "iSbYBRRvqfuLXmSPpLLr999dxugyeLinjhoQag8ZjPKyGhp6Le");
	Query query = new Query("from:realDonaldTrump");
    QueryResult result;
	try {
		result = twitter.search(query);
	    for (Status status : result.getTweets()) {
	    	response.getWriter().print("@" + status.getUser().getScreenName() + ":" + status.getText());
	    }
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	response.setContentType("text/plain");
	response.getWriter().print("Hello App Engine!\r\n");

  }
}