package BusInfo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Burhan N on 8/7/2016.
 * Edited by Jessica N on 8/7/2016.
 * 3x5 multidimensional array to store bus route sizes
 * BST to store actual stop info
 * Nested for loops to access all of the stop info and push to BST
 * Display prints all the buses sorted by soonest arrival time
 */
public class BusInfoDriver {
    private final String hostUrl = "http://api.pugetsound.onebusaway.org/api/where/schedule-for-stop/1_76305.json?key=65f351d5-6625-4a62-af86-88c05ae26b54";
    private BusInfo busInfo;
    private Node root;

    public BusInfoDriver(){
        try{
            URL url = new URL(hostUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            busInfo = (new Gson().fromJson(rootObj, BusInfo.class));
        }catch(Exception e){
            e.printStackTrace();
        }
        root = null;
    }

    public BusInfo getData(){
        return busInfo;
    }

    public Node getRoot(){
        return root;
    }

    //insert the bus info to the BSTree
    public void insert(String desc, long time, int bus){
        Node newNode = new Node(desc, time, bus);
        if (root == null){
            root = newNode;
            return;
        }

        Node current = root;
        Node parent;
        while(true){
            parent = current;
            if (time<current.time){
                current = current.left;
                if(current==null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public class Node {
        private String desc;
        private long time;
        private int bus;
        private Node left;
        private Node right;

        Node(String desc, long time, int bus){
            this.desc = desc;
            this.time = time;
            this.bus = bus;
            left = null;
            right = null;
        }

        public String getDesc(){
            return desc;
        }

        public long getTime(){
            return time;
        }

        public int getBus(){
            return bus;
        }

        public BusInfoDriver.Node getLeft(){
            return left;
        }

        public BusInfoDriver.Node getRight(){
            return right;
        }
    }
}

