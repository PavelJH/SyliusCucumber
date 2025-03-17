Feature: Everything related to login is tested

  Background: The browser opens and goes to the web page
    Given Open webSite "https://v2.demo.sylius.com/en_US/"

    Scenario: Login to the sylius website using the data LogIn
      When Click LogIn button
      And Read and write the LogIn and password data
      And Put "Email" and "password" to fields
      And Click LogIn button on LogIn page
      Then Checking correct LogIn "Hello John!"
      And Click LogOut button and checking output

    Scenario: Login to the sylius website using the wrong email
      When Click LogIn button
      And Read and write the LogIn and password data
      And Put WrongEmail and "password" to fields
      And Click LogIn button on LogIn page
      Then checking Error

      @Tag("negative)
    Scenario: Login to the website using the wrong password
      When Click LogIn button
      And Read and write the LogIn and password data
      And Put "Email" and wrongPassword to fields
      And Click LogIn button on LogIn page
      Then checking Error

    Scenario: Creating new user on the sylius website
      When Click LogIn button
      And Click Register here button
      And Put firstName, LastName, Email, PhoneNumber, password, VerificationPassword
      And Click Create an account
      Then checking Success status

