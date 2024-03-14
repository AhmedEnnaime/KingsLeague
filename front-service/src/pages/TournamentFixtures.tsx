import { useSelector } from "react-redux";
import MatchDay from "../components/MatchDay";
import StandingsTable from "../components/StandingsTable";
import { RootState, useAppDispatch } from "../redux/store";
import Header from "../shared/Header";
import { useEffect } from "react";
import { fetchMatchDaysByTournamentId } from "../redux/matchDays/matchDayActions";
import { useParams } from "react-router-dom";
import not_found from "../assets/not_found.png";
import { fetchTournamentById } from "../redux/tournaments/tournamentActions";
// import TournamentType from "../enums/TournamentType";

const TournamentFixtures = () => {
  const matchDays = useSelector((state: RootState) => state.matchDay.matchDays);
  // const tournament = useSelector(
  //   (state: RootState) => state.tournament.selectedTournament
  // );
  const dispatch = useAppDispatch();
  const routeParams = useParams();

  useEffect(() => {
    console.log(Number(routeParams.id as string));

    dispatch(fetchTournamentById(Number(routeParams.id as string)));
    dispatch(fetchMatchDaysByTournamentId(Number(routeParams.id as string)));

    // if (tournament?.tournamentType == TournamentType.LEAGUE) {
    //   dispatch(fetchMatchDaysByTournamentId(Number(routeParams.id as string)));
    // }
  }, []);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4 w-full pt-8">
        <div className="flex flex-col w-full">
          {matchDays ? (
            matchDays.map((matchDay) => <MatchDay key={matchDay.id} />)
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
          <StandingsTable />
        </div>
      </div>
    </>
  );
};

export default TournamentFixtures;
