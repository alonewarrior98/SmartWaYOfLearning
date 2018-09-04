import org.bson.Document;

public class StudentAdapter {
	public static Document abc(Student std)
	{
		Document d = new Document();
		d.append("name", std.getName());
		d.append("rollno", std.getRollno());
		d.append("marks", std.getMarks());
		d.append("dept", std.getDept());
		return d;
	}
}
