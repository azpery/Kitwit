

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

/**
 * Servlet implementation class GameServlet
 */
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<Entity> list_account; 
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    private static Logger logger = Logger.getLogger(GameServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");    
		PrintWriter out = response.getWriter();
		Query q = new Query("Account");
		list_account = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		JSONArray game = new JSONArray();
		for (int i = 0 ; i < 10 ; i++){
			addQuestion(game);
		}
		
  
		out.print(game);
		out.flush();
	}

	private void addQuestion(JSONArray game) {
		
		int idacc = ThreadLocalRandom.current().nextInt(0, list_account.size());
		while(list_account.get(idacc).getProperty("InActivity").equals("false")){
			idacc = ThreadLocalRandom.current().nextInt(0, list_account.size());
		}
		int idsugg1 = -1;
		while (idsugg1 == idacc || idsugg1 == -1){
			idsugg1 = ThreadLocalRandom.current().nextInt(0, list_account.size());
		}
		int idsugg2 = -1;
		while (idsugg2 == idacc || idsugg2 == -1 || idsugg2 == idsugg1){
			idsugg2 = ThreadLocalRandom.current().nextInt(0, list_account.size());
		}
		int idsugg3 = -1;
		while (idsugg3 == idacc || idsugg3 == -1 || idsugg3 == idsugg1 || idsugg3 == idsugg2){
			idsugg3 = ThreadLocalRandom.current().nextInt(0, list_account.size());
		}
		Entity acc = list_account.get(idacc);
		logger.log(Level.INFO, ""+acc.getProperty("AccountName"));
		Filter authorFilter = new FilterPredicate("Author", FilterOperator.EQUAL, acc.getProperty("AccountName"));
		Query q = new Query("Tweet").setFilter(authorFilter);
		
		List<Entity> res = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
		logger.log(Level.INFO, ""+res.size());
				
		Entity tweet = res.get(ThreadLocalRandom.current().nextInt(0, res.size()));
		
		JSONArray sugg = new JSONArray();
		sugg.put(acc.getProperty("Printed Name").toString());
		sugg.put(list_account.get(idsugg1).getProperty("Printed Name").toString());
		sugg.put(list_account.get(idsugg2).getProperty("Printed Name").toString());
		sugg.put(list_account.get(idsugg3).getProperty("Printed Name").toString());
		
		JSONObject question = new JSONObject();
		try {
			question.put("tweet", tweet.getProperty("Content").toString());
			question.put("suggestions", sugg);
			question.put("answer", acc.getProperty("Printed Name").toString());
			game.put(question);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
