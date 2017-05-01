
package App;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBConnector {
    
    public MongoClient client;
    public MongoDatabase database;
    public MongoCollection<Document> collection;
    
    public void initiate(){
        client = new MongoClient("localhost", 27017);
        database = client.getDatabase("social_net");
        collection = database.getCollection("tweets");
    }
}
