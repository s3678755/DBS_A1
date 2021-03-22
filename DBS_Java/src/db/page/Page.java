package db.page;

//query data
public interface Page {
	public String addRecord(Record r);
	public String findAll();
	public Record findRecordById(long id);
	public String deleteRecord(Record r);
}
