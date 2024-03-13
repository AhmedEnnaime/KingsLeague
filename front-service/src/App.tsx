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
          <Route path="/tournament/:id" element={<TournamentFixtures />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
