import { RegisterModalProps } from "../propsTypes/RegisterModalProps";
import { Dialog, Transition } from "@headlessui/react";
import { Fragment, useEffect, useRef, useState } from "react";
import { RootState, useAppDispatch } from "../redux/store";
import { useParams } from "react-router-dom";
import ITeamPlayers from "../interfaces/ITeamPlayers";
import { useSelector } from "react-redux";
import ITeam from "../interfaces/ITeam";
import { toast } from "react-toastify";
import { fetchSelectedTeam } from "../redux/teams/teamActions";
import {
  fetchAllPlayers,
  fetchSelectedPlayer,
} from "../redux/players/playerActions";
import { addPlayerToTeam } from "../redux/teamPlayers/teamPlayersActions";

const RegisterPlayerModal = ({ open, setOpen }: RegisterModalProps) => {
  const cancelButtonRef = useRef(null);
  const team = useSelector((state: RootState) => state.team.selectedTeam);
  const players = useSelector((state: RootState) => state.player.players);
  const dispatch = useAppDispatch();
  const routeParams = useParams();
  const [inputs, setInputs] = useState<ITeamPlayers>({
    team: team as ITeam,
    player: undefined,
  });

  const handleChange = (
    e:
      | React.ChangeEvent<HTMLInputElement>
      | React.ChangeEvent<HTMLSelectElement>
  ) => {
    const selectedPlayerId = e.target.value;
    const selectedPlayer = players.find(
      (player) => player.id === Number(selectedPlayerId)
    );
    setInputs((prevState) => ({
      ...prevState,
      team: team as ITeam,
      player: selectedPlayer,
    }));
    dispatch(fetchSelectedPlayer(Number(selectedPlayerId)));
  };

  const handleSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    dispatch(addPlayerToTeam(inputs))
      .then(() => {
        setOpen(false);
        toast.success("Player registered successfully");
      })
      .catch((err) => {
        console.error("Failed to register player:", err);
        toast.error("Failed to register player");
      });
  };
  useEffect(() => {
    dispatch(fetchSelectedTeam(Number(routeParams.id)));
    dispatch(fetchAllPlayers());
  }, []);
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
                  <div className="mx-auto flex h-12 w-12 items-center justify-center rounded-full bg-green-100">
                    <i
                      className="p-1 fa-sharp fa-solid fa-dungeon h-6 w-6 text-green-600"
                      aria-hidden="true"
                    ></i>
                  </div>
                  <div className="mt-3 text-center sm:mt-5">
                    <Dialog.Title
                      as="h3"
                      className="text-lg font-medium leading-6 text-gray-900"
                    >
                      {team?.name}
                    </Dialog.Title>

                    <form className="flex flex-col items-center mt-4">
                      <div className="text-left w-full">
                        <label
                          htmlFor="player"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Player
                        </label>
                        <select
                          className="w-full rounded-md"
                          onChange={handleChange}
                          name="player"
                          id="player"
                        >
                          <option value="">Select Player</option>
                          {players ? (
                            players.map((player) => (
                              <option key={player.id} value={player.id}>
                                {player.firstName} {player.lastName}
                              </option>
                            ))
                          ) : (
                            <option value=""></option>
                          )}
                        </select>
                      </div>
                    </form>
                  </div>
                </div>
                <div className="mt-5 sm:mt-6 sm:grid sm:grid-flow-row-dense sm:grid-cols-2 sm:gap-3">
                  <button
                    type="submit"
                    className="inline-flex w-full justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:col-start-2 sm:text-sm"
                    onClick={handleSubmit}
                  >
                    Register
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

export default RegisterPlayerModal;
