Prerequisite:
- MongDB, RabbitMQ, Java compiler (IntelliJ prefered)

Instructions:
- Setup Local Host for RabbitMQ and MongoDB
- Clear all queues, exchanges, collections
- Place mtl data file in the "docs" file (only grade, health and temperature will be written to database)
- To run test, please run AppTest class
- To run the application with simple gui run GUI class
- Check preformence through RabbitMQ and MongoDB


Note:
- The application is given a 5 sec delay before terminating for the wtiting process to complete.
- If more time is needed, please change the "DEALY" number in Consumer class.
