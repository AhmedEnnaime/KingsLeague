import { useSelector } from "react-redux";
import { PlayerCardProps } from "../propsTypes/PlayerCardProps";
import { RootState, useAppDispatch } from "../redux/store";
import { useEffect, useState } from "react";
import {
  fetchAllTeamPlayers,
  fetchTeamsByPlayerId,
} from "../redux/teamPlayers/teamPlayersActions";
import Button from "../shared/Button";
import PlayerModal from "./PlayerModal";
import DeleteModal from "../shared/DeleteModal";

const PlayerCard = ({ player }: PlayerCardProps) => {
  const dispatch = useAppDispatch();
  const [openUpdate, setOpenUpdate] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);
  const teamPlayers = useSelector(
    (state: RootState) => state.teamPlayer.teamPlayers
  );
  const filteredTeamPlayers = teamPlayers
    .filter((teamPlayer) => teamPlayer.player?.id === player.id)
    .sort((a, b) =>
      a.joinedAt && b.joinedAt
        ? new Date(b.joinedAt).getTime() - new Date(a.joinedAt).getTime()
        : 0
    );
  const lastTeamPlayer =
    filteredTeamPlayers.length > 0 ? filteredTeamPlayers[0] : null;
  const lastTeam = lastTeamPlayer ? lastTeamPlayer.team : null;

  useEffect(() => {
    dispatch(fetchAllTeamPlayers());
    dispatch(fetchTeamsByPlayerId(player.id as number));
  }, []);
  return (
    <>
      <div className="w-full max-w-sm overflow-hidden bg-white rounded-lg shadow-lg dark:bg-gray-800">
        <img
          className="object-cover object-center w-full h-56"
          src="https://fcb-abj-pre.s3.amazonaws.com/img/jugadors/MESSI.jpg"
          alt="avatar"
        />

        <div className="flex items-center px-6 py-3 bg-gray-900">
          <h1 className="mx-3 text-lg font-semibold text-white">
            {player.firstName} {player.lastName}
          </h1>
        </div>

        <div className="px-6 py-4">
          <h1 className="text-xl font-semibold text-gray-800 dark:text-white">
            {lastTeam ? lastTeam.name : "N/A"}
          </h1>
          <div className="flex items-center mt-4 text-gray-700 dark:text-gray-200">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              className="w-6 h-6"
            >
              <path
                fillRule="evenodd"
                d="M3 2.25a.75.75 0 0 1 .75.75v.54l1.838-.46a9.75 9.75 0 0 1 6.725.738l.108.054A8.25 8.25 0 0 0 18 4.524l3.11-.732a.75.75 0 0 1 .917.81 47.784 47.784 0 0 0 .005 10.337.75.75 0 0 1-.574.812l-3.114.733a9.75 9.75 0 0 1-6.594-.77l-.108-.054a8.25 8.25 0 0 0-5.69-.625l-2.202.55V21a.75.75 0 0 1-1.5 0V3A.75.75 0 0 1 3 2.25Z"
                clipRule="evenodd"
              />
            </svg>

            <h1 className="px-2 text-sm">{player.nationality}</h1>
          </div>

          <div className="flex items-center mt-4 text-gray-700 dark:text-gray-200">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              className="w-6 h-6"
            >
              <path d="m15 1.784-.796.795a1.125 1.125 0 1 0 1.591 0L15 1.784ZM12 1.784l-.796.795a1.125 1.125 0 1 0 1.591 0L12 1.784ZM9 1.784l-.796.795a1.125 1.125 0 1 0 1.591 0L9 1.784ZM9.75 7.547c.498-.021.998-.035 1.5-.042V6.75a.75.75 0 0 1 1.5 0v.755c.502.007 1.002.021 1.5.042V6.75a.75.75 0 0 1 1.5 0v.88l.307.022c1.55.117 2.693 1.427 2.693 2.946v1.018a62.182 62.182 0 0 0-13.5 0v-1.018c0-1.519 1.143-2.829 2.693-2.946l.307-.022v-.88a.75.75 0 0 1 1.5 0v.797ZM12 12.75c-2.472 0-4.9.184-7.274.54-1.454.217-2.476 1.482-2.476 2.916v.384a4.104 4.104 0 0 1 2.585.364 2.605 2.605 0 0 0 2.33 0 4.104 4.104 0 0 1 3.67 0 2.605 2.605 0 0 0 2.33 0 4.104 4.104 0 0 1 3.67 0 2.605 2.605 0 0 0 2.33 0 4.104 4.104 0 0 1 2.585-.364v-.384c0-1.434-1.022-2.7-2.476-2.917A49.138 49.138 0 0 0 12 12.75ZM21.75 18.131a2.604 2.604 0 0 0-1.915.165 4.104 4.104 0 0 1-3.67 0 2.605 2.605 0 0 0-2.33 0 4.104 4.104 0 0 1-3.67 0 2.605 2.605 0 0 0-2.33 0 4.104 4.104 0 0 1-3.67 0 2.604 2.604 0 0 0-1.915-.165v2.494c0 1.035.84 1.875 1.875 1.875h15.75c1.035 0 1.875-.84 1.875-1.875v-2.494Z" />
            </svg>

            <h1 className="px-2 text-sm">{player.birthday}</h1>
          </div>

          <div className="flex items-center mt-4 text-gray-700 dark:text-gray-200">
            <i className="fa-solid fa-weight-scale"></i>

            <h1 className="px-2 text-sm">{player.weight} KG</h1>
          </div>

          <div className="flex items-center mt-4 text-gray-700 dark:text-gray-200">
            <i className="fa-solid fa-person"></i>

            <h1 className="px-2 text-sm">{player.height} CM</h1>
          </div>
        </div>
        <div className="flex justify-between p-4">
          <Button onClick={() => setOpenUpdate(true)} content="Update Player" />
          <Button
            onClick={() => setOpenDelete(true)}
            bgColor="bg-red-600"
            content="Delete Player"
          />
        </div>
      </div>

      {openUpdate ? (
        <PlayerModal
          open={openUpdate}
          setOpen={setOpenUpdate}
          player={player}
        />
      ) : (
        ""
      )}
      {openDelete ? (
        <DeleteModal
          open={openDelete}
          setOpen={setOpenDelete}
          element={player}
        />
      ) : (
        ""
      )}
    </>
  );
};

export default PlayerCard;
