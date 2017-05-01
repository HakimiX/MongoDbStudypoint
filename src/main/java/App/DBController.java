
package App;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Accumulators.sum;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Indexes.descending;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.BsonValue;
import org.bson.Document;


public class DBController {
    
    private DBConnector connect;
    
    public DBController(){
        connect = new DBConnector();
        connect.initiate();
    }
    
    private Block<Document> print(){
        return new Block<Document>(){
            @Override
            public void apply(final Document document){
                System.out.println(document.toJson());
            }
        };
    }
    
    // QUERY - How many Twitter users are in our database
    public void howManyUsers(){
        int users = connect.collection.distinct("user", BsonValue.class)
                .into(new ArrayList<BsonValue>()).size();
        System.out.println("Get All users: " + users);
    }
    
    // QUERY - Which twitter users link the most to other Twitter users (provide the top ten)
    public void mostLinkedUsers(int topten){
        connect.collection.aggregate(Arrays.asList(
                Aggregates.match(regex("text", ".*@.*")),
                Aggregates.group("$user", sum("tweets", 1)),
                Aggregates.sort(descending("tweets")),
                Aggregates.limit(topten))).forEach(print());
    }
    
    // QUERY - Who are the most mentioned twitter users? (top five)
    // Not implemented
    
    // QUERY - Who are the most active twitter users (top ten)
    public void mostActiveUsers(int topten){
        connect.collection.aggregate(Arrays.asList(
                Aggregates.group("$user", sum("tweets", 1)),
                Aggregates.sort(descending("tweets")),
                Aggregates.limit(topten))).forEach(print());
    }
    
    // Query - Who are the five most grumpy and the most happy? (five users for each group)
    public void mostHappyUsers(int topfive){
        connect.collection.aggregate(Arrays.asList(
                Aggregates.match(regex("text", "love")),
                Aggregates.sort(descending("tweets")),
                Aggregates.limit(topfive))).forEach(print());
    }
    
    public void mostGrumpyUsers(int topfive){
        connect.collection.aggregate(Arrays.asList(
                Aggregates.match(regex("text", "fuck")),
                Aggregates.sort(descending("tweets")),
                Aggregates.limit(topfive))).forEach(print());
    }
    
    
    
    
}
