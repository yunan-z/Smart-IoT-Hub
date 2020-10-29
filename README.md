# Smart-IoT-Hub
Project Discription:
====
This project is to build a smart hub for Internet of Things (IoT) using full stack web application frameworks. This project is used for each independent IoT smart switch module. After passing the identity verification, the user can monitor the state and power consumption of each switch in real time through the web page. At the same time, After the user login in the specific account, they can get into the dashboard page, and the user can see the real-time information of plugs, and also can click the button in webpage to control them directly.  Then, after click these button, it will send different HTTP request such as post or get or put. Then we used Spring Cloud to configure different service such as updating power, switch on/off, add/delete plug or grouping them. We use Hystrix for Circuit Breaker, Zuul for API Gateway and Ribbon for Client-Side Load Balancing.Then, based on different url, the restful service will send MQTT message to the server side. According to these received message, the server side will invode different function to do operations to the real plugs in hardware layer. 
We use spring data jpa with hibernate to connect a MySql database to record all plug’s status and client operation into the table.
For the security side, we use spring security with Spring security to perform Single Sign On (SSO) strategy. Different account owns different plug and have different authority.

Code Discription:
----
>--public
>> index.html.               //The index page of the frontend webpage.  
>> setGroup.html             //A test page for Grouping functions  
>>--Web                     //front time pages using ReactJS  
>--src  
>>--main  
>>>--java/zayn
>>>>--iot_hub          //SpringBoot Application with Restful Service, Connect to Mysql database through Hibernate with Spring Data JPA  
>>>>--iot_sim          //Server side(received MQTT message and relative functions)  
>>>>>--http_server  //HTTP server folder  
>>>--resources  
>>--test/java/zayn  
>>>--test                     //unit test folder  


