

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
		
		/*Query quer = new Query("Account");
		List<Entity> resul = datastore.prepare(quer).asList(FetchOptions.Builder.withDefaults());
		for (Entity ent : resul){
			datastore.delete(ent.getKey());
		}

		Entity acc1 = new Entity("Account");
		acc1.setProperty("Printed Name", "Donald Trump");
		acc1.setProperty("AccountName", "realDonaldTrump");

		Entity acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Katy Perry");
		acc2.setProperty("AccountName", "katyperry");
		datastore.put(acc1);
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Cristiano Ronaldo");
		acc2.setProperty("AccountName", "Cristiano");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Lady Gaga");
		acc2.setProperty("AccountName", "ladygaga");
		datastore.put(acc2);

		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Bill Gates");
		acc2.setProperty("AccountName", "BillGates");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Barack Obama");
		acc2.setProperty("AccountName", "BarackObama");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Jaden Smith");
		acc2.setProperty("AccountName", "officialjaden");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Eminem");
		acc2.setProperty("AccountName", "eminem");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Leonardo DiCaprio");
		acc2.setProperty("AccountName", "LeoDiCaprio");
		datastore.put(acc2);
		
		acc2 = new Entity("Account");
		acc2.setProperty("Printed Name", "Alex Jones");
		acc2.setProperty("AccountName", "RealAlexJones");
		datastore.put(acc2);*/
		
		
		Query q = new Query("Tweet");
		List<Entity> res = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
		if(res == null){
			response.getWriter().append("Failed");
		} else if (res.size() == 0){
			response.getWriter().append("nothing in datastore");
		}
		for (Entity ent : res){
			response.getWriter().append(ent.getProperty("Author")+" : "+ent.getProperty("Content").toString()+"<br />");
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
