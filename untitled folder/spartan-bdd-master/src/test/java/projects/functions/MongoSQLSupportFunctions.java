package projects.functions;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Assert;

import java.util.Map;

public class MongoSQLSupportFunctions {
    private static MongoClient mongoClient = null;
    private static MongoDatabase mongoDB = null;
    private static MongoCollection<Document> mongoColl = null;

    private static DBCollection dbCollection = null;
    private static DBObject dbObject = null;
    private static DBCursor cursor = null;
    public static BasicDBObjectBuilder dbQuery = null;

    //IP & Port
    private static final String DB_PRICING_CONN = "mongodb://10.60.3.2:37017";
    private static final String DB_ZEXAL_CONN = "mongodb://10.60.3.2:27017";


    //Public connection
    public MongoSQLSupportFunctions(String databaseName) {
        switch (databaseName.toUpperCase()) {
            case "ZERAL":
                connectToDatabase_Zexal();
                break;
            case "PRICING":
                connectToDatabase_Pricing();
                break;
        }

        Assert.assertNotNull("Database " + databaseName + " doesn't exist!", mongoDB);
    }

    //Core
    private void connectToDatabase_Zexal() {
        MongoSQLSupportFunctions.mongoClient = connectToDatabase(DB_ZEXAL_CONN);
        setDatabase("zexal");
    }

    private void connectToDatabase_Pricing() {
        MongoSQLSupportFunctions.mongoClient = connectToDatabase(DB_PRICING_CONN);
        setDatabase("pricing");
    }

    private MongoClient connectToDatabase(String dbConnStr) {
        return new MongoClient(new MongoClientURI(dbConnStr));
    }

    private void setDatabase(String dbName) {
        mongoDB = mongoClient.getDatabase(dbName);
    }

    //Query
    @Deprecated
    public void getTable(String collectionName) {
        mongoColl = mongoDB.getCollection(collectionName);
//        mongoColl.drop();
    }

    public void getCollection(String collectionName) {
        mongoColl = mongoDB.getCollection(collectionName);
//        mongoColl.drop();
    }

    private void initMongoQuery(){
        dbQuery = BasicDBObjectBuilder.start();
    }

    public DBObject queryDB(String collectionName, Map<Object, Object> queryAppend, boolean initQuery){
        getCollection(collectionName);
        return queryDB(queryAppend, initQuery);
    }

    public DBObject queryDB(Map<Object, Object> queryAppend, boolean initQuery){
        if(initQuery){
            dbQuery = null;
            dbQuery = BasicDBObjectBuilder.start();
        }

        queryAppend.entrySet().forEach(entry->{
            dbQuery.append(String.valueOf(entry.getKey()), entry.getValue());
        });

//        String key, value;
//        for(Object mapObj : queryAppend.keySet()){
//            key = mapObj.toString();
//            value = queryAppend.get(mapObj).toString();
//            dbQuery.append(key, value);
//        }

        dbObject = dbQuery.get();
        cursor = dbCollection.find(dbObject);
        dbObject = cursor.next();
        return dbObject;
//        return String.valueOf(((DBObject) price.get("price")).get("vnd"));
    }


    //========================================================================================================
    @Deprecated
    public static String getPriceVND() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        return String.valueOf(((DBObject) price.get("price")).get("vnd"));
    }

    @Deprecated
    public static String getPriceCredit() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        return String.valueOf(((DBObject) price.get("price")).get("credit"));
    }

    @Deprecated
    public static String getPricePromotion() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        return String.valueOf(((DBObject) price.get("price")).get("promotion"));
    }

    @Deprecated
    public static String decodeSHA1(String text) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(text);
    }
}
