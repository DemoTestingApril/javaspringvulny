package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Test {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String query = req.getParameter("query");

        MongoClient mongoClient = new MongoClient();
        DB database             = mongoClient.getDB("db");
        DBCollection collection = database.getCollection("coll");
        BasicDBObject query     = new BasicDBObject();

        // ok: nosql-injection-servlets
        query.put("test", input);
    }

    protected void doGet2(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String input = req.getParameter("query");

        MongoClient mongoClient = new MongoClient();
        DB database             = mongoClient.getDB("db");
        DBCollection collection = database.getCollection("coll");
        BasicDBObject query     = new BasicDBObject();

        // ruleid: nosql-injection-servlets
        query.put("$where", "this.test == \"" + input + "\""); // Noncompliant
    }

    protected void doGet3(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String input = req.getParameter("query");

        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("test");
        DBCollection coll = db.getCollection("testCollection");    
        // ruleid: nosql-injection-servlets
        BasicDBObject query = new BasicDBObject("_id", "this.field1 == \"" + input + "\"");
        DBCursor cursor = coll.find(query);
    }

    protected void doGet3(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String input = req.getParameter("query");

        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("test");
        DBCollection coll = db.getCollection("testCollection");    
        // ok: nosql-injection-servlets
        BasicDBObject query = new BasicDBObject("_id", "this.field1 == \"" + (input != null) + "\"");
        DBCursor cursor = coll.find(query);
    }
}

// Additional NoSQL injection sample for fresh finding
class TestExtra {
    protected void doExtra(HttpServletRequest req, HttpServletResponse resp) {
        String input = req.getParameter("q");
        MongoClient client = new MongoClient();
        DB db = client.getDB("d");
        DBCollection c = db.getCollection("c");
        // ruleid: nosql-injection-servlets
        BasicDBObject q = new BasicDBObject("$where", "this.f == \"" + input + "\"");
        c.find(q);
    }
}

// Test 2 - SSRF pattern
class TestSsrf {
    public void fetch(HttpServletRequest req) throws Exception {
        String url = req.getParameter("url");
        java.net.URL u = new java.net.URL(url);
        u.openStream();
    }
}

// Test 2b - guaranteed NoSQL injection via $where
class TestNoSql2 {
    protected void doMore(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String input = req.getParameter("q");
        MongoClient client = new MongoClient();
        DB db = client.getDB("d2");
        DBCollection c = db.getCollection("c2");
        BasicDBObject q = new BasicDBObject();
        // ruleid: nosql-injection-servlets
        q.put("$where", "this.x == \"" + input + "\"");
        c.find(q);
    }
}
