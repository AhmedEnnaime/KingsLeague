import { useSelector } from "react-redux";
import Fixture from "../components/Fixture";
import StandingsTable from "../components/StandingsTable";
import { RootState, useAppDispatch } from "../redux/store";
import Header from "../shared/Header";
import { useEffect } from "react";
import { fetchMatchDaysByTournamentId } from "../redux/matchDays/matchDayActions";
import { useParams } from "react-router-dom";
import not_found from "../assets/not_found.png";
import { fetchTournamentById } from "../redux/tournaments/tournamentActions";
import { fetchAllTournamentTeams } from "../redux/tournamentTeams/tournamentTeamsActions";
import Button from "../shared/Button";
// import TournamentType from "../enums/TournamentType";

const TournamentFixtures = () => {
  const matchDays = useSelector((state: RootState) => state.matchDay.matchDays);
  const tournamentTeams = useSelector(
    (state: RootState) => state.tournamentTeam.tournamentTeams
  );
  // const rounds = useSelector((state: RootState) => state.round.rounds);
  const tournament = useSelector(
    (state: RootState) => state.tournament.selectedTournament
  );
  const dispatch = useAppDispatch();
  const routeParams = useParams();

  const sortedMatchDays = matchDays.slice().sort((a, b) => {
    const dateA = new Date(a.date).getTime();
    const dateB = new Date(b.date).getTime();
    return dateA - dateB;
  });

  useEffect(() => {
    dispatch(fetchTournamentById(Number(routeParams.id)));
    dispatch(fetchMatchDaysByTournamentId(Number(routeParams.id as string)));
    // dispatch(fetchRoundsByTournamentId(Number(routeParams.id as string)));
    dispatch(fetchAllTournamentTeams());
    console.log(tournament);

    // if (tournament?.tournamentType == TournamentType.LEAGUE) {
    //   dispatch(fetchMatchDaysByTournamentId(Number(routeParams.id as string)));
    // }
  }, []);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4 w-full pt-8">
        <div className="flex flex-col w-full">
          <div className="flex justify-between p-4">
            <h2 className="text-xl font-semibold">MatchDays</h2>
            <Button content="Create MatchDay" />
          </div>
          {matchDays ? (
            sortedMatchDays.map((matchDay, index) => (
              <Fixture index={index} matchDay={matchDay} key={matchDay.id} />
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
        <div className="w-3/5">
          <StandingsTable tournamentTeams={tournamentTeams} />
        </div>
      </div>
    </>
  );
};

export default TournamentFixtures;
