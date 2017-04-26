

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class TweetFeeder
 */
public class TweetFeeder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetFeeder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("Lqm5FAXfFRCvDEGm3UeaIwpfs")
		.setOAuthConsumerSecret("iSbYBRRvqfuLXmSPpLLr999dxugyeLinjhoQag8ZjPKyGhp6Le")
		.setOAuthAccessToken("2447499750-vgTS2BReU4WThv4gDGbFacsyMDs9qvB76OS6y1F")
		.setOAuthAccessTokenSecret("W09ihvscegGTcFKrSBOHU9lqq0jGSHKqJuxib7ZJgVdP2");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		response.getWriter().append("Beginning clean of tweets!<br />");
		Query q = new Query("Tweet");
		List<Entity> res = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		for (Entity ent : res){
			datastore.delete(ent.getKey());
		}

		response.getWriter().append("Getting accounts from the store!<br />");
		q = new Query("Account");
		res = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

		for (Entity ent : res){
			response.getWriter().append("Requesting tweets from twitter! acc : "+ent.getProperty("AccountName")+"<br />");
			twitter4j.Query query = new twitter4j.Query("from:"+ent.getProperty("AccountName"));
			QueryResult result;
			try {
				result = twitter.search(query);
				response.getWriter().append(result.getTweets().size()+" tweets <br />");
				/*Status selectedStatus = null;
				for (Status status : result.getTweets()) {
					if (selectedStatus != null){
						if (selectedStatus.getRetweetCount() < status.getRetweetCount()){
							selectedStatus = status;
						}
					}else {
						selectedStatus = status;
					}
				}

				Entity tweet = new Entity("Tweet");
				tweet.setProperty("Author", ent.getProperty("AccountName"));
				tweet.setProperty("Content", removeUrl(selectedStatus.getText()));*/
				int count = 0;
				for (Status status : result.getTweets()) {
					if (!status.isRetweet()){
						String text = removeUrl(status.getText());
						if (text.length() > 25){
							Entity tweet = new Entity("Tweet");
							tweet.setProperty("Author", ent.getProperty("AccountName"));
							tweet.setProperty("Content", text);
							datastore.put(tweet);
							response.getWriter().append(text+" : stored !<br />");
							count ++;
						}
					}
				}
				if (count > 0){
					if (ent.getProperty("InActivity").equals("false")){
						response.getWriter().append("account is now active !<br />");
						ent.setProperty("InActivity", "true");
						datastore.put(ent);
					}
				}else {
					response.getWriter().append("account is now inactive !<br />");
					ent.setProperty("InActivity", "false");
					datastore.put(ent);
				}


			}catch (Exception e){
				response.getWriter().append("error : "+e.getMessage()+"<br />");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String removeUrl(String commentstr)
	{
		String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(commentstr);
		int i = 0;
		while (m.find()) {
			commentstr = commentstr.replaceAll(m.group(i),"").trim();
			i++;
		}
		return commentstr;
	}

}
