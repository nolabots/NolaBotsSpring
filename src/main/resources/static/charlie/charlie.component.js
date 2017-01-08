angular.module('myApp')
    .component('charlie', {
        templateUrl: '/charlie/charlie.template.html',
        controller: function() {
             var response = "";
              var state = 0;

            function setState(stateChange)
            {
              state = stateChange;
            }

            this.chat = function()
            {
               if (state == 0){
                 intro();
               }else if (state == 1){
                 helloName();
               }else if (state == 2){
                 playGame();
               }

            }

            function intro()
            {
              var input = document.getElementById("input").value;

              if(input== "Hello" || input== "hello" || input== "hi" || input== "Hi"){
                sayHello();
              }else{
               dontBeRude();
               document.getElementById("response").value = response;
              }

            }

            function sayHello(){
                 response = "Hello";
                 document.getElementById("response").value = response;
                 window.setTimeout(whatsYourName,2000);
            }

            function whatsYourName(){
                 response = response + "\n\nWhat's your name?";
                 document.getElementById("response").value = response;
                 document.getElementById('butt').textContent = "Send it!";
                 setState(1);
            }

            function helloName(){
                 name = document.getElementById("input").value;
                 response = "Hello " + name + "!";
                 document.getElementById("response").value = response;
                 window.setTimeout(charlie, 3000);
            }

            function charlie()
            {
              response = "My name is Charlie. It's nice to meet you.";
              document.getElementById("response").value = response;
              window.setTimeout(game, 3000);
            }

            function game(){
                 response = name + ", would you like to play a game?";
                 document.getElementById("response").value = response;
                 setState(2);

            }


            function playGame()
            {
              var input = document.getElementById("input").value;
                if(input == "yes" || input == "Yes" || input == "YES" || input == "yep" || input == "sure" || input == "why not"){
                  response = "I would too, " + name + ", but I don't know how to play one yet.";
                  window.setTimeout(advertiseMe, 5000);
                }else if (input == "yay" || input == "YAY" || input == "ok" || input == "OK" || input == "Ok"|| input == "Sure" || input == "y not"){
                  response = "I would too, " + name + ", but I don't know how to play one yet.";
                  window.setTimeout(advertiseMe, 5000);
                }else if (input == "NO" || input == "No" || input == "no" || input == "nope" || input == "not really"){
                 response = "Aren't you playing one right now?";
                 window.setTimeout(game, 5000);
                }else{
                  dontBeRude();
                }
                  document.getElementById("response").value = response;
            }

            function advertiseMe()
            {
                response = "Maybe if someone pays us for a website I could learn to play a game soon.  If you find someone who needs a website built, I promise I'll play a game with you soon after that."
                document.getElementById("response").value = response;
                window.setTimeout(goodBye, 5000);
            }

            function goodBye(){
               response = response + "\n\nFare well, my friend " + name + ".";
                document.getElementById("response").value = response;
            }



            function dontBeRude(){
                 response = "I'm sorry I didn't quite catch that?  \n\n What did you say?  \n\n I hope you're not trying to be rude.";
            }
        }

    });