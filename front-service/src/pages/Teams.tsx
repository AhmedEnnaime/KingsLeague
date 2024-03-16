import { useEffect, useState } from "react";
import Button from "../shared/Button";
import Header from "../shared/Header";
import { useSelector } from "react-redux";
import { RootState, useAppDispatch } from "../redux/store";
import { fetchAllTeams } from "../redux/teams/teamActions";
import TeamCard from "../components/TeamCard";
import not_found from "../assets/not_found.png";
import TeamModal from "../components/TeamModal";

const Teams = () => {
  const [open, setOpen] = useState(false);
  const teams = useSelector((state: RootState) => state.team.teams);
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(fetchAllTeams());
  }, []);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl font-semibold">Teams</h1>
        <Button
          onClick={() => {
            setOpen(true);
          }}
          content="Create Team"
        />
      </div>
      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center items-center p-4">
        {teams ? (
          teams.map((team) => <TeamCard team={team} key={team.id} />)
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
      {open ? <TeamModal open={open} setOpen={setOpen} /> : ""}
    </>
  );
};

export default Teams;
