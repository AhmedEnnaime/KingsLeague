import { useEffect, useState } from "react";
import TournamentCard from "../components/TournamentCard";
import Header from "../shared/Header";
import TournamentType from "../enums/TournamentType";
import Dropdown from "../shared/Dropdown";
import API from "../utils/API";
import ITournament from "../interfaces/ITournament";
import not_found from "../assets/not_found.png";

const Tournaments = () => {
  const [selectedTournament, setSelectedTournament] =
    useState<TournamentType>();
  const [tournaments, setTournaments] = useState<ITournament[]>();

  const handleTournamentSelect = (tournamentType: TournamentType) => {
    setSelectedTournament(tournamentType);
    console.log(selectedTournament);
  };

  const fetchTournaments = async () => {
    await API.get(`/TOURNAMENT-SERVICE/api/v1/tournaments`)
      .then((res) => {
        setTournaments(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    fetchTournaments();
  }, []);

  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl font-semibold">Tournaments</h1>
        <Dropdown onSelect={handleTournamentSelect} />
      </div>
      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center p-4 items-center">
        {tournaments ? (
          tournaments.map((tournament) => (
            <TournamentCard key={tournament.id} tournament={tournament} />
          ))
        ) : (
          <div className="flex justify-center items-center lg:col-span-3">
            <img
              className="flex justify-center w-22 h-22"
              src={not_found}
              alt="not found"
            />
          </div>
        )}
      </div>
    </>
  );
};

export default Tournaments;
