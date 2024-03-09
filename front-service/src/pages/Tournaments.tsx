import { useState } from "react";
import TournamentCard from "../components/TournamentCard";
import Header from "../shared/Header";
import TournamentType from "../enums/TournamentType";
import Dropdown from "../shared/Dropdown";

const Tournaments = () => {
  const [selectedTournament, setSelectedTournament] = useState<TournamentType>(
    TournamentType.LEAGUE
  );

  const handleTournamentSelect = (tournamentType: TournamentType) => {
    setSelectedTournament(tournamentType);
    console.log(selectedTournament);
  };

  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl">Tournaments</h1>
        <Dropdown onSelect={handleTournamentSelect} />
      </div>
      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center pt-4 items-center">
        <TournamentCard />
        <TournamentCard />
        <TournamentCard />
      </div>
    </>
  );
};

export default Tournaments;
