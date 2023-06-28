# Cinema-Room-REST-Service

Change the JSON response when a customer purchases a ticket by making a POST request to the /purchase endpoint. Turn it into the following format:

	{
    	"token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
	    "ticket": {
    	    "row": 1,
        	"column": 1,
	        "price": 10
    	}
	}

Implement the /return endpoint, which will handle POST requests and allow customers to refund their tickets.

The request should have the token feature that identifies the ticket in the request body. Once you have the token, you need to identify the ticket it relates to and mark it as available. The response body should be as follows:

	{
    	"returned_ticket": {
	    "row": 1,
        "column": 1,
       	"price": 10
	    }
	}

If you cannot identify the ticket by the token, make your program respond with a 400 status code and the following response body:

	{
    	"error": "Wrong token!"
	}

Implement the /stats endpoint that will handle GET requests with URL parameters. If the URL parameters contain a password key with a super_secret value, return the movie theatre statistics in the following format:

	{
    	"current_income": 0,
	    "number_of_available_seats": 81,
	    "number_of_purchased_tickets": 0
	}
Take a look at the description of keys:

	current_income — shows the total income of sold tickets.
	number_of_available_seats — shows how many seats are available.
	number_of_purchased_tickets — shows how many tickets were purchased.
If the parameters don't contain a password key or a wrong value has been passed, respond with a 401 status code. The response body should contain the following:

	{
    	"error": "The password is wrong!"
	}
