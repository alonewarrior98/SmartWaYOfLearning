import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class Main {
public static void main(String[] args) {
	MongoClient mongo = new MongoClient("localhost",27017);
	MongoDatabase db = mongo.getDatabase("demo2");
	//db.createCollection("studTest");
	MongoCollection<Document> collection = db.getCollection("apoorv");
	Document d1= new Document();
	
	Scanner sc = new Scanner(System.in);
	int choice=0;
	do
	{
		System.out.println("1. Add a new Student");
		
		System.out.println("2. Search  student by rollno");
		System.out.println("3. Search astudent by marks");
		System.out.println("4. Find  student with highest marks");
		System.out.println("5. Find all  student that have failed");
		System.out.println("6. Marks between some range");
		System.out.println("7. Find student by dept");
		System.out.println("8. All Student having highest marks in all  dept");
		System.out.println("9. Totla no. of student in each dept");
		System.out.println("10. Exit");
			

			
		choice= sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			sc.nextLine();
			System.out.println("Enter your name");
			String name= sc.nextLine();
			System.out.println("Enter your rollno");
			String rollno = sc.nextLine();
			System.out.println("Enter your marks");
			double marks = sc.nextDouble();
			sc.nextLine();
			System.out.println("Enter your dept");
			String dept = sc.nextLine();
			Document d9=new Document();
			d9.append("name",name );
			d9.append("rollno", rollno);
			d9.append("marks", marks);
			d9.append("dept", dept);
			collection.insertOne(d1);
			break;
		}
		
		
		case 2:
		{
			Document d = new Document();
			sc.nextLine();
			System.out.println("Enter rollno you want to search");
			String search= sc.nextLine();
			d.append("rollno", search);
			FindIterable<Document> ftr = collection.find(d);
			Iterator it= ftr.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			break;
			
		}
		case 3:
		{
			Document d = new Document();
			sc.nextLine();
			System.out.println("Enter marks you want to search");
			double search= sc.nextDouble();
			d.append("marks", search);
			FindIterable<Document> ftr = collection.find(d);
			Iterator it= ftr.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
		
			break;
		}
		case 4:
		{	
			Document d = new Document();
			d.append("marks", -1);
			FindIterable<Document> ftr = collection.find().sort(d).limit(1);
			Iterator it= ftr.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
		
			break;
		}
		case 5:
		{
			System.out.println("Students who are fail ");
			 FindIterable<Document> iter5 = collection.find().filter(Filters.lt("marks", 35));
			  Iterator it5 = iter5.iterator();
				while (it5.hasNext()) {
					System.out.println(it5.next());

				}
			break;
		}
		case 6:
		{
			FindIterable<Document> iter = collection.find().filter(Filters.and(Filters.gte("marks", 60),Filters.lte("marks",90)));
			Iterator it = iter.iterator();
			
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			
			break;
		}
		case 7:
		{
			Document d = new Document();
			sc.nextLine();
			System.out.println("Enter department name you want to search");
			String search= sc.nextLine();
			d.append("dept", search);
			FindIterable<Document> ftr = collection.find(d);
			Iterator it= ftr.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			
			break;
		}
		case 8:
		{	ArrayList al = new ArrayList<>();
			Document d5= new Document();
			d5.append("max", -1);
			al.add(Aggregates.group("$dept", Accumulators.max("max", "$marks")));
			al.add(Aggregates.sort(d5));
			al.add(Aggregates.limit(1));
			Iterator it = collection.aggregate(al).iterator();	
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			
			break;
		}
		case 9:
		{
			ArrayList al = new ArrayList<>();
			al.add(Aggregates.group("$dept", Accumulators.sum("count", 1)));
			Iterator it = collection.aggregate(al).iterator();	
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			
			
		
			break;
		}
		case 10:
		{
			System.exit(0);
		}
		}

	}while(choice!=11);
}
}

