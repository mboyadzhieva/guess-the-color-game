# Guess the Color Game

## Rules
There're three levels of complexity:
- Easy: Consists of 10 rounds, each correct answer adds a 100 points to the Current score. On every third round, the number of colors to choose from increases by 1.
- Medium: Consists of 15 rounds, each correct answer adds 200 points to the Current score. On every third round, the number of colors to choose from increases by 1.
- Hard: Consists of 20 rounds, each correct answer adds 300 points to the Current score. On every fourth round, the number of colors to choose from increases by 1.  

Each incorrect answer, regardless of the complexity, decreases the score by 50 points.

At the end of the game, the Current score is compared to the Best score, and if the former is higher, the latter is replaced by the new value.

Upon finishing the game, the player gets a congratulations message and is prompted to play again, choosing a complexity once again.

## Application details

- Backend: Spring Boot, created with the help of [Spring Initializr](https://start.spring.io/).  
- Documentation of the available endpoints: [Swagger UI](https://guess-the-colour-game.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/).  
- Frontend: ReactJS, created via [Create React App](https://create-react-app.dev/) and with the help of [React Bootstrap](https://react-bootstrap.github.io/).  
- No database is used. The best score value is stored in the HttpSession. Everything else is generated every time the user requests it.

## Ways to check out the application
### 1. Access the deployed version at [Color Guessing Game](https://guess-the-colour-game.herokuapp.com/)
### 2. Download the source code and:
- Navigate to _color-guessing-game/_
- run `mvn spring-boot:run`
### 3. Screenshots:

- Home page: 

![Color-Guessing-Game-Home](https://user-images.githubusercontent.com/43497483/173827090-ec2bb469-d803-47c1-a1ee-a0b5731ffd8e.png)

- Choosing complexity:

![Color-Guessing-Game-Complexity](https://user-images.githubusercontent.com/43497483/173827390-5c15981d-632c-49e1-96f7-b224ae4d06a8.png)

- Starting a game: 
Depending on the chosen complexity, the number of rounds is different, as well as the points that a player wins on every correct answer.

![Color-Guessing-Game-Game-Start](https://user-images.githubusercontent.com/43497483/173827591-ee7b87a4-0ab6-4e40-876f-8c600b65203a.png)

- Finishing a game:
If that's the first game this user has played within the current Http session, the current score is automatically assigned as a best score, after the  congratulations message appears upon finishing the game. Now the player can choose to play another game with the same or a different complexity.

![Color-Guessing-Game-Game-End](https://user-images.githubusercontent.com/43497483/173827971-fcee6cc1-6f73-4e34-8032-8e8242c0b5be.png)

- New highscore:
After playing another game which ends with a better current score, the high score is updated. 

![Color-Guessing-Game-Highscore](https://user-images.githubusercontent.com/43497483/173828676-d8f9194d-31f4-4ed3-8cce-d3a750dfa68a.png)

