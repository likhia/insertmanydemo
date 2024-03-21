# Create validate rule in MongoDB compass
{
  $jsonSchema: {
     required: [ "message", "imageURI" ],
     properties: {
        message: {
           bsonType: "string",
           minLength : 10,
           description: "must be a string and is required"
        }
     }
  }
}

#Create new collection with validate rule in Mongosh
db.createCollection("Notification", {
   validator: {
      $jsonSchema: {
            required: [ "message", "imageURI", "schema_version" ],
            properties: {
               message: {
                  bsonType: "string",
                  minLength : 10,
                  description: "must be a string and is required"
               }
            }
         }
      }
})

# For other purpose
db.createCollection("test", {
   validator: {
     "$and": [
       {
           "schema_version" : "1"
       },
       // Validation with JSON Schema
       {
         $jsonSchema: {
               required: [ "message", "imageURI", "schema_version" ],
               properties: {
                  message: {
                     bsonType: "string",
                     minLength : 10,
                     description: "must be a string and is required"
                  }
               }
            }
        }
      ]
    }
  }
)


db.createCollection("test", {
   validator: {
     "$or" : [
      {
         "$and": [
                  {
                     "schema_version" : 1
                  },
                  // Validation with JSON Schema
                  {
                     $jsonSchema: {
                           required: [ "message", "imageURI", "schema_version" ],
                           properties: {
                              message: {
                                 bsonType: "string",
                                 minLength : 10,
                                 description: "must be a string and is required"
                              }
                           }
                        }
                  }
               ]
      },
      {
         "$and": [
                  {
                     "schema_version" : 2
                  },
                  // Validation with JSON Schema
                  {
                     $jsonSchema: {
                           required: [ "message", "imageURI", "schema_version" ],
                           properties: {
                              message: {
                                 bsonType: "string",
                                 minLength : 5,
                                 description: "must be a string and is required"
                              }
                           }
                        }
                  }
               ]
      },
     ]
     
    }
  }
)

