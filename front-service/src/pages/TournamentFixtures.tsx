import MatchDay from "../components/MatchDay";
import StandingsTable from "../components/StandingsTable";
import Header from "../shared/Header";

const TournamentFixtures = () => {
  return (
    <>
      <Header />
      <div className="flex justify-between p-4 w-full pt-8">
        <div className="flex flex-col w-full">
          <MatchDay />
          <MatchDay />
          <MatchDay />
        </div>
        <div className="w-3/5">
          <StandingsTable />
        </div>
      </div>
    </>
  );
};

export default TournamentFixtures;
