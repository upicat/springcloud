# include provider & consumer.
# change port & name, and then start different instances.

start provider application with
-Dname=provider

start consumer application with
-Dprovider.name=provider -Dname=consumer