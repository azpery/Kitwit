

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import com.google.appengine.api.datastore.Entity;

/**
 * Servlet implementation class Score
 */
public class Score extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private static Logger logger = Logger.getLogger(GameServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");    
		PrintWriter out = response.getWriter();
		
		Entity newScore = new Entity("Score");
		newScore.setProperty("username", request.getParameter("username"));
		newScore.setProperty("mail", request.getParameter("mail"));
		newScore.setProperty("score", request.getParameter("result"));
		datastore.put(newScore);
		
		Query q = new Query("Score");
		q.addSort("score", SortDirection.DESCENDING);
		List<Entity> list_score = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(3));
		JSONArray scores = new JSONArray();
		try {
			boolean found = false;
			int i = 1;
			for (Entity ent : list_score){
				JSONObject score = new JSONObject();
				score.put("mail", ent.getProperty("mail"));
				score.put("rank", i);
				score.put("username", ent.getProperty("username"));
				score.put("score", ent.getProperty("score"));
				if (ent.getProperty("mail").equals(request.getParameter("mail"))){
					found = true;
				}
				i++;
				logger.log(Level.SEVERE, "adding score to return");
				scores.put(score);
			}
			if (!found){
				Filter mailFilter = new FilterPredicate("mail", FilterOperator.EQUAL, request.getParameter("mail"));
				q = new Query("Score").setFilter(mailFilter).addSort("score", SortDirection.DESCENDING);
				Entity ent = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(1)).get(0);
				JSONObject score = new JSONObject();
				score.put("mail", ent.getProperty("mail"));
				score.put("rank", i);
				score.put("username", ent.getProperty("username"));
				score.put("score", ent.getProperty("score"));
				scores.put(score);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.print(scores);
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		doGet(request, response);

	}

}
