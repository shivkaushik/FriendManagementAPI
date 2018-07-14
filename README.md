# FriendManagementAPI

Git URL : https://github.com/shivkaushik/FriendManagementAPI.git


1.Request for New User Friend :

                     Method: POST
                     Postman URL: http://localhost:8080/userFriendRequest
                     Request Body: 
                                  { 

                                    "friends":
                                            [
                                             "shiv.kaushik@gmail.com",
                                             "ravi.sinde@gmail.com"
                                            ]
                                  }

2.Request for Get User Friend List :

                      Method: POST
                      Postman URL: http://localhost:8080/getUserFriendList
                      Request Body: 
                                {
                                  "email":shiv.kaushik@gmail.com
                                }
                                
                                
3.Request for Get User Common Friends :
                      
                      Method: POST
                      Postman URL: http://localhost:8080/getCommonUserFriends
                      Request Body: 
                                { 
                                  "friends":
                                  [
                                    "meena.verma@gmail.com",
                                    "shiv.kaushik@gmail.com"
                                  ]
                                }


4.Request for Subscribe User Friends :
                      
                      Method: POST
                      Postman URL: http://localhost:8080/subscribeUserRequest
                      Request Body: 
                                  {
                                  "requestor":"shiv.kaushik@gmail.com",
                                  "target":"ravi.sinde@gmail.com"
                                  }
  
5.Request for Block User Friend :
                      
                      Method: POST
                      Postman URL: http://localhost:8080/blockUserRequest
                      Request Body: 
                                  {
                                  "requestor":"kalpesh.sambre@gmail.com",
                                  "target":"ravi.sinde@gmail.com"
                                  }
