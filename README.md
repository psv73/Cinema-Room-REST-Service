# Cinema-Room-REST-Service
You need to create a REST service that will return information about the cinema in JSON format.

1. Implement the /purchase endpoint that handles POST requests and marks a booked ticket as purchased.

A request should contain the following data:

row — the row number;
column — the column number.
Take these variables and check if the specified ticket is available. If the ticket is booked, mark the seat as purchased and don't show it in the list.

If the purchase is successful, the response body should be as follows:

	{
		"row": 5,
		"column": 7,
		"price": 8
	}
The ticket price is determined by a row number. If the row number is less or equal to 4, set the price at 10. All other rows cost 8 per seat.

If the seat is taken, respond with a 400 (Bad Request) status code. The response body should contain the following:

	{
 		"error": "The ticket has been already purchased!"
	}
If users pass a wrong row/column number, respond with a 400 status code and the following line:

	{
		"error": "The number of a row or a column is out of bounds!"
	}
Show the ticket price when the /seats endpoint is accessed.

2. Implement the /seats endpoint that handles GET requests and returns the information about the movie theatre.

Change the JSON response when a customer purchases a ticket by making a POST request to the /purchase endpoint. Turn it into the following format:

	{
		"token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
		"ticket": {
 			"row": 1,
			"column": 1,
			"price": 10
    	}
	}

3. Implement the /return endpoint, which will handle POST requests and allow customers to refund their tickets.

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

4. Implement the /stats endpoint that will handle GET requests with URL parameters. If the URL parameters contain a password key with a super_secret value, return the movie theatre statistics in the following format:

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
