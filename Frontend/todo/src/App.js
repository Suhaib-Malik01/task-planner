import { BrowserRouter, Route, Routes } from "react-router-dom";
import Nav from "./Components/Navbar";
import SignIn from "./Components/SignIn";
import SignUp from "./Components/SignUp";
import Home from "./Components/Home";

function App() {
  return (
    <div className="App">
      <Nav />

      <BrowserRouter>
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/SignUp" element={<SignUp />} />
          <Route path="/SignIn" element={<SignIn />} />
          <Route path="/Dashboard" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
