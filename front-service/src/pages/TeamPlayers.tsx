import { useParams } from "react-router-dom";
import { RootState, useAppDispatch } from "../redux/store";
import Header from "../shared/Header";
import { Table } from "flowbite-react";
import { useSelector } from "react-redux";
import not_found from "../assets/not_found.png";
import { useEffect, useState } from "react";
import { fetchAllTeamPlayers } from "../redux/teamPlayers/teamPlayersActions";
import DeleteModal from "../shared/DeleteModal";
import ITeamPlayers from "../interfaces/ITeamPlayers";

const TeamPlayers = () => {
  const [openDelete, setOpenDelete] = useState(false);
  const [selectedTeamPlayer, setSelectedTeamPlayer] = useState<ITeamPlayers>();
  const dispatch = useAppDispatch();
  const routeParams = useParams();
  const { teamPlayers } = useSelector((state: RootState) => state.teamPlayer);
  const players = teamPlayers.filter(
    (teamPlayer) => teamPlayer.id?.teamId === Number(routeParams.id)
  );

  useEffect(() => {
    dispatch(fetchAllTeamPlayers());
  }, []);
  return (
    <>
      <Header />
      <div className="overflow-x-auto pt-8 px-8">
        <Table hoverable>
          <Table.Head>
            <Table.HeadCell> First Name</Table.HeadCell>
            <Table.HeadCell> Last Name</Table.HeadCell>
            <Table.HeadCell>Weight</Table.HeadCell>
            <Table.HeadCell>Height</Table.HeadCell>
            <Table.HeadCell>Birthday</Table.HeadCell>
            <Table.HeadCell>Nationality</Table.HeadCell>
            <Table.HeadCell>Joining Date</Table.HeadCell>
            <Table.HeadCell>
              <span className="sr-only">Remove</span>
            </Table.HeadCell>
          </Table.Head>
          <Table.Body className="divide-y">
            {players.length > 0 ? (
              players.map((player) => (
                <Table.Row
                  key={player.player.id}
                  className="bg-white none:border-gray-700 none:bg-gray-800"
                >
                  <Table.Cell>{player.player.firstName}</Table.Cell>
                  <Table.Cell>{player.player.lastName}</Table.Cell>
                  <Table.Cell>{player.player.weight}</Table.Cell>
                  <Table.Cell>{player.player.height}</Table.Cell>
                  <Table.Cell>{player.player.birthday}</Table.Cell>
                  <Table.Cell>{player.player.nationality}</Table.Cell>
                  <Table.Cell>{player.joinedAt?.split("T")[0]}</Table.Cell>
                  <Table.Cell>
                    <span
                      onClick={() => {
                        setSelectedTeamPlayer(player);
                        setOpenDelete(true);
                      }}
                      className="font-medium cursor-pointer text-red-600 hover:underline none:text-cyan-500"
                    >
                      Remove
                    </span>
                  </Table.Cell>
                </Table.Row>
              ))
            ) : (
              <td className="flex justify-center items-center">
                <img
                  className="flex justify-center w-22 h-22"
                  src={not_found}
                  alt="not found"
                />
              </td>
            )}
          </Table.Body>
        </Table>
      </div>
      {openDelete ? (
        <DeleteModal
          open={openDelete}
          setOpen={setOpenDelete}
          element={selectedTeamPlayer as ITeamPlayers}
        />
      ) : (
        ""
      )}
    </>
  );
};

export default TeamPlayers;
