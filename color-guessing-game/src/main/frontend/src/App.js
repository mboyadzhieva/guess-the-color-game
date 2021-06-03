import { useState } from "react";
import {
  Button,
  ButtonGroup,
  Dropdown,
  Row,
  Container,
  Col,
} from "react-bootstrap";
import React from "react";
import Typical from "react-typical";
import "./App.css";

function App() {
  const [game, setGame] = useState();

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
      });
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
            <br></br>
            <Row>
              <Col></Col>
              {game.won ? (
                <Typical
                  className="congratsText"
                  steps={["Congratulations, you won! Play again?", 5000]}
                />
              ) : (
                <div></div>
              )}
              <Col></Col>
            </Row>
            <Row>
              <Col></Col>
              <Col>
                {game.won ? (
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
                ) : (
                  <div></div>
                )}
              </Col>
              <Col></Col>
            </Row>
          </>
        )}
      </Container>
    </div>
  );
}

export default App;
