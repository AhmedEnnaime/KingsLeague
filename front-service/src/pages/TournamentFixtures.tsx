import { useSelector } from "react-redux";
import Fixture from "../components/Fixture";
import StandingsTable from "../components/StandingsTable";
import { RootState, useAppDispatch } from "../redux/store";
import Header from "../shared/Header";
import { useEffect, useState } from "react";
import { fetchMatchDaysByTournamentId } from "../redux/matchDays/matchDayActions";
import { useParams } from "react-router-dom";
// import not_found from "../assets/not_found.png";
import { fetchAllTournamentTeams } from "../redux/tournamentTeams/tournamentTeamsActions";
import Button from "../shared/Button";
import FixtureModal from "../components/FixtureModal";
import TournamentType from "../enums/TournamentType";
import { fetchRoundsByTournamentId } from "../redux/rounds/roundActions";
import { fetchTournamentById } from "../redux/tournaments/tournamentActions";
import ITournament from "../interfaces/ITournament";

const TournamentFixtures = () => {
  const [openFixtureModal, setOpenFixtureModal] = useState(false);
  const [tournament, setTournament] = useState<ITournament>();
  const routeParams = useParams();
  const dispatch = useAppDispatch();
  const matchDays = useSelector((state: RootState) => state.matchDay.matchDays);
  const allTournamentTeams = useSelector(
    (state: RootState) => state.tournamentTeam.tournamentTeams
  );
  // const tournamentTeams = allTournamentTeams.filter(
  //   (tournamentTeam) => tournamentTeam.tournament?.id === Number(routeParams.id)
  // );
  const rounds = useSelector((state: RootState) => state.round.rounds);

  const sortedMatchDays = matchDays.slice().sort((a, b) => {
    const dateA = new Date(a.date).getTime();
    const dateB = new Date(b.date).getTime();
    return dateA - dateB;
  });

  const sortedRounds = rounds.slice().sort((a, b) => {
    const dateA = new Date(a.date).getTime();
    const dateB = new Date(b.date).getTime();
    return dateA - dateB;
  });

  useEffect(() => {
    dispatch(fetchAllTournamentTeams());
    dispatch(fetchTournamentById(Number(routeParams.id))).then((res) => {
      setTournament(res.payload as ITournament);
      console.log(res.payload);
    });
    dispatch(fetchMatchDaysByTournamentId(Number(routeParams.id as string)));
    dispatch(fetchRoundsByTournamentId(Number(routeParams.id as string)));
  }, []);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4 w-full pt-8">
        <div className="flex flex-col w-full">
          <div className="flex justify-between p-4">
            <h2 className="text-xl font-semibold">
              {tournament && tournament?.tournamentType == TournamentType.LEAGUE
                ? "MatchDays"
                : "Rounds"}
            </h2>
            <Button
              onClick={() => setOpenFixtureModal(true)}
              // onClick={() => test}
              content={
                tournament &&
                tournament?.tournamentType == TournamentType.LEAGUE
                  ? "Create MatchDay"
                  : "Create Round"
              }
            />
          </div>
          {tournament && tournament?.tournamentType == TournamentType.LEAGUE
            ? sortedMatchDays.map((matchDay, index) => (
                <Fixture index={index} matchDay={matchDay} key={matchDay.id} />
              ))
            : sortedRounds.map((round, index) => (
                <Fixture index={index} round={round} key={round.id} />
              ))}
        </div>
        <div className="w-3/5">
          <StandingsTable tournamentTeams={allTournamentTeams} />
        </div>
      </div>
      {openFixtureModal ? (
        <FixtureModal
          open={openFixtureModal}
          setOpen={setOpenFixtureModal}
          tournament={tournament}
        />
      ) : (
        ""
      )}
    </>
  );
};

export default TournamentFixtures;
