import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import UserList from "./components/UserList";
import AddUser from "./components/AddUser";
import UserCard from "./components/UserCard";

function App() {
  return (
      <Router>
        <div className="App">
          <Switch>
            <Route path="/add-user">
              <AddUser />
            </Route>

            <Route path="/user/:userId">
              <UserCard />
            </Route>

            <Route path="/">
              <UserList />
            </Route>
          </Switch>
        </div>
      </Router>
  );
}

export default App;
