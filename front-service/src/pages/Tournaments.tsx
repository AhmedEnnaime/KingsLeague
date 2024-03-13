import { useEffect, useState } from "react";
import TournamentCard from "../components/TournamentCard";
import Header from "../shared/Header";
import not_found from "../assets/not_found.png";
import { RootState, useAppDispatch } from "../redux/store";
import { useSelector } from "react-redux";
import { fetchAllTournaments } from "../redux/tournaments/tournamentActions";
import TournamentModal from "../components/TournamentModal";
import Button from "../shared/Button";

const Tournaments = () => {
  const [open, setOpen] = useState(false);
  const tournaments = useSelector(
    (state: RootState) => state.tournament.tournaments
  );
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(fetchAllTournaments());
  }, [open]);

  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl font-semibold">Tournaments</h1>
        <Button
          onClick={() => {
            setOpen(true);
          }}
          content="Create Tournament"
        />
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
      {open ? <TournamentModal open={open} setOpen={setOpen} /> : ""}
    </>
  );
};

export default Tournaments;
