import { useEffect, useState } from "react";
import TournamentCard from "../components/TournamentCard";
import Header from "../shared/Header";
import TournamentType from "../enums/TournamentType";
import Dropdown from "../shared/Dropdown";
import not_found from "../assets/not_found.png";
import { RootState, useAppDispatch } from "../redux/store";
import { useSelector } from "react-redux";
import { fetchAllTournaments } from "../redux/tournaments/tournamentActions";

const Tournaments = () => {
  const [selectedTournament, setSelectedTournament] =
    useState<TournamentType>();
  const tournaments = useSelector(
    (state: RootState) => state.tournament.tournaments
  );
  const dispatch = useAppDispatch();

  const handleTournamentSelect = (tournamentType: TournamentType) => {
    setSelectedTournament(tournamentType);
    console.log(selectedTournament);
  };

  useEffect(() => {
    dispatch(fetchAllTournaments());
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
