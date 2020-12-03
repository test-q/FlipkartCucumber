Feature:  Product details page Test

 Scenario: Verify the title of product detail page.
 Given Browser is invoked.
 When User navigate to product detail page
 Then Validate page title.

 Scenario: Adding a product to cart
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When User select the colour/size
 And Click the add to Add to cart button
 Then The product is added to my shopping basket
 And User redirected to My cart page.

 Scenario: Try to add the same product to cart
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When User try to add same product in cart
 And Click the Go To Cart button
 Then The product is not added to my shopping basket
 And User redirected to My cart page.
 
 Scenario: Try to add the product to cart without selecting the size of the product
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When Click the add to Add to cart button
 Then Please select a size to proceed text message display

 Scenario: Text of the button change to Go To cart after adding product to cart
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When User select the colour/size
 And Click the add to Add to cart button
 And Go to same product detail page
 Then Add To cart button text change to Go To Cart

 Scenario: Non logged in user try to buy a product
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When User select the colour/size
 And Click the Buy now button
 Then User redirected to Checkout up page.
 
 Scenario: Non logged in user check all available offers
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When Click on More Offer link
 Then All the available offers are visible.
 
 
 Scenario: Non logged in user try to add product to wishlist
 Given Browser is invoked.
 And Non logged in user on a product detail page
 When Click on WishList icon
 Then Login pop is open

 




