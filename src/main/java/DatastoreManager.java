

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.repackaged.com.google.datastore.v1.CompositeFilter.Operator;
/**
 * Servlet implementation class DatastoreManager
 */
public class DatastoreManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatastoreManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		response.setCharacterEncoding("UTF-8");

		/*Query quer = new Query("Score");
		List<Entity> resul = datastore.prepare(quer).asList(FetchOptions.Builder.withDefaults());
		for (Entity ent : resul){
			datastore.delete(ent.getKey());
		}

		Entity acc1 = new Entity("Account");
		acc1.setProperty("Printed Name", "Donald Trump");
		acc1.setProperty("AccountName", "realDonaldTrump");
		acc1.setProperty("InActivity", "true");

		Entity acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Katy Perry");
		acc2.setProperty("AccountName", "katyperry");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc1);
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Cristiano Ronaldo");
		acc2.setProperty("AccountName", "Cristiano");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Lady Gaga");
		acc2.setProperty("AccountName", "ladygaga");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);

		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Bill Gates");
		acc2.setProperty("AccountName", "BillGates");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Barack Obama");
		acc2.setProperty("AccountName", "BarackObama");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Jaden Smith");
		acc2.setProperty("AccountName", "officialjaden");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Eminem");
		acc2.setProperty("AccountName", "eminem");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Leonardo DiCaprio");
		acc2.setProperty("AccountName", "LeoDiCaprio");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Alex Jones");
		acc2.setProperty("AccountName", "RealAlexJones");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Stephen Colbert");
		acc2.setProperty("AccountName", "StephenAtHome");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "God");
		acc2.setProperty("AccountName", "TheTweetOfGod");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Lord Voldemort");
		acc2.setProperty("AccountName", "Lord_Voldemort7");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Chuck Norris");
		acc2.setProperty("AccountName", "chuck_facts");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Big Ben");
		acc2.setProperty("AccountName", "big_ben_clock");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "NASA");
		acc2.setProperty("AccountName", "NASA");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Snoop Dogg");
		acc2.setProperty("AccountName", "SnoopDogg");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Ed Sheeran");
		acc2.setProperty("AccountName", "edsheeran");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "The Economist");
		acc2.setProperty("AccountName", "TheEconomist");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Emma Watson");
		acc2.setProperty("AccountName", "EmmaWatson");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Neil Patrick Harris");
		acc2.setProperty("AccountName", "ActuallyNPH");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "LeBron James");
		acc2.setProperty("AccountName", "KingJames");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Jeb Bush");
		acc2.setProperty("AccountName", "JebBush");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Vladimir Ilyich Ulyanov Lenin");
		acc2.setProperty("AccountName", "VLenin_1917");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "George Washington");
		acc2.setProperty("AccountName", "foundingfather");
		acc2.setProperty("InActivity", "true");
		datastore.put(acc2);*/
	
		
		
		Query q = new Query("Score");
		q.addSort("score", SortDirection.DESCENDING);
		List<Entity> res = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
		if(res == null){
			response.getWriter().append("Failed");
		} else if (res.size() == 0){
			response.getWriter().append("nothing in datastore");
		}
		for (Entity ent : res){
			response.getWriter().append(ent.getProperty("mail").toString()+"<br />");
			response.getWriter().append(ent.getProperty("username")+"<br />");
			response.getWriter().append(ent.getProperty("score").toString()+"<br />");

		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
