# REST API documentation

* _GET v1/items_
* _POST v1/sales/taxes_


## [GET] items

    GET v1/items

List all items.

### Response
Array list of:

- **id** — The item ID
- **name** — The item name
- **category** — The item category (BOOK, FOOD, MEDICAL or OTHER)
- **price** — The item price
- **imported** — True if the item is imported, false otherwise

### Errors
- **500** - Unhandled internal server error

### Example
**Request**

    https://localhost:9999/v1/items

**Return** 
``` json
[
    {
        "id": "67870ad6-bc7c-47cb-bcad-96b18b2366ad",
        "name": "book",
        "category": "BOOK",
        "price": 12.49,
        "imported": false
    },
    {
        "id": "1f8e66f8-07ef-4bb4-bd1d-c8ea7654818a",
        "name": "music CD",
        "category": "OTHER",
        "price": 14.99,
        "imported": false
    }
]
```

## [POST] sales taxes

    POST v1/sales/taxes

Calculate sales taxes for an item list.

### Body
Array list of:

- **item_id** - The item ID
- **amount** — The item amount

### Response
- **items** — Array list of:
    - **id** — The item ID
    - **name** — The item name
    - **category** — The item category (BOOK, FOOD, MEDICAL or OTHER)
    - **price** — The item price with sales taxes
    - **imported** — True if the item is imported, false otherwise
- **sales_taxes** — The total sales taxes amount
- **amount** — The total price amount

### Errors
- **400** - Invalid item ID or item amount
- **500** - Unhandled internal server error

### Example
**Request**

    https://localhost:9999/v1/sales/taxes

``` json
[
	{
		"item_id": "39d3f34d-1812-4b62-b2f0-cfcfa993aed0",
		"amount": 1
	},
	{
		"item_id": "80ed3311-5b14-43ee-80a0-59305ebda65e",
		"amount": 1
	},
	{
		"item_id": "b23cef17-ea11-4d0f-802c-956324d5ecfc",
		"amount": 1
	},
	{
		"item_id": "1aaec938-8e02-4379-b338-3b55f1275859",
		"amount": 1
	}
]
```

**Return** 
``` json
{
  "items": [
    {
      "id": "39d3f34d-1812-4b62-b2f0-cfcfa993aed0",
      "amount": 1,
      "name": "imported bottle of perfume",
      "category": "OTHER",
      "price": 54.65,
      "imported": true
    },
    {
      "id": "80ed3311-5b14-43ee-80a0-59305ebda65e",
      "amount": 1,
      "name": "bottle of perfume",
      "category": "OTHER",
      "price": 20.89,
      "imported": false
    },
    {
      "id": "b23cef17-ea11-4d0f-802c-956324d5ecfc",
      "amount": 1,
      "name": "packet of headache pills",
      "category": "MEDICAL",
      "price": 9.75,
      "imported": false
    },
    {
      "id": "1aaec938-8e02-4379-b338-3b55f1275859",
      "amount": 1,
      "name": "imported box of chocolates",
      "category": "FOOD",
      "price": 10.50,
      "imported": true
    }
  ],
  "sales_taxes": 9.55,
  "total": 95.79
}
```