import { MatchModalProps } from "../propsTypes/MatchModalProps";
import { Fragment, useEffect, useRef, useState } from "react";
import { RootState, useAppDispatch } from "../redux/store";
import IMatch from "../interfaces/IMatch";
import MatchStatus from "../enums/MatchStatus";
import { toast } from "react-toastify";
import { Dialog, Transition } from "@headlessui/react";
import { useSelector } from "react-redux";
import { fetchAllTeams } from "../redux/teams/teamActions";
import { fetchAllStadiums } from "../redux/stadiums/stadiumActions";
import { createMatch } from "../redux/matches/matchActions";
import MatchType from "../enums/MatchType";

const MatchModal = ({ open, setOpen, fixture }: MatchModalProps) => {
  const cancelButtonRef = useRef(null);
  const dispatch = useAppDispatch();
  const teams = useSelector((state: RootState) => state.team.teams);
  const stadiums = useSelector((state: RootState) => state.stadium.stadiums);
  const [inputs, setInputs] = useState<IMatch>({
    time: "",
    status: MatchStatus.SCHEDULED,
    matchType:
      fixture && "league" in fixture ? MatchType.LEAGUE : MatchType.CUP,
    roundId: fixture && "league" in fixture ? null : fixture?.id,
    matchDayId: fixture && "league" in fixture ? fixture.id : null,
    stadiumId: 0,
    opponentAId: 0,
    opponentBId: 0,
  });

  const handleChange = (
    e:
      | React.ChangeEvent<HTMLInputElement>
      | React.ChangeEvent<HTMLSelectElement>
  ) => {
    setInputs((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    console.log(inputs);

    dispatch(createMatch(inputs))
      .then(() => {
        toast.success("Match created successfully");
        setOpen(false);
      })
      .catch((err) => {
        console.error("Failed to create match:", err);
        toast.error("Failed to create match");
      });
  };

  useEffect(() => {
    dispatch(fetchAllTeams());
    dispatch(fetchAllStadiums());
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
                      Fill match information
                    </Dialog.Title>

                    <form className="flex flex-col items-center mt-4">
                      <div className="text-left w-full">
                        <label
                          htmlFor="time"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Time
                        </label>
                        <div className="mt-1">
                          <input
                            type="time"
                            name="time"
                            value={inputs?.time}
                            onChange={handleChange}
                            id="time"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                          />
                        </div>
                      </div>
                      <div className="text-left w-full">
                        <label
                          htmlFor="opponentAId"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Team A
                        </label>
                        <div className="mt-1">
                          <select
                            onChange={handleChange}
                            value={inputs.opponentAId}
                            name="opponentAId"
                            id="opponentAId"
                            className="w-full rounded-md"
                          >
                            <option value="">Select First Team</option>
                            {teams &&
                              teams.map((team) => (
                                <option value={team.id} key={team.id}>
                                  {team.name}
                                </option>
                              ))}
                          </select>
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="opponentBId"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Team B
                        </label>
                        <div className="mt-1">
                          <select
                            onChange={handleChange}
                            value={inputs.opponentBId}
                            name="opponentBId"
                            id="opponentBId"
                            className="w-full rounded-md"
                          >
                            <option value="">Select Second Team</option>
                            {teams &&
                              teams.map((team) => (
                                <option value={team.id} key={team.id}>
                                  {team.name}
                                </option>
                              ))}
                          </select>
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="stadiumId"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Stadium
                        </label>
                        <div className="mt-1">
                          <select
                            onChange={handleChange}
                            value={inputs.stadiumId}
                            name="stadiumId"
                            id="stadiumId"
                            className="w-full rounded-md"
                          >
                            <option value="">Select Stadium</option>
                            {stadiums &&
                              stadiums.map((stadium) => (
                                <option value={stadium.id} key={stadium.id}>
                                  {stadium.name}
                                </option>
                              ))}
                          </select>
                        </div>
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
                    Add
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

export default MatchModal;
