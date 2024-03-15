import { Dialog, Transition } from "@headlessui/react";
import { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { Fragment } from "react/jsx-runtime";
import { RootState, useAppDispatch } from "../redux/store";
import { useParams } from "react-router-dom";
import ITournamentTeams from "../interfaces/ITournamentTeams";
import { fetchTournamentById } from "../redux/tournaments/tournamentActions";
import { RegisterModalProps } from "../propsTypes/RegisterTeamModalProps";
import { registerTeamInTournament } from "../redux/tournamentTeams/tournamentTeamsActions";
import { toast } from "react-toastify";
import ITournament from "../interfaces/ITournament";
import { fetchAllTeams, fetchSelectedTeam } from "../redux/teams/teamActions";

const RegisterTeamModal = ({ open, setOpen }: RegisterModalProps) => {
  const cancelButtonRef = useRef(null);
  const tournament = useSelector(
    (state: RootState) => state.tournament.selectedTournament
  );

  const { status } = useSelector((state: RootState) => state.tournamentTeam);
  const dispatch = useAppDispatch();
  const routeParams = useParams();
  const teams = useSelector((state: RootState) => state.team.teams);
  const [inputs, setInputs] = useState<ITournamentTeams>({
    tournament: tournament as ITournament,
    team: undefined,
    points: 0,
  });

  const handleChange = (
    e:
      | React.ChangeEvent<HTMLInputElement>
      | React.ChangeEvent<HTMLSelectElement>
  ) => {
    const selectedTeamId = e.target.value;
    const selectedTeam = teams.find(
      (team) => team.id === Number(selectedTeamId)
    );
    setInputs((prevState) => ({
      ...prevState,
      team: selectedTeam,
    }));
    dispatch(fetchSelectedTeam(Number(selectedTeamId)));
  };

  const handleSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    console.log(inputs);
    dispatch(registerTeamInTournament(inputs))
      .then(() => {
        if (status == 201) {
          toast.success("Team registered successfully");
          setOpen(false);
        } else {
          toast.error("Failed to register team");
        }
      })
      .catch((err) => {
        console.error("Failed to register team:", err);
      });
  };
  useEffect(() => {
    dispatch(fetchTournamentById(Number(routeParams.id as string)));
    dispatch(fetchAllTeams());
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
                      {tournament?.tournamentType}
                    </Dialog.Title>

                    <form className="flex flex-col items-center mt-4">
                      <div className="text-left w-full">
                        <label
                          htmlFor="team"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Team
                        </label>
                        <select
                          className="w-full rounded-md"
                          onChange={handleChange}
                          name="team"
                          id="team"
                        >
                          <option value="">Select Team</option>
                          {teams ? (
                            teams.map((team) => (
                              <option key={team.id} value={team.id}>
                                {team.name}
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

export default RegisterTeamModal;
