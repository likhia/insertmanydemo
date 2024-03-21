package com.ocbc.demo.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.bulk.BulkWriteInsert;
import com.mongodb.bulk.BulkWriteResult;
import com.ocbc.data.Notification;

/**
 * This is to test how to implement insertMany.   This will insert document (Notification) into OCBC database.
 * 
 * Please add the 1st validation rule using MongoDB compass or when creating collection in mongosh.   Please refer to README.md.
 */
@Component
public class NotificationRepoImpl implements NotificationRepo {

   @Autowired
   MongoTemplate template;

   //Please note that this is not a production quality code.  It is just for testing purpose. 
   //This is to test with transactional boundary.   All the messages will rollback if there is error.    
   @Transactional
   public String insertNotification2() {
        int size = 10;
        BulkOperations bulkInsertion = template.bulkOps(BulkOperations.BulkMode.UNORDERED, Notification.class);
        List<Notification> docs = new ArrayList<Notification>();
        
        for(int i=0; i<size; i++) {

            Notification n = null;
            
            if(i == 5)
                n = new Notification("test", "http://test_" + i, i);
            else
                n = new Notification("this is to inform you that the CPU utilisation of the xxx server has reached 100%", "http://image_" + i, i);

            bulkInsertion.insert(n);
            docs.add(n);

        }

        BulkWriteResult result;
        try {
            result = bulkInsertion.execute();
            System.out.print(result.getInsertedCount());
            

        } catch(org.springframework.data.mongodb.BulkOperationException e) {
            e.printStackTrace();
            System.out.print(e.getResult().getInsertedCount()); 
        }

        return "OK";
   }

   //Please note that this is not a production quality code.  It is just for testing purpose. 
   //This is to test with no transactional boundary.   With unordered as the bulk mode,  the execution will continue even there is error with one or more of the documents.  
   public String insertNotification() {
        int size = 30;
        BulkOperations bulkInsertion = template.bulkOps(BulkOperations.BulkMode.UNORDERED, Notification.class);
        List<Notification> docs = new ArrayList<Notification>();
        
        for(int i=0; i<size; i++) {

            Notification n = null;
            
            if(i == 5 || i == 12)
                n = new Notification("test", "http://test_" + i, i);
            else
                n = new Notification("this is to inform you that the CPU utilisation of the xxx server has reached 100%", "http://image_" + i, i);

            bulkInsertion.insert(n);
            docs.add(n);

        }

        BulkWriteResult result;
        try {
            result = bulkInsertion.execute();
            System.out.print(result.getInsertedCount());
            

        } catch(org.springframework.data.mongodb.BulkOperationException e) {
            e.printStackTrace();

            
            System.out.print("=========== " + e.getResult().getInsertedCount() + "  insert array" + e.getResult().getInserts().size());

            // Getting the result of the inserts.  Please NOTE that this array (writeinserts) will only include the insert that are successful.   
            // For example,  1 , 2, 3, 4, 5.  If fail to insert 3, the array returned will be 1, 2, 4, 5.  So some manual handling is required if you want 
            // to retry the failed insert.   
            // I have created another array called docs to keep all the insert doc so it can be used to retrieve the failed doc to retry.  
            // The logic is not implemented in this example.   It is the idea.  
            List<BulkWriteInsert> writeinserts = e.getResult().getInserts();
            for( BulkWriteInsert w: writeinserts) {
                System.out.print(w.getIndex() + "  " + w.getId() + " ");
                System.out.println(docs.get(w.getIndex()).getImageURI());
            }
        }

        return "OK";
    }

}