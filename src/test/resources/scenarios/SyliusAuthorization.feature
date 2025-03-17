Feature: Everything related to login is tested

  Background: The browser opens and goes to the web page
    Given Open webSite "https://v2.demo.sylius.com/en_US/"

    Scenario: Login to the website using the data LogIn
      When Click LogIn button
      And Read and write the LogIn and password data
      And Put "logIn" and "password" to fields
      And Click LogIn button on LogIn page
      Then Checking correct LogIn "Hello John!"
      And Click LogOut button and checking output
