import { useParams } from "react-router-dom";
import logo from "../assets/team_logo.png";
import { StandingsTableProps } from "../propsTypes/StandingsTableProps";
import Button from "../shared/Button";
import { useEffect, useState } from "react";
import { useAppDispatch } from "../redux/store";
import { fetchAllTournamentTeams } from "../redux/tournamentTeams/tournamentTeamsActions";
import RegisterTeamModal from "./RegisterTeamModal";
import DeleteModal from "../shared/DeleteModal";
import ITournamentTeams from "../interfaces/ITournamentTeams";

const StandingsTable = ({ tournamentTeams }: StandingsTableProps) => {
  const [open, setOpen] = useState(false);
  const [openRemove, setOpenRemove] = useState(false);
  const [selectedTournamentTeam, setSelectedTournamentTeam] =
    useState<ITournamentTeams>();
  const routeParams = useParams();
  const registeredTeams = tournamentTeams
    .filter(
      (tournamentTeam) =>
        tournamentTeam.id?.tournamentId === Number(routeParams.id)
    )
    .sort((a, b) => (b.points as number) - (a.points as number));
  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(fetchAllTournamentTeams());
  }, [open]);

  const showPointsColumn =
    tournamentTeams &&
    tournamentTeams.length > 0 &&
    tournamentTeams[0].tournament?.tournamentType === "LEAGUE";

  return (
    <section className="container px-4 mx-auto">
      <div className="flex justify-between items-center">
        <div className="flex items-center gap-x-3">
          <h2 className="text-lg font-medium text-gray-800 dark:text-white">
            Teams
          </h2>
          <span className="px-3 py-1 text-xs text-blue-600 bg-blue-100 rounded-full dark:bg-gray-800 dark:text-blue-400">
            {registeredTeams.length}
          </span>
        </div>

        <Button onClick={() => setOpen(true)} content="Register a team" />
      </div>
      <div className="flex flex-col mt-6">
        <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div className="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
            <div className="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
              <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                <thead className="bg-gray-50 dark:bg-gray-800">
                  <tr>
                    <th
                      scope="col"
                      className="py-3.5 px-4 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400"
                    >
                      <div className="flex items-center gap-x-3">
                        <span>Team</span>
                      </div>
                    </th>
                    <th
                      scope="col"
                      className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400"
                    ></th>

                    {showPointsColumn && (
                      <th
                        scope="col"
                        className="px-2 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400"
                      >
                        Points
                      </th>
                    )}

                    <th
                      scope="col"
                      className="px-4 py-3.5 text-sm font-normal text-center text-gray-500 dark:text-gray-400"
                    >
                      Remove
                    </th>

                    <th scope="col" className="relative py-3.5 px-4">
                      <span className="sr-only">Remove</span>
                    </th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200 dark:divide-gray-700 dark:bg-gray-900">
                  {registeredTeams.map((tournamentTeam) => (
                    <tr key={tournamentTeam.id?.teamId}>
                      <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                        <div className="inline-flex items-center gap-x-3">
                          <div className="flex items-center gap-x-2">
                            <img
                              className="object-cover w-10 h-10 rounded-full"
                              src={logo}
                              alt=""
                            />
                          </div>
                        </div>
                      </td>
                      <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                        {tournamentTeam?.team?.name}
                      </td>
                      {showPointsColumn && (
                        <td className="px-2 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                          {tournamentTeam.points}
                        </td>
                      )}

                      <td className="px-4 py-4 text-sm whitespace-nowrap">
                        <div className="flex justify-center items-center gap-x-6">
                          <button
                            onClick={() => {
                              setSelectedTournamentTeam(tournamentTeam);
                              setOpenRemove(true);
                            }}
                            className="text-gray-500 transition-colors duration-200 dark:hover:text-red-500 dark:text-gray-300 hover:text-red-500 focus:outline-none"
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              fill="none"
                              viewBox="0 0 24 24"
                              strokeWidth="1.5"
                              stroke="currentColor"
                              className="w-5 h-5"
                            >
                              <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.021-2.09 2.201v.916m7.5 0L21.5 5.79M4.772 5.79L2 5.79M2 5.79h1.772M21.5 5.79l2.772 0M21.5 5.79V4M2 5.79V4"
                              ></path>
                            </svg>
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      {open && <RegisterTeamModal open={open} setOpen={setOpen} />}
      {openRemove ? (
        <DeleteModal
          open={openRemove}
          setOpen={setOpenRemove}
          element={selectedTournamentTeam as ITournamentTeams}
        />
      ) : (
        ""
      )}
    </section>
  );
};

export default StandingsTable;
