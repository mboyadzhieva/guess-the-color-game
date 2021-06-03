import { useState } from "react";
import {
  Button,
  ButtonGroup,
  Dropdown,
  Row,
  Container,
  Col,
  Greeting,
} from "react-bootstrap";
import "./App.css";

//function Ticker({ delay }) {
// const [count, setCount] = useState(0);
// const [timerId, setTimerId] = useState();

// const start = function () {
//   let timerId = setInterval(() => {
//     setCount((prevCount) => prevCount + 1);
//   }, delay);
//   setTimerId(timerId);
// };

// const stop = function () {
//   clearInterval(timerId);
//   setTimerId(null);
//   setCount(0);
// };

// return (
//   <div>
//     {timerId ? (
//       <button onClick={stop}>Stop</button>
//     ) : (
//       <button onClick={start}>Start</button>
//     )}
//   </div>
// );
//}

function App() {
  const [game, setGame] = useState();
  const [count, setCount] = useState(0);
  const [isWon, setIsWon] = useState(0);
  const [timerId, setTimerId] = useState();
  const [mainColor, setMainColor] = useState();
  const [sideColors, setSideColors] = useState();

  const startTimer = function () {
    let timerId = setInterval(() => {
      setCount((prevCount) => prevCount + 1);
    }, 250);
    setTimerId(timerId);
  };

  const startNewGame = async (complexity) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "text/plain" },
      body: complexity,
    };

    let response = await fetch("/api/games", requestOptions)
      .then((response) => response.json())
      .then((data) => {
        setGame(data);
        setMainColor(data.mainColor);
        setSideColors(data.colorsToChooseFrom);
        setIsWon(data.isWon);
        console.log(isWon);
      });

    startTimer();
  };

  const checkColor = async (color) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=UTF-8" },
      body: JSON.stringify({
        color: color,
        gameId: game.id,
      }),
    };

    let response = await fetch("/api/color", requestOptions)
      .then((response) => response.json())
      .then((data) => setGame(data));
  };

  let greeting;

  if (game && game.won) {
    console.log(game.won);
    greeting = "Congratulations";
  } else {
    greeting = "";
  }

  return (
    <div className="App">
      <Container fluid>
        {!game ? (
          <>
            <Row>
              <Col></Col>
              <h1>Welcome to Color Guessing Game</h1>
              <Col></Col>
            </Row>
            <Row>
              <Col></Col>
              <Dropdown as={ButtonGroup} onSelect={startNewGame}>
                <Button variant="success">Choose game complexity</Button>

                <Dropdown.Toggle
                  split
                  variant="success"
                  id="dropdown-split-basic"
                />

                <Dropdown.Menu>
                  <Dropdown.Item eventKey="Easy">Easy</Dropdown.Item>
                  <Dropdown.Item eventKey="Medium">Medium</Dropdown.Item>
                  <Dropdown.Item eventKey="Hard">Hard</Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
              <Col></Col>
            </Row>
          </>
        ) : (
          <>
            <Row className="statRows">
              <Col>
                <h7>Time:</h7> <h7>{count}</h7>
              </Col>
              <Col>
                <h7>Rounds:</h7> {game.currentRound}/{game.rounds}
              </Col>
              <Col>
                <h7>Score:</h7> {game.score}
              </Col>
              <Col>
                <h7>Current best:</h7> {game.bestScore}
              </Col>
            </Row>
            <Row>
              <Col></Col>
              <h1 style={{ color: game.mainColor }}>Which color is this?</h1>
              <Col></Col>
            </Row>
            <Row>
              <Col></Col>
              {game.colorsToChooseFrom.map((color) => (
                <Button
                  value={color}
                  onClick={() => checkColor(color)}
                  className="colorsToChooseFrom"
                  style={{ backgroundColor: color }}
                ></Button>
              ))}
              <Col></Col>
            </Row>
            <Row>{greeting}</Row>
          </>
        )}
      </Container>
    </div>
  );
}

export default App;
