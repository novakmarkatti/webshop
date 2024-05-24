
# Webshop
The task is to create a webshop with limited functionality using Spring Boot (Maven) and Thymeleaf frameworks.

# General Coding Guidelines
- Adherence to Clean Code principles.
- Application of development best practices (e.g., extracting common code).

# General UI Expectations
- Header with content aligned centrally.
- Header Content:
  -- Icon to navigate to the user manager page.
  -- Cart icon with a number indicating the quantity of items, clicking on the cart leads to the shopping cart display page. (The number beside the cart should always display the current quantity)

# Screens to Implement
- Product Display Page (Homepage)
  -- Products should be displayed including name, price, quantity to purchase, and "Add to Cart" button.
- User Manager Page
  -- Display user's data initially, then billing and shipping addresses. Users should be able to modify these data (no need to add new data, only modification of existing ones).
  -- The cart content should not be lost when closing and reopening/refreshing the page.
- Shopping Cart Display Page
  -- Display items in the cart with quantities and total prices. Users should be able to modify the quantity and remove items from the list.
  -- Display total amount.
  -- Include a button "Enter Shipping Information" to navigate to the Billing and Shipping Information page.
- Billing and Shipping Information Page
  -- Display billing and shipping information.
  -- Button "Proceed to Checkout" should lead to the Summary and Checkout Page.
- Summary and Checkout Page
  -- User's details on top.
  -- Billing and shipping information.
  -- Ordered products.
  -- Total amount.
  -- "Place Order" button (This should only update the timestamp of order completion in the DB, indicating the end of the order. After this, if we open the application again, there should be a completely empty new cart.)
