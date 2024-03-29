import { Dialog, Transition } from "@headlessui/react";
import { Fragment, useRef } from "react";
import { DeleteModalProps } from "../propsTypes/DeleteModalProps";
import { toast } from "react-toastify";
import { useAppDispatch } from "../redux/store";
import { deleteStadium } from "../redux/stadiums/stadiumActions";
import { deleteTournament } from "../redux/tournaments/tournamentActions";
import { removeTeamFromTournament } from "../redux/tournamentTeams/tournamentTeamsActions";
import { TournamentTeamKey } from "../embddables/TournamentTeamKey";
import { deleteTeam } from "../redux/teams/teamActions";
import { HiOutlineExclamationCircle } from "react-icons/hi";
import { removePlayerFromTeam } from "../redux/teamPlayers/teamPlayersActions";
import { TeamPlayerKey } from "../embddables/TeamPlayerKey";
import { deletePlayer } from "../redux/players/playerActions";

const DeleteModal = ({ open, setOpen, element }: DeleteModalProps) => {
  const cancelButtonRef = useRef(null);
  const dispatch = useAppDispatch();

  const deleteElement = async () => {
    if ("capacity" in element) {
      dispatch(deleteStadium(element.id as number))
        .then(() => {
          toast.success("Stadium deleted successfully");
          setOpen(false);
        })
        .catch((err) => {
          console.error("Failed to create stadium:", err);
          toast.error("Failed to delete stadium");
        });
    } else if ("tournamentType" in element) {
      dispatch(deleteTournament(element.id as number))
        .then(() => {
          toast.success("Tournament deleted successfully");
          setOpen(false);
        })
        .catch((err) => {
          console.error("Failed to create tournament:", err);
          toast.error("Failed to delete tournament");
        });
    } else if ("points" in element) {
      const tournamentTeamKey: TournamentTeamKey = {
        teamId: element.team?.id as number,
        tournamentId: element.tournament?.id as number,
      };
      dispatch(removeTeamFromTournament(tournamentTeamKey))
        .then(() => {
          toast.success("Team removed successfully");
          setOpen(false);
        })
        .catch((err) => {
          console.error("Failed to remove team:", err);
          toast.error("Failed to remove team from tournament");
        });
    } else if ("country" in element) {
      dispatch(deleteTeam(element.id as number))
        .then(() => {
          toast.success("Team deleted successfully");
          setOpen(false);
        })
        .catch((err) => {
          console.error("Failed to create team:", err);
          toast.error("Failed to delete team");
        });
    } else if ("joinedAt" in element) {
      const teamPlayerKey: TeamPlayerKey = {
        teamId: element.team.id as number,
        playerId: element?.player?.id as number,
      };
      dispatch(removePlayerFromTeam(teamPlayerKey))
        .then(() => {
          toast.success("Player removed from team successfully");
          setOpen(false);
        })
        .catch((err) => {
          console.error("Failed to remove player:", err);
          toast.error("Failed to remove player");
        });
    } else if ("weight" in element) {
      dispatch(deletePlayer(element.id as number))
        .then(() => {
          toast.success("Player deleted successfully");
          setOpen(false);
        })
        .catch((err) => {
          console.error("Failed to delete player:", err);
          toast.error("Failed to delete player");
        });
    }
  };
  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog
        as="div"
        className="relative z-10"
        initialFocus={cancelButtonRef}
        onClose={setOpen}
      >
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
        </Transition.Child>

        <div className="fixed inset-0 z-10 overflow-y-auto">
          <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              enterTo="opacity-100 translate-y-0 sm:scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 translate-y-0 sm:scale-100"
              leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            >
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white px-4 pt-5 pb-4 text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg sm:p-6">
                <div>
                  <HiOutlineExclamationCircle className="mx-auto mb-4 h-14 w-14 text-gray-400 dark:text-gray-200" />
                  <div className="mt-3 text-center sm:mt-5">
                    <Dialog.Title
                      as="h3"
                      className="text-lg font-medium leading-6 text-gray-900"
                    >
                      Are you sure you want to delete this element
                    </Dialog.Title>
                  </div>
                </div>
                <div className="mt-5 sm:mt-6 sm:grid sm:grid-flow-row-dense sm:grid-cols-2 sm:gap-3">
                  <button
                    type="submit"
                    className="inline-flex w-full justify-center rounded-md border border-transparent bg-red-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 sm:col-start-2 sm:text-sm"
                    onClick={deleteElement}
                  >
                    Delete
                  </button>

                  <button
                    type="button"
                    className="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:col-start-1 sm:mt-0 sm:text-sm"
                    onClick={() => setOpen(false)}
                    ref={cancelButtonRef}
                  >
                    Cancel
                  </button>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition.Root>
  );
};

export default DeleteModal;
