import { useEffect, useState } from "react";
import PlayerCard from "../components/PlayerCard";
import { RootState, useAppDispatch } from "../redux/store";
import Button from "../shared/Button";
import Header from "../shared/Header";
import { useSelector } from "react-redux";
import { fetchAllPlayers } from "../redux/players/playerActions";
import not_found from "../assets/not_found.png";
import PlayerModal from "../components/PlayerModal";

const Players = () => {
  const dispatch = useAppDispatch();
  const [open, setOpen] = useState(false);
  const { players, loading } = useSelector((state: RootState) => state.player);

  useEffect(() => {
    dispatch(fetchAllPlayers());
  }, []);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4 mx-6">
        <h2 className="text-xl font-semibold">Players</h2>
        <Button onClick={() => setOpen(true)} content="Add Player" />
      </div>

      {loading ? (
        <section className="bg-white dark:bg-gray-900">
          <div className="container px-6 py-10 mx-auto animate-pulse">
            <h1 className="w-48 h-2 mx-auto bg-gray-200 rounded-lg dark:bg-gray-700"></h1>

            <p className="w-64 h-2 mx-auto mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></p>
            <p className="w-64 h-2 mx-auto mt-4 bg-gray-200 rounded-lg sm:w-80 dark:bg-gray-700"></p>

            <div className="grid grid-cols-1 gap-8 mt-8 xl:mt-12 xl:gap-12 sm:grid-cols-2 lg:grid-cols-3">
              <div className="w-full ">
                <div className="w-full h-64 bg-gray-300 rounded-lg md:h-72 dark:bg-gray-600"></div>

                <h1 className="w-56 h-2 mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></h1>
                <p className="w-24 h-2 mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></p>
              </div>

              <div className="w-full ">
                <div className="w-full h-64 bg-gray-300 rounded-lg md:h-72 dark:bg-gray-600"></div>

                <h1 className="w-56 h-2 mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></h1>
                <p className="w-24 h-2 mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></p>
              </div>

              <div className="w-full ">
                <div className="w-full h-64 bg-gray-300 rounded-lg md:h-72 dark:bg-gray-600"></div>

                <h1 className="w-56 h-2 mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></h1>
                <p className="w-24 h-2 mt-4 bg-gray-200 rounded-lg dark:bg-gray-700"></p>
              </div>
            </div>
          </div>
        </section>
      ) : (
        <div className="grid gap-4 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center pt-4 items-center ml-8 my-4">
          {players.length > 0 ? (
            players.map((player) => (
              <PlayerCard key={player.id} player={player} />
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
      )}
      {open ? <PlayerModal open={open} setOpen={setOpen} /> : ""}
    </>
  );
};

export default Players;
