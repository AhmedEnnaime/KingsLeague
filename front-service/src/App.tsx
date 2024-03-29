import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./shared/Layout";
import Home from "./pages/Home";
import NotFound from "./components/NotFound";
import Tournaments from "./pages/Tournaments";
import Stadiums from "./pages/Stadiums";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import TournamentFixtures from "./pages/TournamentFixtures";
import Teams from "./pages/Teams";
import TeamPlayers from "./pages/TeamPlayers";
import Players from "./pages/Players";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <ToastContainer />
        <Routes>
          <Route path="/" element={<Layout />} />
          <Route index element={<Home />} />
          <Route path="/tournaments" element={<Tournaments />} />
          <Route path="/stadiums" element={<Stadiums />} />
          <Route path="/teams" element={<Teams />} />
          <Route path="/players" element={<Players />} />
          <Route path="/tournament/:id" element={<TournamentFixtures />} />
          <Route path="/team/:id/players" element={<TeamPlayers />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
